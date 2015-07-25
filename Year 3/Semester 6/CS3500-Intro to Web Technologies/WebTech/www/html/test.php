

	<?php
		$s = $_POST["s"];
		$i = $_POST["i"];
		$r = $_POST["r"];
		$beta = $_POST["beta"];
		$gamma = $_POST["gamma"];
		$dT = $_POST["dT"];
		$totalT = $_POST["totalT"];
			
		$output = `./fuckit $s $i $r $beta $gamma $dT $totalT`;
		
		echo $output;
		?>
		
