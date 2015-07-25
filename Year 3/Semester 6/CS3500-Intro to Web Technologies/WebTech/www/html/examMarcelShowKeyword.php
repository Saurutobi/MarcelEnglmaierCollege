<?php
$werd = $_GET['keyword'];
$string = $_GET['string'];

$con = mysqli_connect('localhost','exam_user','12345','exam');
if(!$con)
{
	die('Could not connect: ' . mysqli_error($con));
}
if(strcmp ($werd , "empty") == 0)
{
	//nothing, just show the empty stuff3
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
}
else
{
	$sql="SELECT * FROM marcelhash WHERE keyword = '" . $werd . "'";

	$result = mysqli_query($con,$sql);

	$count = 0;
	while($row = mysqli_fetch_array($result))
	{
		$count = $count + 1;
		echo "something was found";
		echo "
		<div id=\"inputs\">
			Keyword: <input type=\"text\" id=\"keyword\" value=\"" . $row['keyword'] ."\"><br>
			String: <input type=\"text\" id=\"stringOutput\" value=\"" . $row['value'] ."\"><br>
		</div>
		<div id=\"someFuckingButtons\">
			<input type=\"button\" value=\"Look Up Keyword\" onClick=\"showKeywordAndString()\">
			<input type=\"button\" value=\"Save Keyword/String\" onClick=\"save()\">
			<input type=\"button\" value=\"Change Password\" onClick=\"changePassword()\">
			<a href=\"/\">Logout</a>
		</div>
		";
	}
	if($count == 0)
	{
		echo "Nothing was found, enter a string and click save to make it";
		echo "
		<div id=\"inputs\">
			Keyword: <input type=\"text\" id=\"keyword\" value=\"" . $werd . "\"><br>
			String: <input type=\"text\" id=\"stringOutput\" value=\"" . $string . "\"><br>
		</div>
		<div id=\"someFuckingButtons\">
			<input type=\"button\" value=\"Look Up Keyword\" onClick=\"showKeywordAndString()\">
			<input type=\"button\" value=\"Save Keyword/String\" onClick=\"save()\">
			<input type=\"button\" value=\"Change Password\" onClick=\"changePassword()\">
			<a href=\"/\">Logout</a>
		</div>
	";	
	}
}

mysqli_close($con);
?> 