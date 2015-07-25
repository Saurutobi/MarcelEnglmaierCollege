	.global FincWhenCSet
	!author: Marcel Englmaier

FincWhenCSet: save %sp, -1024, %sp

	clr %l0		!clears %l0-used as the 'number before the flag-setting'
	clr %l1		!clears %l1

loop: 
	addcc 1,%l0,%l1	!adds 1 to %l0 and saves it to %l1 while setting flags
	bcs away		!if the 'c' flag has been set, got to away
	mov %l0,%i0		!in the delay slot, moves last used number to %i0 for output
	ba loop		!goes back to loop to continue cycle
	add 0,%l1,%l0		!copies %l1 to %l0, no flag has been set

away:
	ret			!returns
	restore		!restores
