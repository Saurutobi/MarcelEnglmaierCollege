!DisplayHist.s

	!11/19/2012
	!David E. Charles	

	.global DisplayHist

DisplayHist: 
	save %sp,-800, %sp
	set screen, %l0   !setting screen
	
	mov %i0, %l4      !saving %i0 because we are going to clear it
	mov %i1, %l5      !saving %i1 beacause we are going to clear it
	mov %i0, %o3	    !saving %i0 beacause we are going to clear it
	mov %i1, %o4	    !saving %i1 beacause we are going to clear it	
	clr %l3           !counter, clearing our counter
	mov 32,%l7 	    !moving 32 to %l7
	
	
fillIt:      
	 
	stb %l7,[%l0+%l3] !store %l7 which is 32 in (%l0 which is screen + 
                          !the 
			    !counter which is %l3)
	
	cmp %l3,2000      !compare %l3 which is the counter to 2000
	bl,a fillIt       !if is less than 2000 then increment %l3
	inc %l3	    !if is not less than it will anull the branch and 
                         !continue
	
!=========================================================
	
	 mov %l4,%l3      !%l4 contains our input 0 (%i0), putting it on %l3
	 mov 85,%l6       !ascii code for U
	
	!CheckForUorS will check if it's scaled or unscaled

CheckForUorS:
	
	ldub [%l3],%l7   !loading a character of the tittle which base address it's in %l3
	mov 1,%o5        !making 1 our scale factor
	cmp %l7,%g0	   !comparing to null 

	be,a FetchArgs   !if it's null call FetchArgs	
	clr %o5          !clear %o5, since it's unscaled

	cmp %l7,%l6      !compare that character with 'U' for Unscaled
	be  FetchArgs !if it's equal, it's Unscaled, go to FetchArgs
	mov %i2,%l1      !Saving our input number 3 in %l1

	ba CheckForUorS  !loop check for unscaled or scaled
	inc %l3          !increment where we got the tittle to get the next char

!===========================================================================
	
        FetchArgs:
	 !clearing stuff
	 clr %l7
        clr %l6
        call PutArgs
        clr %l3

!Put register args into caller's stack frame 

PutArgs:
      
     !we put this stuff back in %i0 and %i1 because we clear it 
     !in the program, so it erases it from the stack or frame pointer? 
     !but the thing we had to save them at the beginning of the program
     !and here we are putting them back to get the values from them
     !only affects the input 1 and 2, others will always be ok :)
	
     mov %l4,%o3
     mov %l5, %o4

     add %i0,%l4,%i0		!putting back real value of input 0 in %i0
     mov %l5, %i1           !putting back real value of input 1 in %i1
     st %i5,[%fp + 88]
     st %i4,[%fp + 84]
     st %i3,[%fp + 80]
     st %i2,[%fp + 76]
     st %i1,[%fp + 72]
     st %i0,[%fp + 68]
     add %fp, 68, %l7  !base address of all arguments into %l7


     cmp %o5,1         !check if it's unscaled or scaled, if it's 1, it's unscaled
     be  ReadyForLines !if it's equal, it's Unscaled, go to ReadyForLines
     mov %i2,%l1       !Saving our input number 3 in %l1

     call readArgs


readArgs:   ld [%l7+4],%o0 ! First arg  im using o0 since I cant use %l0
        ld [%l7+8],%l1 ! Second arg
	 ld [%l7+12],%l2! Third arg
        cmp %l1, %l3   !compare the frecuency with %l3 wich contains the max
	 bg,a loadArgs      !if is greater call load
	 mov %l1, %l3   !delay slot moves what is in %l1 to %l3
   			  !meaning %l3 has the max value 

loadArgs:add %l7,8,%l7 ! ready for next arg, will be +8 to base address
         !we gotta check for -1 because it's the terminating condition
         cmp %l2, -1    !comparing %l2 to -1, which is the third arguemtn
	  bne readArgs   !if is not equal is gonna keep looping and is gonna
	  mov %l3, %l5	    !execute the branch, for now %l5 has print value	

         !get x
	  mov %l3, %o0    !moving max frequency to %o0
	  call .udiv      !call .udiv, which it's gonna take w.e it's
			    !in %o0 and divide it by w.e it's on %o1
			    !this will gives us the frecuency
	  mov 20, %o1     !moving 20 to %o1, since we got 20 lines
	 		    !frecuency it's in %o0 now!
	  mov %o0, %l5    !moving w.e udiv returned to %l5, for testing purposes
	                  !we got the frecuency now in %o0 and %l5

	  mov %l5,%o5     !we got the frecuency now in %o0 and %l5

         !Writing x=
	  clr %i1        
	  mov 11, %i0    !location row 11,where we wanna print it
	  mov %i0,%o0    !moving our location to %o0
	  call offset   
	  mov %i1, %o1   !moving our col value 
	  mov 88, %l7    !moving x ascii value to %l7
	  stb %l7,[%l0+%o0] !writing it to w.e offset returned
	  mov 61, %l7    !putting "=" ascii code on %l7
	  inc %o0        !increment the col 
	  stb %l7,[%l0+%o0] !writing it, so we got x= so far
         !end writing x=
         mov %o0, %l3   !move offset value to %l3, store it, save it
         add %l3,3,%l3    
         
	  call WriteSF
