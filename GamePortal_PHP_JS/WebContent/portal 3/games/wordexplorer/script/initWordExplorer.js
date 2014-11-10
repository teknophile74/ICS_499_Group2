/**
 * initializes Word Explorer page dynamically based on JSON content.
 * 2014/10/23 - Aaron Burke
 * ICS499 - Group 2
 */

var __debugFlag = true;
var msgStr;

function RemoveLocalChildElement(parentName, childName)
{
	var retVal = false;
	if ((parentName) && (childName))
	{
		var parentElement = document.getElementById(parentName);
		var element = document.getElementById(childName);
		if (element) {
			parentElement.removeChild(element);
			retVal = true;
		}
	}
	return retVal;
}  //function RemoveLocalChildElement(parentName, childName)

function initializeWordExplorer(inboundCatArray) 
{
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
	/*
	 * This function takes inthe word array and writes out 
	 * the new interface for the user to play
	 */
	var i;
	var wordArray = (inboundCatArray).slice(0);
	
	function SetElemObjClsOrIdName(imgElemObj,updateStr,classoridStr)
	{
		if (imgElemObj)
		{
			if ((classoridStr) && (updateStr))
			{
				imgElemObj.setAttribute(classoridStr,updateStr);
			}
			else
			{
				// TODO Write out error message for not including both values
			}
		}
	} // End SetElemObjClsOrIdName(imgElemObj,classStr,idStr)
	
	function SetImageAttr(imgElemObj,srcStr,altStr)
	{
		if (imgElemObj)
		{
			if ((srcStr) && (altStr))
			{
				imgElemObj.setAttribute('src',srcStr);
				imgElemObj.setAttribute('alt',altStr);					
			}
		}
	} // End SetImageAttr(imgElemObj,srcStr,altStr)

	function SetAnchorProperties(aElemObj,classStr,hrefStr)
	{
		var aStr = arguments[3];
		if (aElemObj)
		{
			if ((classStr) && (hrefStr))
			{
				aElemObj.setAttribute('class',classStr);
				if((hrefStr.startsWith('http://') || hrefStr.startsWith('https://')))
				{
					aElemObj.setAttribute('href', hrefStr);
				} 
				else{
					aElemObj.setAttribute('href', '#'+hrefStr);
				}
			}
		}
			if (aStr)
			{
				aElemObj.textContent = aStr;
			}
	
	} // End SetAnchorProperties(aElemObj,classStr,hrefStr)
	
	function setObjectTagProperties(aElemObj, hrefStr)
	{
			if (aElemObj)
			{
				aElemObj.setAttribute('data', hrefStr);
			}
	} // setObjectTagProperties(aElemObj, hrefStr)
	
	function CreatePrevLink(parentDivElement, id)
	{
		var addArrowNav = arguments[2];
		// Create Previous Link and image tags
		var prevLink = document.createElement("a");
		var prevLinkID = "word"+id;
		var prevLinkImg = document.createElement("img");
		
		SetAnchorProperties(prevLink,'previous',prevLinkID);
		if (addArrowNav)
		{
			SetImageAttr(prevLinkImg,'images/prevArrow.png','previous');
			SetElemObjClsOrIdName(prevLinkImg,'AltNav','class');
		}
		else
		{
			SetImageAttr(prevLinkImg,(inboundCatArray[id].Image),(inboundCatArray[id].PrimLang_word));
		}
		prevLink.appendChild(prevLinkImg);
		
		parentDivElement.appendChild(prevLink);
	} // End CreatePrevLink(parentDivElement, id)

	function CreateNextLink(parentDivElement, id)
	{
		var addArrowNav = arguments[2];
		// Create next Link and image tags			
		var nextLink = document.createElement("a");
		var nextLinkID = "word"+id;
		var nextLinkImg = document.createElement("img");
		
		SetAnchorProperties(nextLink,'next',nextLinkID);
		if (addArrowNav)
		{
			SetImageAttr(nextLinkImg,'images/nextArrow.png','next');
			SetElemObjClsOrIdName(nextLinkImg,'AltNav','class');
		}
		else
		{
			SetImageAttr(nextLinkImg,(inboundCatArray[id].Image),(inboundCatArray[id].PrimLang_word));
		}		
		nextLink.appendChild(nextLinkImg);

		parentDivElement.appendChild(nextLink);
	} // End CreateNextLink(parentDivElement, id)
	
	function CreateLangDiv(parentDivElement, divElementName, id)
	{
		if (divElementName)
		{
			var LiteralWord = null;
			var TransLitWord = null;
			var urlForSoundLink = null;
			var urlForinfoLink = null;
			var newDiv = document.createElement('div');
			newDiv.setAttribute('class',divElementName);
	
			// Create Links with break
			var langLink1 = document.createElement("a");
			var langLink2 = document.createElement("a");
			var breakElement = document.createElement("br");
			var speakerImgElement = document.createElement("img");
			var soundElement = document.createElement("object");
			
			// TODO: Update Link with sound?
			if (divElementName === 'nav_prilang')
			{
				LiteralWord = inboundCatArray[id].PrimLang_word;
				TransLitWord = inboundCatArray[id].PrimLang_translit;
				urlForinfoLink = inboundCatArray[id].PrimLangInfo;
				urlForSoundLink = inboundCatArray[id].PrimLang_sound_url;

				SetAnchorProperties(langLink1,'prilangLiteral',urlForinfoLink,LiteralWord);
				SetAnchorProperties(langLink2,'prilangTransLit',urlForSoundLink,TransLitWord);
				if((urlForSoundLink.startsWith('http://') || urlForSoundLink.startsWith('https://')))
					if (urlForSoundLink.endsWith('.mp3'))
						{
							//<object data="https://ssl.gstatic.com/dictionary/static/sounds/de/0/grandfather.mp3"></object>
							setObjectTagProperties(soundElement, urlForSoundLink);
						}
					}
				}
//			} // end of function CreateLangDiv(parentDivElement, divElementName, id)
			
			if (divElementName === 'nav_seclang')
			{
				LiteralWord = inboundCatArray[id].SecLang_word;
				TransLitWord = inboundCatArray[id].SecLang_translit;
				urlForinfoLink = '';
				urlForSoundLink = inboundCatArray[id].SecLang_sound_url;
				
				SetAnchorProperties(langLink1,'seclangLiteral',urlForinfoLink,LiteralWord);
				SetAnchorProperties(langLink2,'seclangTransLit',urlForSoundLink,TransLitWord);				
			}
			
			SetImageAttr(speakerImgElement,'images/icon-loud-speaker.png','Speaker Image');
			SetElemObjClsOrIdName(speakerImgElement,'speakerImg','class');

			// attach new elements
			newDiv.appendChild(langLink1);
			newDiv.appendChild(breakElement);
			newDiv.appendChild(langLink2);
			newDiv.appendChild(speakerImgElement);
			
			parentDivElement.appendChild(newDiv);
	//	}
} // End CreateLangDiv(parentDivElement, divElementName, id)
	
	this.cleanGame=function()
	{
		if (!(RemoveLocalChildElement("gallery", "fullsize")))
		{
			msgStr = "Function: cleanGame" +
			"Error encountered while attempting to remove the interface " +
			"element from the gallery parent tag!";
			logToConsole(msgStr, true);
		}
		else
		{
			if (__debugFlag)
				{
					msgStr = "Function: cleanGame" +
					"Child [fullsize] successfully removed from parent [gallery]";
					logToConsole(msgStr, true);
				}
		}
	}; // End this.cleanGame=function()
	
	this.writeInterface=function()
	{
		var WExPlaceHolder = document.getElementById('gallery');
		var backgroundArrows = true;
    
	    //TODO set value for backgroundArrows from config file
			
	    if (WExPlaceHolder)
		{
			if (wordArray)
			{
				// remove game from page if it already exists
				this.cleanGame();
				
				var newGUI = document.createElement('div');
				newGUI.setAttribute('id','fullsize');
		
				// Build div array interface
				for(i=0; i<=wordArray.length-1; ++i)
				{
					var currentDivID,nextDivID,prevDivID;
					currentDivID=i;
					// Check for first array value to create loop
					prevDivID = (i===0) ? (wordArray.length-1) : (i-1);
					// Check for end value to look back to zero
					nextDivID = (i===wordArray.length-1) ? (0) : (i+1);
					
					var newDivElement = document.createElement('div');
					var newElementID = "word"+currentDivID;
					
					newDivElement.setAttribute('id',newElementID);

					// Create Main display image
					var mainWordDisplay = document.createElement("img");
					//TODO: Set img values to array info
					SetImageAttr(mainWordDisplay,(wordArray[currentDivID].Image),(wordArray[currentDivID].PrimLang_word));
					newDivElement.appendChild(mainWordDisplay);
					
					CreatePrevLink(newDivElement,prevDivID);
					
					CreateNextLink(newDivElement,nextDivID);
					
					if (backgroundArrows === true)
					{
						CreatePrevLink(newDivElement,prevDivID,true);
						CreateNextLink(newDivElement,nextDivID,true);
					}
					// Create Primary Language Links
					CreateLangDiv(newDivElement,'nav_prilang',currentDivID);
					
					// Create Secondary Language Links
					CreateLangDiv(newDivElement,'nav_seclang',currentDivID);
					
					newGUI.appendChild(newDivElement);
				}
				// add the new elements to the interface
				WExPlaceHolder.appendChild(newGUI);
			}
		}
	}; // End this.writeInterface=function()
}