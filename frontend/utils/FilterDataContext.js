import {createContext, useContext} from "react";

const FilterContext = createContext();

const FilterUpdateContext = createContext();

export function FilterProvider({children, v, setFilter}) {
    // const [filter, setFilter] = React.useState(v);
    return <FilterContext.Provider value={v}>
        <FilterUpdateContext.Provider value={setFilter}>
        {children}
        </FilterUpdateContext.Provider>
    </FilterContext.Provider>;
}

export function useFilter() {
    const filterData = useContext(FilterContext);
    const setFilterData = useContext(FilterUpdateContext);
    return [filterData, setFilterData];
}