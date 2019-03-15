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

    public Ave() {
        super();
        incrementarContAves();
    }

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

    public static void mostrarOrdenado(ArrayList<Animal> listaAnimales) {
        ArrayList<Animal> listaOrdenada = new ArrayList<Animal>();
        System.out.println("¿Por que deseas ordenar las aves?");
        System.out.println("1 - Longitud de vuelo");
        System.out.println("2 - Numero de alas");
        Scanner lector = new Scanner(System.in);
        int opcion = lector.nextInt();
        for (int i = 0; i < listaAnimales.size(); i++) {
            if (listaAnimales.get(i) instanceof Ave) {
                switch (opcion) {
                    case 1:
                        if (listaOrdenada.isEmpty()) {
                            // en este caso la lista está vacía, añado sin más
                            listaOrdenada.add(listaAnimales.get(i));
                        } else {
                            ordenarAveVuelo(listaAnimales, i, listaOrdenada);
                        }
                        break;
                    case 2:
                        if (listaOrdenada.isEmpty()) {
                            // en este caso la lista está vacía, añado sin más
                            listaOrdenada.add(listaAnimales.get(i));
                        } else {
                            ordenarAveAlas(listaAnimales, i, listaOrdenada);
                        }
                        break;
                    default:
                        System.out.println("¿Por que deseas ordenar las aves?");
                        System.out.println("1 - Longitud de vuelo");
                        System.out.println("2 - Numero de alas");
                }
            }
        }
        //en este punto la lista ya está ordenada
        //solo falta mostrarla
        mostrarListaOrdenada(listaOrdenada);
    }

    public static void mostrarListaOrdenada(ArrayList<Animal> listaOrdenada) {
        for (int i = 0; i < listaOrdenada.size(); i++) {
            System.out.println("");
            System.out.println("==============================");
            System.out.println("La lista ordenada de Aves es:");
            System.out.print((i + 1) + ") ");
            listaOrdenada.get(i).mostrarAnimal();
        }
    }

    public static void ordenarAveAlas(ArrayList<Animal> listaAnimales, int i, ArrayList<Animal> listaOrdenada) {
        // ordenamos de menor a mayor
        int j = 0;         //43 minutos                   
        while (j < listaOrdenada.size() && ((Ave) listaAnimales.get(i)).getNumeroAlas()
                > ((Ave) listaOrdenada.get(j)).getNumeroAlas()) {
            j++;
        }
        listaOrdenada.add(j, listaAnimales.get(i));
    }

    public static void ordenarAveVuelo(ArrayList<Animal> listaAnimales, int i, ArrayList<Animal> listaOrdenada) {
        int j = 0;         //43 minutos +45 minutos 10.30                  
        while (j < listaOrdenada.size() && ((Ave) listaAnimales.get(i)).getLongitudVuelo()
                > ((Ave) listaOrdenada.get(j)).getLongitudVuelo()) {
            j++;
        }
        listaOrdenada.add(j, listaAnimales.get(i));
    }

    public static Ave pedirAve() {
        Scanner lector = new Scanner(System.in);
        Ave a1 = new Ave();
        System.out.println("Dame los datos del pez que quieres añadir");
        System.out.println("Nombre");
        a1.setNombre(lector.nextLine());
        System.out.println("Peso");
        a1.setPeso(lector.nextFloat());
        System.out.println("Longitud de vuelo");
        a1.setLongitudVuelo(lector.nextFloat());
        System.out.println("Numero de alas");
        a1.setNumeroAlas(lector.nextInt());
        return a1;
    }

    @Override
    public void getContador() {
        System.out.println("contador animales" + Animal.getContanimales());
        System.out.println("contador aves" +Ave.getContAves());
    }
}
