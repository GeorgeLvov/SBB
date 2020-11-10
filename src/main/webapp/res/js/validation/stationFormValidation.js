let statInput = document.getElementById("statInput");
let statLabel = document.getElementById("statLbl");
const statLabelText = statLabel.textContent;

let statError = document.getElementById("title.errors");

if(statError !== null){
    invalid(statInput, statLabel, statError.textContent);
}

function stationSuccess(){
        statLabel.textContent = "*Station was successfully added!";
        statLabel.style.color = "limegreen";
}

function validateStationForm(){

    let title = document.forms["stationForm"]["statInput"].value;

    if (title === null || title.match(/^ *$/) !== null) {
        invalid(statInput, statLabel, "*Title can't be empty.");
        return false;
    }

    else if(title.length < 3 || title.length > 50){
        invalid(statInput, statLabel, "*Title must be between 3 and 50 characters.");
        return false;
    }

    else if(title.match("^[a-zA-Z0-9 .-]+$") === null){
        invalid(statInput, statLabel, "*Invalid symbol (only spaces,hyphens or dots are allowed).");
        return false;
    }

}

function undoStationInputStyle() {
    undoStyle(statInput, statLabel);
    statLabel.textContent = statLabelText;
}