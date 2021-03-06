/**
 * Global Page level JavaScript
 * Group: 2
 * @Author Aaron Burke
 */

if (typeof String.prototype.startsWith != 'function') {
    String.prototype.startsWith = function(prefix) {
        return this.slice(0, prefix.length) == prefix;
    };
}
 
if (typeof String.prototype.endsWith != 'function') {
    String.prototype.endsWith = function(suffix) {
        return this.slice(-suffix.length) == suffix;
    };
}

function GetInboundLanguage(queryString)
{
	// break apart incoming location.search variable
	var returnLang;
	if (currentLang) 
	{
		returnLang = currentLang; // default
	}
	var strQueryArray = queryString.slice(1).split('&');
	
	for (var i=0; i < strQueryArray.length; i++) 
	{
		var tempLang = doDecode(strQueryArray[i].split('=')[1]);
		if ((tempLang) && (tempLang !== ''))
		{
			returnLang = tempLang;
		}
	}
	return returnLang;
}

function doDecode(inObject) 
{
	var outObject = null;
    if (inObject) 
    {
        // decode URI and change + to spaces
    	outObject = decodeURIComponent((inObject.replace(/\+/g, '%20')));
    }
    return outObject;
}


function GetLanguageFromQueryString()
{
	var success = false;
	if (location) 
	{
		var queryString = location.search;
		
		if (queryString) 
		{
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
			success = true;
		}
	}
	return success;
}

function PopulatePageDropDownSettings()
{
	var selectBox;
	var msgString = "Adding countries to country select tag";
	logToConsole(msgString, true)
	for (var i=0; i < countryCodes.length-1; i++) 
	{
		selectBox = document.getElementById('country');
		SetCountryOption(selectBox, i);
	}
	
	msgString = "Adding languages to primaryLang and secondaryLang select tags";
	logToConsole(msgString, true)	
	for (var key in languageCodes) 
	{
		selectBox = document.getElementById('primaryLang');
		SetLangOption(selectBox, key);
		
		selectBox = document.getElementById('secondaryLang');
		SetLangOption(selectBox, key);
	}
	
}

function SetLangOption(selectBox, key)
{
    var newOption = document.createElement('option');
    newOption.innerHTML = languageCodes[key];
    newOption.value = key;
    selectBox.appendChild(newOption);
}

function SetCountryOption(selectBox, counter)
{
    var newOption = document.createElement('option');
    newOption.innerHTML = countryCodes[counter].name;
    newOption.value = countryCodes[counter].code;
    selectBox.appendChild(newOption);
}

function SetLangScripts(i)
{
    var head = document.getElementsByTagName("head")[0];
    var sourcesPath = CurrentLangDirs[i].dir;
    var scriptId = CurrentLangDirs[i].arrayName+'LangArray';
    
    sourcesPath = 'lang/' + sourcesPath.replace("_", "/") + '/LangJSON.js';
  
    var oldScript = document.getElementById(scriptId);
	if (oldScript) 
	{
		// Already exists delete
		oldScript.parentNode.removeChild(oldScript);
	}
	
	var newScript = document.createElement('script'); // New - Create
	newScript.id = scriptId;
	newScript.src = sourcesPath;
	head.appendChild(newScript);
}

function logToConsole(msgString, debugFlag)
{
	if (debugFlag)
	{
		if (msgString)
		{
			console.log(msgString);
		}
	}
}
