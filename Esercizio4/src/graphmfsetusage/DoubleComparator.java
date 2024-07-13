/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmfsetusage;

import java.util.Comparator;

/**
 *
 * @author daniel
 */
public class DoubleComparator implements Comparator<Double> {

  @Override
  public int compare(Double d1, Double d2) {
    int result = (d1.compareTo(d2));
    return result;
  }
}
