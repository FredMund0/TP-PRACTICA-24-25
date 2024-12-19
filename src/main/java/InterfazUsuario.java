import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 *Clase la cual se encarga de
 */
public class InterfazUsuario {
    private LibroDeRecetas libroDeRecetas;
    private PlanificadorSemanal planificador;
    private final int maxIngredientes;
    private final int maxInstrucciones;

    /**
     * Función que incializa los parametros de la clase que recibe en la linea de comandos al ejecutarse el programa.
     *Además inicializa el Libro de Recetas y el Planificador Semanal
     * @param maxIngredientes Cantidad máxima de ingredientes que puede tener cada receta.(int)
     * @param maxInstrucciones Cantidad máxima de istrucciones que puede tener cada receta.(int)
     * @param maxRecetasEnLibro Cantidad máxima de recetas que puede guardar el Libro de Recetas.(int)
     */
    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro) {
        // Inicialización de la herramienta de recetas
        libroDeRecetas=new LibroDeRecetas(maxRecetasEnLibro);
        this.maxIngredientes=maxIngredientes;
        this.maxInstrucciones=maxInstrucciones;
        planificador=new PlanificadorSemanal();
    }

    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro, String archivoRecetas) {
        this(maxIngredientes, maxInstrucciones, maxRecetasEnLibro);
        try {
            libroDeRecetas.cargarRecetasDeArchivo(archivoRecetas, maxIngredientes, maxInstrucciones);
        } catch (IOException e) {
            System.out.println("Error al cargar las recetas");
        }
        iniciar();
        // Cargar las recetas predefinidas al iniciar la aplicación
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
        scanner.close();
    }

    private void menuPrincipal(Scanner scanner) {
        // Muestra el menú principal y gestiona la entrada del usuario para dirigirlo a la opción seleccionada
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

    private void agregarReceta(Scanner scanner) {
        // Solicita al usuario los datos de la receta y la añade al libro de recetas
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

    private void consultarReceta(Scanner scanner) {
        // Busca una receta por su nombre y activa el menú de edición
       Receta receta= buscarRecetaPorNombre(scanner);
       if(receta!=null) {
           System.out.println(receta.toString());
           editarReceta(scanner, receta);
       }
        }


    private Receta buscarRecetaPorNombre(Scanner scanner) {
        // Solicita al usuario un texto para buscar y seleccionar una receta por su nombre
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
                    return seleccionarReceta(scanner, recetasEncontradas);
                }
            }
            return null;
    }

    private void editarReceta(Scanner scanner, Receta seleccionada) {
        // Pantalla de edición de receta
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

    private Receta seleccionarReceta(Scanner scanner, Receta[] recetas) {
        // Muestra las recetas encontradas y solicita al usuario que elija una
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

    private void planificarComidas(Scanner scanner) {
        // Inicia el proceso de planificación de comidas
        System.out.println("Planificación de comidas para la semana:");
        System.out.println(planificador.toString());
        int intdia=Utilidades.leerDiaDeLaSemana(scanner,"Introduce el día de la semana (L, M, X, J, V, S, D): ");
        Receta receta=buscarRecetaPorNombre(scanner);
        planificador.agregarComida(intdia,receta);
        System.out.println("Receta planificada para "+Utilidades.posicionADiaSemana(intdia));
    }

    private void guardarRecetas(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y guarda las recetas en ese archivo
       String nombreArchivo=Utilidades.leerCadena(scanner, "Introduce el nombre del archivo donde guardar las recetas: ");
       try {
           libroDeRecetas.guardarRecetasEnArchivo(nombreArchivo);
           System.out.println("Recetas guardadas en "+nombreArchivo);
       }catch (IOException e) {
           System.out.println("Error al guardar el archivo.");
       }
    }

    private void cargarRecetas(Scanner scanner) {
        String nombreArchivo=Utilidades.leerCadena(scanner,"Introduce la ruta del archivo de donde cargar las recetas: ");
        try {
            libroDeRecetas.cargarRecetasDeArchivo(nombreArchivo, maxIngredientes, maxIngredientes);
            System.out.println("Recetas cargadas desde "+nombreArchivo);
        }catch (IOException e){
            System.out.println("Error al cargar el archivo");
        }
        // Solicita al usuario un nombre de archivo y carga las recetas desde ese archivo
    }
    private void guardarPlanSemanal(Scanner scanner) {
        // Solicita al usuario un nombre de archivo y guarda el plan semanal en ese archivo
        String nombreArchivo=Utilidades.leerCadena(scanner,"Introduce el nombre del archivo donde guardar el plan semanal: ");
        try {
            planificador.guardarPlanEnArchivo(nombreArchivo);
            System.out.println("Plan semanal guardado en "+nombreArchivo);
        }catch(IOException e){
            System.out.println("Error al guardar el archivo.");
        }
    }
}
