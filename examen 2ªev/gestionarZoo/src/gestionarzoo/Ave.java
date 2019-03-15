/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionarzoo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rafae
 */
public class Ave extends Animal {

    private static int contAves = 0;
    private int numeroAlas;
    private float longitudVuelo;

    public Ave(String nombre, float peso, float longitudVuelo, int numeroAlas) {
        super(nombre, peso);
        incrementarContAves();
        this.longitudVuelo = longitudVuelo;
        this.numeroAlas = numeroAlas;
    }

    public Ave(Ave a1) {
        super(a1.getNombre(), a1.getPeso());
        incrementarContAves();
        this.setLongitudVuelo(a1.getLongitudVuelo());
        this.setNumeroAlas(a1.getNumeroAlas());
    }

    public static int getContAves() {
        return contAves;
    }

    public static void incrementarContAves() {
        Ave.contAves++;
    }

    public int getNumeroAlas() {
        return numeroAlas;
    }

    public void setNumeroAlas(int numeroAlas) {
        this.numeroAlas = numeroAlas;
    }

    public float getLongitudVuelo() {
        return longitudVuelo;
    }

    public void setLongitudVuelo(float longitudVuelo) {
        this.longitudVuelo = longitudVuelo;
    }

    @Override
    public void mostrarAnimal() {
        super.mostrarAnimal();
        System.out.println("Longitud de vuelo: " + this.getLongitudVuelo());
        System.out.println("Número de alas: " + this.getNumeroAlas());
    }

    @Override
    public void mostrarOrdenado(ArrayList<Animal> listaAnimales) {
        ArrayList<Ave> listaOrdenada;
        for (int i = 0; i < listaAnimales.size(); i++) {
            if (listaAnimales.get(i) instanceof Ave) {
                System.out.println("¿Por que deseas ordenar las aves?");
                System.out.println("1 - Longitud de vuelo");
                System.out.println("2 - Numero de alas");                
                Scanner lector = new Scanner(System.in);
                int opcion = lector.nextInt();
                switch (opcion) {
                    case 1:                        
                        if (listaOrdenada.isEmpty()){
                            // en este caso la lista está vacía
                            listaOrdenada.add(listaAnimales.get(i));
                        }else{
                            int j=0;         //43 minutos                   
                            while(listaAnimales.get(i)>lista.listaOrdenada(j){
                                j++;
                            }
                            break;
                        }
                    case 2:
                        System.out.println("Has seleccionado la opcion 2 - Prestar "
                                + "libro");
                        Libro.pedir_prestar_Libro(lista_libros);
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3 - Devolver "
                                + "libro");
                        Libro.pedir_devolver_Libro(lista_libros);
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 4 - Localizar "
                                + "libro");
                        Libro.localizar_Libro(lista_libros, false);
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            }
        }
    }
}
