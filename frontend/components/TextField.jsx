export default function TextField({type, name, placeholder, handleChange, label, value}) {
    return (
        <div className="w-full">
            <label className="font-bold text-sm md:text-lg" htmlFor={name}>{label}</label>
            <input
                className="bg-textFieldColor text-xs md:text-sm rounded-lg focus:ring-primary p-3 w-full border-2 border-hintTextColor"
                type={type}
                name={name}
                placeholder={placeholder}
                onChange={handleChange}
                value={value}
            />
        </div>
    );
}