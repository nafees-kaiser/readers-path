import React from 'react';

const Page = ({params: {id}}) => {
    return (
        <div>
            the book id is {id}
        </div>
    );
};

export default Page;