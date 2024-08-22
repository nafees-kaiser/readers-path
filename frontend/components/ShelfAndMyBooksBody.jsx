import React from 'react';
import BookCard from "@/components/BookCard";

const ShelfAndMyBooksBody = ({state = "shelf", heading = "Shelf"}) => {
    return (
        <div>
            <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6"}>
                <div className={"text-lg md:text-xl font-bold"}>{heading}</div>
                <div className={"flex flex-col gap-3"}>
                    {[...Array(5)].map((v, i) => <BookCard state={state}/>)}
                    {/*<BookCard state={"shelf"}/>*/}
                </div>
            </div>
        </div>
    );
};

export default ShelfAndMyBooksBody;