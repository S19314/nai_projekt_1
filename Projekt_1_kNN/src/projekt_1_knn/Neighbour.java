/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_1_knn;

import models.IElementProbki;

/**
 *
 * @author admin
 */
public class Neighbour implements Comparable<Neighbour> {
    private IElementProbki elementProbki;
    private double distance = Double.MAX_VALUE;
    
    public Neighbour(){}

    public IElementProbki getElementProbki() {
        return elementProbki;
    }

    public void setElementProbki(IElementProbki elementProbki) {
        this.elementProbki = elementProbki;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    public String getAtrybutDecezyjny(){
        return elementProbki.getAtrybutDecezyjny();
    }
    public int compareTo(Neighbour neighbour){
        return (int)(this.distance - neighbour.getDistance());
    }
    
}
