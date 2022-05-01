import * as commons from "./commons.js";

/**
 * Creates a request to the server for populating the section with content
 */
function requestChange(){
    let section = document.getElementById("results");
    let startDate = new Date("2022-05-24");
    let endDate = new Date("2022-06-04");
    let entries = [getEntry("Barcelona", "Spain", startDate, endDate, 1473)];
    
    while (section.firstChild) {
        section.removeChild(section.firstChild);
    }
    
    for(const entry of entries){
        section.appendChild(entry);
    }
}
window.openElaborateView = openElaborateView;
window.requestChange = requestChange;

/**
 * Returns a DOM element that contains demo info from an entry
 */
function getEntry(city, country, startDate, endDate, price){
    let entry = document.createElement("div");
    entry.classList.add("entry");
    entry.dataset.city = city;
    entry.dataset.country = country;
    entry.dataset.startDate = startDate;
    entry.dataset.endDate = endDate;
    entry.dataset.price = price;
    entry.innerHTML = `<div class="entry_heading">
        <h3>${city}, ${country}</h3>
        <span class="date_start">${commons.dateCustomToString(startDate)}</span>
        <span class="date_end">${commons.dateCustomToString(endDate)}</span>
        <span class="number_of_days">${commons.dateDiffInDays(startDate, endDate)} days</span>
    </div>
    <div class="imagelist">
        <ul>
            <li>sight 1</li>
            <li>sight 2</li>
            <li>...and more</li>
        </ul>
        <img>
    </div>
    <button onclick="openElaborateView(this)">More info</button>
    <span class="price">${price.toFixed(2)}</span>`;
    return entry;
}

function openElaborateView(elem){
    let parent = elem.parentNode;
    let url = new URL(window.location.href.replace("index.html", "elaborate_view.html"));
    url.searchParams.append("city", parent.dataset.city);
    url.searchParams.append("country", parent.dataset.country);
    url.searchParams.append("startDate", parent.dataset.startDate);
    url.searchParams.append("endDate", parent.dataset.endDate);
    url.searchParams.append("duration", parent.dataset.duration);
    url.searchParams.append("price", parent.dataset.price);
    window.open(url, "_blank");
}

window.onload = requestChange();
