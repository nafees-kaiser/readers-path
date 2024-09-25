"use client"

import {useSession} from "next-auth/react";
import {useRouter} from "next/navigation";
import {FallingLines} from "react-loader-spinner";
import {useEffect} from "react";

const RedirectPage = () => {
    const router = useRouter();
    const {data: session} = useSession();
    useEffect(() => {
        if (session?.user?.role) {
            const userRole = session?.user?.role;
            if (userRole === "ROLE_ADMIN") {
                router.push("/admin")
            } else {
                router.push("/books")
            }
            // console.log(userRole);
        }
        // console.log(session?.user?.role)
    })

    return (
        <div className="absolute inset-0 z-10 w-full h-full flex flex-col justify-center items-center bg-white">
            <h1 className="text-xl mb-3">Redirecting...</h1>
            <FallingLines color="#116466" width="100" visible={true} ariaLabel="falling-circles-loading"/>
        </div>
    )
}

export default RedirectPage;
