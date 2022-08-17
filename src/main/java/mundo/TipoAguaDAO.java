/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juan Sebastian
 */
public class TipoAguaDAO implements BD_Operaciones_int{
    
    // Atributos de la clase
    private ArrayList<String> lista_tipos_de_agua;
    private Conexion conexion;
    
    // Constructor

    public TipoAguaDAO() {
        this.lista_tipos_de_agua = new ArrayList();
        this.conexion = new Conexion();
    }
    
    // Get y set

    public ArrayList<String> getLista_tipos_de_agua() {
        return lista_tipos_de_agua;
    }

    public void setLista_tipos_de_agua(ArrayList<String> lista_cuerpos_de_agua) {
        this.lista_tipos_de_agua = lista_cuerpos_de_agua;
    }
    
    
    // Métodos implementados de la interfaz de BD_Operaciones (NO TODOS SERÁN DEFINIDOS POR FALTA DE TIEMPO DE ENTREGA Y SOLO SE IMPLEMENTAN LOS MÁS IMPORTANTES)
    
    @Override
    public boolean crear() {
        return false;  
    }

    // Método para consultar y devolver los diferentes tipos de agua
    @Override
    public boolean consultar() {
         System.out.println("Consultar los tipos de agua");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos
        this.reiniciarListaDatosSalida();

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de consulta de todos los datos de los cuerpos de agua
                // NOTA: Esto con procedimientos almacenados sería mucho mejor; pero SQLite no lo permite, como se dijo anteriormente, este proyecto será en aras de probar ese SGBD, solamente este
                String sentencia = "select descripcion from Tipos_cuerpo_agua";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement consulta = con.prepareStatement(sentencia);
                // Con Resultset obtenemos los resultados
                ResultSet resultado = consulta.executeQuery(sentencia);

                while (resultado.next()) {  // Mientras halla registros en la consulta obtenida
                    // Obtengo toda la cadena completa de los datos del cuerpo de agua (el orden será igual como es solicitado al mostrar los datos para procesar)
                    String tipo_agua =  resultado.getString("descripcion");
                    this.lista_tipos_de_agua.add(tipo_agua);
                }
                
                System.out.println("La consulta de los tipos de agua fue realizada");
                estado = true;
                // Podría cerrar la conexión pero creo bloquearía la posibilidad de cerrarla ante otros métodos que la requieran (POSIBILIDAD)
                // Cerramos la conexión (experimental)
                con.close();  // Local de DAO
                this.conexion.getConexion().close();  // Del objeto conexión
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Hubo conexión a la base de datos; pero la transacción falló");
                System.out.println("El error fue: " + e);
                estado = false;
            }
        } else {
            System.out.println("No hay conexión a la base de datos");
            estado = false;
        }
        return estado;
    }

    @Override
    public boolean actualizar() {
        return false;
    }

    @Override
    public boolean eliminar() {
        return false;
    }
    
    // Método para reiniciar la lista de datos de salida
    private void reiniciarListaDatosSalida() {

        if (!this.lista_tipos_de_agua.isEmpty()) {  // Si y solo si hay elementos...
            this.lista_tipos_de_agua= new ArrayList();  // Reinicio el arreglo con los datos de salida para prevenir utilizar datos anteriores
        }
    }
    
    @Override
    public boolean cerrarConexion(){
        if(this.conexion.getBandera()){
            this.conexion.cerrarConexion();
            return true;
        }else{
            return false;
        }
    }

    
}
