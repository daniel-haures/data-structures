
package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The class create a graph with nodes of type N and allow to create Edge
 * labeled with type E. 
 * For a correct working algorithm redefine equals() and hashcode() of the node type.
 * @author daniel
 * @param <N>: The type of the node of the graph
 * @param <E>: The Type of the label of a edge
 */
public class Graph<N, E> {

  private Map<N, Map<N, E>> nodes;
  private boolean makeDirect;
  private int num_nodes;
  
  /**
   * It creates a empty graph. 
   * @param makeDirect: if the true it will be created a direct graph, otherwise 
   * a non direct graph.
   */
  public Graph(boolean makeDirect) {
    this.makeDirect = makeDirect;
    this.nodes = new HashMap<N, Map<N, E>>();
    this.num_nodes = 0;
  }

  /**
   * @return true if the graph is direct
   */
  public boolean isDirect() {
    return makeDirect;
  }

  /**
   * Add a node to the graph.
   * @param node: the node to be added
   * @throws GraphExceptions if node is null or the node is already contained in
   * the graph.
   * The function temporal complexity is O(1)
   */
  public void addNode(N node) throws GraphExceptions {

    if (node == null) {
      throw new GraphExceptions("Graph addNode:node cannot be null");
    }
    if (nodes.containsKey(node) == true) {
      throw new GraphExceptions("Graph addNode:node already exists");
    }

    Map<N, E> adjList = new HashMap<N, E>();
    nodes.put(node, adjList);
    num_nodes++;

  }

  /**
   * Remove a node and its relations with other node
   * @param delNode: the node to be deleted.
   * @throws GraphExceptions if the node is null or the node don't exists.
   * The function temporal complexity is O(n).
   */
  public void removeNode(N delNode) throws GraphExceptions {
    if (delNode == null) {
      throw new GraphExceptions("Graph removeNode: node to remove cannot be null");
    }
    if (!nodes.containsKey(delNode)) {
      throw new GraphExceptions("Graph removeNode: node to remove don't exist");
    }
    Set<N> set = nodes.keySet();
    Iterator<N> value = set.iterator();

    while (value.hasNext()) {
      if (nodes.containsKey(delNode)) {
        nodes.get(value.next()).remove(delNode);
      }
    }

    nodes.remove(delNode);
    num_nodes--;
  }

  /**
   * @param findNode: the node checked for it existence.
   * @return true if the node exist, false otherwise.
   * @throws GraphExceptions if node is null.
   * The function temporal complexity is O(1).
   */
  public boolean containsNode(N findNode) throws GraphExceptions {
    if (findNode == null) {
      throw new GraphExceptions("Graph containsNode: node cann ot be null");
    }
    return nodes.containsKey(findNode);
  }

  /**
   * It add a edge from first parameter node to third parameter node with the
   * second parameter as label.
   * @param fromNode:the start node of the edge
   * @param label: the label of the edge
   * @param toNode: the end node of the edge
   * @throws GraphExceptions if the parameters are null or the nodes don't exist.
   * If the edge already exist the label will be overwritten.
   * The function temporal complexity is O(1).
   */
  public void addEdge(N fromNode, E label, N toNode) throws GraphExceptions {

    if (fromNode == null) {
      throw new GraphExceptions("Graph addEdge: first node can not be null");
    }
    if (toNode == null) {
      throw new GraphExceptions("Graph addEdge: second node can not be null");
    }
    if (label == null) {
      throw new GraphExceptions("Graph addEdge: label can not be null");
    }

    if (nodes.containsKey(fromNode) == false) {
      throw new GraphExceptions("Graph addEdge: first node don't exist");
    }
    if (nodes.containsKey(toNode) == false) {
      throw new GraphExceptions("Graph addEdge: second node don't exist");
    }

    nodes.get(fromNode).put(toNode, label);
    if (!makeDirect) {
      nodes.get(toNode).put(fromNode, label);
    }

  }

  /**
   * It add a edge to the graph.
   * @param edge Object of class Edge
   * @throws GraphExceptions if the parameter is null.
   * If the label already exist it will be overwritten.
   * The function temporal complexity is O(1).
   */
  public void addEdge(Edge<N, E> edge) throws GraphExceptions {
    if (edge == null) {
      throw new GraphExceptions("Graph addEdge: first node cant be null");
    }
    addEdge(edge.getFromNode(), edge.getLabel(), edge.getToNode());

  }

  /**
   * Remove the edge starting from first parameter node and ending on second
   * parameter node.
   * @param fromNode the starting node.
   * @param toNode the ending node.
   * @throws GraphExceptions if parameters are null or the edge don't exist.
   * The function temporal complexity is O(1)
   */
  public void removeEdge(N fromNode, N toNode) throws GraphExceptions {

    if (fromNode == null) {
      throw new GraphExceptions("Graph removeEdge: first node cant be null");
    }
    if (toNode == null) {
      throw new GraphExceptions("Graph removeEdge: second node cant be null");
    }
    if (!containsEdge(fromNode, toNode)) {
      throw new GraphExceptions("Graph removeEdge: the edge don't exist");
    }
    nodes.get(fromNode).remove(toNode);
    if (!makeDirect) {
      nodes.get(toNode).remove(fromNode);
    }
  }

