"use client"
import React from 'react';
import ProfileAvatar from "@/components/ProfileAvatar";
import {IoBookOutline} from "react-icons/io5";
import {BsCoin} from "react-icons/bs";
import ProfileModalLink from "@/components/ProfileModalLink";
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";
import {signOut} from "next-auth/react";

const ProfileModal = () => {
    const {data} = useSWR("/user", fetcher)
    const user = data?.data

    const {data: rewardData} = useSWR("/user/reward", fetcher)
    const rw = rewardData?.data
    // console.log(rw)

    const logout = async () => {
        await signOut({
            callbackUrl: "/",
        })
    }

    return (
        <div className={"w-[300px] bg-white rounded-lg  shadow-lg shadow-gray-700/30 p-4"}>
            <div className={"flex gap-2"}>
                <ProfileAvatar className={"text-lg"}/>
                <div className={"flex flex-col gap-1.5 text-xs md:text-sm overflow-visible text-wrap"}>
                    <p className={"font-bold"}>{user?.name}</p>
                    <p className={"text-light-text"}>{user?.email}</p>
                    <div className={"flex gap-4 items-center"}>
                        <div className={"flex gap-1 text-xs md:text-sm text-light-text justify-center items-center"}>
                            <div className={"text-sm md:text-base"}>
                                <IoBookOutline/>
                            </div>
                            <div>{rw?.completedBooksCount ?
                                rw?.completedBooksCount : 0
                            }</div>
                        </div>
                        <div className={"flex gap-1 text-xs md:text-sm text-light-text justify-center items-center"}>
                            <div className={"text-sm md:text-base"}>
                                <BsCoin/>
                            </div>
                            <div>{rw?.coins ?
                                rw?.coins : 20
                            }</div>
                        </div>
                    </div>
                </div>
            </div>


            <div className={"border-t border-border w-full mt-2 mb-4"}></div>
            <div className={"flex flex-col gap-2 items-start justify-start"}>
                <ProfileModalLink content={"Profile"} address={"/profile"}/>
                <ProfileModalLink content={"Add a book"} address={"/add-book"}/>
                <ProfileModalLink content={"My books"} address={"/my-books"}/>
                {/*<ProfileModalLink content={"Logout"} address={"/logout"}/>*/}
                <button
                    onClick={() => logout().then()}
                    className={"text-xs md:text-sm font-bold w-full text-start text-secondary px-2 hover:bg-light-text"}>
                    Logout
                </button>
            </div>
        </div>
    );
};

export default ProfileModal;