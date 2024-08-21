"use client"
import React from 'react';
import {IoClose} from "react-icons/io5";
import CheckBox from "@/components/CheckBox";
import Button from "@/components/Button";

const CategoryFilter = () => {
    return (
        <div
            className={"flex flex-col gap-12"}>
            <div className={"flex flex-col gap-3"}>
                <div className={"flex justify-between items-center w-full"}>
                    <div className={"text-base font-bold"}>Category</div>
                    <button className={"md:hidden"} onClick={() => setIsCategoryModalOpen(false)}>
                        <div className={"text-xl"}>
                            <IoClose/>
                        </div>
                    </button>
                </div>

                <div className={"flex flex-col gap-2"}>
                    <CheckBox
                        content={"Novel"}/>
                    <CheckBox content={"Thriller"}/>
                </div>
            </div>
            <Button className={"bg-secondary text-white w-fit"} content={"Apply"}/>
        </div>
    );
};

export default CategoryFilter;