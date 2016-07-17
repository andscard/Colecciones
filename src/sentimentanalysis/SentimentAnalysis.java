/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.util.Scanner;

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
        Scanner keyboard = new Scanner(System.in);
        Scanner s= new Scanner(System.in);
        System.out.println(">> Ingrese el nombre de un archivo: ");
        String file_name= keyboard.nextLine();
        Analizador analizador= new Analizador("movieReviews.txt");
        
        System.out.println("¿Qué desea hacer? "
                +"\n1. Ontener el puntaje de una palabra"
                +"\n2. Obtener el puntaje promedio de todas las palabras"
                +"\n3. Econtras las palabras con el más alto/bajo puntaje"
                +"\n4. Ordenar las palabras del acrchivo en ficheros positivas.txt y negativas.txt "
                +"\n5. Salir del programa");
        System.out.println("Escriba un número de 1 al 5: ");
        
        
        String opcion=s.next();
        
        while(true){
           
           switch(opcion){
               case "1":
                  System.out.println("\n>> Escriba una palabra: ");
                  String word=keyboard.nextLine();
                  analizador.buscaPalabraMapa(word);
                   break;
               case "2":
                  analizador.analisisArchivo();
                   break;
               case "3":
                   analizador.palabraPositivaNegativa();
                   break;
               case "4":
                   analizador.escribirArchivosSecundarios();
                   break;
               case "5":
                   return ;
               default:
                   System.out.println("Opción no válida");
                   break;
           }
        
        }
        
        
    }
    
}
