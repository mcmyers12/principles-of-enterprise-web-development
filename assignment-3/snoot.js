/* JavaScript 6th Edition
* Chapter 6
* Chapter Case

* Snoot Flowers
* Functions
* Author: Ashley Skala
* Date: 4/6/16

* Filename: snoot.js
*/

"use strict"; //interpret document contents in JavaScript strict mode

/*global variables*/
var twentyNine = document.createDocumentFragment();
var thirty = document.createDocumentFragment();
var thirtyOne = document.createDocumentFragment();
var formValidity = true;

/* remove default values and formatting from state and delivery date selection lists */
function removeSelectDefaults() {
	var emptyBoxes = document.getElementsByTagName("select");
	for (var i = 0; i < emptyBoxes.length; i++) {
	emptyBoxes[i].selectedIndex = -1;	
	}
}

/*remove fallback placeholder text */
function zeroPlaceholder() {
	var messageBox = document.getElementById("customText");
	messageBox.style.color = "black";
	if (messageBox.value === messageBox.placeholder) {
	messageBox.value = "";
	}
}
/*restore placeholder text if box contains no user entry */
function checkPlaceholder() {
	var messageBox = document.getElementById("customText");
	if (messageBox.value === "") {
		messageBox.style.color = "rgb(178,184,183)";
		messageBox.value = messageBox.placeholder;
	}
}


function generatePlaceHolder() {
	if (Modernizr.input.placeholder) {
		var messageBox = document.getElementById("customText");
		messageBox.value = messageBox.placeholder;
		messageBox.style.color = "rgb(178,184,183)";	
		if (messageBox.addEventListener) {
			messageBox.addEventListener("focus", zeroPlaceholder, false);
			messageBox.addEventListener("blur", checkPlaceholder, false);
		}else if (messageBox.attachEvent) {
			messageBox.attachEvent("onfocus", zeroPlaceholder);
			messageBox.attachEvent("onblur", checkPlaceholder);
		}
	}
}

/*automatically check custom message check box if user makes entry in customText box */
function autocheckCustom () {
	var messageBox = document.getElementById("customText");
	if (messageBox.value !== "" && messageBox.value !== messageBox.placeholder) {
		//if user entry in textarea, check Custom check box
		document.getElementById("custom").checked = "checked";
	}
}

/*copy values for Billing Address fields to Delivery Address fields */
function copyBillingAddress() {
	var billingInputElements = document.querySelectorAll("#billingAddress input");
	var deliveryInputElements = document.querySelectorAll("#deliveryAddress input");
	if (document.getElementById("sameAddr").checked) {
		for (var i = 0; i < billingInputElements.length; i++) {
			deliveryInputElements[i + 1].value = billingInputElements[i].value;
		}
		document.querySelector("#deliveryAddress select").value = document.querySelector("#billingAddress select").value;
	}
	else { 
		for (var i = 0; i < billingInputElements.length; i++) {
			deliveryInputElements[i + 1].value = "";
		}
		document.querySelector("#deliveryAddress select").selectedIndex = -1;
	}
}

/*validate address fieldsets*/
function validateAddress(fieldsetId) {
	var inputElements = document.querySelectorAll("#" + fieldsetId + " input");
	var errorDiv = document.querySelectorAll("#" + fieldsetId + " .errorMessage")[0];
	var fieldsetValidity = true;
	var elementCount = inputElements.length;
	var currentElement;
	try {
		for (var i = 0; i < elementCount; i++) {
			//validate all input elements in fieldset
			currentElement = inputElements[i];
			if (currentElement.value === "") {
				currentElement.style.background = "rgb(255,233,233)";
				fieldsetValidity = false;
			} else {
				currentElement.style.background = "white";
			}
		}
		currentElement = document.querySelector("#" + fieldsetId + " select");
		//validate state select element
		if (currentElement.selectedIndex === -1) {
			currentElement.style.border = "1px solid red";
			fieldsetValidity = false;
		} else {
			currentElement.style.border = "";
		}
		if (fieldsetValidity === false) {
			//throw appropriate message based on current fieldset
			if (fieldsetId === "billingAddress") {
				throw "Please complete all Billing Address information.";
			} else {
				throw "Please complete all Delivery Address information.";
			}
		} else {
			errorDiv.styel.display = "none";
			errorDiv.innerHTML = "";
		}
	}
	catch(msg) {
		errorDiv.style.display = "block";
		errorDiv.innerHTML = msg;
		formValidity = false;
	}
}

