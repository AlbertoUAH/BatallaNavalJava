package codigoNegocio;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * FactoriaNavios: Representa un objeto de tipo FactoriaNavios encargado de
 *                 fabricar los navios correspondientes.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class FactoriaNavios implements Serializable{
    
    // Nombre que hace referencia a la Corbeta.
    public static final String CORBETA = "Corbeta";
    // Nombre que hace referencia a la Destructor.
    public static final String DESTRUCTOR = "Destructor";
    // Nombre que hace referencia a la Acorazado.
    public static final String ACORAZADO = "Acorazado";
    // Nombre que hace referencia a la Portaaviones.
    public static final String PORTAAVIONES = "Portaaviones";
    // Nombre que hace referencia a la Submarino.
    public static final String SUBMARINO = "Submarino";
    
    /**
     * Constructor.
     * 
     * @param tipo Tipo del Navio a fabricar.
     * @param nombre Nombre del Navio a fabricar.
     * @param posiciones Colección con las posiciones que ocupa en el tablero y
     *                   si ha sido tocado en alguna de ellas o no.
     * @return Navio correspondiente.
     */
    public Navio getNavio(String tipo, String nombre, LinkedHashMap<String,Boolean> posiciones){
        switch(tipo){
            case CORBETA:
                return new Corbeta(2, nombre, posiciones);
            case DESTRUCTOR:
                return new Destructor(3, nombre, posiciones);
            case ACORAZADO:
                return new Acorazado(4, nombre, posiciones);
            case PORTAAVIONES:
                return new Portaaviones(5, nombre, posiciones);
            case SUBMARINO:
                return new Submarino(3, nombre, posiciones);
        }
        return null;
    } 
}