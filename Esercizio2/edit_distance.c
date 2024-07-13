#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "edit_distance.h"

static int dyn_edit_distance(char* s1, char* s2,int *a,int vv);
static int edit_distance(char* s1, char* s2);

int edit_distance_init(char* s1, char* s2){
  if(s1==NULL){
    fprintf(stderr,"edit_distance: s1 parameter can't be NULL");
    exit(EXIT_FAILURE);
  }
  if(s2==NULL){
    fprintf(stderr,"edit_distance: s2 parameter can't be NULL");
    exit(EXIT_FAILURE);
  }
  return edit_distance(s1,s2);
}

//Return the edit distance between two strings with a recursion.
static int edit_distance(char* s1, char* s2){
  int s1_len=(int)strlen(s1);
  int s2_len=(int)strlen(s2);
  if(s1_len==0){
    return s2_len;
  }
  if(s2_len==0){
    return s1_len;
  }
  int no_op=s1_len*20;
  if(s1[0]==s2[0]){
    no_op=edit_distance(s1+1,s2+1);
  }
  int dcanc=1+edit_distance(s1,s2+1);
  int dins=1+edit_distance(s1+1,s2);
  int min=no_op;
  if(dcanc<min)min=dcanc;
  if(dins<min)min=dins;
  return min;
  
}

int dyn_edit_distance_init(char* s1, char* s2){
  if(s1==NULL){
    fprintf(stderr,"edit_distance: s1 parameter can't be NULL");
    exit(EXIT_FAILURE);
  }
  if(s2==NULL){
    fprintf(stderr,"edit_distance: s2 parameter can't be NULL");
    exit(EXIT_FAILURE);
  }
  int a [(strlen(s1)+1)*(strlen(s2)+1)];
  for(unsigned i=0;i<=strlen(s1);i++){
    for(unsigned j=0;j<=strlen(s2);j++){
      a[i+(j*(strlen(s1)+1))]=-1;
    }
  }
  return dyn_edit_distance(s1,s2,a,(int)strlen(s1)+1);
}

//Return the edit distance between them with a dynamic recursion.
static int dyn_edit_distance(char* s1, char* s2,int *a,int array_size){
  int s1_len=(int)strlen(s1);
  int s2_len=(int)strlen(s2);
  if(a[s1_len+(s2_len*array_size)]==-1){
    if(s1_len==0){
      return s2_len;
    }
    if(s2_len==0){
      return s1_len;
    }
    int no_op=s1_len*20;
    if(s1[0]==s2[0]){
      no_op=dyn_edit_distance(s1+1,s2+1,a,array_size);
    }
    int dcanc=1+dyn_edit_distance(s1,s2+1,a,array_size);
    int dins=1+dyn_edit_distance(s1+1,s2,a,array_size);
    int min=no_op;
    if(dcanc<min)min=dcanc;
    if(dins<min)min=dins;
    a[s1_len+(s2_len*array_size)]=min;
    return min;
   
  }else{
    return a[s1_len+(s2_len*array_size)];
  }
}
