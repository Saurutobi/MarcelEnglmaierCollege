<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="mainWebsiteCSS.css">
		<title>Homepage of VPS08, Home of Group 8</title>
	
		<script src="jquery.js"></script> 
		<script> 
			$(function()
			{
				$("#navbarInclude").load("navbar.html");
				$("#sidebarInclude").load("sidebar.html");
		
				
			var iValue = $('#iValue');
			$('#setI').change(function(){
			iValue.html(this.value);
			});
			
			$('#setI').change();
			
			var rValue = $('#rValue');
			$('#setR').change(function(){
			rValue.html(this.value);
			});
			
			$('#setR').change();
			
			var sValue = $('#sValue');
			$('#setS').change(function(){
			sValue.html(this.value);
			});
			
			$('#setS').change();
			
			var betaValue = $('#betaValue');
    	$('#setb').change(function(){
      betaValue.html(this.value);
    	});
    		
    	$('#setb').change();
    		
    	var gammaValue = $('#gammaValue');
    	$('#setk').change(function(){
     	gammaValue.html(this.value);
    	});
    		
    	$('#setk').change();
			});
		</script>
		<script type="text/javascript">
		
		function saveData(a, filename)
		{
			var Si = document.getElementById("setS").value;
			this.setS = Si;
			var Ii = document.getElementById("setI").value;
			this.setI = Ii;			
			var Ri = document.getElementById("setR").value;
			this.setR = Ri;
			var bi = document.getElementById("setb").value;
			this.setb = bi;
			var ki = document.getElementById("setk").value;
			this.setk = ki;
			var dti = document.getElementById("setdT").value;
			this.setdT = dti;
			var ti = document.getElementById("setT").value;
			this.setT = ti;
			var data = Si + 
				       ";" +
					   Ii +
				       ";" +
				       Ri +
				       ";" +
				       bi +
				       ";" +
				       ki +
				       ";" +
				       dti +
				       ";" +
				       ti;
			
			contentType = 'data:application/octet-stream,';
			uriContent = contentType + encodeURIComponent(data);
			a.setAttribute('href', uriContent);
			a.setAttribute('download', filename);
			
		}
		
		var text = "";
		var stateNames = {};
		stateNames[FileReader.EMPTY]   = 'EMPTY';
		stateNames[FileReader.LOADING] = 'LOADING';
		stateNames[FileReader.DONE]    = 'DONE';
		var openFile = function(event) {
		var input = event.target;

			var reader = new FileReader();
			reader.onload = function(event){
			var reader = event.target;

			text = reader.result;
			console.log(reader.result.substring(0, 200));
			console.log(text);
			fileSIRModel(text);
	  
			};
			reader.readAsText(input.files[0]);
	
		};
		
		</script>
		
		</head>

	<body>
	<div id="wrap">
		<div id="header">
			<h1>Basic PHP</h1>
	
			<p>Group Members: Marcel Englmaier, Thinh Nguyen, David Rice</p>
		</div>
		<div id="navbarInclude"></div>
		
		<div id="main">
		With PHP, you can echo any html. In fact, most of this page was at one point just echoed!
		<br><br>
		
		
		
		<?php
		
				
		?>
		








<fieldset>
			<legend><b>Please Enter Values<b></legend>
	
			<table>				
    		<tr>
        	<td>Time Increment:
        	<input  id="setdT" type="text" value="1" autofocus> days</td>
        	<td>Total Time:
        	<input  id="setT" type="text" value="100"> days</td>
    		</tr>	
    		
    		<tr>
        	<td colspan="2"><p class="note">S value: <span id="sValue">0</span></p></td>
    		</tr>
    		<tr>
        	<td colspan="2"><input id="setS" type="range" min="0" max="1000" step="1" value="999"/></td>
    		</tr>
    		
    		<tr>
        	<td colspan="2"><p class="note">I value: <span id="iValue">0</span></p></td>
        </tr>
        <tr>
        	<td colspan="2"><input id="setI" type="range" min="0" max="1000" step="1" value="1"/></td>
        </tr>	
        
    		<tr>
        	<td colspan="2"><p class="note">R value: <span id="rValue">0</span></p></td>
        </tr>	
        <tr>
        	<td colspan="2"><input id="setR" type="range" min="0" max="1000" step="1" value="0"/>	</td>
        </tr>
        
    		
    		<tr>
        	<td><p class="note">Beta value: <span id="betaValue">0</span></p></td>
        	<td><p class="note">Gamma value: <span id="gammaValue">0</span></p></td>	
    		</tr>
    		
    		<tr>
        	<td><input id="setb" type="range" min=".0" max="1.00" step=".01"/></td>
        	<td><input id="setk" type="range" min=".0" max="1.00" step=".01"/></td>	
    		</tr>
    		
			</table>
			</fieldset>
			</br>
			<style>
			section { display: inline; }
			</style>
			<section>
			<p>Click the button to start the model</p>
			<button onclick="SIRModel()">Graph it!</button></br>
			
			
			<input type="file" accept='text/plain' onChange='openFile(event)' style="padding-right: 30px;" />	
			
			<a href="#" onClick="saveData(this, 'graph.txt');" style="padding-left: 30px;">&nbsp;&nbsp;Save Graph</a>
			</section>
			</br>

		<div id="chartContainer" style="height: 500px; width: 90%;"></div>		
		
		</div>
		
		<div id="sidebarInclude">
		</div>
		<div id="footer">
		</div>
	</div>
	
	

