import React from 'react';
import BookInfoBody from "@/components/BookInfoBody";

const Page = ({params: {id}}) => {
    return (
        <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6"}>
            <BookInfoBody isAuthor={true}/>
        </div>
    );
};

export default Page;