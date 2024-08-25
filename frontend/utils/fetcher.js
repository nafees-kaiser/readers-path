import {deleteCall, getCall, postCall} from "@/utils/axios.settings";

export async function fetcher(url) {
    return await getCall({url});
}

export async function bookFetcher([url, data]) {
    return await postCall({url, data});
}

export async function postFetcher([url, headers], {arg}) {
    return await postCall({url, headers, data: arg});
}

export async function deleteFetcher([url, headers], {arg}) {
    return await deleteCall({url, headers});
}