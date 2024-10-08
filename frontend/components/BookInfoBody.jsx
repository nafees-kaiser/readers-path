"use client"
import React from 'react';
import BookInfoCard from "@/components/BookInfoCard";
import CustomRating from "@/components/CustomRating";
import ReviewCard from "@/components/ReviewCard";
import QuestionCard from "@/components/QuestionCard";

const BookInfoBody = ({value, isMyBook = false}) => {


    return (
        <div className={"flex flex-col gap-6"}>
            <div>
                <BookInfoCard value={value} isMyBook={isMyBook}/>
            </div>
            {value?.reviewsAndRating && value?.reviewsAndRating.length > 0 && (
                <div className={"flex flex-col gap-4"}>
                    <div className={"text-lg md:text-xl font-bold"}>Reviews and Rating</div>
                    <div className={"flex flex-col gap-2 items-center"}>
                        <div className={"flex items-end"}>
                            <div
                                className={"text-lg md:text-xl font-bold"}>{value?.overAllRating ? value?.overAllRating.substring(0,4) : ""}/
                            </div>
                            <div className={"text-base md:text-lg"}>5 ({
                                value?.reviewsAndRating ? value?.reviewsAndRating.length : "0"
                            })
                            </div>
                        </div>
                        <div>
                            <CustomRating readOnly={true} rating={value?.overAllRating ? value?.overAllRating : 0}
                                          className={"text-xl md:text-2xl"}/>
                        </div>
                    </div>
                    <div className={"flex flex-col gap-3"}>
                        {
                            value?.reviewsAndRating.map((r, index) => (
                                <ReviewCard key={r.id}
                                            value={r}
                                />
                            ))
                        }
                    </div>
                </div>
            )}

            {
                value?.questionaries && value?.questionaries.length > 0 &&
                (
                    <div className={"flex flex-col gap-4"}>
                        <div className={"text-lg md:text-xl font-bold"}>Questions about the book</div>
                        <div className={"flex flex-col gap-3"}>
                            {value?.questionaries.map(ques => (
                                <QuestionCard key={ques.id}
                                              isAuthor={isMyBook}
                                              bookId={value?.id}
                                              value={ques}/>
                            ))}
                        </div>
                    </div>
                )
            }


        </div>
    );
};

export default BookInfoBody;