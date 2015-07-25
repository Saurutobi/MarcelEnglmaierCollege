//author: Marcel Englmaier

#include "meh.h"

typedef int (*FunctionPtr)(char *);

//function prototypes
int TraverseDir(char *, FunctionPtr);
void RemoveCore(char *);
void FindFile(char *);

varialbes
DIR *dir;               
struct dirent *ent;
struct stat sb;
char *StartDir, *path;
char buf[PATH_MAX];
char *listOfArgs[10];
int i;

int main(int argc, char** argv) 
{   
    //move all arguments to a seperate array for easy access
    for(i = 0; i < argc - 3; i++)
    {
        listOfArgs[i] = argv[i + 3];
    }
    
    StartDir = argv[1];
    chdir(StartDir);
    
    //if statement for the two options
    if(strcmp(argv[2], "Find") == 0)
    {
        printf("Finding\n");
         TraverseDir(StartDir, FindFile);
    }
    else if(strcmp(argv[2], "RemoveCore") == 0)
    {
        printf("Removing\n");
         TraverseDir(StartDir, RemoveCore);
    }
    return (EXIT_SUCCESS);
}

int TraverseDir(char * DirName, FunctionPtr fptr)
{
	//traverse dir
    getcwd(buf, PATH_MAX);
    dir = opendir(buf);
    while((ent = readdir(dir)) != NULL)
    {
        if(lstat(ent->d_name, &sb))
            err_sys("lstat error\n");
        if(strcmp(ent->d_name, ".") != 0 && strcmp(ent->d_name, "..") != 0)
        {
            if(S_ISDIR(sb.st_mode))
            {
                printf("%s - Directory\n", ent->d_name);
                chdir(ent->d_name);
                TraverseDir(ent->d_name, fptr);
                chdir("..");
            }
            else if(S_ISREG(sb.st_mode))
            {
                printf("%s - File\n", ent->d_name);
                fptr(ent->d_name);
            }
        }
    }
    closedir(dir);
    return 0;
}

void RemoveCore(char * dirName)
{
	//remove the "CORE" in current directory
    if(strcmp(dirName, "CORE") == 0)
    {
        printf("deleting %s\n", dirName);
        remove(dirName);
    }
}

void FindFile(char * dirName)
{
	//find file in current directory
    i = 0;
    while(listOfArgs[i] != NULL)
    {
        if(strcmp(dirName, listOfArgs[i]) == 0)
        {
            printf("%s file found at %s\n", dirName, buf);
        }
        i++;
    }
}
