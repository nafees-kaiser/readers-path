"use client"
import React, {useState} from 'react';
import TextField from "@/components/TextField";
import Button from "@/components/Button";
import useSWRMutation from "swr/mutation";
import {postFetcher} from "@/utils/fetcher";
import {useSWRConfig} from "swr";

const QuestionCard = ({isAuthor = false, value, bookId}) => {

    const {trigger} = useSWRMutation(['/user/add-ans'], postFetcher)
    const {mutate} = useSWRConfig()
    const [ans, setAns] = useState('')

    const addAns = async () => {
        try {
            const res = await trigger({
                id: value?.id,
                answer: ans
            })
            // console.log(res)
            if (res.status === 201) {
                // toast.success("Question added successfully!");
                mutate('/books/' + bookId)
            }
        } catch (e) {
            console.log(e)
        }
        // console.log(ans)
    }
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
                {/*{isAuthor ? (*/}
                {/*    <div className={"flex gap-1.5 w-full"}>*/}
                {/*        <TextField*/}
                {/*            placeholder={"Write the answer"}*/}
                {/*            onChange={(e) => {*/}
                {/*                e.preventDefault()*/}
                {/*                setAns(e.target.value)*/}
                {/*            }}*/}
                {/*        />*/}
                {/*        <Button*/}
                {/*            content={"Save"}*/}
                {/*            className={"bg-secondary hover:bg-button-hover text-white"}*/}
                {/*        />*/}
                {/*    </div>*/}

                {/*) : (*/}
                {/*    <div>*/}
                {/*        {value?.answer ? (*/}
                {/*            <p>{value?.answer}</p>*/}
                {/*        ) : (*/}
                {/*            */}
                {/*            <p className={"italic"}>Not answered by the author yet!</p>*/}
                {/*        )}*/}
                {/*    </div>*/}
                {/*)}*/}
                {value?.answer ? (
                    <p>{value?.answer}</p>
                ) : isAuthor ? (
                    <div className={"flex gap-1.5 w-full"}>
                        <TextField
                            placeholder={"Write the answer"}
                            handleChange={(e) => {
                                e.preventDefault()
                                setAns(e.target.value)
                            }}
                        />
                        <Button
                            content={"Save"}
                            onClick={() => addAns().then()}
                            className={"bg-secondary hover:bg-button-hover text-white"}
                        />
                    </div>
                ) : (
                    <p className={"italic"}>Not answered by the author yet!</p>
                )}


            </div>


        </div>
    );
};

export default QuestionCard;