

function GetLanguageFromQueryString()
{
	var success = false;
	var queryString = location.search;
	if (queryString) {
		var userChosenLang = GetInboundLanguage(queryString);
		// Get languageSelector object
		var languageSelector = document.getElementById("Lang");
		// Iterate nodes in the select tag
		for(var x=0;x < languageSelector.length -1 ; x++)
		{
		   if(userChosenLang == languageSelector.options[x].text)
		 
			   languageSelector.selectedIndex = x;
		}
		currentLang = userChosenLang;
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

function GetInboundLanguage(queryString){ 	
	// break apart incoming location.search variable
	var returnLang = currentLang; // default
	var strQueryArray = queryString.slice(1).split('&');
	
	for (var j=0; j < strQueryArray.length; j++) {
		var tempLang = doDecode(strQueryArray[j].split('=')[1]);
		if ((tempLang) && !(tempLang == '')) {
			returnLang = tempLang;
		}
	}
	return returnLang;
}

function PopulatePageLanguageSettings()
{
	var i = 0;
	var selectBox = document.getElementById('Lang');
	
	for (var language in CurrentLangDirs) 
	{
		SetOption(selectBox, language, i);
		SetLangScripts(selectBox, language, i);
        ++i;
	}
}

function SetOption(selectBox, language, i){
    
    var newOption = document.createElement('option');
    
    newOption.id = CurrentLangDirs[i].dir;
    newOption.innerHTML = CurrentLangDirs[i].arrayName;
    newOption.value = CurrentLangDirs[i].arrayName;
    selectBox.appendChild(newOption);
}

function SetLangScripts(selectBox, language, i)
{
    var head = document.getElementsByTagName("head")[0];
    var sourcesPath = CurrentLangDirs[i].dir;
    var scriptId = CurrentLangDirs[i].arrayName+'LangArray'
    
    sourcesPath = 'lang/' + sourcesPath.replace("_", "/") + '/LangJSON.js';
    
	if (document.getElementById(scriptId)) {
		eval("var old = document.getElementById(scriptId)"); // Already exists delete
		old.parentNode.removeChild(old);
		delete old;
	}
	
	var script = document.createElement('script'); // New - Create
	script.id = scriptId;
	script.src = sourcesPath;
	head.appendChild(script);
}
