import React from 'react';
import Image from "next/image";
import logo from "@/public/logo_primary.svg"
import Link from "next/link";

const AuthLayout = ({children}) => {
    return (
        <section>
            <Link href={"/"}>
                <div className={"w-36 md:w-48 mx-[10px] md:mx-[50px] lg:mx-[100px] my-4"}>
                    <Image src={logo} alt={"logo"}/>
                </div>

            </Link>
            {children}
        </section>
    );
};

export default AuthLayout;