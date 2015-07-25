#include <stdio.h>
#include <stdlib.h>

void doMeASearch(int,int,int);	//primitive or whatever\

int datMatrix[20][20]; //[][] datMatrix of 1s and 0s
int lengthOfX;	//length of X axis
int lengthOfY;	//length of Y axis

int main(int argc, char **argv)
{
	//initialize them variables
	lengthOfX = 20;
	lengthOfY = 20;
	int numberToInsert = 2;	//starting at 2 because 1 is already being used
	int xCounter = 0;
	int yCounter = 0;

	srand(time(NULL));	

	//build random datMatrix
	for(xCounter = 0; xCounter < lengthOfX; xCounter++)
	{
		for(yCounter = 0; yCounter < lengthOfY; yCounter++)
		{
			datMatrix[xCounter][yCounter] = (int)rand() % 2;	//this outputs either a 1 or 0
			printf(" %4d ", datMatrix[xCounter][yCounter]);	//print it here for initial output
		}
		printf("\n");
	}
	
	//do the search
	for(xCounter = 0; xCounter < lengthOfX; xCounter++)
	{
		for(yCounter = 0; yCounter < lengthOfY; yCounter++)
		{
			//if we've hit on the number of interest
			if(datMatrix[xCounter][yCounter] == 1)
			{
				//do some quick variable magic so output is nicely readable
			    int nicelyFormattedX = xCounter + 1;
			    int nicelyFormattedY = yCounter + 1;
				//print the status message. Originally this was just a debug thing, but now it's nice to look at
			    printf("\nfound [%3d,%3d], inserting: %d", nicelyFormattedX, nicelyFormattedY, numberToInsert);
				//begin the recursive search
				doMeASearch(xCounter, yCounter, numberToInsert);
				numberToInsert++;
			}
		}
	}
	
	//print out completed datMatrix, same as the first loop without the rand()
	printf("\n\n");
	for(xCounter = 0; xCounter < lengthOfX; xCounter++)
	{
		for(yCounter = 0; yCounter < lengthOfY; yCounter++)
		{
			printf(" %4d ", datMatrix[xCounter][yCounter]);
		}
		printf("\n");
	}
	
	//and now we be done
	return 0;
}

//this method does recursive magic
void doMeASearch(int x, int y, int thisBeTheNumberToInsert)
{
	//start by magicking(?) the current number
	datMatrix[x][y] = thisBeTheNumberToInsert;
	
	//check right(if it's within bounds, not 0, and not the current number. I did this for making the merging in the parallel version easier).
	if((y != lengthOfY - 1) && datMatrix[x][y + 1] != 0 && datMatrix[x][y + 1] != thisBeTheNumberToInsert)
	{
		doMeASearch(x, y + 1, thisBeTheNumberToInsert);
	}
	//check bottom(if it's within bounds, not 0, and not the current number. I did this for making the merging in the parallel version easier).
	if((x != lengthOfX - 1) && datMatrix[x + 1][y] != 0 && datMatrix[x + 1][y] != thisBeTheNumberToInsert)
	{
		doMeASearch(x + 1, y, thisBeTheNumberToInsert);
	}
	//check left(if it's within bounds, not 0, and not the current number. I did this for making the merging in the parallel version easier).
	if((y != 0) && datMatrix[x][y - 1] != 0 && datMatrix[x][y - 1] != thisBeTheNumberToInsert)
	{
		doMeASearch(x, y - 1, thisBeTheNumberToInsert);
	}
	//check top(if it's within bounds, not 0, and not the current number. I did this for making the merging in the parallel version easier).
	if((x != 0) && datMatrix[x - 1][y] != 0 && datMatrix[x - 1][y] != thisBeTheNumberToInsert)
	{
		doMeASearch(x - 1, y, thisBeTheNumberToInsert);
	}
}
