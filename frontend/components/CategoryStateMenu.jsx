'use client'
import React, {useState} from 'react';
import Option from "@/components/Option";
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";
import CategoryMenuItem from "@/components/CategoryMenuItem";

const CategoryStateMenu = ({onChange}) => {
    const {data} = useSWR('/category', fetcher)
    const categories = data?.data
    // console.log(categories)

    const [title, setTitle] = useState("Category");

    const handleClick = (e, t) => {
        e.preventDefault();
        setTitle(t);
        onChange("category", {name: t})
    }
    return (
        <Option
            className={"shadow-sm ring-1 ring-inset ring-gray-300  hover:bg-gray-50"}
            menuItems={
                <div>
                    {
                        categories &&
                        categories.map((cat) => (
                            <CategoryMenuItem
                                content={
                                    <div className={"flex gap-1.5 items-center"}
                                         onClick={(e) => handleClick(e, cat?.name)}>
                                        {cat?.name}
                                    </div>
                                }
                            />
                        ))
                    }
                </div>
            }
            title={title}
        />
    );
};

export default CategoryStateMenu;