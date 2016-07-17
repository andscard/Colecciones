/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Analizador {
    Archivo archivo;
    Map<String, Palabra> mapaPalabras;

    public Analizador(String file_name) {
        this.archivo =new Archivo(file_name);
        this.mapaPalabras = crearMapa(this.archivo);
    }


    public Map<String, Palabra> crearMapa(Archivo archivo) {
        try {
            Scanner reviewScanner = new Scanner(archivo.getReviewFile());
            Map<String, Palabra> mapaPalabras = new HashMap();
            int frecuencia;
            Palabra pal;
            
            while (reviewScanner.hasNextLine()) {
                
                String reviewText = reviewScanner.nextLine();
                String[] oracion = reviewText.split(" ");
                
                for (int i = 1; i < oracion.length; i++) {
                    
                    if (mapaPalabras.containsKey(oracion[i])) {
                        Palabra pal2 = mapaPalabras.get(oracion[i]);
                        frecuencia = (pal2.getFrecuencia()) + 1;
                        pal2.setFrecuencia(frecuencia);
                        pal2.setPromedio(Integer.parseInt(oracion[0]));
                        mapaPalabras.replace(oracion[i], pal2);
                    } else {
                        pal = new Palabra(oracion[i]);
                        pal.setPromedio(Integer.parseInt(oracion[0]));
                        mapaPalabras.put(oracion[i], pal);
                    }
                }
            }
            return mapaPalabras;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Analizador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
      public void buscaPalabraMapa(String word) {
        if (this.mapaPalabras.containsKey(word)) {
            Palabra pal = this.mapaPalabras.get(word);
            pal.estadoPalabra();
        } else {
            System.out.println("\nLa palabra no existe en el archivo");
        }
    } 
      
    public Set<String> getkeyset (){
        return this.mapaPalabras.keySet();
    }
    
     public float puntajePromedioArchivo() {
        Set<String> keyset = this.getkeyset();
        float pto_promedio = 0;
        Palabra palabra;
        for (String word : keyset) {
            palabra = this.mapaPalabras.get(word);
            pto_promedio = pto_promedio + palabra.calculoPromedio();
        }
        return pto_promedio / keyset.size();
    }
    
     
    public ArrayList<Palabra> palabrasPorPromedios() {
        ArrayList<Palabra> lista = new ArrayList<Palabra>();
        Set<String> keyset = mapaPalabras.keySet();
        Palabra palabra;
        for (String word : keyset) {
            palabra = mapaPalabras.get(word);
            lista.add(palabra);
        }
        Collections.sort(lista);

        return lista;
    }
    
    
    public ArrayList<Palabra> palabrasPositivas() {
        int size=this.palabrasPorPromedios().size();
        Palabra pal;
        ArrayList<Palabra> palabrasPositivas = new ArrayList<Palabra>();
        for(int i=0; i<size; i ++){
            pal=this.palabrasPorPromedios().get(i);       
            if(pal.calculoPromedio()>2.1){
            palabrasPositivas.add(pal);}
        }
        return palabrasPositivas;
     }
    
    public ArrayList<Palabra> palabrasNegativas() {
        int size=this.palabrasPorPromedios().size();
        Palabra pal;
        ArrayList<Palabra> palabrasNegativas = new ArrayList<Palabra>();
        for(int i=0; i<size; i ++){
            pal=this.palabrasPorPromedios().get(i);       
            if(pal.calculoPromedio()<1.99){
            palabrasNegativas.add(pal);}
        }
        return palabrasNegativas;
    }
    
    public void palabraPositivaNegativa() {
        ArrayList<Palabra> lista_promedios=this.palabrasPorPromedios();
        int size = lista_promedios.size() - 1;
        Palabra positiva = lista_promedios.get(size);
        Palabra negativa = lista_promedios.get(0);

        System.out.println("-La palabra más positiva, con un puntaje de " + positiva.calculoPromedio() + " es " + positiva.getPalabra());
        System.out.println("-La palabra más negativa, con un puntaje de " + negativa.calculoPromedio() + " es " + negativa.getPalabra());
    }
    
    
     public void sentimientoGeneralArchivo(float promedio) {
        if (promedio > 2.01) {
            System.out.println("-El sentimiento general del archivo " + this.archivo.getReviewFile() + " es positivo");
        }
        if (promedio < 1.99) {
            System.out.println("-El sentimiento general del archivo " + this.archivo.getReviewFile() + " es negativo");
        }
     }
        
     public void analisisArchivo() {
        Map <String, Palabra> mapaPalabras= this.mapaPalabras;
        float pt_promedio = this.puntajePromedioArchivo();
        System.out.println("-El puntaje promedio del archivo " + this.archivo.getReviewFile() + " es " + pt_promedio);
        this.sentimientoGeneralArchivo(pt_promedio);
    }
     
    
    public void escribirArchivoPositivo(){
    ArrayList<Palabra> palabrasPositivas = this.palabrasPositivas();
    int p= palabrasPositivas.size();
    String p_positiva;
    try {
            BufferedWriter bw2=new BufferedWriter(new FileWriter(this.archivo.getF_positivo()));
            for (int i=0; i< p; i++ ){
                p_positiva=palabrasPositivas.get(i).getPalabra();
                bw2.write(p_positiva);
                bw2.newLine();
                bw2.flush();
            }
        } catch (IOException ex) {
            System.out.println("No se puedo escribir en el archivo positivo.txt");
        }
    }
    
    public void escribirArchivoNegativo(){
    ArrayList<Palabra> palabrasNegativas = this.palabrasNegativas();
    int n= palabrasNegativas.size();
    String p_negativa; 
     try {
            BufferedWriter bw1=new BufferedWriter(new FileWriter(this.archivo.getF_negativo()));
            for (int i=0; i< n; i++ ){
                p_negativa=palabrasNegativas.get(i).getPalabra();
                bw1.write(p_negativa);
                bw1.newLine();
                bw1.flush();
            }
            
        } catch (IOException ex) {
         System.out.println("No se puedo escribir en el archivo negativo.txt");
        }
    }
    
    public void escribirArchivosSecundarios(){   
        
    
    }
}
