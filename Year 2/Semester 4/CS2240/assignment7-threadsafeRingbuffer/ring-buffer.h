#ifndef TRENARY_RINGBUFFER_H
#define TRENARY_RINGBUFFER_H

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TNY_OK 0
#define TNY_FAIL 1

struct tny_ringbuffer_ {
	char *buffer; // Data
	size_t size;  // Size of ring buffer
	char *begin;  // Beginning pointer
	char *end;    // Ending pointer
	pthread_mutex_t datMutex; // A mutex
};

typedef void * tny_dataptr;
typedef struct tny_ringbuffer_ tny_ringbuffer;

tny_ringbuffer * tny_ringbuffer_new(size_t size);
void tny_ringbuffer_free(tny_ringbuffer* self);
void tny_ringbuffer_clear(tny_ringbuffer* self);
size_t tny_ringbuffer_get_free_space(const tny_ringbuffer* self);
size_t tny_ringbuffer_get_data_size(const tny_ringbuffer* self);
int tny_ringbuffer_push(tny_ringbuffer* self, tny_dataptr data, size_t size);
int tny_ringbuffer_pop(tny_ringbuffer* self, tny_dataptr data, size_t size);
int tny_ringbuffer_peek(tny_ringbuffer* self, tny_dataptr data, size_t size);

#endif