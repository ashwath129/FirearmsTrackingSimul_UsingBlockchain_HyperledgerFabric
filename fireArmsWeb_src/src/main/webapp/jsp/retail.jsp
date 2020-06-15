<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Retail</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/bootstrap/css/style.css" rel="stylesheet">
<link href="resources/bootstrap/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- <link rel="shortcut icon" href="resources/images/logo_small.png"> -->
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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

#mytable, td {
	border: 1px solid blue;
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

#in input {
	margin-top: 10px;
}

#inlab label {
	margin-top: 18px;
}

.bld {
	font-weight: bold;
	font-size: 14px;
}

#btnDiv {
	margin-top: 60px;
}

#btnDiv button {
	width: 180px;
	height: 50px;
}

#clrbtn {
	margin-left: 80px;
}

#vdbtn {
	margin-left: 115px;
}

.modal h3 {
	display: inline;
}

.cIn {
	margin-top: 20px;
}

#purFailBtn {
	background: black;
	color: white;
}

body {
	
}

#ff label {
	margin-top: 5px;
	font-size: 16px;
}

#head333 {
	padding-left: 234px;
	font-size: 42px;
	font-weight: bold;
	margin-bottom: 30px;
}

.stT {
	margin-left: 30px;
}

.stdiv {
	border: 2px solid grey;
	padding: 21px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<div class="mar-top10px">

				<h3>
					<b>Retail Interface</b>
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

		<div>
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
		<br> <br>

		<h2 id="head333">Customer Verification</h2>
		<form class="form-inline" action="#"></form>
		<div class="form-group col-md-8 col-md-offset-2" id="ff">

			<div class="col-md-12 cIn">
				<div class="col-md-4" id="inlab1">
					<label for="cName">Customer Name:</label>
				</div>
				<div class="col-md-8" id="in1">
					<input type="text" class="form-control form-control-lg" id="cName"
						placeholder="Enter Customer Name" name="cust1">
				</div>
			</div>

			<div class=" col-md-12 cIn">
				<div class="col-md-4" id="inlab2">
					<label for="cAddr">Customer Address:</label>
				</div>
				<div class="col-md-8" id="in2">
					<input type="text" class="form-control form-control-lg" id="cAddr"
						placeholder="Enter Customer Address" name="cust1">
				</div>
			</div>

			<div class=" col-md-12 cIn">
				<div class="col-md-4" id="inlab3">
					<label for="cEml">Email ID:</label>
				</div>
				<div class="col-md-8" id="in3">
					<input type="email" class="form-control form-control-lg" id="cEml"
						placeholder="Enter Customer Email ID" name="cust1">
				</div>
			</div>

			<div class=" col-md-12 cIn">
				<div class="col-md-4" id="inlab4">
					<label for="cSSN">SSN:</label>
				</div>
				<div class="col-md-8" id="in4">
					<input type="text" class="form-control form-control-lg" id="cSSN"
						placeholder="Enter Customer SSN" name="cust1">
				</div>
			</div>

			<div class=" col-md-12 cIn">
				<div class="col-md-4" id="inlab4">
					<label for="selG">Select Gun:</label>
				</div>
				<div class="col-md-8" id="in5">

					<select class="form-control" id="selG">
						<option>Select gun for purchase</option>
						<option>Make: Bolinger, Model: AK47</option>
						<option>Make: Carbine, Model: M4</option>
						<option>Make: Bolinger, Model: M4</option>
					</select>

				</div>
			</div>

			<div class="col-md-12" id="btnDiv">
				<button class="btn btn-primary " id="vdbtn">Verify Details</button>
				<button class="btn btn-danger " id="clrbtn">Clear</button>
			</div>

		</div>


		<div class="modal fade" id="successModal" tabindex="-1" role="dialog"
			aria-labelledby="successModalTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="successModalTitle">Successful
							Verification</h3>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>Customer Details have been verified</p>

						<div>
							<span class="bld">Name :</span> <span class='cn'></span> <br>
							<br> <span class="bld">Address :</span> <span class='ca'></span>
							<br>
							<br> <span class="bld">Email :</span> <span class='ce'></span>
							<br>
							<br> <span class="bld">SSN :</span> <span class='cs'></span>
							<br>
							<br> <span class="bld"><u>Gun Details :</u></span> <br><br>
								<span class='gd'></span>
							<br>

						</div>
						<br>

						<div class="stdiv">
							<h4>Status Check</h4>
							<span class="">Police :</span> <span
								class="glyphicon glyphicon-ok"></span> <span class="stT">Medical
								:</span> <span class="glyphicon glyphicon-ok"></span> <span class="stT">License
								:</span> <span class="glyphicon glyphicon-ok"></span>
						</div>
						<br>
						<br>
						<p>Customer eligible to purchase gun</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-success">Purchase
							Gun</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="failureModal" tabindex="-1" role="dialog"
			aria-labelledby="failureModalLongTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="failureModalLongTitle">Verification
							Failed</h3>


						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>Purchase not authorized</p>
						<div>
							<span class="bld">Name :</span> <span class='cn'></span> <br>
							<br> <span class="bld">Address :</span> <span class='ca'></span>
							<br>
							<br> <span class="bld">Email :</span> <span class='ce'></span>
							<br>
							<br> <span class="bld">SSN :</span> <span class='cs'></span>
							<br>
							<br> <span class="bld">Gun Details :</span> <br><br>
								<span class='gd'></span>
							<br>

						</div>
						<br>

						<div class="stdiv">
							<h4>Status Check</h4>
							<span class="">Police :</span> <span
								class="glyphicon glyphicon-remove"></span> <span class="stT">Medical
								:</span> <span class="glyphicon glyphicon-remove"></span> <span
								class="stT">License :</span> <span
								class="glyphicon glyphicon-ok"></span>
						</div>

						<br>
						<br>
						<p>Customer not eligible for gun purchase</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-secondary" id="purFailBtn"
							disabled>Purchase Gun</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	</form>


	</div>

	<br>

	<!-- end Main prog -->

	<div class="fixed-footer" style="position: bottom;">
		<p class="txt-center margin0x">
			<b>&copy;</b> <b>UFL 2020</b>
		</p>
	</div>
</body>

<script>
    $(document).ready(function () {

        $('#vdbtn').on('click', function () {
            let ssnval = $("#cSSN").val();
            let cn= $('#cName').val();
            let ca=$('#cAddr').val();
            let ce= $('#cEml').val();
            let gd = $("#selG").val();

            $('.cn').text(cn);
            $('.ca').text(ca);
            $('.cs').text(ssnval);
            $('.ce').text(ce);
            $('.gd').text(gd);

            if (ssnval == '1000000000') {

                $('#failureModal').modal('show');
            }
            else {
                $('#successModal').modal('show');
            }

        });

    });
</script>
</html>

