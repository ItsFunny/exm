window.onload = function()
{
	var xhr = ajaxFunction();
	xhr.onreadystatechange = function()
	{
		if (xhr.readyState == 4)
		{
			if (xhr.status == 200)
			{
				var data = xhr.responseText;
				// 改为json格式:
				var json = eval("(" + data + ")");
				alert(json[0].province);

				for (var i = 0; i < json.length; ++i)
				{
					var provinceJson = json[i].province;
					var optionElement = document.createElement("option");
					optionElement.setAttribute("vale", provinceJson)
					var provinceText = document.createTextNode(provinceJson);
					optionElement.appendChild(provinceText);
					var provinceElement = document.getElementById("province");
					provinceElement.appendChild(optionElement);
				}
			}
		}
	}
	xhr.open("POST", "/static/test/json", true);
	xhr.setRequestHeader("Content-type", "")
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