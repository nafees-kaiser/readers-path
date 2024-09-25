"use client"
import React, {useState} from 'react';
import Image from "next/image";
import logo from "@/public/readerspath_logo_primary.svg";
import TextField from "@/components/TextField";
import Button from "@/components/Button";
import Link from "next/link";
import {signIn} from "next-auth/react";

const Page = () => {
    const [credentials, setCredentials] = useState({
        email: '',
        password: ''
    });

    const handleSubmit = async () => {
        // console.log(credentials);
        await signIn("credentials", {
            email: credentials.email,
            password: credentials.password,
            redirect: true,
            callbackUrl: "/redirect"
        })
    }

    const handleChange = (name, value) => {
        setCredentials((prev) => ({...prev, [name]: value}));
    }
    return (
        <form onSubmit={(e) => {
            e.preventDefault();
            handleSubmit()
        }} className={"w-full min-h-screen flex flex-col justify-center items-center px-[15px]"}>
            <div className={"px-4 py-6 my-6 border-2 border-border rounded-xl"}>
                <div className={"flex flex-col justify-center items-center gap-4"}>
                    <div className={""}>
                        <Image className={"w-10"} src={logo} alt={"logo"}/>
                    </div>
                    <div className={"text-2xl font-bold lg:text-3xl"}>Login</div>
                </div>

                <div className={"flex flex-col items-end gap-4"}>
                    <div className={"flex flex-col justify-center items-center gap-2 w-full"}>
                        <TextField
                            label={"Email"}
                            type={"email"}
                            name={"email"}
                            placeholder={"eg. email@email.com"}
                            handleChange={(e) => handleChange(e.target.name, e.target.value)}
                        />
                        <TextField
                            label={"Password"}
                            type={"password"}
                            name={"password"}
                            placeholder={"*******"}
                            handleChange={(e) => handleChange(e.target.name, e.target.value)}
                        />
                    </div>
                    <Button type={"submit"} content={"Login"}
                            className={"bg-secondary hover:bg-button-hover text-white"}/>
                </div>
                <div className={"flex flex-col justify-center items-center gap-2 w-full"}>
                    <div className={"w-full border-t border-divider mt-4"}></div>
                    <Link href={"/register"}>
                        <div className={"text-light-text hover:underline text-xs md:text-base"}>Don't have an account?
                            Register
                        </div>
                    </Link>
                </div>
            </div>
        </form>
    );
};

export default Page;