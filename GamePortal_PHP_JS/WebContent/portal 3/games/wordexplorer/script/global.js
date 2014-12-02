/**
 * Global Page level JavaScript
 * Group: 2
 * @Author Aaron Burke
 */

/*function playSoundAudio()
{
	var play= document.getElementByClass("prilangTransLit");
	play.playSoundAudio();
}

function imageInfo()
{
	var imageInfo = document.createElement("prilangLiteral").target="_blank";
	//var urlForSoundLink = window.open("prilangLiteral", "_blank");
}
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

function doDecode(object)
{
	if (object) 
	{
		// decode URI and change + to spaces
		object = decodeURIComponent((object.replace(/\+/g, '%20')));
	}
	return object;
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

	selectBox = document.getElementById('primaryLang');
	for (var i=0; i < CurrentLangDirs.length; i++) 
	{
		SetLangOption(selectBox, 'PrimaryLang', i);
	}
	
	selectBox = document.getElementById('secondaryLang');
	for (var i=0; i < CurrentLangDirs.length; i++) 
	{
		SetLangOption(selectBox, 'SecondaryLang', i);
	}
	
	selectBox = document.getElementById('categories');
	for (var i=0; i < CategoryList.length; i++) 
	{
	    SetCatOption(selectBox, i);
	    SetCatScripts(i);
	}
}

function SetCatOption(selectBox, i)
{
    var newOption = document.createElement('option');
    
    newOption.id = CategoryList[i].id;
    newOption.innerHTML = CategoryList[i].category;
    newOption.value = CategoryList[i].category;
    selectBox.appendChild(newOption);
}

function SetLangOption(selectBox, currentLang, i)
{
    var newOption = document.createElement('option');
    var strCatArray = CurrentLangDirs[i];     // Replace stupidCatArray with strCatArray
    
    newOption.id = CurrentLangDirs[i].dir;
    newOption.innerHTML = strCatArray[currentLang];
    newOption.value = strCatArray[currentLang];
    selectBox.appendChild(newOption);
}

function SetCatScripts(i)
{
    var head = document.getElementsByTagName("head")[0];
    var sourcesPath = CurrentLangDirs[0].dir;
    
    var currentCatValue = ((CategoryList[i].category).toLowerCase()).replace(" ", "_");
    
    var scriptId = CurrentLangDirs[0].PrimaryLang+CurrentLangDirs[0].SecondaryLang+'_Cat_'+currentCatValue;
  //  var scriptId = CurrentLangDirs[0].SecondaryLang+CurrentLangDirs[0].PrimaryLang+'_Cat_'+currentCatValue;
    
    sourcesPath = 'lang/' + sourcesPath.replace(/_+/g, "/") + '/' + currentCatValue + '.js';
  
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