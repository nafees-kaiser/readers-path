"use client"
import React from 'react';
import {IoClose} from "react-icons/io5";
import CheckBox from "@/components/CheckBox";
import Button from "@/components/Button";
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";

const CategoryFilter = ({setIsCategoryModalOpen, apply}) => {
    const [categories, setCategories] = React.useState([]);

    const handleCategory = (cat, ins) => {
        if (ins === "add") {
            if (!categories.includes(cat)) {
                setCategories(prevState => [...prevState, cat]);
            }
        } else {
            if (categories.includes(cat)) {
                setCategories(prevState => prevState.filter(c => c !== cat))
            }
        }
    }

    const {data} = useSWR("/category", fetcher)
    return (
        <div
            className={"flex flex-col gap-12"}>
            <div className={"flex flex-col gap-3"}>
                <div className={"flex justify-between items-center w-full"}>
                    <div className={"text-base font-bold"}>Category</div>
                    <button className={"md:hidden"} onClick={(e) => {
                        e.preventDefault()
                        setIsCategoryModalOpen(false)
                    }}>
                        <div className={"text-xl"}>
                            <IoClose/>
                        </div>
                    </button>
                </div>

                <div className={"flex flex-col gap-2"}>
                    {data?.data &&
                        data?.data?.map(cat => (
                            <CheckBox key={cat.id}
                                      onChange={handleCategory}
                                      content={cat.name}/>
                        ))
                    }
                    {/*<CheckBox*/}
                    {/*    content={"Novel"}/>*/}
                    {/*<CheckBox content={"Thriller"}/>*/}
                </div>
            </div>
            <Button
                onClick={(e) => {
                    e.preventDefault();
                    apply(categories)
                    if (setIsCategoryModalOpen) setIsCategoryModalOpen(false)
                    // setIsCategoryModalOpen(false)
                }}
                className={"bg-secondary hover:bg-button-hover text-white"} content={"Apply"}/>
        </div>
    );
};

export default CategoryFilter;