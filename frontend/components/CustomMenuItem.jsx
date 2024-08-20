import React from 'react';
import {MenuItem} from '@headlessui/react'

const CustomMenuItem = ({content}) => {
    return (
        <MenuItem>
            {/*<a*/}
            {/*    href="#"*/}
            {/*    className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"*/}
            {/*>*/}
            {/*    Account settings*/}
            {/*</a>*/}
            {content}
        </MenuItem>
    );
};

export default CustomMenuItem;