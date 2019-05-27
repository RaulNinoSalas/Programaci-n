/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen3evaluacion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Rafael
 */
public class Examen3evaluacion {

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            try (Connection conBBDD = obtenerConexion()) {
                menu(conBBDD);
            }
        } catch (SQLException ex) {
            System.out.println("SQLSTATE " + ex.getSQLState() + "SQLMESSAGE"
                    + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error entrada salida " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error no controlado " + ex.getMessage());
        }

    }

    public static void menu(Connection conBBDD) throws Exception {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;

        int opcion;
        while (!salir) {

            System.out.println("1. Opcion 1 - consulta");
            System.out.println("2. Opcion 2 - actualización");
            System.out.println("3. Opcion 3 - salir");

            System.out.println("Escribe una de las opciones");

            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    consultarBBDD(conBBDD);
                    break;
                case 2:
                    ejecutarTransaccion(conBBDD);
                    break;
                default:
                    salir = true;
                    System.out.println("Adios");
            }
        }
    }

    public static Connection obtenerConexion() throws Exception {
        String url = "jdbc:mysql://localhost:3306/practica8";
        return DriverManager.getConnection(url, "root", "");

    }

    public static void consultarBBDD(Connection conDDBB) throws Exception {
        Statement st;
        ResultSet rs;
        st = conDDBB.createStatement();
        rs = st.executeQuery("select * from DRINKER");
        escribirResultado(rs);
        st.close();
        rs.close();
    }

    public static void escribirResultado(ResultSet rs) throws Exception {
        FileWriter fichero = new FileWriter("D:\\Users\\Rafael\\Documents\\"
                + "salida.txt");
        PrintWriter pw = new PrintWriter(fichero);
        pw.println("En el bar tenemos estos datos");
        pw.println("===========================================");
        while (rs.next()) {
            pw.println("Nombre del bar: " + rs.getString("name") + "\t");
            pw.println(" Dirección del bar: " + rs.getString("address") + "\n");

        }
        pw.close();
    }

    public static void ejecutarTransaccion(Connection conDDBB) throws 
            Exception {
        try (Statement st = conDDBB.createStatement();){           
            conDDBB.setAutoCommit(false);
            Scanner sn = new Scanner(System.in);
            System.out.println("¿Cuantas filas quieres actualizar de la tabla "
                    + "serve"
                    + "s?");
            int filas = sn.nextInt();
            sn.nextLine();
            for (int i = 0; i < filas; i++) {
                actualizarRegistro(sn, st);
            }
            conDDBB.commit();
            conDDBB.setAutoCommit(true);            
        } catch (SQLException ex) {
            System.out.println("SQLSTATE " + ex.getSQLState() + " SQLMESSAGE"
                    + ex.getMessage());           
            conDDBB.rollback();           
        }
    }

    public static void actualizarRegistro(Scanner sn, Statement st) throws
            SQLException {
        System.out.println("¿QUÉ REGISTRO DE SERVES QUIERES MODIFICAR?");
        System.out.println("DIME EL BAR");
        String bar = sn.nextLine();
        System.out.println("DIME LA CERVEZA");
        String cerveza = sn.nextLine();
        System.out.println("¿Y QUÉ PRECIO QUIERES PONER?");
        String precio = sn.nextLine();
        int filas;
        String sentencia = ("UPDATE SERVES SET PRICE = \"" + precio
                + "\" WHERE  SERVE.BAR = \"" + bar + "\" and SERVES.BEER = \""
                + cerveza + "\";");
        filas = st.executeUpdate(sentencia);
        System.out.println(filas + " será actualizada");
    }
}
