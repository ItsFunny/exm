window.onload = function()
{
	document.getElementById("checkusername").onclick = function()
	{
		var xhr = ajaxFunction();
		var username = document.getElementById("username");
		xhr.onreadystatechange = function()
		{
			if (xhr.readyState == 4)
			{
				// if(xhr.status==200)
				// {
				var data = xhr.responseText;
				alert(data);
				document.getElementById("divcheck").innerHTML = data;
				// }
			}
		}
		xhr.open("POST", "/user/register/check", true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhr.send("username=" + username);

	}

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