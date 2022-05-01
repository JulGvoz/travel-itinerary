import * as commons from "./commons.js";

function populateInformation(){
    const urlParams = new URLSearchParams(window.location.search);
    let content = document.getElementById("elaborate_view");

    let startDate = new Date(urlParams.get("startDate"));
    let endDate = new Date(urlParams.get("endDate"));
    let diff = commons.dateDiffInDays(startDate, endDate);
    console.log(startDate);
    console.log(endDate);
    console.log(diff);
    content.getElementsByTagName("h3")[0].innerHTML = `${urlParams.get("city")}, ${urlParams.get("country")}`;
    content.getElementsByClassName("date_start")[0].innerHTML = `${commons.dateCustomToString(startDate)}`;
    content.getElementsByClassName("date_end")[0].innerHTML = `${commons.dateCustomToString(endDate)}`;
    content.getElementsByClassName("number_of_days")[0].innerHTML = `${diff} days`;
    content.getElementsByClassName("price")[0].innerHTML = `${Number(urlParams.get("price")).toFixed(2)}`;
}

window.onload = populateInformation;
window.refreshEntries = commons.refreshEntries;