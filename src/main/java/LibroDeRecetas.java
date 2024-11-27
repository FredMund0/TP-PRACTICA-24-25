import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LibroDeRecetas {
    private Receta recetas[];
    private int numRecetas=0;
    public LibroDeRecetas(int maxRecetasEnLibro) {
        // Inicialización del libro de recetas
       recetas= new Receta[maxRecetasEnLibro];

    }

    public boolean agregarReceta(Receta receta) {
        // Añade una receta al libro de recetas
        if(!recetasCompletas()){
            for(int i=0;i<recetas.length;i++){
                if(recetas[i]==null)
                    recetas[i]=receta;}
            numRecetas++;
            return true;
        }
        else
            return false;
    }

    public Receta[] buscarRecetaPorNombre(String texto) {
       Receta[]Encontradas=new Receta[recetas.length];
       int recetasEncontradas=0;
        for(int i=0;i<=recetas.length;i++){
            Receta receta=recetas[i];
            for(int j=0;j<=(receta.getNombre()).length()-texto.length();j++) {
                if (((receta.getNombre()).toUpperCase()).substring(j, j + texto.length()) == texto.toUpperCase()){
                    Encontradas[recetasEncontradas]=receta;
                    recetasEncontradas++;
                }
            }
        }
        return Encontradas; // @todo MODIFICAR PARA DEVOLVER LAS RECETAS ENCONTRADAS
    }

    public void guardarRecetasEnArchivo(String nombreArchivo) throws IOException {
        // Guarda las recetas en un archivo de texto
        File guardado = new File("C://", nombreArchivo+".txt");
        try{
            PrintWriter escritor= new PrintWriter(new FileWriter(guardado));
            for(int i=0;i<recetas.length;i++) {
                if (recetas[i] != null) {
                    Receta receta = recetas[i];
                    System.out.println(receta.toRawString());
                }
            }
        }catch(IOException e){
            System.out.println("Ocurrió un error al escribir en el archivo.");
        }
    }

    public void cargarRecetasDeArchivo(String nombreArchivo, int maxIngredientes, int maxInstrucciones) throws IOException {
        // Carga las recetas desde un archivo de texto
    }

    public boolean recetasCompletas() {
        if(numRecetas>=recetas.length)
        return true; // @todo MODIFICAR PARA DEVOLVER SI ESTÁ COMPLETO
        else return false;
    }

    public int numRecetas() {
        return numRecetas;
    }

    public void eliminarReceta(Receta seleccionada) {
        // Elimina una receta del libro de recetas
        for(int i=0;i<recetas.length;i++){
            Receta receta=recetas[i];
            if(receta==seleccionada)
                recetas[i]=null;
        }

    }
}

