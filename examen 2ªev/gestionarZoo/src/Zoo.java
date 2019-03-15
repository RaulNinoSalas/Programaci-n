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
public class Zoo {

    static ArrayList<Animal> listaAnimales = new ArrayList<Animal>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Zoo.menu(listaAnimales);
    }

    static public void menu(ArrayList<Animal> listaAnimales) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    

        while (!salir) {

            System.out.println("1. Opcion 1 - Alta Pez");
            System.out.println("2. Opcion 2 - Alta Ave");
            System.out.println("3. Opcion 3 - Mostrar Ordenado");
            System.out.println("4. Salir");

            System.out.println("Escribe una de las opciones");
            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la opcion 1 - Alta "
                            + "Pez");
                    listaAnimales.add(Pez.pedirPez());
                    break;
                case 2:
                    System.out.println("Has seleccionado la opcion 2 - Alta "
                            + "Ave");
                    listaAnimales.add(Ave.pedirAve());
                    break;
                case 3:
                    menuMostrarOrdenado(listaAnimales);

                    break;

                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }

        }
    }

    public static void menuMostrarOrdenado(ArrayList<Animal> listaAnimales) {
        int opcion; //Guardaremos la opcion del usuario            
        Scanner sn = new Scanner(System.in);
        System.out.println("¿Qué desea ver?");
        System.out.println("1) Aves ordenadas");
        System.out.println("2) Peces ordenados");
        System.out.println("3) Salir");
        opcion = sn.nextInt();
        switch (opcion) {
            case 1:
                Ave.mostrarOrdenado(listaAnimales);
                break;
            case 2:
                Pez.mostrarOrdenado(listaAnimales);
                break;
            case 3:
                break;
            default:
                System.out.println("Solo números entre 1 y 3");
        }

    }

}
