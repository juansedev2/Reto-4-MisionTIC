/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import mundo.TipoAguaDAO;

/**
 *
 * @author Juan Sebastian
 */
public class TipoAguaDTO  extends OperacionesDTO{
    
    // Atributos de la clase
    private TipoAguaDAO tipo_de_agua_DAO;
    
    // Constructor
    public TipoAguaDTO() {
        super();  // Lamar al constructor de la clase padre
        this.tipo_de_agua_DAO = new TipoAguaDAO();
    }
    
    // Método para devolver todos los tipos de agua
    public ArrayList<String> devolverTodos(){
        // Llamo a la función de consultar y verifico que efectivamente se pudo realizar
        if(this.tipo_de_agua_DAO.consultar()){
            this.setLista_datos_salida(tipo_de_agua_DAO.getLista_tipos_de_agua());
            return this.getLista_datos_salida();
        }else{
            return null;
        }
    } 
    
}
