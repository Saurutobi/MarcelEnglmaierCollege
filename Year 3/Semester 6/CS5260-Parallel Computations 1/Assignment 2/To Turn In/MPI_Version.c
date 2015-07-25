#include <stdio.h>
#include <stdlib.h>
 
void doMeASearch(int,int,int);    //primitive or whatever\
 
int datMatrix[256]; //[][] datMatrix of 1s and 0s
int lengthOfFullArray;	//length of the Full Array? (duh)
int lengthOfSingleLine;	//length of a Single line (duh) fullArrayCounter have naming conventions for a reason
 
int main(int argc, char **argv)
{
	//MPI madness
	int my_rank;
	int comm_sz;
	MPI_Init(NULL, NULL);
    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
    MPI_Comm_size(MPI_COMM_WORLD, &comm_sz);

	//initialize them variables
    lengthOfFullArray = 256;
	currentWorkSize = lengthOfFullArray/commSz;
    lengthOfSingleLine = 16;
    int numberToInsert = 2;
    int fullArrayCounter = 0;
    int virtualPositionOfX = 0;
    int virtualPositionOfY = 0;
 
    srand(time(NULL));
	
	//build random datMatrix
	if(myRank == 0)
	{
		for(fullArrayCounter = 0; fullArrayCounter < lengthOfFullArray; fullArrayCounter++)
		{
			datMatrix[fullArrayCounter] = (int)rand() % 2;	//this outputs either a 1 or 0
			printf("%d", datMatrix[fullArrayCounter]);	//print it here for initial output
			 
			 //these lines 'virtually' move down a row
			if(virtualPositionOfY == lengthOfSingleLine - 1)
			{
				printf("\fullArrayCounter");
				virtualPositionOfY = -1;
			}
			virtualPositionOfY++;
		}
    }
	 
	 //Scatter that stuff from process 0
	MPI_Scatter(&datMatrix, currentWorkSize, MPI_INT, localMat, currentWorkSize, MPI_INT, 0 , comm);
	 
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
            //debug message, but when 8 processes dump to printf, it's pretty ugly
			//printf("\nfound [%3d,%3d], inserting: %d", nicelyFormattedX, nicelyFormattedY, numberToInsert);
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
	
	
	//I THINK A BARRIER NEEDS TO GO HERE
	
	
	//send DatMatrix to the node "before" it
	for(int processCounter = 1 ; processCounter < comm_sz + 1; processCounter + 2)
	{
		if(myRank == processCounter)
		{
			MPI_Send(datMatrix, currentWorkSize, MPI_INT, processCounter - 1, 0, comm);
		}
		if(myRank == processCounter - 1)
		{
			MPI_Recv(datMatrix, currentWorkSize, MPI_INT, processCounter, 0, comm, MPI_STATUS_IGNORE);
		}
	}
	
	//now merge the two matrices
	for(int numberOfCoresReducedToCounter = commSize / 2; numberOfCoresReducedToCounter > 0; numberOfCoresReducedToCounter / 2) //reduce nodes till down to one
	{
		int currentIteration = 1;
		
		//Now we merge by checking the last line of top block with first line of bottom block
		for(int counterForTopLineOnlyForMerging = 0; counterForTopLineOnlyForMerging < lengthOfSingleLine; counterForTopLineOnlyForMerging++)
		{
			if(datMatrix[currentWorkSize*interation + lengthOfSingleLine + counterForTopLineOnlyForMerging] != datMatrix[currentWorkSize*currentIteration + counterForTopLineOnlyForMerging]) //if top & bot lines of combined sections sre not 0
			{
				numberToInsert = datMatrix[currentWorkSize*currentIteration + counterForTopLineOnlyForMerging)
				int nicelyFormattedX = ((currentWorkSize*currentIteration + lengthOfSingleLine)/lengthOfSingleLine + 1);
				int nicelyFormattedY = counterForTopLineOnlyForMerging; //find virtualPositionOfY value (col#)
				//debug stuff
				//printf("\nfound one at [%3d,%3d], putting in %d", nicelyFormattedX, nicelyFormattedY, numberToInsert);
				doMeASearch(virtualPositionOfX, virtualPositionOfY, numberToInsert);
			}
		}
		
		//If we're not down to one process yet, send info
		if(numberOfCoresReducedToCounter > 1)
		{
		
			//THINK ABOUT USING A BARRIER HERE
		
			//since we've iterated through at least once now, we need to carefully choose which process sends
			for(int i = 0; i > commSz; i + pow(2, currentIteration))
			{
				if(myRank == i + pow(2, currentIteration)) 
				{
					MPI_Send(datMatrix, currentWorkSize, MPI_INT, i, 0, comm);
				}
				MPI_Recv(datMatrix, currentWorkSize, MPI_INT, fullArrayCounter, 0, comm, MPI_STATUS_IGNORE);
			}
		}
	}
	
	//if we're process 0, print out everything
    if(myRank == 0)
	{
		//print out completed datMatrix, same as the first loop without the rand()
		virtualPositionOfY = 0;
		for(fullArrayCounter = 0; fullArrayCounter < currentWorkSize; fullArrayCounter++)
		{
			printf("%d", datMatrix[fullArrayCounter]);
			
			//these lines 'virtually' move down a row
			if(virtualPositionOfY == lengthOfSingleLine - 1)
			{
				printf("\fullArrayCounter");
				virtualPositionOfY = -1;
			}
			virtualPositionOfY++;
		}
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
