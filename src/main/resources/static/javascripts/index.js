/**
 * Creates a request to the server for populating the section with content
 */
function requestChange(){
    let section = document.getElementById("results");
    section.appendChild(getDemoEntry())
}

/**
 * Returns a DOM element that contains demo info from an entry
 */
function getDemoEntry(){
    let entry = document.createElement("div");
    entry.classList.add("entry")
    entry.innerHTML = "";
    return entry;
}

window.onload = requestChange()