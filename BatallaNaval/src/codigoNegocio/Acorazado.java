package codigoNegocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * Acorazado: Representa un objeto de tipo Navio, en este caso, Acorazado.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Acorazado extends Navio{

    /**
     * Constructor.
     * 
     * @param casillas Número de casillas que ocupa (vidas).
     * @param nombre Nombre del Acorazado.
     * @param posiciones Colección con las posiciones que ocupa en el Tablero y
     *                   si ha sido tocado en alguna de ellas o no.
     */
    public Acorazado(int casillas, String nombre, 
                     LinkedHashMap<String,Boolean> posiciones) {
        super(casillas, nombre, posiciones);
    }

    /**
     * Método encargado de verificar si las casillas elegidas son correctas para
     * el ataque especial del Acorazado.
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
        
        // Restamos las coordenadas para verificar que son contiguas y que forman
        // un cuadrado.
        int restaPrimeraSegundaCoordenada = Math.abs(Character.getNumericValue(casillas.get(0).charAt(0))
                                            - Character.getNumericValue(casillas.get(1).charAt(0)))
                                            + Math.abs(Integer.parseInt(casillas.get(0).substring(1))
                                            - Integer.parseInt(casillas.get(1).substring(1)));
        
        int restaTerceraCuartaCoordenada = Math.abs(Character.getNumericValue(casillas.get(2).charAt(0))
                                           - Character.getNumericValue(casillas.get(3).charAt(0)))
                                           + Math.abs(Integer.parseInt(casillas.get(2).substring(1))
                                           - Integer.parseInt(casillas.get(3).substring(1)));
        
        int restaPrimeraCuartaCoordenada = Math.abs(Character.getNumericValue(casillas.get(0).charAt(0))
                                           - Character.getNumericValue(casillas.get(3).charAt(0)))
                                           + Math.abs(Integer.parseInt(casillas.get(0).substring(1))
                                           - Integer.parseInt(casillas.get(3).substring(1)));
        
        return (restaPrimeraSegundaCoordenada == restaTerceraCuartaCoordenada) 
                && (restaTerceraCuartaCoordenada == 1) && (restaPrimeraCuartaCoordenada == 2);
    }
    
    /**
     * Método que se encarga de realizar la copia del Acorazado.
     * 
     * @return Acorazado con las casillas, el nombre y las posiciones del objeto
     *         Acorazado que lo llama.
     */
    @Override
    public Object copia(){
        return new Acorazado(this.getCasillas(), this.getNombre(), this.getPosiciones());
    }
}