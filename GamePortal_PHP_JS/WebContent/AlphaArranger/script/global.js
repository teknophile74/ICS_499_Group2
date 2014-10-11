/**
 * Global Page level JavaScript
 * Group: 2
 * @Author Aaron Burke
 */

function GetInboundLanguage(queryString)
{ 	
	// break apart incoming location.search variable
	var returnLang;
	if (currentLang) {
		returnLang = currentLang; // default
	}
	var strQueryArray = queryString.slice(1).split('&');
	
	for (var i=0; i < strQueryArray.length; i++) {
		var tempLang = doDecode(strQueryArray[i].split('=')[1]);
		if ((tempLang) && (tempLang !== ''))
		{
			returnLang = tempLang;
		}
	}
	return returnLang;
}

function doDecode(object)
{
	if (object) {
		// decode URI and change + to spaces
		object = decodeURIComponent((object.replace(/\+/g, '%20')));
	}
	return object;
}

function GetLanguageFromQueryString()
{
	var success = false;
	var queryString = location.search;
	if (queryString) {
		var userChosenLang = GetInboundLanguage(queryString);
		// Get languageSelector object
		var selectBox = document.getElementById("Lang");
		// Iterate nodes in the select tag
		for(var i=0; i < selectBox.length-1; i++)
		{
		   if (userChosenLang == selectBox.options[i].text)
		   {
			   selectBox.selectedIndex = i;
		   }
		}
		currentLang = userChosenLang;
	}
	return success;
}

function PopulatePageLanguageSettings()
{
	var selectBox = document.getElementById('Lang');
	for (var i=0; i < CurrentLangDirs.length; i++) 
	{
	    SetOption(selectBox, i);
	    SetLangScripts(i);
	}
}

function SetOption(selectBox, i)
{
    var newOption = document.createElement('option');
    
    newOption.id = CurrentLangDirs[i].dir;
    newOption.innerHTML = CurrentLangDirs[i].arrayName;
    newOption.value = CurrentLangDirs[i].arrayName;
    selectBox.appendChild(newOption);
}

function SetLangScripts(i)
{
    var head = document.getElementsByTagName("head")[0];
    var sourcesPath = CurrentLangDirs[i].dir;
    var scriptId = CurrentLangDirs[i].arrayName+'LangArray';
    
    sourcesPath = 'lang/' + sourcesPath.replace("_", "/") + '/LangJSON.js';
  
    var oldScript = document.getElementById(scriptId);
	if (oldScript) {
		// Already exists delete
		oldScript.parentNode.removeChild(oldScript);
	}
	
	var newScript = document.createElement('script'); // New - Create
	newScript.id = scriptId;
	newScript.src = sourcesPath;
	head.appendChild(newScript);
}
