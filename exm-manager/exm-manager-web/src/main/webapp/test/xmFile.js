window.onload = function()
{
	var xhr = ajaxFunction();

	xhr.onreadystatechange = function()
	{
		if (xhr.readyState == 4)
		{
			if (xhr.status == 200)
			{
				var data = xhr.responseXML;
				alert(data);
			}

		}
	}
	xhr.open("POST", "/static/test/xml", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(null);
}

function ajaxFunction()
{
	var xmlHttp;
	try
	{ // Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest();
	} catch (e)
	{
		try
		{// Internet Explorer
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e)
		{
			try
			{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e)
			{
			}
		}
	}

	return xmlHttp;
}