package codigoNegocio;

import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * DecoradorTablero: Representa un objeto de tipo DecoradorTablero.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public abstract class DecoradorTablero implements TableroIF{
    
    // Interface que comunica el DecoradorTablero con Tablero.
    private TableroIF tablero; 
    
    /**
     * Constructor.
     * 
     * @param tablero Interface para la comunicación entre del DecoradorTablero
     *                y el Tablero.
     */
    public DecoradorTablero(TableroIF tablero) {
        this.tablero = tablero;
    }

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
    @Override
    public void construirTablero(int numFilas, int numColumnas, int modo) {
        tablero.construirTablero(numFilas, numColumnas, modo);
    }

    /**
     * Método encargado de cambiar la apariencia del Tablero.
     * 
     * @param casillasJugador Colección de las casillas del Jugador junto con el
     *                        JLabel correspondiente de cada una del Tablero.
     * @param casillasIA Colección de las casillas de la IA, junto con el JLabel
     *                   correspondiente de cada una del Tablero.
     * @param tablero JFrame correspondiente al Tablero.
     */
    @Override
    public abstract void decorarTablero(LinkedHashMap<String, JLabel> casillasJugador,
                                        LinkedHashMap<String, JLabel> casillasIA, JFrame tablero);
}