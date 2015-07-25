#include<stdio.h>

//author Marcel Englmaier

unsigned char NthByteC(int, int);
unsigned char NthByteS(int, int);

int main(int argc, char * argv[])
{
	printf("Value = 5\nByteNdx = 2\n");

		//takes a 32 bit input, gets 1 byte back, and displays it as hex
	printf("Using C: %x is returned when passing 0xfacecafe and asking for byte #2\n", NthByteC(0xfacecafe,2));
	printf("Using C: %x is returned when passing 0xfaed9327 and asking for byte #3\n", NthByteC(0xfaed9327,3));
	printf("Using C: %x is returned when passing 0x2893fade and asking for byte #0\n", NthByteC(0x2893fade,0));

		//takes a 32 bit input, gets 1 byte back, and displays it as hex
	printf("Using Assemlby: %x is returned when passing 0xfacecafe and asking for byte #2\n", NthByteS(0xfacecafe,2));
	printf("Using Assemlby: %x is returned when passing 0xfaed9327 and asking for byte #3\n", NthByteC(0xfaed9327,3));
	printf("Using Assemlby: %x is returned when passing 0x2893fade and asking for byte #0\n", NthByteC(0x2893fade,0));
}

unsigned char NthByteC(int Val, int ByteNdx)
{
	ByteNdx = ByteNdx << (3);	//takes ByteNdx and converts it to bits so the next shift works
	return ((Val >> ByteNdx) & 0xFF);	//returns the wanted byte by shifting the Val over, then mask it and return it
}
