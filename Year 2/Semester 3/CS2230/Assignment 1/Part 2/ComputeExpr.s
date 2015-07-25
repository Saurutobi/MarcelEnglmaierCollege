	.global ComputeExpr

ComputeExpr:	save %sp, -1024, %sp

	call Double	!calls Double first time
	mov %i0, %o0	!moves imput to output before save is made so output becomes input at save
	mov %o0, %l0	!moves output(doubled) to a local to be added later

	call Double	!calls Double second time
	mov %i1, %o0	!moves imput to output before save is made so output becomes input at save
	mov %o0, %l1	!moves output(doubled) to a local to be added later

	call Double	!calls Double third time
	mov %i2, %o0	!moves imput to output before save is made so output becomes input at save
	mov %o0, %l2	!moves output(doubled) to a local to be added later

	add %l0, %l1, %i0	!adds first two locals
	add %i0, %l2, %i0	!adds third local to the first two to make the final result

	ret
	restore

Double:	
	.word 0x9DE3B400	!is equal to 'save %o6, -1024, %o6'. binary is:
				!1001 1101 1110 0011 1011 0100 0000 0000
		
	.word 0xB0060018	!adds the input '0' to itself to effectivly double, saves it to input.
				!so the restore moves it to output '0'. This is equal to add %10, %10, %10. binary is:
				!1011 0000 0000 0110 0000 0000 0001 1000

	.word 0x81C7E008	!is equal to 'ret' or 'jmpl %i7 + 8, %g0'. binary is:
				!1000 0001 1100 0111 1110 0000 0000 1000 

	.word 0x81E82008	!is equal to 'restore' or 'restore %g0, %g0, %g0. binary is:
				!1000 0001 1110 1000 0010 0000 0000 1000
	
	 
