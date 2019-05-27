/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen3evaluacion;

/**
 *
 * @author Rafael
 */
public class Excepcion2 {
    public static void main(String[] args) {
        int array[]=new int[5];
        try {
            
            for (int i=0; i<7;i++){
                System.out.println("hola"+array[i]);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error:"+e.getMessage());   
            System.out.println();
        }

        
    }
    
}
