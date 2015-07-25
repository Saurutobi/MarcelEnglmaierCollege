/* 
 * File:   main.c
 * Author: thomas
 *
 * Created on January 28, 2013, 2:33 PM
 */

#include <fcntl.h>
#include "apue.h"
#include <stdio.h>

#define CODE_MAX 3
#define NAME_MAX 20
#define BUF_MAX 200

typedef struct {
    char code[CODE_MAX];
    char name[NAME_MAX];
    int Population; 
    float LifeExpectancy;
} Country; 

typedef struct {
    char code[CODE_MAX];
    int byteOffset;
} Directory;

int main(int argc, char** argv) 
{
    //variable declarations
    int Offset, I, J, N, first, last, middle, AnswerPop;
    FILE *file1, *file2, *file3;
    float AnswerLifeExp;
    char Buffer[BUF_MAX], UserInput[BUF_MAX], AnswerCode[CODE_MAX + 1], AnswerName[NAME_MAX];
    char *Tok;
    Country *CountryArray;
    Directory *DirectoryArray, *DirectoryArrayTwo;
    Directory TempDir;
    
    //open appropriate files
    file1 = fopen("AllCountries.dat", "r");
    if(file1 == NULL) err_sys("bad open");
    file2 = fopen("RandomStructs.bin", "w+");
    if(file2 == NULL) err_sys("bad open");
    file3 = fopen("RandomStructDir.bin", "w+");
    if(file3 == NULL) err_sys("bad open");
    
    //allocate memory for structs
    CountryArray = (Country *) calloc(atoi(argv[1]), sizeof(Country));
    DirectoryArray = (Directory *) calloc(atoi(argv[1]), sizeof(Directory));
    
    //reset byte offset
    Offset = 0;
       
    //Loop to read AllCountries.dat and store in struct, ready to be written 
    //to RandomStruct.bin
    for(J = 0; J < atoi(argv[1]); J++)
    {
        //Step 1: Fill the buffer with a line
        if(fgets(Buffer, BUF_MAX, file1) == NULL)
            err_sys("could not read");

        //Step 2: split into tokens and store appropriate values in struct
        //skip
        Tok = strtok(Buffer, ",\n"); 

        //code
        Tok = strtok(NULL, ",\n");
        for(I = 0; I < CODE_MAX; I++)
        {  
            CountryArray[J].code[I] = Tok[I];
            DirectoryArray[J].code[I] = Tok[I];
        }
        
        
        //name
        Tok = strtok(NULL, ",\n");
        for(I = 0; I < NAME_MAX; I++)
            CountryArray[J].name[I] = Tok[I];

        //skip
        Tok = strtok(NULL, ",\n"); 
        Tok = strtok(NULL, ",\n");
        Tok = strtok(NULL, ",\n"); 
        Tok = strtok(NULL, ",\n"); 
        
        //population
        Tok = strtok(NULL, ",\n"); 
        CountryArray[J].Population = atoi(Tok);

        //life expectancy
        Tok = strtok(NULL, ",\n"); 
        CountryArray[J].LifeExpectancy = atof(Tok);
        
        //write byte offset and increment
        DirectoryArray[J].byteOffset = Offset;
        Offset += sizeof(Country);
        
        //Step 3: flush buffer
        memset(Buffer, 0, 200);
    }
    
    //write contents of Country struct to RandomStruct.bin
    if (fwrite(CountryArray, sizeof(Country), atoi(argv[1]), file2) != atoi(argv[1]))
        err_sys("write fail");
    
    
    //bubble sort the directory struct
    for(J = 0; J < atoi(argv[1]); J++)
    {
        for(I = J + 1; I < atoi(argv[1]); I++)
        {
            if(strncmp(DirectoryArray[J].code, DirectoryArray[I].code, 3) > 0)
            {
                TempDir = DirectoryArray[J];
                DirectoryArray[J] = DirectoryArray[I];
                DirectoryArray[I] = TempDir;
            }
        }
    }
  
    //write contents of Directory struct to RandomStructDir.bin
    if (fwrite (DirectoryArray, sizeof(Directory), atoi(argv[1]), file3) == -1)
        err_sys("write failed\n");
    
    //close files 
    if(fclose(file1) == EOF) err_sys("could not close AllCountries.dat");
    if(fclose(file2) == EOF) err_sys("could not close RandomStruct.bin");
    if(fclose(file3) == EOF) err_sys("could not close RandomStructDir.bin");
    
    /***************************** PHASE II ***********************************/
    
    //re-open files
    file1 = fopen("RandomStructs.bin", "r");
    if(file1 == NULL) err_sys("bad open");
    file2 = fopen("RandomStructDir.bin", "r");
    if(file2 == NULL) err_sys("bad open");
    
    //allocate memory for DirectoryArrayTwo
    DirectoryArrayTwo = (Directory *) calloc(atoi(argv[1]), sizeof(Directory));
    
    //loop to build sorted directory array
    for(J = 0; J < atoi(argv[1]); J++)
    {
        //inner loop to read and store code
        for(I = 0; I < CODE_MAX + 1; I++)
        {
            N = fgetc(file2); 
            if(N == EOF) err_sys("error reading RandomStructDir.bin");
            DirectoryArrayTwo[J].code[I] = N;
            //printf("%c", DirectoryArrayTwo[J].code[I]);
        }
        N = fread(&DirectoryArrayTwo[J].byteOffset, sizeof(int), 1, file2);
        if(N != 1) err_sys("error reading RandomStructDir.bin 2");
    }
    
    //get user input
    printf("%s", "Please input Country Code: ");
    gets(UserInput);
    
    //binary search
    first = 0;
    last = atoi(argv[1]);
    middle = (first + last)/2;
    
    while(first <= last)
    {
        if(strncmp(DirectoryArrayTwo[middle].code, &UserInput, 3) < 0)
            first = middle + 1;
        else if(strncmp(DirectoryArrayTwo[middle].code, &UserInput, 3) == 0)
        {
            //seek, read, and write
            N = fseek(file1, DirectoryArrayTwo[middle].byteOffset, SEEK_SET);
            if(N == -1) err_sys("error seeking RandomStructs.bin");
            
            I = 0;
            
            //read code                           
            if(fread(AnswerCode, sizeof(char), CODE_MAX, file1) != CODE_MAX)
                err_sys("error reading RandomStructs.bin 1");
            AnswerCode[CODE_MAX + 1] = '\0';

            //read name
            if(fread(AnswerName, sizeof(char), NAME_MAX, file1) !=  NAME_MAX) 
                err_sys("error reading RandomStructs.bin 2");
            AnswerName[NAME_MAX + 1] = '\0';
            
            //read population
            if(fread(&AnswerPop, 4, 1, file1) != 1)
                err_sys("error reading RandomStructs.bin 3");
            
            //read life expectancy
            if(fread(&AnswerLifeExp, 4, 1, file1) != 1)
                err_sys("error reading RandomStructs.bin 4");
            
            //print to standard out
            printf("%s, %s, %d, %f\n", AnswerCode, AnswerName, AnswerPop, AnswerLifeExp);
            
            //break out of the search
            break;
        }
        else
            last = middle - 1;
        middle = (first + last)/2;
    } 
    
    //close opened files
    if(fclose(file1) == EOF) err_sys("could not close RandomStructs.bin");
    if(fclose(file2) == EOF) err_sys("could not close RandomStructDir.bin");
    
    return (EXIT_SUCCESS);
}

