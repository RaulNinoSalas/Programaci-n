/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conecta4;

import java.util.Scanner;

/**
 *
 * @author rafae
 */
public class Conecta4 {

    private static final int Tamfil = 6;
    public static final int Tamcol = 7;
    public static char[][] tablero = new char[Tamfil][Tamcol];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            menu();
        } catch (Exception ex) {
            System.out.println("Error no controlado" + ex.getMessage());
        }
    }

    public static void menu() throws Exception {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;

        int opcion;
        while (!salir) {

            System.out.println("1. Opcion 1 - Jugar a Conecta 4");
            System.out.println("2. Opcion 2 - Salir");

            System.out.println("Escribe una de las opciones");
            try {
                opcion = sn.nextInt();
            } catch (Exception excepcion) {
                System.out.println("Por favor, introduz"
                        + "ca un valor numérico.");
                System.out.println("Excepcion:" + excepcion.getMessage()
                        + excepcion.getStackTrace());
                sn.next();
                opcion = 0;
            }
            switch (opcion) {
                case 1:
                    jugarConecta4();
                    ;
                    break;
                case 2:
                    salir = true;
                default:
                    System.out.println("Adios");
            }
        }
    }

    public static void jugarConecta4() {
        String jugrojo = pedirJugadores("Rojo");
        String jugamarillo = pedirJugadores("Amarillo");
        System.out.println("Bienvenidos comienza la partida");
        iniciarTablero();
        boolean salir = false;
        String turno = jugamarillo;
        while (!salir) { //dejaremos de jugar si gana uno de los dos o acaban en empate
            mostrarTablero();
            turno = cambiarTurno(turno, jugrojo, jugamarillo);
            salir = lanzarFicha(turno, jugrojo, jugamarillo);

        }
    }

    public static void iniciarTablero() {
        for (int i = 0; i < Tamfil; i++) {
            for (int j = 0; j < Tamcol; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public static String pedirJugadores(String color) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Dame el nombre del jugador " + color);
        return sn.nextLine();
    }
    public static void mostrarTablero(){
        for(int i=0;i<Tamfil;i++){
            System.out.println("-----------------");
            for(int j=0;j<Tamcol;j++){
                System.out.print("|"+tablero[i][j]);
                if (j==Tamcol-1){
                    System.out.println("|");
                }
            }
        }
    }
    public static String cambiarTurno(String turno, String jugrojo, String jugamarillo) {
        if (turno.equals(jugrojo)) {
            System.out.println("Es el turno del jugador: " + jugamarillo);
            return jugamarillo;
        } else {
            System.out.println("Es el turno del jugador: " + jugrojo);
            return jugrojo;
        }
    }

    public static boolean lanzarFicha(String turno, String jugrojo, String jugamarillo) {
        System.out.println("¿Por qué columna quieres dejar caer la ficha?");
        Scanner sn = new Scanner(System.in);
        int columna = sn.nextInt();
        int fila;
        while (tablero[0][columna] != ' ') {//si pasa esta situación es no hay hueco en la columna seleccionada y hay que volver a pedir otra columna
            System.out.println("Aviso: la columna " + columna + "está completamente ocupada");
            System.out.println("¿Por qué columna quieres dejar caer la ficha?");
            columna = sn.nextInt();
        }
        // si llegamos aquí es que ha seleccionado una columna que hay espacio, lo que no sabemos es donde la podemos dejar la ficha
        fila = ubicarFicha(columna, turno, jugrojo, jugamarillo);
        // ahora tenemos que validar si alguien a aca
        return validarPartida(fila, columna, turno);//si a partir de una fila o una columna se ha acabado la partida
        //devolverá cierto si ha acabado y falsa en caso contrario
    }

    public static int ubicarFicha(int columna, String turno, String jugrojo, String jugamarillo) {
        boolean encontrado = false;
        int fila = 5;
        char color = 'R';
        if (turno.equals(jugamarillo)) {
            color = 'A';
        }
        while (!encontrado) {
            if (tablero[fila][columna] == ' ') {
                tablero[fila][columna] = color;
                encontrado = true;
            } else {
                fila--;//hay que subir hacia arriba porque está ocupada
            }
        }
        return fila;//devolvemos la fila
    }

    public static boolean validarPartida(int fila, int columna,String turno) {
        //puede acabar por dos motivos
        //una porque se ha hecho 4 en raya
        boolean resultado = false;
        resultado = validar4enRaya(fila, columna);
        if (resultado){
            System.out.println("FELICIDADES, HA GANADO EL JUGADOR " + turno);
        }
        else{ // se pueden colocar más fichas?
            int i=0;
            while (tablero[0][i]!=' ' && i<Tamcol){
                if (tablero[0][i]==' '){
                    resultado=true;
                }
                i++;
            }            
        }
        return resultado;
    }

    public static boolean validar4enRaya(int fila, int columna) {
        //acaba de colocar la posición [fila][columna], ha hecho 4 en raya?
        //validamos en vertical
        boolean resultado = false;
        resultado = validarVertical(fila, columna);
        if (!resultado) {//si no ha ganado todavía entonces validamos horizontalmente
            resultado = validarHorizontal(fila, columna);
            System.out.println("horizontal "+ resultado);
        }
        if (!resultado) {//si no ha ganado todavía entonces validamos horizontalmente
            resultado = validarDiagonal(fila, columna);
            System.out.println("diagonal "+ resultado);            
        }
        return resultado;
    }

    public static boolean validarVertical(int fila, int columna) {
        char color = tablero[fila][columna];
        boolean resultado = false;
        if (fila < 3) {//en la validación validación vertical solo es posible a partir de la fila 3= filas-> (0,1,2,3)
            if (tablero[fila + 1][columna] == color
                    && tablero[fila + 2][columna] == color
                    && tablero[fila + 3][columna] == color) {
                resultado = true;
            }
        }
        return resultado;
    }

    public static boolean validarHorizontal(int fila, int columna) {
        char color = tablero[fila][columna];
        boolean resultado = false;
        int contador = 0;
        //casi que lo más sencillo es recorrer de principio a fin todas las columnas e ir contando
        mostrarTablero();
        for (int i = 0; i < Tamcol; i++) {
            if (tablero[fila][i] == color) {                
                contador++;
                if (contador == 4) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }
        return resultado;
    }

    public static boolean validarDiagonal(int fila, int columna) {
        char color = tablero[fila][columna];
        boolean resultado = false;
        int contador = 0;
        //casi e ir contando
        for (int i = Tamfil - 1; i > 2; i--) {// diagonal primaria
            for (int j = 3; j < Tamcol; j++) {
                contador = 0;
                for (int k = 0; k < 4; k++) {
                    if (tablero[i - k][j - k] == color) {
                        contador++;
                        if (contador == 4) {
                            return true;
                        }
                    }
                }

            }
        }
        for (int i = Tamfil - 1; i > 2; i--) {//diagonal secundaria
            for (int j = 0; j<Tamcol - 3; j++) {
                contador = 0;
                for (int k = 0; k < 4; k++) {
                    if (tablero[i-k][j+k] == color) {
                        contador++;
                        if (contador == 4) {
                            return true;
                        }
                    }
                }
            }
        }
        return resultado;
    }
}
