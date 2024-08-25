import {Lora} from "next/font/google";
import "./globals.css";
import Footer from "@/components/Footer";
import {getServerSession} from "next-auth";
import SessionProvider from "@/utils/sessionProvider";


const lora = Lora({subsets: ["latin"]});

export const metadata = {
    title: "Reader's Path",
    description: "A website for increasing reading habit",
};

export default async function RootLayout({children}) {
    const session = await getServerSession()
    return (
        <html lang="en">
        <body className={lora.className}>
        <SessionProvider session={session}>
            {children}
        </SessionProvider>
        <Footer/>
        </body>
        </html>
    );
}
