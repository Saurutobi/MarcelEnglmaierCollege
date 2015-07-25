#include<stdio.h>

int ComputeExpr(int, int, int);

int main(int argc, char * argv[])
{
	printf("2*4 + 2*1 + 2*7 = %d \n", ComputeExpr(4,1,7));
	printf("2*2 + 2*3 + 2*4 = %d \n", ComputeExpr(2,3,4));
	printf("2*10 + 2*12 + 2*71 = %d \n", ComputeExpr(10,12,71));
	printf("2*28 + 2*123 + 2*40 = %d \n", ComputeExpr(28,123,40));
}
