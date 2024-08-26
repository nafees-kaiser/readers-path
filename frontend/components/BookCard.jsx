import React from 'react';
import Image from "next/image";
import bookCover from "../public/book-covers.jpg"
import {FaRegTrashAlt, FaStar} from "react-icons/fa";
import ShelfCardElement from "@/components/ShelfCardElement";
import {FiEdit} from "react-icons/fi";
import useSWRMutation from "swr/mutation";
import {deleteFetcher} from "@/utils/fetcher";
import {useSWRConfig} from "swr";

const BookCard = ({state, value, shelf}) => {
    const {mutate} = useSWRConfig()

    const imageEncoded = value?.coverImage?.imageEncoded ? value?.coverImage?.imageEncoded : null;
    const imageType = value?.coverImage?.imageType ? value?.coverImage?.imageType : null;
    const image = `data:${imageType};base64,${imageEncoded}`;


    const {trigger: deleteMyBooks} = useSWRMutation(
        [`/user/my-books/delete/${value?.id}`],
        deleteFetcher
    )

    const handleDelete = async () => {
        if (state === 'myBooks') {
            try {
                const res = await deleteMyBooks();
                mutate('/my-books', {})
            } catch (e) {
                console.log(e)
            }
        }
    }

    return (
        <div
            className={"hover:bg-tertiary-bg flex flex-col w-full gap-2 border border-border rounded-lg p-2 sm:p-4 md:p-6 text-start"}>
            <div className={" flex justify-start items-start gap-4 w-full"}>
                <div className={"w-24 xs:w-28"}>
                    <Image src={value?.coverImage ? image : bookCover} alt={"Book cover"}
                           layout="responsive"
                           width={400}
                           height={300}
                    />
                </div>
                <div className={"flex flex-col gap-2"}>
                    <div className={"font-bold text-lg md:text-2xl"}>
                        {value?.title ? value?.title : "Book title"}
                    </div>
                    <div className={"text-xs md:text-base flex flex-col gap-1.5"}>
                        <div>{value?.author?.name ? value?.author?.name : "Author"}</div>
                        <div>{value?.publisher ? value?.publisher : "Publisher"}</div>
                    </div>
                    <div className={"text-xs md:text-base text-light-text flex gap-4 md:gap-12"}>
                        <div className={"flex flex-col gap-1.5"}>
                            <div>Category: {value?.category?.name ? value?.category?.name : "category"}</div>
                            <div>ISBN: {value?.isbn ? value?.isbn : ""}</div>
                        </div>
                        <div className={"flex flex-col gap-1.5"}>
                            <div>{value?.edition ? value?.edition : "edition"}</div>
                            <div>{value?.pageCount ? value?.pageCount : "0"} pages</div>
                        </div>
                    </div>
                    <div className={"text-xs md:text-base text-light-text flex gap-1.5 items-center"}>
                        <div className={"text-secondary md:text-xl"}>
                            <FaStar/>
                        </div>
                        <div>{value?.overAllRating ? value?.overAllRating : ""} ({
                            value?.reviewsAndRating ? value?.reviewsAndRating.length : "0"
                        })
                        </div>
                    </div>

                </div>
            </div>
            {state === 'shelf' && (
                <div className={"border-t border-divider pt-2 mt-2"}
                     onClick={(e) => e.stopPropagation()}>
                    <ShelfCardElement value={shelf}/>
                </div>

            )}
            {state === 'myBooks' && (
                <div className={"border-t border-divider pt-2 mt-2 h-8 relative"}>
                    <div className={"flex items-center justify-start gap-1.5 absolute right-0 bottom-0"}>
                        <button className={'text-xl md:text-2xl text-light-text '}>
                            <FiEdit/>
                        </button>
                        <button
                            onClick={() => handleDelete().then()}
                            className={'text-xl md:text-2xl text-light-text'}>
                            <FaRegTrashAlt/>
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default BookCard;