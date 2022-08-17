/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import mundo.CuerpoDeAguaDAO;

/**
 *
 * @author Juan Sebastian
 */
public class CuerpoDeAguaDTO extends OperacionesDTO{
    
    // Atributos de la clase
    private CuerpoDeAguaDAO cuerpo_de_agua_DAO;
    
    // Constructor
    public CuerpoDeAguaDTO() {
        super();  // Lamar al constructor de la clase padre
        this.cuerpo_de_agua_DAO = new CuerpoDeAguaDAO();
    }
    
    // Get y set

    public CuerpoDeAguaDAO getCuerpo_de_agua_DAO() {
        return cuerpo_de_agua_DAO;
    }

    public void setCuerpo_de_agua_DAO(CuerpoDeAguaDAO cuerpo_de_agua_DAO) {
        this.cuerpo_de_agua_DAO = cuerpo_de_agua_DAO;
    }
    
    
    // Método para insertar un nuevo cuerpo de agua
    public boolean insertarNuevoCuerpo(){
        return this.cuerpo_de_agua_DAO.crear();
    }
    
    // Método para devolver todos los cuerpos de agua con todos sus datos
    public ArrayList<String> devolverTodos(){
        // Llamo a la función de consultar y verifico que efectivamente se pudo realizar
        if(this.cuerpo_de_agua_DAO.consultar()){
            this.setLista_datos_salida(cuerpo_de_agua_DAO.getLista_datos_salida());
            return this.getLista_datos_salida();
        }else{
            System.out.println("No se pudo devolver a todos los cuerpos de agua");
            return null;
        }
    }
    
    // Método para devolver los datos del cuerpo de agua específico
    public ArrayList<String> devolverEspecifico(String id_cuerpo_agua){
        // Llamo a la función de consultar y verifico que efectivamente se pudo realizar
        if(this.cuerpo_de_agua_DAO.consultar(id_cuerpo_agua)){
            this.setLista_datos_salida(cuerpo_de_agua_DAO.getLista_datos_salida());
            return this.getLista_datos_salida();
        }else{
            System.out.println("No se pudo el cuerpo de agua específico");
            return null;
        }
    }
    
    // Método para devolver los niveles de riesgo de cada cuerpo de agua
    public ArrayList<String> devolverNiveles(){
        // Llamo a la función de consultar y verifico que efectivamente se pudo realizar
        if(this.cuerpo_de_agua_DAO.consultarNivelesRiesgo()){
            this.setLista_datos_salida(cuerpo_de_agua_DAO.getLista_datos_salida());
            return this.getLista_datos_salida();
        }else{
            return null;
        }
    }
    
    // Método para devolver los niveles de riesgo de cada cuerpo de agua
    public String devolverInferiores(){
        // Llamo a la función de consultar y verifico que efectivamente se pudo realizar
        if(this.cuerpo_de_agua_DAO.consultarCuerposInferiores()){
            // Obtengo el valor de la cantidad de niveles de riesgo
            String total = this.cuerpo_de_agua_DAO.getCantidad_inferiores();
            return total;
        }else{
            return null;
        }
    }
    
    // Método para devolver el nombre de los cuerpos de agua que tienen un nivel de riesgo medio
    public ArrayList<String> devolverMedios(){
        // Llamo a la función de consultar y verifico que efectivamente se pudo realizar
        if(this.cuerpo_de_agua_DAO.consultarCuerposMedios()){
            this.setLista_datos_salida(cuerpo_de_agua_DAO.getLista_datos_salida());
            return this.getLista_datos_salida();
        }else{
            return null;
        }
    }
    
    // Método para devolver el cuerpo de agua más bajo
    public ArrayList<String> devolverMasBajo(){
        // Llamo a la función de consultar y verifico que efectivamente se pudo realizar
        if(this.cuerpo_de_agua_DAO.consultarCuerpoBajo()){
            this.setLista_datos_salida(cuerpo_de_agua_DAO.getLista_datos_salida());
            return this.getLista_datos_salida();
        }else{
            return null;
        }
    }
    
    
    
}
