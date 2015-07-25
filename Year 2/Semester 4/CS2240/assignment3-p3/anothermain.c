//Author: Marcel Englmaier

//List of Problems:
//Does not use division algorithm to do things

#include "meh.h"

#define MAX_CODE_VAL 4
#define MAX_NAME_VAL 20
#define MAX_BUFFERSIZE_VAL 200


typedef struct {
    char Code[MAX_CODE_VAL];
    char Name[MAX_NAME_VAL];
    int Population; 
    float LifeExpectancy;
} aStruct;

typedef struct {
    char Code[MAX_CODE_VAL];
    int byteOffset;
} aDirectory;

int main(int argc, char** argv) 
{
	//File declarations
    FILE *allCountryFile;
	FILE *randomStructFile;
	FILE *randomStructDirFile;
	//variable declarations
	int Offset = 0;
	int i;
	int j;
	int n;
	int firstInt;
	int lastInt;
	int middleInt;
	int answerPop;
	float answerLifeExp;
    char buffer[MAX_BUFFERSIZE_VAL];
	char userInput[MAX_BUFFERSIZE_VAL];
	char answerCode[MAX_CODE_VAL + 1];
	char answerName[MAX_NAME_VAL + 1];
	char aChar;
    char *Tok, *Instruction;
    aStruct *structPointers;
    aDirectory *directoryPointers;
    aDirectory tempDirectory;
    
    //open appropriate files
    allCountryFile = fopen("AllCountries.dat", "r");
    if(allCountryFile == NULL)
	{
		err_sys("error opening AllCountries.dat");
	}
    randomStructFile = fopen("RandomStruct.bin", "w+");
    if(randomStructFile == NULL)
	{
		err_sys("error opening RandomStuct.bin");
	}
    randomStructDirFile = fopen("RandomStructDir.bin", "w+");
    if(randomStructDirFile == NULL)
	{
		err_sys("error opening RandomStructDir.bin");
	}
    
    //allocate memory for structs
    structPointers = (aStruct *) calloc(atoi(argv[1]), sizeof(aStruct));
    directoryPointers = (aDirectory *) calloc(atoi(argv[1]), sizeof(aDirectory));
       
    //Loop to read allcountries, put in Struct
    for(j = 0; j < atoi(argv[1]); j++)
    {
        //fill buffer with a line
            if(fgets(buffer, MAX_BUFFERSIZE_VAL, allCountryFile) == NULL)
			{
				err_sys("error reading AllCountries.dat");
			}

        //skip
        Tok = strtok(buffer, ",\n"); 

        // Code
        Tok = strtok(NULL, ",\n");
        for(i = 0; i < n; i++)
        {  
            structPointers[j].Code[i] = Tok[i];
            directoryPointers[j].Code[i] = Tok[i];
        }
        
        //Name
        Tok = strtok(NULL, ",\n");
        for(i = 0; i < n; i++)
		{
            structPointers[j].Name[i] = Tok[i];
		}

        //skip these
        Tok = strtok(NULL, ",\n"); 
        Tok = strtok(NULL, ",\n");
        Tok = strtok(NULL, ",\n"); 
        Tok = strtok(NULL, ",\n"); 
        
        //population
        Tok = strtok(NULL, ",\n"); 
        structPointers[j].Population = atoi(Tok);

        //life expectancy
        Tok = strtok(NULL, ",\n"); 
        structPointers[j].LifeExpectancy = atof(Tok);
        
        //write byte offset and increment
        directoryPointers[j].byteOffset = Offset;
        Offset += sizeof(aStruct);
        
        //empty the buffer
        memset(buffer, 0, 200);
    }
    
    //write contents of aStruct struct to RandomStruct.bin
    if(fwrite(structPointers, sizeof(aStruct), atoi(argv[1]), randomStructFile) != atoi(argv[1]))
	{
		err_sys("write didn't work...");
	}
    
    //bubble sort the aDirectory struct
    for(j = 0; j < atoi(argv[1]); j++)
    {
        for(i = j + 1; i < atoi(argv[1]); i++)
        {
            if(strncmp(directoryPointers[j].Code, directoryPointers[i].Code, 3) > 0)
            {
                tempDirectory = directoryPointers[j];
                directoryPointers[j] = directoryPointers[i];
                directoryPointers[i] = tempDirectory;
            }
        }
    }
    
    //write contents of aDirectory struct to RandomStructDir.bin
	if(fwrite(directoryPointers, sizeof(aDirectory), atoi(argv[1]), randomStructDirFile) == -1)
	{
        err_sys("write\n");
	}
    
    //close files
    if(fclose(allCountryFile) == EOF)
	{
		err_sys("could not close AllCountries.dat");
	}
    if(fclose(randomStructFile) == EOF)
	{
		err_sys("could not close RandomStruct.bin");
	}
    if(fclose(randomStructDirFile) == EOF)
	{
		err_sys("could not close RandomStructDir.bin");
	}
    

	
//Phase two starts here**********************************************	
	
	
    
    //re-open files
    randomStructFile = fopen("RandomStruct.bin", "r");
    if(randomStructFile == NULL)
	{
		err_sys("Phase2: error opening RandomStruct.bin");
	}
    randomStructDirFile = fopen("RandomStructDir.bin", "r");
    if(randomStructDirFile == NULL)
	{
		err_sys("Phase2: error opening RandomStructDir.bin");
	}
    
    //build sorted aDirectory struct
    for(j = 0; j < atoi(argv[1]); j++)
    {
        //inner loop to read and store Code
        for(i = 0; i < MAX_CODE_VAL; i++)
        {
            n = fgetc(randomStructDirFile); 
            if(n == EOF)
			{
				err_sys("error reading RandomStructDir.bin");
			}
            directoryPointers[j].Code[i] = n;
        }
        n = fread(&directoryPointers[j].byteOffset, sizeof(int), 1, randomStructDirFile);
        if(n != 1)
		{
			err_sys("error reading RandomStructDir.bin 2");
		}
    }
    
	//user interface part
	int cont = 1;
	while(cont == 1)
	{
		//asks user for input then reads it
		printf("\nWhat would you like to do?\n[f] for find, [q] for quit, [m] for more options\n");
		gets(userInput);
		
		if(strncmp("f", &userInput, 1) == 0)
		{
			printf("\nPlease enter a 3 letter code(In matching case)\n");
			gets(userInput);
    
			//binary search
			firstInt = 0;
			lastInt = atoi(argv[1]);
			middleInt = (firstInt + lastInt)/2;
			while(firstInt <= lastInt)
			{
				if(strncmp(directoryPointers[middleInt].Code, &userInput, 3) < 0)
				{
					firstInt = middleInt + 1;
				}
				else if(strncmp(directoryPointers[middleInt].Code, &userInput, 3) == 0)
				{
					n = fseek(randomStructFile, directoryPointers[middleInt].byteOffset, SEEK_SET);
					if(n == -1)
					{
						err_sys("error seeking RandomStruct.bin");
					}
					
					i = 0;
            
					//read Code
					if(fread(answerCode, sizeof(char), MAX_CODE_VAL, randomStructFile) != MAX_CODE_VAL)
					{
						err_sys("error reading RandomStruct.bin");
					}
					answerCode[MAX_CODE_VAL + 1] = '\0';
					
					//read Name
					if(fread(answerName, sizeof(char), MAX_NAME_VAL, randomStructFile) != MAX_NAME_VAL)
					{
						err_sys("error reading RandomStruct.bin");
					}
					answerName[MAX_NAME_VAL + 1] = '\0';
            
					//read Population
					if(fread(&answerPop, 4, 1, randomStructFile) != 1)
					{
						err_sys("error reading RandomStruct.bin");
					}
            
					//read LifeExpectancy
					if(fread(&answerLifeExp, 4, 1, randomStructFile) != 1)
					{
						err_sys("error reading RandomStruct.bin");
					}
					
					//print output
					printf("---%s, %s, %d, %f\n", answerCode, answerName, answerPop, answerLifeExp);
					
					//leave search
					break;
				}else
				{
					lastInt = middleInt - 1;
				}
				middleInt = (firstInt + lastInt)/2;
			}
		}
		if(strncmp("q", &userInput, 1) == 0)
		{
			cont = 0;
		}
		if(strncmp("m", &userInput, 1) == 0)
		{
			printf("--no further options available to you:P\n");
		}
	}
	printf("--Goodbye\n");	
	
	if(fclose(randomStructFile) == EOF)
	{
		err_sys("could not close RandomStruct.bin");
	}
	if(fclose(randomStructDirFile) == EOF)
	{
		err_sys("could not close RandomStructDir.bin");
	}
	
    return (0);
}
