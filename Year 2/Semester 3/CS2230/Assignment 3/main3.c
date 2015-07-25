#include<stdio.h>

//author Marcel Englmaier

int CountBits(int);
int FincWhenCSet();
int FincWhenVSet();

int main(int argc, char * argv[])
{
	//tests CountBits with multiple problems
	printf("%d 'on' bits in 0x00100011\n", CountBits(0x00100011));
	printf("%d 'on' bits in 0xfacecafe\n", CountBits(0xfacecafe));
	printf("%d 'on' bits in 0xffffffff\n", CountBits(0xffffffff));

	//finds the value for C flag and V flag
	printf("%x is the value just before the 'C' flag is set\n", FincWhenCSet());
	printf("%x is the value just before the 'V' flag is set\n", FincWhenVSet());
}
