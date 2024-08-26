import {createContext, useContext} from "react";

const FilterContext = createContext();

// const FilterUpdateContext = React.createContext(null);

export function FilterProvider({children, v}) {
    // const [filter, setFilter] = React.useState(v);
    return <FilterContext.Provider value={v}>
        {/*<FilterUpdateContext.Provider value={setFilter}>*/}
        {children}
        {/*</FilterUpdateContext.Provider>*/}
    </FilterContext.Provider>;
}

export function useFilter() {
    return useContext(FilterContext);
}