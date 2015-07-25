<?php
$q = $_GET['build'];

$con = mysqli_connect('localhost','sec_user','thisIsThePassword','secure_login');
if(!$con)
{
	die('Could not connect: ' . mysqli_error($con));
}
#members
#grades-userid, grader, proj1, proj2, proj3, groupname
$sql="SELECT * FROM grades WHERE userid = '" . $q . "'";

$result = mysqli_query($con,$sql);

echo "
<p>Grades for " . $q . "</p>
<style>
	table,th,td
	{
		border:1px solid black;
}
</style>

<table border=\"1\" style=\"width:500px\">
	<tr>
		<td>Grader</td>
		<td>Project 1 Grade</td>
		<td>Project 2 Grade</td>
		<td>Project 3 Grade</td>
	</tr>
";

$rowCount = 0;

while($row = mysqli_fetch_array($result))
{
	$rowCount = $rowCount+1;
	#echo "<br> New Row: " . $row['userid'] . " content: " . $row['grader'];
	echo "<tr>
					<td><p>" . $row['grader'] . "</p></td>
					<td><p>" . $row['proj1'] . "</p></td>
					<td><p>" . $row['proj2'] . "</p></td>
					<td><p>" . $row['proj3'] . "</p></td>
				</tr>
";
}
echo "
	</table> 
	<br>
	<br>
	<br>
<section>
<a href=\"http://vps08.cs.wmich.edu/grades.html\">LOGOUT</a>";




mysqli_close($con);
?> 