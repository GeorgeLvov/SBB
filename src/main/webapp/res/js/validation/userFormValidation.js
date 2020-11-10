
let emailInput = document.getElementById("emailInp");
let passInput = document.getElementById("passInp");
let confInput = document.getElementById("confInp");

let emailLabel = document.getElementById("emailLbl");
let passLabel = document.getElementById("passLbl");
let confLabel = document.getElementById("confLbl");

const emailLabelText = emailLabel.textContent;
const passLabelText = passLabel.textContent;
const confLabelText = confLabel.textContent;

let emailErrors = document.getElementById("username.errors");
let passErrors = document.getElementById("password.errors");
let confErrors = document.getElementById("confirmPassword.errors");

if (emailErrors !== null) {
    invalid(emailInput, emailLabel, emailErrors.textContent);
}

if(passErrors != null){
    invalid(passInput, passLabel, passErrors.textContent);
}

if(confErrors != null){
    invalid(confInput, confLabel, confErrors.textContent);
}

function validateRegistrationForm(){

    let emailVal = document.forms["regForm"]["emailInp"].value;
    let passVal = document.forms["regForm"]["passInp"].value;
    let confVal = document.forms["regForm"]["confInp"].value;

    if (emailVal === null || emailVal.match(/^ *$/) !== null) {
        invalid(emailInput, emailLabel, "*Email can't be empty.");
        return false;
    }
    if (emailVal.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/) === null) {
        invalid(emailInput, emailLabel, "*Please provide a valid email address.");
        return false;
    }
    if(emailVal.length < 6 || emailVal.length > 50){
        invalid(emailInput, emailLabel, "*Email must be between 6 and 50 characters.");
        return false;
    }

    if (passVal === null || passVal.match(/^ *$/) !== null) {
        invalid(passInput, passLabel, "*Password can't be empty.");
        return false;
    }

    if (passVal.length < 4 || passVal.length > 50) {
        if (passVal.length < 4) {
            invalid(passInput, passLabel, "*Password must contain 4 characters or more.");
        } else {
            invalid(passInput, passLabel, "*Password must not exceed 50 characters");
        }
        return false;
    }

    if(passVal !== confVal){
        invalid(confInput, confLabel, "*Passwords don't match");
        return false;
    }
}

function undoUserInputStyle(param) {
    if (param === 'email') {
        undoStyle(emailInput, emailLabel);
        emailLabel.textContent = emailLabelText;
    } else if(param === 'pass') {
        undoStyle(passInput, passLabel);
        passLabel.textContent = passLabelText;
    } else if(param === 'conf') {
        undoStyle(confInput, confLabel);
        confLabel.textContent = confLabelText;
    }
}
