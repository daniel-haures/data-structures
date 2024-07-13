
package kruskal;

import graph.Edge;
import graph.Graph;
import graph.GraphExceptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import unionfindset.UnionFindSet;
import unionfindset.UnionFindSetExceptions;

/**
 *
 * @author daniel
 */



class EdgeComparator<N, E> implements Comparator<Edge<N, E>> {

  Comparator<? super E> comparator = null;

  public EdgeComparator(Comparator<? super E> comparator) {
    this.comparator = comparator;
  }

  @Override
  public int compare(Edge<N, E> e1, Edge<N, E> e2) {
    return this.comparator.compare(e1.getLabel(), e2.getLabel());
  }

}

/**
 * The class allow the use of the Kruskal algorithm to a object of Class Graph
 *
 * @author daniel
 * @param <N> is the type of the node of the graph that Kraskul have to process
 * @param <E> is the type of the edge label that Kruskal algorithm have to
 * process
 */
public class Kruskal<N, E> {

  Comparator<? super E> labelComparator = null;
  ArrayList<N> roots;

  /**
   * It accepts as input a comparator implementing the precedence relation
   * between the labels.
   *
   * @param labelComparator: a comparator implementing the precedence relation
   * between edge labels.
   * @throws if the label comparator is null
   */
  public Kruskal(Comparator<? super E> labelComparator) throws GraphExceptions {
    if (labelComparator==null) {
      throw new GraphExceptions("Kruskall constructor: labelComparator cannot be null");
    }
    this.labelComparator = labelComparator;
    roots = new ArrayList<N>();
  }

  /**
   * Apply the Kruskal algorithm to a non direct graph, returning a forest of
   * three represented by a graph
   *
   * @param graph a non direct graph
   * @return a graph representing the minimum spanning forest of the graph
   * passed as parameter
   * @throws if the parameter is null or the graph isn't not direct.
   */
  public Graph<N, E> applyKruskal(Graph<N, E> graph) throws GraphExceptions, UnionFindSetExceptions, KruskalExceptions {

    if (graph == null) {
      throw new GraphExceptions("Kruskall applyKruskal:graph cannot be null");
    }
    if (graph.isDirect()) {
      throw new GraphExceptions("Kruskall applyKruskal:graph cannot be direct, it should be undirect");
    }

    ArrayList<N> nodes = graph.getNodes();
    UnionFindSet<N> mfset = new UnionFindSet<N>();
    for (int i = 0; i < graph.getNumNodes(); i++) {
      mfset.makeSet(nodes.get(i));
    }

    ArrayList<Edge<N, E>> edges = graph.getEdges();

    EdgeComparator<N, E> edgeComparator = new EdgeComparator<N, E>(labelComparator);
    Collections.sort(edges, edgeComparator);

    Graph<N, E> res_graph = new Graph<N, E>(false);
    for (int i = 0; i < edges.size(); i++) {
      Edge<N, E> actualEdge = edges.get(i);
      if (mfset.findSet(actualEdge.getFromNode()) != mfset.findSet(actualEdge.getToNode())) {
        mfset.union(actualEdge.getFromNode(), actualEdge.getToNode());
        if (res_graph.containsNode(actualEdge.getFromNode()) == false) {
          res_graph.addNode(actualEdge.getFromNode());
        }
        if (res_graph.containsNode(actualEdge.getToNode()) == false) {
          res_graph.addNode(actualEdge.getToNode());
        }
        res_graph.addEdge(actualEdge);
      }
    }

    setRoots(mfset,nodes);

    return res_graph;

  }

  /**
   * Store the roots of a mfset structure to a ArrayList of nodes
   *
   * @param mfsetRoots the union find set structure
   */
  private void setRoots(UnionFindSet<N> mfsetRoots,ArrayList<N> nodes) throws UnionFindSetExceptions {
    ArrayList<N> rootsArray = new ArrayList<N>();
    Set<N> rootsSet = new HashSet<N>();
    for (int i = 0; i < nodes.size(); i++) {
      rootsSet.add(mfsetRoots.findSet(nodes.get(i)));
    }

    Iterator<N> value = rootsSet.iterator();
    while (value.hasNext()) {
      rootsArray.add(value.next());
    }

    this.roots = rootsArray;
  }

  /**
   * @return a ArrayList of possible root nodes of the minimum spanning forest
   * calculated previously by the Kruskal algorithm, or a empty array if no
   * minimum spanning forest was calculated.
   */
  public ArrayList<N> getRoots() {
    return roots;
  }

}
