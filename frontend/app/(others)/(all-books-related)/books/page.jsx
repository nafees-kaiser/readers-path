"use client"
import React, {useEffect} from 'react';
import useSWR from "swr";
import {bookFetcher} from "@/utils/fetcher";
import BookCard from "@/components/BookCard";
import Link from "next/link";
import {useFilter} from "@/utils/FilterDataContext";
import Pagination from "@/components/Pagination";

const BooksPage = () => {

    const [filter, setFilterData] = useFilter()
    // useEffect(() => {
    //     console.log("inside page, ", filter)
    // });

    const {data, isLoading, error, mutate} = useSWR(["/books/all", filter], bookFetcher);
    let books = data?.data?.content;

    const update = async () => {
        await mutate(data);
    }

    useEffect(() => {
        update().then()
    }, [filter]);

    const pagination = (value) => {
        const pageNumber = "pageNumber"

        if (value) {
            setFilterData((prevState) => ({
                ...prevState,
                [pageNumber]: (prevState.pageNumber + 1 === value) ? 0 : prevState.pageNumber + 1
            }))
        } else {
            setFilterData((prevState) => ({
                ...prevState,
                [pageNumber]: (prevState.pageNumber === 0) ? 0 : prevState.pageNumber - 1
            }))
        }
        update().then();

    }

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
            <Pagination
                data={data?.data}
                pagination={pagination}
                isLoading={isLoading}
                error={error}
                // url={"/books/all"}
                // toMutate={mutate}
            />

        </div>
    );
};

export default BooksPage;