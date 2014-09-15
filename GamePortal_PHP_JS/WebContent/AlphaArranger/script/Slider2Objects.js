function initializePuzzle(aName)
{
	this.width=4;
	this.height=4;	
	//this.emptyVal=0;
	this.emptyVal="";
	this.pieces=new Array();
	this.fields=new Array();
	this.name=aName;
	this.moveCounter=0;
	this.writePuzzle=function()
	{
		var HTML='<div id="game"><table>';
		var tempArray=this.pieces.slice(0);
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

		for(I=0;I<this.height;I++)
		{
			HTML+='<tr>';
			for(J=0;J<this.width;J++)
			{

				if(this.emptyVal==this.fields[I*this.width+J])
				{
					HTML+='<td onclick="'+this.name+'.move('+I+','+J+');" id="'+(I*this.width+J)+'" class="graysq"></td>';continue;
				}

				HTML+='<td onclick="'+this.name+'.move('+I+','+J+');" id="'+(I*this.width+J)+'">'+this.fields[I*this.width+J]+'</td>';
			}
			HTML+='</tr>';
		}
		HTML+='</table></div>';
		document.writeln(HTML);
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

var puzzle=new initializePuzzle('puzzle');
var I=0;

puzzle.pieces[I++]='a';
puzzle.pieces[I++]='b';
puzzle.pieces[I++]='c';
puzzle.pieces[I++]='d';
puzzle.pieces[I++]='e';
puzzle.pieces[I++]='f';
puzzle.pieces[I++]='g';
puzzle.pieces[I++]='h';
puzzle.pieces[I++]='i';
puzzle.pieces[I++]='j';
puzzle.pieces[I++]='k';
puzzle.pieces[I++]='l';
puzzle.pieces[I++]='m';
puzzle.pieces[I++]='n';
puzzle.pieces[I++]='o';
puzzle.pieces[I++]='';
