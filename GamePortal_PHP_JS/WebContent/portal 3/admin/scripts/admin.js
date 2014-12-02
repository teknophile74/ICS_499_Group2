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
	ToggleInputDisplay( 'categoryNameDiv', 'none' );
	ToggleInputDisplay( 'secondaryLang', 'none' );
}

function updateInputDisplay(object)
{
	if (object) {
		if (object === 'wordexplorer'){
			ToggleInputDisplay( 'categoryNameDiv', 'block' );
			ToggleInputDisplay( 'secondaryLang', 'block' );
		} else {
			ToggleInputDisplay( 'categoryNameDiv', 'none' );
			ToggleInputDisplay( 'secondaryLang', 'none' );
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