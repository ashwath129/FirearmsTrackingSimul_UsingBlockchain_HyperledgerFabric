/**
 * Admin Side Utility Methods
 */
function viewRecord() {

	var tbl = $("<table/>").attr("id", "mytable");
	$("#div1").append(tbl);
	var tr1 = "<tr><th>Record</th><th>State</th><th>City</th><th>Zip</th><th>Age</th><th>Gender</th><th>Race</th><th>HispanicOrigin</th><th>DateOfDeath</th><th>DateOfBirth</th><th>PlaceOfDeath</th><th>CauseOfDeath</th><th>IsAutopsyPerformed</th><th>injuryMechanism</th><th>MannerOfDeath</th><th>TxID</th></tr><";
	$("#mytable").append(tr1);
	for (var i = 0; i < obj.length; i++) {
		var tr = "<tr>";
		var td1 = "<td>" + obj[i]["INDEX"] + "</td>";
		var td2 = "<td>" + obj[i]["DSTATE"] + "</td>";
		var td3 = "<td>" + obj[i]["CITYC"] + "</td>";
		var td4 = "<td>" + obj[i]["ZIP9_D"] + "</td>";
		var td5 = "<td>" + obj[i]["AGE"] + "</td>";
		var td6 = "<td>" + obj[i]["SEX"] + "</td>";
		var td7 = "<td>" + obj[i]["NCHSBRIDGE"] + "</td>";
		var td8 = "<td>" + obj[i]["HISPSTSP"] + "</td>";
		var td9 = "<td>" + obj[i]["DOD_MO"] + "</td>";
		var td10 = "<td>" + obj[i]["DOB_YR"] + "</td>";
		var td11 = "<td>" + obj[i]["DPLACE"] + "</td>";
		var td12 = "<td>" + obj[i]["COD1A"] + "</td>";
		var td13 = "<td>" + obj[i]["AUTOP"] + "</td>";
		var td14 = "<td>" + obj[i]["POILITRL"] + "</td>";
		var td15 = "<td>" + obj[i]["MANNER"] + "</td>";
		var td16 = "<td>" + obj[i]["transactionId"] + "</td></tr>";

		$("#mytable").append(
				tr + td1 + td2 + td3 + td4 + td5 + td6 + td7 + td8 + td9 + td10
						+ td11 + td12 + td13 + td14 + td15 + td16);

	}

}


function viewRecordforState() {

	var tbl = $("<table/>").attr("id", "mytable");
	$("#div1").append(tbl);
	var tr1 = "<tr><th>Record</th><th>State</th><th>City</th><th>Zip</th><th>Age</th><th>Gender</th><th>Race</th><th>HispanicOrigin</th><th>DateOfDeath</th><th>DateOfBirth</th><th>PlaceOfDeath</th><th>CauseOfDeath</th><th>IsAutopsyPerformed</th><th>injuryMechanism</th><th>MannerOfDeath</th></tr><";
	$("#mytable").append(tr1);
	for (var i = 0; i < obj.length; i++) {
		var tr = "<tr>";
		var td1 = "<td>" + obj[i]["INDEX"] + "</td>";
		var td2 = "<td>" + obj[i]["DSTATE"] + "</td>";
		var td3 = "<td>" + obj[i]["CITYC"] + "</td>";
		var td4 = "<td>" + obj[i]["ZIP9_D"] + "</td>";
		var td5 = "<td>" + obj[i]["AGE"] + "</td>";
		var td6 = "<td>" + obj[i]["SEX"] + "</td>";
		var td7 = "<td>" + obj[i]["NCHSBRIDGE"] + "</td>";
		var td8 = "<td>" + obj[i]["HISPSTSP"] + "</td>";
		var td9 = "<td>" + obj[i]["DOD_MO"] + "</td>";
		var td10 = "<td>" + obj[i]["DOB_YR"] + "</td>";
		var td11 = "<td>" + obj[i]["DPLACE"] + "</td>";
		var td12 = "<td>" + obj[i]["COD1A"] + "</td>";
		var td13 = "<td>" + obj[i]["AUTOP"] + "</td>";
		var td14 = "<td>" + obj[i]["POILITRL"] + "</td>";
		var td15 = "<td>" + obj[i]["MANNER"] + "</td></tr>";


		$("#mytable").append(
				tr + td1 + td2 + td3 + td4 + td5 + td6 + td7 + td8 + td9 + td10
						+ td11 + td12 + td13 + td14 + td15);

	}

}