/* validate payment fieldset */
function validatePayment () {
	var errorDiv = document.querySelector("#paymentInfo .errorMessage");
	var fieldsetValidity = true;
	var ccNumElement = document.getElementById("ccNum");
	var selectElements = document.querySelectorAll("#paymentInfo select");
	var elementCount = selectElements.length;
	var cvvElement = document.getElementById("cvv");
	var cards = document.getElementsByName("PaymentType");
	var currentElement;
	try {
		if (!cards[0].checked && !cards[1].checked && !cards[2].checked && !cards[3].checked) {
			//verify that a card is selected
			for (i = 0; i < 4; i++) {
				cards[i].style.outline = "1px solid red";
			}
			fieldsetValidity = false;
		} else {
			for (i = 0; i < 4; i++) {
				cards[i].style.outline = "";
			}
		}
		if (ccNumElement.value === "") {
			//verify that a card number has been entered 
			ccNumElement.style.background = "rgb(255,233,233)";
			fieldsetValidity = false;
		} else {
			ccNumElement.style.background = "white";
		}
		for (var i = 0; i < elementCount; i++) {
			//verify that a month and year have been selected
			currentElement = selectElements[i];
			if (currentElement.selectedIndex === -1) {
				currentElement.style.border = "1px solid red";
				fieldsetValidity = false;
			} else {
				currentElement.style.border = "";
			}
		}
		if (cvvElement.value === "") {
			//verify that a cvv value has been entered
			cvvElement.style.background = "rgb(255,233,233)";
			fieldsetValidity = false;
		} else {
			cvvElement.style.background = "white";
		}
		if (!fieldsetValidity) { //check if any field is blank
		throw "Please complete all payment information.";
		} else {
			errorDiv.style.display = "none";
		}
	}
	catch(msg) {
		errorDiv.style.display = "block";
		errorDiv.innerHTML = msg;
		formValidity = false;
	}
}

/*validate message fieldset*/
function validateMessage () {
	var errorDiv = document.querySelector("#message .errorMessage");
	var msgBox = document.getElementById("customText");
try {
	if (document.getElementById("custom").checked && ((msgBox.value === "") || (msgBox.value === msgBox.placeholder))) {
		throw "Please enter your message text.";
	} else {
		errorDiv.style.display = "none";
		msgBox.style.background = "white";
	}
}
	catch(msg) {
		errorDiv.style.display = "block";
		errorDiv.innerHTML = msg;
		msgBox.style.background = "rgb(255,233,233)";
		formValidity = false;
	}
}

/*validate create account fieldset */
function validateCreateAccount() {
	var errorDiv = document.querySelector("#createAccount .errorMessage");
	var usernameElement = document.getElementById("username");
	var pass1Element = document.getElementById("pass1");
	var pass2Element = document.getElementById("pass2");
	var passwordMismatch = false;
	var invColor = "rgb(255,233,233)";
try { 
//reset styles to valid state
usernameElement.style.background = "";
pass1Element.style.background = "";
pass2Element.style.background = "";
errorDiv.style.display = "none";
if ((usernameElement.value !== "" && pass1Element.value !== "" && pass2Element.value !== "")) {
	//all fields are filled
	if (pass1Element.value !== pass2Element.value) {
		//passwords don't match
		passwordMismatch = true;
		throw "Passwords entered do not match; please reenter.";
	}
}
if (!(usernameElement.value === "" && pass1Element.value === "" && pass2Element.value === "")) {
	//not all fields are blank
	throw "Please complete all fields to create an account.";
	}
}
catch(msg) {
	errorDiv.innerHTML = msg;
	errorDiv.style.display = "block";
	if (passwordMismatch) {
		usernameElement.style.background = "";
		pass1Element.style.background = invColor;
		pass2Element.style.background = invColor;
	} else {
		if (usernameElement.value === "") {
			usernameElement.style.background = invColor;
		}
		if (pass1Element.value === "") {
			pass1Element.style.background = invColor;
		}
		if (pass2Element.value === "") {
			pass2Element.style.background = invColor;
		}
	}
	formValidity = false;
}
}

