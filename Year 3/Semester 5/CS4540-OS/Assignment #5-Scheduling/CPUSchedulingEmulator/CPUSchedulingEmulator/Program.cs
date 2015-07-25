using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CPUSchedulingEmulator
{
    class Program
    {
        static Dictionary<int, Process> processes = new Dictionary<int, Process>();
        static List<int> priorityQueue = new List<int>();
        static List<int> waitingForIO = new List<int>();
        static int maxTimeInQueue;
        static int timeToIOInterupt = 0;
        static int currentProcessInCPU;
        static int processNextForIOInterupt;
        static bool cpuIdle = true;

        static void Main(string[] args)
        {
            startUp();
            Console.WriteLine("Ready?");
            string input = Console.ReadLine();

            //Here's the loop for doing the work
            int i = 0;
            bool exit = false;
            while (i < 10000 && exit == false)
            {
                exit = runCPUOnce();
                i++;
            }
            Console.WriteLine("Done!\n");
            results();
            input = Console.ReadLine();
        }

        public static bool runCPUOnce()
        {
            bool exitVal = true;

            //For figuring out timings
            foreach (var pair in processes)
            {
                Process current = pair.Value;
                if (current.cpuIORepetitions > 0)
                {
                    exitVal = false;
                    if (current.isProcessInCPU)
                    {
                        current.currentTimeInCPU++;
                        current.totalTimeInCPU++;
                    }
                    else if (current.isProcessInIO)
                    {
                        current.totalTimeInIO++;
                        current.cpuIOCurrentTimeLeft--;
                    }
                    else
                    {
                        current.currentTimeInQueue++;
                        current.totalTimeInQueue++;
                        current.longestTimeInQueue = current.currentTimeInQueue > current.longestTimeInQueue ? current.currentTimeInQueue : current.longestTimeInQueue;
                        current.smallestTimeInQueue = current.currentTimeInQueue < current.smallestTimeInQueue ? current.currentTimeInQueue : current.smallestTimeInQueue;
                        if (current.currentTimeInQueue > maxTimeInQueue)
                        {
                            current.currentPriority = current.currentPriority + 2 >= 15 ? 15 : current.currentPriority + 2;
                        }
                    }
                }
            }

            //if there is a process in the cpu, work on it, otherwise check if we can put a process in the cpu
            if (!cpuIdle)
            {
                if (processes[currentProcessInCPU].cpuIOCurrentPauseTimeLeft == 0)
                {
                    processes[currentProcessInCPU].isProcessInIO = true;
                    processes[currentProcessInCPU].isProcessInCPU = false;
                    if (timeToIOInterupt > processes[currentProcessInCPU].cpuIOWaitTime || timeToIOInterupt < 0)
                    {
                        timeToIOInterupt = processes[currentProcessInCPU].cpuIOWaitTime;
                        processNextForIOInterupt = currentProcessInCPU;
                        waitingForIO.Insert(0, currentProcessInCPU);
                    }
                    else
                    {
                        waitingForIO.Add(currentProcessInCPU);
                    }
                    if (priorityQueue.Count > 0)
                    {
                        currentProcessInCPU = priorityQueue[0];
                        priorityQueue.RemoveAt(0);
                        cpuIdle = false;
                        processes[currentProcessInCPU].isProcessInCPU = true;
                        processes[currentProcessInCPU].cpuIOCurrentPauseTimeLeft = processes[currentProcessInCPU].cpuIOPauseTime;
                    }
                    else
                    {
                        cpuIdle = true;
                    }
                }
                else
                {
                    processes[currentProcessInCPU].cpuIOCurrentPauseTimeLeft--;
                }
            }
            else
            {
                if (priorityQueue.Count > 0)
                {
                    currentProcessInCPU = priorityQueue[0];
                    priorityQueue.RemoveAt(0);
                    cpuIdle = false;
                    processes[currentProcessInCPU].isProcessInCPU = true;
                    processes[currentProcessInCPU].cpuIOCurrentPauseTimeLeft = processes[currentProcessInCPU].cpuIOPauseTime;
                }
            }

            //This just checks if it's time to process a finished IO, or not
            timeToIOInterupt--;
            if(timeToIOInterupt == 0)
            {
                priorityQueue.Add(processNextForIOInterupt);
                waitingForIO.Remove(processNextForIOInterupt);
                processes[processNextForIOInterupt].isProcessInIO = false;
                processes[processNextForIOInterupt].cpuIORepetitions--;
                processes[processNextForIOInterupt].cpuIOCurrentTimeLeft = processes[processNextForIOInterupt].cpuIOWaitTime;
                processes[processNextForIOInterupt].currentPriority = processes[processNextForIOInterupt].startingPriority;

                //Here's where we remove a finished process
                if(processes[processNextForIOInterupt].cpuIORepetitions == 0)
                {
                    waitingForIO.Remove(processNextForIOInterupt);
                    priorityQueue.Remove(processNextForIOInterupt);
                }
                for (int i = 1; i < waitingForIO.Count; i++)
                {
                    if (processes[waitingForIO[i - 1]].cpuIOCurrentTimeLeft < processes[waitingForIO[i]].cpuIOCurrentTimeLeft)
                    {
                        processNextForIOInterupt = waitingForIO[i - 1];
                        timeToIOInterupt = processes[processNextForIOInterupt].cpuIOCurrentTimeLeft <= 0 ? 1 : processes[processNextForIOInterupt].cpuIOCurrentTimeLeft;
                    }
                }
                
                waitingForIO.Remove(processNextForIOInterupt);
                waitingForIO.Insert(0, processNextForIOInterupt);
            }

            //if the cpu is idle and we've just added an item to the queue
            if (cpuIdle && priorityQueue.Count > 0)
            {
                currentProcessInCPU = priorityQueue[0];
                priorityQueue.RemoveAt(0);
                cpuIdle = false;
                processes[currentProcessInCPU].isProcessInCPU = true;
                processes[currentProcessInCPU].cpuIOCurrentPauseTimeLeft = processes[currentProcessInCPU].cpuIOPauseTime;
            }
            else if (!cpuIdle)
            {
                //organize the priority queue because states could have changed
                for (int i = 0; i < priorityQueue.Count; i++)
                {
                    for (int y = 1; y < priorityQueue.Count; y++)
                    {
                        if(processes[priorityQueue[y - 1]].currentPriority > processes[priorityQueue[y]].currentPriority)
                        {
                            int temp = processes[priorityQueue[y - 1]].currentPriority;
                            processes[priorityQueue[y - 1]].currentPriority = processes[priorityQueue[y]].currentPriority;
                            processes[priorityQueue[y]].currentPriority = temp;
                        }
                    }
                }
            }

            return exitVal;
        }

        /// <summary>
        /// This method sets up the Dictionary with 16 processes
        /// </summary>
        public static void startUp()
        {
            currentProcessInCPU = 0;
            maxTimeInQueue = 200;
            Random randy = new Random();
            processes.Add(0, new Process("#0I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(1, new Process("#1I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(2, new Process("#2I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(3, new Process("#3I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(4, new Process("#4I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(5, new Process("#5I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(6, new Process("#6I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(7, new Process("#7I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(8, new Process("#8I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(9, new Process("#9I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(10, new Process("#10I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(11, new Process("#11I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(12, new Process("#12I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(13, new Process("#13I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(14, new Process("#14I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            processes.Add(15, new Process("#15I am a Process", randy.Next(1, 16), false, randy.Next(1, 10), randy.Next(1, 20), randy.Next(1, 10)));
            priorityQueue.AddRange(processes.Keys);
        }

        /// <summary>
        /// Dummy method which can be used to add a new Process
        /// </summary>
        public static void addProcess()
        {
            Random randy = new Random();
            processes.Add(processes.Count, new Process("#" + processes.Count + "I am a new Process", randy.Next(1, 16), false, randy.Next(1, 500), randy.Next(1, 500), randy.Next(1, 500)));
        }

        public static void results()
        {
            Console.WriteLine("Here are the results");
            foreach (Process current in processes.Values)
            {
                Console.WriteLine("My Name is: " + current.name);
                Console.WriteLine("My current Priority is: " + current.currentPriority);
                Console.WriteLine("I spent this long in the queue: " + current.totalTimeInQueue);
                Console.WriteLine("I spent this long in the CPU: " + current.totalTimeInCPU);
                Console.WriteLine("I spent this long in I/O: " + current.totalTimeInIO);
                Console.WriteLine("I have this many I/O Repetitions left: " + current.cpuIORepetitions);
                Console.WriteLine("The smallest time I spent in the queue is: " + current.smallestTimeInQueue);
                Console.WriteLine("The longest time I spent in the queue is: " + current.longestTimeInQueue);
            }
        }
    }
}
