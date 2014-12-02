/*
 * Last Update By: Aaron Burke
 * Last Update On: 2014/11/08
 * Choices for outputStyle are: 'char','glyph','word','image'
 */
var PuzzleBaseConfig = 
{ 
	puzzleName : 'puzzle',
	initialStyle : 'image',
	currentLang : 'English',
	initialWidth : 4,
	initialHeight : 4,
	initialOffset : 0
};

var PuzzleOutputStyles = 
[ 
	//Character : 'char',
	{ 
		name  : 'Letter',
		style : 'glyph'
	},
	{ 
		name  : 'Word',
		style : 'word' 
	},
	{ 
		name  : 'Image',
		style : 'image'
	}
];