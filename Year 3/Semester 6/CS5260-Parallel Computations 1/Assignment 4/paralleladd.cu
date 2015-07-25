#include <stdio.h>
#include <time.h>
#include <sys/time.h>

__global__ void matrixAddition (int *a, int *b, int *c, int f)
{
	int column = blockIdx.x * blockDim.x + threadIdx.x;
	int row = blockIdx.y * blockDim.y + threadIdx.y;
	int index = column + row * f;
	if (column < f && row < f)
	{
		c[index] = a[index] + b[index];
	}
}

int main(int argc, char *argv[])
{
	int n = atoi(argv[1]);
	printf("\n\nRunning with n=%d\n", n);
	int a[n][n];
	int b[n][n];
	int c[n][n];
	struct timeval start;
	struct timeval end;
	int *gpu_a;
	int *gpu_b;
	int *gpu_c;
	int matrixSize = n * n * sizeof(int);
	
	//make values
	for(int i = 0; i < n; i++)
	{
		for(int j = 0; j < n; j++)
		{
			a[i][j] = 2 * i + j + 1;
			b[i][j] = i + 4 * j + 2;
		}
	}
	
	cudaMalloc((void**)&gpu_a, matrixSize);
	cudaMalloc((void**)&gpu_b, matrixSize);
	cudaMalloc((void**)&gpu_c, matrixSize);
	
	gettimeofday(&start, NULL);
	cudaMemcpy(gpu_a, a, matrixSize, cudaMemcpyHostToDevice);
	cudaMemcpy(gpu_b, b, matrixSize, cudaMemcpyHostToDevice);
	dim3 dimBlock(2,2);
	dim3 dimGrid((int)ceil(n/dimBlock.x),(int)ceil(n/dimBlock.y));
	
	matrixAddition<<<dimGrid,dimBlock>>>(gpu_a,gpu_b,gpu_c,n);
	
	cudaMemcpy(c, gpu_c, matrixSize, cudaMemcpyDeviceToHost);
	gettimeofday(&end, NULL);
	
	
	cudaFree(gpu_a);
	cudaFree(gpu_b);
	cudaFree(gpu_c);
	
	int success = 1;
	
	for(int i = 0; i < n; i++)
	{
		for(int j = 0; j < n; j++)
		{
			if(c[i][j] != (a[i][j] + b[i][j]))
			{
				success = 0;
				printf("FAILED\n");
				printf("c(%d) != a(%d) + b(%d)\n", c[i][j], a[i][j], b[i][j]);
				break;
			}
		}
		if(success == 0)
		{
			break;
		}
	}
	
	if(success == 1)
	{
		printf("We Did It!!\n");
		int timeran = (((end.tv_sec - start.tv_sec) * 1000000) +(end.tv_usec - start.tv_usec));
		printf("Completed in %d Nano Seconds\n", timeran);
	}
	else
	{
		int timeran = (((end.tv_sec - start.tv_sec) * 1000000) +(end.tv_usec - start.tv_usec));
		printf("Completed in %d Nano Seconds\n", timeran);
	}
	
	return(0);
}
