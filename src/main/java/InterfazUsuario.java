import java.io.IOException;
import java.util.Scanner;

/**
 *Clase la cual se encarga de las interacciones con el usuario.
 *Esta clase se inicializa con parámetros definidos en la linea de comandos al ejecutarse el programa
 *Contiene la función que crea el menú legible, el cual tiene numerosas funciones: Te permite agregar una receta,
 * Consultar, editar o borrar una receta que elija el ususario, Editar el planificador semanal, guardar un
 * archivo con todas las recetas y cargar un archivo que añade las recetas. Además puede guardar el Plan Semanal
 * en un archivo de texto para su lectura y uso.
 */
public class InterfazUsuario {
    private LibroDeRecetas libroDeRecetas;
    private PlanificadorSemanal planificador;
    private final int maxIngredientes;
    private final int maxInstrucciones;

    /**
     * Función que incializa los parametros de la clase que recibe en la linea de comandos al ejecutarse el programa.
     *Además inicializa el Libro de Recetas.
     * @param maxIngredientes Cantidad máxima de ingredientes que puede tener cada receta.(int)
     * @param maxInstrucciones Cantidad máxima de istrucciones que puede tener cada receta.(int)
     * @param maxRecetasEnLibro Cantidad máxima de recetas que puede guardar el Libro de Recetas.(int)
     */
    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro) {
        libroDeRecetas=new LibroDeRecetas(maxRecetasEnLibro);
        this.maxIngredientes=maxIngredientes;
        this.maxInstrucciones=maxInstrucciones;
    }

    /**
     * Función que incializa los parametros de la clase que recibe en la linea de comandos al ejecutarse el programa,
     * la cual contiene el nombre de un archivo desde el que cargar sus recetas.
     * Además inicializa el Libro de Recetas.
     * @param maxIngredientes Cantidad máxima de ingredientes que puede tener cada receta.(int)
     * @param maxInstrucciones Cantidad máxima de istrucciones que puede tener cada receta.(int)
     * @param maxRecetasEnLibro Cantidad máxima de recetas que puede guardar el Libro de Recetas.(int)
     * @param archivoRecetas Nombre del archivo del cual se cargan las recetas.(String)
     */
    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro, String archivoRecetas) {
        libroDeRecetas=new LibroDeRecetas(maxRecetasEnLibro);
        this.maxIngredientes=maxIngredientes;
        this.maxInstrucciones=maxInstrucciones;
        try {
            libroDeRecetas.cargarRecetasDeArchivo(archivoRecetas, maxIngredientes, maxInstrucciones);
        } catch (IOException e) {
            System.out.println("Error al cargar las recetas");
        }
        iniciar();
    }

    /**
     * Función que inicializa el Scanner, la clase del Planificador semanal y ejecuta la función del menú principal.
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        planificador=new PlanificadorSemanal();
        menuPrincipal(scanner);
        scanner.close();
    }

    /**
     * Función que se encarga de imprimir el menú principal y recibiendo la entrada del usuario invoca la función
     * deseada.
     * @param scanner Scanner, el cual se utiliza en el todas las funciones que ejecuta esta función.
     */
    private void menuPrincipal(Scanner scanner) {
        int opcion;
        do {
            System.out.println("--- Menú Principal ---");
            System.out.println("1. Agregar Receta");
            System.out.println("2. Consultar/Editar Receta");
            System.out.println("3. Planificar Comidas");
            System.out.println("4. Guardar Recetas");
            System.out.println("5. Cargar Recetas");
            System.out.println("6. Guardar Plan Semanal");
            System.out.println("7. Salir\n");
            opcion = Utilidades.leerNumero(scanner, "Elige una opción: ", 1, 7);
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    agregarReceta(scanner);
                    break;
                case 2:
                    consultarReceta(scanner);
                    break;
                case 3:
                    planificarComidas(scanner);
                    break;
                case 4:
                    guardarRecetas(scanner);
                    break;
                case 5:
                    cargarRecetas(scanner);
                    break;
                case 6:
                    guardarPlanSemanal(scanner);
                    break;
                default:
                    break;
            }
        }while(opcion!=7);
    }

    /**
     * Función que recibe la entrada del usuario para crear una receta. Esto incluye un nombre, ingredientes e
     * instrucciones elegidas por el usuario.
     * @param scanner Scanner necesario para ejecutar las funciones.
     */
    private void agregarReceta(Scanner scanner) {
        Receta receta=new Receta(Utilidades.leerCadena(scanner,"Nombre de la receta: "),maxIngredientes,maxInstrucciones);
        System.out.println("Introduce los ingredientes (una línea por ingrediente, escribe 'fin' para terminar):");
        String ingredienteinstruccion;
        do{
            ingredienteinstruccion=Utilidades.leerCadena(scanner,"");
        }while(!ingredienteinstruccion.equalsIgnoreCase("fin")&&receta.agregarIngrediente(ingredienteinstruccion));
        System.out.println("Introduce las instrucciones (una línea por instrucción, escribe 'fin' para terminar): ");
        do{
            ingredienteinstruccion=Utilidades.leerCadena(scanner,"");
        }while(!ingredienteinstruccion.equalsIgnoreCase("fin")&&receta.agregarInstruccion(ingredienteinstruccion));
        if(libroDeRecetas.agregarReceta(receta))
            System.out.println("¡Receta agregada exitosamente!");
    }

    /**
     * Función que ejecuta la función que busca recetas según un String que escribe el usuario, una vez elegida la
     * receta, la imprime y despues invoca la función que permite al usuario editar la receta.
     * @param scanner Scanner necesario para ejecutar las funciones.
     */
    private void consultarReceta(Scanner scanner) {
       Receta receta= buscarRecetaPorNombre(scanner);
       if(receta!=null) {
           System.out.println(receta.toString());
           editarReceta(scanner, receta);
       }
    }


    /**
     * Función busca recetas cuyos nombres coincidan con la entrada del usuario(un String), después envia el array de
     * las recetas encontradas a la función seleccionarReceta, la cual permite al usuario escoger una receta la cual
     * será lo que devuelve la función original.
     * @param scanner Scanner necesario para ejecutar las funciones.
     * @return Devuelve una receta de la clase Receta, que coincide con el criterio de busqueda y es elegida por el
     * usuario.
     */
    private Receta buscarRecetaPorNombre(Scanner scanner) {
        Receta receta=null;
            String cadena = Utilidades.leerCadena(scanner, "Introduce el texto de la receta a buscar (-FIN- para volver): ");
            boolean recetasVacias=true;
            if (!(cadena).equalsIgnoreCase("FIN")) {
                Receta [] recetasEncontradas = libroDeRecetas.buscarRecetaPorNombre(cadena);
                for (int i=0;i<recetasEncontradas.length;i++){
                    if(recetasEncontradas[i]!=null){
                        recetasVacias=false;
                    }
                }
                if(recetasVacias)buscarRecetaPorNombre(scanner);
                else {
                    receta= seleccionarReceta(scanner, recetasEncontradas);
                }
            }
            return receta;
    }

    /**
     * Función que permite al susuario editar una receta seleccionada, con la posibilidad de añadir un ingredente,
     * añadir una instrucción o borrar tda la receta.
     * @param scanner Scanner necesario para ejecutar las funciones.
     * @param seleccionada Receta que fue elegida por el usuario para ser editada.
     */
    private void editarReceta(Scanner scanner, Receta seleccionada) {
        int opcion;
            System.out.println("1. Añadir ingrediente");
            System.out.println("2. Añadir instrucción");
            System.out.println("3. Eliminar receta");
            System.out.println("4. Volver");
            opcion=Utilidades.leerNumero(scanner,"Elige una opción: ",1,4);
            scanner.nextLine();
            switch (opcion){
                case 1: String Ingrediente=Utilidades.leerCadena(scanner,"Introduce el ingrediente a añadir: ");
                    seleccionada.agregarIngrediente(Ingrediente);
                    break;
                case 2:String Instruccion=Utilidades.leerCadena(scanner,"Introduce la instrucción a añadir: ");
                seleccionada.agregarInstruccion(Instruccion);
                    break;
                case 3:libroDeRecetas.eliminarReceta(seleccionada);
                System.out.println("Receta eliminada.");
                break;
                default:
            }
    }

    /**
     * Función que recibe un array con las recetas cuyos nombres coinciden con la entrada del usuario, la cual imprime
     * el nombre de todas esas recetas para que el usuario pueda elegir cual de ellas quiere editar.
     * @param scanner Scanner necesario para ejecutar las funciones.
     * @param recetas Array que contiene todas las recetas encontradas por la funcion buscarRecetaPorNombre
     * @return Devuelve la receta elegida por el usuario.
     */
    private Receta seleccionarReceta(Scanner scanner, Receta[] recetas) {
        int numRecetasEncontradas=0;
        System.out.println("Recetas encontradas:");
        for (int i = 0; i < recetas.length; i++) {
            Receta receta = recetas[i];
            if(recetas[i]!=null){
            System.out.println((i + 1) + ". " + receta.getNombre());
            numRecetasEncontradas++;}
        }
        int recetaElegida = Utilidades.leerNumero(scanner, "Elige una receta: ", 1, numRecetasEncontradas);
        return recetas[(recetaElegida-1)];
    }

    /**
     * Función que permite al usuario editar el plan semanal, añadiendo comidas un dia de la semana o sustituyendo
     * existentes comidas por otras.
     * @param scanner Scanner necesario para ejecutar las funciones.
     */
    private void planificarComidas(Scanner scanner) {
        System.out.println("Planificación de comidas para la semana:");
        System.out.println(planificador.toString());
        int intdia=Utilidades.leerDiaDeLaSemana(scanner,"Introduce el día de la semana (L, M, X, J, V, S, D): ");
        Receta receta=buscarRecetaPorNombre(scanner);
        planificador.agregarComida(intdia,receta);
        System.out.println("Receta planificada para "+Utilidades.posicionADiaSemana(intdia));
    }

    /**
     * Función que guarda la información de todas las recetas para que puedan ser cargadas más tarde.
     * @param scanner Scanner necesario para ejecutar las funciones.
     */
    private void guardarRecetas(Scanner scanner) {
       String nombreArchivo=Utilidades.leerCadena(scanner, "Introduce el nombre del archivo donde guardar las recetas: ");
       try {
           libroDeRecetas.guardarRecetasEnArchivo(nombreArchivo);
       }catch (IOException e) {
           System.out.println("Error al guardar el archivo.");
       }
    }

    /**
     * Función que lee un archivo de texto y carga las recetas que se encuentren en este y envia un mensaje de error
     * si no se pudo cargar el archivo o si el archivo no existe.
     * @param scanner Scanner necesario para ejecutar las funciones.
     */
    private void cargarRecetas(Scanner scanner) {
        String nombreArchivo=Utilidades.leerCadena(scanner,"Introduce la ruta del archivo de donde cargar las recetas: ");
        try {
            libroDeRecetas.cargarRecetasDeArchivo(nombreArchivo, maxIngredientes, maxIngredientes);
        }catch (IOException e){
            System.out.println("Error al cargar el archivo");
        }
    }

    /**
     * Guarda el plan semanal en una archivo legible, para el uso propio del usuario.
     * Devuelve un mensaje de error si no se pudo guardar el archivo.
     * @param scanner Scanner necesario para ejecutar las funciones.
     */
    private void guardarPlanSemanal(Scanner scanner) {
        String nombreArchivo=Utilidades.leerCadena(scanner,"Introduce el nombre del archivo donde guardar el plan semanal: ");
        try {
            planificador.guardarPlanEnArchivo(nombreArchivo);
            System.out.println("Plan semanal guardado en "+nombreArchivo);
        }catch(IOException e){
            System.out.println("Error al guardar el archivo.");
        }
    }
}
