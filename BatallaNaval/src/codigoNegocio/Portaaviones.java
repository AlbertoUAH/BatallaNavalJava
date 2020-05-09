package codigoNegocio;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Portaaviones: Representa un objeto de tipo Navio, en este caso, Portaaviones.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Portaaviones extends Navio{

    /**
     * Constructor.
     * 
     * @param casillas Número de casillas que ocupa (vidas).
     * @param nombre Nombre del Portaaviones.
     * @param posiciones Colección con las posiciones que ocupa en el Tablero y
     *                   si ha sido tocado en alguna de ellas o no.
     */
    public Portaaviones(int casillas, String nombre, LinkedHashMap<String,Boolean> posiciones) {
        super(casillas, nombre, posiciones);
    }

    /**
     * Método encargado de verificar si las casillas elegidas son correctas para
     * el ataque especial del Portaaviones.
     * 
     * @param casillas Colección de casillas a las cuales va dirigido el ataque.
     * @return Booleano que indica si las casillas indicadas para el ataque son
     *         correctas con respecto al tipo de Navio.
     */
    @Override
    public boolean atacar(ArrayList<String> casillas) {
        // En este caso, únicamente hay que verificar que las casillas obtenidas
        // son 5 ya que al ser un ataque superior al resto no se verifica que las
        // casillas ya hayan sido atacadas para que el juego este equilibrado.
        return casillas.size() == 5;
    }
    
    /**
     * Método que se encarga de realizar la copia del Portaaviones.
     * 
     * @return Portaaviones con las casillas, el nombre y las posiciones del objeto
     *         Portaaviones que lo llama.
     */
    @Override
    public Object copia(){
        return new Portaaviones(this.getCasillas(), this.getNombre(), this.getPosiciones());
    }   
}