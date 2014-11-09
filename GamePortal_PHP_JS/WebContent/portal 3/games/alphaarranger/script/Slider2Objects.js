/**
 * Slider Puzzle JavaScript
 * Group: 2
 * @Author Aaron Burke
 */

var PuzzleBaseConfig = window.PuzzleBaseConfig;
var currentLang=null;
var puzzle=null;
var puzzleName=null;
var useSymbol=null;

function CreatePuzzle(puzzleName, outputStyle, Offset, Lang, Width, Height) 
{
	var useImage = false;
	if (outputStyle === "image") 
	{
		useImage = true;
	}
	puzzle = new initializePuzzle(puzzleName, Width, Height, useImage);
	// Load in the requested alpha array
	loadCharArray(outputStyle, Width, Height, Offset, Lang);
}

function initializePuzzle(aName,puzzleWidth,puzzleHeight,useImage)
{
	var I=0;
	this.width=puzzleWidth;
	this.height=puzzleHeight;	
	//this.emptyVal=0;
	this.emptyVal="";
	this.pieces=[];
	this.fields=[];
	this.name=aName;
	this.moveCounter=0;
	this.useImage=useImage;
	this.writePuzzle=function()
	{
		if (document.getElementById("gameHolder")){
			var gamePlaceHolder = document.getElementById("gameHolder");
			// remove game from page if it already exists
			
			var HTML= document.createElement('div');
			HTML.setAttribute('id',"game");
			var tbl = document.createElement("table");
			var tblBody = document.createElement("tbody");
			
			var tempArray=this.pieces.slice(0+this.Offset);
			// Randomly Fill fields array with contents of pieces array
			for(I=0;I<(this.width*this.height);I++)
			{
				var P=Math.floor(Math.random()*(tempArray.length-I));
				this.fields[I]=tempArray[P];
				for(var J=P;J<tempArray.length-1;J++)
				{
					tempArray[J]=tempArray[J+1];
				}				
			}
	
			// Build Table
			for(I=0;I<this.height;I++)
			{
				var row = document.createElement("tr");
				for(J=0;J<this.width;J++)
				{
					var cell = document.createElement("td");
					var cellText = null;
					cell.setAttribute('id',(I*this.width+J));
					//var eventListernerString = this.name+'.move('+I+','+J+')';
					cell.addEventListener('click', function(){
						(this.name+'.move('+I+','+J+')');
					});
					cell.setAttribute('onclick', (this.name+'.move('+I+','+J+')'));
					// if empty cell appoint special class and empty node
					if(this.emptyVal==this.fields[I*this.width+J])
					{
						cell.setAttribute('class','graysq');
						cellText = document.createTextNode("");
					}
					else {
						if (this.useImage)
						{
							//cellText = document.createTextNode(this.fields[I*this.width+J]);
							
							cellText = document.createElement("img");
							cellText.setAttribute('src',this.fields[I*this.width+J]);
						}
						else
						{
							cellText = document.createTextNode(this.fields[I*this.width+J]);
						}
					}
					cell.appendChild(cellText);
					row.appendChild(cell);
				}
				tblBody.appendChild(row);
			}
			// put the <tbody> in the <table>
			tbl.appendChild(tblBody);
			HTML.appendChild(tbl);
			gamePlaceHolder.appendChild(HTML);
		}
	};

	this.move=function(aY,aX)
	{
		if(aX<this.width-1)
		{
			if(this.fields[aY*this.width+aX+1]==this.emptyVal)
			{
				this.fields[aY*this.width+aX+1]=this.fields[aY*this.width+aX];
				this.fields[aY*this.width+aX]=this.emptyVal;
				document.getElementById(aY*this.width+aX+1).innerHTML=this.fields[(aY*this.width+aX+1)];
				document.getElementById(aY*this.width+aX).innerHTML='';
				document.getElementById(aY*this.width+aX).setAttribute("class", "graysq");
				document.getElementById(aY*this.width+aX+1).setAttribute("class", "");
				UpdateCounter()
			}
		}
		if(aX>0)
		{
			if(this.fields[aY*this.width+aX-1]==this.emptyVal)
			{
				this.fields[aY*this.width+aX-1]=this.fields[aY*this.width+aX];
				this.fields[aY*this.width+aX]=this.emptyVal;
				document.getElementById(aY*this.width+aX-1).innerHTML=this.fields[(aY*this.width+aX-1)];
				document.getElementById(aY*this.width+aX).innerHTML='';
				document.getElementById(aY*this.width+aX).setAttribute("class", "graysq");
				document.getElementById(aY*this.width+aX-1).setAttribute("class", "");
				UpdateCounter()
			}
		}		
		if(aY>0)
		{
			if(this.fields[(aY-1)*this.width+aX]==this.emptyVal)
			{
				this.fields[(aY-1)*this.width+aX]=this.fields[aY*this.width+aX];
				this.fields[aY*this.width+aX]=this.emptyVal;
				document.getElementById((aY-1)*this.width+aX).innerHTML=this.fields[((aY-1)*this.width+aX)];
				document.getElementById(aY*this.width+aX).innerHTML='';
				document.getElementById(aY*this.width+aX).setAttribute("class", "graysq");
				document.getElementById((aY-1)*this.width+aX).setAttribute("class", "");
				UpdateCounter()
			}
		}
		if(aY<this.height-1)
		{
			if(this.fields[(aY+1)*this.width+aX]==this.emptyVal)
			{
				this.fields[(aY+1)*this.width+aX]=this.fields[aY*this.width+aX];
				this.fields[aY*this.width+aX]=this.emptyVal;
				document.getElementById((aY+1)*this.width+aX).innerHTML=this.fields[((aY+1)*this.width+aX)];
				document.getElementById(aY*this.width+aX).innerHTML='';
				document.getElementById(aY*this.width+aX).setAttribute("class", "graysq");
				document.getElementById((aY+1)*this.width+aX).setAttribute("class", "");
				UpdateCounter()
			}
		}

		// Check for puzzle being solved
		if (IsSolved(this.pieces, this.fields)) {
			alert('Puzzle Solved');
		}
	};

	this.cleanGame=function()
	{
		var gamePlaceHolder = document.getElementById("gameHolder");
		var element = document.getElementById("game");
		if (element) {
			gamePlaceHolder.removeChild(element);
		}
	};
}

