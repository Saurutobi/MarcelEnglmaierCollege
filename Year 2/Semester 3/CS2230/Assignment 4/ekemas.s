! CS 2230
! Assignment 4
! Thomas EKema
! 11/13/12
! This method takes in a title and data points as parameters
! and outputs a histogram using a trap

        .global DisplayHist
DisplayHist: save %sp, -1024, %sp

! store extra args

        call PutArgs
        nop

! calculate the scale

        clr %l0
        ld [%l7], %l1

MaxLoop:
        ld [%l7], %l1
        cmp %l1, -1
        be EndMaxLoop
        nop
        cmp %l1, %l0
        bge,a NewMax
        mov %l1, %l0
NewMax:
        ba MaxLoop
        add %l7, 4, %l7         !ready for next arg
EndMaxLoop:

        add %fp, 68, %l7        !reset base address
        mov %l0, %i0
        call .udiv
        mov 17, %i1
        mov %o0, %l6

! loop to blank fill

        set SCREEN, %l1
        clr  %l0                !initialize counter
        mov ' ', %l2            !character to be stored

BlankingLoop:
        cmp %l0, 2000
        be EndBlankingLoop
        stb %l2, [%l1 + %l0]
        ba BlankingLoop
        add %l0, 1 ,%l0         !increment counter
EndBlankingLoop:

! create graph
! 1. title

        clr %l0                 !initialize counter
        clr %l2

TitleLoop:
        cmp %l0, 16
        be EndTitleLoop
        ldub [%i0 + %l0], %l2
        stb %l2, [%l1 + %l0]
        ba TitleLoop
        add %l0, 1, %l0         !increment counter
EndTitleLoop:

! 2. y - axis and scale

        mov 1,  %l0
        mov "|", %l2
        mov "\n", %l3

YLoop:
!       cmp %l0, 19
!       be EndYLoop
!       mov %l0, %i0
!       call Offset
!       mov 1, %i1
!       stb %l2, [%l1 + %o0]
!       call Offset
!       mov 25, %i1
!       stb %l3, [%l1 + %o0]
!       ba YLoop
!       add %l0, 1, %l0 
EndYLoop:

! 3. x - axis and labels

! read and display data

! five statements to trap

        mov 4, %g1
        mov 1, %o0
        set SCREEN, %o1
        mov 2000, %o2
        ta 0

        ret
        restore

PutArgs:
        st %i5,[%fp + 88]
        st %i4,[%fp + 84]
        st %i3,[%fp + 80]
        st %i2,[%fp + 76]
        st %i1,[%fp + 72]
        st %i0,[%fp + 68]
        retl
        add %fp, 68, %l7

Offset: save %sp, -128, %sp

        mov %i0, %o0
        call .umul
        mov 4, %o1 !4 bytes space given per column
        mov %o0, %i0
        mov %i1, %o0
        call .umul
        mov 100, %o1 !100  bytes space per row
        add %i0, %o0, %i0

        ret
        restore

        .section ".data"
SCREEN: .skip 2000
TABLE:  .ascii "123456789"
