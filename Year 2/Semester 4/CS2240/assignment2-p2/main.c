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
    int allCountryFile;
	int randomStructFile;
	int randomStructDirFile;
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
    aDirectory *directoryPointers, *directoryPointersTwo;
    aDirectory tempDirectory;
    
    //open appropriate files
    allCountryFile = open("AllCountries.dat", O_RDONLY);
    if(allCountryFile == -1)
	{
		err_sys("error opening AllCountries.dat");
	}
    randomStructFile = open("RandomStruct.bin", O_CREAT | O_TRUNC | O_RDWR, S_IRUSR | S_IWUSR);
    if(randomStructFile == -1)
	{
		err_sys("error opening RandomStuct.bin");
	}
    randomStructDirFile = open("RandomStructDir.bin", O_CREAT | O_TRUNC | O_RDWR, S_IRUSR | S_IWUSR);
    if(randomStructDirFile == -1)
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
        i = 0;    
        do 
        {
            n = read(allCountryFile,&aChar,1); 
            if(n == -1) err_sys("error reading AllCountries.dat");
            buffer[i++] = aChar;
        } while(aChar != '\n');

        //skip
        Tok = strtok(buffer, ",\n"); 

        // Code
        Tok = strtok(NULL, ",\n");
        n = strlen(Tok);
        for(i = 0; i < n; i++)
        {  
            structPointers[j].Code[i] = Tok[i];
            directoryPointers[j].Code[i] = Tok[i];
        }
        
        //Name
        Tok = strtok(NULL, ",\n");
        n = strlen(Tok);
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
        n = strlen(Tok);
        structPointers[j].Population = atoi(Tok);

        //life expectancy
        Tok = strtok(NULL, ",\n"); 
        n = strlen(Tok);
        structPointers[j].LifeExpectancy = atof(Tok);
        
        //write byte offset and increment
        directoryPointers[j].byteOffset = Offset;
        Offset += sizeof(aStruct);
        
        //empty the buffer
        memset(buffer, 0, 200);
    }
    
    //write contents of aStruct struct to RandomStruct.bin
    if(write (randomStructFile, structPointers, sizeof(aStruct)*atoi(argv[1])) == -1)
	{
		err_sys("write\n");
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
    if(write(randomStructDirFile, directoryPointers, sizeof(aDirectory)*atoi(argv[1])) == -1)
	{
        err_sys("write\n");
	}
    
    //close files
    if(close(allCountryFile) == -1)
	{
		err_sys("could not close AllCountries.dat");
	}
    if(close(randomStructFile) == -1)
	{
		err_sys("could not close RandomStruct.bin");
	}
    if(close(randomStructDirFile) == -1)
	{
		err_sys("could not close RandomStructDir.bin");
	}
    

	
//Phase two starts here**********************************************	
	
	
    
    //re-open files
    allCountryFile = open("RandomStruct.bin", O_RDWR, S_IRUSR | S_IWUSR);
    if(allCountryFile == -1)
	{
		err_sys("Phase2: error opening RandomStruct.bin");
	}
    randomStructFile = open("RandomStructDir.bin", O_RDWR, S_IRUSR | S_IWUSR);
    if(randomStructFile == -1)
	{
		err_sys("Phase2:error opening RandomStructDir.bin");
	}
    
    //build sorted aDirectory struct
    for(j = 0; j < atoi(argv[1]); j++)
    {
        //inner loop to read and store Code
        for(i = 0; i < MAX_CODE_VAL; i++)
        {
            n = read(randomStructFile,&aChar,1); 
            if(n == -1)
			{
				err_sys("error reading RandomStructDir.bin");
			}
            directoryPointers[j].Code[i] = aChar;
        }
        n = read(randomStructFile, &directoryPointers[j].byteOffset, 4);
        if(n == -1)
		{
			err_sys("error reading RandomStructDir.bin 2");
		}
    }
    
	
	//user interface part
	int inputquery;
	int cont = 1;
	while(cont == 1)
	{
		//asks user for input then reads it
		if(write(STDOUT_FILENO, "\nWhat would you like to do?\n[f] for find, [q] for quit, [m] for more options\n", 77) == -1)
		{
			err_sys("error printing query");
		}
		inputquery = read(STDIN_FILENO, userInput, MAX_BUFFERSIZE_VAL);
		if(inputquery == -1)
		{
			err_sys("error while reading user input");
		}
		if(strncmp("f", &userInput, 1) == 0)
		{
			if(write(STDOUT_FILENO, "\nPlease enter a 3 letter code(In matching case)\n", 48) == -1)
			{
				err_sys("error printing Code query");
			}
			n = read(STDIN_FILENO, userInput, MAX_BUFFERSIZE_VAL);
			if(n == -1)
			{
				err_sys("error reading from standard in");
			}
    
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
					n = lseek(allCountryFile, directoryPointers[middleInt].byteOffset, SEEK_SET);
					if(n == -1)
					{
						err_sys("error seeking RandomStruct.bin");
					}
					
					i = 0;
            
					//read Code               
					n = read(allCountryFile,answerCode,MAX_CODE_VAL); 
					if(n == -1)
					{
						err_sys("error reading RandomStruct.bin");
					}
					answerCode[MAX_CODE_VAL + 1] = '\0';
					if(write(STDOUT_FILENO, "--", 2) == -1)
					{
						err_sys("error printing '--'");
					}
					if(write(STDOUT_FILENO, answerCode, sizeof(answerCode)) == -1)
					{
						err_sys("error printing Code");
					}
					if(write(STDOUT_FILENO, ", ", 2) == -1)
					{
						err_sys("error printing ', '");
					}

					//read Name
					n = read(allCountryFile,answerName,MAX_NAME_VAL); 
					if(n == -1)
					{
						err_sys("error reading RandomStruct.bin");
					}
					answerName[MAX_NAME_VAL + 1] = '\0';
					if(write(STDOUT_FILENO, answerName, sizeof(answerName)) == -1)
					{
						err_sys("error printing Code");
					}
					if(write(STDOUT_FILENO, ", ", 2) == -1)
					{
						err_sys("error printing ', '");
					}
            
					//read Population
					n = read(allCountryFile, &answerPop, 4);
					if(n == -1)
					{
						err_sys("error reading RandomStruct.bin");
					}
					//I used Printf here because I didn't quite know how to print it correctly
					printf("%d, ", answerPop);
            
					//read LifeExpectancy
					n = read(allCountryFile, &answerLifeExp, 4);
					if(n == -1)
					{
						err_sys("error reading RandomStruct.bin");
					}
					//same here
					printf("%f\n", answerLifeExp);
					
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
			if(write(STDOUT_FILENO, "--no further options available to you:P\n", 40) == -1)
			{
				//ha!
				err_sys("error printing FU message");
			}
		}
	}
	if(write(STDOUT_FILENO, "--Goodbye\n", 10) == -1)
	{
		err_sys("error printing goodbye message");
	}
    
    return (0);
}
