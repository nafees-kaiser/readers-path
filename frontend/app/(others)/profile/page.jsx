"use client"
import React, {Suspense, useState} from 'react';
import useSWR, {mutate} from "swr";
import {fetcher, postFetcher, putFetcher} from "@/utils/fetcher";
import {IoBookOutline} from "react-icons/io5";
import {BsCoin} from "react-icons/bs";
import {FallingLines} from "react-loader-spinner";
import {FiEdit} from "react-icons/fi";
import TextField from "@/components/TextField";
import Button from "@/components/Button";
import ProfileEditCard from "@/components/ProfileEditCard";
import useSWRMutation from "swr/mutation";
import {toast} from "sonner";
import {signOut} from "next-auth/react";

const Page = () => {
    const {data: rewardFetchData} = useSWR("/user/reward", fetcher)
    const {data: userFetchData} = useSWR("/user", fetcher)
    const user = userFetchData?.data
    const rewardData = rewardFetchData?.data;

    const {trigger} = useSWRMutation(['/user/edit-user', {}], putFetcher)

    const edit = async (value, isEmail) => {
        try {
            const res = await trigger(value)
            if (res.status === 200 || res.status === 201) {
                toast.success("Updated info successfully")
                if (isEmail) {
                    toast.success("You need to login again")
                    await signOut({
                        callbackUrl: "/login",
                    })
                } else{
                    mutate("/user")
                }
            }
        } catch (e) {
            console.log(e)
        }
    }

    const [isEdit, setEdit] = useState(false);
    return (
        <div className={"flex flex-col px-[15px] md:px-[150px] lg:px-[270px] py-8 gap-6"}>
            <div className={"flex flex-col gap-4"}>
                <div className={"flex flex-col gap-2"}>
                    <h1 className={"font-bold text-3xl md:text-4xl"}>Reward</h1>
                    <p className={"text-light-text text-xs md:text-sm"}>You will get 20 reward coins per completed
                        book!</p>
                </div>
                <div className={"flex gap-8 items-center"}>
                    <div className={"flex gap-1 text-sm md:text-base justify-center items-center"}>
                        <div className={"text-lg md:text-xl"}>
                            <IoBookOutline/>
                        </div>
                        <div>
                            Completed books:
                        </div>
                        <div>{rewardData?.completedBooksCount ?
                            rewardData?.completedBooksCount : 0
                        }</div>
                    </div>
                    <div className={"flex gap-1 text-sm md:text-base justify-center items-center"}>
                        <div className={"text-lg md:text-xl"}>
                            <BsCoin/>
                        </div>
                        <div>
                            Reward coins:
                        </div>
                        <div>{rewardData?.coins ?
                            rewardData?.coins : 20
                        }</div>
                    </div>
                </div>
            </div>
            <Suspense fallback={<FallingLines color="#116466" width="100" visible={true}
                                              ariaLabel="falling-circles-loading"/>}>
                <div className={"flex flex-col gap-4"}>
                    <h1 className={"font-bold text-3xl md:text-4xl"}>User details</h1>
                    <ProfileEditCard
                        content={user?.name}
                        heading={"Name"}
                        edit={edit}
                        isEmail={false}
                        name={"name"}
                    />

                    <ProfileEditCard
                        content={user?.email}
                        heading = {"Email"}
                        edit={edit}
                        isEmail={true}
                        name={"email"}
                    />
                </div>
            </Suspense>

        </div>
    );
};

export default Page;