import React from 'react';

const LargeTextField = ({name, placeholder, handleChange, label, value}) => {
    return (
        <div className="w-full">
            <label className="font-bold text-sm md:text-lg" htmlFor={name}>{label}</label>
            <textarea
                rows={5}
                className="resize-none bg-textFieldColor text-xs md:text-sm rounded-lg focus:ring-primary p-3 w-full border-2 border-hintTextColor"
                name={name}
                placeholder={placeholder}
                onChange={handleChange}
                value={value}
            />
        </div>
    );
};

export default LargeTextField;