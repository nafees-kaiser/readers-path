import React from 'react';

const EmptyComponent = ({content}) => {
    return (
        <div className={"w-full min-h-screen flex justify-center items-center text-light-text"}>
            {content}
        </div>
    );
};

export default EmptyComponent;