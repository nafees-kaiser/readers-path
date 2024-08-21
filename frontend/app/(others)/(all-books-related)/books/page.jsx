"use client"
import React from 'react';
import {useSearchParams} from "next/navigation";
import BookCard from "@/components/BookCard";

const BooksPage = () => {
    const param = useSearchParams();
    const search = param.get("search")
    return (
        <div>
            <BookCard/>
            <BookCard/>
            <BookCard/>
            <BookCard/>
        </div>
    );
};

export default BooksPage;