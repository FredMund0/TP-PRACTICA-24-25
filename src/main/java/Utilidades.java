import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase con métodos de utilidad para la entrada de datos por teclado.
 */
public class Utilidades {
    public static String leerCadena(Scanner teclado, String s) {
        System.out.println(s);
        String cadena = teclado.nextLine();
        // Muestra un mensaje y lee una cadena por teclado
        return cadena;
    }

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

    public static int diaSemanaAPosicion(String dia) {
        int posicion = -1;
        do {
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

            }
        }while(posicion == -1);
        // Devuelve la posición de un día de la semana (L, M, X, J, V, S, D) dentro de la semana (0-6)
        return posicion; // @todo MODIFICAR PARA DEVOLVER LA POSICIÓN DEL DÍA DE LA SEMANA
    }

    public static String posicionADiaSemana(int pos) {
        String dia = "a";
        do {
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

            }
        }while(dia.equals("a"));
        // Devuelve el día de la semana (Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo)
        // correspondiente a una posición dentro de la semana (0-6)
        return dia; // @todo MODIFICAR PARA DEVOLVER EL DÍA DE LA SEMANA
    }


}
