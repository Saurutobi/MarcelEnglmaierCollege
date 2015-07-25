#include <stdio.h>
#include "cunit.h"

int largest(int [], int, int);
int largestFixed(int [], int, int);

int main()
{
	cunit_init();
	int length = 10;
	int input[length];
	int i = 0;
	for(i = 0; i < length; i++)
	{
		input[i] = i;
	}
	
	assert_eq("Testing old with n=1, l=0", largest(input, 1, 0), input[length - 1]);
	assert_eq("Testing old with n=10, l=20", largest(input, 10, 20), input[length - 1]);
	assert_eq("Testing old with n=202, l=0", largest(input, 202, 0), input[length - 1]);
	assert_eq("Testing old with n=10, l=-1", largest(input, 10, -1), input[length - 1]);
	assert_eq("Testing old with n=-1, l=10", largest(input, 10, -1), input[length - 1]);
	
	assert_eq("Testing fixed with standardized n, l=500", largestFixed(input, length, 500), input[length - 1]);
	assert_eq("Testing fixed with standardized n, l=700", largestFixed(input, length, 700), input[length - 1]);
	assert_eq("Testing fixed with standardized n, l=200", largestFixed(input, length, 200), input[length - 1]);
	return 0;
}


int largest(int list[], int n, int l)
{
	int i;
	for(i = 0; i <= n; i++)
	{
		if(list[i] > l)
		{
			l = list[i];
		}
	}
	return l;
}

int largestFixed(int list[], int n, int l)
{
	int i;
	l = list[0];
	for(i = 0; i < n; i++)
	{
		if(list[i] > l)
		{
			l = list[i];
		}
	}
	return l;
}
