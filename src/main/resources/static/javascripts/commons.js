// Source: https://stackoverflow.com/questions/3224834/get-difference-between-2-dates-in-javascript
export function dateDiffInDays(a, b) {
    // Discard the time and time-zone information.
    const utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
    const utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());
    const _MS_PER_DAY = 1000 * 60 * 60 * 24;
    return Math.floor((utc2 - utc1) / _MS_PER_DAY);
}

export function dateCustomToString(date){
    const day = date.getDate();
    const monthInt = date.getMonth();
    const months = [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July',
        'August',
        'September',
        'October',
        'November',
        'December'
      ];
    return `${day} ${months[monthInt]}`;
}

export var xhttp = new XMLHttpRequest();

export function refreshEntries(){
    xhttp.open("GET", "/api/entities");
    xhttp.send();
    console.log(xhttp.responseXML);
}

