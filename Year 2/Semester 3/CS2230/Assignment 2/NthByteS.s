	.global NthByteS
	!author: Marcel Englmaier

NthByteS: save %sp, -1024, %sp
				!%10 = val
				!%i1 = ByteNdx

	sll %i1, 3, %i1	!effectively converts %i1 to bits for next shift
	srl %i0, %i1, %i0	!shifts %i0 to the right by %i1 bits
	
	and %i0, 0xFF, %i0	!masks %i0 with 0xFF

	ret
	restore
