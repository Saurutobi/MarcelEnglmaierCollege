<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8"/>
		<link rel="stylesheet" type="text/css" href="mainWebsiteCSS.css">
		<title>Homepage of VPS08, Home of Group 8</title>
	
		<script src="jquery.js"></script> 
		<script> 
			$(function()
			{
				$("#navbarInclude").load("navbar.html");
				$("#sidebarInclude").load("sidebar.html");
			});
			
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
			<h1>QR Code Creator</h1>
	
			<p>Group Members: Marcel Englmaier, Thinh Nguyen, David Rice</p>
		</div>
		<div id="navbarInclude">
		</div>
		
		<div id="main">
		<div class="form">
	<form action="/" method="post">
		<p>Text to embed in QR Code</p>
		<p><textarea name="text" id="meh" style="height:50px;width:350px;"></textarea></p>
		<p class="error"> Text needed! </p>
		<p>Image Size : <select name="size" id="picSize">
			<option value="100x100">100x100</option>
			<option value="150x150">150x150</option>
			<option value="200x200">200x200</option>
			<option value="250x250">250x250</option>
			<option value="300x300" selected>300x300</option>
			<option value="350x350">350x350</option>
			<option value="400x400">400x400</option>
			<option value="500x500">500x500</option>
		</select></p>

	</form>
	</div>
	
			<button onclick="testfunc()">Create QR Code</button></br>
			<!--src="fileEntry.toURL();"-->
			<img id="qrimage"  title="Google's QR Code Maker" alt="Red dot" />
			<a href="#" onClick="saveData(this, 'image.png');"  style="padding-left: 35px;">&nbsp;&nbsp;Save QR Code</a>
			<input id="saveImage" type="button" value="save image" disabled="disabled"/>
			<canvas id="myCanvas" width="578" height="200" border="solid"></canvas>
		<div id="sidebarInclude">
		</div>
		<div id="footer">
		</div>
	</div>
	
        
        


	
	
	
	

	
	<script type="text/javascript">
            window.onload = function () {
                var img = document.getElementById('qrimage');
                var button = document.getElementById('saveImage');
                img.src = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUA'+
                'AAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO'+
                '9TXL0Y4OHwAAAABJRU5ErkJggg==';
                img.onload = function () {
                    button.removeAttribute('disabled');
                };
                button.onclick = function () {
                    window.location.href = img.src.replace('image/png', 'image/octet-stream');
                };
            };
     
		
	

	
	
	function testfunc()
	{
		var canvas = document.getElementById('myCanvas');
		var context = canvas.getContext('2d');
		var size = document.getElementById('picSize').value;
		var meh = document.getElementById('meh').value;
		document.getElementById('qrimage').src = 'https://chart.googleapis.com/chart?chs='+ size + '&cht=qr&chl=' + meh + '&choe=UTF-8';
		var dataURL = canvas.toDataURL();
	}
	</script>
	
	</body>
</html>