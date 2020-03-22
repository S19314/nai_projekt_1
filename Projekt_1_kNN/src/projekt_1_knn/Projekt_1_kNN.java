package projekt_1_knn;

import fileReader.MyFileReader;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import models.IElementProbki;

/**
 *
 * @author admin
 */
public class Projekt_1_kNN {
    
    private  List<IElementProbki> trainingElements;
    private List<IElementProbki> testElements;
    private Projekt_1_kNN main;

    public Projekt_1_kNN(){
         
         MyFileReader myFileReader = new MyFileReader();
           testElements = myFileReader
                   .copyDataFromFileIntoListIElementProbki("data/iris_test.txt");
            trainingElements =  myFileReader
                   .copyDataFromFileIntoListIElementProbki("data/iris_training.txt");
           start();
    }
    
    
    public static void main(String[] args) {
        
        Projekt_1_kNN main_poN = new Projekt_1_kNN();
        
         
        
//        main.tryTestFile(trainingElements, testsElements);
        
//       IElementProbki defineEl = main.defineClassElement(new Element("", new double[]{5.4,3.7, 1.5, 0.2}), trainingElements, 3);
//        System.out.println(defineEl);
    
//    defineEl = main.defineClassElement(new Element("", new double[]{5.9,3.0,5.1,1.8}), trainingElements, 1);
///        System.out.println(defineEl);

    }
    
    public void tryTestFile(
                              int k
//                            List<IElementProbki> trainingElements,
//                            List<IElementProbki> testElements 
    ){
        System.out.println("Test ");
        for(int i = 0; i < testElements.size(); i++){
            System.out.println(i + " " + testElements.get(i).toString());
        }
        
        List<IElementProbki> classedElementList = new ArrayList<>();
        for(int i = 0; i < testElements.size(); i++){
//            testElements.get(i).setAtrybutDecezyjny(""); 
            IElementProbki classedElement = defineClassElement(
                                            testElements.get(i),
                                            trainingElements,
                                            k
            );
            classedElementList.add(classedElement);
        }
        System.out.println("classed ");
        for(int i = 0; i < classedElementList.size(); i++){
            System.out.println(i + " " + classedElementList.get(i).toString());
        }
        
        
        
        
        showStatistic(classedElementList, testElements);
        
        for(int i = 0; i < classedElementList.size(); i++){
            classedElementList.get(i).setAtrybutDecezyjny("");
        }
        
//        showStatistic(classedElementList,testElements);
        
    }
    
    public  void showStatistic(
                                List<IElementProbki> elementsAfterClassing,
                                List<IElementProbki> elementsBeforeClassing                                
    ){
        int prawidlowoZaklasyfikowani = 0;
        for(int i = 0; i < elementsAfterClassing.size(); i++ ){
            IElementProbki elemAfterClassing = elementsAfterClassing.get(i),
                    elementBeforeClassing = elementsBeforeClassing.get(i);
//            System.out.println(elemAfterClassing + " aft");
//            System.out.println(elementBeforeClassing + " before");
            
//            String 
            
            if(elemAfterClassing.getAtrybutDecezyjny().equals(
                    elementBeforeClassing.getAtrybutDecezyjny())){
                prawidlowoZaklasyfikowani++;
            }
        }
        System.out.println("Size " + elementsAfterClassing.size());
            System.out.println(
                "Prawidlowo Zaklasyfikowani elementy " 
                + prawidlowoZaklasyfikowani + ","
                + (prawidlowoZaklasyfikowani*100.0/elementsAfterClassing.size()));
        
    
    }
    
    public IElementProbki defineClassElement(                    
                        IElementProbki unclassedElement,
                        List<IElementProbki> dataBaseOfElements,
                        int k
    ){
        Neighbour[] neighbours = findKNearestNeighbours(
                                                        unclassedElement, 
                                                        dataBaseOfElements, 
                                                        k
                                                        );
        if(neighbours.length == 0)
            return null;
        
        for(int i = 0; i < neighbours.length; i++){
            System.out.print( neighbours[i].getAtrybutDecezyjny() + 
                    " atrybutDecezyjny distance " + neighbours[i].getDistance() 
                    + " paramDouble [");
            double[] paramDouble = neighbours[i].getElementProbki().getAtrybutyNumeryczne();
            for(int j = 0; j < paramDouble.length; j++)
                System.out.print(paramDouble[j] + "; ");
            System.out.print("]\t");
        }
        System.out.println("");
        List<List<Neighbour>> classes = new ArrayList<>();
        
        classes.add(new ArrayList<Neighbour>());
        classes.get(0).add(neighbours[0]);
        for(int i = 1; i < neighbours.length; i++){
            boolean isAdded = false;
            for(int j = 0; j < classes.size(); j++)
            {   
//                System.out.println("classes " + classes.get(j).get(0).getAtrybutDecezyjny());
//                System.out.println("Nei " + neighbours[i].getAtrybutDecezyjny());
                if(classes.get(j).get(0).getAtrybutDecezyjny().equals(
                   neighbours[i].getAtrybutDecezyjny())
                )
                {
                    List<Neighbour> classList = classes.get(j);
                    classList.add(neighbours[i]);
                    classes.set(j, classList);
//                    System.out.println("Neigh" + neighbours[i].getAtrybutDecezyjny()
//                    + ", into " + classes.toString());
                    isAdded = true;
                    break;
                }
            }
            
//            System.out.println("IsAdede " + isAdded);
            if(!isAdded){
                classes.add(new ArrayList<Neighbour>());
                classes.get(classes.size() - 1 ).add(neighbours[i]);
            }
        }
        
        List<Neighbour> mainClass = classes.get(0);
//        System.out.println("class number 0 " + classes.get(0).size());

        for(int i = 1;  i < classes.size(); i++){
//            System.out.println("class number "+ i + " "+ classes.get(i).size());
            if(mainClass.size() < classes.get(i).size()){
                mainClass = classes.get(i);
            }
        }
        
        
//        System.out.println("Unclased " + unclassedElement.getAtrybutDecezyjny() );
        //unclassedElement.setAtrybutDecezyjny(mainClass.get(0).getAtrybutDecezyjny());
        
        System.out.print("clased " + mainClass.get(0).getAtrybutDecezyjny() + " param " );
        System.out.println(Arrays.toString( unclassedElement.getAtrybutyNumeryczne()));
        Element element = new Element(mainClass.get(0).getAtrybutDecezyjny(), unclassedElement.getAtrybutyNumeryczne());
//        System.out.println("New el " + element);
        return  element;
    }
    
