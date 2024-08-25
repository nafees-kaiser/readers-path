"use client"
import React, {useState} from 'react';
import CategoryPreferenceSelect from "@/components/CategoryPreferenceSelect";
import RegistrationForm from "@/components/RegistrationForm";
import {toast, Toaster} from "sonner";
import useSWRMutation from "swr/mutation";
import {postFetcher} from "@/utils/fetcher";
import {useRouter} from "next/navigation";

const Page = () => {
    const router = useRouter();
    const [page, setPage] = useState(0);

    const [appUser, setAppUser] = useState({
        name: '',
        email: '',
        password: '',
        retypePassword: ''
    })

    const {trigger} = useSWRMutation(['/register', {}], postFetcher);

    const changePage = () => {
        if (page === 0) {
            if (appUser.password !== appUser.retypePassword) {
                // alert("Passwords do not match")
                toast.error("Passwords don't match");
            } else {
                setPage(page + 1);
            }
        } else if (page === 1) {
            if (favouriteCategories.length === 0) {
                toast.error("Select at least one category");
            } else {
                setPage(page + 1);
            }

        }
    }

    const [favouriteCategories, setFavouriteCategories] = useState([])
    const handleCategory = (categories) => {
        setFavouriteCategories(categories)

    }

    const [favouriteAuthors, setFavouriteAuthors] = useState([])
    const handleAuthor = (authors) => {
        setFavouriteAuthors(authors)
    }

    const handleReg = (name, value) => {
        setAppUser((prev) => ({...prev, [name]: value}));
    }

    const register = async () => {
        if (favouriteAuthors.length === 0) {
            toast.error("Select at least one author");
        } else {
            const data = {
                appUser,
                favouriteCategories,
                favouriteAuthors,
            }
            try {
                const res = await trigger(data);
                console.log(res);
                if (res.data) {
                    toast.success("Successfully registered!");
                    setTimeout(() => {
                        router.replace('/login')
                    }, 1000)

                }
                // console.log("The response ", res)
            } catch (error) {
                if (error.status === 400) {
                    setPage(0)
                    toast.error("Email already exists, please change your email");
                } else {
                    console.log(error.status + ": " + error.message);
                }

                // console.log("the error", error)
            }

        }


    }
    return (
        <section>
            <Toaster richColors={true} position={"top-center"}/>
            {page === 1 ? <CategoryPreferenceSelect
                    key={0}
                    isCategory={true}
                    buttonText={"Next"}
                    onSubmit={changePage}
                    onChange={handleCategory}/>
                : page === 2 ? <CategoryPreferenceSelect
                        key={1}
                        isCategory={false}
                        buttonText={"Save"}
                        onChange={handleAuthor}
                        onSubmit={register}/>
                    : <RegistrationForm onSubmit={changePage} handleChange={handleReg}/>}
            {/*<CategoryPreferenceSelect*/}
            {/*    isCategory={true}*/}
            {/*    buttonText={"Next"}*/}
            {/*    onSubmit={changePage}*/}
            {/*    onChange={handleCategory}/>*/}
        </section>

    );
};

export default Page;