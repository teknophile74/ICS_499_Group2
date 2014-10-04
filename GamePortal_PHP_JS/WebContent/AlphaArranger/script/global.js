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
		SetCurrentlyLoadedLangArray()
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

function populateLanguageDropDown()
{
	var i = 0;
	for (var dir in CurrentLangDirs) 
	{
        var selectBox = document.getElementById('Lang');
        var newOption = document.createElement('option');
        newOption.id = CurrentLangDirs[i].dir;
        newOption.innerHTML = CurrentLangDirs[i].arrayName;
        newOption.value = (CurrentLangDirs[i].dir).split('_')[1];
        //if(i==0) { newOption.setAttribute('selected','selected'); }
        selectBox.appendChild(newOption);
        ++i;
	}
}

function SetCurrentlyLoadedLangArray()
{
    var cssWork;
    var selectBox = document.getElementById('Lang');
    var sourcesPath = selectBox.options[selectBox.selectedIndex].id;
    sourcesPath = sourcesPath.replace("_", "/");
    
	if (document.getElementById('langArray')) {
		cssWork = document.getElementById('langArray'); // Already exists update
		cssWork.src = 'lang/' + sourcesPath + '/LangJSON.js';
		cssWork.onload = scriptLoaded;
	}
	else {
		cssWork = document.createElement('script'); // New - Create
		cssWork.id = 'langArray';
		cssWork.src = 'lang/' + sourcesPath + '/LangJSON.js';
		cssWork.onload = scriptLoaded;
		document.head.appendChild(cssWork);
	}
}

function scriptLoaded() {
	charArray = (eval(selectBox.options[selectBox.selectedIndex].text)).slice(0);
}