<?php
$q = $_GET['build'];

$con = mysqli_connect('localhost','sec_user','thisIsThePassword','secure_login');
if(!$con)
{
	die('Could not connect: ' . mysqli_error($con));
}
#members
#grades-userid, grader, proj1, proj2, proj3, groupname
$sql="SELECT * FROM grades WHERE grader = '" . $q . "'";

$result = mysqli_query($con,$sql);

echo "
<style>
	table,th,td
	{
		border:1px solid black;
}
</style>
<table border=\"1\" style=\"width:500px\">
	<tr>
		<td>Name</td>
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
					<td><p class=\"name\">" . $row['userid'] . "</p></td>
					<td>
						<select class=\"g1\">
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
						<select class=\"g2\">
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
						<select class=\"g3\">
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
";
}

echo "
	</table> 
	<br>
	<br>
	<br>
<section>
<button id=\"submit\" onclick=\"submitTable()\">Submit</button> 
<a href=\"http://vps08.cs.wmich.edu/grades.html\">LOGOUT</a>";



mysqli_close($con);
?> 