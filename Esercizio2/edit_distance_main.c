#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>
#include "edit_distance.h"

#define INIT_ARRAY_SIZE 200
#define DICTIONARY_SIZE 2000000

typedef struct{
  char ** array;
  int max_size;
  int size;
}StringArray;

typedef struct{
  int * array;
  int max_size;
  int size;
}IndexArray;

//Load the correctme words to a StringArray struct
static StringArray * load_correctme(const char* correctme){
  StringArray * words=malloc(sizeof(StringArray));
  if(words == NULL){
    fprintf(stderr,"main: unable to allocate memory for the struct StringArray");
    exit(EXIT_FAILURE);
  }  
  words->array=malloc(sizeof(char*)*INIT_ARRAY_SIZE);
  if(words->array == NULL){
    fprintf(stderr,"main: unable to allocate memory for the array of strings");
    exit(EXIT_FAILURE);
  } 
  words->max_size=INIT_ARRAY_SIZE;
  char *read_line_p;
  char buffer[1024];
  int buf_size = 1024;
  FILE *fp;
  printf("\nLoading the corectme...\n");
  fp = fopen(correctme,"r");
  if(fp == NULL){
    fprintf(stderr,"main: unable to open the file");
    exit(EXIT_FAILURE);
  }
  int i=0;
  while(fgets(buffer,buf_size,fp) != NULL){
    read_line_p = malloc((strlen(buffer)+1)*sizeof(char));
    if(read_line_p == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read line");
      exit(EXIT_FAILURE);
    }   
    strcpy(read_line_p,buffer); 
    char * word=strtok(read_line_p," ,.-:\n");
    while(word!=NULL){
      word[0]=(char)tolower(word[0]);
      char *entry= malloc((strlen(word)+1)*sizeof(char));
      if(entry == NULL){
        fprintf(stderr,"main: unable to allocate memory for the word");
        exit(EXIT_FAILURE);
      }
      strcpy(entry,word);
      if(i==words->max_size){
        words->array=realloc(words->array,sizeof(char *)*(long unsigned int)words->max_size*2);
        if(words->array == NULL){
          fprintf(stderr,"main: unable to realocate allocate memory for the words array");
          exit(EXIT_FAILURE);
        }
        words->max_size=words->max_size*2;
      }
      words->array[i]=entry; 
      word = strtok (NULL," ,.-:\n");
      i++;
    }
    free(read_line_p);
  }
  words->size=i;
  printf("\nCorectme loaded...\n");
  fclose(fp);
  return words;
}

//Load the dictionary words to a StringArray struct
static StringArray * load_dictionary(const char* readfile){
  StringArray * dictionary=malloc(sizeof(StringArray));
  if(dictionary == NULL){
    fprintf(stderr,"main: unable to allocate memory for the struct StringArray");
    exit(EXIT_FAILURE);
  }  
  dictionary->array=malloc(sizeof(char*)*DICTIONARY_SIZE);
  if(dictionary->array == NULL){
    fprintf(stderr,"main: unable to allocate memory for the array of strings");
    exit(EXIT_FAILURE);
  } 
  char *read_line_p;
  char buffer[1024];
  int buf_size = 1024;
  FILE *fp;
  printf("\nLoading the dictionary...\n");
  fp = fopen(readfile,"r");
  if(fp == NULL){
    fprintf(stderr,"main: unable to open the file");
    exit(EXIT_FAILURE);
  }
  int i=0;
  while(fgets(buffer,buf_size,fp) != NULL){
    read_line_p = malloc((strlen(buffer)+1)*sizeof(char));
    if(read_line_p == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read line");
      exit(EXIT_FAILURE);
    }   
    strcpy(read_line_p,buffer); 
    char * word=strtok(read_line_p," \n");
    char * entry= malloc((strlen(word)+1)*sizeof(char));
    if(entry == NULL){
      fprintf(stderr,"main: unable to allocate memory for entry");
      exit(EXIT_FAILURE);
    } 
    strcpy(entry,word);
    dictionary->array[i]=entry;
    free(read_line_p);
    i++;
  }
  printf("\nDictionary Loaded...\n");
  dictionary->size=i;
  fclose(fp);
  return dictionary;
}

