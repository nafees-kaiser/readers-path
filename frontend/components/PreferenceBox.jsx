"use client"
import React, {useState} from 'react';

const PreferenceBox = ({content, onChange}) => {
    const [isSelected, setIsSelected] = useState(false);
    return (
        <div
            onClick={() => {
                const currentlySelected = !isSelected;
                setIsSelected(currentlySelected);
                currentlySelected ? onChange({name: content}, "add") : onChange({name: content}, "remove");
            }}
            className={`${isSelected ? 'border-2 border-secondary' : ''} bg-tertiary-bg p-6 rounded-lg w-24 h-24 overflow-visible text-wrap text-xs md:text-sm md:w-28 md:h-28 lg:w-32 lg:h-32 min-h-fit lg:text-base flex items-center justify-center text-center`}>
            {content}
        </div>
    );
};

export default PreferenceBox;