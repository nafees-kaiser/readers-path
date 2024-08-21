import React from 'react';
import Image from "next/image";
import bookCover from "@/public/book-covers.jpg";
import {FaStar} from "react-icons/fa";
import ViewBookLink from "@/components/ViewBookLink";
import {PiBooksBold} from "react-icons/pi";

const BookInfoCard = () => {
    return (
        <div className={"flex flex-col w-full gap-2 border border-border rounded-lg p-4 sm:p-8"}>
            <div
                className={"flex flex-col sm:flex-row sm:items-start justify-start items-center gap-4 sm:gap-8 w-full"}>
                <div className={"w-32 xs:w-44 sm:w-32 md:w-44"}>
                    <Image src={bookCover} alt={"Book cover"}/>
                </div>
                <div className={"flex flex-col items-center sm:items-start gap-2"}>
                    <div className={"font-bold text-lg md:text-2xl"}>
                        book title
                    </div>
                    <div className={"text-xs md:text-base items-center sm:items-start flex flex-col gap-1.5"}>
                        <div>author</div>
                        <div>publisher</div>
                    </div>
                    <div className={"text-xs md:text-base text-light-text flex gap-14"}>
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
                        <div className={"text-secondary text-sm md:text-xl"}>
                            <FaStar/>
                        </div>
                        <div>rating (count)</div>
                    </div>
                    <button
                        className={"flex items-center gap-1.5 text-xs md:text-base cursor-pointer"}>
                        <div className={"text-lg md:text-xl"}><PiBooksBold/></div>
                        <div>Add to shelf</div>
                    </button>

                </div>
            </div>
            <div className={"border-t border-divider pt-3 mt-2"}>
                <div className={"flex items-center gap-1.5 flex-wrap"}>
                    <ViewBookLink
                        content={"Rokomari"}/>
                    <ViewBookLink
                        content={"Amazon"}/>
                    <ViewBookLink
                        content={"abc"}/>
                </div>
            </div>
        </div>
    );
};

export default BookInfoCard;