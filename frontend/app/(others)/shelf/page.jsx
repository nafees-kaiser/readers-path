"use client"
import React from 'react';
import ShelfAndMyBooksBody from "@/components/ShelfAndMyBooksBody";
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";
import {Toaster} from "sonner";

const Page = () => {
    const {data} = useSWR('/user/shelf', fetcher)

    return (
        <>
            <Toaster position={"top-center"} richColors={true}/>
            <ShelfAndMyBooksBody value={data?.data}/>


        </>
    );
};

export default Page;