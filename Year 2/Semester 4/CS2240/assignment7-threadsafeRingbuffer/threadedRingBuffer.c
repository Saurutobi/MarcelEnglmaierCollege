//Author: Marcel Englmaier

#include "ring-buffer.h"
#include <pthread.h>

void *readTest(void *buffer);
void *writeTest(void *buffer);
const char* TSELIOT = "This is the way the world ends. Not with a bang but a whimper.";

int main (int argc, char **argv)
{
  tny_ringbuffer* ring = tny_ringbuffer_new(1000); //create ringbuffer struct

  pthread_t thread1, thread2; 
  int thr1, thr2;

  thr1 = pthread_create(&thread1, 0, readTest, (void *)ring); //Make first thread
  thr2 = pthread_create(&thread2, 0, writeTest, (void *)ring); //Make 2nd thread

  pthread_join(thread1, 0); //wait for threads
  pthread_join(thread2, 0);
   
  tny_ringbuffer_free(ring); 

  printf ("All said and done \n\n");
  
  pthread_exit(NULL);
  return 0;
}

void *readTest(void *buffer)
{

  int j;
  int counter = 0; //Number of times to try and read from ringbuffer
  char read[100]; //buffer
  memset(read, 0, 100);
 
  while (counter < 10) 
  {
    if (tny_ringbuffer_pop((tny_ringbuffer*) buffer, read, 103) == 0)
    {
      counter++;
    }
  }
  printf("Everything read well\n", counter);
  return;
}

void *writeTest(void *buffer)
{
  int i;
  int counter = 0; //Number of times to try and write from ringbuffer

  while (counter < 10)
  {
    if (tny_ringbuffer_push((tny_ringbuffer*) buffer, &TSELIOT, 103) == 0)
      counter++;
  }

  printf("Everything wrote well \n");
  return;
}