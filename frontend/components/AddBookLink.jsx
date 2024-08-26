import React from 'react';
import {IoClose} from "react-icons/io5";

const AddBookLink = ({content, onClick}) => {
    return (
        <div
            className={"border-2 text-secondary font-bold border-secondary w-fit text-xs md:text-base rounded-xl md:rounded-2xl px-2 py-1"}>
            <div className={"flex gap-1 items-center"}>
                {content}
                <button
                    className={"text-sm md:text-lg"}
                    onClick={onClick}>
                    <IoClose/>
                </button>
            </div>
        </div>
    );
};

export default AddBookLink;