/**
 * Main admin init and processing script
 */


function ToggleInputDisplay( id, value ) 
{
    var element = document.getElementById(id);

    if (element) {
		if (value) {
			element.style.display = value.toString();
		}
	}
}

function SetCategoryInputHidden()
{
	ToggleInputDisplay( 'categoryNameControl', 'none' );
	ToggleInputDisplay( 'secondaryLangLabel', 'none' );
}

function updateInputDisplay(object)
{
	if (object) {
		if (object === 'wordexplorer'){
			ToggleInputDisplay( 'categoryNameControl', 'block' );
			ToggleInputDisplay( 'secondaryLangLabel', 'block' );
		} else {
			ToggleInputDisplay( 'categoryNameControl', 'none' );
			ToggleInputDisplay( 'secondaryLangLabel', 'none' );
		}
	}
}

function init()
{
	PopulatePageDropDownSettings();
	SetCategoryInputHidden();
}

window.onload=function newOnload()
{
	init();
};