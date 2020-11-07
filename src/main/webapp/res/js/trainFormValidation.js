let trainInput = document.getElementById("trainInput");
let capInput = document.getElementById("capInput");

let trainLabel = document.getElementById("trainLbl");
let capLabel = document.getElementById("capLbl");

const trainLabelText = trainLabel.textContent;
const capLabelText = capLabel.textContent;

let trainError = document.getElementById("trainName.errors");

if (trainError !== null) {
    invalid(trainInput, trainLabel, "*Such train already exists.");
}

function trainSuccess() {
    trainLabel.textContent = "*Train was successfully added!";
    trainLabel.style.color = "limegreen";
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

function undoTrainInputStyle(param) {
    if (param === 'train') {
        undoStyle(trainInput, trainLabel);
        trainLabel.textContent = trainLabelText;
    } else if(param === 'cap') {
        undoStyle(capInput, capLabel);
        capLabel.textContent = capLabelText;
    }
}