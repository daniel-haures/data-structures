
package graph;

import java.util.Objects;

/**
 * A class representing a edge that can be managed from the graph object
 * as input parameter or return value.
 * @author daniel
 * @param <N> the type of the node represented in the graph
 * @param <E> the type of the label of the edge
 *
 */
public class Edge<N, E> {

  private N fromNode;
  private E label;
  private N toNode;

  /**
   * Create a edge that can be managed from the graph.
   * @param fromNode the starting node of the edge
   * @param label the label associated with the edge
   * @param toNode the ending node of the edge
   * If the graph isn't direct, the fromNode and toNode parameters represents
   * the left and the right nodes of the edge.
   */
  public Edge(N fromNode, E label, N toNode) {
    this.fromNode = fromNode;
    this.label = label;
    this.toNode = toNode;
  }

  public void setFromNode(N fromNode) {
    this.fromNode = fromNode;
  }

  public void setLabel(E label) {
    this.label = label;
  }

  public void setToNode(N toNode) {
    this.toNode = toNode;
  }

  public N getFromNode() {
    return fromNode;
  }

  public N getToNode() {
    return toNode;
  }

  public E getLabel() {
    return label;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (this == o) {
      return true;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    Edge<?, ?> other = (Edge<?, ?>) o;

    return (this.fromNode.equals(other.getFromNode()) && this.toNode.equals(other.getToNode())
            && this.label.equals(other.getLabel()));

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.fromNode, this.label, this.toNode);
  }

}
