"use client"
import React from 'react';
import BookCard from "@/components/BookCard";

const Page = () => {
    return (
        <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6"}>
            <div className={"text-lg md:text-xl font-bold"}>Shelf</div>
            <div className={"flex flex-col gap-3"}>
                {[...Array(5)].map((v, i) => <BookCard state={"shelf"}/>)}
                {/*<BookCard state={"shelf"}/>*/}
            </div>
        </div>
    );
};

export default Page;