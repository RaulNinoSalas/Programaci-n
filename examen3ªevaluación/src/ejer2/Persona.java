/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer2;

/**
 *
 * @author Rafael
 */
public class Persona {
    static private int contador;
    protected int id;
    protected String nombre;
    protected String priape;
    protected String secape;

    public Persona() {
    }

    public Persona(String nombre, String priape, String secape) {
        Persona.setContador();
        this.id = Persona.getContador();
        this.nombre = nombre;
        this.priape = priape;
        this.secape = secape;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador() {
        Persona.contador ++;;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPriape() {
        return priape;
    }

    public void setPriape(String priape) {
        this.priape = priape;
    }

    public String getSecape() {
        return secape;
    }

    public void setSecape(String secape) {
        this.secape = secape;
    }
    public void mostrarDatos(){
        System.out.println("Mostrar datos");
        System.out.println("ID: "+this.id);
        System.out.println("Nombre: "+ this.nombre);
        System.out.println("Apellido 1: "+ this.priape);
        System.out.println("Apellido 2: "+ this.secape);
    }
}
    
