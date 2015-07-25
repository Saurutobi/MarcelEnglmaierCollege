//Authoer: Marcel Englmaier

#include "ring-buffer.h"
#include <pthread.h>

void tny_ringbuffer_resize (tny_ringbuffer* self, size_t size);
long int endNote = 0;



tny_ringbuffer * tny_ringbuffer_new(size_t size)
{
	tny_ringbuffer * ringBuffer; // Points to the ringbuffer
	ringBuffer = (tny_ringbuffer *) calloc(1, sizeof(tny_ringbuffer));
	ringBuffer->size = size;
	ringBuffer->buffer = (char *) calloc(ringBuffer->size, sizeof(char));//calloc mem
	ringBuffer->begin = NULL; //set begin
	ringBuffer->end = NULL;	//set the end
	pthread_mutex_init(&ringBuffer->datMutex, NULL);
	return ringBuffer;
}

void tny_ringbuffer_free(tny_ringbuffer* self)
{
	free(self->buffer); 
	pthread_mutex_destroy(&self->datMutex);
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
	int lockDown; 
	
	do
	{
		lockDown = pthread_mutex_trylock(&self->datMutex);
	}
	while(lockDown != 0);
	
	int size1 =  (long int)self->buffer + self->size - (long int)self->end - 1;
	int size2 = size - size1;
	if((self->end == NULL) && (self->begin == NULL)) 
	{
		
		self->begin = self->buffer;
		self->end = self->buffer + size - 1;
		endNote = (long int) self->end;
		memcpy(self->begin, data, size);
	}
	else
	{
		if(size <= size1) 
		{
			memcpy(self->end + 1, data, size);
			self->end += size;
			if((endNote < (long int)self->end) && ((long int)self->end > (long int)self->begin))
			{
				if(self->end != self->buffer + self->size - 1)
				{
					self->begin = self->end + 1;
				}
				else
				{
					self->begin = self->buffer;
				}
			}
			endNote = (long int) self->end;
		}
		else	//wrap-around
		{
			if(self->end != self->buffer + self->size - 1)
			{
				memcpy(self->end + 1, data, size1);
				memcpy(self->buffer, data + size1, size2);
				self->end = self->buffer + size2 - 1;
				if((endNote > (long int)self->end) && (endNote < (long int)self->begin))
				{
					self->begin = self->end + 1;
				}
				endNote = (long int)self->end;
			}
			else
			{
				int before;
				before = (int)tny_ringbuffer_get_free_space(self);
				memcpy(self->buffer, data, size);
				self->end = self->buffer + size - 1;
				if((self->begin == self->buffer) && (before == 0))
				{
					self->begin = self->end + 1;
				}
				endNote = (long int)self->end;
			}
		}
	}
	//unlock the thread
	pthread_mutex_unlock(&self->datMutex);
	return 0;
}

int tny_ringbuffer_pop(tny_ringbuffer* self, tny_dataptr data, size_t size)
{
	int lockDown; 
	do
	{
		lockDown = pthread_mutex_trylock(&self->datMutex);
	}
	while(lockDown != 0);
	
	int size1 = 0;
	int size2 = 0;
	size_t dataSize = tny_ringbuffer_get_data_size(self);
	char tempString[size];
	char realData[size];
	size1 =  (long int)self->buffer + self->size - (long int)self->begin;
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
	else if((self->end != NULL) && (self->begin != NULL)) //it's circular
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
			self->begin = self->buffer + size2 -1;
			if(size == dataSize) //reset pointers once everything is popped
			{
				self->begin = NULL;
				self->end = NULL;
			}			
		}
	}
	//unlock this bitch!
	pthread_mutex_unlock(&self->datMutex);
	return 0;
}

int tny_ringbuffer_peek(tny_ringbuffer* self, tny_dataptr data, size_t size)
{
	int size1 =  (long int)self->buffer + self->size - (long int)self->begin;
	int size2 = size - size1 + 1;
	char tempString[size];
	char realData[size];
	if(self->end > self->begin)
	{
		memcpy(data, self->begin, size);
	}
	else if((self->end != NULL) && (self->begin != NULL)) //it's circular!
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