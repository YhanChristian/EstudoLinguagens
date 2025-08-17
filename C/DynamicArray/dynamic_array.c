#include "dynamic_array.h"

 /**
  * Initializes a dynamic array with a specified initial capacity.
  * @param initial_capacity The initial capacity of the dynamic array.
  * @return A pointer to the initialized dynamic array.
  */
st_dynamic_array* initialize(size_t initial_capacity)
 {
    st_dynamic_array *array = (st_dynamic_array *)malloc(sizeof(st_dynamic_array));
    if(array == NULL) { 
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }

    array->items = calloc(initial_capacity, sizeof(st_any_type));
    if(array->items == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        free(array);
        return NULL;
    }
    array->capacity = initial_capacity;
    array->length = 0;
    return array;
}

/**
 * Ensures that the dynamic array has enough capacity to hold additional elements.
 * If the current length is equal to the capacity, the capacity is doubled.
 * @param array The dynamic array to ensure capacity for.
 */
void ensure_capacity(st_dynamic_array *array) 
{
    if(array->length >= array->capacity) {
        size_t new_capacity = array->capacity * 2;
        st_any_type *new_items = realloc(array->items, new_capacity * sizeof(st_any_type));
        if(new_items == NULL) {
            fprintf(stderr, "Memory reallocation failed\n");
            free(array->items);
            free(array);
            return;
        }
        array->items = new_items;
        array->capacity = new_capacity;
    }
}

/**
 * Adds a new element to the end of the dynamic array.
 * @param array The dynamic array to add the element to.
 * @param value A pointer to the value to be added.
 * @param size The size of the value in bytes.
 * @param data_type The type of the value being added.
 */
void push(st_dynamic_array *array, void *value, size_t size, e_type data_type) 
{
    ensure_capacity(array);
    st_any_type new_item;
    new_item.data = malloc(size);
    if(new_item.data == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return;
    }
    memcpy(new_item.data, value, size);
    new_item.size = size;
    new_item.data_type = data_type;
    array->items[array->length++] = new_item;
}

/**
 * Inserts a new element at a specified index in the dynamic array.
 * @param array The dynamic array to insert the element into.
 * @param index The index at which to insert the new element.
 * @param value A pointer to the value to be inserted.
 * @param size The size of the value in bytes.
 * @param data_type The type of the value being inserted.
 */
void insert_at(st_dynamic_array *array, size_t index, void *value, size_t size, e_type data_type) 
{
    if(index > array->length) {
        fprintf(stderr, "Index out of bounds\n");
        return;
    }
    ensure_capacity(array);
    for(size_t i = array->length; i > index; i--) {
        array->items[i] = array->items[i - 1];
    }
    st_any_type new_item;
    new_item.data = malloc(size);
    if(new_item.data == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return;
    }
    memcpy(new_item.data, value, size);
    new_item.size = size;
    new_item.data_type = data_type;
    array->items[index] = new_item;
    array->length++;
}

/**
 * Removes an element at a specified index from the dynamic array.
 * @param array The dynamic array to remove the element from.
 * @param index The index of the element to be removed.
 */

void remove_at(st_dynamic_array *array, size_t index) 
{
    if(index < 0 || index >= array->length) {
        fprintf(stderr, "Index out of bounds\n");
        return;
    }

    free(array->items[index].data);
    for(size_t i = index; i < array->length - 1; i++) {
        array->items[i] = array->items[i + 1];
    }
    array->length--;
}

/**
 * Retrieves the element at a specified index from the dynamic array.
 * @param array The dynamic array to retrieve the element from.
 * @param index The index of the element to be retrieved.
 * @return A pointer to the data at the specified index, or NULL if the index is out of bounds.
 */
void* get_at(st_dynamic_array *array, size_t index)
{
    if(index < 0 || index >= array->length) {
        fprintf(stderr, "Index out of bounds\n");
        return NULL;
    }
    return array->items[index].data;
}

/**
 * Returns the current length of the dynamic array.
 * @param array The dynamic array to get the length of.
 * @return The current length of the dynamic array.
 */
void print_array(st_dynamic_array *array) 
{
    for(size_t i = 0; i < array->length; i++) {
        switch(array->items[i].data_type) {
            case INT:
                printf("%d ", *(int *)array->items[i].data);
                break;
            case FLOAT:
                printf("%f ", *(float *)array->items[i].data);
                break;
            case DOUBLE:
                printf("%lf ", *(double *)array->items[i].data);
                break;
            case CHAR:
                printf("%c ", *(char *)array->items[i].data);
                break;
            case STRING:
                printf("%s ", (char *)array->items[i].data);
                break;
            case BOOL:
                printf("%s ", (*(int *)array->items[i].data) ? "true" : "false");
                break;
            default:
                printf("Unknown type ");
                break;
        }
    }
    printf("\n");
}

/**
 * Returns the current length of the dynamic array.
 * @param array The dynamic array to get the length of.
 * @return The current length of the dynamic array.
 */
size_t get_length(st_dynamic_array *array)
{
    return array->length;
}

/**
 * Returns the current capacity of the dynamic array.
 * @param array The dynamic array to get the capacity of.
 * @return The current capacity of the dynamic array.
 */
size_t get_capacity(st_dynamic_array *array)
{
    return array->capacity;
}

/**
 * Cleans up and frees all memory associated with the dynamic array.
 * @param array The dynamic array to be freed.
 */
void free_array(st_dynamic_array *array) 
{
    if(array == NULL) return;
    for(size_t i = 0; i < array->length; i++) {
        free(array->items[i].data);
    }
    free(array->items);
    free(array);
}