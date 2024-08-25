import {getCall, postCall} from "@/utils/axios.settings";

export async function fetcher(url) {
    return await getCall({url});
}

export async function postFetcher([url, headers], {arg}) {
    return await postCall({url, headers, data: arg});
}