//Create and return a collection of IndexArray where will be storred the minimum edit distance words indexes
static IndexArray ** create_correct_words_arrays(int nwords){
  IndexArray ** correct_words=malloc(sizeof(IndexArray*)*(long unsigned int)nwords);
  if(correct_words == NULL){
      fprintf(stderr,"main: unable to allocate memory for the read line");
      exit(EXIT_FAILURE);
  }  
  for(int i=0;i<nwords;i++){
    correct_words[i]=malloc(sizeof(IndexArray));
    if(correct_words[i] == NULL){
      fprintf(stderr,"main: unable to allocate memory for correct_words[%d]",i);
      exit(EXIT_FAILURE);
    }  
    correct_words[i]->array=malloc(sizeof(int)*INIT_ARRAY_SIZE);
    if(correct_words[i]->array == NULL){
      fprintf(stderr,"main: unable to allocate memory for correct_words[%d] array",i);
      exit(EXIT_FAILURE);
    }  
    correct_words[i]->max_size=INIT_ARRAY_SIZE;
    correct_words[i]->size=0;
  }
  return correct_words;
}

//For every word of the phrase, print the minimum edit distance words.
static void printresults(StringArray * words,StringArray * dictionary,IndexArray ** correct_words){
  for(int i=0;i<words->size;i++){
    printf("%s-->[",words->array[i]);
    for(int j=0;j<correct_words[i]->size;j++){
      if(j==correct_words[i]->size-1){
        printf("%s]",dictionary->array[correct_words[i]->array[j]]);
      }else{
        printf("%s,",dictionary->array[correct_words[i]->array[j]]);
      }
    }
    printf("\n");
  }
}

//Calculate the edit distances and store the minimum edit distances words indexes. 
static void calculate_edit_distances(StringArray * words,StringArray * dictionary,IndexArray ** correct_words){
  for(int i=0;i<words->size;i++){
      printf("word:%s\n",words->array[i]);
      int min=200;
      int res;
      for(int j=0;j<dictionary->size;j++){
        char *w=words->array[i];
        char *d=dictionary->array[j];
        if(abs((int)strlen(w)-(int)strlen(d))<=min){
          res=dyn_edit_distance_init(w,d);
          if(res<=min){
            if(res<min){
              min=res;
              correct_words[i]->size=0;
            }
            if(correct_words[i]->size==correct_words[i]->max_size){
              correct_words[i]->array=realloc(correct_words[i]->array,sizeof(int)*(long unsigned int)correct_words[i]->max_size*2);
              if(words->array == NULL){
                fprintf(stderr,"main: unable to realocate allocate memory for the correct_words array");
                exit(EXIT_FAILURE);
              }
              correct_words[i]->max_size=correct_words[i]->max_size*2;
            }
            correct_words[i]->array[correct_words[i]->size]=j;
            correct_words[i]->size=correct_words[i]->size+1;
          }
        }
      }
  }
}

static void free_structs(StringArray * words,StringArray * dictionary,IndexArray ** correct_words){
  
  for(int i=0;i<words->size;i++){
    free(correct_words[i]->array);
    free(correct_words[i]);
  }
  free(correct_words);
  
  for(int i=0;i<words->size;i++){
    free(words->array[i]);
  }
  free(words->array);
  free(words);
  
  for(int i=0;i<dictionary->size;i++){
    free(dictionary->array[i]);
  }
  free(dictionary->array);
  free(dictionary);
}
//Correct the phrase specified in correctme file
//printing for each word of the phrase the words in dictionary that have the minimum edit distance.
static void correct_phrase(const char* correctme,const char* dictionary_file){
 
  StringArray * words=load_correctme(correctme);
  StringArray * dictionary=load_dictionary(dictionary_file);
  IndexArray ** correct_words=create_correct_words_arrays(words->size);
  clock_t t = clock();
  calculate_edit_distances(words,dictionary,correct_words);
  t = clock() - t;
	double time_taken = ((double)t)/CLOCKS_PER_SEC;
 
  printresults(words,dictionary,correct_words);
  free_structs(words,dictionary,correct_words);
  printf("\ntime:%lf\n",time_taken);
}

//Expet as first parameter a filepath to a file containing the correctme phare
//ad as seccond parameter a filepath to the dictionary
int main(int argc, char const *argv[]) {
  if(argc < 3) {
    printf("Usage: ordered_array_main <file_name>\n");
    exit(EXIT_FAILURE);
  }
  correct_phrase(argv[1],argv[2]);
  //printf("%d\n",dyn_edit_distance_init("","ciao"));
  return (EXIT_SUCCESS);
}
