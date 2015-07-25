There are two versions: the inline version and the parallel version.
badCar.c is the inline version, and can be compiled with 'gcc badCar.c'
badCarParallelParked.c is the parallel version, and can be compiled with
	'gcc -openmp -o badcar badCarParallelParked.c'

Both programs required you to specify the number of iterations. The Parallel
	one requires an extra number for the number of threads. Below are the two execution
	commands with the about compilation used:
	
	inline:
	'./a.out [iterations]'
	
	parallel
	'./badcar [iterations] [number of threads]
	
When running the parallel version, you can create an environment variable called
	'OMP_SCHEDULE' to specify it's schedule. This can be done with:
	'export OMP_SCHEDULE="[the schedule goes here]"'