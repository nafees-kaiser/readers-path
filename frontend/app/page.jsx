"use client"

import QuestionCard from "@/components/QuestionCard";

export default function Home() {
    return (
        <main className="">
            {/*<TextField*/}
            {/*    type="text"*/}
            {/*    name={"email"}*/}
            {/*    placeholder={"Email"}*/}
            {/*    label={"Email"}*/}
            {/*/>*/}
            {/*<Button content={"Save"}*/}
            {/*        prefixIcon={<Fa0/>}*/}
            {/*        suffixIcon={<FaArrowRight/>}/>*/}
            <div className={'mx-[10px]'}>
                {/*<BookCard state={"shelf"}/>*/}
                {/*<BookInfoCard/>*/}
                {/*<CustomRating*/}
                {/*    stop={1}*/}
                {/*    rating={1}*/}
                {/*/>*/}
                {/*<ReviewCard/>*/}
                <QuestionCard/>
            </div>
            {/*<div className={"absolute top-52 left-36"}>*/}
            {/*    <BookStateMenu/>*/}
            {/*</div>*/}


        </main>
    );
}
