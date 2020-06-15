<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/bootstrap/css/style.css" rel="stylesheet">
<link href="resources/bootstrap/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- <link rel="shortcut icon" href="resources/images/logo_small.png"> -->
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="resources/js/recordUtilityScript.js"></script>
<script>
var n = ${noOfBlocks};
</script>
<style>
<style>
div.scrollmenu {
	background-color: #333;
	overflow: auto;
	white-space: nowrap;
}

div.scrollmenu button {
	display: inline-block;
	text-decoration: none;
	color: #fff;
	font-weight: bold;
	background-color: #538fbe;
	padding: 20px 20px;
	font-size: 15px;
	border: 1px solid #2d6898;
	box-shadow: 0px 6px 0px #2b638f, 0px 3px 15px rgba(0, 0, 0, .4), inset
		0px 1px 0px rgba(255, 255, 255, .3), inset 0px 0px 3px
		rgba(255, 255, 255, .5);
}

div.scrollmenu button:hover {
	background-color: #777;
}

</style>
</head>
<body>

<br>
<br>
<div class="scrollmenu" id="blockContainer">
				<br>
				<script type='text/javascript'>	
     addBlockBasic();
          </script>
			</div>
		</div>
		<br> <br>
	</div>
</body>
</html>