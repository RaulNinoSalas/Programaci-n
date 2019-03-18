/*
 * El objetivo del siguiente programa es presentar varios ejemplos de distintos
 * usos de las excepciones
 */
package ejemplosexcepciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafae
 */
public class EjemplosExcepciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        /* Tal y como hemos visto, podemos distinguir entre excepciones controladas 
        (checked) y no
        controladas (unchecked)
         */
        while (!salir) {

            System.out.println("1. Opcion 1 - Excepción no controlada");
            System.out.println("2. Opcion 2 - Excepción controlada");
            System.out.println("3. Opcion 3 - Excepción manual");
            System.out.println("4. Opcion 4 - Nueva excepción");
            System.out.println("5. Salir");

            System.out.println("Escribe una de las opciones");
            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    excepcion1();
                    break;
                case 2:
                    excepcion2();
                    break;
                case 3:
                    //como vemos genera una excepcion InputMismatchException
                    // que como es unchecked no nos obliga, pero deberíamos
                    excepcion3();
                    try {
                        System.out.println("introduce un intro y verás lo que pasa");
                        excepcion3();
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                    //IMPORTANTE, SI NO TENEMOS CLARO QUE TIPO DE EXCEPCIÓN DEBEMOS PONER
                    // PONEMOS DE TIPO EXCEPTION, MEJOR GENERALIZAR QUE CONCRETAR INCORRECTAMENTE
                    break;
                case 4: {
                    try {
                        excepcion4();
                    } catch (menorEdad ex) {
                        Logger.getLogger(EjemplosExcepciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }

        }
    }

    public static void excepcion1() {
        situacion1();
        situacion2();
    }

    public static void situacion1() {
        Scanner sc = new Scanner(System.in);
        // qué ocurre si un usuario no introduce el valor que esperamos?
        //podríamos dar una excepción informando del problema para poder continuar 
        System.out.println("Dime tu nombre");
        String nombre = sc.nextLine();
        //esto es lo que hacemos actualmente, es decir, no controlamos nada,
        //por tanto, si se produce una excepción se para el programa
        // es este caso se haces caso al texto y en lugar de introducir una edad (un número)
        // introduces una cadena se produce la excepción indicada
        
        System.out.println("Y tu edad ¿qué pasa si introduces una cadena?");
        System.out.println("Dará una excepción InputMismatchException");
        int edad = sc.nextInt();

    }

    public static void situacion2() {
        Scanner sc = new Scanner(System.in);
        // qué ocurre si un usuario no introduce el valor que esperamos
        //podríamos dar una excepción informando del problema para poder continuar 
        //pero en esta ocasión controlaremos la excepción. Ojo, no es una excepción
        //que estemos obligados a controlarla, pero que obviamente como buenos
        //programadores que somos lo haremos
        System.out.println("Dime tu nombre");
        String nombre = sc.nextLine();
        int edad = introducirEdad();

    }

    public static int introducirEdad() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Y tu edad ¿qué pasa si introduces una cadena?");
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Has introducido un valor incorrecto. Por favor debes introducir");
            System.out.println("Incluso podemos hacer recursividad");
            return introducirEdad();
        }
    }

    public static void excepcion2() {
        controlada1();
        try {
            // aquí podríamos hacer lo mismo o elevarla o controlarla en el mismo sitio
            controlada2();
        } catch (FileNotFoundException ex) {
            System.out.println("es fuera del método que llama y vuelve a ocurrir otra excepción");
            System.out.println("en este caso se trata fuera");
            Logger.getLogger(EjemplosExcepciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void controlada1() {
        //las excepciones controladas nos obligan a controlarlas y hacer algo con ellas
        //por ejemplo en el caso de los ficheros generan excepciones de tipo ioexception
        // y éstas tal y como hemos visto en la teoría son checked, luego hay que controlarlas

        File archivo2 = new File("D:\\Users\\Rafael\\Documents\\entrada2.txt");
        FileReader fr = null;
        try {
            // Apertura del fichero
            File archivo = new File("D:\\Users\\Rafael\\Documents\\entrada2.txt");
            fr = new FileReader(archivo);
        } catch (Exception e) {
            //en este caso la capturamos con exception que es más general
            // pero podríamos haber afinado más e incluir IOException
            // pe
            System.out.println("imprimimos pila de ejecución");
            e.printStackTrace();//imprime pila de ejecución            
        }
    }

    public static void controlada2() throws FileNotFoundException {
        //este método es el mismo que controlada1, pero es para que veáis que podemos
        // elegir entre controlar la excepcion en el mismo momento o elevar esta hacia arriba
        // y que sea el llamante o llamantes quien estén obligados a controlarla

        File archivo = new File("D:\\Users\\Rafael\\Documents\\entrada2.txt");
        FileReader fr = new FileReader(archivo);
    }

    public static void leerFichero(BufferedReader br, PrintWriter pw) throws NumberFormatException, IOException {
        // Lectura del fichero
        String linea;
        while ((linea = br.readLine()) != null) {
            String vector[] = linea.split(" ");
            pw.println("El número " + vector[0]);
        }
    }

    public static void cerrarFichero(FileReader fr) {
        // En el finally cerramos el fichero, para asegurarnos
        // que se cierra tanto si todo va bien como si salta
        // una excepcion.
        try {
            if (null != fr) {
                fr.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void excepcion3() throws InputMismatchException {
        //otra situación que se puede dar es que queramos general una excepcion
        // manualmente ¿qué quiere decir esto? como lo hacemos?
        //esto nos ayuda a que si alguien utiliza este método, le estamos indicando
        //que debería ser conveniente capturar la excepcion y tratarla
        //lo vemos

        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu nombre");
        String nombre = sc.nextLine();
        if (nombre.length() == 0) {
            throw new InputMismatchException("no se ha introducido el  nombre");
        }
    }

    public static void excepcion4() throws menorEdad {
        //hacemos uso de la nueva excepción que nos hemos creado
        // en caso de que la edad sea menor de 18 proporcionaremos la excepción

        Scanner sc = new Scanner(System.in);
        System.out.println("¿Qué edad tienes?");
        int edad = sc.nextInt();
        if (edad < 18) {
            throw new menorEdad("La edad introducida " + edad + " es menor que 18, y eso no es posible en esta aplicación");
        }
    }
}

class menorEdad extends Exception {

    public menorEdad() {
    }

    public menorEdad(String mensaje) {
        super(mensaje);
    }

}
