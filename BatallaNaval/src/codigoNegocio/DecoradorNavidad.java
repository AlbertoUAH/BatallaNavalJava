package codigoNegocio;

import java.util.LinkedHashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * DecoradorNavidad: Representa un objeto de tipo DecoradorTablero, en este 
 *                   caso, DecoradorNavidad encargado de decorar el Tablero
 *                   con la apariencia de Navidad.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class DecoradorNavidad extends DecoradorTablero {
    
    /**
     * Constructor.
     * 
     * @param tablero Interface para la comunicación entre del DecoradorNavidad
     *                y el Tablero.
     */
    public DecoradorNavidad(TableroIF tablero) {
        super(tablero);
    }

    /**
     * Método encargado de cambiar la apariencia del Tablero, en este caso, a la
     * apariencia de Navidad.
     * 
     * @param casillasJugador Colección de las casillas del Jugador junto con el
     *                        JLabel correspondiente de cada una del Tablero.
     * @param casillasIA Colección de las casillas de la IA, junto con el JLabel
     *                   correspondiente de cada una del Tablero.
     * @param ventana JFrame correspondiente al Tablero.
     */
    @Override
    public void decorarTablero(LinkedHashMap<String, JLabel> casillasJugador,
                               LinkedHashMap<String, JLabel> casillasIA, JFrame ventana) {
        // Creamos los tres iconos posibles para decorar.
        Icon iconoPredeterminado = new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTablero.png"));
        Icon iconoHalloween = new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroHalloween.png"));
        Icon iconoNavidad = new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroNavidad.png"));
        
        // Flags para verificar que tipo de icono hay en cada momento.
        boolean hayIconoPredeterminado;
        boolean hayIconoHalloween;
        boolean hayIconoNavidad;
        
        // Recorremos las casillas del Jugador y de la IA verificando, antes de 
        // establecer el icono de Navidad, si la casilla tiene un JLabel 
        // correspondiente a alguno de los iconos creados anteriormente o por el
        // contrario, tiene un icono correspondiente al funcionamiento del juego.
        for(JLabel casilla: casillasJugador.values()) {
            hayIconoPredeterminado = casilla.getIcon().toString().equals(iconoPredeterminado.toString());
            hayIconoHalloween = casilla.getIcon().toString().equals(iconoHalloween.toString());
            hayIconoNavidad = casilla.getIcon().toString().equals(iconoNavidad.toString());
            if(hayIconoPredeterminado || hayIconoHalloween || hayIconoNavidad) {
                casilla.setIcon(null);
                casilla.setIcon(new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroNavidad.png")));
            }
        }
        
        for(JLabel casilla: casillasIA.values()) {
            hayIconoPredeterminado = casilla.getIcon().toString().equals(iconoPredeterminado.toString());
            hayIconoHalloween = casilla.getIcon().toString().equals(iconoHalloween.toString());
            hayIconoNavidad = casilla.getIcon().toString().equals(iconoNavidad.toString());
            if(hayIconoPredeterminado || hayIconoHalloween || hayIconoNavidad) {
                casilla.setIcon(null);
                casilla.setIcon(new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroNavidad.png")));
            }
        }
        
        // Establecemos el fondo del Tablero con la apariencia de Navidad.
        JLabel fondo = new JLabel();
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/FondoTableroNavidad.jpg")));
        ventana.getContentPane().add(fondo);
        fondo.setBounds(0, 0, ventana.getSize().width,  ventana.getSize().height);
    }
}