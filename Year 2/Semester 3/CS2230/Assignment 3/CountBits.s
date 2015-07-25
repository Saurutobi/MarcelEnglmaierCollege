	.global CountBits
	!author: Marcel Englmaier

CountBits: save %sp, -1024, %sp

	clr %l0	!clears %l0-used as a temporary variable
	clr %l1	!clears %l1-used as loop counter
	clr %l2	!clears %l2-used as 'number of on bits' counter

loop: 	cmp %l1,32	!begins loop, tests if the loop has run 32 times using %l1
	be,a away	!if loop has ran 32 times, go to away
	mov %l2, %i0	!in the delay slot, moves 'number of on bits' counter to be outputted
	and %i0,1,%l0	!gets the right-most bit in %i0 and saves it to temporary %l0
	srl %i0,1,%i0	!shifts %i0 1 bit
	cmp %l0,1	!tests if %l0 is 1
	be addition	!if %l0 is 1, go to the addition branch
	clr %l0	!resets %l0 for next test in the delay slot
endAdd:		!after addition is done, execution comes back here to continue inside the loop
	ba loop	!tells execution to go back to loop to continue on looping
	add %l1,1,%l1	!in the delay slot, advance %l1 by 1

addition:
	ba endAdd	!tells the execution to return back to the loop
	add 1,%l2,%l2	!in the delay slot, add 1 to the 'number of bits' counter

away:
	ret		!returns
	restore	!restores
