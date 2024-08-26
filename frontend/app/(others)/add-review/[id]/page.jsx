"use client"
import React, {useState} from 'react';
import BookInfoCard from "@/components/BookInfoCard";
import LargeTextField from "@/components/LargeTextField";
import Button from "@/components/Button";
import CustomRating from "@/components/CustomRating";
import TextField from "@/components/TextField";
import useSWR from "swr";
import {fetcher, postFetcher} from "@/utils/fetcher";
import useSWRMutation from "swr/mutation";
import {useRouter} from "next/navigation";
import {toast} from "sonner";

const Page = ({params: {id}}) => {
    const [rating, setRating] = useState("");
    const [review, setReview] = useState("");

    const {data} = useSWR(`/books/${id}`, fetcher)
    const {trigger} = useSWRMutation(['/user/add-review'], postFetcher)
    const router = useRouter();

    const addReview = async () => {
        try {
            const res = await trigger({
                rating: rating,
                review: review,
                book: {
                    id: id
                }
            })
            console.log(res)
            if (res.status === 201) {
                toast.success("Review added successfully!");
                router.push("/shelf")
            }

        } catch (e) {
            console.log(e)
        }
    }
    return (
        <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6"}>
            <div className={"text-lg md:text-xl font-bold"}>Add review</div>
            <BookInfoCard
                value={data?.data}
                isMyBook={false}
            />
            <form
                onSubmit={(e) => {
                    e.preventDefault();
                    addReview().then()
                }}>
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
                        <LargeTextField
                            placeholder={"Write something..."}
                            value={review}
                            handleChange={(e) => {
                                e.preventDefault()
                                setReview(e.target.value)
                            }}
                        />
                        <Button
                            type={"submit"}
                            className={"bg-secondary hover:bg-button-hover text-white"}
                            content={"Submit"}/>
                    </div>
                </div>
            </form>

        </div>
    );
};

export default Page;