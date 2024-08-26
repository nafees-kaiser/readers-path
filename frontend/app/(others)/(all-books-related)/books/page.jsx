"use client"
import React, {useEffect} from 'react';
import useSWR from "swr";
import {bookFetcher} from "@/utils/fetcher";
import BookCard from "@/components/BookCard";
import Link from "next/link";
import {useFilter} from "@/utils/FilterDataContext";

const BooksPage = () => {

    const filter = useFilter()
    useEffect(() => {
        console.log("inside page, ", filter)
    });

    const {data, isLoading, mutate} = useSWR(["/books/all", filter], bookFetcher);
    let books = data?.data;

    const update = async () => {
        await mutate(data);
    }

    useEffect(() => {
        update().then()
    }, [filter]);

    return (
        <div className={"w-full flex flex-col gap-4"}>
            <div className={"text-sm md:text-base font-bold"}>All books</div>
            <div className={"flex flex-col items-center justify-start gap-3 w-full"}>
                {books && Array.isArray(books) && books.length > 0 &&
                    books.map((book, index) => (
                        <Link className={"w-full"} href={`/books/${book.id}`} key={book.id}>
                            <BookCard value={book}/>
                        </Link>
                    ))
                }
            </div>

        </div>
    );
};

export default BooksPage;