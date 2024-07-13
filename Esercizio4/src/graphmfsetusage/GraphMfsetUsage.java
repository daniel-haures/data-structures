/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmfsetusage;

import graph.Edge;
import graph.Graph;
import graph.GraphExceptions;
import kruskal.Kruskal;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 * Implementation of Graph,UnionFindSet and Kruskal Libraries.
 * Creation of a graph and determination of his minimum spanning forest
 * @author daniel
 */
public class GraphMfsetUsage {

  private static final Charset ENCODING = StandardCharsets.UTF_8;

  //Loading of the graph from csv file recods.
  private static void loadGraph(String filepath, Graph<String, Double> graph) throws IOException, GraphExceptions {
    System.out.println("\nLoading data from file...\n");
    
    Path inputFilePath = Paths.get(filepath);
    try ( BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
      String line = null;
      while ((line = fileInputReader.readLine()) != null) {
        String[] lineElements = line.split(",");
        if (!graph.containsNode(lineElements[0])) {
          graph.addNode(lineElements[0]);
        }
        if (!graph.containsNode(lineElements[1])) {
          graph.addNode(lineElements[1]);
        }
        graph.addEdge(lineElements[0], Double.parseDouble(lineElements[2]), lineElements[1]);
      }
    }
    System.out.println("\nData loaded\n");
  }
  
  //Print the results of the kruskal algorithm
  private static void printResults(Graph<String, Double> resGraph,Graph<String, Double> oldGraph,long loadTime,long kruskalTime) throws IOException, GraphExceptions {
    ArrayList<Edge<String, Double>> resEdges = resGraph.getEdges();
    double sum = 0;
    for (int i = 0; i < resEdges.size(); i++) {
      sum = sum + resEdges.get(i).getLabel();
    }

    System.out.println("Totale nodi:" + resGraph.getNumNodes());
    System.out.println("Totale archi prima dell'algoritmo di Kruskal: " + oldGraph.getNumEdges());
    System.out.println("Totale archi dopo l'algoritmo di Kruskal: " + resGraph.getNumEdges());
    System.out.printf("Somma in km: %.4f\n", sum / 1000);
    System.out.println("Tempo di caricamento grafo: " + loadTime + "ms");
    System.out.println("Tempo di esecuzione Kruskal: " + kruskalTime + "ms");
  }

  public static void main(String args[]) throws Exception, GraphExceptions {

    if (args.length < 1) {
      throw new Exception("Usage: OrderedArrayUsageJava <file_name>");
    }

    long start, end;

    start = System.currentTimeMillis();
    Graph<String, Double> graph = new Graph<String, Double>(false);
    loadGraph(args[0], graph);
    end = System.currentTimeMillis();
    long loadTime = end - start;
    
    start = System.currentTimeMillis();
    DoubleComparator comparator = new DoubleComparator();
    Kruskal<String, Double> krs = new Kruskal<>(comparator);
    Graph<String, Double> resGraph = krs.applyKruskal(graph);
    end = System.currentTimeMillis();
    long kruskalTime = end - start;

    printResults(resGraph,graph,loadTime,kruskalTime);
    
    SwingUtilities.invokeLater(new GraphicsRunnable(resGraph, krs.getRoots()));

  }

}
