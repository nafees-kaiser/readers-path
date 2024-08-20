import React from 'react';
import UserNavbar from "@/components/UserNavbar";

const MainLayout = ({children}) => {
    return (
        <section>
            <UserNavbar/>
            {children}
        </section>
    );
};

export default MainLayout;