    public Neighbour[] findKNearestNeighbours(
                        IElementProbki unclassedElement,
                        List<IElementProbki> dataBaseOfElements,
                        int k
    ){
        if(dataBaseOfElements.size() == 0)
            return null;
        
        Neighbour[] neighbourds = new Neighbour[k];
        for(int i = 0; i < neighbourds.length; i++){
            neighbourds[i] = new Neighbour();
        }
        // neighbourds[/0] =  dataBaseOfElements.get(0);
        for(int i = 0; i < dataBaseOfElements.size(); i++){
            double distanceToNeighbour = countDistanceToNeighbor(
                                            unclassedElement, 
                                            dataBaseOfElements.get(i)
            );

//            System.out.println("Neighbours. To new N " + distanceToNeighbour);
//            for(int j = 0; j < neighbourds.length; j++)
//                System.out.print(" " + neighbourds[j].getDistance());
//            System.out.println("");
            Arrays.sort(neighbourds);
//            System.out.println("After sort");
//            for(int j = 0; j < neighbourds.length; j++)
//                System.out.print(" " + neighbourds[j].getDistance());
//            System.out.println("");
            
            
            int indexLastElement = neighbourds.length - 1;
            if(neighbourds[indexLastElement].getDistance() > distanceToNeighbour){
               neighbourds[indexLastElement].setDistance(distanceToNeighbour);
               neighbourds[indexLastElement].setElementProbki(dataBaseOfElements.get(i));
//                System.out.println("New " + neighbourds[indexLastElement].getDistance());
            }
        }
        
        return neighbourds;
    }
    
    
    private double countDistanceToNeighbor(IElementProbki unclassedElement, 
                                           IElementProbki elemFromDataBase){
        // Nie będzie elementu z tabkicą o rozmiarze zero w DataBase, bo go
        // poprostu nir dodają do tej DB(DataBase);
        
        double distance = 0;
        double[] numeryczneAtrybutyElemFromDataBase = elemFromDataBase.getAtrybutyNumeryczne(),
                 numeryczneAtrybutyUnclassedElem = unclassedElement.getAtrybutyNumeryczne();
        if(numeryczneAtrybutyUnclassedElem.length == 0)
            return -1;
        for(int i = 0; i < numeryczneAtrybutyElemFromDataBase.length; i++){
            distance =  distance +
                        Math.pow( 
                                 (numeryczneAtrybutyElemFromDataBase[i] 
                                  - 
                                  numeryczneAtrybutyUnclassedElem[i]),
                        2);
        }
        
        return Math.sqrt(distance);
    }
 
    public void start(){
        String command = "";
        Scanner commandLine = new Scanner(System.in);
        System.out.println("You have two options: "
                + "\n Use k-NN"
                + "\n Insert new parametr");
        do{
            command = commandLine.nextLine();
            switch(command){
                case "Use k-NN":
                    System.out.println("Write parametr k: ");
                    int parametr = commandLine.nextInt();
                    tryTestFile(parametr);
                    break;
                case "Insert new parametr":
                    System.out.println("Insert numeric parametrs with whitespace: ");
                    String doublePar = commandLine.nextLine();
                    StringTokenizer strTokenizer = new StringTokenizer(doublePar, ";");
                    double[] numericsParam = new double[strTokenizer.countTokens()];
                    System.out.println("" + doublePar);
                    System.out.println("counts " + strTokenizer.countTokens());
                    int limit = strTokenizer.countTokens();
                    for(int i = 0; i < limit; i++){
                        numericsParam[i] = Double.parseDouble(strTokenizer.nextToken().trim());
                        System.out.println(i + " " + numericsParam[i]);
                    }
                    System.out.println("Write parametr k ");
                    int parametrK = commandLine.nextInt();
                    IElementProbki defineEl = this.defineClassElement(
                        new Element ("", numericsParam), trainingElements, parametrK);
                System.out.println(defineEl);
                    break;
            }
            }while(command != "EXIT");
        
        // Считывание и выписыание команд
        
        
    }
    
}
