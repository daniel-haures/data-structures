/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unionfindset;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * It specifies a test suite for the Union Find Set Library
 * @author Daniel
 */
public class UnionFindSetTests {

  private UnionFindSet<String> unionFindSet;

  @Before
  public void createUnionFindSet() throws UnionFindSetExceptions {
    unionFindSet = new UnionFindSet<>();
  }

  @Test
  public void testOneElFind() throws Exception {
    String a = "ciao";
    unionFindSet.makeSet(a);
    assertTrue(a.equals(unionFindSet.findSet(a)));
  }

  @Test
  public void testFindOnTwoElDisjoined() throws Exception {
    String a = "ciao";
    String b = "cammello";
    unionFindSet.makeSet(a);
    unionFindSet.makeSet(b);
    assertFalse(a.equals(unionFindSet.findSet(b)));
    assertFalse(b.equals(unionFindSet.findSet(a)));
  }

  @Test
  public void testTwoElUnion() throws Exception {
    String a = "ciao";
    String b = "cammello";
    unionFindSet.makeSet(a);
    unionFindSet.makeSet(b);
    unionFindSet.union(a, b);
    assertTrue(b.equals(unionFindSet.findSet(a)));
  }

  @Test
  public void testRankEuristic() throws Exception {
    String a = "ciao";
    String b = "cammello";
    String c = "compito";
    unionFindSet.makeSet(a);
    unionFindSet.makeSet(b);
    unionFindSet.makeSet(c);
    //b become the father with rank 1
    unionFindSet.union(a, b);
    //b still remain the father because c is rank 0
    unionFindSet.union(b, c);
    assertTrue(b.equals(unionFindSet.findSet(c)));
  }

}
