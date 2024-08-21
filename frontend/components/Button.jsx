export default function Button({content, onClick, type = 'button', prefixIcon, suffixIcon, className}) {
    return (
        <button type={type}
                className={`${className} w-fit text-xs xs:text-sm md:text-base rounded-lg px-4 py-2 font-bold flex gap-1.5 items-center`}
                onClick={onClick}>
            {prefixIcon}
            {content}
            {suffixIcon}
        </button>
    );
}