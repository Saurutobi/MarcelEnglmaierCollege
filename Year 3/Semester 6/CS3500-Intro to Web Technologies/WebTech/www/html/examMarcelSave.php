<?php
$werd = $_GET['keyword'];
$string = $_GET['string'];

$con = mysqli_connect('localhost','exam_user','12345','exam');
if(!$con)
{
	die('Could not connect: ' . mysqli_error($con));
}
$sql="INSERT INTO marcelhash (keyword, value) VALUES ('" . $werd . "', '" . $string ."')";
$result = mysqli_query($con,$sql);
echo "Item inserted";
echo "
	<div id=\"inputs\">
		Keyword: <input type=\"text\" id=\"keyword\" value=\"Enter your Keyword Here\"><br>
		String: <input type=\"text\" id=\"stringOutput\" value=\"Output will go Here\"><br>
	</div>
	<div id=\"someFuckingButtons\">
		<input type=\"button\" value=\"Look Up Keyword\" onClick=\"showKeywordAndString()\">
		<input type=\"button\" value=\"Save Keyword/String\" onClick=\"save()\">
		<input type=\"button\" value=\"Change Password\" onClick=\"changePassword()\">
		<a href=\"/\">Logout</a>
	</div>
	";

mysqli_close($con);
?> 