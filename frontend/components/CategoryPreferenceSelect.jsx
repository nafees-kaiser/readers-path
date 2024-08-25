"use client"
import React, {useState} from 'react';
import PreferenceBox from "@/components/PreferenceBox";
import Button from "@/components/Button";
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";

const CategoryPreferenceSelect = ({onSubmit, isCategory, buttonText, onChange, selected = []}) => {
    const url = isCategory ? '/category' : '/author';

    const {data, isLoading} = useSWR(url, fetcher);
    const items = data?.data;

    const [selectedItems, setSelectedItems] = useState(selected)
    const handleSelectedItems = (item, ins) => {
        if (ins === "add") {
            if (!selectedItems.some(i => i.name === item.name)) {
                const items = [...selectedItems, item];
                // console.log("includes, ", items)
                setSelectedItems(items)
                onChange(items)
            }
        } else if (ins === "remove") {
            if (selectedItems.some(i => i.name === item.name)) {
                let items = selectedItems;
                items = items.filter(i => i.name !== item.name)
                // console.log("excludes, ", items)
                setSelectedItems(items)
                onChange(items)
            }
        }
    }
    return (
        <form onSubmit={(e) => {
            e.preventDefault();
            onSubmit()
        }}
              className={"w-full min-h-screen flex flex-col justify-center items-center px-[15px]"}>
            <div className={"flex flex-col gap-8 my-6"}>
                <div className={"text-lg md:text-xl font-bold"}>Select one or
                    more {isCategory ? 'categories' : 'authors'}</div>
                <div className={"flex flex-col items-end gap-4 w-fit"}>
                    <div className={"grid grid-cols-3 items-stretch sm:grid-cols-4 md:grid-cols-5 gap-4 w-fit"}>
                        {Array.isArray(items) && items.map((v, index) => {
                            return <div key={index}>
                                <PreferenceBox
                                    content={v.name}
                                    onChange={handleSelectedItems}/>
                            </div>
                        })}

                    </div>
                    <Button type={"submit"} content={buttonText}
                            className={"bg-secondary hover:bg-button-hover text-white"}/>
                </div>
            </div>
        </form>
    );
};

export default CategoryPreferenceSelect;