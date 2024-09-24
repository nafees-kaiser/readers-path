import UserNavbar from "@/components/UserNavbar";
import Button from "@/components/Button";
import {FaArrowRight} from "react-icons/fa";
import Image from "next/image";
import book2 from "@/public/book2.png"


export default function Home() {
    return (
        <main className="">
            <UserNavbar/>
            <div
                className={`relative w-full min-h-screen flex flex-col justify-center items-center bg-black z-10 text-white py-4 px-4`}>
                <div className={"flex flex-col justify-center items-center gap-16"}>
                    <div className={"flex flex-col gap-2 justify-center items-center"}>
                        <p className={"text-xl xs:text-2xl md:text-3xl lg:text-4xl font-bold text-center"}>
                            Find books tailored to your preferences
                        </p>
                        <p className={"text-xs xs:text-sm lg:text-base"}>
                            Register now to read your favourite books
                        </p>
                    </div>
                    <Button
                        className={"bg-secondary hover:bg-button-hover"}
                        content={"Register now"}
                        suffixIcon={<FaArrowRight/>}
                    /></div>
                <div
                    className={`absolute inset-0 bg-cover bg-center bg-no-repeat z-[-1] opacity-50`}
                    style={{backgroundImage: `url(book1.png)`}}>
                </div>
            </div>
            <div
                className={"w-full h-fit flex flex-col justify-center items-center sm:items-start py-8 px-[15px] md:px-[100px] lg:px-[150px] gap-4 sm:flex-row"}>
                {/*<div className={"relative h-full w-full"}>*/}
                {/*    <div className={"absolute inset-0 bg-cover bg-center bg-no-repeat border border-black z-[-1]"}*/}
                {/*         style={{backgroundImage: `url(book2.png)`}}>*/}
                {/*    </div>*/}
                {/*</div>*/}
                {/*<div className={"w-full md:w-1/2"}>*/}
                    <Image src={book2} alt={"book shelf"} width={400}/>
                {/*</div>*/}

                <div className={"flex flex-col gap-8 items-center"}>
                    <p className={"text-center text-lg sm:text-xl md:text-2xl lg:text-3xl font-bold sm:text-start"}>
                        Find your favourite books and get recommendation of similar books.
                    </p>
                    <Button
                        className={"bg-secondary hover:bg-button-hover text-white"}
                        content={"View all books"}
                        suffixIcon={<FaArrowRight/>}
                    />
                </div>
            </div>
        </main>
    );
}
