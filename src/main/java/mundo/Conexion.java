/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import java.sql.*;

/**
 *
 * @author Juan Sebastian
 */
public class Conexion {
    
    // Atributos de la clas
    private Connection conexion;  // Es el conector que me permitirá conectarme y operar con la base de datos
    private boolean bandera;  // bandera me permite saber si la conexión a la base de datos fue exitosa o no
    private String url;   // Cadena que contiene la URL de la conexion a la base de datos
    private String tipo_bd;
    private String localizacion;
    private String bd_nombre;
    private String usuario;
    private String contrasena;
    

    public Conexion() {
        
        // El : del inicio de la cadena de localizacion se debe dejar por defecto
        this.localizacion = "://localhost:3306/bd_irca";
        this.tipo_bd = "mysql";
        this.usuario = "root";
        this.contrasena = "";
        this.url = this.tipo_bd + this.localizacion;
        
        try {
            // ACTUALIZACIÓN: SQLITE tiene errores con jdbc, puede ser el mismo sgbd pero no es capaz de reconocer tablas, hacer operaciones y solo sabe conectar, incluso revisando que todo está bien
            // Errores como: "No se encuentra la tabla **** en la base de datos, o que se perdió la base de datos y cosas así, con MySQL no pasa esto"
            // No tiene procedimientos almacenados y demás funcionaldiades, así que se descarta el uso de este SGBD y se cambia  MySQL proporcionado por Apache, pésimo
            
            // En este proyecto se instaló las jdbc de MySQL y SQLite, así que hechar un vistazo al POM y utilizar el SGBD que se necesite usar
            
            // En el caso de MySQL (ejemplo): 
            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_name", "user", "password");
            
            // En el caso de SQLite, se necesita una ruta absoluta donde está el directorio del programa y allí (en mi caso) estará el archivo con la base de datos
            // this.conexion = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\juans_000\\Desktop\\Programacion\\SQLiteStudio\\FIRST_BD_SQLITE.db", "root", "");
            
            this.conexion = DriverManager.getConnection("jdbc:" + this.url , this.usuario , this.contrasena);
            System.out.println("La conexion a la base de datos fue exitosa");
            this.bandera = true;
            //this.conexion.close();  // Cerra la conexion

        } catch (SQLException e) {
            this.bandera = false;
            System.out.println("El error es: " + e);
        }
    }
    
    // Get y set

    public boolean getBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public Connection getConexion() {
        return conexion;
    }
    
    public void cerrarConexion() {
        try{
            this.conexion.close();
            System.out.println("Conexion cerrada");
        }catch(SQLException e){
            System.out.println("Hubo un error al cerrar la conexión de la bd y fue: " + e );
        }
    }
    
    /*Main para probar la conexion
    public static void main(String [] args){
        Conexion conexion = new Conexion();
    }*/
        
}
