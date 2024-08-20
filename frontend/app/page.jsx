import BookCard from "@/components/BookCard";

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
                <BookCard state={"shelf"}/>
            </div>
            {/*<div className={"absolute top-52 left-36"}>*/}
            {/*    <BookStateMenu/>*/}
            {/*</div>*/}


        </main>
    );
}
