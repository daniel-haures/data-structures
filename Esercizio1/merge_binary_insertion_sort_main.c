#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "merge_binary_insertion_sort.h"



struct Record{
  int id;
  char* field1;
  int field2;
  float field3;
};


//It takes as input two pointers to struct record.
//It returns 1 iff the integer field of the first record is less or equal than
//the integer field of the second one,0 otherwise
static int precedes_record_int_field(void* r1_p,void* r2_p){
  if(r1_p == NULL){
    fprintf(stderr,"precedes_record_int_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_record_int_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  struct Record *rec1_p = (struct Record*)r1_p;
  struct Record *rec2_p = (struct Record*)r2_p;
  if(rec1_p->field2 <= rec2_p->field2){
    return(1);
  }
  return(0);
}

//It takes as input two pointers to struct record.
//It returns 1 iff the string field of the first record is less or equal than
//the integer field of the second one,0 otherwise
static int precedes_record_string_field(void* r1_p,void* r2_p){
	
  if(r1_p == NULL){
    fprintf(stderr,"precedes_string: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_string: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
	
  struct Record *rec1_p = (struct Record*)r1_p;
  struct Record *rec2_p = (struct Record*)r2_p;
	
  if(strcmp(rec1_p->field1,rec2_p->field1)<=0){
    return(1);
  }
  return(0);
	
}

//It takes as input two pointers to struct record.
//It returns 1 iff the float field of the first record is less or equal than
//the integer field of the second one,0 otherwise
static int precedes_record_float_field(void* r1_p,void* r2_p){
  if(r1_p == NULL){
    fprintf(stderr,"precedes_record_int_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_record_int_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  struct Record *rec1_p = (struct Record*)r1_p;
  struct Record *rec2_p = (struct Record*)r2_p;

  if(rec1_p->field3 <= rec2_p->field3){
    return(1);
  }
  return(0);
	
}


//Load data from csv
static void load_array(const char* file_name, struct Record ** array,int size){
  char *read_line_p;
  char buffer[1024];
  int buf_size = 1024;
  int i;
  FILE *fp;
  printf("\nLoading data from file...\n");
  fp = fopen(file_name,"r");
  if(fp == NULL){
    fprintf(stderr,"main: unable to open the file");
    exit(EXIT_FAILURE);
  }
  i=0;
  while(fgets(buffer,buf_size,fp) != NULL && i<size){
    read_line_p = malloc((strlen(buffer)+1)*sizeof(char));
    if(read_line_p == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read line");
      exit(EXIT_FAILURE);
    }
    strcpy(read_line_p,buffer);
    char *id_in_read_line_p = strtok(read_line_p,",");
    char *field1_in_read_line_p = strtok(NULL,",");
    char *field2_in_read_line_p = strtok(NULL,",");
    char *field3_in_read_line_p = strtok(NULL,",");
    int id_field=atoi(id_in_read_line_p);
    char *field1 = malloc((strlen(field1_in_read_line_p)+1)*sizeof(char));
    if(field1 == NULL){
      fprintf(stderr,"main: unable to allocate memory for the string field of the read record");
      exit(EXIT_FAILURE);
    }
    strcpy(field1,field1_in_read_line_p);
    int field2 = atoi(field2_in_read_line_p);
    float field3 =(float) atof(field3_in_read_line_p);
    struct Record *record_p = malloc(sizeof(struct Record));
    if(record_p == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read record");
      exit(EXIT_FAILURE);
    }
    record_p->id = id_field;
    record_p->field1 = field1;
    record_p->field2 = field2;
    record_p->field3 = field3;
	
    array[i]=record_p;
    free(read_line_p);
    i++;
  }
  fclose(fp);
  printf("\nData loaded\n");
  
}

static  void free_array(struct Record** array,int size){

  for(int i=0;i<size;i++){
    free(array[i]->field1);
    free(array[i]);
  }
  free(array);
  printf("\nFree array complete\n");
}

//Print an arrat value at every step
static void step_print(struct Record** array,int step,int size){
  if(step<1)step=1;
  for(int i=0;i<size;i=i+step){
    printf("Array posizione %d f1:%s f2:%d f3:%f\n",i,array[i]->field1,array[i]->field2,array[i]->field3);
  }
	
}

//Print from 0 to tot_rec value of array.
static void seq_print(struct Record** array,int tot_rec){
  for(int i=0;i<tot_rec;i++){
    printf("Array posizione %d f1:%s f2:%d f3:%f\n",i,array[i]->field1,array[i]->field2,array[i]->field3);
  }
}

static double test_merge_binary_insertion_sort(const char* file_name, int (*compare)(void*, void*),int d,int size) {
  struct Record ** array=malloc(sizeof(struct Record *)*(long unsigned int)size);
  if(array == NULL){
    fprintf(stderr, "main: unable to allocate memory for the array");
    exit(EXIT_FAILURE);
  }
  load_array(file_name,array,size);
  clock_t t = clock();
  merge_binary_insertion_sort(compare,(void**)array,d,size);
  t = clock() - t;
  double time_taken = ((double)t)/CLOCKS_PER_SEC;
  printf("\nModular print:\n");
  step_print(array,size/50,size);
  printf("\nSequential print:\n");
  if(size<50){
    seq_print(array,size);
  }else{
    seq_print(array,50);
  }
  free_array(array,size);
  return time_taken;
}

//It should be invoked with one parameter specifying the filepath of the data file, 
//one specifying the k value and one specifying the number of element we want to consider from data file
int main(int argc, char const *argv[]) {
  int d,size;
  double times[3];
  if(argc < 4) {
    printf("Usage: ordered_array_main <file_name>\n");
    exit(EXIT_FAILURE);
  }
  
  d=atoi(argv[2]);
  size=atoi(argv[3]);
  if(size<0){
    printf("Usage: third argment can't be negative");
    exit(EXIT_FAILURE);
  }
  if(d<0 || d>size){
    printf("Usage: second argment can't be negative or greater than the size of the array");
  exit(EXIT_FAILURE);
  }
  
  printf("\nSorting int\n");
  times[0]=test_merge_binary_insertion_sort(argv[1], precedes_record_int_field,d,size);
  printf("\nSorting Strings\n");
  times[1]=test_merge_binary_insertion_sort(argv[1], precedes_record_string_field,d,size);
  printf("\nSorting floats\n");
  times[2]=test_merge_binary_insertion_sort(argv[1], precedes_record_float_field,d,size);
	
  printf("Time for sorting int with k=%d : %lf\n",d,times[0]);
  printf("Time for sorting strings with k=%d : %lf\n",d,times[1]);
  printf("Time for sorting floats with k=%d : %lf\n",d,times[2]);
	
  return (EXIT_SUCCESS);
}
