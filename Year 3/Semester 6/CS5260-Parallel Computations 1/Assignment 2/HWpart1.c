


//run findAndChange

#include <stdio.h>
#include <stdlib.h>
 
void findAndChange(int,int,int);    //primitive or whatever\
 
int matrix[49]; //[][] matrix of 1s and 0s
int arrayLength;
int lineLength;
int insert;
 
int main(int argc, char **argv)
{
	int my_rank, comm_sz;

	/* Let the system do what it needs to start up MPI */
   MPI_Init(NULL, NULL);

   /* Get my process rank */
   MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);

   /* Start timer in process with rank 0 */
   //if(my_rank == 0) startwtime = MPI_Wtime();

   /* Find out how many processes are being used */
   MPI_Comm_size(MPI_COMM_WORLD, &comm_sz);

	
	 
    arrayLength = 256;
	size = arrayLength/commSz;
    lineLength = 16;
    insert = 2;
    int i = 0;
    int x = 0;
    int y = 0;
 
    srand(time(NULL));  
 
    //build matrix
    for(i = 0; i < arrayLength; i++)
    {
        matrix[i] = (int)rand() % 2;
        printf("%d", matrix[i]);
         
        if(y == lineLength - 1)
        {
            printf("\n");
            y = -1;
        }
        y++;
    }
     
	 MPI_Scatter(matrix, size, MPI_INT, localMat, size, MPI_INT, 0 , comm);
	 
	 

	 
    //do the search
    x = 0;
    y = 0;
    for(i = 0; i < arrayLength; i++)
    {
        if(matrix[i] == 1)
        {
		
			/*The Problem might be that its changing x everytime it runs through this. 
			meaning every iteration moves down a row instead of across y*/
            
			int xOut = x + 1;
            int yOut = y + 1;
            printf("\nfound one at [%3d,%3d], putting in %d", xOut, yOut, insert);
            findAndChange(x, y, insert);
            insert++;
        }
        if(y == lineLength)
        {
            y = 0;
            x++;
        }
        y++;
    }
	for(int i = 1 ; i < comm_sz + 1; i+2) //send matrix from odd nodes to even nodes
	{
		MPI_Send(matrix, size, MPI_INT, i-1, 0, comm);
		MPI_Recv(matrix, size, MPI_INT, i, 0, comm, MPI_STATUS_IGNORE);
	}
	
	for(int k = commSize/2; k > 0; k / 2) //reduce nodes till down to one
	{
		int iteration = 1;
		
			for(i = 0; i < lineLength; i++)
			{
				if(matrix[size*interation + lineLength + i] != 0 && matrix[size*interation + i] != 0) //if top & bot lines of combined sections sre not 0
				{
					
					insert = matrix[size*interation + i) //set insert to value of top line
					
					int xOut = ((size*interation + lineLength)/lineLength + 1); //find x value (row#)
					int yOut = i; //find y value (col#)
					printf("\nfound one at [%3d,%3d], putting in %d", xOut, yOut, insert);
					findAndChange(x, y, insert);
					
				}
				
			}
			
			/* I am not sure about the ability to specify just one processor to send from instead of all of them. I am emailing dedoncker now*/ 
		if(K > 1)
		{
			MPI_Send(matrix, size, MPI_INT, i-1, 0, comm);
			MPI_Recv(matrix, size, MPI_INT, i, 0, comm, MPI_STATUS_IGNORE);
		}
		
	}
     
    //print out completed matrix
    printf("\n\n proc %d of %d", my_rank, comm_sz);
    y = 0;
    for(i = 0; i < size; i++)
    {
        printf("%d", matrix[i]);
        if(y == lineLength - 1)
        {
            printf("\n");
            y = -1;
        }
        y++;
    }
     
    //and done
    return 0;
}
 
void findAndChange(int x, int y, int placeholder)
{
    //get the location
    int loc = (x * lineLength) + y;
     
    //start by changing the current unit
    matrix[loc] = placeholder;
     
    //check right
    if((y != lineLength - 1) && matrix[loc + 1] != 0 && matrix[loc + 1] != placeholder)
    {
        findAndChange(x, y + 1, placeholder);
    }
     
    int secondLoc = ((x + 1) * lineLength) + y;
     
    //check bottom
    if((x != lineLength - 1) && matrix[secondLoc] != 0 && matrix[secondLoc] != placeholder)
    {
        findAndChange(x + 1, y, placeholder);
    }
    //check left
    if((y != 0) && matrix[loc - 1] != 0 && matrix[loc - 1] != placeholder)
    {
        findAndChange(x, y - 1, placeholder);
    }
 
    secondLoc = ((x - 1) * lineLength) + y;
     
    //check top
    if((x != 0) && matrix[secondLoc] != 0 && matrix[secondLoc] != placeholder)
    {
        findAndChange(x - 1, y, placeholder);
    }
}

