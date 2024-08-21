import React from 'react';
import BookInfoCard from "@/components/BookInfoCard";
import ReviewCard from "@/components/ReviewCard";
import CustomRating from "@/components/CustomRating";
import QuestionCard from "@/components/QuestionCard";
import Button from "@/components/Button";
import LargeTextField from "@/components/LargeTextField";

const Page = ({params: {id}}) => {
    return (
        <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6"}>
            <div>
                <BookInfoCard/>
            </div>
            <div className={"flex flex-col gap-4"}>
                <div className={"text-lg md:text-xl font-bold"}>Reviews and Rating</div>
                <div className={"flex flex-col gap-2 items-center"}>
                    <div className={"flex items-end"}>
                        <div className={"text-lg md:text-xl font-bold"}>4.5/</div>
                        <div className={"text-base md:text-lg"}>5 (322)</div>
                    </div>
                    <div>
                        <CustomRating readOnly={true} rating={4.5} className={"text-xl md:text-2xl"}/>
                    </div>
                </div>
                <div className={"flex flex-col gap-2"}>
                    {[...Array(5)].map((v, i) => {
                        return <ReviewCard/>
                    })}
                </div>
            </div>
            <div className={"flex flex-col gap-4"}>
                <div className={"text-lg md:text-xl font-bold"}>Questions about the book</div>
                <div className={"flex flex-col gap-2"}>
                    {[...Array(5)].map((v, i) => {
                        return <QuestionCard/>
                    })}
                </div>
            </div>
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