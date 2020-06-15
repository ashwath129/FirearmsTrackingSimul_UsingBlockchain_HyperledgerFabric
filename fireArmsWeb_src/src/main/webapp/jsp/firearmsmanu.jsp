<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FireArms</title>

<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/bootstrap/css/style.css" rel="stylesheet">
<link href="resources/bootstrap/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- <link rel="shortcut icon" href="resources/images/logo_small.png"> -->
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="resources/js/recordUtilityScript.js"></script>
<script type="text/javascript">
	var n = ${noOfBlocks};
</script>
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

#mytable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 75px;
	margin-left: 15px;
}

#mytable td, #mytable th {
	border: 1px solid #ddd;
	padding: 8px;
}

#mytable tr:nth-child(even) {
	background-color: #f2f2f2;
}

#mytable tr:hover {
	background-color: #ddd;
}

#mytable th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: black;
	color: white;
}
.my-custom-scrollbar {
position: relative;
height: 200px;
overflow: auto;
}
.table-wrapper-scroll-y {
display: block;
}
#tabDiv,#createQueryGuns{
margin-top: 30px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<div class="mar-top10px">

				<h3>
					<b>Fire Arms Manufacturer Interface</b>
				</h3>
			</div>
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" style="padding-top: 15px;" href="#"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i> <strong><p>${username}</p></strong>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul></li>
		</ul>
	</div>
	</nav>

	<div class="container">

		<div class="row">
			<p>
				<strong>Number of Blocks in Ledger : ${noOfBlocks}</strong>
			</p>

			<br>
			<center>
				<p>
					<strong>Distributed Blockchain Ledger</strong>
				</p>
			</center>

			<div class="scrollmenu" id="blockContainer">
				<br>
				<script type='text/javascript'>
					addBlockBasic();
				</script>

			</div>
		</div>

		<div id="createQueryGuns" class="row">
			<div id="createGun" class="col-md-6">
				<strong> Add Gun Info: </strong><br>
				<br> <input type="text" id="make" class="form-control"
					placeholder="Make" name="make" required> <br> <input
					type="text" id="model" class="form-control" placeholder="Model"
					name="model" required> <br> <input type="text"
					id="type" class="form-control" placeholder="Type" name="type"
					required> <br> <strong>Valid </strong> <input
					type="checkbox" id="valid" required><br>
				<br>
				<button class="btn btn-lg btn-primary btn-block" type="submit"
					onclick="creategun();">Create Gun</button>
			</div>
			<div id="getGuns" class="col-md-6">

				<button class="btn btn-lg btn-success btn-block" type="button"
					onclick="getGuns();">Get Guns</button>


				<div class="table-wrapper-scroll-y my-custom-scrollbar" id='tabDiv'>

					<table class="table table-bordered table-striped mb-0"
						id="gunTable">
						<thead>
							<tr>
								<th>MAKE</th>
								<th>MODEL</th>
								<th>TYPE</th>
								<th>VALID</th>

							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>

				</div>
			</div>
<!-- modal -->


        <div class="modal fade" id="blockModal" tabindex="-1" role="dialog" aria-labelledby="failureModalLongTitle"
            aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title" id="failureModalLongTitle">Block Details</h3>

                       
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h4>Details</h4>
                        <div id="contentBlock">
                            
                        </div>
                        <br><br>
                       
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                       
                    </div>
                </div>
            </div>
        </div>
<!-- end of modal -->



		</div>
		<br>
		<!-- <div id="createQueryGuns">
		<div id="createGun" style="width:20%;margin: 20px;">
		    <strong> Add Gun Info: </strong><br><br>
            <input
			type="text" id="make" class="form-control" placeholder="Make"
			name="make" required> <br>
			<input
			type="text" id="model" class="form-control" placeholder="Model"
			name="model" required> <br>
		    <input
			type="text" id="type" class="form-control" placeholder="Type"
			name="type" required> <br>
			<strong>Valid       </strong> <input type="checkbox" id="valid" required><br><br>
			<button class="btn btn-lg btn-primary btn-block" type="submit" onclick="creategun();">Create
			Gun</button>
		</div>
		<div id = "getGuns" align="center">
		
		  			<button class="btn btn-lg btn-primary btn-block" type="button" onclick="getGuns();">Get Guns</button>
		
		</div>
	</div>
	 -->
		<br>

		<div class="fixed-footer" style="position: bottom;">
			<p class="txt-center margin0x">
				<b>&copy;</b> <b>UFL 2020</b>
			</p>
		</div>
</body>
</html>