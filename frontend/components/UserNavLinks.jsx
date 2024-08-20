import React from 'react';
import NavLink from "@/components/NavLink";


const UserNavLinks = ({className}) => {
    return (
        <ul className={`${className} text-sm md:text-base`}>
            <NavLink
                address={"/"}
                content={"Home"}
            />
            <NavLink
                address={"/books"}
                content={"All books"}
            />
            <NavLink
                address={"/recommended-books"}
                content={"Recommended"}
            />
            <NavLink
                address={"#about-us"}
                content={"About us"}
            />
        </ul>
    );
};

export default UserNavLinks;