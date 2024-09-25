export default function TextField({type = "text",
                                      name,
                                      placeholder,
                                      handleChange,
                                      label,
                                      value,
                                      required = true}) {
    return (
        <div className="w-full">
            <label className="font-bold text-sm md:text-lg" htmlFor={name}>{label}</label>
            <input
                className="bg-white text-xs md:text-sm rounded-lg focus:ring-primary p-3 w-full border-2 border-border"
                type={type}
                name={name}
                placeholder={placeholder}
                onChange={handleChange}
                value={value}
                required={required}
            />
        </div>
    );
}