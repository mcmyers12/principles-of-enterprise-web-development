$(document).ready(function(){
 	
 	(function() {
		var aboutMeText1 = document.getElementById("about-me-text-1");
		var aboutMeText2 = document.getElementById("about-me-text-2");
		var myAddress = document.getElementById("my-address");
        var myPhoneNumber = document.getElementById("my-phone-number");
        var myEmail = document.getElementById("my-email");
    
        function getData(dataUrl) {
			var xhr = new XMLHttpRequest();
			xhr.overrideMimeType("application/json");
			xhr.open('GET', dataUrl, true);

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {						
						var jsonData = JSON.parse(xhr.responseText);
						
						aboutMeText1.innerHTML += jsonData["about-me-text-1"];
						aboutMeText2.innerHTML += jsonData["about-me-text-2"];
						myAddress.innerHTML += jsonData["my-address"];
						myPhoneNumber.innerHTML += jsonData["my-phone-number"];
						myEmail.innerHTML += jsonData["my-email"];
						
					} else {
						console.log(xhr.statusText);
					}
				}
			}
			xhr.send();
		}
		
        getData('data/pet-sitting-site-data.json');
    })();
    
    /* Set up bootstrap datepicker */
    $(document).ready(function(){
      var date_input=$('input[name="date"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
    
    $(".login-item").hide();
    
    $("#login-button").click(function(){ $(".login-item").show();
    });
    
});
/*
window.onload = function() {


	(function() {
		var donateButton = document.getElementById('donate-button');
		var donationAddress = document.getElementById('donation-address');
		var donateFormContainer = document.getElementById('donate-form-container');
		var customAmount = document.getElementById('customAmount');
		var donateForm = document.forms['_xclick'];
		var donateLaterLink = document.getElementById('donate-later-link');
		var statesList = document.getElementById('state');
		var checkedInd = 2;
		
		function loadData(dataUrl, rootElement, target) {
			var xhr = new XMLHttpRequest();
			xhr.overrideMimeType("application/json");
			xhr.open('GET', dataUrl, true);

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						
						//parse jsoon data
						var jsonData = JSON.parse(xhr.responseText);
						
						var optionsHTML = ''
						for(var i= 0; i < jsonData[rootElement].length; i++){
							optionsHTML+='<option value="'+jsonData[rootElement][i].code+'">'+jsonData[rootElement][i].name+'</option>'
						}
						
						var targetCurrentHtml = target.innerHTML;
						target.innerHTML = targetCurrentHtml + optionsHTML;
						
					} else {
						console.log(xhr.statusText);
						
						// Show the error on the Web page
                        tempContainer.innerHTML += '<p class="error">Error getting ' + 
                                      target.name + ": "+ xhr.statusText + ",code: "+ xhr.status + "</p>";
					}
				}
			}
			xhr.send();
		}
		
		loadData('data/us-states.json', 'usstateslist', statesList);

		loadData('data/countries.json', 'countrieslist', counriesList);
		
		
		function showDonationForm() {		
			donationAddress.style.display = "none";
			donateFormContainer.style.display = "block";
		}
		donateButton.addEventListener('click', showDonationForm, false);
		
		function defaultAmountSelected() {
			for (var i = 0; i < donateForm.length; i++) {
				if (donateForm[i].type == 'radio') {
					donateForm[i].onclick = function() {
						customAmount.value = '';
					}
				}				
			}
		}
		defaultAmountSelected();
					
		//uncheck selected radio buttons if custom amount was choosen
		function onCustomAmountFocus() {
			for (var i = 0; i < donateForm.length; i++) {
				if (donateForm[i].type == 'radio' && donateForm[i].checked == true) {
					checkedInd = i;
					donateForm[i].checked = false;
				}
			}
		}
		customAmount.addEventListener('focus', onCustomAmountFocus, false);
				
		function onCustomAmountBlur() {
			var value = customAmount.value;
			if (value == '') {
				donateForm[checkedInd].checked = true;
			}
		}
		customAmount.addEventListener('blur', onCustomAmountBlur, false);
		
		function donateLater(){
			donationAddress.style.display = "block";
			donateFormContainer.style.display = "none";
		}
		donateLaterLink.addEventListener('click', donateLater, false);
		
	})();
 

}
*/