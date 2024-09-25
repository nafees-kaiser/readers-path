"use client"
import React from 'react';
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";
import BookCard from "@/components/BookCard";

const Page = () => {
    const {data} = useSWR("/admin/books", fetcher)
    const books = data?.data;
    return (
        <div className={"flex flex-col items-center px-[15px] sm:px-[50px] md:px-[100px] lg:px-[200px] py-8 gap-6"}>
            <h1 className={"text-xl md:text:2xl font-bold"}>All books</h1>
            <div className={"flex flex-col items-center gap-4"}>
                {
                    books?.map((book) => (
                        <BookCard value={book} key={book.id} state={'admin'}/>
                    ))
                }
            </div>
        </div>
    );
};

export default Page;