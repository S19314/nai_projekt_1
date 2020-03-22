/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.IElementProbki;
import projekt_1_knn.Element;
/**
 *
 * @author admin
 */
public class MyFileReader {
    public List<IElementProbki> copyDataFromFileIntoListIElementProbki(String filePath){
        
        List<IElementProbki> elementProbkis = null;
        Scanner scannerFile;
        try{
            scannerFile = new Scanner(new File(filePath));
            
             elementProbkis =  new ArrayList<>();
            List<Double> numberyczneAtrybutyList = new ArrayList<>();  
            while(scannerFile.hasNextLine()){
                Scanner scannerLine = new Scanner(scannerFile.nextLine());
                while(scannerLine.hasNextDouble())
                    numberyczneAtrybutyList.add(scannerLine.nextDouble());
                
                
                double[] numberyczneAtrybutyArray = doubleListToArray(numberyczneAtrybutyList);
                numberyczneAtrybutyList.clear();
//                String decezyjnyAtrybt = scanner.next();
//                if(decezyjnyAtrybt != null && decezyjnyAtrybt != "")
                if(scannerLine.hasNext() && numberyczneAtrybutyArray.length != 0)
                elementProbkis.add(
                        new Element(
                                scannerLine.next(),
                                    //decezyjnyAtrybt,
                                    numberyczneAtrybutyArray
                        )
                );
                
            }
        
            scannerFile.close();
            
        }catch(IOException ioe){
            System.err.println("ioe " + ioe.getMessage());
        }
        
        return  elementProbkis;
    }
    
    
    private double[] doubleListToArray(List<Double> list){
        double[] result = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    
    
}
