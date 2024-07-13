#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "merge_binary_insertion_sort.h"


static void merge(int (*precedes)(void*,void*),void** array,void ** supportarray,int start,int half,int end);
static void modified_merge_sort(int (*precedes)(void*,void*),void** array,void ** supportarray,int d,int start,int end);
static void binary_insertion_sort(int (*precedes)(void*,void*),void** array,int start,int end);
static int binary_search(int (*precedes)(void*,void*),void** array,int start,int end,int x);


void merge_binary_insertion_sort(int (*precedes)(void*,void*),void** array,int d,int size){
  if(precedes == NULL){
    fprintf(stderr,"merge_binary_insertion_sort: precedes parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  if(array == NULL){
    fprintf(stderr,"merge_binary_insertion_sort: array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  if(size<0){
    fprintf(stderr,"merge_binary_insertion_sort: size can't be negative");
    exit(EXIT_FAILURE);
  }
  for(int i=0;i<size;i++){
    if(array[i] == NULL){
      fprintf(stderr,"merge_binary_insertion_sort: array parameter cannot be NULL");
      exit(EXIT_FAILURE);
    }
  }
  if(d<0){
    fprintf(stderr,"merge_binary_insertion_sort: d can't be negative");
    exit(EXIT_FAILURE);
  }
  if(size>0){
    void ** supportarray=malloc(sizeof(void *)*(long unsigned int)size);
    if(array == NULL){
      fprintf(stderr, "main: unable to allocate memory for the array");
      exit(EXIT_FAILURE);
    }
    modified_merge_sort(precedes,array,supportarray,d,0,size-1);
  }
}

//Recursive function that apply a merge sort if d=0, a binary insertion sort if d=size, and a merge-sort conbined to a binary insertion sort if 0>d<size
static void modified_merge_sort(int (*precedes)(void*,void*),void** array,void ** supportarray,int d,int start,int end){
  int half;
  if(end-start<=d){
    binary_insertion_sort(precedes,array,start,end);
  }else{
    if(start<end){
      half=(start+end)/2;
      modified_merge_sort(precedes,array,supportarray,d,start,half);
      modified_merge_sort(precedes,array,supportarray,d,half+1,end);
      merge(precedes,array,supportarray,start,half,end);
    }
  }
}

//Merge two ordered arrays
static void merge(int (*precedes)(void*,void*),void** array,void ** supportarray,int start,int half,int end){
  int start_i=start;
  int half_j=half+1;
  int k=start;
  while(start_i<=half && half_j<=end){
    if(precedes(array[start_i],array[half_j])==1){
      supportarray[k]=array[start_i];
      start_i++;
    }else{
      supportarray[k]=array[half_j];
      half_j++;
    }
    k++;
  }
  if(start_i<=half){
    for(int i=k;i<=end;i++){
      supportarray[i]=array[start_i+(i-k)];
    }
  }else{
    for(int i=k;i<=end;i++){
      supportarray[i]=array[i];
    }
  }
  for(int i=start;i<=end;i++){
    array[i]=supportarray[i];
  }
}

//Sort an array from start to end with a binary insertion sort
static void binary_insertion_sort(int (*precedes)(void*,void*),void** array,int start,int end){
  for(int i=start+1;i<=end;i++){
    if(precedes(array[i-1],array[i])==0){
      int near_index=binary_search(precedes,array,start,i-1,i);
      void * mem_rec=array[i];
      for(int j=i;j>near_index;j--){
        array[j]=array[j-1];
      }
      array[near_index]=mem_rec;
    }
  }
}

//Return the index of the nearest element to x from start to end values of a array
static int binary_search(int (*precedes)(void*,void*),void** array,int start,int end,int x){
  if((end-start)==0){
    return start;
  }else{
    int m=(end+start)/2;
    if(precedes(array[x],array[m])==1){
      return binary_search(precedes,array,start,m,x);
    }else{
      return binary_search(precedes,array,m+1,end,x);
    }
  }
  return 0;	
}
