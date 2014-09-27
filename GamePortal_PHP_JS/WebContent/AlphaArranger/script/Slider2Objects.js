/**
 * Slider Puzzle JavaScript
 * Group: 2
 * @Author Aaron Burke
 */

var puzzleName = 'puzzle';
var useSymbol = true;
var initialWidth = 4;
var initialHeight = 4;
var initialOffset = 0;
var currentLang = "en";
var puzzle=null
createPuzzle();

function createPuzzle() 
{
	puzzle = new initializePuzzle(puzzleName, initialWidth, initialHeight);
	// Load in the requested alpha array
	loadCharArray(useSymbol, initialHeight, initialHeight, initialOffset, currentLang);
}

function initializePuzzle(aName,puzzleWidth,puzzleHeight)
{
	var I=0;
	this.width=puzzleWidth;
	this.height=puzzleHeight;	
	//this.emptyVal=0;
	this.emptyVal="";
	this.pieces=new Array();
	this.fields=new Array();
	this.name=aName;
	this.moveCounter=0;
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
				for(J=P;J<tempArray.length-1;J++)
				{
					tempArray[J]=tempArray[J+1]
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
						cellText = document.createTextNode(this.fields[I*this.width+J]);
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
	}

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
			}
		}

		// Check for puzzle being solved
		if (IsSolved(this.pieces, this.fields)) {
			alert('Puzzle Solved');
		}
		this.moveCounter++;
		document.getElementById("MoveCounter").innerHTML=this.moveCounter;
	}

	this.cleanGame=function()
	{
		var gamePlaceHolder = document.getElementById("gameHolder");
		var element = document.getElementById("game");
		if (element) {
			gamePlaceHolder.removeChild(element);
		}
	}
}

function changePuzzleSize(newSize)
{
	this.Size = parseInt(newSize);
	this.Lang = (document.getElementById("Lang")).value;
	this.Offset = parseInt((document.getElementById("Offset")).value);
	puzzle.cleanGame();
	puzzle = new initializePuzzle(puzzleName, this.Size, this.Size);
	loadCharArray(useSymbol, this.Size, this.Size, this.Offset, this.Lang);
	puzzle.writePuzzle();
}

function changeLanguage(currentLang)
{
	this.Size = parseInt((document.getElementById("Size")).value);
	this.Lang = currentLang;
	this.Offset = parseInt((document.getElementById("Offset")).value);
	puzzle.cleanGame();
	puzzle = new initializePuzzle(puzzleName, this.Size, this.Size);
	loadCharArray(useSymbol, this.Size, this.Size, this.Offset, this.Lang);
	puzzle.writePuzzle();
}

function changePuzzleOffset(newOffset)
{
	this.Size = parseInt((document.getElementById("Size")).value);
	this.Lang = (document.getElementById("Lang")).value;
	this.Offset = parseInt(newOffset);
	puzzle.cleanGame();
	puzzle = new initializePuzzle(puzzleName, this.Size, this.Size);
	loadCharArray(useSymbol, this.Size, this.Size, this.Offset, this.Lang);
	puzzle.writePuzzle();
}

function loadCharArray(Symbols, intWidth, intHeight, intOffset, currentLang)
{
	var charArray = new Array();
	if (intOffset.NaN) {
		intOffset = 0;
	}
	if (currentLang) {
		this.currentLang = currentLang;
	}

	switch (this.currentLang) {
		//case "fr" : {charArray = charArrayFR.slice(0); break;}	
		case "US/en" : {charArray = English.slice(0); break;}
		case "IN/te" : {charArray = Telugu.slice(0); break;}
		case "IN/hi" : {charArray = Hindi.slice(0); break;}
		default : {charArray = English.slice(0); break;}
	}
	
	var maxChars = (intWidth*intHeight);
	
	for (var i=0; i <= maxChars-1; ++i) {
		if (i > maxChars-2) {
			puzzle.pieces[i]="";
		}
		else {
			initializePuzzle(puzzle.name, intWidth, intHeight);
			if (useSymbol) {
				if (i+intOffset > charArray.lenth-1) {
					puzzle.pieces[i]=charArray[(i+intOffset-(charArray.lenth-1))].symbol;
				}
				else {
					puzzle.pieces[i]=charArray[(i+intOffset)].symbol;
				}
			}
			else {
				if (i+intOffset > charArray.lenth-1) {
					puzzle.pieces[i]=charArray[(i+intOffset-(charArray.lenth-1))].char;
				}
				else {
					puzzle.pieces[i]=charArray[(i+intOffset)].char;
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
