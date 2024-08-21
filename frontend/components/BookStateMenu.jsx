'use client'
import React, {useState} from 'react';
import Option from "@/components/Option";
import BookStateMenuItem from "@/components/BookStateMenuItem";
import {FaCircle, FaMinusCircle} from "react-icons/fa";
import {FaCircleCheck} from "react-icons/fa6";

const BookStateMenu = () => {
    const [title, setTitle] = useState("Wish to read");
    const changeTitle = (e, title) => {
        e.preventDefault();
        setTitle(title);
    }
    return (
        <Option
            menuItems={
                <div>
                    <BookStateMenuItem content={<div className={'flex gap-1.5 items-center'}
                                                     onClick={(e) => changeTitle(e, "Wish to read")}>
                        <FaCircle className={'text-secondary'}/>
                        Wish to read
                    </div>}/>
                    <BookStateMenuItem content={<div className={'flex gap-1.5 items-center'}
                                                     onClick={(e) => changeTitle(e, "In progress")}>
                        <FaMinusCircle className={'text-gray-800'}/>
                        In progress
                    </div>}/>
                    <BookStateMenuItem content={<div className={'flex gap-1.5 items-center'}
                                                     onClick={(e) => changeTitle(e, "Finished")}>
                        <FaCircleCheck className={'text-primary'}/>
                        Finished
                    </div>}/>
                </div>
            }
            title={title}
        />
    );
};

export default BookStateMenu;