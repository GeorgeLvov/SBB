let firstNameInp = document.getElementById("firstN");
let lastName = document.getElementById("lastN");
let birthDate = document.getElementById("BD");

let firstNameLabel = document.getElementById("fNLbl");
let lastNameLabel = document.getElementById("lNLbl");
let birthDateLabel = document.getElementById("bDLbl");

const firstNameLabelText = firstNameLabel.textContent;
const lastNameLabelText = lastNameLabel.textContent;
const birthDateLabelText = birthDateLabel.textContent;

function validationCheckInForm() {

    let firstNameVal = document.forms["checkInForm"]["firstN"].value;
    let lastNameVal = document.forms["checkInForm"]["lastN"].value;
    let birthDateVal = document.forms["checkInForm"]["BD"].value;

    if (firstNameVal === null || firstNameVal.match(/^ *$/) !== null) {
        invalid(firstNameInp, firstNameLabel, "*Name can't be empty.");
        return false;
    }
    if (lastNameVal === null || lastNameVal.match(/^ *$/) !== null) {
        invalid(lastName, lastNameLabel, "*Surname can't be empty.");
        return false;
    }
    if (birthDateVal === null || birthDateVal.match(/^ *$/) !== null) {
        invalid(birthDate, birthDateLabel, "*Birthdate can't be empty.");
        return false;
    }
}
function undoCheckInInputStyle(param) {
    if (param === 'firstN') {
        undoStyle(firstNameInp, firstNameLabel);
        firstNameLabel.textContent = firstNameLabelText;
    } else if(param === 'lastN') {
        undoStyle(lastName, lastNameLabel);
        lastNameLabel.textContent = lastNameLabelText;
    } else if(param === 'BD') {
        undoStyle(birthDate, birthDateLabel);
        birthDateLabel.textContent = birthDateLabelText;
    }
}