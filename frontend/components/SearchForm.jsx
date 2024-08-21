"use client"
import React, {useState} from 'react';
import TextField from "@/components/TextField";
import {FiSearch} from "react-icons/fi";
import {usePathname, useRouter} from "next/navigation";

const SearchForm = () => {
    const [searchText, setSearchText] = useState("");
    const router = useRouter();
    const path = usePathname();
    const handleSearch = (e) => {
        e.preventDefault();
        router.push(`${path}?search=${searchText}`);
    }
    return (
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
    );
};

export default SearchForm;