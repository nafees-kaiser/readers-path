'use client'
import React, {useState} from 'react';
import Image from "next/image";
import navbarLogo from "../public/logo_name_white.svg"
import {FaUser} from "react-icons/fa6";
import {PiBooksBold} from "react-icons/pi";
import Link from "next/link";
import {IoMenu} from "react-icons/io5";
import UserNavLinks from "@/components/UserNavLinks";

const UserNavbar = ({isLoggedIn = false}) => {
    const [navBar, setNavBar] = useState(false);
    return (
        <nav className={""}>
            <div className={"bg-primary flex justify-between py-4 text-white px-[10px] md:px-[50px] lg:px-[100px]"}>
                <div>
                    <Image src={navbarLogo} alt={"Logo"} width={170}/>
                </div>
                <div className={"hidden md:block"}>
                    <UserNavLinks className={'flex md:gap-4 m-auto lg:gap-8'}/>
                </div>
                <div className={"flex gap-1.5"}>
                    {isLoggedIn ? (
                        <div className={"flex gap-1.5"}>
                            <Link href={"/shelf"}>
                                <PiBooksBold size={28} color={"white"}/>
                            </Link>
                            <Link href={"/profile"}>
                                <FaUser size={25} color={"white"}/>
                            </Link>
                        </div>

                    ) : (
                        <div>
                            <Link className={"hover:underline text-sm md:text-base"} href={"/login"}>Login</Link>
                        </div>

                    )}
                    <div className={"md:hidden"}>
                        <button onClick={() => setNavBar(!navBar)}>
                            <IoMenu size={28}/>
                        </button>

                    </div>
                </div>
            </div>

            {navBar && (
                <div className={'py-6 bg-white text-black md:hidden px-[10px] shadow-tertiary-bg shadow-md '}>
                    <UserNavLinks className={'flex flex-col gap-4'}/>
                </div>
            )}


        </nav>
    );
};

export default UserNavbar;