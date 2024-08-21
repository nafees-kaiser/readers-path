import React from 'react';
import CustomRating from "@/components/CustomRating";
import ProfileAvatar from "@/components/ProfileAvatar";

const ReviewCard = () => {
    return (
        <div className={"flex-col flex gap-2 p-2 border border-border rounded-lg lg:p-4"}>
            <div className={"flex gap-2 items-center"}>
                <ProfileAvatar className={"text-2xl"}/>
                <div className={"flex flex-col gap-1"}>
                    <div className={"font-bold text-sm md:text-base"}>User</div>
                    <div className={"flex gap-1 items-center"}>
                        <div>
                            <CustomRating
                                rating={1}
                                readOnly={true}
                                stop={1}
                            />
                        </div>
                        <div className={"text-light-text text-sm md:text-base"}>4.5</div>
                    </div>
                </div>
            </div>


            <p className={"text-sm md:text-base"}>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean sit
                amet mauris nunc. Nulla nec neque
                vitae justo varius lacinia id non tortor. Donec at enim lacinia tellus sodales tempus. </p>
        </div>
    );
};

export default ReviewCard;