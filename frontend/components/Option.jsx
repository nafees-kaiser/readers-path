"use client"
import {Menu, MenuButton, MenuItems} from '@headlessui/react'
import {FaChevronDown} from "react-icons/fa";

export default function Option({menuItems, title, className}) {
    // const className = 'shadow-sm ring-1 ring-inset ring-gray-300  hover:bg-gray-50'
    return (
        <Menu as="div" className="relative inline-block text-left">
            <div>
                <MenuButton
                    className={`${className} text-xs md:text-base bg-transparent inline-flex w-full justify-center gap-x-1.5 rounded-md bg-white px-3 py-2 font-semibold`}>
                    {title}
                    <FaChevronDown color={'black'} aria-hidden="true"
                                   className="-mr-1 h-4 w-4 md:h-5 md:w-5 text-gray-400"/>
                </MenuButton>
            </div>

            <MenuItems
                transition
                className="absolute right-0 z-10 mt-2 w-36 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
            >
                <div className="py-1">

                    {menuItems}
                </div>
            </MenuItems>
        </Menu>
    )
}
