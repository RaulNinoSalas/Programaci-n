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
public class Plaza {

    private static int contador;
    private int id;
    private char tipo;
    private isAdjudicada adjudicada;
    private Persona interino;

    public enum isAdjudicada {
        ADJUDICADA, NO_ADJUDICADA
    };

    public Plaza(char tipo, boolean adjudicada) {
        this.id = contador++;
        this.tipo = tipo;
        this.adjudicada = isAdjudicada.NO_ADJUDICADA;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public isAdjudicada isAdjudicada() {
        return adjudicada;
    }

    public void setAdjudicada() {
        this.adjudicada = isAdjudicada.ADJUDICADA;
    }

    public Persona getInterino() {
        return interino;
    }

    public void setInterino(Persona interino) {
        this.interino = interino;
    }

    public void mostrarPlaza() {
        System.out.println("ID Plaza " + this.id);
        System.out.println("Tipo: " + this.getTipo());
        System.out.println("Adjudicada: " + this.isAdjudicada());
        if (this.isAdjudicada() == isAdjudicada.ADJUDICADA) {
            this.getInterino().mostrarDatos();
        }
        System.out.println("____________________________________________");
    }
}
