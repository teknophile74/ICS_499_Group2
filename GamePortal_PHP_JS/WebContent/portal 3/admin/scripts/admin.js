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
}

function updateInputDisplay(object)
{
	if (object) {
		if (object === 'wordexplorer'){
			ToggleInputDisplay( 'categoryNameDiv', 'block' );
		} else {
			ToggleInputDisplay( 'categoryNameDiv', 'none' );
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