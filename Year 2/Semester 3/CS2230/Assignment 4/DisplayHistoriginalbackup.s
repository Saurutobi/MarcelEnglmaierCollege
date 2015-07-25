	.global DisplayHist
	!author: Marcel Englmaier

DisplayHist: save %sp, -1024,%sp

	call Initialize
	clr %l0
	clr %g1

loopReadDataFirst:
	mov %g1,%o0
	call .umul
	mov 4,%o1
	ldub [%l7+%o0],%i0
	cmp %i0,-1
	be,a endLoopReadDataFirst
	clr %l0
	mov %g1,%o0
	call .umul
	mov 8,%o1
	ldub [%l7+%o0],%i1
	cmp %i1,%g2
	bg,a continueFirst
	mov %i1,%g2
continueFirst:
	ba loopReadDataFirst
	add %g1,1,%g1
endLoopReadDataFirst:

	cmp %g2,21
	bl,a continueSecond
	mov 20,%g2
continueSecond:

loopBuildHist:	cmp %l0,%g1
	be,a endLoopBuildHist
	clr %l0
	add 0,%g1,%o0
	call .umul
	mov 4,%o1
	ldub [%l7+%o0],%i0
	add 0,%g1,%o0
	call .umul
	mov 8,%o1
	ldub [%l7+%o0],%i1
	call PrinterData
	clr %l0
	ba loopBuildHist
	add %l0,1,%l0
endLoopBuildHist:	

	mov 4,%g1
	mov 1,%o0
	set screenLocation,%o1
	mov 2000,%o2
	ta 0

	ret
	restore

Initialize:
	stb %i5,[%fp+88]
	stb %i4,[%fp+84]
	stb %i3,[%fp+80]
	stb %i2,[%fp+76]
	stb %i1,[%fp+72]
	mov %i0,%i5

	set screenLocation,%l4
	mov ' ',%l5
loopInIt:	cmp %l0,2000
	be,a endLoopInIt
	clr %l0
	stb %l5,[%l4+%l0]
	ba loopInIt
	add %l0,1,%l0
endLoopInIt:

	mov '/n',%l5
loopReturns:	cmp %l0,20
	be,a endLoopReturns
	clr %l0
	mov 100,%o0
	call .umul
	add 0,%l0,%o1
	stb %l5,[%l4+%o0]
	ba loopReturns
	add %l0,1,%l0
endLoopReturns:

	mov '|',%l5
loopVertAxis:	cmp %l0,18
	be,a endLoopVertAxis
	clr %l0
	mov 4,%g3
	call Offset
	add %l0,0,%g4
	stb %l5,[%l4+%g3]
	ba loopVertAxis
	add %l0,1,%l0
endLoopVertAxis:

	mov '_',%l5
loopHorAxis:	cmp %l0,100
	be,a endLoopHorAxis
	clr %l0
	add 0,%l0,%g3
	call Offset
	mov 19,%g4
	stb %l5,[%l4+%g3]
	ba loopHorAxis
	add %l0,1,%l0
endLoopHorAxis:

loopTitle:	cmp %l0,16
	be,a endLoopTitle
	clr %l0
	ldub [%i5+%l0],%l2
	stb %l2,[%l4+%l0]
	ba loopTitle
	add %l0,1,%l0
endLoopTitle:

	retl
	add %fp,68,%l7


Offset:
	mov %g4,%o0
	call .umul
	mov 100,%o1
	add %o0,%g3,%g3
	retl
	nop


PrinterData:
	mov 19,%g4
	call Offset
	mov 4,%g3
	mov %g3,%l0
	add %i0,%i0,%g3
	add %i0,%g3,%l0
	stb %i0,[%l0+%l4]

	sub %o2,20,%o3
	mov 'x',%l5
loopPrinterData:	cmp %l0,%o2
	be,a endLoopPrinterData
	clr %l0
	
	mov %o3,%g4
	call Offset
	mov 104,%g3
	mov %g3,%o4

	mov %l0,%g4
	call Offset
	mov 1,%g3

	add %i0,%i0,%i0
	add %g3,%i0,%i0
	add %o4,%i0,%i0
	stb %l5,[%i0+%l4]

	ba loopPrinterData
	add %l0,1,%l0
endLoopPrinterData:

	retl
	nop


!data section with output buffer
	.section ".data"
screenLocation: .skip 2000

