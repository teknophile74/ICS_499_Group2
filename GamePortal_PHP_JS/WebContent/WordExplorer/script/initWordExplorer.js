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
}

function initializeWordExplorer(inboundCatArray) 
{
	var i;
	
	/*
	this.width=puzzleWidth;
	this.height=puzzleHeight;	
	//this.emptyVal=0;
	this.emptyVal="";
	this.pieces=[];
	this.fields=[];
	this.name=aName;
	this.moveCounter=0;
	*/
	this.wordArray=[];
	
	function SetImageAttr(imgElemObj,srcStr,altStr)
	{
		var arrowName = arguments[3];
		var setArrowImg = arguments[4];
		if (imgElemObj)
		{
			if ((srcStr) && (altStr))
			{
				if (setArrowImg)
				{
					if (arrowName == 'previous')
					{
						imgElemObj.setAttribute('src','../images/prevArrow.png');
					}
					else
					{
						imgElemObj.setAttribute('src','../images/nextArrow.png');
					}
					imgElemObj.setAttribute('alt',arrowName);	
				}
				else 
				{
					imgElemObj.setAttribute('src',srcStr);
					imgElemObj.setAttribute('alt',altStr);					
				}
			}
		}
	}

	function SetAnchorProperties(aElemObj,classStr,hrefStr)
	{
		var aStr = arguments[3];
		if (aElemObj)
		{
			if ((classStr) && (hrefStr))
			{
				aElemObj.setAttribute('class',classStr);
				aElemObj.setAttribute('href','#'+hrefStr);
			}
			if (aStr)
			{
				aElemObj.textContent = aStr;
			}
		}
	}
	
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
			SetImageAttr(prevLinkImg,('srcPlaceHolder'+id),('altPlaceHolder'+id),'previous',addArrowNav);
		}
		else
		{
			SetImageAttr(prevLinkImg,('srcPlaceHolder'+id),('altPlaceHolder'+id));
		}
		prevLink.appendChild(prevLinkImg);
		
		parentDivElement.appendChild(prevLink);
	}

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
			SetImageAttr(nextLinkImg,('srcPlaceHolder'+id),('altPlaceHolder'+id),'next',addArrowNav);
		}
		else
		{
			SetImageAttr(nextLinkImg,('srcPlaceHolder'+i),('altPlaceHolder'+id));
		}
		SetImageAttr(nextLinkImg,('srcPlaceHolder'+nextLinkID),('altPlaceHolder'+nextLinkID));
		
		nextLink.appendChild(nextLinkImg);

		parentDivElement.appendChild(nextLink);
	}
	
	function CreateLangDiv(parentDivElement, divElementName, id)
	{
		if (divElementName)
		{
			var newDiv = document.createElement('div');
			newDiv.setAttribute('class',divElementName);
	
			// Create Links with break
			var langLink1 = document.createElement("a");
			var langLink2 = document.createElement("a");
			var breakElement = document.createElement("br");
			
			
			// Update Link with sound?
			if (divElementName === 'nav_prilang')
			{
				SetAnchorProperties(langLink1,'prilangLiteral','',('LiteralWord'+id));
				SetAnchorProperties(langLink2,'prilangTransLit','',('TransLitWord'+id));
			}
			else
			{
				SetAnchorProperties(langLink1,'seclangLiteral','',('LiteralWord'+id));
				SetAnchorProperties(langLink2,'seclangTransLit','',('TransLitWord'+id));				
			}
			
			// attach new elements
			newDiv.appendChild(langLink1);
			newDiv.appendChild(breakElement);
			newDiv.appendChild(langLink2);
			
			parentDivElement.appendChild(newDiv);
		}
	}
	
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
	};
	
	this.writeInterface=function()
	{
		var WExPlaceHolder = document.getElementById('gallery');
		var backgroundArrows = true;
    
    //TODO set value for backgroundArrows from config file
		
    if (WExPlaceHolder)
		{
			if (inboundCatArray)
			{
				// remove game from page if it already exists
				this.cleanGame();
				
				var newGUI = document.createElement('div');
				newGUI.setAttribute('id','fullsize');
				
				this.wordArray = inboundCatArray.slice(0);
		
				// Build div array interface
				for(i=0; i<=this.wordArray.length-1; ++i)
				{
					var currentDivID,nextDivID;
					currentDivID=i;
					// Check for first array value to create loop
					var prevDivID = (i===0) ? (this.wordArray.length-1) : (i-1);
					// Check for end value to look back to zero
					var nextDivID = (i===this.wordArray.length-1) ? (0) : (i+1);
					
					var newDivElement = document.createElement('div');
					var newElementID = "word"+currentDivID;
					
					newDivElement.setAttribute('id',newElementID);

					// Create Main display image
					var mainWordDisplay = document.createElement("img");
					//TODO: Set img values to array info
					SetImageAttr(mainWordDisplay,('srcPlaceHolder'+currentDivID),('altPlaceHolder'+currentDivID));
					newDivElement.appendChild(mainWordDisplay);
					
					CreatePrevLink(newDivElement,prevDivID);
					
					CreateNextLink(newDivElement,nextDivID);
					
					if (backgroundArrows === true)
					{
						CreatePrevLink(newDivElement,prevDivID,true);
						
						CreateNextLink(newDivElement,nextDivID,true);
					}
					// Create Primary Language Links
					CreateLangDiv(newDivElement,'nav_prilang',prevDivID);
					
					// Create Secondary Language Links
					CreateLangDiv(newDivElement,'nav_seclang',nextDivID);
					
					newGUI.appendChild(newDivElement);
				}
				// add the new elements to the interface
				WExPlaceHolder.appendChild(newGUI);
			}
		}
	};
}