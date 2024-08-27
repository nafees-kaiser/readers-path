'use client'
import React, {useState} from 'react';
import Option from "@/components/Option";
import BookStateMenuItem from "@/components/BookStateMenuItem";
import {FaCircle, FaMinusCircle} from "react-icons/fa";
import {FaCircleCheck} from "react-icons/fa6";
import useSWRMutation from "swr/mutation";
import {postFetcher, putFetcher} from "@/utils/fetcher";
import {useSWRConfig} from "swr";
import {toast} from "sonner";

const BookStateMenu = ({value}) => {
    let state = value?.state?.replace(/_/g, ' ');
    state = state?.charAt(0).toUpperCase() + state.substring(1).toLowerCase();

    const {mutate} = useSWRConfig();
    const {trigger} = useSWRMutation(['/user/shelf/change-state'], putFetcher)
    const {trigger: addReward} = useSWRMutation(['/user/reward/complete-book'], postFetcher)

    // useEffect(() => {
    //     console.log(state)
    // }, []);
    const [title, setTitle] = useState(state ?
        state
        : 'Wish to read');
    const changeTitle = async (e, t) => {
        e.preventDefault();
        setTitle(t);
        try {
            const shelfState = t.replace(/ /g, '_').toUpperCase()
            // console.log(shelfState)

            const res = await trigger({
                id: value?.id,
                state: shelfState
            })
            if (res.status === 200) {
                toast.success("State changed to " + t)
                mutate("/user/shelf")
            }
        } catch (e) {
            console.log(e)
        }

        if (t === "Finished") {
            try {
                const res = await addReward({id: value?.book?.id})
                if (res.status === 201) {
                    toast.success("Congratulations! you have completed reading" + value?.book?.title)
                }
            } catch (e) {
                console.log(e)
            }
            console.log(value?.book)
        }
        // toast.success("State changed to " + t)
    }


    return (
        <Option
            menuItems={
                <div>
                    <BookStateMenuItem content={<div className={'flex gap-1.5 items-center'}
                                                     onClick={(e) => {
                                                         changeTitle(e, "Wish to read").then()
                                                     }}>
                        <FaCircle className={'text-secondary'}/>
                        Wish to read
                    </div>}/>
                    <BookStateMenuItem content={<div className={'flex gap-1.5 items-center'}
                                                     onClick={(e) => {
                                                         changeTitle(e, "On progress").then()
                                                     }}>
                        <FaMinusCircle className={'text-gray-800'}/>
                        On progress
                    </div>}/>
                    <BookStateMenuItem content={<div className={'flex gap-1.5 items-center'}
                                                     onClick={(e) => {
                                                         changeTitle(e, "Finished").then()
                                                     }}>
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