function getDateFormat(fmt, date) {
    date = new Date(date);
    const o = {
        "M+": date.getMonth() + 1,                 //月份
        "d+": date.getDate(),                    //日
        "h+": date.getHours(),                   //小时
        "m+": date.getMinutes(),                 //分
        "s+": date.getSeconds(),                 //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function getISODateFormat(date) {
    return getDateFormat('yyyy-MM-dd', date);
}

function getISOTimeFormat(date) {
    return getDateFormat('yyyy-MM-dd hh:mm:ss', date);
}

function getSearchStr(url, offset = 1) {
    if (!url) {
        url = window.location.href;
    }
    if (url.indexOf('?') > -1) {
        return url.substring(url.indexOf('?') + offset);
    }
    return '';
}

export default {
    getDateFormat,
    getISODateFormat,
    getISOTimeFormat,
    getSearchStr
}