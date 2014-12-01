/**
<<<<<<< Upstream, based on origin/Prod
<<<<<<< Upstream, based on origin/Prod
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
=======
 * Main init admin script
=======
 * Main admin init and processing script
>>>>>>> ed5ac9a Updating upload files and conversion settings for admin page
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
<<<<<<< Upstream, based on origin/Prod
  init();
>>>>>>> ff02009 new admin script to drive upload page
};
=======
	init();
};
>>>>>>> ed5ac9a Updating upload files and conversion settings for admin page
