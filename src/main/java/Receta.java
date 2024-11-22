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
        String[] Ingredientes = new String[maxIngredientes];
        String[] Instrucciones = new String[maxInstrucciones];
    }

    public String getNombre() {
        // Devuelve el nombre de la receta
        return nombre;
    }

    public String[] getIngredientes() {
        // Devuelve los ingredientes de la receta
        return Ingredientes;
    }

    public String[] getInstrucciones() {
        // Devuelve las instrucciones de la receta
        return Instrucciones;
    }

    /**
     * Funcion que añade un Ingrediente a la receta y un boolean 0/1 depende si se ha llegado al limite de ingredientes.
     * @param ingrediente - String del nombre del ingrediente que se quiere añadir a la receta.
     * @return
     */
    public boolean agregarIngrediente(String ingrediente) {
        // Añade un ingrediente a la receta
        if(ingredientesCompletos())
            return false;
        else {
            Ingredientes[numIngredientes] = ingrediente;
            numIngredientes++;

            return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO EL INGREDIENTE
        }
    }

    public boolean agregarInstruccion(String instruccion) {
        if(instruccionesCompletas())
            return false;
        else {
            Instrucciones[numInstrucciones] = instruccion;
            numInstrucciones++;

            return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA INSTRUCCIÓN
        }
    }

    public boolean ingredientesCompletos() {
        // Comprueba si la receta tiene el máximo de ingredientes
        if (numIngredientes>(maxIngredientes-1))
        return true; // @todo MODIFICAR PARA DEVOLVER SI ESTÁN COMPLETOS LOS INGREDIENTES
        else
            return false;
    }

    public boolean instruccionesCompletas() {
        // Comprueba si la receta tiene el máximo de instrucciones
        if(numInstrucciones>(maxInstrucciones-1))
            return true;
        else
        return false; // @todo MODIFICAR PARA DEVOLVER SI ESTÁN COMPLETAS LAS INSTRUCCIONES
    }

    public int numIngredientes() {
        return numIngredientes;
    }

    public int numInstrucciones() {
       return numInstrucciones;
    }

    @Override
    public String toString() {
        // Devuelve una representación en forma de cadena de la receta
        System.out.println("Receta: "+nombre);
        System.out.println("Ingredientes:");
        for(int i=0;i<=numIngredientes;i++) {
            System.out.println(Ingredientes[i]);
        }
        System.out.println("Instrucciones:");
        for(int i=0;i<=numInstrucciones;i++){
            System.out.println((i+1)+". "+Instrucciones[i]);
        }
        return null; // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    public String toRawString() {
        System.out.println(nombre);
        System.out.println("INGREDIENTES");
        for(int i=0;i<=numIngredientes;i++) {
            System.out.println(Ingredientes[i]);
        }
        System.out.println("INSTRUCCIONES");
        for(int i=0;i<=numInstrucciones;i++){
            System.out.println(Instrucciones[i]);
        }
        return null; // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    public int getMaxIngredientes() {
       return maxIngredientes;
    }

    public int getMaxInstrucciones() {
        return maxInstrucciones;
    }
}

