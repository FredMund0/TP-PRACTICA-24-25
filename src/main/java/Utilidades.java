import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase con métodos de utilidad para la entrada de datos por teclado.
 */
public class Utilidades {


    /**
     * Función cuyo objetivo es pedir una cadena mediane un mensaje pidiendola e introduciendola por teclado
     * @param teclado Declaración del Scanner teclado para poder escribir mediante teclado una cadena
     * @param s Variable de clase string que sirve como mensaje introductorio para leer una nueva línea
     * @return Devuelve la cadena de texto que ha sido pedida por el mensaje escrito
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        String cadena = teclado.nextLine();
        return cadena;
    }


    /**
     * Función que pide con un mensaje que se introduzca un número dentro de unos parámetros ya establecidos y que se pide hasta que se haya introducido un número dentro de los parámetros
     * @param teclado Declaración del Scanner teclado para poder escribir mediante teclado un número
     * @param mensaje Variable de clase string que sirve como mensaje introductorio para pedir un número
     * @param minimo Es el parametro mínimo aceptado para el número que se pide introducir
     * @param maximo Es el parametro máximo aceptado para el número que se pide introducir
     * @return Devuelve un número que se tiene que encontrar dentro de los parámetros establecidos anteriormente por el máximo y el mínimo
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int num = -1;
            do {
                try{
                    System.out.print(mensaje);
                    num = teclado.nextInt();
                    if (num > maximo || num < minimo) {
                        System.out.println("El número debe ser menor que " + maximo + " y mayor que " + minimo);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("No es un número");
                    teclado.nextLine();
                }
            } while (num < minimo || num > maximo);
        return num;
    }

    /**
     * Función que pide un día de la semana mediante un mensaje y obtiene la posición en la semana correspondiente al día introducido, es decir, L->0, M->1 y así con todos,
     * o repite el mensaje hasta que se introduzca un día válido de la semana. Todo con ayuda de la función diaSemanaPosicion
     * @param teclado Declaración del Scanner teclado para poder escribir mediante teclado un número
     * @param mensaje Variable de clase string que sirve como mensaje introductorio para pedir un día de la semana por su letra representativa
     * @return Devuelve la posición en la semana del día que se ha intorducido anteriormente
     */
    public static int leerDiaDeLaSemana(Scanner teclado, String mensaje) {
        int posicion;
        do{
        System.out.println(mensaje);
        posicion = diaSemanaAPosicion(teclado.nextLine());
        }while (posicion == -1);
        return posicion;
    }


    /**
     * Funcion a la cual le llega un día de la semana y cambia este por su posición en la semana,es decir, L->0, M->1 y así con todos
     * @param dia Variable de tipo String que debe ser comparada a ver si coincide o no con algún día y si es así cambiar por su posición en la semana
     * @return Devuelve la posicion de la semana asociada al día de la semana introducido con anterioridad y si no coincide con ninguno lo comenta
     */
    public static int diaSemanaAPosicion(String dia) {
        int posicion = -1;
            switch (dia) {
                case "L":
                    posicion = 0;
                    break;
                case "M":
                    posicion = 1;
                    break;
                case "X":
                    posicion = 2;
                    break;
                case "J":
                    posicion = 3;
                    break;
                case "V":
                    posicion = 4;
                    break;
                case "S":
                    posicion = 5;
                    break;
                case "D":
                    posicion = 6;
                    break;
                default:
                    System.out.println("No coincide con ningún día de la semana.");
                    break;
            }
        return posicion;
    }

    /**
     * Funcion que compara una posición de los días dada como parámetro y lo identifica con el día de la semana correspondiente a esa posición de la semana
     * @param pos Es una variable numérica que debe ser una posición de los días de la semana para poder comparar y saber a cual
     * @return Devuelve el día de la semana al que corresponde la posición en la semana del parámetro
     */
    public static String posicionADiaSemana(int pos) {
        String dia = "a";
            switch (pos) {
                case 0:
                    dia = "Lunes";
                    break;
                case 1:
                    dia = "Martes";
                    break;
                case 2:
                    dia = "Miércoles";
                    break;
                case 3:
                    dia = "Jueves";
                    break;
                case 4:
                    dia = "Viernes";
                    break;
                case 5:
                    dia = "Sábado";
                    break;
                case 6:
                    dia = "Domingo";
                    break;
                default:
                    dia = "Desconocido";
                    break;
            }
        return dia;
    }
}
