/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rafael
 */
public class Principal {
    public static void main(String[] args) {
       Scanner sn = new Scanner(System.in);
       boolean salir = false;
       int opcion; //Guardaremos la opcion del usuario
       ArrayList<Alumno> alumnos = new ArrayList<>();
       ArrayList<Profesor> profesores = new ArrayList<>();
       Curso eso1=new Curso(1, "ESO");
       Curso eso2=new Curso(2, "ESO");
       ArrayList <Asignatura> p1 = new ArrayList<>();
       p1.add(new Asignatura(eso2, "Matemáticas"));
       p1.add(new Asignatura(eso2, "Tecnología"));
       p1.add(new Asignatura(eso1, "Matemáticas"));
       p1.add(new Asignatura(eso1, "Tecnología"));       
// me creo unos cuantos profes y alumnos
        for (int i=1;i<10;i++){
            alumnos.add(new Alumno(eso1, "nombre "+i, "Apellido"+i, "Secape"+i,
                    i));
//            profesores.add(new Profesor(p1,"profe"+i, "profape1"+i, 
//                    "profesecape"+i));
        }
        
       while(!salir){
            
           System.out.println("1. Opcion 1 - Obtener alumnos");
         
           System.out.println("2. Salir");
            
           System.out.println("Escribe una de las opciones");
           opcion = sn.nextInt();
           switch(opcion){
               case 1:
                   System.out.println("Has seleccionado la opcion 1 - "
                           + "Obtener alumnos");
                   obtenerAlumnos(alumnos);
                   break;
                case 2:
                   salir=true;
                   break;
                default:
                   System.out.println("Solo números entre 1 y 2");
           }           
            
       }
    }
    public static void obtenerAlumnos(ArrayList<Alumno> al){
        Scanner lector=new Scanner(System.in);
        System.out.println("======================");
        System.out.println("LOCALIZADOR DE ALUMNOS ");
        System.out.println("----------------------");
        System.out.println("Dime el numero de id del alumno");
        int id=lector.nextInt();
        boolean encontrado=false;
        int i = 0;
        while ((i < al.size()) && (encontrado==false)) {
            if (al.get(i).getId()==id){
                System.out.println("El alumno con id " + id +
                        " ha sido localizado.");
                al.get(i).mostrarDatos();
                encontrado=true;                
            }else i++;            
        }
        if (encontrado==false){
            System.out.println("El alumno no existe");
        }                
    }
}
