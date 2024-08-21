import React from 'react';
import ProfileAvatar from "@/components/ProfileAvatar";
import {IoBookOutline} from "react-icons/io5";
import {BsCoin} from "react-icons/bs";
import ProfileModalLink from "@/components/ProfileModalLink";

const ProfileModal = () => {
    return (
        <div className={"w-52 bg-white rounded-lg  shadow-lg shadow-gray-700/30 p-4"}>
            <div className={"flex gap-2"}>
                <ProfileAvatar className={"text-lg"}/>
                <div className={"flex flex-col gap-1.5 text-xs md:text-sm overflow-visible text-wrap"}>
                    <p className={"font-bold"}>Ikhtiar Uddin Mohammad Bokhtir khilji</p>
                    <p className={"text-light-text"}>User email</p>
                    <div className={"flex gap-4 items-center"}>
                        <div className={"flex gap-1 text-xs md:text-sm text-light-text"}>
                            <div className={"text-sm md:text-base"}>
                                <IoBookOutline/>
                            </div>
                            <div>10</div>
                        </div>
                        <div className={"flex gap-1 text-xs md:text-sm text-light-text"}>
                            <div className={"text-sm md:text-base"}>
                                <BsCoin/>
                            </div>
                            <div>100</div>
                        </div>
                    </div>
                </div>
            </div>


            <div className={"border-t border-border w-full mt-2 mb-4"}></div>
            <div className={"flex flex-col gap-2"}>
                <ProfileModalLink content={"Profile"} address={"/profile"}/>
                <ProfileModalLink content={"Add a book"} address={"/add-book"}/>
                <ProfileModalLink content={"My books"} address={"/my-books"}/>
                <ProfileModalLink content={"Logout"} address={"/logout"}/>
            </div>
        </div>
    );
};

export default ProfileModal;