import React from 'react';
import BookInfoCard from "@/components/BookInfoCard";
import CustomRating from "@/components/CustomRating";
import ReviewCard from "@/components/ReviewCard";
import QuestionCard from "@/components/QuestionCard";

const BookInfoBody = ({isAuthor = false}) => {
    return (
        <div className={"flex flex-col gap-6"}>
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
                <div className={"flex flex-col gap-3"}>
                    {[...Array(5)].map((v, i) => {
                        return <ReviewCard/>
                    })}
                </div>
            </div>
            <div className={"flex flex-col gap-4"}>
                <div className={"text-lg md:text-xl font-bold"}>Questions about the book</div>
                <div className={"flex flex-col gap-3"}>
                    {[...Array(5)].map((v, i) => {
                        return <QuestionCard isAuthor={isAuthor}/>
                    })}
                </div>
            </div>
        </div>
    );
};

export default BookInfoBody;