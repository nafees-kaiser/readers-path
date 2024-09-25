import React from 'react';
import AdminNavbar from "@/components/AdminNavbar";

const Layout = ({children}) => {
    return (
        <section>
            <AdminNavbar/>
            {children}
        </section>
    );
};

export default Layout;