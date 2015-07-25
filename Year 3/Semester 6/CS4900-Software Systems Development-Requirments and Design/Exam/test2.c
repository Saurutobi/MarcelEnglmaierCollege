#include <stdio.h>

int largest(int [], int);
int max(int,int);

int main()
{

	int length = 10;
	int input[length];
	int i = 0;
	for(i = 0; i < length; i++)
	{
		input[i] = i;
	}
	int n = 5;
	printf("Testing with max(a,b) returning b for n=%d\n", n);
	printf("Expected=%d returned=%d\n", input[n - 1], largest(input, n));


	return 0;
}

int largest(int list[], int n)
{
	int i;
	int l;
	l = list[0];
	for(i = 1; i < n; i++)
	{
		l = max(l, list[i]);
	}
	return l;
}

int max(int a, int b)
{
	return b;
}
