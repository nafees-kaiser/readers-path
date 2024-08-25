import React from 'react';
import TextField from "@/components/TextField";
import Button from "@/components/Button";

const QuestionCard = ({isAuthor = false, value}) => {
    // const ans = "null";
    return (
        <div className={"flex-col flex gap-2 p-2 border border-border rounded-lg md:p-4"}>
            <div className={"flex gap-2 items-start text-sm md:text-base"}>
                <div className={"font-bold"}>Q:</div>
                <div className={"flex flex-col gap-1"}>
                    <p>{value?.question ? value?.question : "Question"}</p>
                    <div className={"text-light-text text-sm md:text-base"}>Asked by {value?.askedBy?.name ?
                        value?.askedBy?.name : "User"
                    }</div>
                </div>
            </div>
            <div className={"flex gap-2 items-start text-sm md:text-base"}>
                <div className={"font-bold"}>A:</div>
                {isAuthor ? (
                    <div className={"flex gap-1.5 w-full"}>
                        <TextField
                            placeholder={"Write the answer"}
                        />
                        <Button
                            content={"Save"}
                            className={"bg-secondary hover:bg-button-hover text-white"}
                        />
                    </div>

                ) : (
                    <div>
                        {value?.answer ? (
                            <p>{value?.answer}</p>
                        ) : (
                            <p className={"italic"}>Not answered by the author yet!</p>
                        )}
                    </div>
                )}

            </div>


        </div>
    );
};

export default QuestionCard;