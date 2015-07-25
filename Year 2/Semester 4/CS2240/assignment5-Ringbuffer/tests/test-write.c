#include "ring-buffer.h"

int main (int argc, char **argv)
{
  tny_ringbuffer* ring = tny_ringbuffer_new(10);

  // manually set up the read state since this may not be implemented yet
  tny_ringbuffer_push(ring, "foobar", 6);

  char result[100];
  memset(result, 0, 100);

  tny_ringbuffer_peek(ring, result, 6);
  if (strcmp("foobar", result))
  {
    printf ("Peek failed\n");
    return 1;
  }

  memset(result, 0, 100);
  tny_ringbuffer_pop(ring, result, 3);
  if (strcmp("foo", result))
  {
    printf ("Pop failed\n");
    return 1;
  }

  tny_ringbuffer_push(ring, "bazbar", 6);

  memset(result, 0, 100);
  tny_ringbuffer_peek(ring, result, 9);
  if (strcmp("barbazbar", result))
  {
    printf ("Peek 2 failed\n");
    return 1;
  }

  // FIXME: Doesn't check wrap reading

  tny_ringbuffer_free(ring);

  printf ("Write Pass\n");

  return 0;
}