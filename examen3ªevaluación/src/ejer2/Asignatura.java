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
public class Asignatura {
    private Curso curs;
    private String asignatura;

    public Asignatura(Curso curs, String asignatura) {
        this.curs = curs;
        this.asignatura = asignatura;
    }

    public Curso getCurs() {
        return curs;
    }

    public void setCurs(Curso curs) {
        this.curs = curs;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
    
    
}
