import Image from "next/image";
import CustomRating from "@/components/CustomRating";
import bookCover from "@/public/book-covers.jpg";
import React from "react";

const BookAndRatingCard = ({image,name,rating,author}) => {
    const imageEncoded = image?.imageEncoded ? image?.imageEncoded : null;
    const imageType = image?.imageType ? image?.imageType : null;
    const coverImage = `data:${imageType};base64,${imageEncoded}`;
    return (
        <>
            <div className={"flex-1 min-w-[150px] max-w-[300px] border rounded-xl shadow-xl mb-5"}>
                <div className={"w-full h-[150px] bg-slate-100 rounded-lg relative"}>
                    <Image src={image ? coverImage: '/book-covers.jpg'}
                           alt={'cover'}
                           fill
                           // layout="responsive"
                           // width={400}
                           // height={300}
                           objectFit={"cover"}
                           className={"rounded-lg"} />
                    {/*<Image src={image ? image : '/book-covers.jpg'} alt={"Book cover"}*/}
                    {/*       layout="responsive"*/}
                    {/*       width={400}*/}
                    {/*       height={300}*/}
                    {/*       objectFit={"cover"}*/}
                    {/*       className={"rounded-lg"}*/}
                    {/*/>*/}
                </div>
                <div className={"py-4 px-2"}>
                    <p className={"font-semibold text-sm overflow-hidden whitespace-nowrap text-ellipsis"}>{name}</p>
                    <CustomRating readOnly={true} rating={rating} className={"text-md md:text-2xl text-yellow-400"}/>
                    <p className={'text-light-text text-sm overflow-hidden whitespace-nowrap text-ellipsis'}>{author}</p>

                </div>
            </div>

        </>
    );
}
export default BookAndRatingCard;