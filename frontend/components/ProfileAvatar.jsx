import React from 'react';
import {FaUser} from "react-icons/fa6";

const ProfileAvatar = ({className}) => {
    return (
        <div className={`${className} bg-light-text w-fit h-fit text-white p-3 rounded-[100px]`}>
            <FaUser/>
        </div>
    );
};

export default ProfileAvatar;