  /**
   * Remove the edge passed as parameter from the graph.
   * @param edge object of class Edge.
   * @throws GraphExceptions if parameters are null or the edge don't exist.
   * The function temporal complexity is O(1).
   */
  public void removeEdge(Edge<N, E> edge) throws GraphExceptions {
    if (edge == null) {
      throw new GraphExceptions("Graph removeEdge: edge cannot be null");
    }
    removeEdge(edge.getFromNode(), edge.getToNode());
  }

  /**
   * @param fromNode
   * @param toNode
   * @return true if the edge from first parameter node to second parameter node exist, 
   * false otherwise.
   * @throws GraphExceptions if parameters are null The function temporal
   * complexity is O(1)
   */
  public boolean containsEdge(N fromNode, N toNode) throws GraphExceptions {
    if (fromNode == null) {
      throw new GraphExceptions("Graph containsEdge: first node cannot be null");
    }
    if (toNode == null) {
      throw new GraphExceptions("Graph containsEdge: second node cannot be null");
    }
    if (nodes.containsKey(fromNode) == false) {
      return false;
    }
    return nodes.get(fromNode).containsKey(toNode);
  }

  /**
   * @param edge A object of class Edge
   * @return true if the edge exist, false otherwise
   * @throws GraphExceptions The function temporal complexity is O(1)
   */
  public boolean containsEdge(Edge<N, E> edge) throws GraphExceptions {
    if (edge == null) {
      throw new GraphExceptions("Graph containsEdge: edge cannot be null");
    }
    return containsEdge(edge.getFromNode(), edge.getToNode());
  }

  /**
   * @return the number of nodes The function temporal complexity is O(1)
   */
  public int getNumNodes() {
    return num_nodes;
  }

  /**
   * @return the number of edges.
   * The function temporal complexity is O(n),
   * HashMap size function is O(1) as described in the source.
   */
  public int getNumEdges() {

    int sum = 0;
    Set<N> set = this.nodes.keySet();
    Iterator<N> value = set.iterator();

    while (value.hasNext()) {
      sum = sum + this.nodes.get(value.next()).size();
    }

    if (!makeDirect) {
      sum = sum / 2;
    }

    return sum;

  }

  /**
   * Return the label of a Edge
   * @param fromNode the starting node of the edge containing the label
   * @param toNode the ending node of the edge containing the label
   * @return the label of the edge
   * @throws GraphExceptions if edge don't exist or the parameters are null.
   * The function temporal complexity is O(1).
   */
  public E getLabel(N fromNode, N toNode) throws GraphExceptions {
    if (fromNode == null) {
      throw new GraphExceptions("Graph getLabel: first node cannot be null");
    }
    if (toNode == null) {
      throw new GraphExceptions("Graph getLabel: second node cannot be null");
    }
    if (!containsEdge(fromNode, toNode)) {
      throw new GraphExceptions("Graph getLabel: Edge don't exist");
    }

    return nodes.get(fromNode).get(toNode);

  }

  /**
   * Return a ArrayList of the adjacent nodes of the node passed as parameter
   * @param node the node from which we want know the adjacent nodes
   * @return ArrayList of the adjacent nodes of the node passed as parameter.
   * @throws GraphExceptions if node is null or if the node don't exist.
   * The function temporal complexity is O(1) assuming that the 
   * number of edge per node is constant.
   */
  public ArrayList<N> getAdjNodes(N node) throws GraphExceptions {
    if (node == null) {
      throw new GraphExceptions("Graph getAdjNodes: node cannot be null");
    }
    if (!nodes.containsKey(node)) {
      throw new GraphExceptions("Graph getAdjNodes: node to remove don't exist");
    }
    Set<N> set = nodes.get(node).keySet();
    Iterator<N> value = set.iterator();
    ArrayList<N> adj = new ArrayList<N>();
    while (value.hasNext()) {
      adj.add(value.next());
    }
    return adj;

  }

  /**
   * Return all the node of the graph.
   * @return ArrayList of Nodes of type N.
   * The temporal complexity of the function is O(n).
   */
  public ArrayList<N> getNodes() {
    Set<N> set = nodes.keySet();
    Iterator<N> value = set.iterator();
    ArrayList<N> nodesArray = new ArrayList<N>();
    while (value.hasNext()) {
      nodesArray.add(value.next());
    }

    return nodesArray;

  }

  /**
   * Return all the edges of the graph.
   * @return ArrayList of Edges objects of types N and E.
   * The temporal complexity of the function is O(n) assuming that the 
   * number of edge per node is constant. 
   */
  public ArrayList<Edge<N, E>> getEdges() throws GraphExceptions {

    ArrayList<Edge<N, E>> edgesArray = new ArrayList<>();
    ArrayList<N> nodesArray = getNodes();
    ArrayList<N> adjNodes;
    HashSet<N> isPassed = new HashSet<>();

    for (int i = 0; i < nodesArray.size(); i++) {
      Set<N> adjNodesSet = nodes.get(nodesArray.get(i)).keySet();
      Iterator<N> value = adjNodesSet.iterator();
      while (value.hasNext()) {
        N actualnode = value.next();
        if (!makeDirect) {
          if (isPassed.contains(actualnode) == false) {
            Edge<N, E> edge = new Edge<N, E>(nodesArray.get(i), getLabel(nodesArray.get(i), actualnode), actualnode);
            edgesArray.add(edge);
          }
          isPassed.add(nodesArray.get(i));
        } else {
          Edge<N, E> edge = new Edge<N, E>(nodesArray.get(i), getLabel(nodesArray.get(i), actualnode), actualnode);
          edgesArray.add(edge);
        }
      }
    }

    return edgesArray;

  }

}
