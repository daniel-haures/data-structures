/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmfsetusage;

import graph.Graph;
import graph.GraphExceptions;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runnable for running the Swing interface only after the application of Kruskal algorithm
 * @author daniel
 */
public class GraphicsRunnable implements Runnable {

  Graph<String, Double> graph;
  ArrayList<String> radix;

  public GraphicsRunnable(Graph<String, Double> graph, ArrayList<String> radix) {
    super();
    this.graph = graph;
    this.radix = radix;
  }

  @Override
  public void run() {
    try {
      new DrawTrees(graph, radix);
    } catch (GraphExceptions ex) {
      Logger.getLogger(GraphicsRunnable.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
