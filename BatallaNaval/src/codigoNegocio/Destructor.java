package codigoNegocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * Destructor: Representa un objeto de tipo Navio, en este caso, Destructor.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Destructor extends Navio{

    /**
     * Constructor.
     * 
     * @param casillas Número de casillas que ocupa (vidas).
     * @param nombre Nombre de la Destructor.
     * @param posiciones Colección con las posiciones que ocupa en el Tablero y
     *                   si ha sido tocado en alguna de ellas o no.
     */
    public Destructor(int casillas, String nombre, LinkedHashMap<String,Boolean> posiciones) {
        super(casillas, nombre, posiciones);
    }

    /**
     * Método encargado de verificar si las casillas elegidas son correctas para
     * el ataque especial de la Destructor.
     * 
     * @param casillas Colección de casillas a las cuales va dirigido el ataque.
     * @return Booleano que indica si las casillas indicadas para el ataque son
     *         correctas con respecto al tipo de Navio.
     */
    @Override
    public boolean atacar(ArrayList<String> casillas) {
        // Ordenamos las casillas para evitar que el usuario las tenga que meter
        // en orden.
        Collections.sort(casillas);
        // Restamos las coordenadas para verificar que son contiguas y que formen
        // un triángulo.
        int restaPrimeraSegundaCoordenada = Math.abs(Character.getNumericValue(casillas.get(0).charAt(0)) 
                                            - Character.getNumericValue(casillas.get(1).charAt(0))) 
                                            + Math.abs(Integer.parseInt(casillas.get(0).substring(1))
                                            - Integer.parseInt(casillas.get(1).substring(1)));
        
        int restaSegundaTerceraCoordenada = Math.abs(Character.getNumericValue(casillas.get(1).charAt(0))
                                            - Character.getNumericValue(casillas.get(2).charAt(0)))
                                            + Math.abs(Integer.parseInt(casillas.get(1).substring(1))
                                            - Integer.parseInt(casillas.get(2).substring(1)));
        
        int restaPrimeraTerceraCoordenada = Math.abs(Character.getNumericValue(casillas.get(0).charAt(0))
                                            - Character.getNumericValue(casillas.get(2).charAt(0)))
                                            + Math.abs(Integer.parseInt(casillas.get(0).substring(1))
                                            - Integer.parseInt(casillas.get(2).substring(1)));
        
        return (restaPrimeraSegundaCoordenada == restaSegundaTerceraCoordenada) 
                && (restaPrimeraSegundaCoordenada == 2) && (restaPrimeraTerceraCoordenada == 2);
    }
    
    /**
     * Método que se encarga de realizar la copia de la Destructor.
     * 
     * @return Destructor con las casillas, el nombre y las posiciones del objeto
     *         Destructor que lo llama.
     */
    @Override
    public Object copia(){
        return new Destructor(this.getCasillas(), this.getNombre(), this.getPosiciones());
    }
}