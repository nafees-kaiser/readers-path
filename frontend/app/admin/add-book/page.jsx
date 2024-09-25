"use client"
import React, {useEffect} from 'react';
import BookForm from "@/components/BookForm";

const Page = () => {
    useEffect(() => {
        console.log("hello")
    });
    return (
        <div>
            <BookForm
                isUser={false}
                heading={"Add Book"}
                path={"/admin"}
                />
        </div>
    );
};

export default Page;