import React from 'react';
import BookLayoutComponent from "@/components/BookLayoutComponent";

const AllBooksRelatedLayout = ({children}) => {


    return (
        <section>
            <BookLayoutComponent>
                {children}
            </BookLayoutComponent>
        </section>
    );
};

export default AllBooksRelatedLayout;