
int maxnumber[100];
int counter = -1;

int mock_max_setup(int a[], int n)
{
	int i;
	for(i = 0; i < n; i++)
	{
		maxnumber[i] = a[i];
	}
}

int max(int a, int b)
{
	counter++;
	return maxnumber[counter];
}
