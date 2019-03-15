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
public class Pez extends Animal {

    private static int contPeces = 0;
    private int numeroAletas;
    private float velocidadNado;

    public Pez(int numeroAletas, float velocidadNado, String nombre, float peso) {
        super(nombre, peso);
        this.numeroAletas = numeroAletas;
        this.velocidadNado = velocidadNado;
        incrementarContPeces();
    }

    public Pez() {
        super();
    }

    public static int getContPeces() {
        return contPeces;
    }

    public static void incrementarContPeces() {
        Pez.contPeces++;
    }

    public int getNumeroAletas() {
        return numeroAletas;
    }

    public void setNumeroAletas(int numeroAletas) {
        this.numeroAletas = numeroAletas;
    }

    public float getVelocidadNado() {
        return velocidadNado;
    }

    public void setVelocidadNado(float velocidadNado) {
        this.velocidadNado = velocidadNado;
    }

    @Override
    public void mostrarAnimal() {
        System.out.println("Nombre: "+this.getNombre());
        System.out.println("Peso: "+this.getPeso());
        System.out.print("Velocidad de nado: " + this.velocidadNado);
        System.out.print("Número de aletas: " + this.getNumeroAletas());
    }

    public static void mostrarOrdenado(ArrayList<Animal> listaAnimales) {
        ArrayList<Animal> listaOrdenada = new ArrayList<Animal>();
        System.out.println("¿Por que deseas ordenar los peces?");
        System.out.println("1 - Velocidad nado");
        System.out.println("2 - Numero de aletas");
        Scanner lector = new Scanner(System.in);
        int opcion = lector.nextInt();
        for (int i = 0; i < listaAnimales.size(); i++) {
            if (listaAnimales.get(i) instanceof Pez) {
                switch (opcion) {
                    case 1:
                        if (listaOrdenada.isEmpty()) {
                            // en este caso la lista está vacía, añado sin más
                            listaOrdenada.add(listaAnimales.get(i));
                        } else {
                            ordenarPezVelocidad(listaAnimales, i, listaOrdenada);
                        }
                        break;
                    case 2:
                        if (listaOrdenada.isEmpty()) {
                            // en este caso la lista está vacía, añado sin más
                            listaOrdenada.add(listaAnimales.get(i));
                        } else {
                            ordenarPezAletas(listaAnimales, i, listaOrdenada);
                        }
                        break;
                    default:
                        System.out.println("¿Por que deseas ordenar los peces?");
                        System.out.println("1 - Velocidad nado");
                        System.out.println("2 - Numero de aletas");
                        opcion = lector.nextInt();
                }
            }
        }
        //cuando haya acabado de aquí estará ordenado. es momento de mostrar
        for (int i = 0; i < listaOrdenada.size(); i++) {
            System.out.println("");
            System.out.println("==============================");
            System.out.println("La lista ordenada de Pez es:");
            System.out.print((i + 1) + ") ");
            listaOrdenada.get(i).mostrarAnimal();
        }
    }

    public static void ordenarPezAletas(ArrayList<Animal> listaAnimales, int i, ArrayList<Animal> listaOrdenada) {
        int j = 0;         //43 minutos      
        while (j < listaOrdenada.size() && ((Pez) listaAnimales.get(i)).getNumeroAletas()
                > ((Pez) listaOrdenada.get(j)).getNumeroAletas());
        {
            j++;
        }
        listaOrdenada.add(j, listaAnimales.get(i));
    }

    public static void ordenarPezVelocidad(ArrayList<Animal> listaAnimales, int i, ArrayList<Animal> listaOrdenada) {
        int j = 0;         //43 minutos                   
        while (j < listaOrdenada.size() && ((Pez) listaAnimales.get(i)).getVelocidadNado()
                > ((Pez) listaOrdenada.get(j)).getVelocidadNado()) {
            j++;
        }
        listaOrdenada.add(j, listaAnimales.get(i));
    }

    public static Pez pedirPez() {
        Scanner lector = new Scanner(System.in);
        Pez p1 = new Pez();
        System.out.println("Dame los datos del pez que quieres añadir");
        System.out.println("Nombre");
        p1.setNombre(lector.nextLine());
        System.out.println("Peso");
        p1.setPeso(lector.nextFloat());
        System.out.println("Numero de aletas");
        p1.setNumeroAletas(lector.nextInt());
        System.out.println("Velocidad de nado");
        p1.setVelocidadNado(lector.nextFloat());
        return p1;
    }   
}
