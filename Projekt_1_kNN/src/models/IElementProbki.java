package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin

 */
public interface IElementProbki {
    
    public void setAtrybutDecezyjny(String atrybut);
    public double[] getAtrybutyNumeryczne();
    
    public String getAtrybutDecezyjny();
    
    @Override
    public String toString();

}
