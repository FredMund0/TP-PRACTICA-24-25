public class Main {
    /**
     * Función que inicializa todos los atributos necesarios para poder iniciar la clase interfazUsuario y saber diferenciar si
     * en los parámetros está el nombre de un archivo para cargar las recetas del archivo o si estamos creando un nuevo plan sin archivos anteriores.
     * @param args Es el array que contiene los parámetros con los que se va a ejecutar la función, de manera que se utilizan para poder
     *             inicializar los atributos del interfaz de usuario. Siendo en orden de los parámetros maxIngredientesPorReceta, maxInstruccionesPorReceta,
     *             maxRecetasEnLibro y opcionalmente nombreArchivoRecetas.
     */
    public static void main(String[] args) {
        int maxIngredientesPorReceta;
        int maxInstruccionesPorReceta;
        int maxRecetasEnLibro;

        String nombreArchivoRecetas = null;

        if (args.length < 3 || args.length > 4) {
            System.out.println("Se deben usar los parámetros: maxIngredientesPorReceta, maxInstruccionesPorReceta, maxRecetasEnLibro y opcionalmente nombreArchivoRecetas. ");
        }else{
            try{
                maxIngredientesPorReceta = Integer.parseInt(args[0]);
                maxInstruccionesPorReceta = Integer.parseInt(args[1]);
                maxRecetasEnLibro = Integer.parseInt(args[2]);
                System.out.println("Máximo de ingredientes por receta: " + maxIngredientesPorReceta);
                System.out.println("Máximo de instrucciones por receta: " + maxInstruccionesPorReceta);
                System.out.println("Máximo de recetas del libro de recetas: " + maxRecetasEnLibro);

                if (args.length == 3){
                    InterfazUsuario interfazUsuario = new InterfazUsuario(maxIngredientesPorReceta,maxInstruccionesPorReceta,maxRecetasEnLibro);
                    interfazUsuario.iniciar();
                }

                if (args.length == 4){
                    nombreArchivoRecetas = args[3];
                    InterfazUsuario interfazUsuario = new InterfazUsuario(maxIngredientesPorReceta,maxInstruccionesPorReceta,maxRecetasEnLibro,nombreArchivoRecetas);
                    interfazUsuario.iniciar();
                }
            }catch (NumberFormatException e){
                System.out.println("Los tres primeros parámetros tienen que se números.");
            }catch (Exception e){
                System.out.println("ERROR DESCONOCIDO");
            }
        }
    }
}

