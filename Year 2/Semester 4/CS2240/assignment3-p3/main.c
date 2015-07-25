//Author: Marcel Englmaier

//List of Problems:
//Does not use division algorithm to do things
//Country Code doesn't output correctly
//Life Expectancy doesn't seem to work right

#include "meh.h"

#define MAX_CODE_VAL 3
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
    //variable declarations
    int Offset = 0;
	int i, j, n, firstInt, lastInt, middleInt, answerPop;
    FILE *allCountryFile, *randomStructFile, *randomStructDirFile;
    float answerLifeExp;
    char buffer[MAX_BUFFERSIZE_VAL], userInput[MAX_BUFFERSIZE_VAL], answerCode[MAX_CODE_VAL + 1], answerName[MAX_NAME_VAL];
    char *Tok;
    aStruct *structPointers;
    aDirectory *directoryPointers;
    aDirectory tempDirectory;
    
    //open appropriate files
    allCountryFile = fopen("AllCountries.dat", "r");
    if(allCountryFile == NULL) err_sys("bad open");
    randomStructFile = fopen("RandomStruct.bin", "w+");
    if(randomStructFile == NULL) err_sys("bad open");
    randomStructDirFile = fopen("RandomStructDir.bin", "w+");
    if(randomStructDirFile == NULL) err_sys("bad open");
    
    //allocate memory for structs
    structPointers = (aStruct *) calloc(atoi(argv[1]), sizeof(aStruct));
    directoryPointers = (aDirectory *) calloc(atoi(argv[1]), sizeof(aDirectory));
       
    //Loop to read allcountries, put in Struct
    for(j = 0; j < atoi(argv[1]); j++)
    {
        //fill buffer with a line
        if(fgets(buffer, MAX_BUFFERSIZE_VAL, allCountryFile) == NULL)
            err_sys("could not read");

        //skip
        Tok = strtok(buffer, ",\n"); 

        // Code
        Tok = strtok(NULL, ",\n");
        for(i = 0; i < MAX_CODE_VAL; i++)
        {  
            structPointers[j].Code[i] = Tok[i];
            directoryPointers[j].Code[i] = Tok[i];
        }
        
        //Name
        Tok = strtok(NULL, ",\n");
        for(i = 0; i < MAX_NAME_VAL; i++)
            structPointers[j].Name[i] = Tok[i];

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
    if (fwrite(structPointers, sizeof(aStruct), atoi(argv[1]), randomStructFile) != atoi(argv[1]))
        err_sys("write fail");
    
    
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
    if (fwrite (directoryPointers, sizeof(aDirectory), atoi(argv[1]), randomStructDirFile) == -1)
        err_sys("write failed\n");
    
    //close files 
    if(fclose(allCountryFile) == EOF) err_sys("could not close AllCountries.dat");
    if(fclose(randomStructFile) == EOF) err_sys("could not close RandomStruct.bin");
    if(fclose(randomStructDirFile) == EOF) err_sys("could not close RandomStructDir.bin");
    

	
//Phase two starts here**********************************************	
	
	
    
   //re-open files
    randomStructFile = fopen("RandomStruct.bin", "r");
    if(randomStructFile == NULL) err_sys("bad open");
    randomStructDirFile = fopen("RandomStructDir.bin", "r");
    if(randomStructDirFile == NULL) err_sys("bad open");
	
    //build sorted aDirectory struct
    for(j = 0; j < atoi(argv[1]); j++)
    {
        //inner loop to read and store Code
        for(i = 0; i < MAX_CODE_VAL + 1; i++)
        {
            n = fgetc(randomStructDirFile); 
            if(n == EOF) err_sys("error reading RandomStructDir.bin");
            directoryPointers[j].Code[i] = n;
        }
        n = fread(&directoryPointers[j].byteOffset, sizeof(int), 1, randomStructDirFile);
        if(n != 1) err_sys("error reading RandomStructDir.bin 2");
    }
    
    //get user input
    printf("%s", "Please input Country Code: ");
    gets(userInput);
    
    //binary search
    firstInt = 0;
    lastInt = atoi(argv[1]);
    middleInt = (firstInt + lastInt)/2;
    
    while(firstInt <= lastInt)
    {
        if(strncmp(directoryPointers[middleInt].Code, &userInput, 3) < 0)
            firstInt = middleInt + 1;
        else if(strncmp(directoryPointers[middleInt].Code, &userInput, 3) == 0)
        {
            n = fseek(randomStructFile, directoryPointers[middleInt].byteOffset, SEEK_SET);
            if(n == -1) err_sys("error seeking RandomStruct.bin");
            
            i = 0;
            
            //read Code, Name, population, life expectancy
            if(fread(answerCode, sizeof(char), MAX_CODE_VAL, randomStructFile) != MAX_CODE_VAL)
                err_sys("error reading RandomStruct.bin Code");
            answerCode[MAX_CODE_VAL + 1] = '\0';
			
            if(fread(answerName, sizeof(char), MAX_NAME_VAL, randomStructFile) !=  MAX_NAME_VAL) 
                err_sys("error reading RandomStruct.bin Name");
            answerName[MAX_NAME_VAL + 1] = '\0';
            
            if(fread(&answerPop, 4, 1, randomStructFile) != 1)
                err_sys("error reading RandomStruct.bin Pop");
            
            if(fread(&answerLifeExp, 4, 1, randomStructFile) != 1)
                err_sys("error reading RandomStruct.bin LifeExp");
            
            //print ALL the things
            printf("%s, %s, %d, %f\n", answerCode, answerName, answerPop, answerLifeExp);
            
            //leave search
            break;
        }
        else
            lastInt = middleInt - 1;
        middleInt = (firstInt + lastInt)/2;
    } 
    
    //close opened files
    if(fclose(randomStructFile) == EOF) err_sys("could not close RandomStruct.bin");
    if(fclose(randomStructDirFile) == EOF) err_sys("could not close RandomStructDir.bin");
   
   
    return (0);
}

