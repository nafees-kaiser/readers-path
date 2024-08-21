"use client"
import React from 'react';
import {useSearchParams} from "next/navigation";

const BooksPage = () => {
    const param = useSearchParams();
    const search = param.get("search")
    return (
        <div>
            this is inside book page {search}
        </div>
    );
};

export default BooksPage;