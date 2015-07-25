include_directories (${RINGBUFFER_SOURCE_DIR}/src) 

# Make sure the linker can find the Hello library once it is built. 
link_directories (${RINGBUFFER_BINARY_DIR}/src) 

add_executable (create-test test-create.c)
target_link_libraries (create-test ring-buffer)  

add_executable (read-test test-read.c)
target_link_libraries (read-test ring-buffer) 

add_executable (write-test test-write.c)
target_link_libraries (write-test ring-buffer)

ADD_TEST( create-test ${CMAKE_CURRENT_BINARY_DIR}/create-test RINGBUFFER )
ADD_TEST( read-test ${CMAKE_CURRENT_BINARY_DIR}/read-test RINGBUFFER )
ADD_TEST( write-test ${CMAKE_CURRENT_BINARY_DIR}/write-test RINGBUFFER )