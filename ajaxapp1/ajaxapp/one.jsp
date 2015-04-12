<html>
	<head>
		<script>
		function callAjax(){
			alert('we are in test ajax');
			var url='http://localhost:8500/ajaxapp/ajax?fone=ajax';
			var result;
			if (window.XMLHttpRequest)
			{
				a=new XMLHttpRequest();
	    	}
			else
			{
				a=new ActiveXObject("Microsoft.XMLHTTP");
			}
		a.onreadystatechange=function(){
			if (a.readyState==4){
				result=a.responseText;   
			}
		};
		a.open("POST",url,false);
		a.send();
		alert(result);
		}
		</script>
	</head>
	<body>
		<form name="ajaxapp" action="/ajaxapp/fs">
			Give i/p here :<input type="text" name="fone"/>
			<input type="submit" value="clickme" onclick="callAjax()"/>
	</body>
</html>
