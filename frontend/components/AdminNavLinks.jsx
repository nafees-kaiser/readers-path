import React from 'react';
import NavLink from "@/components/NavLink";


const AdminNavLinks = ({className}) => {
    return (
        <ul className={`${className} text-sm md:text-base`}>
            <NavLink
                address={"/admin"}
                content={"Home"}
            />
            <NavLink
                address={"/admin/add-category"}
                content={"Add category"}
            />
            <NavLink
                address={"/admin/add-book"}
                content={"Add a book"}
            />
        </ul>
    );
};

export default AdminNavLinks;