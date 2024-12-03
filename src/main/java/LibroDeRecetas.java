import java.io.*;

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
        //Deberia haberlo hecho con contains
       Receta[]Encontradas=new Receta[recetas.length];
       int recetasEncontradas=0;
        for(int i=0;i<recetas.length;i++){
            Receta receta=recetas[i];
            for(int j=0;j<=((receta.getNombre()).length()-texto.length());j++) {
                if (((receta.getNombre()).toUpperCase()).substring(j, (j + texto.length())).equals(texto.toUpperCase())){
                    Encontradas[recetasEncontradas]=receta;
                    recetasEncontradas++;
                }
            }
        }
        return Encontradas; // @todo MODIFICAR PARA DEVOLVER LAS RECETAS ENCONTRADAS
    }

    public void guardarRecetasEnArchivo(String nombreArchivo) throws IOException {
        // Guarda las recetas en un archivo de texto
        File guardado = new File(nombreArchivo+".txt");
        try{
            PrintWriter escritor= new PrintWriter(new FileWriter(guardado));
            for(int i=0;i<recetas.length;i++) {
                if (recetas[i] != null) {
                    Receta receta = recetas[i];
                    escritor.print(receta.toRawString());}
            }
            escritor.close();
            System.out.println("Archivo guardado exitosamente");
        }catch(IOException e){
            System.out.println("Ocurrió un error al escribir en el archivo."+ e.getMessage());
            e.printStackTrace();

        }
    }

    public void cargarRecetasDeArchivo(String nombreArchivo, int maxIngredientes, int maxInstrucciones) throws IOException {
        // Solucionar errores al meter un archivo mal hecho
        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo + ".txt"));
            String linea;
            do {
                linea = lector.readLine();
                if (linea.isEmpty()){}
            else{
                    Receta receta = new Receta(linea, maxIngredientes, maxInstrucciones);
                    for (int i = 0; i < maxIngredientes; i++) {
                        linea = lector.readLine();
                        if (linea.equals("INSTRUCCIONES")) {
                            receta.agregarIngrediente(linea);
                        } else break;
                    }
                    for (int i = 0; i < maxInstrucciones; i++) {
                        linea = lector.readLine();
                        if (!linea.equals("-----")) {
                            receta.agregarInstruccion(linea);
                        } else break;
                    }
                    agregarReceta(receta);
                }
            }while(!linea.isEmpty());
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo llamado: " + nombreArchivo + ".txt");
        }catch (IOException ex) {
            System.out.println(ex.getMessage());}
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

