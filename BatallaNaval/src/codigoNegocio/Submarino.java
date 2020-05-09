package codigoNegocio;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Submarino: Representa un objeto de tipo Navio, en este caso, Submarino.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Submarino extends Navio{

    // Booleano que indica si el Submarino está o no sumergido.
    boolean sumergido;
    
    /**
     * Constructor.
     * 
     * @param casillas Número de casillas que ocupa (vidas).
     * @param nombre Nombre del Submarino.
     * @param posiciones Colección con las posiciones que ocupa en el Tablero y
     *                   si ha sido tocado en alguna de ellas o no.
     */
    public Submarino(int casillas, String nombre, LinkedHashMap<String,Boolean> posiciones) {
        super(casillas, nombre, posiciones);
        this.sumergido = false;
    }

    /**
     * Método encargado de devolver el atributo sumergido.
     * 
     * @return Booleano que indica si el Submarino está o no sumergido.
     */
    public boolean getSumergido(){
        return this.sumergido;
    }
    
    /**
     * Método encargado de establecer el valor del atributo sumergido.
     * 
     * @param sumergido Booleano que indica si el Submarino está o no sumergido.
     */
    public void setSumergido(boolean sumergido){
        this.sumergido = sumergido;
    }
    
    /**
     * Método encargado de sumergir el Submarino.
     * 
     * @param casillas Colección de casillas a las cuales va dirigido el ataque.
     * @return Booleano que indica si está sumergido o no el Submarino.
     */
    @Override
    public boolean atacar(ArrayList<String> casillas) {
        if(!getSumergido() && !getPosiciones().containsValue(true)){
            setSumergido(true);
        }
        return getSumergido();
    }
    
    /**
     * Método que se encarga de realizar la copia del Submarino.
     * 
     * @return Submarino con las casillas, el nombre y las posiciones del objeto
     *         Submarino que lo llama.
     */
    @Override
    public Object copia(){
        return new Submarino(this.getCasillas(), this.getNombre(), this.getPosiciones());
    }
}