function showRecords() {

	var x = document.getElementById("div1");
	if (x.style.display === "none") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}

function showAddAsset() {

	var x = document.getElementById("div4");
	if (x.style.display === "none") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}

function addBlock() {

$(document).ready(
	
		function() {
			var html = "";
			for (i = 0; i <= n; i++) {
				var click = "location.href='blocks'"
				html += "<button id ='no(" + i + ")' value=" + i
						+ " name='blockno' onclick='location.href=\"\/BlockChainFabric/blocks/"+i+"\";'>Block" + i + "</button>";

				$("#blockContainer").html(html);
			}
		});



}


function getGuns(){
	
	$.ajax({
		url : "queryGuns",
		success : function(data) {
			console.log(data);
            createTableData(data);
		}
	});
	
	
}


function createTableData(data){
	console.log(JSON. parse(data));
	let tabdat = JSON. parse(data);
	console.log();
	$("#gunTable tbody").empty();
	var i;
	for (i = 0; i < tabdat.length; i++) {
		let record = tabdat[i].Record;
		$("#gunTable tbody").append("<tr><td>"+record.make+"</td><td>"+record.model+"</td><td>"+record.type+"</td><td>"+record.valid+"</td></tr>");
	}
	
	
	
	
		
}

function creategun(){
	
	var id = n+1;
	var make = $("#make").val();
	var model = $("#model").val();
	var type = $("#type").val();
	var valid;
	if(document.getElementById("valid").checked == true)
	{
		valid = "true";
	} 
	else
		valid = "false";
	console.log(id +"" +make +"" +valid);
	
	$.ajax({
		url : "createGun",
		data : ({
			id: id,
			make : make,
			model : model,
			type : type,
			valid : valid
		}),
		success : function(data) {
			if(data === "200")
            {
				alert("Success");
			document.getElementById("make").val = "";
			document.getElementById("model").val = "";
			document.getElementById("type").val = "";
			setTimeout(function(){
				location.reload(true);
			},2000)	
			
            }
		}
	});
	
}

function addBlockBasic(){
	$(document).ready(function () {
		var html = "";
	    for (i = 0 ; i < n ; i++){
	    	var click = "location.href='blocks'"
	      	html += "<button id ='no("+i+")' value="+i+" name='blockno' onclick='basicBlockInfo("+i+");'>Block"+i+"</button>";
	    
	      	$("#blockContainer").html(html);}
	});
	
}
function basicBlockInfo(i){
	var blockno;

	blockno = document.getElementById("no("+i+")").value;
	$.ajax({
		
		url : "blocksModal",
		data : ({
			blockno : blockno
		}),
		success : function(data) {
			$("#contentBlock").text(data);
			
			$('#blockModal').modal('show');
	
		}
	}); 
	
	
}

function returnTxData(bno){
	var blockno = bno;
	console.log(blockno);
	$.ajax({
		
		url : "txInfo",
		data : ({
			blockno : blockno
		}),
		success : function(data) {

			$("#getCode").html("<table><tr><th>Transaction ID:</th><td>"+data[4]+"</td></tr><tr><th>Channel Name:</th><td>"+data[5]+"</td></tr><tr><th>TimeStamp:</th><td>"+data[6]+"</td></tr><tr><th>TransactionType:</th><td>"+data[7]+"</td></tr><tr><th>Transaction Validity:</th><td>"+data[8]+"</td></tr><tr><th>Transaction Response Status:</th><td>"+data[9]+"</td></tr><tr><th>Endorsement Count:</th><td>"+data[11]+"</td></tr><tr><th>Endorsers:</th><td>"+data[12]+"</td></tr><tr><th style=\"color:green;\">Read/WriteSet:</th><td>"+data[18]+"</td></tr></table>");
			$("#networkModal").modal('show');
			console.log(data[0]);

		}
	}); 

}

function emptyDiv() {
	document.getElementById("div3").innerHTML = "";

}