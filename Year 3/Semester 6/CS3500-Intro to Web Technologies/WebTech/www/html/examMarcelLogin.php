<?php
$uname = $_GET['username'];
$pword = $_GET['password'];
$pword2 = "";
$con = mysqli_connect('localhost','exam_user','12345','exam');
if(!$con)
{
	die('Could not connect: ' . mysqli_error($con));
}
#q is the password recieved from client 
#members
#grades-userid, grader, proj1, proj2, proj3, groupname
$sql="SELECT * FROM marceluser WHERE name = '" . $uname . "'";

$result = mysqli_query($con,$sql); 

while($row = mysqli_fetch_array($result))
{
	ob_start();
	echo $row['password'];
	$pword2 = ob_get_contents();
	ob_end_clean();
}


$hash1 = crypt($pword2);
$hash2 = crypt($pword);
if(strcmp($pword, $pword2) == 0)
{
	echo "yes";
}
else
{
	echo "no";
}

mysqli_close($con);
?> 