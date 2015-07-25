#include<stdio.h>

//author Marcel Englmaier

void DisplayHist(char *, ...);

int main(int argc, char * argv[])
{
	DisplayHist("Test",1,1,-1);
	DisplayHist(“First 1 Unscaled”,10,15,-1);
	DisplayHist(“Second 1 Scaled”,10,154,-1);
	DisplayHist(“Third 2 Unscaled”,10,15,25,10,-1);
	DisplayHist(“Fourth 2 Scaled”,10,15,25,157,-1);
	DisplayHist(“Fifth 4 Scaled”,10,15,20,567,30,4,45,100,-1);
}
