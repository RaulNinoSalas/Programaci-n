/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer2;

import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class Profesor extends Persona{
    private ArrayList <Asignatura> imparte;

    public Profesor() {
    }

    public Profesor(String nombre, String priape, String secape) {
        super(nombre, priape, secape);
    }

    public Profesor(ArrayList<Asignatura> imparte) {
        this.imparte = imparte;
    }

    public Profesor(ArrayList<Asignatura> imparte, String nombre, String priape, String secape) {
        super(nombre, priape, secape);
        this.imparte = imparte;
    }

    public ArrayList<Asignatura> getImparte() {
        return imparte;
    }

    public void setImparte(ArrayList<Asignatura> imparte) {
        this.imparte = imparte;
    }
}
