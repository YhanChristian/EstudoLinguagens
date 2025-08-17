#include <stdio.h>
#include "dynamic_array.h"


int main() {
    printf("Dynamic Array Module\n");
    st_dynamic_array *array = initialize(2);


    if (array == NULL) {
        printf("Failed to initialize dynamic array\n");
        return 1; 
    }

    int a = 10, b = 20, c = 30;
    char d = 'A';
    char *str = "Hello";
    float f = 3.14f;
    double db = 2.718281828459;

    push(array, &a, sizeof(int), INT);
    push(array, &b, sizeof(int), INT);
    push(array, &c, sizeof(int), INT);
    push(array, &d, sizeof(char), CHAR);
    push(array, str, strlen(str) + 1, STRING);
    push(array, &f, sizeof(float), FLOAT);
    push(array, &db, sizeof(double), DOUBLE);
    
    print_array(array);
    printf("\nLength: %zu, Capacity: %zu\n", get_length(array), get_capacity(array));
    printf("\n\n");

    remove_at(array, 4); 
    printf("After removing element at index 4:\n");
    print_array(array);
    printf("\nLength: %zu, Capacity: %zu\n", get_length(array), get_capacity(array));

    printf("Testing error handling for out of bounds access\n");
    get_at(array, 100);

    free_array(array);
    return 0;
}