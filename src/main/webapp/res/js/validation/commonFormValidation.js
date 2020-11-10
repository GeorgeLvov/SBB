

/* Adds style for form input by validation errors */
function invalid(input, label, textContent){
    input.classList.add("is-invalid");
    label.style.color = "red";
    label.textContent = textContent;
}


/* Undoes style for form input after validation errors */
function undoStyle(input, label){
    if(input.classList.contains("is-invalid")){
        input.classList.remove("is-invalid");
    }
    label.style.color = "black";
}