/*validate number fields for older browsers */
function validateNumbers() {
	var ccNotNum;
	var cvvNotNum;
	var ccNumElement = document.getElementById("ccNum");
	var cvvElement = document.getElementById("cvv");
	var ccNumErrMsg = document.getElementById("ccNumErrorMessage");
	var cvvErrMsg = document.getElementById("cvvErrorMessage");
try {
	if (isNaN(ccNumElement.value) || ccNumElement.value === "") {
		ccNotNum = true;
	} else {
		ccNumElement.style.background = "";
		ccNumErrMsg.style.display = "none";
	}
	if (isNaN(cvvElement.value) || cvvElement.value === "") {
		cvvNotNum = true;
	} else { //cvv value is a number
	cvvElement.style.background = "";
	cvvErrMsg.style.display = "none";
	}
	if (ccNotNum || cvvNotNum) {
		throw "must contain numbers only.";
	}
}
	catch(msg) {
		if (ccNotNum) {
			ccNumElement.style.background = "rgd(255,233,233)";
			ccNumErrMsg.style.display = "block";
			ccNumErrMsg.innerHTML = "The card number " + msg;
		}
		if (cvvNotNum) {
			cvvElement.style.background = "rgd(255,233,233)";
			cvvErrMsg.style.display = "block";
			cvvErrMsg.innerHTML = "The cvv number " + msg;
		}
		formValidity = false;
	}
}

/*validate form */
function validateForm(evt) {
	if (evt.preventDefault) {
		evt.preventDefault(); //prevent form from submitting
	} else {
		evt.returnValue = false; //prevent form from submitting in IE8
	}
	formValidity = true; //reset value for revalidation
	validateAddress("billingAddress");
	validateAddress("deliveryAddress");
	validateDeliveryDate();
	validatePayment();
	validateMessage();
	validateCreateAccount();
	validateNumbers();
	if (formValidity === true) {
		document.getElementById("errorText").innerHTML = "";
		document.getElementById("errorText").style.display = "none";
		document.getElementsByTagName("form")[0].submit();
	} else {
		document.getElementById("errorText").innerHTML = "Please fix the indicated problems and then resubmit your order.";
		document.getElementById("errorText").style.display = "block";
		scroll (0,0);
	}
}


/*create event listeners*/
function createEventListeners() {
	var messageBox = document.getElementById("customText");
	if (messageBox.addEventListener) {
		messageBox.addEventListener("blur", autocheckCustom, false);
	}else if (messageBox.attachEvent) {
		messageBox.attachEvent("onblur", autocheckCustom);
	}
	var same = document.getElementById("sameAddr");
	if (same.addEventListener) {
		same.addEventListener("click", copyBillingAddress, false);
	}else if (same.attachEvent) {
		same.attachEvent("onclick", copyBillingAddress);
	}
	var form = document.getElementsByTagName("form") [0];
	if (form.addEventListener) {
		form.addEventListener("submit", validateForm, false);
	}else if (form.attachEvent) {
		form.attachEvent("onsubmit", validateForm);
	}
}

/* run initial form configuration functions */
function setUpPage() {
	removeSelectDefaults();
	createEventListeners();
	generatePlaceHolder();
}

/*run setup function when page finishes loading */
if (window.addEventListener) {
	window.addEventListener("load", setUpPage, false);
} else if (window.attachEvent) {
	window.attachEvent("onload", setUpPage);
}