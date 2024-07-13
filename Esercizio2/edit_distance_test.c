#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "edit_distance.h"

/*
 * Test suite for edit distance algorithms
 */


  
static void test_edit_distance_empty_strings(void){
  TEST_ASSERT_EQUAL_INT(0,edit_distance_init("",""));
}

static void test_edit_distance_same_strings(void){
  TEST_ASSERT_EQUAL_INT(0,edit_distance_init("HelloWord","HelloWord"));
}

static void test_edit_distance_one_empty(void){
  TEST_ASSERT_EQUAL_INT(9,edit_distance_init("","HelloWord"));
}

static void test_edit_distance_no_similar_strings(void){
  TEST_ASSERT_EQUAL_INT(8,edit_distance_init("Book","Dust"));
}

static void test_dyn_edit_distance_empty_strings(void){
  TEST_ASSERT_EQUAL_INT(0,dyn_edit_distance_init("",""));
}

static void test_dyn_edit_distance_same_strings(void){
  TEST_ASSERT_EQUAL_INT(0,dyn_edit_distance_init("HelloWord","HelloWord"));
}

static void test_dyn_edit_distance_one_empty(void){
  TEST_ASSERT_EQUAL_INT(9,dyn_edit_distance_init("","HelloWord"));
}

static void test_dyn_edit_distance_no_similar_strings(void){
  TEST_ASSERT_EQUAL_INT(8,dyn_edit_distance_init("Book","Dust"));
}


int main(void) {

  //test session
  UNITY_BEGIN();
  
  RUN_TEST(test_edit_distance_empty_strings);
  RUN_TEST(test_edit_distance_same_strings);
  RUN_TEST(test_edit_distance_one_empty);
  RUN_TEST(test_edit_distance_no_similar_strings);
  RUN_TEST(test_dyn_edit_distance_empty_strings);
  RUN_TEST(test_dyn_edit_distance_same_strings);
  RUN_TEST(test_dyn_edit_distance_one_empty);
  RUN_TEST(test_dyn_edit_distance_no_similar_strings);
  
  return UNITY_END();
}

