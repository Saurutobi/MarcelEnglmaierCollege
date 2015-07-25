#include "ring-buffer.h"

int main (int argc, char **argv)
{
  tny_ringbuffer* ring = tny_ringbuffer_new(500);

  // manually set up the read state since this may not be implemented yet
  memcpy (ring->buffer, "foobar", 6);
  ring->begin = ring->buffer;
  ring->end = ring->buffer+5;

  char result[7];
  result[6] = 0;

  tny_ringbuffer_peek(ring, result, 6);
  if (strcmp("foobar", result))
  {
    printf ("Peek failed\n");
    return 1;
  }

  tny_ringbuffer_pop(ring, result, 6);
  if (strcmp("foobar", result))
  {
    printf ("Pop failed\n");
    return 1;
  }

  // FIXME: Doesn't check wrap reading

  tny_ringbuffer_free(ring);

  printf ("Read Pass\n");

  return 0;
}