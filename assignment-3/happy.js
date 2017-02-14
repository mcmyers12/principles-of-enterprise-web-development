"use strict";

addEventListeners()


function validateForm(e) {
    try {
        validateMessage();
        copyAddress();
        validatePasswords();
    }
    catch(error) {
        e.preventDefault(); //prevent submission
        window.alert(error);
    }
}

function validateMessage() {
    var message = document.querySelectorAll("#message input");
    
    var messageSelected = false;
    messageSelected |= document.getElementById("congratulations").checked;
    messageSelected |= document.getElementById("happyBirthday").checked;
    messageSelected |= document.getElementById("happyAnniversary").checked;
    messageSelected |= document.getElementById("iLoveYou").checked;
    messageSelected |= document.getElementById("customMessage").checked;
    
    if (messageSelected == false) {
        throw 'You must select at least one message';
    }
    
    if (document.getElementById("customMessage").checked) {
        var customText = document.getElementById("customText").value;
        if ((customText === "")) {
            throw 'Please enter your custom message';
        }
    }
}

function copyAddress() {
    var billingAddress = document.querySelectorAll("#billingAddress input");
	var deliveryAddress = document.querySelectorAll("#deliveryAddress input");
    if(document.getElementById("sameAddress").checked) {
        for (var i = 0; i < billingAddress.length; i++) {
			deliveryAddress[i + 1].value = billingAddress[i].value;
		}
		document.querySelector("#deliveryAddress select").value = document.querySelector("#billingAddress select").value;
    }
    else {
        for (var i = 1; i < deliveryAddress.length; i++) {
			deliveryAddress[i].value = "";
		}
    }
}

function validatePasswords() {
    if (document.getElementById('verifyPassword').value.localeCompare(document.getElementById('password').value) !== 0) {
        throw 'Passwords do not match, please reenter';
    }
    
}

function addEventListeners() {
    var form = document.getElementById("flowerForm");
    if (form.addEventListener) { 
        form.addEventListener('submit', validateForm, true);
    }
    
    var sameAddress = document.getElementById("sameAddress");
	if (sameAddress.addEventListener) {
		sameAddress.addEventListener("click", copyAddress);
	}
    
}


