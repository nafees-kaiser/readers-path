import React from 'react';
import BookCard from "@/components/BookCard";

const BookLayoutBody = () => {
    return (
        <div className={"flex gap-10"}>
            <div className={"hidden md:block md:w-1/4 md:h-full"}>
                abcdgjd
            </div>
            <div
                className={" mt-8 flex flex-col items-center justify-start gap-2 bg-tertiary-bg min-h-full h-fit w-full px-[15px] pt-4 pb-6 rounded-t-xl"}>
                {/*<div className={"flex justify-between items-center w-full text-xs"}>*/}
                {/*    <div>Filter</div>*/}
                {/*    <div>sort by</div>*/}
                {/*</div>*/}
                {/*<div>All books</div>*/}
                <BookCard/>
                <BookCard/>
                <BookCard/>
            </div>

        </div>

    );
};

export default BookLayoutBody;