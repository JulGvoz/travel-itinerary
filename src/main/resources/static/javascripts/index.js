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
    entry.innerHTML = `<h3>Barcelona, Spain</h3>
    <span class="date_start">21 May</span>
    <span class="date_end">4 June</span>
    <span class="number_of_days">14 days</span>
    <div class="imagelist">
        <ul>
            <li>sight 1</li>
            <li>sight 2</li>
            <li>...and more</li>
        </ul>
        <img>
    </div>
    <button>More info</button>
    <span class="price">1473.00</span>`;
    return entry;
}

window.onload = requestChange()