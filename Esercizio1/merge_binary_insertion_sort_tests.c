#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "merge_binary_insertion_sort.h"

/*
 * Test suite for merge binary insertion sort algorithm
 */

//precedence relation used in tests
static int precedes_int(void* i1_p,void* i2_p){
  int* int1_p = (int*)i1_p;
  int* int2_p = (int*)i2_p;
  if((*int1_p) < (*int2_p))
    return(1);
  return(0);
}

//Data elements that are initialized before each test
static int a,b,c;

void setUp(void){
  a = 6;
  b = 5;
  c = 11;
}

static void test_merge_binary_insertion_sort_one_el_zero_k(void){
  int* tested[]={&a};
  merge_binary_insertion_sort(precedes_int,(void **)tested,0,1);
  TEST_ASSERT_EQUAL_INT(a,*tested[0]);
}

static void test_merge_binary_insertion_sort_two_el_zero_k(void){
  int* tested[]={&a,&b};
  merge_binary_insertion_sort(precedes_int,(void **)tested,0,2);
  TEST_ASSERT_EQUAL_INT(b,*tested[0]);
  TEST_ASSERT_EQUAL_INT(a,*tested[1]);
}

static void test_merge_binary_insertion_sort_three_el_zero_k(void){
  int* tested[]={&a,&b,&c};
  merge_binary_insertion_sort(precedes_int,(void **)tested,0,3);
  TEST_ASSERT_EQUAL_INT(b,*tested[0]);
  TEST_ASSERT_EQUAL_INT(a,*tested[1]);
  TEST_ASSERT_EQUAL_INT(c,*tested[2]);
}

static void test_merge_binary_insertion_sort_one_el_max_k(void){
  int* tested[]={&a};
  merge_binary_insertion_sort(precedes_int,(void **)tested,1,1);
  TEST_ASSERT_EQUAL_INT(a,*tested[0]);
}

static void test_merge_binary_insertion_sort_two_el_max_k(void){
  int* tested[]={&a,&b};
  merge_binary_insertion_sort(precedes_int,(void **)tested,2,2);
  TEST_ASSERT_EQUAL_INT(b,*tested[0]);
  TEST_ASSERT_EQUAL_INT(a,*tested[1]);
}

static void test_merge_binary_insertion_sort_three_el_max_k(void){
  int* tested[]={&a,&b,&c};
  merge_binary_insertion_sort(precedes_int,(void **)tested,3,3);
  TEST_ASSERT_EQUAL_INT(b,*tested[0]);
  TEST_ASSERT_EQUAL_INT(a,*tested[1]);
  TEST_ASSERT_EQUAL_INT(c,*tested[2]);
}

//If the sort works on 0 elements the array should not change.
static void test_merge_binary_insertion_sort_zero_el(void){
  int* tested[]={&a,&b};
  merge_binary_insertion_sort(precedes_int,(void **)tested,0,0);
  TEST_ASSERT_EQUAL_INT(a,*tested[0]);
  TEST_ASSERT_EQUAL_INT(b,*tested[1]);
}





int main(void) {

  //test session
  UNITY_BEGIN();
  
  RUN_TEST(test_merge_binary_insertion_sort_one_el_zero_k);
  RUN_TEST(test_merge_binary_insertion_sort_two_el_zero_k);
  RUN_TEST(test_merge_binary_insertion_sort_three_el_zero_k);
  RUN_TEST(test_merge_binary_insertion_sort_one_el_max_k);
  RUN_TEST(test_merge_binary_insertion_sort_two_el_max_k);
  RUN_TEST(test_merge_binary_insertion_sort_three_el_max_k);
  RUN_TEST(test_merge_binary_insertion_sort_zero_el);
	
  return UNITY_END();
}
