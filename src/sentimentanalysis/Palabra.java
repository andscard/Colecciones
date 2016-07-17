/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Administrador
 */
public class Palabra implements Comparable<Palabra>{
    private String palabra;
    private int frecuencia;
    private ArrayList<Integer> lista_promedio;


    enum Sentimiento{ Positivo, Negativo };
    
   
   
    public Palabra(String palabra) {
        this.palabra = palabra;
        this.frecuencia = 1;
        this.lista_promedio= new ArrayList();
    }

    public String getPalabra() {
        return this.palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getFrecuencia() {
        return this.frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public ArrayList<Integer> getPromedio() {
        return this.lista_promedio;
    }

    public void setPromedio(int promedio) {
        this.lista_promedio.add(promedio);
    }

    
    public void determinaSentimiento(){
        if (this.calculoPromedio()>2.01){
            Sentimiento sent=Sentimiento.Positivo;}
        
        if (this.calculoPromedio()<1.99){
            Sentimiento sent=Sentimiento.Positivo;}
    }
    
    
    public float calculoPromedio (){
        float suma=0;
        for (int i=0 ; i<this.lista_promedio.size() ;i++){
            suma=suma+this.lista_promedio.get(i);
    }
       return suma/this.lista_promedio.size(); 
        }
    
    

    public void estadoPalabra (){
        System.out.println("La palabra: "+this.palabra+" aparece "+this.getFrecuencia()+" veces.");
        System.out.println("El puntaje promedio es : "+this.calculoPromedio());
        System.out.println("Promedios: "+this.lista_promedio);
    }
    
    @Override
    public int compareTo(Palabra pal) {
    if (this.calculoPromedio()>pal.calculoPromedio()){
        return 1;
    }else if(this.calculoPromedio()<pal.calculoPromedio()){
        return -1;
    }else{
        return 0;}   
    }
}

