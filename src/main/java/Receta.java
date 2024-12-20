/**
 * Función que se encarga en inicializar la recetas, devolver sus parametros, agregar ingredientes e
 * instrucciones y imprimir la receta para su lectura.
 *
 */
public class Receta {
    private String nombre;
    private String[] Ingredientes;
    private String[] Instrucciones;
    private int maxIngredientes;
    private int maxInstrucciones;
    private int numIngredientes=0;//A lo mejor no hace falta crear esta variable
    private int numInstrucciones=0;
    public Receta(String nombre, int maxIngredientes, int maxInstrucciones) {
        // Inicialización de la receta
        this.nombre= nombre;
        this.maxIngredientes=maxIngredientes;
        this.maxInstrucciones=maxInstrucciones;
       Ingredientes = new String[maxIngredientes];
       Instrucciones = new String[maxInstrucciones];
    }

    /**
     * @return -Devuelve el nombre de la receta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return -Devuelve el array String de los Ingredientes
     */
    public String[] getIngredientes() {
        return Ingredientes;
    }

    /**
     * @return -Devuelve el array String de las instrucciones.
     */
    public String[] getInstrucciones() {
        return Instrucciones;
    }

    /**
     * Funcion que añade un Ingrediente a la receta y un boolean 0/1 que depende si se ha llegado al limite de ingredientes.
     * @param ingrediente - String del nombre del ingrediente que se quiere añadir a la receta.
     * @return -Devuelve un valor boolean que depende si se ha llegado al limite de ingredientes y no se ha podido crear uno nuevo.(true=exito, false=limite pasado)
     */
    public boolean agregarIngrediente(String ingrediente) {
        if(ingredientesCompletos()){
            System.out.println("No se pueden añadir más ingredientes.");
            }
        else {
            Ingredientes[numIngredientes] = ingrediente;
            numIngredientes++;
        }
        return (!ingredientesCompletos());
    }

    /**
     * Función que añade una Instruccion al array Instrucciones[] y devuelve un boolean que depende si se ha llegado al limite de instrucciones.
     * @param instruccion -String de la linea que contiene la instrucción.
     * @return -Devuelve un boolean que es igual a true si se añade la instrucción correctamente y false si se ha llegado al limite de instrucciones.
     */
    public boolean agregarInstruccion(String instruccion) {
        if(instruccionesCompletas()){
            System.out.println("No se pueden añadir más instrucciones.");
            return false;}
        else {
            Instrucciones[numInstrucciones] = instruccion;
            numInstrucciones++;
            return true; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA INSTRUCCIÓN
        }
    }

    /**
     * Función que comprueba si se ha llegado al limite de ingredientes y devuelve un boolean
     * @return-Devuelve un boolean 0 si no se ha llegado y un 1 si ya se ha llegado al limite.
     */
    public boolean ingredientesCompletos() {
       return (numIngredientes>=maxIngredientes);
    }

    /**
     * Función que comprueba si se ha llegado al limite de instrucciones y devuelve un boolean
     * @return -Devuleve el boolean 0 si no se ha llegado y un 1 si ya se ha llegado al limite.
     */
    public boolean instruccionesCompletas() {
        return numInstrucciones >= maxInstrucciones;
    }

    /**
     * @return -Devuelve el numero(int) de ingredientes
     */
    public int numIngredientes() {
        return numIngredientes;
    }

    /**
     * @return -Devuelve el numero(int) de instrucciones.
     */
    public int numInstrucciones() {
       return numInstrucciones;
    }

    /**
     * Función que devuelve un string de toda la receta escrita para su lectura.
     * @return Devuelve el String de toda la receta escrita para su lectura.
     */
    @Override
    public String toString() {
        String receta="";
       receta+="Receta: "+nombre+"\n";
        receta+="Ingredientes:\n";
        for(int i=0;i<numIngredientes;i++) {
            if(Ingredientes[i]!=null){
            receta+="- "+Ingredientes[i]+"\n";}
        }
       receta+="Instrucciones:\n";
        for(int i=0;i<numInstrucciones;i++){
            if(Instrucciones[i]!=null){
            receta+=(i+1)+". "+Instrucciones[i]+"\n";}
        }
        return receta;
    }

    /**
     * Función que devuelve un String con la receta escrita de forma simplificada para poder guardar y cargar archivos.
     * @return -Devuelve un String con la receta escrita de forma simplificada para poder guardar y cargar archivos.
     */
    public String toRawString() {
        String rawReceta="";
        rawReceta+=nombre+"\n";
        for(int i=0;i<=numIngredientes;i++) {
            if(Ingredientes[i]!=null){
            rawReceta+=Ingredientes[i]+"\n";}
        }
       rawReceta+="INSTRUCCIONES\n";
        for(int i=0;i<=numInstrucciones;i++){
            if(Instrucciones[i]!=null){
            rawReceta+=Instrucciones[i]+"\n";}
        }
        rawReceta+="-----\n";
        return rawReceta;
    }

    /**
     * @return -Devuelve el limite de ingredientes de la receta
     */
    public int getMaxIngredientes() {
       return maxIngredientes;
    }

    /**
     * @return -Devuelve el limite de instrucciones de la receta
     */
    public int getMaxInstrucciones() {
        return maxInstrucciones;
    }
}