<script>
function SIRModel()
{
	localStorage.setItem('S', document.getElementById("setS").value);
	localStorage.setItem('I', document.getElementById("setI").value);
	localStorage.setItem('R', document.getElementById("setR").value);
	localStorage.setItem('b', document.getElementById("setb").value);
	localStorage.setItem('k', document.getElementById("setk").value);
	localStorage.setItem('dt', document.getElementById("setdT").value);
	localStorage.setItem('t', document.getElementById("setT").value);
	var s = parseInt(localStorage.getItem('S'));
	var i = parseInt(localStorage.getItem('I'));
	var r = parseInt(localStorage.getItem('R'));
	var beta = parseFloat(localStorage.getItem('b'));
	var gamma = parseFloat(localStorage.getItem('k'));
	var dT = parseInt(localStorage.getItem('dt'));
	var totalT = parseInt(localStorage.getItem('t'));

	$.post('test.php', {'s': s, 'i': i, 'r': r, 'beta': beta, 'gamma': gamma,
													'dT': dT, 'totalT': totalT}, 
			function(e) {

  	var n = 0;
		var data_array = e.split(',');
		var dpS = [{x: 0, y: parseInt(data_array[0])}];   //dataPoints. 
  	var dpI = [{x: 0, y: parseInt(data_array[1])}];
  	var dpR = [{x: 0, y: parseInt(data_array[2])}];
  
		for (n = 1; n < (data_array.length/3); n+=3)
		{
	 	 dpS.push({x: n,y: parseInt(data_array[n-1])});
			dpI.push({x: n,y: parseInt(data_array[n])});
   	 dpR.push({x: n,y: parseInt(data_array[n+1])});
    }
    
    
    var chart = new CanvasJS.Chart("chartContainer",{
	
		backgroundColor: "Gold",
      	title :{
      		text: "SIR Model",
			fontFamily: "modern",
      	},
      	legend: {
		  fontWeight: "bold",
      	  horizontalAlign: "right",
      	  verticalAlign: "top",
      	},
	
		
      	axisX: {						
      		title: "Days",
			titleFontFamily: "modern",			
      	},
      	axisY: {						
      		title: "Population",
			titleFontFamily: "modern",	
      	},
      	data: [{			
      		type: "line",			
			lineThickness: 3,			
      		dataPoints : dpS,
      		showInLegend: true,
      		legendText: "S"
      	},
      	{ type: "line",		  
		  lineThickness: 3,		  
      	  dataPoints : dpI,
      	  showInLegend: true,
      		legendText: "I"
      	},
      	{ type: "line",		  
		  lineThickness: 3,
      	  dataPoints : dpR,
      	  showInLegend: true,
      		legendText: "R"
      	}]
      });

      chart.render();
});
	
}
</script>

<script type="text/javascript" src="canvasjs.min.js"></script>

<div id="text"></div>
<script>
	
function SetFocus()
	{
    // safety check, make sure its a post 1999 browser
    if (!document.getElementById)
    {
        return;
    }

    var userinput = document.getElementById("setdT");
	
	

    if (userinput != null)
    {
        userinput.focus();
		userinput.select();
    }
	}
	SetFocus();
</script>
</body>
</html>