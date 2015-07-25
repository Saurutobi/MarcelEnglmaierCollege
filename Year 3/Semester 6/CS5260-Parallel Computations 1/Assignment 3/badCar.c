//Author: Marcel Englmaier

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	//set up variables and print messages
	printf("\nStarting");
	int numberofTosses = atoi(argv[1]);
	printf("\nNumber of Tosses = %d", numberofTosses);
	
	//setting up for parallel as per instructions
	unsigned short int xs[3];
	xs[0] = 123;
	xs[1] = 345;
	xs[2] = 2; //will be tid in parallel
	
	int i = 0;
	double numberInCircle = 0;
	
	//do the summation
	for(i = 0; i < numberofTosses; i++)
	{
		double x = erand48(xs);
		double y = erand48(xs);
		double dSquared = x*x + y*y;
		if(dSquared <= 1)
		{
			numberInCircle++;
		}
	}
	//and calculate it
	double estimate = 4 * numberInCircle/((double)numberofTosses);
	//print out the result
	printf("\nEstimate = %f\n", estimate);
	
	return 0;

}

