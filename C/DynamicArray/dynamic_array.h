/**
 * @file dynamic_array.h
 * @brief Header file for dynamic array implementation in C.
 * This file defines the structures and function prototypes for managing a dynamic array.
 * It supports operations such as initialization, insertion, removal, and printing of elements.
 * Author: Yhan Christian Souza Silva
 * Obs.: This project is only for educational purposes.
 * It is not intended for production use and may not handle all edge cases or errors robustly.
 */

#ifndef _DYNAMIC_ARRAY_H_
#define _DYNAMIC_ARRAY_H_

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef enum {
    INT,
    FLOAT,
    DOUBLE,
    CHAR,
    STRING,
    BOOL
} e_type;

typedef struct {
    void *data;
    size_t size;
    e_type data_type;

} st_any_type;

typedef struct {
    st_any_type *items;
    size_t capacity;
    size_t length;
} st_dynamic_array;


st_dynamic_array* initialize(size_t initial_capacity);
void ensure_capacity(st_dynamic_array *array);
void push(st_dynamic_array *array, void *value, size_t size, e_type data_type);
void insert_at(st_dynamic_array *array, size_t index, void *value, size_t size, e_type data_type);
void remove_at(st_dynamic_array *array, size_t index);
void* get_at(st_dynamic_array *array, size_t index);

void print_array(st_dynamic_array *array);
size_t get_length(st_dynamic_array *array);
size_t get_capacity(st_dynamic_array *array);
void free_array(st_dynamic_array *array);

#endif