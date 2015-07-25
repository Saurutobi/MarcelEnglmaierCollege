<?php
/* $uname = $_GET['build']; */
$uname = $_GET['build'];
$pword = $_GET['password'];
$pword2 = "";
$group = "";
$instructor = "instructor";
$has_graded = 0;
$con = mysqli_connect('localhost','sec_user','thisIsThePassword','secure_login');
if(!$con)
{
	die('Could not connect: ' . mysqli_error($con));
}
#q is the password recieved from client 
#members
#grades-userid, grader, proj1, proj2, proj3, groupname
$sql="SELECT * FROM members WHERE userid = '" . $uname . "'";

$result = mysqli_query($con,$sql); 


while($row = mysqli_fetch_array($result))
{
	ob_start();
	echo $row['password'];
	$pword2 = ob_get_contents();
	ob_end_clean();
}


$sql= "SELECT * FROM members WHERE userid='" . $uname . "'";
$result = mysqli_query($con, $sql);
while($row = mysqli_fetch_array($result))
{
	ob_start();
	echo $row['groupname'];
	$group = ob_get_contents();
	ob_end_clean();
}


$sql= "SELECT * FROM members WHERE userid='" . $uname . "'";
$result = mysqli_query($con, $sql);
while($row = mysqli_fetch_array($result))
{
	ob_start();
	echo $row['has_graded'];
	$has_graded = INTVAL(ob_get_contents());
	ob_end_clean();
}


$hash1 = crypt($pword2);
$hash2 = crypt($pword);
if((strcmp($pword, $pword2) == 0) && (strcmp($group,$instructor) == 0))
{
	echo "instructor";
}
else if((strcmp($pword, $pword2) == 0) && ($has_graded == 1))
{
	echo "has_graded";
}
else if(strcmp($pword, $pword2) == 0)
{
	echo "yes";
}
else
{
	echo "no";
}

mysqli_close($con);
?> 