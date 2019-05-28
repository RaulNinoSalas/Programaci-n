/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenjunio2019;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafa
 */
public class ExamenJunio2019 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try (Connection conBBDD = obtenerConexion()) {
                menu(conBBDD);
        } catch (SQLException ex) {
            System.out.println("SQLSTATE " + ex.getSQLState() + "SQLMESSAGE"
                    + ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println("Error de entrada salida " + ex.getMessage());            
        }
        catch (Exception ex){
            System.out.println("Error no controlado"+ ex.getMessage());
        }

    }

    public static void menu(Connection conBBDD) throws Exception {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;

        int opcion;
        while (!salir) {

            System.out.println("1. Opcion 1 - insertar serves");
            System.out.println("2. Opcion 2 - consultar drinkers");
            System.out.println("3. Opcion 3 - salir");

            System.out.println("Escribe una de las opciones");
            try {
                opcion = sn.nextInt();
            } catch (Exception excepcion) {
                System.out.println("Por favor, introduz"
                        + "ca un valor numérico.");
                System.out.println("Excepcion:" + excepcion.getMessage()
                        + excepcion.getStackTrace());
                sn.next();
                opcion = 0;
            }
            switch (opcion) {
                case 1:
                    insertarServes(conBBDD);
                    ;
                    break;
                case 2:
                    consultarDrinkers(conBBDD);
                    break;
                case 3:
                    salir = true;
                default:
                    System.out.println("Adios");
            }

        }
    }

    public static Connection obtenerConexion() throws SQLException {
        //String url = "jdbc:mysql://localhost:3306/practica8";
        String url = "jdbc:mysql://db4free.net:3306/rafagion";
        return DriverManager.getConnection(url, "rafagion", "*****");
    }

    public static void insertarServes(Connection con) throws SQLException {
        PreparedStatement pst=null;
        try {
            int num_sentencias = solicitarSentencias();
            pst = con.prepareStatement(preparedInsert());
            con.setAutoCommit(false);
            //dadas n sentencias tendré en total n/3 transacciones distintas
            num_sentencias= num_sentencias/3;
            for (int i =0; i <num_sentencias;i++){
                while (!lanzarTransaccion(pst,con));                                
            }   
        } catch (SQLException ex) {
            throw new SQLException("Excepción en insertarServer", "SQLSTATE: "+ex.getSQLState());
        } finally {
            con.setAutoCommit(false);
            pst.close();
        }
        
    }
    public static String preparedInsert(){
        return "INSERT INTO Serves VALUES (?,?,?)";                
    }

    public static int solicitarSentencias() {
        Scanner sn = new Scanner(System.in);
        int num_sentencias = 0;
        System.out.println("Por favor, introduzca el número de Serves que quieres insertar");
        num_sentencias = sn.nextInt();
        while (((num_sentencias % 3) != 0) 
            || (num_sentencias < 3)){
        System.out.println("Por favor, introduzca el número de Serves que quieres insertar");
            num_sentencias = sn.nextInt();
        }
        return num_sentencias;
    }

    public static void consultarDrinkers(Connection con)  throws SQLException, IOException {
        Statement st;
        ResultSet rs;
        st = con.createStatement();
        rs = st.executeQuery("select * from Drinker");
        leerResultado(rs);
        st.close();
        rs.close();
    }

    public static void leerResultado(ResultSet rs) throws SQLException, IOException {
        System.out.println("En el Bar tenemos estos datos");
        File archivoSalida = new File("destinoBuffer.txt");
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida));
        escritor.write("Los resultados de la consulta son\n");
        escritor.newLine();
        while (rs.next()) {
            escritor.write("Nombre del Bar: " + rs.getString("name"));
            escritor.newLine();
            escritor.write("Dirección del Bar: " + rs.getString("address"));            
            escritor.newLine();
        }
        escritor.close();
    }

    private static void ejecutarInsert(PreparedStatement pst) throws SQLException {
        Scanner sn = new Scanner(System.in);
        System.out.println("Vamos a insertar un nuevo registro");
        System.out.println("Dame el nombre del bar"); 
        String bar=sn.next();
        pst.setString(1, bar);
        System.out.println("Dame el nombre de la cerveza");
        pst.setString(2,sn.next());
        System.out.println("Dame el precio de la cerveza");        
        pst.setFloat(3, sn.nextFloat());
        pst.execute();
        System.out.println("ha funcionado");
    }

    private static boolean lanzarTransaccion(PreparedStatement pst, Connection con) throws SQLException {
        try {
            ejecutarInsert(pst);
            System.out.println("sentencia 1 ok");
            ejecutarInsert(pst);
            System.out.println("sentencia 2 ok");            
            ejecutarInsert(pst);
            System.out.println("sentencia 3 ok");            
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("Hemos hecho rollback");
            System.out.println("Error en la transacción: " + ex.getMessage() + " SQLSTATE:" + ex.getSQLState());
            System.out.println("Hay que volver a introducir toda la información");
            return false;
        }
        return true;
    }
}
