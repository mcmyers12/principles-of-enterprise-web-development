"use strict";

addEventListeners()


function validateForm() {
    try {
        if (document.getElementById('verifyPassword').value.localeCompare(document.getElementById('password').value) !== 0) {
            console.log("error");
            throw 'Passwords do not match, please reenter';
        }
        //if other stuff, throw error
    }
    catch(error) {
        window.alert(error);
    }
}

function validatePasswords() {

    
}

function addEventListeners() {
    console.log(document);//.getElementById("flowerForm"));
    console.log(document.getElementById("flowerForm"));
    //document.getElementsByTagName("form")[0].addEventListener('submit', validateForm());
    
}


