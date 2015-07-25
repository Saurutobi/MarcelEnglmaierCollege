<?php
$userid = $_GET['userid'];
$subject = $_GET['subject'];
$grade1 = INTVAL($_GET['grade1']);
$grade2 = INTVAL($_GET['grade2']);
$grade3 = INTVAL($_GET['grade3']);

$con = mysqli_connect('localhost','sec_user','thisIsThePassword','secure_login');
if(!$con)
{
	die('Could not connect: ' . mysqli_error($con));
}

$sql= "UPDATE grades SET proj1=" . $grade1 . " WHERE grader='" . $userid . "' AND userid='" . $subject . "'";
$result = mysqli_query($con, $sql);
$sql= "UPDATE grades SET proj2=" . $grade2 . " WHERE grader='" . $userid . "' AND userid='" . $subject . "'";
$result = mysqli_query($con, $sql);
$sql= "UPDATE grades SET proj3=" . $grade3 . " WHERE grader='" . $userid . "' AND userid='" . $subject . "'";
$result = mysqli_query($con, $sql);
$sql= "UPDATE members SET has_graded=1 WHERE userid='" . $userid . "'";
$result = mysqli_query($con, $sql);
mysqli_close($con);
?> 