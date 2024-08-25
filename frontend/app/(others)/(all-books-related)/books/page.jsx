"use client"
import React from 'react';
import {usePathname, useSearchParams} from "next/navigation";
import useSWR from "swr";
import {bookFetcher} from "@/utils/fetcher";
import BookCard from "@/components/BookCard";
import Link from "next/link";

const BooksPage = () => {
    const param = useSearchParams();
    const search = param.get("search");
    const path = usePathname();

    const filter = {
        search,
        category: '',
        sortBy: ''
    }

    const {data, isLoading, error} = useSWR(["/books/all", {}], bookFetcher);
    const books = data?.data;


    // useEffect(() => {
    //     // console.log(books)
    //     // console.log(books)
    //     const resp = axios.post('http://localhost:8080/api/v1/books/all', {}).then(res => res.data)
    //     console.log(resp)
    // }, []);

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