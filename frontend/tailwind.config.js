/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./pages/**/*.{js,ts,jsx,tsx,mdx}",
        "./components/**/*.{js,ts,jsx,tsx,mdx}",
        "./app/**/*.{js,ts,jsx,tsx,mdx}",
    ],
    theme: {
        extend: {
            colors: {
                'border': '#BEC5D1',
                'light-text': '#9CA3AF',
                'secondary-bg': '#F6F8FA',
                'tertiary-bg': '#E9E9E9',
                'primary': '#116466',
                'secondary': '#E64833',
                'divider': '#DAD9D9'
            }
        },
    },
    plugins: [],
};
