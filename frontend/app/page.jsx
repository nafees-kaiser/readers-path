import BookInfoCard from "@/components/BookInfoCard";
import AddBookLink from "@/components/AddBookLink";

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
                <BookInfoCard/>
            </div>
            <AddBookLink content={"hello"}/>
            {/*<div className={"absolute top-52 left-36"}>*/}
            {/*    <BookStateMenu/>*/}
            {/*</div>*/}


        </main>
    );
}
