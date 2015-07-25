This is a console app. you can run the executable, or open the solution and run it from VS
	The executable is in /bin/Debug

Graphs are hard. I didn't really know how to do it, so I printed the relevant information in the Console

How would I Simulate a swap process with time?
	I could use a System.sleep(x); or equivalent, and choose how long it waits
Which Scheduling Alogithm am I using?
	I don't really know, I came up with this thing on a Sunday morning at 4 am. Codeathons are nice.
		I just decided how to handle everything myself.
How does it handle high vs low priority processes?
	If a process exeeds a threshold in the queue, it gets two more priority points up to the max.
		Then, a sort reorganizes the queue. A high priority process will leave the cpu for an I/O,
		so then the next process gets put in the cpu. After I/O, a process gets placed back in the 
		queue with its original priority.
