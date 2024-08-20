import React from 'react';
import Link from "next/link";
import Image from "next/image";
import logoBlack from "../public/logo_name_black.svg"

function Footer() {
    return (
        <div className={"flex flex-col bg-tertiary-bg w-full"}>
            <div
                className={"flex gap-[20%] lg:gap-[30%] md:gap-[25%] mx-[15px] pt-[70px] pb-10 text-xs md:text-base md:mx-[50px] lg:mx-[100px]"}>
                <div>
                    <Image src={logoBlack} alt={"Logo"}/>
                </div>
                <div className={"flex gap-[20%] w-full"}>
                    <ul className={"flex flex-col gap-1.5"}>
                        <li><Link href={""}>Home</Link></li>
                        <li><Link href={"/books"}>All books</Link></li>
                        <li><Link href={"/recommended-books"}>Recommended</Link></li>
                        <li><Link href={"/shelf"}>Shelf</Link></li>
                    </ul>
                    <ul className={"flex flex-col gap-1.5"}>
                        <li><Link href={"/profile"}>Profile</Link></li>
                        <li><Link href={"/add-book"}>Add a book</Link></li>
                        <li><Link href={"/my-books"}>View my books</Link></li>
                    </ul>
                </div>
            </div>
            <div>
                <div className={"border border-divider"}></div>
                <div className={"text-center py-2 text-xs md:text-base"}>&copy; copyright 2024</div>
            </div>
        </div>

    );
}

export default Footer;