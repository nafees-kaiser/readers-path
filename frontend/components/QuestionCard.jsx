import React from 'react';
import TextField from "@/components/TextField";
import Button from "@/components/Button";

const QuestionCard = ({isAuthor = false}) => {
    const ans = "null";
    return (
        <div className={"flex-col flex gap-2 p-2 border border-border rounded-lg md:p-4"}>
            <div className={"flex gap-2 items-start text-sm md:text-base"}>
                <div className={"font-bold"}>Q:</div>
                <div className={"flex flex-col gap-1"}>
                    <p>What is your inspiration to write this book?</p>
                    <div className={"text-light-text text-sm md:text-base"}>Asked by user</div>
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
                        {ans ? (
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean sit amet mauris nunc.
                                Nulla
                                nec
                                neque
                                vitae justo varius lacinia id non tortor. Donec at enim lacinia tellus sodales
                                tempus. </p>
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