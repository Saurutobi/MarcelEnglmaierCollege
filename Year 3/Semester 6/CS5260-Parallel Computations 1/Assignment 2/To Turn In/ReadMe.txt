There are 3 programs here, each with different outputs.

cereal.c:
this is the first iteration. It is the Serial version
it can be compiled with 'gcc cereal.c'
To choose the size of the matrix, you must edit 4 things:
    The x dimension and the y dimension of datMatrix[][] on line 6
    lengthOfX on line 13 so that it is the same as x dimension of datMatrix
    lengthOfY on line 14 so that it is the same as y dimension of datMatrix


inline.c:
This is the second version I made, in preparation of the Parallel version. 
More of a "Sanity-check" than anything else. The difference from cereal.c
is that inline.c uses a 1D matrix not a 2D.
it can also be compiled with 'gcc inline.c'
To choose the size of the matrix, you must edit 3 things:
    the size of datMatrix on line 6 to be a square number(eg 10^2=100, 20^2=200, 7^2 = 49 etc)
    on line 13, you must enter the same number as you just did on line 6
    on line 14, you must enter the square root of the size of datMatrix(eg 100 > 10, 200 > 20, 49 > 7, etc)

MPI_Version.c:
It's not functioning yet. So close, but I'm still debugging it. I thought I'd
include it so you can look at it and give me pointers as to why it's not working
yet.
To choose the size follow the same steps as inline.c(but it won't work yet)
