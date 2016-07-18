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
    private File reviewFile;
    private File f_positivo;
    private File f_negativo;
    
    
    public Archivo (String file_name) {
        this.reviewFile= new File(file_name);
        this.f_positivo=new File("positivo.txt");
        this.f_negativo=new File("negativo.txt");
    }

    public File getReviewFile() {
        return reviewFile;
    }

    public void setReviewFile(File reviewFile) {
        this.reviewFile = reviewFile;
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
    
    public void crearArchivosSecundarios(){
        try {
            this.f_negativo.createNewFile();
            this.f_positivo.createNewFile();
        } catch (IOException exception) {
            System.out.println("\nNo se pudieron crear los archivos");}
    }
  
    
    
    
}
