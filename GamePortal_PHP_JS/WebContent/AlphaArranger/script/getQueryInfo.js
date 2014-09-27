function getLanguageFromQueryString()
{
	var success = false;
	var queryString = location.search;
	if (queryString) {
		var userChosenLang = getInboundLanguage(queryString);
		// Get languageSelector object
		var languageSelector = document.getElementById("Lang");
		// Iterate nodes in form elements array
		languageSelector.value = userChosenLang;
		puzzle.writePuzzle();
	}
	return success;
}

function doDecode(object)
{
	if (object) {
		// decode URI and change + to spaces
		object = decodeURIComponent((object.replace(/\+/g, '%20')));
	}
	return object;
}

function getInboundLanguage(queryString){ 	
	// break apart incoming location.search variable
	var returnLang = "en"; // default
	var strQueryArray = queryString.slice(1).split('&');
	
	for (var j=0; j < strQueryArray.length; j++) {
		var tempLang = doDecode(strQueryArray[j].split('=')[1]);
		if ((tempLang) && !(tempLang == '')) {
			returnLang = tempLang;
		}
	}
	
	return returnLang;
}
