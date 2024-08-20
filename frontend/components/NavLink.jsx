import {usePathname} from "next/navigation";
import Link from "next/link";
import React from "react";

export default function NavLink({address, content}) {
    const path = usePathname()
    return <li
        className={`hover:underline ${path === address ? 'text-light-text underline' : ''}`}>
        <Link href={address}>{content}</Link>
    </li>;
}