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

Double:	save %sp, -1024, %sp

	add %i0, %i0, %i0	!adds the input '0' to itself to effectivly double, saves it to input
				!so the restore moves it to output '0'
	ret
	restore
	