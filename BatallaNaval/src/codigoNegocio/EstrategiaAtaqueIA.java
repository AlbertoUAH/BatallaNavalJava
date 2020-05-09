package codigoNegocio;

import interfaz.Tablero;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * EstrategiaAtaqueIA: Representa un objeto de tipo EstrategiaAtaqueIA encargado
 *                     de los ataques de la IA.                     
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public abstract class EstrategiaAtaqueIA {
    
    // Nombre del tipo de ataque a utilizar.
    private String tipoAtaque = null;
    // Colección con las casillas que van a ser atacadas.
    private ArrayList<String> casillasDeAtaque = new ArrayList<>();
    // Ruta de la imagen de un Navio tocado.
    private String imagenTocado = "/interfaz/imagenes/CasillaTableroTocado.png";
    // Ruta de la imagen de un Navio no tocado.
    private String imagenNoTocado = "/interfaz/imagenes/CasillaTableroNoTocado.png";
    // Tablero donde se desarrolla toda la acción del juego.
    private Tablero tableroJuego;
    
    /**
     * Constructor.
     * 
     * @param tableroJuego Tablero donde se desarrolla toda la acción del juego.
     */
    public EstrategiaAtaqueIA(Tablero tableroJuego) {
        this.tableroJuego = tableroJuego;
    }
    
    /**
     * Método encargado de devolver el atributo tipoAtaque.
     * 
     * @return Nombre del tipo de ataque a utilizar.
     */
    public String getTipoAtaque() {
        return this.tipoAtaque;
    }
    
    /**
     * Método encargado de devolver el atributo casillasDeAtaque.
     * 
     * @return Colección con las casillas que van a ser atacadas.
     */
    public ArrayList<String> getCasillasDeAtaque() {
        return this.casillasDeAtaque;
    }
    
    /**
     * Método encargado de devolver el atributo imagenTocado.
     * 
     * @return Ruta de la imagen de un Navio tocado.
     */
    public String getImagenTocado() {
        return this.imagenTocado;
    }
    
    /**
     * Método encargado de devolver el atributo imagenNoTocado.
     * 
     * @return Ruta de la imagen de un Navio no tocado.
     */
    public String getImagenNoTocado() {
        return this.imagenNoTocado;
    }
    
    /**
     * Método encargado de devolver el atributo tableroJuego.
     * 
     * @return Tablero donde se desarrolla toda la acción del juego.
     */
    public Tablero getTableroJuego() {
        return this.tableroJuego;
    }
    
    /**
     * Método encargado de establecer el valor del atributo tipoAtaque.
     * 
     * @param tipoAtaque Nombre del tipo de ataque a utilizar.
     */
    public void setTipoAtaque(String tipoAtaque) {
        this.tipoAtaque = tipoAtaque;
    }
    
    /**
     * Método encargado de establecer el valor del atributo casillasDeAtaque.
     * 
     * @param casillasDeAtaque Colección con las casillas que van a ser atacadas.
     */
    public void setCasillasDeAtaque(ArrayList<String> casillasDeAtaque) {
        this.casillasDeAtaque = casillasDeAtaque;
    }
    
    /**
     * Método encargado de eliminar una casilla concreta de las casillas a atacar.
     * 
     * @param indice Número que indica la casilla a eliminar.
     */
    public void eliminarCasillaDeAtaque(int indice) {
        this.casillasDeAtaque.remove(indice);
    }
    
    /**
     * Método encargado de realizar el ataque del Navio concreto seleccionado
     * por la IA.
     * 
     */
    public abstract void atacar();
    
    /**
     * Método encargado de obtener las casillas a atacar por parte de la IA.
     * 
     * @return Colección con las casillas que van a ser atacadas.
     */
    public abstract ArrayList<String> obtenerCasillasDeAtaque();
    
    /**
     * Método encargado de verificar si las casillas de ataque seleccionadas ya
     * han sido atacadas anteriormente o no. De esta forma obligamos a la IA a
     * que aquellas casillas que ya han sido atacadas anteriormente no vuelvan
     * a ser atacadas.
     * 
     * @param coordenadasAtaque Colección con las casillas que van a ser atacadas.
     * @param casillasTablero Colección con las casillas y el JLabel correspondiente
     *                        a cada una de ellas.
     * @return Booleano que indica si las casillas son válidas o no.
     */
    public boolean comprobarCasillasDeAtaque(ArrayList<String> coordenadasAtaque,
                                             LinkedHashMap<String, JLabel> casillasTablero) {
        // Obtenemos el icono de Navio tocado.
        Icon iconoBarcoTocado = new ImageIcon(getClass().getResource(getImagenTocado()));
        
        // Flags para recorrer las casillas seleccionadas para el ataque.
        boolean hayCasillasTocadas = false;
        int cont = 0;
        while(cont < coordenadasAtaque.size() && !hayCasillasTocadas) {
            
            // Detecta si un Navio ya ha sido atacado.
            if(casillasTablero.get(coordenadasAtaque.get(cont)).getIcon().equals(iconoBarcoTocado))
                hayCasillasTocadas = true;
            cont++;
        }
        return hayCasillasTocadas;
    }
}