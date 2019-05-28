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
public class Sanitario extends Persona {
    private String especialidad;
    private int dias_Experiencia;

    public Sanitario(String especialidad, int dias_Experiencia,String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2);
        this.especialidad = especialidad;
        this.dias_Experiencia = dias_Experiencia;
    }

    public Sanitario() {
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getDias_Experiencia() {
        return dias_Experiencia;
    }

    public void setDias_Experiencia(int dias_Experiencia) {
        this.dias_Experiencia = dias_Experiencia;
    }
    public void mostrarDatos(){
        super.mostrarDatos();
        System.out.println("Especialidad: "+ this.getEspecialidad());
        System.out.println("Dias experiencia: "+ this.getDias_Experiencia());
    }    
    
}
