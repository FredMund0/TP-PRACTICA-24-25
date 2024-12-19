import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase se encarga de realizar todas las tareas que respectan a la organización de las
 * recetas a lo largo de la semana como agregar ciertas recetas que uno quiera para un día
 * de la semana. También puede crear un esquema de los días de la semana con sus respectivas
 * recetas para cada día y se puede guardar el plan semanal establecido en un archivo de
 * texto para posteriormente poder volver a abrirlo y tener el plan semanal de recetas.
 */
public class PlanificadorSemanal {
    private final Receta[] planificador;
    private Receta receta;
    private int dia;

    /**
     * Constructor que inicializa tanto el array que va a contener las recetas como si fueran los días de la semana
     * como en si la receta que estará contenida dentro del array.
     */
    public PlanificadorSemanal() {
        this.planificador = new Receta[7];
        this.receta = planificador[dia];
    }

    /**
     * Función que sirve para poder organizar y agregar recetas a los distintos días de la semana.
     * @param dia Variabe numérica que representa en qué posición de los días de la semana nos encontramos.
     * @param receta Objeto que asociamos a la posición del array indicada por el día en el que queramos guardar la receta
     */
    public void agregarComida(int dia, Receta receta) {
        if (dia < 0 || dia > 6) {
            throw new IllegalArgumentException("No entra dentro de los parámetros de la semana. Debe estar entre el 0 (Lunes) y el 6 (Martes)");
        }
            planificador[dia] = receta;
        // Añade una receta a un día de la semana en el planificador semanal
    }

    /**
     * Función que crea un esquema de los días de la semana y las recetas asociadas respectivamente
     * @return Devuelve un esquema de texto con la organización semanal de las recetas y los días de la semana ya sea que haya recetas o no
     */
    @Override
    public String toString() {
        String plan = "";
        boolean hayReceta = false;
        for (int i = 0; i < 7; i++){
            if (planificador[i] != null){
                hayReceta = true;
                break;
            }
        }
        if (hayReceta) {

            plan += "---------------------------------------------------------------------------------------------------------------------------------------------------\n";
            plan += " Lunes                Martes               Miércoles            Jueves               Viernes              Sábado               Domingo             \n";
            plan += "---------------------------------------------------------------------------------------------------------------------------------------------------\n ";
            for (int i = 0; i < 7; i++) {
                receta = planificador[i];
                if (planificador[i] == null) {
                    plan += "                     ";
                } else {
                    String nombreReceta = receta.getNombre();
                    int espacio = 21 - nombreReceta.length();

                    plan += nombreReceta;
                    for (int j = 0; j < espacio ;j++){
                        plan += " ";
                    }
                }
            }
            plan += "\n---------------------------------------------------------------------------------------------------------------------------------------------------\n" + "\n";
        }else{
            plan =
                    "-----------------------------------------------------------------------------\n" +
                    " Lunes      Martes     Miércoles  Jueves     Viernes    Sábado     Domingo   \n" +
                    "-----------------------------------------------------------------------------\n" +
                    "                                                                             \n" +
                    "-----------------------------------------------------------------------------\n" +
                    "\n";
        }
            // Devuelve una representación en forma de cadena del planificador semanal
            return plan;

    }

    /**
     * Función que guarda el plan semanal simplificado en un archivo de texto para poder posteriormente ser leído de vuelta y se pueda acceder a su contenido.
     * @param nombreArchivo Es el nombre que se le dará al archivo que contendrá los datos del plan semanalde recetas.
     * @throws IOException Es la excepción que se lanza cuando sucede algún error al guardar el plan en el archivo de texto.
     */
    public void guardarPlanEnArchivo(String nombreArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (int i = 0; i < planificador.length; i++){
            Receta receta = planificador[i];
            String recetaNombre;
            if (receta != null) {
                recetaNombre = receta.getNombre();
            }   else {
                recetaNombre = "---";
            }
            writer.write(diasSemana[i] + ": " + recetaNombre + "\n");
        }
        }
        // Guarda el planificador semanal en un archivo de texto
    }
}
