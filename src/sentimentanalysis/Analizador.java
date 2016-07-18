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
        try {
            this.archivo =new Archivo(file_name);
        } catch (FileNotFoundException ex) {
           System.out.println("\nEl archivo no existe");
        } catch(IOException e){
            System.out.println("\nEl nombre que ingresó es incorrecto");
        }
        this.mapaPalabras = crearMapa();
    }



    public Map<String, Palabra> crearMapa() {
        try {
            Scanner reviewScanner = new Scanner(this.archivo.getReviewFile());
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

      
    public boolean buscaPalabraMapa(String word) {
        if (this.mapaPalabras.containsKey(word)) {
            return true;
        } else {
            return false;
        }
    } 
    
    public void resultadosBusquedaPalabra(String word){
        if (this.buscaPalabraMapa(word)){
            Palabra pal = this.mapaPalabras.get(word);
            pal.estadoPalabra();
        }else{
            System.out.println("\n  *** La palabra no existe en el archivo ***\n");
        }
    }
    
      
    public ArrayList<Palabra> palabrasUserFile() throws FileNotFoundException{
        ArrayList<Palabra> palabrasUserFile= new ArrayList<Palabra>();
        
        if (this.archivo.getUserFile().equals(this.archivo.getReviewFile())){
            palabrasUserFile=this.palabrasPorPromedios();
            return palabrasUserFile;
        }
        
            Scanner userScanner = new Scanner(this.archivo.getUserFile());
            while (userScanner.hasNextLine()) {
                String user_word = userScanner.nextLine();
                if (this.buscaPalabraMapa(user_word)){
                    Palabra pal = this.mapaPalabras.get(user_word);
                    palabrasUserFile.add(pal);
                }
         }
                
        
        return palabrasUserFile;
    }
    
    
    public Set<String> getkeyset (){
        return this.mapaPalabras.keySet();
    }
    
     public float puntajePromedioArchivo() throws FileNotFoundException {
        ArrayList<Palabra> palabrasUserFile= this.palabrasUserFile();
        float pto_promedio = 0;
        for (Palabra palabra : palabrasUserFile) {
            pto_promedio = pto_promedio + palabra.calculoPromedio();
        }
        return pto_promedio / palabrasUserFile.size();
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
    
    
    public ArrayList<Palabra> palabrasPositivas() throws FileNotFoundException {
        ArrayList<Palabra> palabras =this.palabrasUserFile();
        int size=palabras.size();
        Palabra pal;
        ArrayList<Palabra> palabrasPositivas = new ArrayList<Palabra>();
        for(int i=0; i<size; i ++){
            pal=palabras.get(i);       
            if(pal.calculoPromedio()>2.01){
            palabrasPositivas.add(pal);}
        }
        return palabrasPositivas;
     }
    
    public ArrayList<Palabra> palabrasNegativas() throws FileNotFoundException {
        ArrayList<Palabra> palabras =this.palabrasUserFile();
        int size=palabras.size();
        ArrayList<Palabra> palabrasNegativas = new ArrayList<Palabra>();
        Palabra pal;
        for(int i=0; i<size; i ++){
            pal=palabras.get(i);
            if(pal.calculoPromedio()<1.99){
            palabrasNegativas.add(pal);}
        }
        return palabrasNegativas;
    }
    
    public void palabraPositivaNegativa() throws FileNotFoundException {
        ArrayList<Palabra> lista_palabras=this.palabrasUserFile();
        int size = lista_palabras.size() - 1;
        Palabra positiva = lista_palabras.get(size);
        Palabra negativa = lista_palabras.get(0);

        System.out.println("-La palabra más positiva, con un puntaje de " + positiva.calculoPromedio() + " es " + positiva.getPalabra());
        System.out.println("-La palabra más negativa, con un puntaje de " + negativa.calculoPromedio() + " es " + negativa.getPalabra());
    }
    
    
     public void sentimientoGeneralArchivo(float promedio) {
        if (promedio > 2.01) {
            System.out.println("-El sentimiento general del archivo " + this.archivo.getUserFile() + " es positivo");
        }
        if (promedio < 1.99) {
            System.out.println("-El sentimiento general del archivo " + this.archivo.getUserFile() + " es negativo");
        }
     }
        
     public void analisisArchivo() throws FileNotFoundException {
        float pt_promedio = this.puntajePromedioArchivo();
        System.out.println("-El puntaje promedio del archivo " + this.archivo.getUserFile() + " es " + pt_promedio);
        this.sentimientoGeneralArchivo(pt_promedio);
    }
     
    
    public void escribirArchivoPositivo() throws FileNotFoundException{
    this.archivo.crearArchivoPositivo();
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
            bw2.close();
            System.out.println("Se creó exitosamente el archivo positivo.txt");
        } catch (IOException ex) {
            System.out.println("No se pudo escribir en el archivo positivo.txt");
        }
    }
    
    public void escribirArchivoNegativo() throws FileNotFoundException{
    this.archivo.crearArchivoNegativo();
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
            bw1.close();
           System.out.println("Se creó exitosamente el archivo negativo.txt"); 
        } catch (IOException ex) {
         System.out.println("No se pudo escribir en el archivo negativo.txt");
        }
    }
    
 
}
