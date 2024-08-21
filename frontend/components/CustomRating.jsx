"use client"
import React from 'react';
import Rating from "react-rating";
import {FaRegStar, FaStar} from "react-icons/fa";

const CustomRating = ({rating, readOnly = false, className, stop, onChange}) => {
    return (
        <Rating
            fullSymbol={
                <div className={`${className} text-secondary`}>
                    <FaStar/>
                </div>
            }
            emptySymbol={
                <div className={`${className} text-secondary`}>
                    <FaRegStar/>
                </div>
            }
            initialRating={rating ? rating : 0}
            readonly={readOnly}
            stop={stop ? stop : 5}
            onChange={onChange}
            fractions={2}
        />
    );
};

export default CustomRating;