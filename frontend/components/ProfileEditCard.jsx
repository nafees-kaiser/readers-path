"use client"
import React, {useEffect, useRef} from 'react';
import TextField from "@/components/TextField";
import Button from "@/components/Button";
import {FiEdit} from "react-icons/fi";

const ProfileEditCard = ({content, edit, heading, isEmail, name}) => {
    const [isEdit, setIsEdit] = React.useState(false);

    const [changedData, setChangedData] = React.useState({});
    const [field, setField] = React.useState(content)

    useEffect(() => {
        setField(content)
    }, [content]);

    const changeData= e =>{
        e.preventDefault();
        setChangedData(prevState => {
            return {
                [e.target.name]: e.target.value,
            }
        });
        setField(e.target.value)
    }
    const handleEditClick = () => {
        setIsEdit(true); // Give React some time to render the input before focusing
    };
    return (
        <div>
            {
                isEdit ? (
                    <div className={"flex flex-col gap-2"}>
                        <div className={"text-sm md:text-base font-bold"}>{heading}</div>
                        <div className={"flex gap-4 items-center"}>
                            <TextField
                            name={name}
                            value={field}
                            placeholder={"Enter name"}
                            handleChange={changeData}
                        />
                            <div className={"flex"}>
                                <Button
                                content={"Save"}
                                className={"text-black font-bold"}
                                onClick={(e)=>{
                                    e.preventDefault();
                                    edit(changedData,isEmail)
                                    setIsEdit(false);
                                }}
                                />
                                <Button
                                    content={"Cancel"}
                                    className={"text-secondary font-bold"}
                                    onClick={() => setIsEdit(false)}
                                />
                            </div>
                        </div>
                    </div>


                ) : (
                    <div className={"flex flex-col gap-2"}>
                        <div className={"flex gap-4"}>
                            <div className={"text-sm md:text-base font-bold"}>{heading}</div>
                            <button
                                onClick={(e) => {
                                    e.preventDefault();
                                    handleEditClick()
                                }}
                                className={'text-xl md:text-2xl text-light-text '}>
                                <FiEdit/>
                            </button>
                        </div>
                        <div className={"text-sm md:text-base"}>{content}</div>
                    </div>
                )
            }
        </div>
    );
};

export default ProfileEditCard;