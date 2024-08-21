import React from 'react';
import CustomMenuItem from "@/components/CustomMenuItem";

const BookStateMenuItem = ({content, onClick}) => {
    return (
        <CustomMenuItem
            content={
                <button
                    className="w-full px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                    onClick={onClick}
                >
                    {content}
                </button>
            }
        />
    );
};

export default BookStateMenuItem;