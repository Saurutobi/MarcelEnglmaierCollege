Startup.
Create a 2000byte long empty(" ") space in memory
Call Printaxis
copy all inputs to stackframe
Go to input data, loop, read in, take largest y in g1, total number of reads until hit -1 goes in g3
		g3,g1 are the scalars.( 40/g3 determines total number of pixels per grade item)
For all g3, read in first number(s).
		g2 keeps count.
	put x in i0 then call Printerdata
	Now put y value in i1 and call Printerdata

Trap to print(must assign 5 statements first, then trap)

Away
	Ret restore(end of program

Offset save
	Calculate using numbers row #-1*80, + column number
	Ret
	restore

Printaxis labels the axis and puts title on it, then uses offset to print axis lines in ascii("|" and "_")

Printerdata takes o0, and plants it in the place determined by g2 using the value of x label line, then goes to specific location using g2, and scale. (just makes the label)
	Then takes o1 and uses yscale to find how many times too loop(l0)
		Loops for l0, working backwords using offset(and l0) to go up the graph, uses g2 and xscale to find location on that line, then places an "x" there in ascii
