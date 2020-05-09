package codigoNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Navio: Representa un objeto de tipo Navio.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public abstract class Navio implements ClonarNavios, Serializable {
    
    // Número de casillas que ocupa (vidas).
    private int casillas;
    // Nombre del Navio.
    private String nombre;
    // Colección con las posiciones que ocupa en el Tablero y si ha sido tocado
    // en alguna de ellas o no.
    private LinkedHashMap<String,Boolean> posiciones;
    
    /**
     * Constructor.
     * 
     * @param casillas Número de casillas que ocupa (vidas).
     * @param nombre Nombre del Navio.
     * @param posiciones Colección con las posiciones que ocupa en el Tablero y
     *                   si ha sido tocado en alguna de ellas o no.
     */
    public Navio(int casillas, String nombre, LinkedHashMap<String,Boolean> posiciones) {
        this.casillas = casillas;
        this.nombre = nombre;
        this.posiciones = posiciones;
    }
    
    /**
     * Método encargado de devolver el atributo casillas.
     * 
     * @return Número de casillas que ocupa (vidas).
     */
    public int getCasillas(){
        return this.casillas;
    }
    
    /**
     * Método encargado de establecer el valor del atriubto casillas.
     * 
     * @param casillas Número de casillas que ocupa (vidas).
     */
    public void setCasillas(int casillas){
        this.casillas = casillas;
    }
    
    /**
     * Método encargado de devolver el atributo nombre.
     * 
     * @return Nombre del Navio.
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Método encargado de establecer el valor del atributo nombre.
     * 
     * @param nombre Nombre del Navio.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * Método encargado de devolver el atributo posiciones.
     * 
     * @return Colección con las posiciones que ocupa en el Tablero y si ha sido
     *         tocado en alguna de ellas o no.
     */
    public LinkedHashMap<String,Boolean> getPosiciones(){
        return this.posiciones;
    }
    
    /**
     * Método encargado de establecer el valor del atributo posiciones.
     * 
     * @param posiciones Colección con las posiciones que ocupa en el Tablero y 
     *                   si ha sido tocado en alguna de ellas o no.
     */
    public void setPosiciones(LinkedHashMap<String, Boolean> posiciones){
        this.posiciones = posiciones;
    }
    
    /**
     * Método que se encarga de realizar la copia del Navio.
     * 
     * @return Navio con las casillas, el nombre y las posiciones del objeto
     *         Navio que lo llama.
     */
    @Override
    public abstract Object copia();
    
    /**
     * Método encargado de verificar si las casillas elegidas son correctas para
     * el ataque especial del Navio.
     * 
     * @param casillas Colección de casillas a las cuales va dirigido el ataque.
     * @return booleano que indica si las casillas indicadas para el ataque son
     *         correctas con respecto al tipo de Navio.
     */
    public abstract boolean atacar(ArrayList<String> casillas); 
}