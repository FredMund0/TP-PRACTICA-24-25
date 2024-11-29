import java.io.IOException;

public class PlanificadorSemanal {
    private Receta[] planificador;
    private Receta receta;
    private int dia;

    public PlanificadorSemanal() {
        this.planificador = new Receta[6];
        Receta receta = planificador[dia];
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
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("\tLunes\t\t\t\tMartes\t\t\t\tMiércoles\t\t\t\tJueves\t\t\t\tViernes\t\t\t\tSábado\t\t\t\tDomingo");
        System.out.println("------------------------------------------------------------------------------------------");
        for (int i = 0; i <=6; i++) {
            receta = planificador[i];
            System.out.printf(" \t%-17s",receta.getNombre());
        }
        System.out.println("------------------------------------------------------------------------------------------");
        // Devuelve una representación en forma de cadena del planificador semanal
        return null; // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    public void guardarPlanEnArchivo(String nombreArchivo) throws IOException {
        // Guarda el planificador semanal en un archivo de texto
    }
}
