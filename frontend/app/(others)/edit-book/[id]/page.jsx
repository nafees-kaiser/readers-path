'use client'
import React from 'react';
import BookForm from "@/components/BookForm";
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";

const Page = ({params: {id}}) => {
    const {data} = useSWR(id ? `/books/${id}` : null, fetcher)
    const content = {
        id: data?.data?.id,
        title: data?.data?.title,
        publisher: data?.data?.publisher,
        edition: data?.data?.edition,
        pageCount: data?.data?.pageCount,
        isbn: data?.data?.isbn,
        category: data?.data?.category,
        links: data?.data?.links,
    }

    // useEffect(() => {
    //     console.log("edit-book ", content)
    // });
    return (
        <BookForm
            heading={"Edit Book"}
            content={content}
            isEdit={true}

        />
    );
};

export default Page;