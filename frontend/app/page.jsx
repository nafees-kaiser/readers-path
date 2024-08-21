"use client"

import ProfileModal from "@/components/ProfileModal";

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
                {/*<QuestionCard/>*/}
                <ProfileModal/>
                <div className={"h-56"}></div>
            </div>
            {/*<div className={"absolute top-52 left-36"}>*/}
            {/*    <BookStateMenu/>*/}
            {/*</div>*/}


        </main>
    );
}
