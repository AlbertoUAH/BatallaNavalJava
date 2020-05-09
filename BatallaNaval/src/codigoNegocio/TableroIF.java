package codigoNegocio;

import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * TableroIF: Interface encargada de la comunicación entre Tablero y el Decorador.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public interface TableroIF {
    
    /**
     * Método encargado de contruir el Tablero llamando al método en cuestión de
     * la clase Tablero.
     * 
     * @param numFilas Número de filas del Tablero a crear.
     * @param numColumnas Número de columnas del Tablero a crear.
     * @param modo Número que indica el modo del Tablero, es decir, si el
     *             Tablero tiene que ser construido de cero o tiene que ser
     *             restaurado de una partida guardada anteriormente.
     */
    public void construirTablero(int numFilas, int numColumnas, int modo);
    
    /**
     * Método encargado de cambiar la apariencia del Tablero.
     * 
     * @param casillasJugador Colección de las casillas del Jugador junto con el
     *                        JLabel correspondiente de cada una del Tablero.
     * @param casillasIA Colección de las casillas de la IA, junto con el JLabel
     *                   correspondiente de cada una del Tablero.
     * @param ventana JFrame correspondiente al Tablero.
     */
    public void decorarTablero(LinkedHashMap<String, JLabel> casillasJugador, 
                               LinkedHashMap<String, JLabel> casillasIA, JFrame ventana);
}