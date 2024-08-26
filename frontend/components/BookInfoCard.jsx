"use client"
import React, {useEffect, useState} from 'react';
import Image from "next/image";
import bookCover from "@/public/book-covers.jpg";
import {FaStar} from "react-icons/fa";
import ViewBookLink from "@/components/ViewBookLink";
import {PiBooksBold} from "react-icons/pi";
import useSWRMutation from "swr/mutation";
import {deleteFetcher, fetcher, postFetcher} from "@/utils/fetcher";
import useSWR from "swr";

const BookInfoCard = ({value}) => {
    const {data: shelfStatus} = useSWR(value?.id ? `/user/shelf/book-exists/${value?.id}` : null, fetcher)

    const [isShelf, setIsShelf] = useState(false);
    useEffect(() => {
        setIsShelf(shelfStatus?.data)
    }, [shelfStatus]);


    const {trigger: addToShelf} = useSWRMutation(
        ['/user/shelf/add', {}],
        postFetcher)

    const {trigger: removeFromShelf} = useSWRMutation(
        [`/user/shelf/delete/${value?.id}`, {}],
        deleteFetcher)

    const handleShelf = async () => {
        try {
            let res;
            if (isShelf) {
                res = await removeFromShelf();
            } else {
                res = await addToShelf({
                    book: {id: value?.id},
                });
            }
            // console.log(res);
            if (res.status === 201 || res.status === 204) {
                setIsShelf(!isShelf);
            }
        } catch (e) {
            console.error(e);
        }
    }

    const imageEncoded = value?.coverImage?.imageEncoded ? value?.coverImage?.imageEncoded : null;
    const imageType = value?.coverImage?.imageType ? value?.coverImage?.imageType : null;
    const image = `data:${imageType};base64,${imageEncoded}`;

    return (
        <div className={"flex flex-col w-full gap-2 border border-border rounded-lg p-4 sm:p-8"}>
            <div
                className={"flex flex-col sm:flex-row sm:items-start justify-start items-center gap-4 sm:gap-8 w-full"}>
                <div className={"w-32 xs:w-44 sm:w-32 md:w-44"}>
                    <Image src={value?.coverImage ? image : bookCover} alt={"Book cover"}
                           layout="responsive"
                           width={400}
                           height={300 * 3 / 4}
                    />
                </div>
                <div className={"flex flex-col items-center sm:items-start gap-2"}>
                    <div className={"font-bold text-lg md:text-2xl"}>
                        {value?.title ? value?.title : "Book title"}
                    </div>
                    <div className={"text-xs md:text-base items-center sm:items-start flex flex-col gap-1.5"}>
                        <div>{value?.author?.name ? value?.author?.name : "Author"}</div>
                        <div>{value?.publisher ? value?.publisher : "Publisher"}</div>
                    </div>
                    <div className={"text-xs md:text-base text-light-text flex gap-14"}>
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
                        <div className={"text-secondary text-sm md:text-xl"}>
                            <FaStar/>
                        </div>
                        <div>{value?.overAllRating ? value?.overAllRating : "0"} ({
                            value?.reviewsAndRating ? value?.reviewsAndRating.length : "0"
                        })
                        </div>
                    </div>
                    <button
                        onClick={() => handleShelf().then()}
                        className={`${isShelf ? 'text-secondary' : 'text-black'} flex items-center gap-1.5 text-xs md:text-base cursor-pointer`}>
                        <div className={"text-lg md:text-xl"}><PiBooksBold/></div>
                        <div>{isShelf ? 'Added' : 'Add'} to shelf</div>
                    </button>

                </div>
            </div>
            <div className={"border-t border-divider pt-3 mt-2"}>
                <div className={"flex items-center gap-1.5 flex-wrap"}>
                    {
                        value?.links && value?.links.length > 0 &&
                        value?.links.map((link, index) => (
                            <ViewBookLink key={link?.id}
                                          address={link?.link}
                                          content={link?.name}/>
                        ))
                    }
                    {/*<ViewBookLink*/}
                    {/*    content={"Rokomari"}/>*/}
                    {/*<ViewBookLink*/}
                    {/*    content={"Amazon"}/>*/}
                    {/*<ViewBookLink*/}
                    {/*    content={"abc"}/>*/}
                </div>
            </div>
        </div>
    );
};

export default BookInfoCard;