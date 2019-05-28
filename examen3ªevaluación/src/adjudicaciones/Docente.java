/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adjudicaciones;

/**
 *
 * @author rafae
 */
public class Docente extends Persona{
    private String titulacion;
    private float puntos;

    public Docente() {
    }

    public Docente(String titulacion, float puntos,String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2);
        this.titulacion = titulacion;
        this.puntos = puntos;
    }
    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }    
    @Override
    public void mostrarDatos(){
        super.mostrarDatos();
        System.out.println("Titulación: "+ this.getTitulacion());
        System.out.println("Puntos: "+ this.getPuntos());
    }    
}
