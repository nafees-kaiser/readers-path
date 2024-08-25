import React from 'react';
import CustomRating from "@/components/CustomRating";
import ProfileAvatar from "@/components/ProfileAvatar";

const ReviewCard = ({value}) => {
    return (
        <div className={"flex-col flex gap-2 p-2 border border-border rounded-lg lg:p-4"}>
            <div className={"flex gap-2 items-center"}>
                <ProfileAvatar className={"text-2xl"}/>
                <div className={"flex flex-col gap-1"}>
                    <div
                        className={"font-bold text-sm md:text-base"}>{value?.appUser?.name ? value?.appUser?.name : "User"}</div>
                    <div className={"flex gap-1 items-center"}>
                        <div>
                            <CustomRating
                                rating={1}
                                readOnly={true}
                                stop={1}
                            />
                        </div>
                        <div
                            className={"text-light-text text-sm md:text-base"}>{value?.rating ? value?.rating : 0}</div>
                    </div>
                </div>
            </div>


            <p className={"text-sm md:text-base"}>{value?.review ? value?.review : "Review"}</p>
        </div>
    );
};

export default ReviewCard;