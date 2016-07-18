/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class SentimentAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        Scanner s= new Scanner(System.in);
        System.out.println("        SENTIMENT ANALYSIS\n");
        
         while(true){
       
        System.out.println("\n\n¿Qué desea hacer? "
                +"\n1. Ontener el puntaje de una palabra"
                +"\n2. Obtener el puntaje promedio de todas las palabras"
                +"\n3. Econtras las palabras con el más alto/bajo puntaje"
                +"\n4. Ordenar las palabras del acrchivo en ficheros positivas.txt y negativas.txt "
                +"\n5. Salir del programa");
        System.out.println("\nEscriba un número de 1 al 5: ");
        
        
        String opcion=s.next();
        
        
        SentimentAnalysis.submenu1(opcion);
        
        
        }
        
        
    }
    public static void submenu1(String opcion){
  
        try {
            Scanner keyboard = new Scanner(System.in);
            switch(opcion){
                case "1":
                    Analizador analizador1= new Analizador("movieReviews.txt");
                    System.out.println("\n>> Escriba una palabra: ");
                    String word=keyboard.nextLine();
                    analizador1.resultadosBusquedaPalabra(word);
                    break;
                case "2":
                    System.out.println(">> Ingrese el nombre de un archivo: ");
                    String file_name2= keyboard.nextLine();
                    Analizador analizador2= new Analizador(file_name2);
                    analizador2.analisisArchivo();
                    break;
                case "3":
                    System.out.println(">> Ingrese el nombre de un archivo: ");
                    String file_name3= keyboard.nextLine();
                    Analizador analizador3= new Analizador(file_name3);
                    analizador3.palabraPositivaNegativa();
                    break;
                case "4":
                    System.out.println(">> Ingrese el nombre de un archivo: ");
                    String file_name4= keyboard.nextLine();
                    Analizador analizador4= new Analizador(file_name4);
                    analizador4.escribirArchivoPositivo();
                    analizador4.escribirArchivoNegativo();
                    break;
                case "5":
                    System.out.println("Fin del Programa");
                    return ;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\nEl archivo no existe, el programa no se puede ejecutar");
        }
      
    }
    
}