function changePuzzleSize(newSize)
{
	var Size = parseInt(newSize);
	var Lang = (document.getElementById("Lang")).value;
	var Offset = parseInt((document.getElementById("Offset")).value);
	changePuzzle(Size, Lang, Offset);
}

function changePuzzleLanguage(newLang)
{
	var Size = parseInt((document.getElementById("Size")).value);
	var Lang = newLang;
	var Offset = parseInt((document.getElementById("Offset")).value);
	changePuzzle(Size, Lang, Offset);
}

function changePuzzleOffset(newOffset)
{
	var Size = parseInt((document.getElementById("Size")).value);
	var Lang = (document.getElementById("Lang")).value;
	var Offset = parseInt(newOffset);
	changePuzzle(Size, Lang, Offset);
}

function changePuzzle(Size, Lang, Offset)
{
	puzzle.cleanGame();
	CreatePuzzle(puzzleName, outputStyle, Offset, Lang, Size, Size);
	ResetCounter();
	puzzle.writePuzzle();
}


function loadCharArray(outputStyle, intWidth, intHeight, intOffset, newLang)
{
	var increment;
	if (intOffset.NaN) {
		intOffset = 0;
	}
	
	if (!(newLang)) {
		var selectBox = document.getElementById('Lang');
		newLang = selectBox.options[selectBox.selectedIndex].text;
	}
	
	if (window[newLang]) {
	
		var charArray = (window[newLang]).slice(0);
		
		var maxChars = (intWidth*intHeight);
		
		for (var i=0; i <= maxChars-1; ++i) {
			if (i > maxChars-2) {
				puzzle.pieces[i]="";
			}
			else {
				// initializePuzzle(puzzle.name, intWidth, intHeight);
				if (i+intOffset > charArray.lenth-1) {
					increment = i+intOffset-(charArray.lenth-1);
				}
				else {
					increment = i+intOffset;
				}
				
				switch(outputStyle) {
				    case "char":
				    	puzzle.pieces[i]=charArray[increment].char;
				        break;
				    case "word":
				    	puzzle.pieces[i]=charArray[increment].word;
				        break;
				    case "image":
				    	puzzle.pieces[i]=charArray[increment].image;
				    	break;
				    default:
				        // default to glyph
				    	puzzle.pieces[i]=charArray[increment].glyph;
				}
			}
		}
	}
}

function IsSolved(pieces, fields) 
{
	// Check for puzzle being solved
	for(var I=0;I<pieces.length;I++)
	{
		if(!(pieces[I]===fields[I])) 
		{
			return false;
		}
	}
	return true;
}


function init()
{
	// set initially needed values
	puzzleName = PuzzleBaseConfig.puzzleName;
	outputStyle = PuzzleBaseConfig.outputStyle;
	currentLang = PuzzleBaseConfig.currentLang;
	
	PopulatePageLanguageSettings();
	if (!(GetLanguageFromQueryString()))
	{
		var selectBox = document.getElementById('Lang');
		for(var i=0; i < selectBox.length; i++)
		{
		   if (currentLang == selectBox.options[i].text)
		   {
			   selectBox.selectedIndex = i;
		   }
		}
	}
	// initial parameters used to create the game
	CreatePuzzle(puzzleName, outputStyle, PuzzleBaseConfig.initialOffset, currentLang, PuzzleBaseConfig.initialWidth, PuzzleBaseConfig.initialHeight);
	ResetCounter();
	puzzle.writePuzzle();
}

function ResetPuzzle()
{
	(document.getElementById("Size")).value = PuzzleBaseConfig.initialWidth;
	(document.getElementById("Offset")).value = PuzzleBaseConfig.initialOffset;
	changePuzzleLanguage((document.getElementById('Lang')).value);
}

function UpdateCounter()
{
	// sent by puzzle movement and sets the counter value for the page
	puzzle.moveCounter++;
	document.getElementById("MoveCounter").innerHTML=puzzle.moveCounter;
}

function ResetCounter()
{
	// Check for puzzle being changed and reset counter value to zero
	puzzle.moveCounter=0;
	document.getElementById("MoveCounter").innerHTML=puzzle.moveCounter;
}
