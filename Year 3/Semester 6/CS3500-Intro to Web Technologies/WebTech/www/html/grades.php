<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="mainWebsiteCSS.css">
		<title>Rate Your Group</title>
		<script type="text/javascript" src="base64.js"></script>
		<script src="jquery.js"></script> 
		<script> 
		
			$(function()
			{
				$("#navbarInclude").load("navbar.html");
				$("#sidebarInclude").load("sidebar.html");
			});
		</script>
		<script type="text/javascript">
			function crypt(mode){		//mode 0=encrypt 1=decrypt
			
		
				var input  = document.getElementById('input').value;
				var key    = document.getElementById('key').value;
				var output = "";
				
				if(mode==1){
					input = Base64.decode(input);
				}
				
				for(var i=0;i<input.length;i++){
					var c = input.charCodeAt(i);
					var k = key.charCodeAt(i%key.length);
					
					output += String.fromCharCode(c ^ k);
				}
				
				if(mode==0){
					output = Base64.encode(output);
				}
				
				document.getElementById('output').value = output;
				
			}
			
			window.onload = function(){
				document.getElementById('output').onclick=function(){
					this.focus();
					this.select();
				}
			}
		</script>
		
		
	</head>

	<body onload="disableControls()">
	<div id="wrap">
		<div id="header">
			<h1>Rate Your Group</h1>
	
			<p>Group Members: Marcel Englmaier, Thinh Nguyen, David Rice</p>
		</div>
		<div id="navbarInclude"></div>
		
		<div id="main">
			<style>
				table,th,td
				{
					border:1px solid black;
				}
			</style>
			<table border="1" style="width:500px">
				<tr>
					<td>Name</td>
					<td>Project 1 Grade</td>
					<td>Project 2 Grade</td>
					<td>Project 3 Grade</td>
				</tr>
				<tr>
					<td><input type="text" id="userInput1" fname="first name"></td>
					<td>
						<select id="p1g1">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
					<td>
						<select id="p1g2">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
					<td>
						<select id="p1g3">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><input type="text" id="userInput2" fname="first name"></td>
					<td>
						<select id="p2g1">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
					<td>
						<select id="p2g2">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
					<td>
						<select id="p2g3">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><input type="text" id="userInput3" fname="first name"></td>
					<td>
						<select id="p2g1">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
					<td>
						<select id="p2g2">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
					<td>
						<select id="p2g3">
							<option value=\"0\">0%</option>
							<option value=\"10\">10%</option>
							<option value=\"20\">20%</option>
							<option value=\"30\">30%</option>
							<option value=\"40\">40%</option>
							<option value=\"50\">50%</option>
							<option value=\"60\">60%</option>
							<option value=\"70\">70%</option>
							<option value=\"80\">80%</option>
							<option value=\"90\">90%</option>
							<option value=\"100\">100%</option>
						</select>
					</td>
				</tr>
				
				
			</table> 
		<br><br>
		</br>
		<section>
		<form>
<select name="users" onchange="showUser(this.value)">
<option value="">Select a person:</option>
<option value="1">David Rice</option>
<option value="2">Marcel Englmaier</option>
<option value="3">Thinh Nguyen</option>
</select>
</form>
<br>
<div id="txtHint"><b>Person info will be listed here.</b></div>
		
		<button  id="loginBtn"  onclick="getUserData()">Login</button>
		<button  id="submitBtn" onclick="FUCKING BULLSHIT HERE">Save!</button>
		</br>
		<div class="container">
		<h1>Javascript XOR Cipher</h2>
		<table>
			<tr>
				<td>Plaintext/Cipher</td>
				<td><textarea cols="40" rows="8" id="input"></textarea></td>
			</tr>
			<tr>
				<td>Key</td>
				<td><input type="text" size="40" id="key"/></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<input type="button" onclick="crypt(0)" value="Encrypt"/>
					<input type="button" onclick="crypt(1)" value="Decrypt"/>
				</tr>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td>Output<br/><span>(Click to select)</span></td>
				<td><textarea cols="40" rows="8" id="output" readonly="readonly"></textarea></td>
			</tr>
		</table>
	</div>
		</br>
		<div id="sidebarInclude">
		</div>
		<div id="footer">
		</div>
	</div>
	<script>
	function disableControls()
	{
		document.getElementById( 'userInput1' ).disabled='true';
		document.getElementById( 'userInput2' ).disabled='true';
		document.getElementById( 'userInput3' ).disabled='true';
		document.getElementById( 'submitBtn' ).disabled='true';
		/*document.getElementById( 'btnSubmit' ).disabled='true';
		document.getElementById( 'btnSubmit' ).disabled='true';
		document.getElementById( 'btnSubmit' ).disabled='true';
		document.getElementById( 'btnSubmit' ).disabled='true';
		document.getElementById( 'btnSubmit' ).disabled='true'; */
	
	}
	</script>
	<script>
function showUser(str)
{
	var user = "";
	switch(str)
	{
		case "1":
		user = "David Rice";
		break;
		case "2":
		user = "Marcel Englmaier";
		break;
		case "3":
		user = "Thinh Nguyen";
		break;
	
	}
alert(user);
if (str=="")
  {
  document.getElementById("txtHint").innerHTML="";
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","login.php?q="+user,true);
xmlhttp.send();
}
</script>
	
	
	<script>
	
	function getUserData()
	{
		<!--<input type="button" onclick="crypt(0)" value="Encrypt"/>-->
		<!--<input type="button" onclick="crypt(1)" value="Decrypt"/>-->
		var userName=prompt("Please enter user name","")
		var password=prompt("Please enter your password","")
		crypt(1, password);
	
	}
	

	
	
	
	
	</script>
</body>
</html>