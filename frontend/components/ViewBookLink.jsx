import React from 'react';
import Link from "next/link";
import {CgArrowTopRight} from "react-icons/cg";

const ViewBookLink = ({content}) => {
    return (
        <div
            className={"border-2 text-secondary font-bold border-secondary w-fit text-xs md:text-base rounded-xl md:rounded-2xl px-2 py-1"}>
            <Link href={"#"}>
                <div className={"flex gap-1 items-center"}>
                    {content}
                    <div className={"text-sm md:text-lg"}>
                        <CgArrowTopRight/>
                    </div>
                </div>

            </Link>


        </div>
    );
};

export default ViewBookLink;