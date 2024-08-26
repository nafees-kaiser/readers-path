export const extractMainDomain = (url) => {
    let domain = url.replace(/(^\w+:|^)\/\//, '');
    domain = domain.replace(/^www\./, '');

    let domainParts = domain.split('.');
    return domainParts.slice(0, 2).join('.');
}