!===================================================================

         WriteSF:
         clr %o0
         add %l5,%o0,%o0  !moving frecuency to %o0
   
	  call .urem	     !call urem to get the mod, (the last digit)
	  mov 10, %o1      !mov 10, because we wanna get the last digit to %l7
         mov %o0, %l7     !move whatever urem returned which is the number to %l7
         add 48,%l7,%l7   !we add 48 to make it an integer
  
         
         stb %l7,[%l0+%l3]!write that, which is the number we want
         clr %o0
	  add %l5,%o0,%o0  !move the frecuency to %o0 
	  call .udiv       !because we are calling udiv and it's gonna divide
	  mov 10, %o1	     !making the number less
                          !moving 10 to %o1, because we want to divide by 10
	  mov %o0, %l5     !moving whatever the division by 10 returned to %l5
	  cmp %l5, 0       !compare is %l5 equal to 0?
	  bne,a WriteSF    !if it's not equal decrement %l3 and loop 
	  dec %l3	     !We decrease it because we get the last digit so we are going backwards
  
!=============================================================================
ReadyForLines:
	mov 10, %l7       !putting the new line in %l7
			    !Every 80 characters put a \n using the same 
			    !the loop is until 25
	clr %i1
	mov 79, %i1	    !forcing it... to start putting me the new 
			    !lines at the end of the first line
	clr %i0
	clr %l3
	call WriteNewLines
	
WriteNewLines:      
	mov %i1, %o1      !moving our y to %o1, so offset can use it as an input
	call offset       !calling offset
	mov %i0, %o0      !moving our x to %o0, so offset can use it as an input
	stb %l7,[%l0+%o0] !storing that %l7 which is the new line character
			    !in screen plus whatever offset returned, which it's in %o0	
	add %i0,1,%i0     !adding 1 to our x 
	cmp %l3,25        !compare if our x (rows) it's 25, which it's the max
	bl,a WriteNewLines  !loop to new line if it's less than 25
	inc %l3           !increment our counter if it's less than 25


       !clearing stuff
	mov 124, %l7      !moving our | character to %l7
	clr %i0           !we clear it becasue we are getting input from c 
                         !and that was ... the thing
	clr %i1	    !same reason remember we were getting 2 and 3 or 
                         !something, it has to start on 0 for testing purposes -_-
	clr %l3
	mov 5, %i1

	!Writing |, Y axis
y:      
	mov %i1, %o1
	call offset
	mov %i0, %o0	 
	stb %l7,[%l0+%o0]	
	add %i0,1,%i0
	cmp %l3,21
	bl,a y
	inc %l3
		
	!clearing stuff
	mov 45, %l7       !moving our - character to %l7
	clr %i1	    !same reason remember we were getting 2 and 3 
			    !or something, it has to start on 0 -_-
	clr %l3
	clr %i0
	mov 21, %i0       !start on the row 21

	!Writing -, X axis
x:      
	mov %i1, %o1
	call offset 
	mov %i0, %o0
	stb %l7,[%l0+%o0]	
	add %i1,1,%i1
	cmp %l3,79
	bl,a x
	inc %l3
	
!======================================================================================================
	!values of %i0 and %i1 are in %o3 and %o4
	!%o5 got our scale factor, divide value between that and that's %l4
	!%l4 : times we have to write x for each value

	!clearing some registers, just in case
	clr %l7
	clr %l5
	clr %l6
	
	add %fp, 68, %l7 !setting base address on %l7
	mov 20,%l5  !row 20
	mov 8,%l6   !start at col 8 to write the values
	mov 8,%l3   !start at col 8 to write the values
	mov 1, %o2  !counter will start at 0, this will make sure we print the right amount of x's by value
	mov 120, %l2 !putting the x in %l2
	mov 1,%l3    !moving 1 to %l3, this will be our counter for v1, v2, etc...
	mov %o3, %i0 !putting back input 0 values on %i0
	mov %o4, %i1 !putting back input 1 values on %i1


