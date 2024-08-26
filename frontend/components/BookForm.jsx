'use client'
import React, {useEffect, useRef, useState} from 'react';
import TextField from "@/components/TextField";
import CategoryStateMenu from "@/components/CategoryStateMenu";
import InputImage from "@/components/InputImage";
import Button from "@/components/Button";
import AddBookLink from "@/components/AddBookLink";
import {extractMainDomain} from "@/utils/editStrings";
import useSWRMutation from "swr/mutation";
import {postFetcher, putFetcher} from "@/utils/fetcher";
import {toast} from "sonner";
import {useRouter} from "next/navigation";

const BookForm = ({
                      heading,
                      isUser = true,
                      isEdit = false,
                      content = {
                          title: '',
                          publisher: '',
                          edition: '',
                          pageCount: '',
                          isbn: '',
                          category: {},
                          links: [],
                      }
                  }) => {
    const urlEdit = isUser ? '/user/my-books/edit' : '/admin/add-book'
    const urlAdd = isUser ? '/user/add-book' : '/admin/add-book';
    const url = isEdit ? urlEdit : urlAdd;
    const headers = {
        'Content-Type': 'multipart/form-data',
    }

    const {trigger} = useSWRMutation([url, headers], isEdit ? putFetcher : postFetcher)
    const router = useRouter()

    const [bookForm, setBookForm] = useState(content)

    const [image, setImage] = useState('')
    const linkRef = useRef('')

    if (isEdit) {
        useEffect(() => {
            setBookForm(content)
        }, [content]);
    }


    const addLink = e => {
        e.preventDefault();
        if (bookForm.links.filter(l => l.link === linkRef.current).length === 0) {
            const aLink = {
                link: linkRef.current,
                name: extractMainDomain(linkRef.current)
            }
            setBookForm(prevState => {
                return {
                    ...prevState,
                    links: [...prevState.links, aLink],
                }
            })
        }

    }

    const removeLink = link => {
        const links = bookForm.links.filter(l => l.link !== link)
        setBookForm(prevState => {
            return {
                ...prevState,
                links: links
            }
        })
    }
    const changeAuthor = e => {
        e.preventDefault()
        if (!isUser) {
            handleChange("author", {name: e.target.value})
        }
    }

    const handleChange = (name, value) => {
        setBookForm((prevBook) => ({...prevBook, [name]: value}));
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        const save = async () => {
            const formData = new FormData()
            formData.append("book", new Blob([JSON.stringify(bookForm)], {type: 'application/json'}))
            if (image !== '') formData.append("coverImage", image)
            try {
                const res = await trigger(formData)
                // console.log(res)
                if (res.status === 201 || res.status === 200) {
                    toast.success(`Book ${isEdit ? 'edited' : 'added'} successfully!`);
                    setTimeout(() => {
                        router.push("/my-books")
                    }, 1000)
                }
            } catch (e) {
                console.log(e)
            }
        }

        if (isEdit || (Object.keys(bookForm.category).length > 0 && bookForm.links.length > 0)) {
            save().then()
        } else {
            toast.error("Please fill up all fields")
        }

        // if () {
        //     // save().then()
        // }

        // console.log(bookForm)

    }


    return (
        <div
            className={"flex flex-col items-center px-[15px] sm:px-[50px] md:px-[100px] lg:px-[200px] py-8"}>
            <div
                className={"flex flex-col gap-6"}>
                <div className={"text-lg md:text-xl font-bold"}>{heading}</div>
                <form
                    onSubmit={handleSubmit}
                    className={"flex flex-col gap-6 items-end w-fit"}>
                    <div className={"flex flex-col gap-3"}>
                        <TextField
                            name={"title"}
                            label={"Title"}
                            placeholder={"eg. Harry Potter"}
                            required={!isEdit}
                            value={bookForm.title}
                            handleChange={(e) => {
                                e.preventDefault();
                                handleChange(e.target.name, e.target.value);
                            }}
                        />
                        {!isUser &&
                            <TextField
                                name={"author"}
                                label={"Author"}
                                placeholder={"eg. J.K Rowlilng"}
                                value={bookForm.author?.name}
                                required={!isEdit}
                                handleChange={changeAuthor}
                            />
                        }
                        <TextField
                            name={"publisher"}
                            label={"Publisher"}
                            placeholder={"eg. ABC Publisher"}
                            required={!isEdit}
                            value={bookForm.publisher}
                            handleChange={(e) => {
                                e.preventDefault();
                                handleChange(e.target.name, e.target.value);
                            }}
                        />
                        <div className={"flex flex-col gap-3 sm:flex-row sm:gap-3 sm:items-center"}>
                            <TextField
                                name={"isbn"}
                                label={"ISBN"}
                                placeholder={"eg. 984#########"}
                                value={bookForm.isbn}
                                required={!isEdit}
                                handleChange={(e) => {
                                    e.preventDefault();
                                    handleChange(e.target.name, e.target.value);
                                }}
                            />

                            {/*category*/}
                            <div className={"w-full flex flex-col"}>
                                <label
                                    className={"font-bold text-sm md:text-lg"}
                                    htmlFor={"category"}>Category</label>
                                <CategoryStateMenu
                                    id={"category"}
                                    onChange={handleChange}
                                />
                            </div>

                        </div>
                        <div className={"flex flex-col gap-3 sm:flex-row sm:gap-4 sm:items-center"}>
                            <TextField
                                name={"pageCount"}
                                label={"Page count"}
                                placeholder={"eg. 200"}
                                required={!isEdit}
                                value={bookForm.pageCount}
                                handleChange={(e) => {
                                    e.preventDefault();
                                    handleChange(e.target.name, e.target.value);
                                }}
                            />
                            <TextField
                                name={"edition"}
                                label={"Edition"}
                                placeholder={"eg. 1st edition"}
                                value={bookForm.edition}
                                required={!isEdit}
                                handleChange={(e) => {
                                    e.preventDefault();
                                    handleChange(e.target.name, e.target.value);
                                }}
                            />

                        </div>
                        <InputImage
                            label={"Book cover"}
                            onChange={setImage}
                            required={!isEdit}
                        />
                        <div className={"flex items-end gap-2"}>
                            <TextField
                                label={"Links to buy"}
                                name={"links"}
                                required={false}
                                placeholder={"eg. https://www.example.com"}
                                type={"url"}
                                handleChange={(e) => {
                                    e.preventDefault();
                                    linkRef.current = e.target.value;
                                }}
                            />
                            <Button
                                content={"Add"}
                                type={"button"}
                                onClick={addLink}
                                className={"border-2 border-secondary text-secondary hover:text-button-hover"}
                            />
                        </div>
                        <div className={"flex items-center gap-1.5 flex-wrap"}>
                            {
                                bookForm.links && bookForm.links.length > 0 &&
                                bookForm.links.map((link, index) => (
                                    <AddBookLink key={index}
                                                 onClick={(e) => {
                                                     e.preventDefault();
                                                     removeLink(link.link);
                                                 }}
                                                 content={link.name}/>
                                ))
                            }

                        </div>
                    </div>
                    <Button
                        type={"submit"}
                        className={"bg-secondary text-white hover:bg-button-hover"}
                        content={"Save"}
                    />

                </form>
            </div>
        </div>
    );
};

export default BookForm;