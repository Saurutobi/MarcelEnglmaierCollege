//Author: Marcel Englmaier

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main(int argc, char **argv)
{
	//Set up basic variables
	int numberOfTosses = atoi(argv[1]);
	int thread_count = atoi(argv[2]);
	//Instructions said to do xs[]
	unsigned short int xs[3];
	xs[0] = 123;
	xs[1] = 345;
	int i;
	double numberInCircle = 0;
	
	//print notifications
	printf("\nNumber of Tosses:%d", numberOfTosses);
	printf("\nNumber of Threads:%d", atoi(argv[2]));
	printf("\nSchedule:%s", getenv("OMP_SCHEDULE"));
	
	//start timer
	double timeStart = omp_get_wtime();
	//parallel section
#   pragma omp parallel num_threads(thread_count) \
	default(none) \
	private(i, xs) \
	shared(numberOfTosses, numberInCircle, thread_count)
	{
		//Instructions said to do this
		xs[2] = omp_get_thread_num();
		//parallel for
#		pragma omp parallel for reduction(+: numberInCircle) schedule(runtime)
		for(i = 0; i < (numberOfTosses); i++)
		{
			//summation as defined by book's pseudocode
			double x = erand48(xs);
			double y = erand48(xs);
			double dSquared = x*x + y*y;
			if(dSquared <= 1)
			{
				numberInCircle++;
			}
		}
	}
	//here we estimate
	double estimate = 4 * numberInCircle/((double)numberOfTosses);
	//We're done, so let's stop the timer
	double timeEnd = omp_get_wtime();
	//Now we'll print out the results
	printf("\nEstimate of Pi:%f", estimate);
	printf("\ntime:%f\n\n", timeEnd - timeStart);
	
	return 0;
}