ReadyToWrite:

	mov 22,%l5         !making our row 22
	mov %l5,%o0        !putting our row in %o0
	call offset        !calling offset
	mov %l6,%o1        !putting our col in %o1
	mov %o0, %l3       !moving whatever offset returned to %l3
	call readNextV


readNextV: 
	
        mov %o3, %i0     !putting back the input value of input0
	 mov %o4, %i1     !putting back the input value of input 1
	 ld [%l7+4],%l2   !fist argument in %l2
 	 ld [%l7+8],%o0   !second arg frequency im using o0 since I cant use %l0
	 ld [%l7+12],%i0! !Third arg on %i0, to check for -1
	 call .udiv
	 mov %o5,%o1      !moving the scale factor
	 mov %o0,%l4      !puting on %l4 the times we have to write x
	
	
	!%l5 will be our row
	!%l6 will be our col
!=====================================================================================
	!writing values
 
WriteValues:

	mov %l2,%o0
	call .urem         !getting the last using using the mod
	mov 10, %o1
	add %o0,48,%o0     !making it an integer
	stb %o0,[%l0+%l3]  !"Writing the digits of the current value" 
		
	mov %l2,%o0 	     !moving our input to divide 
	call .udiv           
	mov 10, %o1        !moving 10 to %o1, so we can divite by 10
	
	mov %o0, %l2       !moving whatever the division returned back in %l2
	cmp %l2, 0         !compare is %l2 equal to 0?
	bne,a WriteValues  !if it's not equal decrement %l3 and loop 
	dec %l3	     !We decrease it because we get the last digit so we are going backwards
	
	clr %o0
	clr %l2
	mov 1, %o2         !making our counter 1
	mov 120, %l2       !putting the "x" ascii code value in %l2
	cmp %o5,1          !comparing if it's scale or unscaled, 1 it's for unscaled
	be,a getFrequency  !if it's one go to getFrequency, since it's unscaled
	mov %l1,%i2        !we had our input 2 stored in %l1

	call writeXs
	mov 20,%l5  

writeXs:
	mov %l5, %o0 !rows in o0
	call offset
	mov %l6, %o1 !cols in o0
	stb %l2,[%l0+%o0] !putting the x in that location
	dec %l5 !decresing %l5,which contains our row number
	cmp %o2, %l4 !compare our counter with %l4, which has the times we have to write x for the current value
	bl,a writeXs !if it's less call write
	inc %o2      !increase our counter
	
	
readyNextValue:	
	add %l6,5,%l6  !add 4 to %l6 to cols -_-
	mov %l6,%l3    !putting our start col in %l3
	mov 20,%l5     !row 20
	mov 1, %o2     !moving 1 to %o2 which it's our counter
	cmp %i0,-1     !comparing our third argument to check if there is more to read
	bne,a ReadyToWrite    !if is not -1, which is the terminating condition, read next
	add %l7,8,%l7	!we add 8 because that is the next value
		
	
	call ReadyForTitle
!======================================================================
	!this method it's only called if it's unscaled 
getFrequency:
 	ld [%l7+8],%l4
	call writeXs
	mov 20,%l5 
!=======================================================================

ReadyForTitle:
			
	!clearing stuff just in case
	clr %l3
	clr %i1	
	clr %l7       
	clr %i0
	clr %i0

	mov 36, %i1     !We are going to start writing the tittle at col 36
	mov %o3,%l4     !%o3 contains our input 0 values, we are moving it to %l4

WriteTitle:
	ldub [%l4],%l7   !loading the tittle which it's in %l4
	cmp %l7, %g0	   !comparing to null 
	be away          !if it's equal leave terminate 
	mov %i1, %o1     !moving %i1 which it's 40 there
	call offset      !call offset
	mov %i0, %o0     !moving %i0 to %o0, which it's 0 
	stb %l7,[%l0+%o0]!writing the tittle which it's in %l7	
	add %i1,1,%i1    !add one to the col
	ba WriteTitle    !loop to write the next character
	inc %l4          !increment where we got the tittle to get the next char

	clr %i0
	clr %i1

away:
	mov %l0, %o1      !moving screen to %o1 because that is where 
			    !ta takes it
	mov 4, %g1        ! 4 is write
	mov 1, %o0        ! 1 is STDOUT
	mov 2000 , %o2    ! 8 nibbles in an int
	ta 0		    ! System call
			    
       ret
	restore	

offset:	
	save %sp, -800, %sp
	mov %i0,%o0
	call .umul
	mov 80,%o1
	add %i1,%o0,%i0

	ret
	restore
	
	.section ".data"  !making this readable
screen:
	.skip 2000  !the screen function
table:	.ascii " Title"

