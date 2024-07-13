package graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.Objects;
import java.util.ArrayList;


/**
 * It specifies a test suite for the Union Find Set Library
 * @author daniel
 */
public class GraphTests {

  private Graph<Node, Integer> inDirectGraph;
  private Graph<Node, Integer> directGraph;
  private Node a;
  private Node b;
  private Node c;

  @Before
  public void createGraph() throws GraphExceptions {
    this.a = new Node("Emanuele", 11);
    this.b = new Node("Daniel", 21);
    this.c = new Node("Marco", 22);
    this.directGraph = new Graph<Node, Integer>(true);
    this.inDirectGraph = new Graph<Node, Integer>(false);
  }

  @Test
  public void testContainsNodeZeroEl() throws GraphExceptions {
    assertFalse(directGraph.containsNode(a));
  }

  @Test
  public void testAddNodeWithContainsNode() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    assertTrue(directGraph.containsNode(a) && directGraph.containsNode(b));
  }

  @Test
  public void testAddNodeWithNumEdges() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    assertTrue(2 == directGraph.getNumNodes());
  }

  @Test
  public void testRemoveNodeWithNumEdges() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.removeNode(a);
    assertTrue(0 == directGraph.getNumNodes());
  }

  @Test
  public void testRemoveNodeWithContainsEdges() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.removeNode(a);
    assertFalse(directGraph.containsNode(a));
  }

  @Test
  public void testNumNodeZeroEl() throws GraphExceptions {
    assertTrue(0 == directGraph.getNumNodes());
  }

  @Test
  public void testNumNodeThreeEl() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addNode(c);
    assertTrue(3 == directGraph.getNumNodes());
  }

  @Test
  public void testNumNodeSameNode() {
    try {
      directGraph.addNode(a);
      directGraph.addNode(new Node("emanuele", 11));
    } catch (GraphExceptions a) {

    }
    assertTrue(1 == directGraph.getNumNodes());
  }

  @Test
  public void testContainsEdgeZeroElDirect() throws GraphExceptions {
    assertFalse(directGraph.containsEdge(a, b));
  }

  @Test
  public void testAddEdgeWithContainsEdgeDirect() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addNode(c);
    directGraph.addEdge(a, 5, b);
    directGraph.addEdge(a, 5, c);
    assertTrue(directGraph.containsEdge(a, b) && directGraph.containsEdge(a, c));
  }

  @Test
  public void testAddEdgeWithNumEdgesDirect() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addNode(c);
    directGraph.addEdge(a, 5, b);
    directGraph.addEdge(a, 5, c);
    assertTrue(2 == directGraph.getNumEdges());
  }

  @Test
  public void testRemoveEdgeWithContainsdgesDirect() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addEdge(a, 5, b);
    directGraph.removeEdge(a, b);
    assertFalse(directGraph.containsEdge(a, b));
  }

  @Test
  public void testRemoveEdgeWithNumEdgesDirect() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addEdge(a, 5, b);
    directGraph.removeEdge(a, b);
    assertTrue(0 == directGraph.getNumEdges());
  }

  @Test
  public void testNumEdgeZeroElDirect() throws GraphExceptions {
    assertTrue(0 == directGraph.getNumEdges());
  }

  @Test
  public void testNumEdgeTwoElDirect() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addNode(c);
    directGraph.addEdge(a, 5, b);
    directGraph.addEdge(a, 5, c);
    assertTrue(2 == directGraph.getNumEdges());
  }

  @Test
  public void testNumEdgeSameEdgeDirect() {
    try {
      directGraph.addNode(a);
      directGraph.addNode(b);
      directGraph.addEdge(a, 5, b);
      directGraph.addEdge(new Node("emanuele", 11), 4, new Node("daniel", 21));
    } catch (GraphExceptions a) {

    }
    assertTrue(1 == directGraph.getNumEdges());
  }

  
  @Test
  public void testContainsEdgeZeroElIndirect() throws GraphExceptions {
    assertFalse(inDirectGraph.containsEdge(a, b));
  }

  @Test
  public void testAddEdgeWithContainsEdgeInDirect() throws GraphExceptions {
    inDirectGraph.addNode(a);
    inDirectGraph.addNode(b);
    inDirectGraph.addNode(c);
    inDirectGraph.addEdge(a, 5, b);
    assertTrue(inDirectGraph.containsEdge(a, b) && inDirectGraph.containsEdge(b, a));
  }

  @Test
  public void testAddEdgeWithNumEdgesInDirect() throws GraphExceptions {
    inDirectGraph.addNode(a);
    inDirectGraph.addNode(b);
    inDirectGraph.addNode(c);
    inDirectGraph.addEdge(a, 5, b);
    inDirectGraph.addEdge(a, 5, c);
    assertTrue(2 == inDirectGraph.getNumEdges());
  }

  @Test
  public void testRemoveEdgeWithContainsdgesIndirect() throws GraphExceptions {
    inDirectGraph.addNode(a);
    inDirectGraph.addNode(b);
    inDirectGraph.addEdge(a, 5, b);
    inDirectGraph.removeEdge(a, b);
    assertFalse(inDirectGraph.containsEdge(a, b));
    assertFalse(inDirectGraph.containsEdge(b, a));
  }

  @Test
  public void testRemoveEdgeWithNumEdgesIndirect() throws GraphExceptions {
    inDirectGraph.addNode(a);
    inDirectGraph.addNode(b);
    inDirectGraph.addEdge(a, 5, b);
    inDirectGraph.removeEdge(a, b);
    assertTrue(0 == inDirectGraph.getNumEdges());
  }

  @Test
  public void testNumEdgeZeroElIndirect() throws GraphExceptions {
    assertTrue(0 == inDirectGraph.getNumEdges());
  }

  @Test
  public void testNumEdgeTwoElIndirect() throws GraphExceptions {
    inDirectGraph.addNode(a);
    inDirectGraph.addNode(b);
    inDirectGraph.addNode(c);
    inDirectGraph.addEdge(a, 5, b);
    inDirectGraph.addEdge(a, 5, c);
    assertTrue(2 == inDirectGraph.getNumEdges());
  }

  @Test
  public void testNumEdgeSameEdgeIndirect() {
    try {
      inDirectGraph.addNode(a);
      inDirectGraph.addNode(b);
      inDirectGraph.addEdge(a, 5, b);
      inDirectGraph.addEdge(new Node("emanuele", 11), 4, new Node("daniel", 21));
    } catch (GraphExceptions a) {

    }
    assertTrue(1 == inDirectGraph.getNumEdges());
  }

  @Test
  public void testGetLabel() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addEdge(a, 5, b);
    assertTrue(5 == directGraph.getLabel(new Node("emanuele", 11), new Node("daniel", 21)));
  }

  @Test
  public void testGetAdjNodesZeroAdj() throws GraphExceptions {
    directGraph.addNode(a);
    ArrayList<Node> adj = directGraph.getAdjNodes(a);
    assertTrue(adj.isEmpty());
  }

  @Test
  public void testGetAdjNodesThreeAdj() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addNode(c);
    directGraph.addEdge(a, 5, b);
    directGraph.addEdge(a, 5, c);
    ArrayList<Node> adj = directGraph.getAdjNodes(a);
    assertTrue(adj.size() == 2 && ((adj.get(0).equals(b) && adj.get(1).equals(c)) || (adj.get(0).equals(c) && adj.get(1).equals(b))));
  }

  @Test
  public void testGetNodesZeroNodes() throws GraphExceptions {
    ArrayList<Node> nodes = directGraph.getNodes();
    assertTrue(nodes.isEmpty());
  }

  @Test
  public void testGetNodesTwoNodes() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    ArrayList<Node> nodes = directGraph.getNodes();
    assertTrue(nodes.size() == 2 && ((nodes.get(0).equals(a) && nodes.get(1).equals(b)) || (nodes.get(0).equals(b) && nodes.get(1).equals(a))));
  }

  @Test
  public void testGetEdgesNoElDirect() throws GraphExceptions {
    ArrayList<Edge<Node, Integer>> edges = directGraph.getEdges();
    assertTrue(edges.isEmpty());
  }
  
  @Test
  public void testGetEdgesNoElInDirect() throws GraphExceptions {
    ArrayList<Edge<Node, Integer>> edges = inDirectGraph.getEdges();
    assertTrue(edges.isEmpty());
  }
  
  @Test
  public void testGetEdgesTwoEdgesDirect() throws GraphExceptions {
    directGraph.addNode(a);
    directGraph.addNode(b);
    directGraph.addNode(c);
    Edge<Node, Integer> first = new Edge<Node, Integer>(a, 5, b);
    Edge<Node, Integer> second = new Edge<Node, Integer>(a, 5, c);
    directGraph.addEdge(first);
    directGraph.addEdge(second);
    ArrayList<Edge<Node, Integer>> edges = directGraph.getEdges();
    assertTrue(edges.size() == 2 && ((edges.get(0).equals(first) && edges.get(1).equals(second))
            || (edges.get(0).equals(second) && edges.get(1).equals(first))));
  }

  @Test
  public void testGetEdgesTwoEdgesInDirect() throws GraphExceptions {
    inDirectGraph.addNode(a);
    inDirectGraph.addNode(b);
    inDirectGraph.addNode(c);
    Edge<Node, Integer> first = new Edge<Node, Integer>(a, 5, b);
    Edge<Node, Integer> second = new Edge<Node, Integer>(a, 5, c);
    Edge<Node, Integer> inv_first = new Edge<Node, Integer>(b, 5, a);
    Edge<Node, Integer> inv_second = new Edge<Node, Integer>(c, 5, a);
    inDirectGraph.addEdge(first);
    inDirectGraph.addEdge(second);
    ArrayList<Edge<Node, Integer>> edges = inDirectGraph.getEdges();
    assertTrue(edges.size() == 2 && ((edges.get(0).equals(first) && edges.get(1).equals(second))
            || (edges.get(0).equals(second) && edges.get(1).equals(first))
            || (edges.get(0).equals(inv_first) && edges.get(1).equals(inv_second))
            || (edges.get(0).equals(inv_second) && edges.get(1).equals(inv_first))));
  }

}



class Node {

  private String nome;
  private int eta;

  public Node(String nome, int eta) {
    this.nome = nome;
    this.eta = eta;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getEta() {
    return eta;
  }

  public void setEta(int eta) {
    this.eta = eta;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    if (this == o) {
      return true;
    }
    Node other = (Node) o;
    return (this.nome.toLowerCase().equals(other.getNome().toLowerCase())
            && this.eta == other.getEta());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.nome.toLowerCase(), this.eta);
  }
}
