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
public class Alumno extends Persona {
    private Curso curso;
    private int edad;

    public Alumno() {
    }

    public Alumno(Curso curso, String nombre, String priape, String secape, int edad) {
        super(nombre, priape, secape);
        this.curso = curso;
        this.setEdad(edad);
    }
    public Alumno(Alumno a1) {
        super(a1.nombre, a1.priape, a1.secape);
        this.curso = a1.curso;
        this.setEdad(a1.edad);
    }
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad<18){ 
            this.edad = edad;
        }
        else {
            this.edad=0;
        }
    }

    @Override
    public void mostrarDatos(){
        super.mostrarDatos();
        System.out.println("Edad: " + this.edad);
    }    
}
