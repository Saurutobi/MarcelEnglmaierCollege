<?php
$pass = $_GET['password'];
$uname = "marcel";

$con = mysqli_connect('localhost','exam_user','12345','exam');
if(!$con)
{
	die('Could not connect: ' . mysqli_error($con));
}
$sql="UPDATE marceluser SET password='" . $pass . "' WHERE name='" . $uname ."'";

$result = mysqli_query($con,$sql);

echo "updated<br>";
echo "<a href=\"/\">Logout</a>";
mysqli_close($con);
?> 