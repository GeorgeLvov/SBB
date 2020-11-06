let trainInput = document.getElementById("trainInput");
let capInput = document.getElementById("capInput");
let statInput = document.getElementById("statInput");

let trainLabel = document.getElementById("trainLbl");
let capLabel = document.getElementById("capLbl");
let statLabel = document.getElementById("statLbl");

const trainLabelText = trainLabel.textContent;
const capLabelText = capLabel.textContent;
const statLabelText = statLabel.textContent;


let trainError = document.getElementById("trainName.errors");
let statError = document.getElementById("title.errors");

if(trainError !== null){
    invalid(trainInput, trainLabel,"*Such train already exists." );
}
if(statError !== null){
    invalid(statInput, statLabel, "*Such station already exists.");
}

function success(param){
    if(param === 'train'){
        trainLabel.textContent = "*Train was successfully added!";
        trainLabel.style.color = "limegreen";
    } else {
        statLabel.textContent = "*Station was successfully added!";
        statLabel.style.color = "limegreen";
    }
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
function validateTrainForm() {

    let name = document.forms["trainForm"]["trainInput"].value;
    let capacity = document.forms["trainForm"]["capInput"].value;

    if (name === null || name.match(/^ *$/) !== null) {
        invalid(trainInput, trainLabel, "*Train name can't be empty.");
        return false;
    }
    else if(name.length < 3 || name.length > 30){
        invalid(trainInput, trainLabel, "*Name must be between 3 and 30 characters.");
        return false;
    }
    else if(name.match("^[a-zA-Z0-9 -]+$") === null){
        invalid(trainInput, trainLabel, "*Invalid symbol (only spaces or hyphens are allowed).");
        return false;
    }

    else if(capacity < 1 || capacity > 1000){
        invalid(capInput, capLabel, "*Capacity must be between 1 and 1000 seats.");
        return false;
    }
}

function invalid(input, label, textContent){
    input.classList.add("is-invalid");
    label.style.color = "red";
    label.textContent = textContent;
}

function undo(param) {
    if (param === 'train') {
        undoStyle(trainInput, trainLabel);
        trainLabel.textContent = trainLabelText;
    } else if(param === 'cap') {
        undoStyle(capInput, capLabel);
        capLabel.textContent = capLabelText;
    } else if(param === 'stat'){
        undoStyle(statInput, statLabel);
        statLabel.textContent = statLabelText;
    }
}

function undoStyle(input, label){
    input.classList.remove("is-invalid");
    label.style.color = "black";
}
