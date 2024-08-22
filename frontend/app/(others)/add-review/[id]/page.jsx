"use client"
import React, {useState} from 'react';
import BookInfoCard from "@/components/BookInfoCard";
import LargeTextField from "@/components/LargeTextField";
import Button from "@/components/Button";
import CustomRating from "@/components/CustomRating";
import TextField from "@/components/TextField";

const Page = ({params: {id}}) => {
    const [rating, setRating] = useState("");
    return (
        <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6"}>
            <div className={"text-lg md:text-xl font-bold"}>Add review</div>
            <BookInfoCard/>
            <div className={"flex flex-col gap-4"}>
                <div className={"text-lg md:text-xl font-bold"}>Rating</div>
                <div className={"flex w-fit gap-2 items-center"}>
                    <div>
                        <CustomRating rating={rating} className={"text-xl md:text-2xl"}
                                      onChange={(v) => setRating(v.toString())}/>
                    </div>
                    <div className={"w-16"}>
                        <TextField placeholder={"5"} value={rating} handleChange={(e) => {
                            e.preventDefault();
                            setRating(e.target.value);
                        }}/>
                    </div>

                </div>
            </div>
            <div className={"flex flex-col gap-4"}>
                <div className={"text-lg md:text-xl font-bold"}>Review</div>
                <div className={"flex flex-col gap-2 items-end"}>
                    <LargeTextField placeholder={"Write something..."}/>
                    <Button
                        className={"bg-secondary hover:bg-button-hover text-white"}
                        content={"Submit"}/>
                </div>
            </div>
        </div>
    );
};

export default Page;