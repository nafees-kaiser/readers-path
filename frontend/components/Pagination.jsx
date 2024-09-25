"use client"

import React, {useEffect} from "react";

import useSWR from "swr";
import {bookFetcher} from "@/utils/fetcher";
import {FaArrowLeft, FaArrowRight} from "react-icons/fa";
// import {reqFetcher} from "@/utils/fetcher";

const Pagination = ({data, pagination, isLoading, error}) => {
    // const url= '/products/filter'
    // const method="POST"
    //
    // const {data:value,error,isLoading} = useSWR([url,method,data],reqFetcher)
    // const {data:paginatedData, isLoading, error, mutate} = useSWR(["/books/all", data], bookFetcher);
    // const value = paginatedData?.data;
    // useEffect(() => {
    //     console.log(value)
    // });

    const value = data;

    return (
        <>
            <div className="w-full h-10  my-4 mx-auto flex justify-center items-center">
                <button className="text-base md:text-lg"
                        onClick={() => {
                            pagination(0)
                        }}>
                    <FaArrowLeft/>
                </button>
                <p className="mx-4 text-base md:text-lg">
                    {!isLoading && !error && value && value.totalPages > 0 ? value.pageable?.pageNumber + 1 : 0} of {!isLoading && !error && value && value.totalPages ? !isLoading && !error && value && value.totalPages : 0}
                </p>
                <button className="text-base md:text-lg" onClick={() => {
                    pagination(!isLoading && !error && value && value.totalPages)
                }}>
                    <FaArrowRight/>
                </button>
            </div>
        </>
    )
}
export default Pagination;
