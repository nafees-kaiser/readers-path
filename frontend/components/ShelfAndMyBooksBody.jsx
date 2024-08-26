'use client'
import React from 'react';
import BookCard from "@/components/BookCard";
import {useRouter} from "next/navigation";
import EmptyComponent from "@/components/EmptyComponent";

const ShelfAndMyBooksBody = ({state = "shelf", heading = "Shelf", value = null}) => {
    const router = useRouter();
    // useEffect(() => {
    //     console.log(value)
    // }, [value]);
    return (
        <div>
            <div className={"flex flex-col px-[15px] md:px-[50px] lg:px-[100px] py-8 gap-6"}>
                <div className={"text-lg md:text-xl font-bold"}>{heading}</div>
                {value && value.length > 0 ? (
                    <div className={"flex flex-col gap-3"}>
                        {value && value.length > 0 &&
                            value.map(v => (
                                <button className={"w-full"}
                                        onClick={() => router.push(`/books/${v?.book.id}`)}>
                                    <BookCard
                                        key={v.id}
                                        state={"shelf"}
                                        value={v.book && v.book}
                                        shelf={v}
                                    />
                                </button>

                            ))
                        }
                    </div>
                ) : (
                    <EmptyComponent
                        content={`${heading} is empty`}/>
                )}

            </div>
        </div>
    );
};

export default ShelfAndMyBooksBody;