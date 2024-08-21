"use client"
import React, {useState} from 'react';
import TextField from "@/components/TextField";
import {FiSearch} from "react-icons/fi";
import {usePathname, useRouter} from "next/navigation";
import BookCard from "@/components/BookCard";
import {MdOutlineFilterList} from "react-icons/md";
import {BiSortAlt2} from "react-icons/bi";
import CategoryFilter from "@/components/CategoryFilter";

const AllBooksRelatedLayout = ({children}) => {
    const [searchText, setSearchText] = useState("");
    const [isCategoryModalOpen, setIsCategoryModalOpen] = useState(false);
    const router = useRouter();
    const path = usePathname();
    const handleSearch = (e) => {
        e.preventDefault();
        router.push(`${path}?search=${searchText}`);
    }
    return (
        <section>
            <form onSubmit={handleSearch}>
                <div className={"flex gap-2 items-center mt-4 px-[15px] md:px-[70px] lg:px-[170px]"}>
                    <TextField value={searchText} placeholder={"Search"} handleChange={(e) => {
                        e.preventDefault();
                        setSearchText(e.target.value)
                    }}/>
                    <button type={"submit"}>
                        <div className={"text-xl lg:text-2xl"}>
                            <FiSearch/>
                        </div>
                    </button>
                </div>
            </form>
            <div className={"flex gap-10 mt-8"}>
                <div className={"hidden md:block md:w-1/4 md:h-full md:pl-[50px] lg:pl-[100px]"}>
                    <CategoryFilter/>
                </div>
                <div
                    className={"flex flex-col items-center justify-start gap-2 bg-secondary-bg min-h-full h-fit w-full px-[15px] pt-4 pb-10 rounded-t-xl md:rounded-tr-none md:pl-6 lg:pl-8 md:pr-[50px] lg:pr-[100px]"}>
                    <div className={"md:justify-end flex justify-between items-center w-full text-sm font-bold"}>
                        <button className={"md:hidden"}
                                onClick={() => setIsCategoryModalOpen(!isCategoryModalOpen)}
                        >
                            <div className={"flex items-center gap-1"}>
                                <div>Filter</div>
                                <div className={"text-base"}><MdOutlineFilterList/></div>
                            </div>
                        </button>
                        <button>
                            <div className={"flex items-center gap-1"}>
                                <div>Sort</div>
                                <div className={"text-base"}><BiSortAlt2/></div>
                            </div>
                        </button>
                    </div>
                    {/*<div>All books</div>*/}
                    {isCategoryModalOpen && (
                        <div
                            className={"md:hidden w-1/2 py-4 px-4 sm:px-10 bg-white absolute left-0 top-52 h-full shadow-gray-700/40 shadow-lg"}>
                            <CategoryFilter/>
                        </div>
                    )}
                    <BookCard/>
                    <BookCard/>
                    <BookCard/>
                </div>

            </div>
        </section>
    );
};

export default AllBooksRelatedLayout;