"use client"
import React, {useState} from 'react';
import RegistrationForm from "@/components/RegistrationForm";
import {toast} from "sonner";
import useSWRMutation from "swr/mutation";
import {postFetcher} from "@/utils/fetcher";
import {useRouter} from "next/navigation";

const Page = () => {
    const router = useRouter();

    const [appUser, setAppUser] = useState({
        name: '',
        email: '',
        password: '',
        retypePassword: ''
    })

    const {trigger} = useSWRMutation(['/register', {}], postFetcher);

    const handleReg = (name, value) => {
        setAppUser((prev) => ({...prev, [name]: value}));
    }

    const register = async () => {
        try {
            const res = await trigger(appUser);
            if (res.status === 201) {
                toast.success("Successfully registered!");
                router.replace('/login')
            }
        } catch (error) {
            console.log(error)
        }


    }
    return (
        <section>
            <RegistrationForm onSubmit={register} handleChange={handleReg}/>
        </section>

    );
};

export default Page;