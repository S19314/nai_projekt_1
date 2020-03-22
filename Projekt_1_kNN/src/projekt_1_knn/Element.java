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
public class Element implements IElementProbki {
    private double[] numerycznyAtrybuty;
    private String decezyjnyAtryb;

    public Element(String atrybutDecezyjny, double... atrybutyNumeryczne) {
       this.decezyjnyAtryb = atrybutDecezyjny;
       this.numerycznyAtrybuty = atrybutyNumeryczne;
    }

    public double[] getAtrybutyNumeryczne() {
        return numerycznyAtrybuty;
    }

    public void setAtrybutDecezyjny(String atrybut){
        decezyjnyAtryb = atrybut;
    }
    
    public String getAtrybutDecezyjny() {
        return decezyjnyAtryb;
    }

    @Override
    public String toString() {

        String liczby = " ";
        for(int i = 0; i < numerycznyAtrybuty.length; i++)
            liczby = liczby + " " + numerycznyAtrybuty[i];
        return "Element{" + "numerycznyAtrybuty=[" + liczby + " ], decezyjnyAtryb=" + decezyjnyAtryb + '}';
    }

    
}
