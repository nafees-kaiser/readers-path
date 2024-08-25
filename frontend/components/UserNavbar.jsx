'use client'
import React, {useState} from 'react';
import Image from "next/image";
import navbarLogo from "../public/logo_name_white.svg"
import {FaUser} from "react-icons/fa6";
import {PiBooksBold} from "react-icons/pi";
import Link from "next/link";
import {IoMenu} from "react-icons/io5";
import UserNavLinks from "@/components/UserNavLinks";
import ProfileModal from "@/components/ProfileModal";
import {useSession} from "next-auth/react";

const UserNavbar = () => {
    const [navBar, setNavBar] = useState(false);
    const [isProfileModalOpen, setProfileModalOpen] = useState(false);
    const session = useSession();
    return (
        <nav>
            <div
                className={"bg-primary flex justify-between items-center py-4 text-white px-[10px] md:px-[50px] lg:px-[100px]"}>
                <div className={"w-32 lg:w-52"}>
                    <Image src={navbarLogo} alt={"Logo"}/>
                </div>
                <div className={"hidden md:block"}>
                    <UserNavLinks className={'flex md:gap-6 m-auto lg:gap-8'}/>
                </div>
                <div className={"flex gap-1.5"}>
                    {session?.data?.user ? (
                        <div className={"flex gap-1.5"}>
                            <Link href={"/shelf"}>
                                <div className={"text-2xl lg:text-4xl"}>
                                    <PiBooksBold color={"white"}/>
                                </div>

                            </Link>
                            <button onClick={() => {
                                setNavBar(false)
                                setProfileModalOpen(!isProfileModalOpen)
                            }}>
                                <div className={"text-xl lg:text-3xl mb-2"}>
                                    <FaUser color={"white"}/>
                                </div>

                            </button>
                        </div>

                    ) : (
                        <div>
                            <Link className={"hover:underline text-sm md:text-base"} href={"/login"}>Login</Link>
                        </div>

                    )}
                    <div className={"md:hidden"}>
                        <button onClick={() => {
                            setProfileModalOpen(false);
                            setNavBar(!navBar)
                        }}>
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

            {isProfileModalOpen && (
                <div className={"absolute top-0 bg-transparent w-screen h-screen"}
                     onClick={() => setProfileModalOpen(false)}>
                    <div className={"absolute z-10 top-14 right-[15px] md:right-[50px] lg:right-[100px]"}
                         onClick={(e) => e.stopPropagation()}>
                        <ProfileModal/>
                    </div>
                </div>

            )}
        </nav>
    );
};

export default UserNavbar;