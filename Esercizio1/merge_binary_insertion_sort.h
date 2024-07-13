#ifndef MERGE_BINARY_INSERTION_SORT_H_dscnjanwjidnas
#define MERGE_BINARY_INSERTION_SORT_H_dscnjanwjidnas

//The function order an array of elements.
//It accept in input an array of pointers to a generic element and a comparison function implementing the 
//precedence relation between the array elements. 
//The comperison function must accept as input two pointers to the elements that will be compared 
//and returns 1 if the desired comparison function is satisfed;
//The first parameter,second parameter and the elements of the considered array can't be NULL
//The last two parameter must be valid and non negative
void merge_binary_insertion_sort(int (*precedes)(void*,void*),void** array,int d,int size);



#endif