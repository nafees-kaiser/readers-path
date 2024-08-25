import NextAuth from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials"
import baseUrl from "@/utils/baseUrl";
import axios from "axios";


const handler = NextAuth({
    secret: process.env.NEXTAUTH_SECRET,
    providers: [
        CredentialsProvider({
            name: "Credentials",
            credentials: {
                email: {label: "Email", type: "text", placeholder: "Enter Your Email"},
                password: {label: "Password", type: "password", placeholder: "Enter Your Password"}
            },
            async authorize(credentials, req) {
                // // const res = await fetch("http://localhost:8080/api/v1/login", {
                // //     method: 'POST',
                // //     body: JSON.stringify(credentials),
                // //     headers: {"Content-Type": "application/json"}
                // // })
                // const user = await res.json();
                const res = await axios.post(baseUrl + "/login", credentials);
                const user = res.data;
                console.log(credentials);
                if (res.status === 200 && user)
                    return user
                else
                    return null;
            }
        })
    ],
    jwt: {
        signingKey: process.env.JWT_SIGNING_PRIVATE_KEY,
    },
    callbacks: {
        async jwt({token, user}) {
            return {...token, ...user}
        },
        async session({session, token, user}) {
            session.user = token;
            // session.user.user.role = user;
            return session;
        }
    },
    session: {
        strategy: "jwt",
        maxAge: 120000 //in seconds
    },
    pages: {
        signIn: '/login',
        signOut: '/auth/signout',
        error: '/login', // Error code passed in query string as ?error=
        verifyRequest: '/auth/verify-request', // (used for check email message)
        newUser: '/register' // New users will be directed here on first sign in (leave the property out if not of interest)
    }
})

export {handler as GET, handler as POST}