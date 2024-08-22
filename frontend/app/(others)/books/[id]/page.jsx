import React from 'react';
import Button from "@/components/Button";
import LargeTextField from "@/components/LargeTextField";
import BookInfoBody from "@/components/BookInfoBody";

const Page = ({params: {id}}) => {
    return (
        <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6"}>
            <BookInfoBody/>
            <div className={"flex flex-col gap-4"}>
                <div className={"text-lg md:text-xl font-bold"}>Add a question</div>
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