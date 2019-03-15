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
public class Animal {
    private static int contanimales=0;
    private String nombre;
    private float peso;

    public Animal(String nombre, float peso) {
        this.setNombre(nombre);
        this.peso = peso;
        incrementarContanimales();
    }
    
    public Animal(Animal a1) {
        this.setNombre(a1.getNombre());
        this.setPeso(a1.getPeso());
        incrementarContanimales();
    }    

    public static int getContanimales() {
        return contanimales;
    }

    public static void incrementarContanimales() {
        Animal.contanimales = contanimales++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toLowerCase();
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        Scanner lector=new Scanner(System.in);
        while(peso<=0){
            System.out.println("El peso no puede ser 0 o menos. Introduzca el "
                    + "peso de nuevo");
            peso=lector.nextFloat();
        }
        this.peso = peso;
    }
    
    public void mostrarAnimal(){
        System.out.println("Nombre: "+this.getNombre());
        System.out.println("Peso: "+this.getPeso());
    }
    abstract public void mostrarOrdenado(ArrayList<Animal> listaAnimales);    
}
