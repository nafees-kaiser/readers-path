import React from 'react';
import BookStateMenu from "@/components/BookStateMenu";
import Button from "@/components/Button";
import {FaRegTrashAlt} from "react-icons/fa";
import useSWR, {useSWRConfig} from "swr";
import {deleteFetcher, fetcher} from "@/utils/fetcher";
import useSWRMutation from "swr/mutation";

const ShelfCardElement = ({value}) => {
    const {mutate} = useSWRConfig();

    const {data: userData} = useSWR('/user', fetcher);
    const user = userData?.data;

    const {trigger: removeFromShelf} = useSWRMutation(
        [`/user/shelf/delete/${value?.book?.id}`, {}],
        deleteFetcher)

    const handleShelf = async () => {
        try {
            const res = await removeFromShelf()
            mutate('/user/shelf')
            console.log(res)
        } catch (e) {
            console.error(e);
        }
    }

    const alreadyReviewed = () => {
        const reviewed = value?.book?.reviewsAndRating
        // console.log("user ", user?.email);

        return reviewed?.filter(r => r.appUser?.email === user?.email).length > 0
    }
    return (
        <div className={"flex relative"}>
            <div className={"flex gap-2 sm:gap-8 md:gap-12 items-center"}>
                <BookStateMenu value={value}/>
                {value?.state && value?.state === "FINISHED" && !alreadyReviewed() &&
                    (
                        <Button
                            content={"Add review"}
                            className={"bg-secondary hover:bg-button-hover text-white"}
                        />
                    )
                }
                {alreadyReviewed() && (
                    <div
                        className={"bg-gray-300 text-gray-700 w-fit text-xs xs:text-sm md:text-base rounded-lg px-4 py-2 font-bold"}>
                        Already reviewed
                    </div>
                )}

            </div>
            <button className={'text-xl md:text-2xl text-light-text absolute right-0 bottom-0'}
                    onClick={() => handleShelf().then()}>
                <FaRegTrashAlt/>
            </button>
        </div>
    );
};

export default ShelfCardElement;