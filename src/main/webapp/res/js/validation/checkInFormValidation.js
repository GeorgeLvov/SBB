let firstNameInp;
let lastNameInp;
let birthDateInp;

let firstNameLabel;
let lastNameLabel;
let birthDateLabel;

let firstNameLabelText;
let lastNameLabelText;
let birthDateLabelText;

let index;

function setIndex(vs) {
    index = vs;
    console.log('index:' + index + ';vs: ' + vs)
    init(vs);
}

function init(vs) {

    firstNameInp = document.getElementById("firstN" + vs);
    lastNameInp = document.getElementById("lastN" + vs);
    birthDateInp = document.getElementById("BD" + vs);

    firstNameLabel = document.getElementById("fNLbl" + vs);
    lastNameLabel = document.getElementById("lNLbl" + vs);
    birthDateLabel = document.getElementById("bDLbl" + vs);

    console.log(firstNameLabelText);
    firstNameLabelText = firstNameLabel.textContent;
    console.log(firstNameLabelText);

    lastNameLabelText = lastNameLabel.textContent;
    birthDateLabelText = birthDateLabel.textContent;
}

function closeMod() {
    undoCheckInInputStyle('close');
}

function validationCheckInForm() {
    console.log('index:' + index)
    let firstNameVal = document.forms["checkInForm" + index]["firstN" + index].value;
    let lastNameVal = document.forms["checkInForm" + index]["lastN" + index].value;
    let birthDateVal = document.forms["checkInForm" + index]["BD" + index].value;

    if (firstNameVal === null || firstNameVal.match(/^ *$/) !== null) {
        invalid(firstNameInp, firstNameLabel, "*Name can't be empty.");
        return false;
    }

    if (firstNameVal.length < 2 || firstNameVal.length > 100) {
        invalid(firstNameInp, firstNameLabel, "*Name must be between 1 and 100 characters.");
        return false;
    }

    if(firstNameVal.match("^[a-zA-Z -]+$") === null){
        invalid(firstNameInp, firstNameLabel, "*Invalid symbol (only spaces or hyphens are allowed).");
        return false;
    }

    if (lastNameVal === null || lastNameVal.match(/^ *$/) !== null) {
        invalid(lastNameInp, lastNameLabel, "*Surname can't be empty.");
        return false;
    }

    if (lastNameVal.length < 2 || lastNameVal.length > 100) {
        invalid(lastNameInp, lastNameLabel, "*Surname must be between 1 and 100 characters.");
        return false;
    }

    if(lastNameVal.match("^[a-zA-Z -]+$") === null){
        invalid(lastNameInp, lastNameLabel, "*Invalid symbol (only spaces or hyphens are allowed).");
        return false;
    }

    if (birthDateVal === null || birthDateVal.match(/^ *$/) !== null) {
        invalid(birthDateInp, birthDateLabel, "*Birthdate can't be empty.");
        return false;
    }

    if (new Date(birthDateVal) > new Date()) {
        invalid(birthDateInp, birthDateLabel, "*Birthdate can't be later then now.");
        return false;
    }
}

function undoCheckInInputStyle(param) {
    if (param === 'firstN' + index) {
        undoStyle(firstNameInp, firstNameLabel);
        firstNameLabel.textContent = firstNameLabelText;
    } else if (param === 'lastN' + index) {
        undoStyle(lastNameInp, lastNameLabel);
        lastNameLabel.textContent = lastNameLabelText;
    } else if (param === 'BD' + index) {
        undoStyle(birthDateInp, birthDateLabel);
        birthDateLabel.textContent = birthDateLabelText;
    } else {
        undoStyle(firstNameInp, firstNameLabel);
        firstNameLabel.textContent = firstNameLabelText;
        undoStyle(lastNameInp, lastNameLabel);
        lastNameLabel.textContent = lastNameLabelText;
        undoStyle(birthDateInp, birthDateLabel);
        birthDateLabel.textContent = birthDateLabelText;
    }
}