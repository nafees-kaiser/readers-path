import React from 'react';
import BookStateMenu from "@/components/BookStateMenu";
import Button from "@/components/Button";
import {FaRegTrashAlt} from "react-icons/fa";

const ShelfCardElement = () => {
    return (
        <div className={"flex relative"}>
            <div className={"flex gap-2 sm:gap-8 md:gap-12 items-center"}>
                <BookStateMenu/>
                <Button
                    content={"Add review"}
                    className={"bg-secondary hover:bg-button-hover text-white"}
                />
            </div>
            <button className={'text-xl md:text-2xl text-light-text absolute right-0 bottom-0'}>
                <FaRegTrashAlt/>
            </button>
        </div>
    );
};

export default ShelfCardElement;