package codigoNegocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * Corbeta: Representa un objeto de tipo Navio, en este caso, Corbeta.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Corbeta extends Navio{

    /**
     * Constructor.
     * 
     * @param casillas Número de casillas que ocupa (vidas).
     * @param nombre Nombre de la Corbeta.
     * @param posiciones Colección con las posiciones que ocupa en el Tablero y
     *                   si ha sido tocado en alguna de ellas o no.
     */
    public Corbeta(int casillas, String nombre, LinkedHashMap<String,Boolean> posiciones) {
        super(casillas, nombre, posiciones);
    }

    /**
     * Método encargado de verificar si las casillas elegidas son correctas para
     * el ataque especial de la Corbeta.
     * 
     * @param casillas Colección de casillas a las cuales va dirigido el ataque.
     * @return Booleano que indica si las casillas indicadas para el ataque son
     *         correctas con respecto al tipo de Navio.
     */
    @Override
    public boolean atacar(ArrayList<String> casillas) {
        // Ordenamos las casillas para evitar que el usuario las tenga que meter
        // en orden.
        // Creamos dos colecciones independientes:
        //  1. coordenadasLetra: con las letras de cada coordenada
        //  2. coordenadasNumero: con los numeros de cada coordenada
        ArrayList<String> coordenadasLetra = new ArrayList<>();
        ArrayList<Integer> coordenadasNumero = new ArrayList<>();
        
        // Recorremos cada una de las coordenadas
        for(String coordenadaSeleccionada : casillas) {
            coordenadasLetra.add(Character.toString(coordenadaSeleccionada.charAt(0)));
            coordenadasNumero.add(Integer.parseInt(coordenadaSeleccionada.substring(1)));
        }
        casillas.clear();
        
        // Ordenamos cada coleccion por separado
        Collections.sort(coordenadasLetra);
        Collections.sort(coordenadasNumero);
        
        // Una vez ordenadas las coordenadas, juntamos ambas colecciones
        for(int i = 0 ; i < coordenadasLetra.size() ; i++) {
            casillas.add(coordenadasLetra.get(i) + Integer.toString(coordenadasNumero.get(i)));
        }
        
        // Restamos las coordenadas para verificar que son contiguas.
        int restaPrimeraSegundaCoordenada = Math.abs(Character.getNumericValue(casillas.get(0).charAt(0)
                                            + Integer.parseInt(casillas.get(0).substring(1)))
                                            - Character.getNumericValue(casillas.get(1).charAt(0)
                                            + Integer.parseInt(casillas.get(1).substring(1))));
        
        return restaPrimeraSegundaCoordenada == 1;
    }
    
    /**
     * Método que se encarga de realizar la copia de la Corbeta.
     * 
     * @return Corbeta con las casillas, el nombre y las posiciones del objeto
     *         Corbeta que lo llama.
     */
    @Override
    public Object copia(){
        return new Corbeta(this.getCasillas(), this.getNombre(), this.getPosiciones());
    }
}