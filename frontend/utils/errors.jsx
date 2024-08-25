class CustomError extends Error {
    constructor(message, status) {
        super(message);
        this.status = status;
    }
}

export function throwApiError(message, status) {
    throw new CustomError(message + ' with status code ' + status, status);
}

export function throwServerError(message, status) {
    throw new Error(message + ' with status code ' + status);
}

export function throwNetworkError(message, status) {
    throw new Error(message + ' with status code ' + status);
}