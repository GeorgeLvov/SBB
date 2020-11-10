/* JS file for pagination table with stations */

let current_stationPage = 1;
const records_per_stationPage = 5;

let idList = [];
let titles = [];

/* Fill in list with station id's.
   Fill in list with station titles, which will be rendered on page */
function data(id, title) {
    idList.push(id);
    titles.push(title);
}

/* Function for filling pages */
function changePage(page) {
    let btn_next = document.getElementById("btn_next");
    let btn_prev = document.getElementById("btn_prev");
    let stations_table = document.getElementById("stationTable");
    let page_span = document.getElementById("stationPage");

    stations_table.innerHTML = "";

    for (let i = (page - 1) * records_per_stationPage; i < (page * records_per_stationPage) && i < titles.length; i++) {
        let str = '/admin/editstation/' + idList[i];
        stations_table.innerHTML +=
            "<tr>" +
            "<th>" + (i + 1) + "</th>" +
            "<td>" + titles[i] + "</td>" +
            "<td>" + "<a class=\"pag-link\" href=\""  + str + "\">" + '<i class="far fa-edit"></i>' + "</a>" + "</td>" +
            "</tr>";
    }

    page_span.innerHTML = page + "/" + numPages(); // current page number for UI

    if (page === 1) {
        btn_prev.style.visibility = "hidden";
    } else {
        btn_prev.style.visibility = "visible";
    }

    if (page === numPages()) {
        btn_next.style.visibility = "hidden";
    } else {
        btn_next.style.visibility = "visible";
    }
}

/* Is invoked on click of the arrow-to-left on UI. */
function prevPage() {
    if (current_stationPage > 1) {
        current_stationPage--;
        changePage(current_stationPage);
    }
}

/* Is invoked on click of the arrow-to-right on UI */
function nextPage() {
    if (current_stationPage < numPages()) {
        current_stationPage++;
        changePage(current_stationPage);
    }
}

/* Counts the total number of pages */
function numPages() {
    return Math.ceil(titles.length / records_per_stationPage);
}

/* On load renders first page of the both tables(stations and trains) */
window.onload = function () {
    changePage(1);
    changeCurrPage(1);
};