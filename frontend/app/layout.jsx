import {Lora} from "next/font/google";
import "./globals.css";
import Footer from "@/components/Footer";

const lora = Lora({subsets: ["latin"]});

export const metadata = {
    title: "Reader's Path",
    description: "A website for increasing reading habit",
};

export default function RootLayout({children}) {
    return (
        <html lang="en">
        <body className={lora.className}>
        {children}
        <Footer/>
        </body>
        </html>
    );
}
