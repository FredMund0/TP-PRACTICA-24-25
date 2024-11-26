import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase con métodos de utilidad para la entrada de datos por teclado.
 */
public class Utilidades {


    /**
     * Función cuya función es pedir una cadena mediane un mensaje pidiendola e introduciendola por teclado
     * @param teclado Declaración del Scanner teclado para poder escribir mediante teclado una cadena
     * @param s Variable de clase string que sirve como mensaje introductorio para leer una nueva línea
     * @return Devuelve la cadena de texto que ha sido pedida por el mensaje escrito
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.println(s);
        String cadena = teclado.nextLine();
        // Muestra un mensaje y lee una cadena por teclado
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
                    System.out.println(mensaje);
                    num = teclado.nextInt();
                    if (num > maximo || num < minimo) {
                        System.out.println("El número debe ser menor que " + maximo + " y mayor que " + minimo);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("No es un número");
                }
            } while (num < minimo || num > maximo);
        // Muestra un mensaje y lee un número por teclado si no es un número vuelve a solicitar uno
        return num;
    }

    /**
     * Función que pideun día de la semana mediante un mensaje y pasa de el día de la semana introducido a su posición en la semana, es decir, L->0, M->1 y así con todos
     * @param teclado Declaración del Scanner teclado para poder escribir mediante teclado un número
     * @param mensaje Variable de clase string que sirve como mensaje introductorio para pedir un día de la semana por su letra representativa
     * @return Devuelve la posición en la semana del día que se ha intorducido anteriormente
     */
    public static int leerDiaDeLaSemana(Scanner teclado, String mensaje) {
        int dias =-1;
        do{
        System.out.println(mensaje);
        String letra = teclado.nextLine();
        switch (letra) {
            case "L":
                dias = 0;
                break;
            case "M":
                dias = 1;
                break;
            case "X":
                dias = 2;
                break;
            case "J":
                dias = 3;
                break;
            case "V":
                dias = 4;
                break;
            case "S":
                dias = 5;
                break;
            case "D":
                dias = 6;
                break;

        }
        }while (dias == -1);
        // Muestra un mensaje, lee un día de la semana por teclado (L, M, X, J, V, S, D) y devuelve su posición
        // dentro de la semana (0-6)
        return dias; // @todo MODIFICAR PARA DEVOLVER EL DÍA DE LA SEMANA LEÍDO
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
        // Devuelve la posición de un día de la semana (L, M, X, J, V, S, D) dentro de la semana (0-6)
        return posicion; // @todo MODIFICAR PARA DEVOLVER LA POSICIÓN DEL DÍA DE LA SEMANA
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
                    System.out.println("No hay ningún día asociado a ese número");
                    break;
            }

        // Devuelve el día de la semana (Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo)
        // correspondiente a una posición dentro de la semana (0-6)
        return dia; // @todo MODIFICAR PARA DEVOLVER EL DÍA DE LA SEMANA
    }
}
