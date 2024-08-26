import React, {useState} from 'react';
import {Checkbox} from "@headlessui/react";
import tick from "@/public/tick.svg"
import Image from "next/image";

const CheckBox = ({content, onChange}) => {
    const [enabled, setEnabled] = useState(false);
    return (
        <div className={"flex gap-1 items-center w-fit"}>
            <Checkbox
                className={"group size-4 md:size-5 flex justify-center items-center rounded border border-border bg-white data-[checked]:bg-secondary"}
                onChange={() => {
                    const checked = !enabled
                    checked ? onChange(content, "add") : onChange(content, "remove");
                    setEnabled(checked);
                }}
                checked={enabled}
            >

                {/*<TiTick className={"text-base"} color={'white'}/>*/}
                <div className={"hidden size-2.5 md:size-3.5 group-data-[checked]:block"}>
                    <Image src={tick} alt="tick"/>
                </div>
            </Checkbox>
            <div className={"text-sm md:text-base"}>{content}</div>
        </div>
    );
};

export default CheckBox;