#include <stdio.h>
#include <stdlib.h>

void doMeASearch(int,int,int);	//primitive or whatever\

int datMatrix[49]; //[][] datMatrix of 1s and 0s
int lengthOfFullArray;	//length of the Full Array? (duh)
int lengthOfSingleLine;	//length of a Single line (duh) I have naming conventions for a reason

int main(int argc, char **argv)
{
	//initialize them variables
    lengthOfFullArray = 49;
	lengthOfSingleLine = 7;
	int numberToInsert = 2;
	int fullArrayCounter = 0;
	int virtualPositionOfX = 0;
	int virtualPositionOfY = 0;

	srand(time(NULL));	

	//build random datMatrix
	for(fullArrayCounter = 0; fullArrayCounter < lengthOfFullArray; fullArrayCounter++)
	{
	    datMatrix[fullArrayCounter] = (int)rand() % 2;	//this outputs either a 1 or 0
	    printf("%4d", datMatrix[fullArrayCounter]);	//print it here for initial output
	    
		//these lines 'virtually' move down a row
	    if(virtualPositionOfY == lengthOfSingleLine - 1)
	    {
	        printf("\n");
	        virtualPositionOfY = -1;
	    }
	    virtualPositionOfY++;
	}
	
	//do the search
	virtualPositionOfX = 0;
	virtualPositionOfY = 0;
	for(fullArrayCounter = 0; fullArrayCounter < lengthOfFullArray; fullArrayCounter++)
	{
		//if we've hit on the number of interest
	    if(datMatrix[fullArrayCounter] == 1)
		{
			//do some quick variable magic so output is nicely readable
		    int nicelyFormattedX = virtualPositionOfX + 1;
		    int nicelyFormattedY = virtualPositionOfY + 1;
		    //print the status message. Originally this was just a debug thing, but now it's nice to look at
			printf("\nfound [%3d,%3d], inserting: %d", nicelyFormattedX, nicelyFormattedY, numberToInsert);
			//begin the recursive search
			doMeASearch(virtualPositionOfX, virtualPositionOfY, numberToInsert);
			numberToInsert++;
		}
		
		//these lines 'virtually' move down a row
	    if(virtualPositionOfY == lengthOfSingleLine)
	    {
	        virtualPositionOfY = 0;
	        virtualPositionOfX++;
	    }
	    virtualPositionOfY++;
	}
	
	//print out completed datMatrix, same as the first loop without the rand()
	printf("\n\n");
	virtualPositionOfY = 0;
	for(fullArrayCounter = 0; fullArrayCounter < lengthOfFullArray; fullArrayCounter++)
	{
	    printf("%4d", datMatrix[fullArrayCounter]);	//print it here for initial output
	    
		//these lines 'virtually' move down a row
	    if(virtualPositionOfY == lengthOfSingleLine - 1)
	    {
	        printf("\n");
	        virtualPositionOfY = -1;
	    }
	    virtualPositionOfY++;
	}
	
	//and now we be done
	return 0;
}

//this method does linear recursive magic
void doMeASearch(int virtualPositionOfX, int virtualPositionOfY, int placeholder)
{
    //get the location we're at right now
	int locationInFullArray = (virtualPositionOfX * lengthOfSingleLine) + virtualPositionOfY;
	
	//start by magicking(?) the current number
	datMatrix[locationInFullArray] = placeholder;
    
	//check right(if it's within bounds, not 0, and not the current number. I did this for making the merging in the parallel version easier).
	if((virtualPositionOfY != lengthOfSingleLine - 1) && datMatrix[locationInFullArray + 1] != 0 && datMatrix[locationInFullArray + 1] != placeholder)
	{
		doMeASearch(virtualPositionOfX, virtualPositionOfY + 1, placeholder);
	}
	
	//I made this so I didn't have to remake locationInFullArray for X movement. Just looks cleaner
	int secondLoc = ((virtualPositionOfX + 1) * lengthOfSingleLine) + virtualPositionOfY;
	
	//check bottom(if it's within bounds, not 0, and not the current number. I did this for making the merging in the parallel version easier).
	if((virtualPositionOfX != lengthOfSingleLine - 1) && datMatrix[secondLoc] != 0 && datMatrix[secondLoc] != placeholder)
	{
		doMeASearch(virtualPositionOfX + 1, virtualPositionOfY, placeholder);
	}
	//check left(if it's within bounds, not 0, and not the current number. I did this for making the merging in the parallel version easier).
	if((virtualPositionOfY != 0) && datMatrix[locationInFullArray - 1] != 0 && datMatrix[locationInFullArray - 1] != placeholder)
	{
		doMeASearch(virtualPositionOfX, virtualPositionOfY - 1, placeholder);
	}

	//remake it for the other movement in Y
	secondLoc = ((virtualPositionOfX - 1) * lengthOfSingleLine) + virtualPositionOfY;
    
	//check top(if it's within bounds, not 0, and not the current number. I did this for making the merging in the parallel version easier).
	if((virtualPositionOfX != 0) && datMatrix[secondLoc] != 0 && datMatrix[secondLoc] != placeholder)
	{
		doMeASearch(virtualPositionOfX - 1, virtualPositionOfY, placeholder);
	}
}
