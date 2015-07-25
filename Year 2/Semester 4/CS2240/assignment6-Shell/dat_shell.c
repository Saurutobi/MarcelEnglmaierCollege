//Author: Marcel Englmaier

#include "meh.h"

extern int makeargv(char *, char * , char ***);
int symbolCount(char**, int);
void symbolLocations(char**, int[], int, int[]);

int main()
{
	mode_t fileMode = (S_IRUSR | S_IWUSR | S_IXUSR);
	int fileflag = (O_CREAT | O_RDWR | O_TRUNC);
	char **argv;
	int argc;
	int pipeCount;
	char inputbuffer[80];
	pid_t pid;
	int pipeLeft[2];	//left side of pipe
	int pipeRight[2];	//right side of pipe
	int Status;
	int fd[2];
	int start = 0;
	write(STDOUT_FILENO, "\n'_'>", 5);
	while(strcmp(fgets(inputbuffer, 80, stdin), "quit\n") != 0)
	{
		fd[0] = -1;
		fd[1] = -1;
		argc = makeargv(inputbuffer, " \n", &argv);
		pipeCount = symbolCount(argv, argc); //get number of symbols
		int redirection[4];
		redirection[0] = -1;
		redirection[1] = -1;
		redirection[2] = 0;
		redirection[3] = -1;
		int symbols[pipeCount]; //create a array for the symbols
		if(strcmp(argv[0], "&") == 0)
		{
			start = 1;
		}
		symbolLocations(argv, symbols, argc, redirection); //get locations of the symbols

        //All the extra features are right here, I tried to modularize, but I couldn't get it to work
		if(strcmp(argv[0], "cd") == 0)	//cd
		{
			chdir(argv[1]);
			redirection[3] == 0;
			printf("I changed my working directory to:\n"); 
			printf("%s\n", get_current_dir_name());
        }
        else if(strcmp(argv[0], "science?") == 0)	//The Pterodactyl
		{
			redirection[3] == 0;
			printf("yo, gatorade me, bitch!\n");
        }
        else if(strcmp(argv[0], "ptero-me-a-new-asshole") == 0)	//The Pterodactyl
		{
			redirection[3] == 0;
			printf("don't worry, I won't tear you a new one, but The Motherfucking Pterodactyl will\n");
			system("x-www-browser -new-tab http://theoatmeal.com/comics/ptero");
			system("x-www-browser -new-tab http://theoatmeal.com/pterodactyl_video");
			system("x-www-browser -new-tab http://www.youtube.com/watch?v=aHpDPuh8A2Q");
        }
        else if(strcmp(argv[0], "poem") == 0)	//The Pterodactyl
		{
			redirection[3] == 0;
			printf("Have a poem:\n\nRoses are red,\n  My name's Dave\nThis poem makes no sense\n    Microwave\n");
        }
        else if(strcmp(argv[0], "candy") == 0)	//The Pterodactyl
		{
			chdir(argv[1]);
			redirection[3] == 0;
			int f = 0;
			for(f = 0; f < 500000; f++)
			{
				printf("lolololol");
			}
			printf("\n");
        }
        else if(strcmp(argv[0], "help") == 0)	//The Pterodactyl
		{
			chdir(argv[1]);
			redirection[3] == 0;
			printf("\nWelcome to the help menu. various options are available to you\n");
			printf("You have many commands available to you\n");
			printf("Standard Unix commands apply, such as\n");
			printf("        ls\n");
			printf("        grep\n");
			printf("        or | (pipe)\n");
			printf("    each of these works in the same way they do in Bash\n");
			printf("Also, 'cd' is available in Bash function\n");
			printf("\nThere are a few easter eggs:\n");
			printf("    'science?' will show you some...chicken\n");
			printf("    'ptero-me-a-new-asshole' will...well, find out yourself\n");
			printf("        note-if you do this and don't close the tabs before inputting new info, I'll core dump:)\n");
			printf("    'poem' will tell you a...tree\n");
			printf("    'candy' will give you many candies. But, patience is a virtue\n");
			printf("A cool little feature: you can nest dat_shell inside dat_shell so you can shell while you shell\n");
			printf("\nSaddly, the only thing missing is an inputbuffer. That'll be implemented later\n");
			printf("\nanyways, enjoy\n    -Saurutobi\n");
        }
        else
        {
		pid = fork();
                
		if(pipeCount == 0)
		{
			switch (pid)
			{
				case -1:
					err_sys("I failed at forking");
                    break;
				case 0:
					if(redirection[1] != -1) //There is a symbol at this location
					{
						if(redirection[2] == 1)  //Append file
						{
							fd[0] = open(argv[redirection[1] + 1], (O_WRONLY|O_APPEND), fileMode);
							fd[0] = dup2(fd[0], STDOUT_FILENO);
						}
						else
						{
							fd[0] = open(argv[redirection[1] + 1], fileflag, fileMode);
							fd[0] = dup2(fd[0], STDOUT_FILENO);
						}
					}
					else if(redirection[0] != -1)
					{
						fd[1] = open(argv[redirection[0] + 1], O_RDONLY);
						fd[1] = dup2(fd[1], STDIN_FILENO);
					}
					execvp(argv[0], &argv[0]);
                    write(1,"I failed to exec that\n", 21);
                    exit(1);
				default:
					break;
			}
		}
		else //More pipes
		{
			if(pid == 0) 
			{
				pipe(pipeLeft);//pipe left side
				pid = fork(); //fork
				if(pid > 0) // Parent
				{
					if(redirection[1] != -1) //Symbol test
					{
						if(redirection[2] == 1) //Append to file
						{
							fd[0] = open(argv[redirection[1] + 1], (O_WRONLY|O_APPEND), fileMode);
							fd[0] = dup2(fd[0], STDOUT_FILENO);
						}
						else
						{
							fd[0] = open(argv[redirection[1] + 1], fileflag, fileMode);
							fd[0] = dup2(fd[0], STDOUT_FILENO);
						}
					}
					close(pipeLeft[1]);//close out
					pipeLeft[0] = dup2(pipeLeft[0], STDIN_FILENO); //open in
                                        
					execvp(argv[symbols[pipeCount - 1] + 1], &argv[symbols[pipeCount - 1] + 1]); //execute any commands
					err_sys("I well, I failed to exec again\n");
				}
				if(pid == 0) //in the child
				{
					int x = pipeCount - 2;
					if(x > 0) //If there are greater then 2 pipes
					{
						for( ;x > -1; x--)	//I figured out you can do this with empty args!
						{
                            pipeRight[1] = pipeLeft[1];
							pipeRight[1] = dup2(pipeRight[1], STDOUT_FILENO);
							pipeRight[0] = pipeLeft[0];
                            close(pipeRight[0]);
							pipe(pipeLeft);

							pid = fork();

							if(pid > 0) //in the parent
							{
								close(pipeLeft[1]); //close the out
								pipeLeft[0] = dup2(pipeLeft[0], STDIN_FILENO);
								execvp(argv[symbols[x] + 1], &argv[symbols[x] + 1]);
                            	err_sys("One more exec fail, sorry\n"); 
							}
						}
					}
					if(pid == 0) //we are in the child
					{
                        pipeRight[0] = pipeLeft[0];
						pipeRight[1] = pipeLeft[1];
						if(redirection[0] != -1)
						{
							fd[1] = open(argv[redirection[0] + 1], O_RDONLY);
							fd[1] = dup2(fd[1], STDIN_FILENO);
						}
						close(pipeRight[0]);
						pipeRight[1] = dup2(pipeRight[1], STDOUT_FILENO);
						execvp(argv[start], &argv[start]);
						err_sys("And yet another exec fail, but this one's not my fault\n");
					}
				}
			}
		
        }
    }
		if(start == 0)
		{
			waitpid(pid, &Status, 0);
		}
		write(STDOUT_FILENO, "\n'_'>", 5);

	}
	fprintf(stderr, "\nbye\n\n");
	return EXIT_SUCCESS;
}

int symbolCount(char** aCommand, int argc)	//Count the number of pipes
{
	int i;
	int count = 0;
	for(i = 0; i < argc; i++)
	{
		if(strcmp(aCommand[i], "|") == 0)
		{
			count++;
		}
	}
	return count;
}

void symbolLocations(char** aCommand, int symbols[], int argc, int redirection[]) //get the location of the pipes/redirects
{
	int i;
	int punctuation = 0;
	for(i = 0; i < argc; i++) 
	{
		if(strcmp(aCommand[i], "<") == 0)
		{
            redirection[0] = i; //reading
            aCommand[i] = '\0'; //replace with null
		}
		else if(strcmp(aCommand[i], ">") == 0)
		{
            redirection[1] = i; //redirecting
            aCommand[i] = '\0';//replacewith null
		}
		else if(strcmp(aCommand[i], ">>") == 0)
		{
            redirection[1] = i; //redirecting
            redirection[2] = 1; //appending
            aCommand[i] = '\0';//replace with null
		}
		else if(strcmp(aCommand[i], "|") == 0)
		{
            symbols[punctuation++] = i; 
            aCommand[i] = '\0'; //replace with null
		}
	}
}
