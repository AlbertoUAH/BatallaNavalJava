package codigoNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Partida: Representa un objeto de tipo Partida encargado de almacenar todo
 *          aquello necesario para poder volver a cargar una Partida que ya
 *          haya sido empezada.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Partida implements Serializable{
    
    // Jugador presente en la Partida.
    private Jugador jugador;
    // Navios del Jugador en la Partida.
    private ArrayList<Navio> naviosJugador;
    // Navios de la IA en la Partida.
    private ArrayList<Navio> naviosIA;
    // Conjunto de Navios que puede seleccionar el Jugador.
    private JComboBox selectorNavios;
    // Colección de las casillas del Jugador junto con el JLabel correspondiente
    // de cada una del Tablero en la Partida.
    private LinkedHashMap<String, JLabel> casillasJugador;
    // Colección de las casillas de la IA junto con el JLabel correspondiente
    // de cada una del Tablero en la Partida.
    private LinkedHashMap<String, JLabel> casillasIA;
    // Colección de coordenadas en las que se han colocado el Navio.
    private ArrayList<String> posiciones;
    // Número de turnos que lleva la Partida.
    private int contadorTurnos;
    // Número de turnos para contabilizar cuando un Jugador puede volver a utilizar
    // de nuevo el ataque especial de cualquier Navio.
    private int contadorTurnosAux;
    // Booleano que indica si se ha utilizado o no el ataque especial de un Navio.
    private boolean haUtilizadoHabilidadEspecial;
    // Booleano que nos indica si el botón colocar se queda visible o no al 
    // guardar la Partida.
    private boolean esVisibleColocar;
    // Booleano que nos indica si el botón atacar se queda visible o no al 
    // guardar la Partida.
    private boolean esVisibleAtacar;
    // Coleccion con los navios hundidos por el jugador.
    private ArrayList<String> naviosHundidos;
    
    /**
     * Constructor.
     * 
     * @param jugador Jugador presente en la Partida.
     * @param naviosJugador Navios del Jugador en la Partida.
     * @param naviosIA Navios de la IA en la Partida.
     * @param selectorNavios Conjunto de Navios que puede seleccionar el Jugador.
     * @param casillasJugador Colección de las casillas del Jugador junto con el
     *                        JLabel correspondiente de cada una del Tablero en 
     *                        la Partida.
     * @param casillasIA Colección de las casillas de la IA junto con el JLabel 
     *                   correspondiente de cada una del Tablero en la Partida.
     * @param posiciones Colección de coordenadas en las que se han colocado los 
     *                   navios.
     * @param contadorTurnos Número de turnos que lleva la Partida.
     * @param contadorTurnosAux Número de turnos para contabilizar cuando un 
     *                          Jugador puede volver a utilizar de nuevo el 
     *                          ataque especial de cualquier Navio.
     * @param haUtilizadoHabilidadEspecial Booleano que indica si se ha utilizado
     *                                     o no el ataque especial de un Navio.
     * @param esVisibleColocar Booleano que nos indica si el botón colocar se 
     *                         queda visible o no al guardar la Partida.
     * @param esVisibleAtacar  Booleano que nos indica si el botón atacar se 
     *                         queda visible o no al guardar la Partida.
     */
    public Partida(Jugador jugador, ArrayList<Navio> naviosJugador, ArrayList<Navio> naviosIA,
                   JComboBox selectorNavios, LinkedHashMap<String, JLabel> casillasJugador,
                   LinkedHashMap<String, JLabel> casillasIA, ArrayList<String> posiciones,
                   int contadorTurnos, int contadorTurnosAux, boolean haUtilizadoHabilidadEspecial,
                   boolean esVisibleColocar, boolean esVisibleAtacar, ArrayList<String> naviosHundidos) {
        
        this.jugador = jugador;
        this.naviosJugador = naviosJugador;
        this.naviosIA = naviosIA;
        this.selectorNavios = selectorNavios;
        this.casillasJugador = casillasJugador;
        this.casillasIA = casillasIA;
        this.posiciones = posiciones;
        this.contadorTurnos = contadorTurnos;
        this.contadorTurnosAux = contadorTurnosAux;
        this.haUtilizadoHabilidadEspecial = haUtilizadoHabilidadEspecial;
        this.esVisibleColocar = esVisibleColocar;
        this.esVisibleAtacar = esVisibleAtacar;
        this.naviosHundidos = naviosHundidos;
    }
    
    /**
     * Método encargado de devolver el atributo jugador.
     * 
     * @return Jugador presente en la Partida.
     */
    public Jugador getJugador() {
        return this.jugador;
    }
    
    /**
     * Método encargado de devolver el atributo naviosJugador.
     * 
     * @return Navios del Jugador en la Partida.
     */
    public ArrayList<Navio> getNaviosJugador() {
        return this.naviosJugador;
    }
    
    /**
     * Método encargado de devolver el atributo naviosIA.
     * 
     * @return Navios de la IA en la Partida.
     */
    public ArrayList<Navio> getNaviosIA() {
        return this.naviosIA;
    }
    
    /**
     * Método encargado de devolver el atributo selectorNavios.
     * 
     * @return Conjunto de Navios que puede seleccionar el Jugador.
     */
    public JComboBox getSelectorNavios() {
        return this.selectorNavios;
    }
    
    /**
     * Método encargado de devolver el atributo casillasJugador.
     * 
     * @return Colección de las casillas del Jugador junto con el
     *         JLabel correspondiente de cada una del Tablero en 
     *         la Partida.
     */
    public LinkedHashMap<String, JLabel> getCasillasJugador() {
        return this.casillasJugador;
    }
    
    /**
     * Método encargado de devolver el atributo casillasIA.
     * 
     * @return Colección de las casillas de la IA junto con el JLabel 
     *         correspondiente de cada una del Tablero en la Partida.
     */
    public LinkedHashMap<String, JLabel> getCasillasIA() {
        return this.casillasIA;
    }
    
    /**
     * Método encargado de devolver el atributo posiciones.
     * 
     * @return Colección de coordenadas en las que se han colocado los 
     *         navios.
     */
    public ArrayList<String> getPosiciones() {
        return this.posiciones;
    }
    
    /**
     * Método encargado de devolver el atributo contadorTurnos.
     * 
     * @return Número de turnos que lleva la Partida.
     */
    public int getContadorTurnos() {
        return this.contadorTurnos;
    }
    
    /**
     * Método encargado de devolver el atributo contadorTurnosAux.
     * 
     * @return Número de turnos para contabilizar cuando un 
     *         Jugador puede volver a utilizar de nuevo el 
     *         ataque especial de cualquier Navio.
     */
    public int getContadorTurnosAux() {
        return this.contadorTurnosAux;
    }
    
    /**
     * Método encargado de devolver el atributo haUtilizadoHabilidadEspecial.
     * 
     * @return Booleano que indica si se ha utilizado
     *         o no el ataque especial de un Navio.
     */
    public boolean getHaUtilizadoHabilidadEspecial() {
        return this.haUtilizadoHabilidadEspecial;
    }
    
    /**
     * Método encargado de devolver el atributo esVisibleColocar.
     * 
     * @return Booleano que nos indica si el botón colocar se 
     *         queda visible o no al guardar la Partida.
     */
    public boolean getEsVisibleColocar() {
        return this.esVisibleColocar;
    }
    
    /**
     * Método encargado de devolver el atributo esVisibleAtacar.
     * 
     * @return Booleano que nos indica si el botón atacar se 
     *         queda visible o no al guardar la Partida.
     */
    public boolean getEsVisibleAtacar() {
        return this.esVisibleAtacar;
    }
    
    /**
     * Método encargado de devolver el listado de navios hundidos.
     * 
     * @return Coleccion con los navios hundidos.
     */
    public ArrayList<String> getNaviosHundidos() {
        return this.naviosHundidos;
    }
}