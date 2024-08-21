import React from 'react';
import Link from "next/link";

const ProfileModalLink = ({content, address}) => {
    return (
        <Link className={"text-xs md:text-sm font-bold w-full hover:bg-light-text px-2"} href={address}>
            {content}
        </Link>
    );
};

export default ProfileModalLink;