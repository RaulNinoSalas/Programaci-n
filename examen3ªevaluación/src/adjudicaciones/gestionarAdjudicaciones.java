/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adjudicaciones;

import java.util.ArrayList;

/**
 *
 * @author rafae
 */
public class gestionarAdjudicaciones {

    public static void main(String[] args) {
        ArrayList<Plaza> lista_plazas = new ArrayList<Plaza>();
        ArrayList<Persona> lista_personas = new ArrayList<Persona>();
        crearPlazas(lista_plazas);
        crearPersonas(lista_personas);
        adjudicarPlazas(lista_plazas, lista_personas);
        mostrarAdjudicaciones(lista_plazas, 'D');
        mostrarAdjudicaciones(lista_plazas, 'S');

    }

    public static void crearPlazas(ArrayList<Plaza> lista_plazas) {
        for (int i = 0; i < 5; i++) {
            lista_plazas.add(new Plaza('D', false));
            lista_plazas.add(new Plaza('S', false));
        }
    }

    public static void crearPersonas(ArrayList<Persona> lista_plazas) {
        for (int i = 0; i < 3; i++) {
            lista_plazas.add((new Docente("titulacion" + i, i + 2, "nombre" + i, "apellido" + i, "apellido" + i)));
            lista_plazas.add((new Sanitario("especialidad" + i, i + 40, "nombre" + i + 90, "apellido" + i + 21, "apellido2" + i + 90)));
            //lista_plazas.add((new Sanitario("espe" + i, i + 40, "nom" + i + 30, "ap" + i + 21, "apido2" + i + 40)));
        }
    }

    public static void adjudicarPlazas(ArrayList<Plaza> lista_plazas, ArrayList<Persona> lista_personas) {
        int i=0;
        while (i < lista_plazas.size() && lista_personas.size() > 0) { // mientras haya plazas y personas disponibles
            lista_plazas.get(i).setInterino(econtrarInterino(lista_personas, lista_plazas.get(i).getTipo()));
            if (lista_plazas.get(i).getInterino() != null) {
                lista_plazas.get(i).setAdjudicada();
            }
            i++;
        }
    }

    public static Persona econtrarInterino(ArrayList<Persona> lista_personas, char tipo) {
        int num_persona = -1;//indice en la lista en la que lo hemos encontrado
        Persona aux = null;
        for (int i = 0; i < lista_personas.size(); i++) {
            if (tipo == 'D') {// es docente
                if (lista_personas.get(i) instanceof Docente) {
                    if (aux == null) {
                        aux = lista_personas.get(i);
                        num_persona = i;
                    } else {
                        if (((Docente) lista_personas.get(i)).getPuntos() >= ((Docente) aux).getPuntos()) {
                            aux = lista_personas.get(i);
                            num_persona = i;
                        }
                    }
                }
            } else {// es sanitario
                if (lista_personas.get(i) instanceof Sanitario) {
                    if (aux == null) { // inicialmente vale null
                        aux = lista_personas.get(i);
                        num_persona = i;
                    } else {

                        if (((Sanitario) lista_personas.get(i)).getDias_Experiencia() >= ((Sanitario) aux).getDias_Experiencia()) {
                            aux = lista_personas.get(i);
                            num_persona = i;
                        }
                    }
                }
            }
        }
        if (num_persona != -1) {
            lista_personas.remove(num_persona);
        }
        return aux;
    }

    public static void mostrarAdjudicaciones(ArrayList<Plaza> lista_plazas, char tipo) {
        System.out.println("========================");
        if (tipo == 'D') {
            System.out.println("La última adjudicación de plazas ha producido los siguientes resultados para Docentes");
        } else {
            System.out.println("La última adjudicación de plazas ha producido los siguientes resultados para Sanitarios");
        }
        System.out.println("========================");
        for (int i = 0; i < lista_plazas.size(); i++) {
            if (lista_plazas.get(i).getTipo() == tipo) {
                lista_plazas.get(i).mostrarPlaza();
            }
        }
    }
}
