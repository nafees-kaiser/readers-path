import _axios from "axios"
import baseUrl from "@/utils/baseUrl";
import {throwApiError, throwNetworkError, throwServerError} from "@/utils/errors";
import {getSession} from "next-auth/react";

export const HTTP_STATUS = {
    OK: 200,
    CREATED: 201,
    NO_CONTENT: 204,
    BAD_REQUEST: 400,
    UNAUTHORIZED: 401,
    FORBIDDEN: 403,
    NOT_FOUND: 404,
    METHOD_NOT_ALLOWED: 405,
    SERVER_ERROR: 500,
    NOT_ACCEPTABLE: 406,
    UNSUPPORTED_MEDIA_TYPE: 415,
    PRECONDITION_FAILED: 412,
    REQUEST_TIMEOUT: 408,
};

export const REQUEST_STATUS = {
    GET: HTTP_STATUS.OK,
    PUT: HTTP_STATUS.OK,
    PATCH: HTTP_STATUS.OK,
    POST: HTTP_STATUS.CREATED,
    DELETE: HTTP_STATUS.NO_CONTENT,
};

const bearerToken = async ({req}) => {
    const session = await getSession({req});
    const a = session?.user?.token
        ? {
            'Authorization': `Bearer ${session?.user?.token}`,
        }
        : {};
    return a;
};

const axios = _axios.create({
    timeout: 600000,
    baseURL: baseUrl
});

const getErrorMessage = (e) => {
    const code = e.code || e.request?.status || e.response?.status;
    if (code === "ECONNREFUSED" || code === "ECONNABORTED")
        return "Failed to advance to the next step due to network error";

    const data = e.response?.data;
    if (data) {
        if (typeof data === "string") return data;
        if (typeof data.message === "string") return data.message;
        if (typeof data.error === "string") return data.error;
    }
    return e.toString();
};

export const callApi = async ({
                                  req,
                                  url,
                                  method = "GET",
                                  data = {},
                                  headers = {},
                                  params = {},
                              }) => {
    // console.log("inside callapi ", baseUrl)
    let res;
    try {
        res = await axios({
            method,
            url,
            params,
            data,
            headers: {
                ...headers,
                ...(await bearerToken({req})),
            },
        });
    } catch (e) {
        console.log(`Got error in callApi to: ${url}, ${method}`);
        console.dir(e);

        if (e.response) {
            let {status} = e.response;
            let message = getErrorMessage(e);

            if (
                status >= HTTP_STATUS.BAD_REQUEST &&
                status < HTTP_STATUS.SERVER_ERROR
            ) {
                if (status === HTTP_STATUS.PRECONDITION_FAILED) {
                    message = "Data was updated by another user";
                }
                throwApiError(message, status);
            } else {
                throwServerError(message, status);
            }
        } else {
            throwNetworkError(e.toString());
        }
    }

    if (res.status !== REQUEST_STATUS[method.toUpperCase()]) {
        throwApiError(
            `Error in calling server API, HTTP status: ${res.statusText}`,
            res.status
        );
    }

    return {data: res.data, revision: res.headers["etag"], status: res.status};
};


export const postCall = async ({
                                   req, url,
                                   data = {},
                                   headers = {},
                                   params = {},
                               }) => {
    return callApi({req, url, method: "POST", data, headers, params});

}

export const getCall = async ({
                                  req, url,
                                  data = {},
                                  headers = {},
                                  params = {},
                              }) => {
    return callApi({req, url, method: "GET", data, headers, params});

}

export const putCall = async ({
                                  req, url,
                                  data = {},
                                  headers = {},
                                  params = {},
                              }) => {
    return callApi({req, url, method: "PUT", data, headers, params});

}

export const deleteCall = async ({
                                     req, url,
                                     data = {},
                                     headers = {},
                                     params = {},
                                 }) => {
    return callApi({req, url, method: "DELETE", data, headers, params});

}

