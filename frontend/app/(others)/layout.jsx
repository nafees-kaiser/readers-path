import React from 'react';
import UserNavbar from "@/components/UserNavbar";

const MainLayout = ({children}) => {
    return (
        <section>
            <UserNavbar isLoggedIn={true}/>
            {children}
        </section>
    );
};

export default MainLayout;