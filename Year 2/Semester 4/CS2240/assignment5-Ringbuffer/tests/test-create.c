#include "ring-buffer.h"

int main (int argc, char **argv)
{
  tny_ringbuffer* ring = tny_ringbuffer_new(500);

  if (ring->begin != 0)
  {
    printf ("Create Fail: Begin is not properly initialized\n");
    return 1;
  }

  if (ring->end != 0)
  {
    printf ("Create Fail: End is not properly initialized\n");
    return 1;
  }

  if (ring->size != 500)
  {
    printf ("Create Fail: Size is not properly initialized\n");
    return 1;
  }

  tny_ringbuffer_free(ring);

  printf ("Create Pass\n");

  return 0;
}