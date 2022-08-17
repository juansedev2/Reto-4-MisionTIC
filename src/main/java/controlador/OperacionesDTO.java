/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;

/**
 *
 * @author Juan Sebastian
 */
public abstract class OperacionesDTO {
    
    // Atributos de la clase
    private String cadena_datos_entrada;
    private ArrayList<String> lista_datos_salida;
    
    
    // Constructor de la clase
    public OperacionesDTO() {
        this.cadena_datos_entrada = "";
        this.lista_datos_salida = new ArrayList();
    }
    
    // Get y set

    public String getCadena_datos_entrada() {
        return cadena_datos_entrada;
    }

    public void setCadena_datos_entrada(String cadena_datos_entrada) {
        this.cadena_datos_entrada = cadena_datos_entrada;
    }

    public ArrayList<String> getLista_datos_salida() {
        return lista_datos_salida;
    }

    public void setLista_datos_salida(ArrayList<String> lista_datos_salida) {
        this.lista_datos_salida = lista_datos_salida;
    }
    
    
   
}
