/**
 * 
 */

var reader = new XMLHttpRequest() || new ActiveXObject('MSXML2.XMLHTTP');

function loadFile(langFileToLoad) 
{
    reader.open('get', langFileToLoad, true); 
    reader.onreadystatechange = displayContents;
    reader.send(null);
}

function displayContents() 
{
    if(reader.readyState==4) {
        var el = document.getElementById('langDisplay');
        el.innerHTML = reader.responseText;
    }
}