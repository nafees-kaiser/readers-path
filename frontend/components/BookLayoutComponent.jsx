"use client"
import React, {useEffect, useState} from 'react';
import TextField from "@/components/TextField";
import {FiSearch} from "react-icons/fi";
import SortModal from "@/components/SortModal";
import CategoryFilter from "@/components/CategoryFilter";
import {MdOutlineFilterList} from "react-icons/md";
import {BiSortAlt2} from "react-icons/bi";
import {FilterProvider} from "@/utils/FilterDataContext";

const BookLayoutComponent = ({children}) => {
    const [searchText, setSearchText] = useState("");

    const [filterData, setFilterData] = useState({
        search: '',
        category: [],
        sortBy: ''
    });

    const [isCategoryModalOpen, setIsCategoryModalOpen] = useState(false);
    const [isSortModalOpen, setIsSortModalOpen] = useState(false);

    useEffect(() => {
        if (isCategoryModalOpen) {
            document.body.classList.add('overflow-hidden');
            document.body.classList.add('md:overflow-visible');
        } else {
            document.body.classList.remove('overflow-hidden');
            document.body.classList.remove('md:overflow-visible');
        }

        return () => {
            document.body.classList.remove('overflow-hidden');
            document.body.classList.remove('md:overflow-visible');
        };
    }, [isCategoryModalOpen]);


    const handleSearch = (e) => {
        e.preventDefault();
        setFilterData(prevState => ({...prevState, search: searchText}))
    }

    const handleCategory = (cat) => {
        setFilterData(prevState => ({...prevState, category: cat}))
    }

    const handleSortBy = (s) => {
        setFilterData(prevState => ({...prevState, sortBy: s}))
    }
    return (
        <div className={"min-h-screen relative"}>
            <form onSubmit={handleSearch}>
                <div className={"flex gap-2 items-center mt-4 px-[15px] md:px-[70px] lg:px-[170px]"}>
                    <TextField required={false} type={"search"} value={searchText} placeholder={"Search"}
                               handleChange={(e) => {
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
                <div onClick={() => setIsSortModalOpen(false)}
                     className={`${isSortModalOpen ? 'block' : 'hidden'} absolute top-0 w-full h-full`}>
                    <div onClick={(e) => e.stopPropagation()}
                         className={"absolute top-28 right-[15px] md:right-[50px] lg:right-[100px]"}>
                        <SortModal
                            handleSortBy={handleSortBy}
                            setIsSortModalOpen={setIsSortModalOpen}/>
                    </div>
                </div>
                <div
                    className={`${isCategoryModalOpen ? 'block' : 'hidden'} md:block md:static md:w-1/4
                     absolute -top-4 left-0 w-full h-screen bg-gray-400/40 md:bg-transparent`}>
                    <div
                        className={"md:w-full md:pl-[50px] lg:pl-[100px] md:static" +
                            " w-1/2 py-4 px-4" +
                            " sm:px-10 bg-white " +
                            "absolute h-full " +
                            "shadow-gray-700/40 shadow-lg md:shadow-none"}>
                        <CategoryFilter
                            setIsCategoryModalOpen={setIsCategoryModalOpen}
                            // handleCategory={handleCategory}
                            apply={handleCategory}
                        />
                    </div>
                </div>
                <div
                    className={"flex flex-col items-center justify-start gap-3 bg-secondary-bg min-h-screen w-full px-[15px] pt-4 pb-10 rounded-t-xl md:rounded-tr-none md:pl-6 lg:pl-8 md:pr-[50px] lg:pr-[100px]"}>
                    <div className={"md:justify-end flex justify-between items-center w-full text-sm font-bold"}>
                        <button className={"md:hidden"}
                                onClick={(e) => {
                                    e.preventDefault();
                                    setIsCategoryModalOpen(!isCategoryModalOpen)
                                }}
                        >
                            <div className={"flex items-center gap-1"}>
                                <div>Filter</div>
                                <div className={"text-base"}><MdOutlineFilterList/></div>
                            </div>
                        </button>
                        <button onClick={() => setIsSortModalOpen(!isSortModalOpen)}>
                            <div className={"flex items-center gap-1"}>
                                <div>Sort</div>
                                <div className={"text-base"}><BiSortAlt2/></div>
                            </div>
                        </button>
                    </div>
                    <FilterProvider v={filterData}>
                        {children}
                    </FilterProvider>
                </div>

            </div>
        </div>
    );
};

export default BookLayoutComponent;