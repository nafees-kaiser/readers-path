"use client"
import React, {useState} from 'react';
import TextField from "@/components/TextField";
import Button from "@/components/Button";
import useSWR from "swr";
import {fetcher, postFetcher, putFetcher} from "@/utils/fetcher";
import useSWRMutation from "swr/mutation";
import {toast} from "sonner";

const Page = () => {
    const [category, setCategory] = useState("");
    const {data, mutate} = useSWR("/category", fetcher)
    const {trigger} = useSWRMutation(["/admin/add-category"], postFetcher)
    const handleSubmit =async (e) => {
        e.preventDefault();
        const data = {
            "name": category
        }
        try {
            const res = await trigger(data)
            if(res.status === 201 || res.status === 200) {
                toast.success("Category added successfully!");
                await mutate();
                setCategory("");
            }
        }catch (e) {
            console.log(e)
        }
        // console.log(data)
    }
    return (
        <div className={"flex flex-col gap-12 items-center w-full min-h-screen px-[15px] sm:px-[50px] md:px-[100px] lg:px-[200px] py-8"}>
            <form
                onSubmit={handleSubmit}
                className={"flex flex-col gap-6 w-full"}>
                <h1 className={"text-xl md:text:2xl font-bold"}>Add category</h1>
                <div className={"flex flex-row items-center gap-6"}>
                    <TextField
                        name={"name"}
                        placeholder={"eg. Science Fiction"}
                        value={category}
                        handleChange={(e) => {
                            e.preventDefault();
                            setCategory(e.target.value);
                        }}
                    />
                    <Button
                        content={"Add"}
                        type={"submit"}
                        className={"bg-secondary text-white hover:bg-button-hover"}/>
                </div>
            </form>
            <div className={"self-start flex flex-col gap-6"}>
                <h1 className={"text-xl md:text:2xl font-bold"}>Categories</h1>
                <ol className={"flex flex-col gap-4"}>
                    {
                        data?.data && data?.data?.map((cat, i)=>(<li key={i}>
                            {i + 1}. {cat.name}
                        </li>))
                    }
                </ol>
            </div>

        </div>
    );
};

export default Page;