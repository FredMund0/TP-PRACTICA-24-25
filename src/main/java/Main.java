public class Main {
    public static void main(String[] args) {
        int maxIngredientesPorReceta = 0;
        int maxInstruccionesPorReceta = 0;
        int maxRecetasEnLibro = 0;

        String nombreArchivoRecetas = null;

        if (args.length < 3 || args.length > 4) {
            System.out.println("Se deben usar los parámetros: maxIngredientesPorReceta, maxInstruccionesPorReceta, maxRecetasEnLibro y opcionalmente nombreArchivoRecetas. ");
        }else{
            try{
                maxIngredientesPorReceta = Integer.parseInt(args[0]);
                maxInstruccionesPorReceta = Integer.parseInt(args[1]);
                maxRecetasEnLibro = Integer.parseInt(args[2]);

                if (args.length == 4){
                    nombreArchivoRecetas = args[3];
                }
            }catch (NumberFormatException e){
                System.out.println("Los tres primeros parámetros tienen que se números.");
            }catch (Exception e){
                System.out.println("ERROR DESCONOCIDO");
            }
        }
        InterfazUsuario interfazUsuario = new InterfazUsuario(maxIngredientesPorReceta,maxInstruccionesPorReceta,maxRecetasEnLibro,nombreArchivoRecetas);
        // Comprueba los argumentos de la línea de comandos y lanza la interfaz de usuario
        //prueba

    }
}

