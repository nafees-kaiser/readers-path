import React from 'react';

const SortModal = ({setIsSortModalOpen, handleSortBy}) => {
    const handleClick = (e, s) => {
        e.preventDefault();
        handleSortBy(s);
        setIsSortModalOpen(false);
    }
    return (
        <div className={"w-fit flex flex-col gap-1 bg-white rounded-lg  shadow-lg shadow-gray-700/30 py-2"}>
            <button
                onClick={(e) => handleClick(e, "a-z")}
                className={"w-full hover:bg-light-text px-4 py-1 text-sm md:text-base"}>
                A-Z
            </button>
            <button
                onClick={(e) => handleClick(e, "rating")}
                className={"w-full hover:bg-light-text px-4 py-1 text-sm md:text-base"}>
                Rating
            </button>
        </div>
    );
};

export default SortModal;