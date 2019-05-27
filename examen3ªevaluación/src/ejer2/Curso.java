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
public class Curso {

    private int numCurso;
    private String titulo;

    public Curso() {
    }

    public Curso(int numCurso, String titulo) {
        this.numCurso = numCurso;
        this.titulo = titulo;
    }
    public Curso(Curso c1) {
        this.numCurso = c1.numCurso;
        this.titulo = c1.titulo;
    }    

    public int getNumCurso() {
        return numCurso;
    }

    public void setNumCurso(int numCurso) {
        this.numCurso = numCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
