using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CPUSchedulingEmulator
{
    class Process
    {
        public Process(String name, int priority, bool kernelSpace, int A, int B, int C)
        {
            this.name = name;
            this.startingPriority = priority;
            this.currentPriority = priority;
            this.isProcessInKernelSpace = kernelSpace;
            this.cpuIOPauseTime = A;
            this.cpuIOCurrentPauseTimeLeft = 0;
            this.cpuIOWaitTime = B;
            this.cpuIORepetitions = C;
            this.currentTimeInCPU = 0;
            this.cpuIOCurrentTimeLeft = 0;
            this.currentTimeInQueue = 0;
            this.totalTimeInCPU = 0;
            this.totalTimeInIO = 0;
            this.totalTimeInQueue = 0;
            this.smallestTimeInQueue = 10000;
            this.longestTimeInQueue = 0;
            this.isProcessInCPU = false;
            this.isProcessInIO = false;
        }
        
        /// <summary>
        /// Process name
        /// </summary>
        public String name
        {
            get;
            set;
        }

        /// <summary>
        /// Starting Priority of Process
        /// </summary>
        public int startingPriority
        {
            get;
            set;
        }

        /// <summary>
        /// Current Priority of Process
        /// </summary>
        public int currentPriority
        {
            get;
            set;
        }

        /// <summary>
        /// User of Kernel Space
        /// </summary>
        public bool isProcessInKernelSpace
        {
            get;
            set;
        }

        /// <summary>
        /// A. Time in CPU needed for I/O to start
        /// </summary>
        public int cpuIOPauseTime
        {
            get;
            set;
        }

        /// <summary>
        /// counter for A
        /// </summary>
        public int cpuIOCurrentPauseTimeLeft
        {
            get;
            set;
        }

        /// <summary>
        /// B. Time I/O Takes
        /// </summary>
        public int cpuIOWaitTime
        {
            get;
            set;
        }

        /// <summary>
        /// C. Number of IOPause/IOWait cycles before process ends
        /// </summary>
        public int cpuIORepetitions
        {
            get;
            set;
        }

        /// <summary>
        /// D. Time in CPU preparing for I/O
        /// </summary>
        public int currentTimeInCPU
        {
            get;
            set;
        }

        /// <summary>
        /// E. Time left waiting for current I/O
        /// </summary>
        public int cpuIOCurrentTimeLeft
        {
            get;
            set;
        }

        /// <summary>
        /// F. Time Process has been waiting in Queue
        /// </summary>
        public int currentTimeInQueue
        {
            get;
            set;
        }

        /// <summary>
        /// G. Total Time Process has been in CPU
        /// </summary>
        public int totalTimeInCPU
        {
            get;
            set;
        }

        /// <summary>
        /// H. Total Time Process has been in I/O
        /// </summary>
        public int totalTimeInIO
        {
            get;
            set;
        }

        /// <summary>
        /// I. Total Time Process has been in Queue
        /// </summary>
        public int totalTimeInQueue
        {
            get;
            set;
        }

        /// <summary>
        /// J. Smallest Time Process has been in Queue
        /// </summary>
        public int smallestTimeInQueue
        {
            get;
            set;
        }

        /// <summary>
        /// K. Longest Time Process has been in Queue
        /// </summary>
        public int longestTimeInQueue
        {
            get;
            set;
        }
        
        /// <summary>
        /// Is the Process in the CPU?
        /// </summary>
        public bool isProcessInCPU
        {
            get;
            set;
        }

        /// <summary>
        /// It the Process in I/O
        /// </summary>
        public bool isProcessInIO
        {
            get;
            set;
        }
    }
}
