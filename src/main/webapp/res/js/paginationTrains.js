/* JS file for pagination table with trains */

let current_trainPage = 1;
const records_per_trainPage = 5;

let names = [];
let caps = [];

/* Fill in 2 lists with train names and capacity values, which will be rendered on page */
function trainData(n, c) {
    names.push(n);
    caps.push(c);
}

/* Function for filling pages */
function changeCurrPage(page) {
    let btn_next = document.getElementById("btn_nextt");
    let btn_prev = document.getElementById("btn_prevt");
    let trains_table = document.getElementById("trainTable");
    let page_span = document.getElementById("trainPage");

    trains_table.innerHTML = "";

    for (let i = (page - 1) * records_per_trainPage; i < (page * records_per_trainPage) && i < names.length; i++) {
        trains_table.innerHTML +=
            "<tr>" +
            "<th>" + (i + 1) + "</th>" +
            "<td>" + names[i] + "</td>" +
            "<td>" + caps[i] + "</td>" +
            "</tr>";
    }

    page_span.innerHTML = page + "/" + numberPages(); // current page number for UI

    if (page === 1) {
        btn_prev.style.visibility = "hidden";
    } else {
        btn_prev.style.visibility = "visible";
    }

    if (page === numberPages()) {
        btn_next.style.visibility = "hidden";
    } else {
        btn_next.style.visibility = "visible";
    }
}
/* Is invoked on click of the Prev link on UI. */
function prevTrainPage() {
    if (current_trainPage > 1) {
        current_trainPage--;
        changeCurrPage(current_trainPage);
    }
}

/* Is invoked on click of the Next link on UI */
function nextTrainPage() {
    if (current_trainPage < numberPages()) {
        current_trainPage++;
        changeCurrPage(current_trainPage);
    }
}
/* Counts the total number of pages */
function numberPages() {
    return Math.ceil(names.length / records_per_trainPage);
}
