'use client'
import React, {useRef, useState} from 'react';
import Image from "next/image";
import {FaRegImage} from "react-icons/fa";

const InputImage = ({label, onChange, required = true}) => {
    const [image, setImage] = useState('')
    const handleImage = (e) => {
        e.preventDefault();
        // console.log(e.target.files[0]);
        setImage(e.target.files[0]);
        onChange(e.target.files[0]);

    }
    const imageRef = useRef();

    return (
        <div>
            <label
                className={"font-bold text-sm md:text-lg"}
                htmlFor={"image"}>{label}</label>
            <div
                onClick={() => imageRef.current.click()}
                className={"p-4 min-h-36 h-fit w-full sm:w-36 cursor-pointer rounded-lg bg-tertiary-bg flex items-center justify-center"}>
                <input
                    type={"file"}
                    required={required}
                    id={"image"}
                    className={"hidden w-full"}
                    ref={imageRef}
                    onChange={handleImage}
                />
                <div className={"text-light-text w-full flex justify-center items-center"}>
                    {image ? (
                        <div className={"text-center"}>
                            {/*{image.name}*/}
                            <Image src={URL.createObjectURL(image)} alt={image.name}
                                   width={90}
                                   height={90 * 3 / 4}
                            />
                        </div>
                    ) : (
                        <FaRegImage width={100}/>
                    )}

                </div>
            </div>
        </div>
    );
};

export default InputImage;