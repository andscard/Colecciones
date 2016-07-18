/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Archivo {
    private File userFile;
    private File reviewFile;
    private File f_positivo;
    private File f_negativo;
    
    
    public Archivo (String file_name) throws FileNotFoundException{
        this.reviewFile=new File("movieReviews.txt");
        this.f_positivo=new File("positivo.txt");
        this.f_negativo=new File("negativo.txt");
        this.userFile= new File(file_name);
    }

    public File getUserFile() {
        return userFile;
    }

    public File getReviewFile() {
        return reviewFile;
    }


    public File getF_positivo() {
        return f_positivo;
    }

    public void setF_positivo(File f_positivo) {
        this.f_positivo = f_positivo;
    }

    public File getF_negativo() {
        return f_negativo;
    }

    public void setF_negativo(File f_negativo) {
        this.f_negativo = f_negativo;
    }
    
    public void crearArchivoPositivo(){
        try {
            this.f_positivo.createNewFile();
        } catch (IOException exception) {
            System.out.println("\nNo se pudo crear archivo positivo.txt");}
    }
    
    public void crearArchivoNegativo(){
     try {
            this.f_negativo.createNewFile();
        } catch (IOException exception) {
            System.out.println("\nNo se pudo crear archivo negativo.txt");}
    }
    
    
    
}
