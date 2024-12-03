import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PlanificadorSemanal {
    private final Receta[] planificador;
    private Receta receta;
    private int dia;

    public PlanificadorSemanal() {
        this.planificador = new Receta[7];
        this.receta = planificador[dia];
    }

    public void agregarComida(int dia, Receta receta) {
        if (dia < 0 || dia > 6) {
            throw new IllegalArgumentException("No entra dentro de los parámetros de la semana. Debe estar entre el 0 (Lunes) y el 6 (Martes)");
        }
            planificador[dia] = receta;
        // Añade una receta a un día de la semana en el planificador semanal
    }

    @Override
    public String toString() {
        String plan = "";
        plan += "---------------------------------------------------------------------------------------------------------------------------------------------------\n";
        plan += "\tLunes\t\t\t\tMartes\t\t\t\tMiércoles\t\t\t\tJueves\t\t\t\tViernes\t\t\t\tSábado\t\t\t\tDomingo\n";
        plan += "-------------------------------------------------------------------------------------------------------------------------------------------\n";
        for (int i = 0; i <=6; i++) {
            receta = planificador[i];
            if (planificador[i] == null){
                plan += "";
            }   else {
                String nombreReceta = receta.getNombre();
                plan += nombreReceta;
            }
        }
        plan += "\n-------------------------------------------------------------------------------------------------------------------------------------------";
        // Devuelve una representación en forma de cadena del planificador semanal
        return plan; // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    public void guardarPlanEnArchivo(String nombreArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (int i = 0; i <= planificador.length; i++){
            Receta receta = planificador[i];
            String recetaNombre;
            if (receta != null) {
                recetaNombre = receta.getNombre();
            }   else {
                recetaNombre = "---";
            }
            writer.write((i) + " " + diasSemana[i] + ": " + recetaNombre);
            writer.newLine();
        }
        }
        // Guarda el planificador semanal en un archivo de texto
    }
}
