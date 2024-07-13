
package unionfindset;

import java.util.HashMap;

/**
 * @author Daniel
 */
class Node {

  private Node parent;
  private int rank;

  public Node() {
    this.rank = 0;
  }

  Node getParent() {
    return parent;
  }

  void setParent(Node parent) {
    this.parent = parent;
  }

  int getRank() {
    return rank;
  }

  void setRank(int rank) {
    this.rank = rank;
  }

}

/**
 * The class implements union find set (mfset) data structure with
 * rank an path compression.
 * @author daniel
 * @param <T> the type of element managed in the set
 */
public class UnionFindSet<T> {

  private final HashMap<T, Node> nodes;
  private final HashMap<Node, T> elements;

  /**
   * Creates a empty union find set structure
   */
  public UnionFindSet(){
    this.nodes = new HashMap<>();
    this.elements = new HashMap<>();
  }

  /**
   * Create a set for the T element.
   * @param element: the element of type T for which a set is created.
   * @throws UnionFindSetExceptions if the element is null.
   */
  public void makeSet(T element) throws UnionFindSetExceptions {
    if (element == null) {
      throw new UnionFindSetExceptions("UnionFindSet makeSet: element can't be null");
    }
    Node el_node = new Node();
    el_node.setParent(el_node);
    nodes.put(element, el_node);
    elements.put(el_node, element);
  }

  /**
   * Join two different set to one single set.
   * @param elementA
   * @param elementB
   * @throws UnionFindSetExceptions if the parameters are null
   */
  public void union(T elementA, T elementB) throws UnionFindSetExceptions {
    if (elementA == null) {
      throw new UnionFindSetExceptions("UnionFindSet union: first element can't be null");
    }
    if (elementB == null) {
      throw new UnionFindSetExceptions("UnionFindSet union: second element can't be null");
    }
    if(!nodes.containsKey(elementA)){
      throw new UnionFindSetExceptions("UnionFindSet union: there is no set for second parameter element");
    }
    if(!nodes.containsKey(elementB)){
      throw new UnionFindSetExceptions("UnionFindSet union: there is no set for second parameter the element");
    }
    linkNode(onlyNodeFindSet(nodes.get(elementA)), onlyNodeFindSet(nodes.get(elementB)));
  }

  /**
   * Join two sets to a single set
   */
  private void linkNode(Node nodeElementA, Node nodeElementB) {
    if (nodeElementA.getRank() > nodeElementB.getRank()) {
      nodeElementB.setParent(nodeElementA);
    } else {
      nodeElementA.setParent(nodeElementB);
      if (nodeElementA.getRank() == nodeElementB.getRank()) {
        nodeElementB.setRank(nodeElementB.getRank() + 1);
      }
    }
  }

  /**
   * Found and return the root element of the set that contains the element
   * passed as parameter.
   * @param element: the element for which will be applied the search.
   * @return the root of the set that contains the element passed as parameter.
   * @throws UnionFindSetExceptions if the parameter is null or don't exist a set
   * for the element.
   */
  public T findSet(T element) throws UnionFindSetExceptions {
    if (element == null) {
      throw new UnionFindSetExceptions("UnionFindSet findSet: element can't be null");
    }
    if(!nodes.containsKey(element)){
      throw new UnionFindSetExceptions("UnionFindSet findSet: there is no set for the element");
    }
    return elements.get(onlyNodeFindSet(nodes.get(element)));
  }

  private Node onlyNodeFindSet(Node nodeElement) {
    if (nodeElement != nodeElement.getParent()) {
      nodeElement.setParent(onlyNodeFindSet(nodeElement.getParent()));
    }
    return nodeElement.getParent();
  }

}
