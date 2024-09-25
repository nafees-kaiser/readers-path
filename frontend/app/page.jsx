"use client"
import UserNavbar from "@/components/UserNavbar";
import Button from "@/components/Button";
import {FaArrowRight} from "react-icons/fa";
import Image from "next/image";
import book2 from "@/public/book2.png"
import TextField from "@/components/TextField";
import BookAndRatingCard from "@/components/BookAndRatingCard";
import useSWR from "swr";
import {fetcher} from "@/utils/fetcher";


export default function Home() {
    const {data} = useSWR("/get-popular-books", fetcher);
    const books = data?.data;
    return (
        <main className="">
            <UserNavbar/>
            <div className={"flex flex-col gap-8 md:gap-12 lg:gap-16"}>
                <div className={'bg-primary-500 rounded-2xl my-4 md:mt-7 px-[10px] md:mx-[50px] lg:mx-[100px]'}>
                    <Image
                        src={'/abstact_shape_landing.svg'}
                        alt={"abstract_shape_landing"}
                        width={70}
                        height={70}
                    />
                    <div className={'grid grid-cols-12 justify-center items-center md:px-10'}>
                        <div className={'col-span-6'}>
                            <h1 className={'text-lg font-semibold leading-tight md:text-3xl'}>Book is a
                                window to the world</h1>
                            <p className={'text-xs py-2 md:text-lg'}>wake up your dream by reading a book every day</p>
                            <Button content={'Read Now'} className={'bg-secondary text-white'}/>
                        </div>
                        <div className={'col-span-6'}>
                            <Image src={'/ilustration_landing.svg'} alt={"illustration"} layout={'responsive'}
                                   width={100}
                                   height={100}/>
                        </div>
                    </div>
                </div>
                <div className={'my-4 px-[10px] md:px-0 md:mx-[50px] lg:mx-[100px]'}>
                    <div className={'flex justify-between items-center'}>
                        <h1 className="text-lg font-bold md:text-2xl">Popular Now</h1>
                        <p className={'text-light-text'}>View All</p>
                    </div>
                    <div className={'grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-x-6 py-2'}>

                        {/*<BookAndRatingCard image={books[0].coverImage ? books[0].coverImage : '/book-covers.jpg'} name={'Selena'} rating={5} author={'Tere Liye'}/>*/}
                        {/*<BookAndRatingCard image={books[1].coverImage ? books[1].coverImage : '/book-covers.jpg'} name={'My Secrets'} rating={4}*/}
                        {/*                   author={'Felix Y Siauw'}/>*/}
                        {/*<div className={'hidden md:block'}>*/}
                        {/*    <BookAndRatingCard image={'/book-covers.jpg'} name={'My Secrets'} rating={4}*/}
                        {/*                       author={'Felix Y Siauw'}/>*/}
                        {/*</div>*/}
                        {/*<div className={'hidden lg:block'}>*/}
                        {/*    <BookAndRatingCard image={'/book-covers.jpg'} name={'My Secrets'} rating={4}*/}
                        {/*                       author={'Felix Y Siauw'}/>*/}
                        {/*</div>*/}
                        {
                            books && books.map((book) => (
                                <div key={book.id} className={""}>
                                    <BookAndRatingCard
                                        image={book.coverImage}
                                        name={book.title}
                                        rating={book.overAllRating}
                                        author={book.author?.name}
                                        />

                                </div>
                            ))
                        }
                    </div>
                </div>
                <div className="my-4 relative md:mx-[50px] lg:mx-[100px]">
                    <div className="flex flex-col md:flex-row items-center">
                        <div className="relative w-full md:w-1/2">
                            <Image src={'/book2.png'} alt={'book'} width={400} height={100} className="w-full h-auto"/>
                            <div
                                className="absolute inset-0 flex flex-col justify-center items-center text-center text-white md:hidden">
                                <h1 className="text-lg font-bold">Find your favourite books and filter them based on
                                    your
                                    preference</h1>
                                <Button content={'View all books'} className="text-white bg-secondary mt-2"/>
                            </div>
                        </div>
                        <div
                            className="w-full md:w-1/2 md:pl-8 flex flex-col justify-center items-center text-center md:text-left">
                            <h1 className="hidden md:block text-lg font-bold">Find your favourite books and filter them
                                based on your preference</h1>
                            <Button content={'View all books'}
                                    className="hidden md:inline-block text-white bg-secondary mt-2"/>
                        </div>
                    </div>
                </div>
                <div className={'mt-4 mb-6 md:mb-8 lg:mb-16 bg-primary h-[200px] grid grid-cols-12 md:mx-[50px] lg:mx-[100px]'}>
                    <div className={'col-span-6 flex justify-center items-center'}>
                        <div className={'md:w-3/4 md:text-end '}>
                            <h1 className={'text-white md:text-2xl font-bold py-4 px-[10px] md:px-0'}>Add your books to
                                shelf and keep track of your reading</h1>
                            <Button content={'Go to shelf'} className={'text-white md:float-right'}
                                    suffixIcon={<FaArrowRight/>}/>
                        </div>
                    </div>
                    <div className={'col-span-6 relative'}>
                        <Image src={'/book1.png'} alt={'book'} objectFit={'cover'} fill className={'rounded-l-full'}/>
                    </div>
                </div>
                {/*<div className={'my-4 py-6 px-4 bg-primary-500 md:mx-[50px] lg:mx-[100px]'}>*/}
                {/*    <div className={'md:w-2/3 mx-auto'}>*/}
                {/*        <h1 className={'text-black md:text-2xl my-4 font-bold px-[10px] md:px-0'}>Recommended books from you*/}
                {/*            to*/}
                {/*            review</h1>*/}
                {/*        <div className={'flex justify-center gap-x-2'}>*/}
                {/*            <TextField placeholder={'Write your recommendation'}/>*/}
                {/*            <Button content={'Submit'} className={'bg-secondary text-white'}/>*/}
                {/*        </div>*/}
                {/*    </div>*/}
                {/*</div>*/}</div>
        </main>
    );
}
