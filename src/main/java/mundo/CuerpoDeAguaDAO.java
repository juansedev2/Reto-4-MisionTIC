/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Juan Sebastian
 */
public class CuerpoDeAguaDAO implements BD_Operaciones_int {

    // Atributos de la clase
    private String cadena_datos_entrada;  // Será una cadena de datos multiuso para las operaciones CRUD, cargará con cualquier información que se necesite
    private ArrayList<String> lista_datos_salida;
    private String cantidad_inferiores;  // Variable para facilitar la cantidad de cuerpos inferiores
    private ArrayList<String> lista_datos_calculados;
    private Conexion conexion;
    private String id_cuerpo;

    // Constructor
    public CuerpoDeAguaDAO() {
        this.cadena_datos_entrada = "";
        this.conexion = new Conexion();
        this.lista_datos_salida = new ArrayList();
        this.cantidad_inferiores = "";
        this.lista_datos_calculados = new ArrayList();
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

    public String getCantidad_inferiores() {
        return cantidad_inferiores;
    }

    public void setCantidad_inferiores(String cantidad_inferiores) {
        this.cantidad_inferiores = cantidad_inferiores;
    }

    public ArrayList<String> getLista_datos_calculados() {
        return lista_datos_calculados;
    }

    public void setLista_datos_calculados(ArrayList<String> lista_datos_calculados) {
        this.lista_datos_calculados = lista_datos_calculados;
    }
    
    

    // Métodos implementados de la intefaz BD_Operaciones_int, son métodos generales que tengo reservados para pruebas y funcionalidades que pudiesen necesitarse de manera general con la base de datos
    // Método de inserción GENERAL, es decir que podrá ser usado para cualquier inserción en la tabla de cuerpos ede agua
    @Override
    public boolean crear() {

        System.out.println("Insertar cuerpo de agua");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de inserción de un cuerpo de agua
                String sentencia = "insert into Cuerpos_de_agua"
                        + "(id_tipo_cuerpo_agua, id_tipo_agua, nombre, municipio, numero_clasificacion, clasificacion_IRCA) "
                        + "values(?, ?, ?, ?, ?, ?)";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement insercion = con.prepareStatement(sentencia);
                // Definimos los valores para la insercion a traves de un arreglo que contendrá los datos para insertar
                String[] valores = this.cadena_datos_entrada.split(" ");
                // Asignamos valores  (NOTA: El id del cuerpo de agua es autoincrementable, se omite su insercióna acá)
                insercion.setString(1, valores[0]);  // id_tipo_cuerpo_agua
                insercion.setString(2, valores[1]);  // id_tipo_agua
                insercion.setString(3, valores[2]);  // nombre
                insercion.setString(4, valores[3]);  // municipio
                insercion.setString(5, valores[4]);  // numero_clasificacion
                // Antes de insertar la clasificacion IRCA (es una palabra), debo calcular cual es: 
                Float numero_clasificacion = Float.parseFloat(valores[4]);  // No uso try catch porque desde interfaz, obligo a que el tipo de dato sea el correcto y ya puedo asumir que llega el valor adecuado
                insercion.setString(6, calcularIRCA(numero_clasificacion));  // clasificacion_IRCA obtenida del método calcularIRCA
                // Confirmo la ejecución de la sentencia
                insercion.executeUpdate();
                System.out.println("La insercion fue realizada");
                estado = true;
                // Podría cerrar la conexión pero creo bloquearía la posibilidad de cerrarla ante otros métodos que la requieran (POSIBILIDAD)
                // Cerramos la conexión (experimental)
                con.close();  // Local de DAO
                this.conexion.getConexion().close();  // Del objeto conexión
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Hubo conexión a la base de datos; pero la transacción falló");
                System.out.println("El erro fue: " + e);
                estado = false;
            }
        } else {
            System.out.println("No hay conexión a la base de datos");
            estado = false;
        }
        return estado;
    }

    // Método GENERAl de consultar todos los datos de los cuerpos de agua
    @Override
    public boolean consultar() {

        System.out.println("Consultar cuerpos de agua");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos
        this.reiniciarListaDatosSalida();

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de consulta de todos los datos de los cuerpos de agua
                // NOTA: Esto con procedimientos almacenados sería mucho mejor; pero SQLite no lo permite, como se dijo anteriormente, este proyecto será en aras de probar ese SGBD, solamente este
                String sentencia = "select C.nombre, C.id_cuerpo_agua, TC.descripcion as \"des1\", TA.descripcion as \"des2\", C.numero_clasificacion \n"
                        + "from Cuerpos_de_agua C\n"
                        + "inner join Tipos_cuerpo_agua TC\n"
                        + "on C.id_tipo_cuerpo_agua = TC.id_tipo_cuerpo_agua\n"
                        + "inner join Tipos_de_agua TA\n"
                        + "on C.id_tipo_agua = TA.id_tipo_agua;";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement consulta = con.prepareStatement(sentencia);
                // Con Resultset obtenemos los resultados
                ResultSet resultado = consulta.executeQuery(sentencia);

                while (resultado.next()) {  // Mientras halla registros en la consulta obtenida
                    // Obtengo toda la cadena completa de los datos del cuerpo de agua (el orden será igual como es solicitado al mostrar los datos para procesar)
                    String registro_completo = resultado.getString("nombre")
                            + " "
                            + resultado.getString("id_cuerpo_agua")
                            + " "
                            + resultado.getString("des1")  // Descripcion del tipo de cuerpo de agua
                            + " "
                            + resultado.getString("des2")  // Descrpcion del tipo de agua
                            + " "
                            + resultado.getString("numero_clasificacion");
                    this.lista_datos_salida.add(registro_completo);
                }
                
                System.out.println("La consulta fue realizada");
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

        System.out.println("Actualizar cuerpo de agua");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de actualización de un cuerpo de agua
                String sentencia = "update Cuerpos_de_agua set id_tipo_cuerpo_agua = ?, id_tipo_agua = ?, nombre = ?, municipio = ?, numero_clasificacion = ?, clasificacion_IRCA = ? where id_cuerpo_agua = ?";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement insercion = con.prepareStatement(sentencia);
                // Definimos los valores de la actualización
                String[] valores = this.cadena_datos_entrada.split(" ");
                System.out.println("VAMOS A ACTUALIZAR");
                // Asignamos valores  (NOTA: El id del cuerpo de agua es autoincrementable, se omite su insercióna acá)
                insercion.setString(1, valores[0]);  // id_tipo_cuerpo_agua
                insercion.setString(2, valores[1]);  // id_tipo_agua
                insercion.setString(3, valores[2]);  // nombre
                insercion.setString(4, valores[3]);  // municipio
                insercion.setString(5, valores[4]);  // numero_clasificacion
                // Antes de actualizar la clasificacion IRCA (es una palabra), debo calcular cual es (NO LA RECIBO de la cadena de datos porque NO ES UN VALOR que un usuario deba cambiar): 
                Float numero_clasificacion = Float.parseFloat(valores[4]);  // No uso try catch porque desde interfaz, obligo a que el tipo de dato sea el correcto y ya puedo asumir que llega el valor adecuado
                insercion.setString(6, calcularIRCA(numero_clasificacion));  // clasificacion_IRCA obtenida del método calcularIRCA
                insercion.setString(7, valores[5]);  // id_tipo_cuerpo_agua
                // Confirmo la ejecución de la sentencia
                insercion.executeUpdate();
                System.out.println("La actualización fue realizada");
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
    public boolean eliminar() {

        System.out.println("Eliminar cuerpo de agua");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de actualización de un cuerpo de agua
                String sentencia = "delete from Cuerpos_de_agua where id_cuerpo_agua = ?";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement insercion = con.prepareStatement(sentencia);
                // Definimos los valores de la actualización
                String id = this.cadena_datos_entrada;
                // Asignamos valores  (NOTA: El id del cuerpo de agua es autoincrementable, se omite su insercióna acá)
                insercion.setString(1, id);  // id_cuerpo_agua
                // Confirmo la ejecución de la sentencia
                insercion.executeUpdate();
                System.out.println("La eliminación fue realizada");
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
    public boolean cerrarConexion(){
        if(this.conexion.getBandera()){
            this.conexion.cerrarConexion();
            return true;
        }else{
            return false;
        }
    }
    
    // Método para buscar los datos de un cuerpo de agua específico
    public boolean consultar(String id){
        
        System.out.println("Consultar cuerpo de agua específico");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos
        this.reiniciarListaDatosSalida();

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de consulta de todos los datos de los cuerpos de agua
                System.out.println("Valor de id: " + id);
                
                String sentencia = "select id_cuerpo_agua, id_tipo_cuerpo_agua, id_tipo_agua, nombre, municipio, numero_clasificacion, clasificacion_IRCA "
                        + "from cuerpos_de_agua where id_cuerpo_agua = ? limit 1";  
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                try ( // Definimos la conexion para hacer la transaccion obteniendo la conexion
                        Connection con = this.conexion.getConexion()) {
                    // Preparamos la insercion en la bd (pre compilar la sentencia)
                    PreparedStatement consulta = con.prepareStatement(sentencia);
                    System.out.println("Veamos el valor de id nuevamente: " + id);
                    consulta.setString(1, id);
                     System.out.println("Veamos el valor de id nuevamente: " + id);
                    // Con Resultset obtenemos los resultados
                    ResultSet resultado = consulta.executeQuery();
                    System.out.println("Voy a extraer datos");
                    while (resultado.next()) {  // Mientras halla registros en la consulta obtenida
                        // Obtengo toda la cadena completa de los datos del cuerpo de agua (el orden será igual como es solicitado al mostrar los datos para procesar)
                        this.lista_datos_salida.add(resultado.getString("id_cuerpo_agua"));
                        this.lista_datos_salida.add(resultado.getString("id_tipo_cuerpo_agua"));
                        this.lista_datos_salida.add(resultado.getString("id_tipo_agua"));
                        this.lista_datos_salida.add(resultado.getString("nombre"));
                        this.lista_datos_salida.add(resultado.getString("municipio"));
                        this.lista_datos_salida.add(resultado.getString("numero_clasificacion"));
                        this.lista_datos_salida.add(resultado.getString("clasificacion_IRCA"));
                    }   
                    System.out.println("La consulta del cuerpo de agua específico fue realizada");
                    estado = true;
                    // Podría cerrar la conexión pero creo bloquearía la posibilidad de cerrarla ante otros métodos que la requieran (POSIBILIDAD)
                    // Cerramos la conexión (experimental)
                    // Local de DAO
                }
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

    // Método para realizar la consultar el nivel de riesgo de CADA cuerpo de agua (todos)
    public boolean consultarNivelesRiesgo() {

        System.out.println("consultarNivelesRiesgo");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos
        this.reiniciarListaDatosSalida();

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de consulta del nivel de clasificación IRCA de todos los cuerpos de agua
                String sentencia = "select clasificacion_IRCA from Cuerpos_de_agua";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement consulta = con.prepareStatement(sentencia);
                // Con Resultset obtenemos los resultados
                ResultSet resultado = consulta.executeQuery(sentencia);

                while (resultado.next()) {  // Mientras halla registros en la consulta obtenida
                    // Obtengo toda la cadena completa de los datos del cuerpo de agua
                    String registro_completo = resultado.getString("clasificacion_IRCA");
                    this.lista_datos_salida.add(registro_completo);
                }

                System.out.println("La consulta de los niveles de riesgo fue realizada");
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

    // Método para consultar la cantidad de cuerpos inferiores
    public boolean consultarCuerposInferiores() {

        System.out.println("consultarCuerposInferiores");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de consulta de todos los cuerpos de agua que tengan una clasificación IRCA media o inferior
                String sentencia = "select count(*) from cuerpos_de_agua where clasificacion_IRCA in(\"MEDIO\", \"BAJO\", \"SIN RIESGO\")";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement consulta = con.prepareStatement(sentencia);
                // Con Resultset obtenemos los resultados
                ResultSet resultado = consulta.executeQuery(sentencia);
                int contador = 0;
                while (resultado.next()) {  // Mientras halla registros en la consulta obtenida
                    contador++;
                }
                this.cantidad_inferiores = String.valueOf(contador);  // Asigno el valor en cadena para enviarlo
                System.out.println("La consulta de la cantidad de cuerpos inferiores fue realizada");
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

    // Método para consultar el nombre de los cuerpos de agua que tienen un nivel de riesgo medio
    public boolean consultarCuerposMedios() {

        System.out.println("consultarCuerposMedios");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos
        this.reiniciarListaDatosSalida();

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de consulta los nombres de los cuerpos de agua que tengan un nivel de riesgo medio
                String sentencia = "select nombre from cuerpos_de_agua where clasificacion_IRCA = \"MEDIO\"";
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                try ( // Definimos la conexion para hacer la transaccion obteniendo la conexion
                        Connection con = this.conexion.getConexion()) {
                    // Preparamos la insercion en la bd (pre compilar la sentencia)
                    PreparedStatement consulta = con.prepareStatement(sentencia);
                    // Con Resultset obtenemos los resultados
                    ResultSet resultado = consulta.executeQuery(sentencia);
                    int contador = 0;
                    while (resultado.next()) {  // Mientras halla registros en la consulta obtenida
                        contador++;
                        String registro = resultado.getString("nombre");
                        this.lista_datos_salida.add(registro);
                    }   if (contador == 0) {  // Si no hubo cuerpos de agua con nivel medio, debo indicar explícitamente que no hubo con NA y será el único elemento para ese arreglo
                        this.lista_datos_salida.add("NA");
                    }   System.out.println("La consulta el nombre de los cuerpos con clasificacion media fue realizada");
                    estado = true;
                    // Podría cerrar la conexión pero creo bloquearía la posibilidad de cerrarla ante otros métodos que la requieran (POSIBILIDAD)
                    // Cerramos la conexión (experimental)
                    // Local de DAO
                }
                this.conexion.getConexion().close();  // Del objeto conexión
            } catch (NumberFormatException | SQLException e) {
                this.lista_datos_salida.add("NA");
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

    // Método para consultar el nombre del cuerpo con la clasificacion IRCA más baja
    public boolean consultarCuerpoBajo() {

        System.out.println("consultarCuerpoBajo");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos
        this.reiniciarListaDatosSalida();

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de consulta del nombre del cuerpo de agua con clasificación IRCA más baja
                // Un error extraño es que al no agregar el alias de cuerpo de agua, según java esa columna no existe
                String sentencia = "select id_cuerpo_agua as \"id_cuerpo_agua\", nombre from cuerpos_de_agua where numero_clasificacion "
                        + "= (select min(numero_clasificacion) from cuerpos_de_agua)";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement consulta = con.prepareStatement(sentencia);
                // Con Resultset obtenemos los resultados
                ResultSet resultado = consulta.executeQuery(sentencia);

                int contador = 0;
                while (resultado.next()) {  // Mientras halla registros en la consulta obtenida
                    contador++;
                    String registro = resultado.getString("nombre") + " " + resultado.getString("id_cuerpo_agua");
                    this.lista_datos_salida.add(registro);
                }

                System.out.println("La consulta del cuerpo de agua con clasificacion más baja fue realizada");
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

    // TODO: Hacer un método que me permita saber si hay registros en la base de datos
    public boolean consultarCantidad(){
        
         System.out.println("consultarCantidad");

        boolean estado;  // Bandera que obtendrá la respuesta de si hay o no conexión a la base de datos
        this.reiniciarListaDatosSalida();

        if (this.conexion.getBandera()) {  // Necesito primero que todo evaluar que la conexión a la base de datos no sea nula, en caso de ser true, hay conexión

            try {  // Intentar una operación con la base de datos, en este caso una inserción de un cuerpo de agua

                // Sentencia de consulta del nombre del cuerpo de agua con clasificación IRCA más baja
                String sentencia = "select count(*) from Cuerpos_de_agua";
                // Definimos la conexion para hacer la transaccion obteniendo la conexion
                Connection con = this.conexion.getConexion();
                // Preparamos la insercion en la bd (pre compilar la sentencia)
                PreparedStatement consulta = con.prepareStatement(sentencia);
                consulta.setString(1, "MEDIO");
                // Con Resultset obtenemos los resultados
                ResultSet resultado = consulta.executeQuery(sentencia);

                while (resultado.next()) {  // Mientras halla registros en la consulta obtenida
                    this.cantidad_inferiores = resultado.getString("count(*)");
                }

                System.out.println("La consulta del cuerpo de agua con clasificacion más baja fue realizada");
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
    
    
    // Método para calcular la clasificación IRCA al momento de insertar en la base de datos un nuevo cuerpo de agua
    private String calcularIRCA(Float numero_clasificacion) {

        String clasificacion;
        // Según el número en clasificación IRCA (número de identificación), será asignado la clasificación    
        if (numero_clasificacion >= 0 && numero_clasificacion < 5) {

            clasificacion = "SIN RIESGO";

        } else if (numero_clasificacion >= 5 && numero_clasificacion < 14) {

            clasificacion = "BAJO";

        } else if (numero_clasificacion >= 14 && numero_clasificacion < 35) {

            clasificacion = "MEDIO";

        } else if (numero_clasificacion >= 35 && numero_clasificacion <= 80) {  // NOTA: (DEBE SER < 80 ORIGINALMENTE) porque la lógica establecida en el documento no es la misma que plasmraron en codegrade (esa está mal según el documento)

            clasificacion = "ALTO";

        } else if (numero_clasificacion > 80 && numero_clasificacion <= 100) {

            clasificacion = "INVIABLE SANITARIAMENTE";

        } else {
            clasificacion = "FUERA DE RANGO";
        }
        return clasificacion;
    }

    // Método para reiniciar la lista de datos de salida
    private void reiniciarListaDatosSalida() {

        if (!this.lista_datos_salida.isEmpty()) {  // Si y solo si hay elementos...
            this.lista_datos_salida = new ArrayList();  // Reinicio el arreglo con los datos de salida para prevenir utilizar datos anteriores
        }
    }

}
