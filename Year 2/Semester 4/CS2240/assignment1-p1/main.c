//author: Marcel Englmaier

#include "meh.h"

typedef struct
{
	char Code[4]; // a null will be in the fourth position
	char Name[40]; // these string lengths are arbitrarary
	char Population[20]; // but they all be null terminated
	char Lifeexp[10]; // character strings
}aStruct;

int main(int argc, char *argv[])
{
	//a static struct for use later
	aStruct tempStruct;
	//an array of pointers to aStruct
	aStruct *structPointers;
	int arraySize = 239;
	//allocates space for 300 of these things
	structPointers = (aStruct *)calloc(arraySize, sizeof(aStruct));
	
	int err = 0;
	int i;
	int n;
	int x;
	char buffer[200],ch;
	char *Tok;
	int file = open("AllCountries.dat", O_RDONLY);
	if(file == -1)
	{
		err_sys("error on open");
	} else
	{
		//this loop reads in all items
		for(x = 0; x < arraySize; x++)
		{
			buffer[0] = ch;
			i = 0;
			do
			{
				n = read(file, &ch, 1);
				if(n == -1)
				{
					err_sys("reading a line didn't work");
				}
				buffer[i++] = ch;
			}while (ch != '\n');
			
			//1 Code
			Tok = strtok(buffer, ",\n");
			n = strlen(Tok);
			for(i = 0; i < n; i++)
			{
				(structPointers[x].Code[i]) = Tok[i];
			}
			if((structPointers[x].Code[1]) == NULL)
			{
				(structPointers[x].Code[3]) = NULL;
				(structPointers[x].Code[2]) = (structPointers[x].Code[2]);
				(structPointers[x].Code[1]) = '0';
				(structPointers[x].Code[0]) = '0';
			}
			if((structPointers[x].Code[2]) == NULL)
			{
				(structPointers[x].Code[3]) = NULL;
				(structPointers[x].Code[2]) = (structPointers[x].Code[1]);
				(structPointers[x].Code[1]) = (structPointers[x].Code[0]);
				(structPointers[x].Code[0]) = '0';
			}
			
			//2 Skip
			Tok = strtok(NULL,",\n");
			//3 Name
			Tok = strtok(NULL,",\n");
			n = strlen(Tok);
			for(i = 0; i < n; i++)
			{
				(structPointers[x].Name[i]) = Tok[i];
			}
			//4-7 Skip
			Tok = strtok(NULL,",\n");
			Tok = strtok(NULL,",\n");
			Tok = strtok(NULL,",\n");
			Tok = strtok(NULL,",\n");
			//8 Population
			Tok = strtok(NULL,",\n");
			n = strlen(Tok);
			for(i = 0; i < n; i++)
			{
				(structPointers[x].Population[i]) = Tok[i];
			}
			//9 Age
			Tok = strtok(NULL,",\n");
			n = strlen(Tok);
			for(i = 0; i < n; i++)
			{
				(structPointers[x].Lifeexp[i]) = Tok[i];
			}
		}
		
		//Prints Raw Data First for Comparison
		if(write(STDOUT_FILENO, "\nRaw Sort-------------------------------------------------------------------------------\n", 89) == -1)
		{
			err_sys("error outputting Raw Data");
		}
		for(i = 0; i < arraySize; i++)
		{
			if(structPointers[i].Code[0] != NULL)
			{
				//print Code
				if(write(STDOUT_FILENO, "Code: ", 6) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Code), strlen((structPointers[i].Code))) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				//print Name
				if(write(STDOUT_FILENO, "Name: ", 6) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Name), strlen((structPointers[i].Name))) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				//print Population
				if(write(STDOUT_FILENO, "Pop: ", 5) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Population), strlen((structPointers[i].Population))) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				//print Lifeexp
				if(write(STDOUT_FILENO, "Lifeexp: ", 9) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Lifeexp), strlen((structPointers[i].Lifeexp))) == -1)
				{
					err_sys("error outputting Raw Data");
				}
				if(write(STDOUT_FILENO, " | \n", 4) == -1)
				{
					err_sys("error outputting Raw Data");
				}
			}
		}
		//the following code sorts twice and then prints sorted arrays twice
		int f, g, val1, val2;
		val1 = 0;
		val2 = 0;
		//Code Sort
		for (f = (arraySize - 1); f > 0; f--)
		{
			for (g = 1; g <= i; g++)
			{
				if (compareCode(structPointers[g-1].Code, structPointers[g].Code) == 1)
				{
					tempStruct = (structPointers[g-1]);
					(structPointers[g-1]) = (structPointers[g]);
					(structPointers[g]) = tempStruct;
				}
			}
		}
		//Prints Code Sort
		if(write(STDOUT_FILENO, "\nCode Sort------------------------------------------------------------------------------\n", 89) == -1)
		{
			err_sys("error outputting Code Sort");
		}
		for(i = 0; i < arraySize; i++)
		{
			if(structPointers[i].Code[0] != NULL)
			{
				//print Code
				if(write(STDOUT_FILENO, "Code: ", 6) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Code), strlen((structPointers[i].Code))) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				//print Name
				if(write(STDOUT_FILENO, "Name: ", 6) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Name), strlen((structPointers[i].Name))) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				//print Population
				if(write(STDOUT_FILENO, "Pop: ", 5) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Population), strlen((structPointers[i].Population))) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				//print Lifeexp
				if(write(STDOUT_FILENO, "Lifeexp: ", 9) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Lifeexp), strlen((structPointers[i].Lifeexp))) == -1)
				{
					err_sys("error outputting Code Sort");
				}
				if(write(STDOUT_FILENO, " | \n", 4) == -1)
				{
					err_sys("error outputting Code Sort");
				}
			}
		}
		
		//Population Sort
		for (f = (arraySize - 1); f > 0; f--)
		{
			for (g = 1; g <= i; g++)
			{
				if (atoi(structPointers[g-1].Population) > atoi(structPointers[g].Population))
				{
					tempStruct = (structPointers[g-1]);
					(structPointers[g-1]) = (structPointers[g]);
					(structPointers[g]) = tempStruct;
				}
			}
		}
		//Prints Pop Sort
		if(write(STDOUT_FILENO, "\nPop Sort-------------------------------------------------------------------------------\n", 89) == -1)
		{
			err_sys("error outputting Pop Sort");
		}
		for(i = 0; i < arraySize; i++)
		{
			if(structPointers[i].Code[0] != NULL)
			{
				//print Code
				if(write(STDOUT_FILENO, "Code: ", 6) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Code), strlen((structPointers[i].Code))) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				//print Name
				if(write(STDOUT_FILENO, "Name: ", 6) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Name), strlen((structPointers[i].Name))) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				//print Population
				if(write(STDOUT_FILENO, "Pop: ", 5) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Population), strlen((structPointers[i].Population))) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				if(write(STDOUT_FILENO, " | ", 3) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				//print Lifeexp
				if(write(STDOUT_FILENO, "Lifeexp: ", 9) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				if(write(STDOUT_FILENO, (structPointers[i].Lifeexp), strlen((structPointers[i].Lifeexp))) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
				if(write(STDOUT_FILENO, " | \n", 4) == -1)
				{
					err_sys("error outputting Pop Sort");
				}
			}
		}
		
	}
	if(close(file) == -1)
	{
		err_sys("error on close");
	}
	return 0;
}

int compareCode(char one[], char two[])
{
	if ((int)one[0] > (int)two[0])
	{
		return 1;
	}
	if ((int)one[0] == (int)two[0])
	{
		if ((int)one[1] > (int)two[1])
		{
			return 1;
		}
		if ((int)one[1] == (int)two[1])
		{
			if ((int)one[2] > (int)two[2])
			{
				return 1;
			}
		}
	}
	return 0;
}
