import java.io.IOException;

public class PlanificadorSemanal {
    private Receta[] planificador;
    private Receta receta;

    public PlanificadorSemanal() {
        this.planificador = new Receta[6];
        this.receta = new Receta
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
        System.out.println("------------------------------------------------------------------------------------------\n");
        System.out.println("\tLunes\t\tMartes\t\tMiércoles\t\tJueves\t\tViernes\t\tSábado\t\tDomingo");
        System.out.println("------------------------------------------------------------------------------------------\n");
        System.out.println("\t" + planificador[0] + "\t\t" + planificador[1] + "\t\t" + planificador[2] + "\t\t" + planificador[3] + );
        // Devuelve una representación en forma de cadena del planificador semanal
        return null; // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    public void guardarPlanEnArchivo(String nombreArchivo) throws IOException {
        // Guarda el planificador semanal en un archivo de texto
    }
}
