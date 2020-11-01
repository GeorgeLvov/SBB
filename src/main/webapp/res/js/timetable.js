let deptButton = document.getElementById("departureButton");
let arrButton = document.getElementById("arrivalButton");
let divDeparture = document.getElementById("departureTable");
let divArrival = document.getElementById("arrivalTable");

function showOrHideDeparture() {
    changeColor(deptButton, arrButton);
    showHide(divDeparture, divArrival);
}

function showOrHideArrival(){
    changeColor(arrButton, deptButton);
    showHide(divArrival, divDeparture);
}

function changeColor(x, y){
    x.classList.remove("btn-light");
    x.classList.add("btn-danger");
    y.classList.remove("btn-danger");
    y.classList.add("btn-light");
}

function showHide(x, y) {
    if (x.style.display === "none" && y.style.display === "block") {
        x.style.display = "block";
        y.style.display = "none"
    }
}