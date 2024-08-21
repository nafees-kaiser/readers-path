import React from 'react';
import Image from "next/image";
import bookCover from "../public/book-covers.jpg"
import {FaRegTrashAlt, FaStar} from "react-icons/fa";
import ShelfCardElement from "@/components/ShelfCardElement";
import {FiEdit} from "react-icons/fi";

const BookCard = ({state}) => {
    return (
        <div className={"flex flex-col w-full gap-2 border border-border rounded-lg p-2 sm:p-4 md:p-6"}>
            <div className={" flex justify-start items-start gap-4 w-full"}>
                <div className={"w-24 xs:w-28"}>
                    <Image src={bookCover} alt={"Book cover"}/>
                </div>
                <div className={"flex flex-col gap-2"}>
                    <div className={"font-bold text-lg md:text-2xl"}>
                        book title
                    </div>
                    <div className={"text-xs md:text-base flex flex-col gap-1.5"}>
                        <div>author</div>
                        <div>publisher</div>
                    </div>
                    <div className={"text-xs md:text-base text-light-text flex gap-4 md:gap-12"}>
                        <div className={"flex flex-col gap-1.5"}>
                            <div>Category: Novel</div>
                            <div>ISBN: 9847010501131</div>
                        </div>
                        <div className={"flex flex-col gap-1.5"}>
                            <div>edition</div>
                            <div>pages</div>
                        </div>
                    </div>
                    <div className={"text-xs md:text-base text-light-text flex gap-1.5 items-center"}>
                        <div className={"text-secondary md:text-xl"}>
                            <FaStar/>
                        </div>
                        <div>rating (count)</div>
                    </div>

                </div>
            </div>
            {state === 'shelf' && (
                <div className={"border-t border-divider pt-2 mt-2"}>
                    <ShelfCardElement/>
                </div>

            )}
            {state === 'myBooks' && (
                <div className={"border-t border-divider pt-2 mt-2 h-8 relative"}>
                    <div className={"flex items-center justify-start gap-1.5 absolute right-0 bottom-0"}>
                        <button className={'text-xl md:text-2xl text-light-text '}>
                            <FiEdit/>
                        </button>
                        <button className={'text-xl md:text-2xl text-light-text'}>
                            <FaRegTrashAlt/>
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default BookCard;