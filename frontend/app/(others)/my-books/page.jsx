'use client'
import React from 'react';
import ShelfAndMyBooksBody from "@/components/ShelfAndMyBooksBody";
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";

const Page = () => {
    const {data} = useSWR('/user/my-books', fetcher)

    return (
        <>
            <ShelfAndMyBooksBody
                state={"myBooks"}
                value={data?.data}
                heading={"My books"}/>
        </>
    );
};

export default Page;