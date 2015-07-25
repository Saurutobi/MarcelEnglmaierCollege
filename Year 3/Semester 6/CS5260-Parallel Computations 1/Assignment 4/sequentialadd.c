#include <stdio.h>
#include <time.h>
#include <sys/time.h>

int main(int argc, char *argv[])
{
	int n = atoi(argv[1]);
	float A[n][n];
	float B[n][n];
	float C[n][n];
	int i;
	int j;
	struct timeval start;
	struct timeval end;
		
	for(i = 0; i < n; i++)
	{
		for(j = 0; j < n; j++)
		{
			A[i][j] = 2 * i + j + 1;
			B[i][j] = i + 4 * j + 2;
		}
	}
	
	gettimeofday(&start, NULL);
	for(i = 0; i < n; i++)
	{
		for(j = 0; j < n; j++)
		{
			C[i][j] = A[i][j] + B[i][j];
		}
	}
	gettimeofday(&end, NULL);
	int timeran = (((end.tv_sec - start.tv_sec) * 1000000) +(end.tv_usec - start.tv_usec));
	printf("Completed in %d Nano Seconds\n", timeran);
	
	return(0);
}