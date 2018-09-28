window.onload = function()
{
	document.getElementById("ok").onclick = function()
	{
		// 服务器回送请求
		xhr.onreadystatechange = function()
		{
			if (xhr.status == 1)
			{
				document.getElementById("divcheck").innerHTML="<img src='loading33.gif'></img>正在连接"
					alert("xxx");
			}
			// alert(xhr.status);
			else if (xhr.status == 4)
			{
				// alert("服务器挂了")
				if (xhr.stauts == 200 || xhr.status == 304)
				{
					var data = xhr.responseText;
					alert(data)
				}
			}
		}
		// 先创建XMLHtppRequest对象
		var xhr = ajaxFunction();
		// 与服务器建立连接
		xhr.open("POST", "/static/test?a=5&b=3");
		// POST方式需要设置类型
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		// 发送
		xhr.send("a=5&b=3");

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
