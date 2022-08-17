/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import mundo.TipoCuerpoAguaDAO;

/**
 *
 * @author Juan Sebastian
 */
public class TipoCuerpoAguaDTO extends OperacionesDTO {
    
     // Atributos de la clase
    private TipoCuerpoAguaDAO tipo_cuerpo_agua_DAO;
    
    // Constructor
    public TipoCuerpoAguaDTO() {
        super();  // Lamar al constructor de la clase padre
        this.tipo_cuerpo_agua_DAO = new TipoCuerpoAguaDAO();
    }
    
    // Método para devolver todos los tipos de agua
    public ArrayList<String> devolverTodos(){
        // Llamo a la función de consultar y verifico que efectivamente se pudo realizar
        if(this.tipo_cuerpo_agua_DAO.consultar()){
            System.out.println("ENTRÉ PARA OBTENER LOS DATOS");
            this.setLista_datos_salida(tipo_cuerpo_agua_DAO.getLista_cuerpos_de_agua());
            return this.getLista_datos_salida();
        }else{
            System.out.println("NO SE PUDO CONECTAR");
            return null;
        }
    } 
    
}
