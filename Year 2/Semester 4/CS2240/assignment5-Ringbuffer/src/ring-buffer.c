//Author: Marcel Englmaier

#include "ring-buffer.h"

#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <stdlib.h>


#define TNY_OK 0;
#define TNY_FAIL 1;

typedef struct tny_ringbuffer_{
    char *buffer;
    size_t size;
    char *begin;
    char *end;
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

void tny_ringbuffer_resize (tny_ringbuffer* self, size_t size);

tny_ringbuffer * tny_ringbuffer_new(size_t size)
{
	tny_ringbuffer * ringBuff;
	ringBuff = (tny_ringbuffer *) calloc(1, sizeof(tny_ringbuffer));
	ringBuff->size = size;
	ringBuff->buffer = (char *) calloc(ringBuff->size, sizeof(char));
	ringBuff->begin = NULL;
	ringBuff->end = NULL;
	return ringBuff;
}

void tny_ringbuffer_free(tny_ringbuffer* self)
{
	free(self->buffer); 
	free(self);
}

void tny_ringbuffer_clear(tny_ringbuffer* self)
{
	memcpy(self->buffer, "", self->size);
	self->begin = NULL;
	self->end = NULL;
}

size_t tny_ringbuffer_get_free_space(const tny_ringbuffer* self)
{
	size_t freeSpace = 0;
	freeSpace = self->size - tny_ringbuffer_get_data_size(self);
	return freeSpace;
}

size_t tny_ringbuffer_get_data_size(const tny_ringbuffer* self)
{
	size_t dataSize = 0; 
	if(self->end >= self->begin)
	dataSize = self->end - self->begin + 1;
	else
	{
		dataSize = self->end + self->size - self->begin + 1;
	}
	return dataSize;
}

int tny_ringbuffer_push(tny_ringbuffer* self, tny_dataptr data, size_t size)
{
	int firstSize = 0;
	int secondSize = 0;
	firstSize = (long int)self->buffer + self->size - (long int)self->end - 1;
	secondSize = size - firstSize;
	if((self->end == NULL) && (self->begin == NULL)) 
	{

		self->begin = self->buffer;
		self->end = self->buffer + size - 1;
		memcpy(self->begin, data, size);
	}
	else
	{
		if(size <= firstSize)
		{
			memcpy(self->end + 1, data, size);
			self->end += size;
		}
		else
		{
			if(self->end != self->buffer + self->size - 1)
			{
				memcpy(self->end + 1, data, firstSize);
				memcpy(self->buffer, data + firstSize, secondSize);
				self->end = self->buffer + secondSize;
			}
			else
			{
				memcpy(self->buffer, data, size);
				self->end = self->buffer + size;
			}
		}
	}
	return 0;
}

int tny_ringbuffer_pop(tny_ringbuffer* self, tny_dataptr data, size_t size)
{
	int size1 = 0;
	int size2 = 0;
	size_t dataSize = tny_ringbuffer_get_data_size(self);
	char tempString[size];
	char realData[size];
	size1 = (long int)self->buffer + self->size - (long int)self->begin;
	size2 = size - size1 + 1;
	if(self->end > self->begin)
	{
		memcpy(data, self->begin, size);
		memcpy(self->begin, "", size);
		if(size == dataSize) 
		{
			self->begin = NULL;
			self->end = NULL;
		}
		else 
		{
			self->begin += size;
		}
	}
	else if((self->end != NULL) && (self->begin != NULL))
	{
		if(size <= size1)
		{
			memcpy(data, self->begin, size);
			memcpy(self->begin, "", size);
		}
		else
		{
			memcpy(tempString, self->begin, size1 + 1);
			strcpy(realData, tempString);
			memcpy(tempString, self->buffer, size2);
			strcat(realData, tempString);
			memcpy(data, realData, size);
			memcpy(self->begin, "", size1);
			memcpy(self->buffer, "", size2);
		}
		if(size < size1)
		{
			self->begin += size;
		}
		else
		{
			self->begin = self->buffer + size2;
			if(size == dataSize)
			{
				self->begin = NULL;
				self->end = NULL;
			}
		}
	}
	return 0;
}

int tny_ringbuffer_peek(tny_ringbuffer* self, tny_dataptr data, size_t size)
{
	int size1 = 0;
	int size2 = 0;
	char tempString[size];
	char realData[size];
	size1 = (long int)self->buffer + self->size - (long int)self->begin;
	size2 = size - size1 + 1;
	if(self->end > self->begin)
	{
		memcpy(data, self->begin, size);
	}
	else if((self->end != NULL) && (self->begin != NULL))
	{
		if(size <= size1)
		{
			memcpy(data, self->begin, size);
		}
		else
		{
			memcpy(tempString, self->begin, size1 + 1);
			strcpy(realData, tempString);
			memcpy(tempString, self->buffer, size2);
			strcat(realData, tempString);
			memcpy(data, realData, size);
		}
	}
	return 0;
}

void tny_ringbuffer_resize (tny_ringbuffer* self, size_t size)
{
	size_t dataSize = tny_ringbuffer_get_data_size(self);
	char popData[dataSize + 1];
	tny_ringbuffer_pop(self, popData, dataSize);
	tny_ringbuffer_free(self);
	self = tny_ringbuffer_new(size);
	tny_ringbuffer_push(self, popData, dataSize);
}
