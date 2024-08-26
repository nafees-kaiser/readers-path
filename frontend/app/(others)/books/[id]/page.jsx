"use client"
import React, {useState} from 'react';
import Button from "@/components/Button";
import LargeTextField from "@/components/LargeTextField";
import BookInfoBody from "@/components/BookInfoBody";
import useSWR from "swr";
import {fetcher, postFetcher} from "@/utils/fetcher";
import useSWRMutation from "swr/mutation";
import {toast, Toaster} from "sonner";

const Page = ({params: {id}}) => {
    // console.log(id);
    const url = `/books/${id}`;

    const [ques, setQues] = useState("");

    const {data, mutate} = useSWR(url, fetcher)
    const {data: bookStatus} = useSWR(`/user/is-my-book/${id}`, fetcher)
    // useEffect(() => {
    //     console.log(bookStatus)
    // });

    const {trigger} = useSWRMutation(['/user/add-ques', {}], postFetcher)

    const addQuestion = async () => {
        try {
            // console.log(ques)
            // console.log("book id:", id);
            const res = await trigger({
                question: ques,
                book: {
                    id: id
                }
            })
            // console.log(res)
            await mutate({...data})
            if (res.status === 201) {
                toast.success("Question added successfully!");
            }

        } catch (e) {
            console.log(e)
        }
    }
    // console.log(data)
    return (
        <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6 w-full"}>
            <Toaster richColors={true} position={"top-center"}/>
            <BookInfoBody value={data?.data} isMyBook={bookStatus?.data}/>
            {!bookStatus?.data && (
                <div className={"flex flex-col gap-4"}>
                    <div className={"text-lg md:text-xl font-bold"}>Add a question</div>
                    <form
                        onSubmit={(e) => {
                            e.preventDefault();
                            addQuestion().then();
                        }}
                        className={"flex flex-col gap-2 items-end"}>
                        <LargeTextField placeholder={"Write something..."}
                                        handleChange={(e) => {
                                            e.preventDefault();
                                            setQues(e.target.value);
                                        }}
                        />
                        <Button
                            className={"bg-secondary hover:bg-button-hover text-white"}
                            type={"submit"}
                            content={"Submit"}/>
                    </form>
                </div>
            )}

        </div>
    );
};

export default Page;