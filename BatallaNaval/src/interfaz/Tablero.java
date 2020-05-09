package interfaz;

import codigoNegocio.EstrategiaAtaqueIA;
import codigoNegocio.EstrategiaConcretaAcorazado;
import codigoNegocio.EstrategiaConcretaCorbeta;
import codigoNegocio.EstrategiaConcretaDestructor;
import codigoNegocio.EstrategiaConcretaPortaaviones;
import codigoNegocio.EstrategiaConcretaSubmarino;
import codigoNegocio.FactoriaNavios;
import codigoNegocio.IA;
import codigoNegocio.Jugador;
import codigoNegocio.Mediador;
import codigoNegocio.Navio;
import codigoNegocio.Partida;
import codigoNegocio.Recuerdo;
import codigoNegocio.Servidor;
import codigoNegocio.TableroIF;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Tablero: Ventana encargada del transcurso de todo el juego.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Tablero extends javax.swing.JFrame implements ActionListener, KeyListener, 
                                                           TableroIF, Serializable {
    // Instancia única de Tablero.
    private static Tablero instanciaTablero = null;
    // Intermediario entre el Jugador y la IA para la comunicación.
    private Mediador mediador;
    // Ventana Selección anterior a la ventana actual.
    private VentanaSeleccion ventanaSeleccion;
    // Jugador presente en la Partida.
    private Jugador jugador;
    // IA presente en la Partida.
    private IA ia;
    // Objeto EstrategiaAtaqueIA para los ataques de la IA.
    private EstrategiaAtaqueIA estrategiaAtaqueIA;
    // Proxy encargado de la comunicación entre el cliente y el Servidor.
    private Servidor proxy;
    // Factoría encargada de la fabricación de navios.
    private FactoriaNavios factoriaNavios;
    // Navios del Jugador en la Partida.
    private ArrayList<Navio> naviosJugador;
    // Navios de la IA en la Partida.
    private ArrayList<Navio> naviosIA; 
    // Número de filas del Tablero.
    private int numeroFilas;
    // Número de columnas del Tablero.
    private int numeroColumnas;
    // JTextPane donde se muestran los mensajes de la IA y el Jugador.
    private JTextPane chat;
    // JScrollPane para el JTextPane para poder ver todos los mensajes.
    private JScrollPane scrollChat;
    // Estilo de letra para el chat..
    private StyledDocument doc;
    private Style style;
    // JTextField donde se escriben los mensajes que se envían a la IA.
    private JTextField chatEscrito;
    // JLabel con el nombre Navios.
    private JLabel tituloNavios;
    // JComboBox que nos permite seleccionar el tipo de Navio a utilizar.
    private JComboBox selectorNavios;
    // Colección con todos los nombres de los navios para el JComboBox selectorNavios.
    private String[] nombreNavios;
    // Botones de Colocar y Atacar.
    private JButton colocar;
    private JButton atacar;
    // Colección de las casillas del Jugador junto con el JLabel correspondiente
    // de cada una del Tablero en la Partida.
    private LinkedHashMap<String, JLabel> casillasJugador;
    // Colección de las casillas de la IA junto con el JLabel correspondiente
    // de cada una del Tablero en la Partida.
    private LinkedHashMap<String, JLabel> casillasIA;
    // Colección de coordenadas en las que se han colocado el Navio.
    private ArrayList<String> posiciones;
    // JLabel con el nombre Turnos.
    private JLabel tituloTurnos;
    // JLabel que indica el turno actual.
    private JLabel numeroTurnos;
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
    // Coleccion con los navios ya hundidos por el jugador
    private ArrayList<String> naviosHundidos;
    
    /**
     * Constructor.
     * 
     * @param jugador Jugador presente en la Partida.
     * @param ia IA presente en la Partida.
     * @param mediador Intermediario entre el Jugador y la IA para la comunicación.
     * @param ventanaSeleccion Ventana Selección anterior a la ventana actual.
     * @param proxy Proxy encargado de la comunicación entre el cliente y el Servidor.
     */
    private Tablero(Jugador jugador, IA ia, Mediador mediador, VentanaSeleccion ventanaSeleccion,
                    Servidor proxy) {
        
        this.ventanaSeleccion = ventanaSeleccion;
        this.mediador = mediador;
        this.proxy = proxy;
        this.jugador = jugador;
        this.numeroFilas = 20;
        this.numeroColumnas = 20;
        this.ia = ia;
        this.factoriaNavios = new FactoriaNavios();
        this.tituloNavios = new JLabel("Navio", SwingConstants.CENTER);
        this.chat = new JTextPane();
        this.scrollChat = new JScrollPane(chat);
        this.chatEscrito = new JTextField();
        this.tituloTurnos = new JLabel("Turno", SwingConstants.CENTER); 
        this.colocar = new JButton("Colocar");
        this.atacar = new JButton("Atacar");
        
        // Comprobamos si hay alguna Partida guardada para el Jugador correspondiente.
        int indice = existeRecuerdo();
        if(indice != -1) { // Hay Partida guardada.
            
            // A través del Proxy obtenemos el Conserje asociado y recuperamos
            // todos los elementos imprescindibles para reanudar la Partida.
            this.jugador = proxy.getConserje().getRecuerdos().get(indice).getPartida().getJugador();
            this.naviosJugador = proxy.getConserje().getRecuerdos().get(indice).getPartida().getNaviosJugador();
            this.naviosIA = proxy.getConserje().getRecuerdos().get(indice).getPartida().getNaviosIA();
            this.selectorNavios = proxy.getConserje().getRecuerdos().get(indice).getPartida().getSelectorNavios();
            this.casillasJugador = proxy.getConserje().getRecuerdos().get(indice).getPartida().getCasillasJugador();
            this.casillasIA = proxy.getConserje().getRecuerdos().get(indice).getPartida().getCasillasIA();
            this.posiciones = proxy.getConserje().getRecuerdos().get(indice).getPartida().getPosiciones();
            this.contadorTurnos = proxy.getConserje().getRecuerdos().get(indice).getPartida().getContadorTurnos();
            this.contadorTurnosAux = proxy.getConserje().getRecuerdos().get(indice).getPartida().getContadorTurnosAux();
            this.numeroTurnos = new JLabel(Integer.toString(contadorTurnos),SwingConstants.CENTER);
            this.haUtilizadoHabilidadEspecial = proxy.getConserje().getRecuerdos().get(indice).getPartida().getHaUtilizadoHabilidadEspecial();
            this.esVisibleColocar = proxy.getConserje().getRecuerdos().get(indice).getPartida().getEsVisibleColocar();
            this.esVisibleAtacar = proxy.getConserje().getRecuerdos().get(indice).getPartida().getEsVisibleAtacar();
            this.naviosHundidos = proxy.getConserje().getRecuerdos().get(indice).getPartida().getNaviosHundidos();
            
            // Construimos el Tablero en el modo de restaurar la Partida guardada (1).
            construirTablero(numeroFilas, numeroColumnas, 1);
        }
        else { // No hay Partida guardada.
            
            // Inicializamos todos los elementos necesarios para empezar una nueva
            // Partida.
            this.naviosJugador = new ArrayList<>();
            this.naviosIA = new ArrayList<>();           
            this.selectorNavios = new JComboBox();
            this.nombreNavios = new String[]{"Corbeta", "Destructor", "Acorazado", "Portaaviones", "Submarino"};
            this.esVisibleColocar = true;
            this.esVisibleAtacar = false;
            this.casillasJugador = new LinkedHashMap<>();
            this.casillasIA = new LinkedHashMap<>();
            this.posiciones = new ArrayList<>();
            this.contadorTurnos = 1;
            this.numeroTurnos = new JLabel(Integer.toString(contadorTurnos),SwingConstants.CENTER);
            this.contadorTurnosAux = 0;
            this.haUtilizadoHabilidadEspecial = false;
            this.naviosHundidos = new ArrayList<>();

            // Construimos el Tablero en el modo de no restaurar la Partida guardada (0).
            construirTablero(numeroFilas, numeroColumnas, 0); 
        }
        // Pedimos al Jugador que seleccione la apariencia del Tablero.
        seleccionarApariencia();
        // Construimos el chat en el Tablero.
        construirChat();
        // Iniciamos el Tablero y lo ponemos al máximo tamaño disponible de la
        // pantalla.
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * Método encargada de verificar a través del Proxy si existe o no una
     * Partida guardada asociada al Jugador logueado.
     * 
     * @return Devuelve un 1 en caso de que exista y un -1 en caso de que no.
     */
    public int existeRecuerdo() {
        int existeRecuerdo = -1;
        // Recorremos los recuerdos (partidas) almacenados.
        for(int i = 0 ;  i < proxy.getConserje().getRecuerdos().size() ; i++) {
            // Verificamos si hay alguna Partida asociada al Jugador actual.
            if(proxy.getConserje().getRecuerdos().get(i).getPartida().getJugador().equals(jugador))
                existeRecuerdo = i;
        }
        return existeRecuerdo;
    }
    
    /**
     * Método encargado de construir todo el Tablero de manera dinámica.
     *  
     * @param numFilas Número de filas del Tablero.
     * @param numColumnas Número de columnas del Tablero.
     * @param modo Modo de restaurar Partida (1) o no (0).
     */
    @Override
    public void construirTablero(int numFilas, int numColumnas, int modo) {
        // Establecemos el número total de filas y columnas que tendrá
        // todo el Tablero (incluidos los dos tableros de juego, Jugador e IA).
        int filasTablero = numFilas + 1;
        int columnasTablero = numColumnas*2 + 2;
        // Dimension de cada casilla del tablero de juego.
        int tamLabel = 30;
        // Posiciones iniciales del primer componente.
        int posX = tamLabel;
        int posY = 0;
        // Contadores para comenzar las coordenadas del tablero de juego.
        int contadorLetras = 65; // La A en ASCII.
        int contadorNumeros = 1;
        
        // Recorremos las filas y columnas del tablero de juego.
        for(int i=0;i<filasTablero;i++){
            for(int j=0;j<columnasTablero;j++){
                
                // Para dejar espacio entre ambos tableros de juego (IA y Jugador).
                if(j != columnasTablero / 2){
                    // Creamos la casilla.
                    JLabel l = new JLabel();
                    if(j > 0 && i == 0) { // Primera fila. Números.
                        
                        l.setBounds(posX,posY,tamLabel,tamLabel);
                        l.setText(Character.toString((char) contadorLetras));
                        l.setForeground(Color.WHITE);
                        l.setHorizontalAlignment(SwingConstants.CENTER);
                        l.setVerticalAlignment(SwingConstants.CENTER);                  
                        Font f = l.getFont();
                        l.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
                        // Incluimos la casilla en el Tablero y pasamos a la
                        // siguiente letra.
                        this.add(l);
                        contadorLetras++;
                    }
                    
                    else if(j == 0 && i >= 0) { // Primera columna. Letras.
                        
                        l.setBounds(posX,posY,tamLabel,tamLabel);
                        if(j == 0 && i == 0) // En el primer hueco no ponemos nada.
                            l.setText("");
                        else {
                            Font f = l.getFont();
                            l.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
                            l.setText(Integer.toString(contadorNumeros));
                            l.setForeground(Color.WHITE);
                            // Pasamos al siguiente número.
                            contadorNumeros++;  
                        }
                        l.setHorizontalAlignment(SwingConstants.CENTER);
                        l.setVerticalAlignment(SwingConstants.CENTER);
                        // Incluimos la casilla en el Tablero.
                        this.add(l);                         
                    }
                    // Una vez puestos los marcos con las letras y números,
                    // pasaremos a las casillas de juego propiamente dichas.
                    else{
                        if(modo == 0) // En el caso de comenzar un Tablero de cero.
                        {
                            l.setBounds(posX,posY,tamLabel,tamLabel);
                            if(j > columnasTablero / 2){ // Tablero juego IA.
                                // Establecemos la casilla libre, el icono y la
                                // incluimos dentro de la colección de casillas
                                // de la IA.
                                l.setName("LIBRE");
                                l.setIcon(new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTablero.png")));
                                int numLetra = 64 + Math.abs(j - columnasTablero/2);
                                casillasIA.put(Character.toString((char) numLetra) + i, l);
                            }
                            else{ // Tablero juego Jugador.
                                // Establecemos la casilla libre, el icono y la
                                // incluimos dentro de la colección de casillas
                                // del Jugador.
                                l.setName("LIBRE");
                                l.setIcon(new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTablero.png")));
                                int numLetra = 64 + j;
                                casillasJugador.put(Character.toString((char) numLetra)+i, l);
                            }
                            // Incluimos la casilla en el Tablero.
                            this.add(l);
                        }
                    }                    
                } 
                // Reiniciamos el contador de las letras para construir el
                // tablero de juego de la IA.
                else contadorLetras = 65;
                // Actualizamos la posición x de colocación de las siguientes casillas.
                posX = posX + tamLabel;
            }
            // Actualizamos la posición de colocación de las siguientes casillas.
            posX = tamLabel;
            posY = posY + tamLabel;
        }
        // Incluimos el tituloNavios.
        Font fontTituloNavios = tituloNavios.getFont();
        tituloNavios.setFont(fontTituloNavios.deriveFont(fontTituloNavios.getStyle() | Font.BOLD));
        tituloNavios.setForeground(Color.WHITE);
        tituloNavios.setBounds(tamLabel * 2, posY + 5, 110, 20);
        this.add(tituloNavios);
        // Incluimos el botón Colocar.
        colocar.setBounds((tamLabel * 2) + 115, posY + 25, 80, 20);
        colocar.addActionListener(this);
        colocar.setActionCommand("Colocar");
        this.add(colocar);
        colocar.setVisible(esVisibleColocar);
        // Incluimos el botón Atacar.
        atacar.setBounds((tamLabel * 2) + 115, posY + 25, 80, 20);
        atacar.addActionListener(this);
        atacar.setActionCommand("Atacar");
        this.add(atacar);
        atacar.setVisible(esVisibleAtacar);
        // Incluimos el tituloTurnos.
        Font fontTituloTurnos = tituloTurnos.getFont();
        tituloTurnos.setFont(fontTituloTurnos.deriveFont(fontTituloTurnos.getStyle() | Font.BOLD));
        tituloTurnos.setForeground(Color.WHITE);
        tituloTurnos.setBounds((tamLabel * 2) + 200, posY + 5, tamLabel * 2, 20);
        this.add(tituloTurnos);
        // Incluimos el numeroTurnos.
        Font fontNumeroTurnos = numeroTurnos.getFont();
        numeroTurnos.setFont(fontNumeroTurnos.deriveFont(fontNumeroTurnos.getStyle() | Font.BOLD));
        numeroTurnos.setBounds((tamLabel * 2) + 200, posY + 25, tamLabel * 2, 20);
        numeroTurnos.setForeground(Color.WHITE);
        this.add(numeroTurnos);
        // Incluimos el chat. Lo único que hacemos es colocarlo en el Tablero
        // con respecto al resto de componentes.
        scrollChat.setBounds(tamLabel * (columnasTablero/2 + 1) + tamLabel, posY + 5, numeroFilas * tamLabel, 50);
        scrollChat.setFocusable(false);
        chatEscrito.setBounds(tamLabel * (columnasTablero/2 + 1) + tamLabel, posY + 55, numeroFilas * tamLabel, 40);
        
        // Dependiendo de si hay o no Partida guardada.
        if(modo == 1) // Hay Partida guardada.
            restaurarTablero();
        else { // No hay Partida guardada.
            // Incluimos el selector de navios con todos los navios ya que estamos
            // empezando una nueva Partida.
            selectorNavios.setBounds(tamLabel * 2, posY + 25, 110, 20);
            DefaultComboBoxModel modelNavios = new DefaultComboBoxModel(nombreNavios);
            selectorNavios.setModel(modelNavios);
            this.add(selectorNavios);
        }
    }
    
    /**
     * Método que se encarga de restaurar el Tablero de una Partida guardada.
     * 
     */
    public void restaurarTablero() {
        // Incluye el selector de navios y el número de turnos que hemos
        // cargado anteriormente.
        this.add(selectorNavios);
        this.add(numeroTurnos);
        
        // Establece las casillas cargadas anteriormente en el Tablero.
        casillasJugador.values().forEach((casilla) -> {
            this.add(casilla);
        });
        
        casillasIA.values().forEach((casilla) -> {
            this.add(casilla);
        });
    }
    
    /**
     * Método encargado de cambiar la apariencia en el Tablero antes de comenzar
     * o reanudar una Partida.
     * 
     */
    public void seleccionarApariencia() {
        // Creamos la ventana de selección de apariencia y la iniciamos.
        VentanaApariencia ventanaApariencia = new VentanaApariencia(casillasJugador, casillasIA, this, this);
        ventanaApariencia.setVisible(true);
        ventanaApariencia.setAlwaysOnTop(true);
    }
    
    /**
     * Método encargado de establecer los componentes y sus propiedades para
     * inicializar el chat cuando se construya el Tablero.
     * 
     */
    private void construirChat() {          
        chat.setEditable(false);
        doc = chat.getStyledDocument();
        style = chat.addStyle("Style", null);
        this.add(scrollChat);
        chatEscrito.setForeground(Color.GREEN);
        chatEscrito.setBackground(Color.BLACK);
        chatEscrito.addKeyListener(this);
        this.add(chatEscrito);
    }
    
    /**
     * Método encargado de devolver la única instancia de Tablero en caso de
     * existir y si no la crea antes de devolverla.
     * 
     * @param jugador Jugador presente en la Partida.
     * @param ia IA presente en la Partida.
     * @param mediador Intermediario entre el Jugador y la IA para la comunicación.
     * @param ventanaSeleccion Ventana Selección anterior a la ventana actual.
     * @param proxy Proxy encargado de la comunicación entre el cliente y el Servidor.
     * @return La única instancia de Tablero.
     */
    public static Tablero getInstanciaTablero(Jugador jugador, IA ia, Mediador mediador,
                                              VentanaSeleccion ventanaSeleccion, Servidor proxy) {
        // En caso de no existir, la crea.
        if(instanciaTablero == null){
            instanciaTablero = new Tablero(jugador, ia, mediador, ventanaSeleccion, proxy);
        }
        return instanciaTablero;
    }
    
    /**
     * Método encargado de borrar la única instancia de Tablero.
     * 
     */
    public void setInstanciaTablero() {
        instanciaTablero = null;
    }
    
    /**
     * Método encargado de devolver el atributo numeroFilas.
     * 
     * @return Número de filas del Tablero.
     */
    public int getNumeroFilas() {
        return this.numeroFilas;
    }
    
    /**
     * Método encargado de devolver el atributo numeroColumnas.
     * 
     * @return Número de columnas del Tablero.
     */
    public int getNumeroColumnas() {
        return this.numeroColumnas;
    }    
    
    /**
     * Método encargado de devolver el atributo casillasJugador.
     * 
     * @return Colección de las casillas del Jugador junto con el JLabel
     *         correspondiente de cada una del Tablero en la Partida.
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
     * Método encargado de devolver el atributo naviosIA.
     * 
     * @return Navios de la IA en la Partida.
     */
    public ArrayList<Navio> getNaviosIA() {
        return this.naviosIA;
    }
    
    /**
     * Método encargado de devolver el atributo haUtilizadoHabilidadEspecial.
     * 
     * @return Booleano que indica si se ha utilizado o no el ataque especial de un Navio.
     */
    public boolean getHaUtilizadoHabilidadEspecial() {
        return this.haUtilizadoHabilidadEspecial;
    }

    /**
     * Método encargado de establecer el valor del atributo haUtilizadoHabilidadEspecial.
     * 
     * @param haUtilizadoHabilidadEspecial Booleano que indica si se ha utilizado
     * o no el ataque especial de un Navio.
     */
    public void setHaUtilizadoHabilidadEspecial(boolean haUtilizadoHabilidadEspecial) {
        this.haUtilizadoHabilidadEspecial = haUtilizadoHabilidadEspecial;
    }
    

    
    /**
     * Método encargado de verificar si alguna de las coordenadas pasadas por
     * parámetro están ocupadas por algún Navio o no en las casillas de la IA.
     * 
     * @param coordenadas Casillas a verificar.
     * @return Booleano que indica si alguna de esas coordenadas está ocupada o no.
     */
    public boolean casillasOcupadas(LinkedHashMap<String, Boolean> coordenadas) {
        boolean casillaOcupada = false;
        
            for(String coordenada : coordenadas.keySet()) {
                try{
                    if(casillasIA.get(coordenada).getName().equals("OCUPADO"))
                        casillaOcupada = true;
                }
                catch(NullPointerException e) {}
        }
        return casillaOcupada;
    }
    
    /**
     * Método encargado de generar coordenadas en vertical para colocar los navios
     * de la IA.
     * 
     * @param numCoordenadas Número de coordenadas a generar.
     * @return Colección con las coordenadas generadas junto con un booleano que
     * indicará si esa coordenada ha sido atacada o no.
     */
    public LinkedHashMap<String, Boolean> generarCoordenadasVertical(int numCoordenadas) {
        Random r = new Random();
        // Establecemos los límites de las letras (de la A a la T) de las coordenadas.
        int low = 65; // A
        int high = 85; // U, aunque trabajaremos hasta la T.
        // Colección donde guardaremos las coordenadas generadas.
        int result;
        
        LinkedHashMap<String, Boolean> coordenadas = new LinkedHashMap<>();
        // Límites para iniciar la generación de coordenadas en vertical.
        int ini = 1;
        int fin = (20 - numCoordenadas) + 1;
        int inicio;
        // Generamos las coordenadas hasta que todas ellas sean correctas para
        // ser colocadas.
        do {
            coordenadas.clear(); // Vaciamos la colección.
            inicio = r.nextInt(fin-ini) + ini; // Generamos el primer número.
            result = r.nextInt(high-low) + low; // Generamos la primera letra.
            String letra = Character.toString((char) result);
            // Como son contiguas las casillas a la hora de colocar navios, una
            // vez generada la primera coordenada es ir sumando uno al número
            // que hemos generado (vertical).
            for(int i = 0; i < numCoordenadas; i++) {
                coordenadas.put(letra + inicio, false);
                inicio++;
            }
        }while(casillasOcupadas(coordenadas));
        
        return coordenadas;
    }
    
    /**
     * Método encargado de generar coordenadas en horizontal para colocar los navios
     * de la IA.
     * 
     * @param numCoordenadas Número de coordenadas a generar.
     * @return Colección con las coordenadas generadas junto con un booleano que
     * indicará si esa coordenada ha sido atacada o no.
     */
    public LinkedHashMap<String, Boolean> generarCoordenadasHorizontal(int numCoordenadas) {
        Random r = new Random();
        // Establecemos los límites de las letras (de la A a la T) de las coordenadas.
        int low = 65;
        int high = (85 - numCoordenadas) + 1;
        // Colección donde guardaremos las coordenadas generadas.
        int result;
        
         LinkedHashMap<String, Boolean> coordenadas = new LinkedHashMap<>();
        // Límites para iniciar la generación de coordenadas en vertical.
        int ini = 1;
        int fin = 21;
        int inicio;
        
        do {
            coordenadas.clear(); // Vaciamos la colección.
            result = r.nextInt(high-low) + low; // Generamos el primer número.
            inicio = r.nextInt(fin-ini) + ini; // Generamos la primera letra.
            String letra = Character.toString((char) result);
            // Como son contiguas las casillas a la hora de colocar navios, una
            // vez generada la primera coordenada es ir sumando uno a la letra
            // que hemos generado y convertirla de ASCII a letra (horizontal).
            for(int i = 0; i < numCoordenadas; i++) {
                coordenadas.put(letra + inicio, false);
                result++;
                letra = Character.toString((char) result);
            }
        }while(casillasOcupadas(coordenadas));
        return coordenadas;
    }
    
    /**
     * Método encargado de buscar un Navio concreto entre los navios del Jugador
     * y devolver la posición que ocupa.
     * 
     * @param nombreNavio Nombre del Navio a buscar.
     * @return Número que indica la posición del Navio en la colección naviosJugador.
     */
    public int buscarElemento(String nombreNavio) {
        int indice = 0;
        for(Navio navioJugador : naviosJugador) {
            if(!navioJugador.getNombre().equals(nombreNavio))
                indice++;
        }
        return indice;
    }
    
    /**
     * Método encargado llamar a todos los métodos necesarios para realizar la
     * colocación del Navio al pulsar el botón Colocar o realizar el ataque al
     * pulsar el botón de Atacar.
     * 
     * @param evt Evento del botón Colocar o Atacar.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Obtenemos el comando.
        String comando = evt.getActionCommand();
        // Obtenemos el Navio seleccionado por el Jugador.
        String tipoNavio = selectorNavios.getSelectedItem().toString();
        ArrayList<JLabel> coordenadasAtaqueIA = new ArrayList<>();
        if(comando.equals("Colocar")) { // Pulsar botón Colocar.
            // Inicializamos la Ventana de Colocar.
            JButton botonColocar = (JButton) evt.getSource();
            VentanaColocar ventanaColocar = new VentanaColocar(tipoNavio, casillasJugador, posiciones);
            ventanaColocar.setVisible(true);
            // Utilizamos un hilo para esperar mientras el Jugador está introduciendo
            // las casillas donde quiere colocar el Navio correspondiente.
            // Después obtenemos las posiciones y colocamos los navios tanto del
            // Jugador como de la IA.
            Thread t = new Thread() {
            @Override
            public void run() {
                    while (ventanaColocar.isVisible()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    // Obtenemos las posiciones introducidas.
                    LinkedHashMap<String,Boolean> posicionesAux = new LinkedHashMap<>();
                    for (String posicion : posiciones) {
                        posicionesAux.put(posicion, false);
                    }
                    // Creamos el Navio del Jugador con esas posiciones.
                    Navio navio = factoriaNavios.getNavio(tipoNavio, tipoNavio, posicionesAux);
                    // Realizamos la copia del Navio del Jugador cambiandole las
                    // casillas para asociarlo a la IA.
                    Navio navioCopia = (Navio) navio.copia();
                    
                    Random r = new Random();
                    if((r.nextInt(3-1) + 1) == 1)
                        navioCopia.setPosiciones(generarCoordenadasVertical(navioCopia.getCasillas()));
                    else
                        navioCopia.setPosiciones(generarCoordenadasHorizontal(navioCopia.getCasillas()));
                    
                    navioCopia.setNombre(tipoNavio + ":IA");
                    // Incluimos ambos navios en sus respectivas colecciones.
                    naviosJugador.add(navio);
                    naviosIA.add(navioCopia);
                    // Vaciamos las posiciones obtenidas.
                    posiciones.clear();
                    // Actualizamos el estado interno de las casillas del Navio
                    // de la IA.
                    navioCopia.getPosiciones().keySet().forEach((coordenada) -> {
                        casillasIA.get(coordenada).setName("OCUPADO");
                    });
                    // Eliminamos del selector de navios el Navio colocado.
                    selectorNavios.removeItem(tipoNavio);
                    // En caso de haber seleccionado el último Navio reseteamos
                    // el selector de navios para poder atacar una vez colocados
                    // todos los navios tanto de la IA como del Jugador.
                    if(selectorNavios.getItemCount() == 0) {
                        String[] nombreNavios = new String[]{"Corbeta", "Destructor", "Acorazado", "Portaaviones", "Submarino"};
                        esVisibleColocar = false;
                        colocar.setVisible(esVisibleColocar);
                        DefaultComboBoxModel modelNavios = new DefaultComboBoxModel(nombreNavios);
                        selectorNavios.setModel(modelNavios);
                        esVisibleAtacar = true;
                        atacar.setVisible(esVisibleAtacar);
                    }
            }
        };
        t.start();
        } else if(comando.equals("Atacar")) { // Pulsar el botón Atacar.

            // Cuando hayan pasado 5 turnos podemos permitir al Jugador que
            // vuelva a utilizar el ataque especial de cualquiera de los navios
            // disponibles.
            if(contadorTurnosAux + 5 == contadorTurnos) {
                setHaUtilizadoHabilidadEspecial(false);
                contadorTurnosAux = 0;
            }
            // Inicializamos la Ventana de Atacar.
            VentanaAtacar ventanaAtacar = new VentanaAtacar(naviosJugador.stream().filter(navio -> navio.getNombre().equals(selectorNavios.getSelectedItem().toString())).findFirst().get(), casillasIA, casillasJugador, getHaUtilizadoHabilidadEspecial());
            ventanaAtacar.setVisible(true);
            
            // Utilizamos un hilo para esperar mientras el Jugador está introduciendo
            // las casillas donde quiere atacar con el Navio correspondiente.
            // Después obtenemos las posiciones y realizamos el ataque tanto del
            // Jugador como de la IA.
            Thread t = new Thread() {
            @Override
            public void run() {
                    while (ventanaAtacar.isVisible()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    // Establecemos si ha utilizado el ataque especial o no.
                    // Además, actualizamos el número de turnos del contador
                    // de habilidades.
                    setHaUtilizadoHabilidadEspecial(ventanaAtacar.getHaUtilizadoHabilidadEspecial());
                    if(ventanaAtacar.getHaUtilizadoHabilidadEspecial() && contadorTurnosAux == 0)
                        contadorTurnosAux = contadorTurnos;
                    // Actualizamos los estados de los navios de la IA con las
                    // posiciones de ataque establecidas por el Jugador.
                    actualizarEstadoNaviosIA(ventanaAtacar);
                    // Actualizamos las estadísticas del Juagdor.
                    actualizarEstadisticas();
                    if(!comprobarFinDePartida()) { // Si la Partida no ha finalizado.
                        // Realizamos el ataque de la IA.
                        elegirAtaqueIA().atacar();
                        // Actualizamos los estados de los navios del Jugador.
                        actualizarEstadoNaviosJugador();
                        // Comprobamos si la partida ha finalizado.
                        comprobarFinDePartida();
                        // Aumentamos en uno el número de turnos.
                        numeroTurnos.setText(Integer.toString(++contadorTurnos));
                    }
                }
            };
            t.start();
        }
    }
    
    /**
     * Método encargado de actualizar las estadísticas del Jugador en función de
     * los navios que ha hundido de la IA.
     * 
     */
    public void actualizarEstadisticas() {
        // Recorremos los navios de la IA.
        for(Navio navioIA : naviosIA) {
            // Si no se ha hundido el navio y no esta incluida en el listado de navios hundidos
            if(estaHundido(navioIA.getPosiciones()) && !naviosHundidos.contains(navioIA.getNombre())) {
                // Actualizamos las estadísticas del Navio concreto.
                jugador.getEstadisticas().setTotalBarcosHundidos(jugador.getEstadisticas().getTotalBarcosHundidos() + 1);
                switch(navioIA.getNombre()) {
                    case "Corbeta:IA":
                        jugador.getEstadisticas().setTotalBarcosHundidosPorCategorias(0);
                        break;
                    case "Destructor:IA":
                        jugador.getEstadisticas().setTotalBarcosHundidosPorCategorias(1);
                        break;
                    case "Acorazado:IA":
                        jugador.getEstadisticas().setTotalBarcosHundidosPorCategorias(2);
                        break;
                    case "Portaaviones:IA":
                        jugador.getEstadisticas().setTotalBarcosHundidosPorCategorias(3);
                        break;
                    case "Submarino:IA":
                        jugador.getEstadisticas().setTotalBarcosHundidosPorCategorias(4);
                        break;
                }
                naviosHundidos.add(navioIA.getNombre());
            }
        }
    }
    
    /**
     * Método encargado de comprobar si un Navio está hundido o no.
     * 
     * @param casillas Colección de casillas donde se sitúa el Navio junto con
     * un booleano que indica si la casilla ha sido dada o no.
     * @return Booleano que indica si el Navio está hundido o no.
     */
    public boolean estaHundido(LinkedHashMap<String, Boolean> casillas) {
        int contador = 0;
        for (Boolean casilla : casillas.values()) {
            if(casilla == true)
                contador++;
        }  
        return contador == casillas.size();
    }
    
    /**
     * Método encargado de seleccionar el ataque que va a realizar la IA.
     * 
     * @return Estrategia de Ataque a relizar por la IA.
     */
    public EstrategiaAtaqueIA elegirAtaqueIA() {
        
        ArrayList<String> navios = new ArrayList<>();
        for(Navio navioIA : naviosIA){
            if(!estaHundido(navioIA.getPosiciones()))
               navios.add(navioIA.getNombre());
        }
        
        Random indice = new Random();
        // Obtenemos los nombres de los navios restantes de la IA con los que
        // puede atacar.
        
        // Obtenemos un Navio de forma aleatoria.   
        switch(navios.get(indice.nextInt(navios.size()))) {
            // Dependiendo del Navio elegido realizamos su ataque que puede ser
            // normal o especial. Para ello, generamos las estrategia concreta
            // de cada Navio.
            case "Corbeta:IA":
                estrategiaAtaqueIA = new EstrategiaConcretaCorbeta(this);
                break;
            case "Destructor:IA":
                estrategiaAtaqueIA = new EstrategiaConcretaDestructor(this);
                break;
            case "Acorazado:IA":
                estrategiaAtaqueIA = new EstrategiaConcretaAcorazado(this);
                break;
            case "Portaaviones:IA":
                estrategiaAtaqueIA = new EstrategiaConcretaPortaaviones(this);
                break;
            case "Submarino:IA":
                estrategiaAtaqueIA = new EstrategiaConcretaSubmarino(this);
                break;
        }
        
        return estrategiaAtaqueIA;
    }
    
    /**
     * Método encargado de actualizar tanto internamente como estéticamente
     * el estado de los navios del Jugador en la Partida.
     * 
     */
    public void actualizarEstadoNaviosJugador() {
        // Establecemos los iconos necesarios.
        Icon iconoTocado = new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroTocado.png"));
        Icon iconoSubmarinoSumergido = new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroSubmarinoSumergido.png"));
        Icon imagenSubmarino = new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroSubmarino.png"));
        // Recorremos los navios del Jugador.
        for (Navio navioJugador : naviosJugador) {
            // Copiamos las posiciones del Navio del Juagdor para no modifificar
            // las originales.
            LinkedHashMap<String, Boolean> posiciones = navioJugador.getPosiciones();
            // Recorremos las casillas de ataque generadas por la estrategia.
            for(String coordenadaDeAtaque : estrategiaAtaqueIA.getCasillasDeAtaque()) {
                // En caso de haber sido tocado actualizamos las coordenadas
                // del Navio poniendo a true el booleano que indica que ha sido
                // tocado.
                if(casillasJugador.get(coordenadaDeAtaque).getIcon().toString().equals(iconoTocado.toString())) {
                    posiciones.replace(coordenadaDeAtaque, true);
                    navioJugador.setPosiciones(posiciones);
                }
            }
            // Si el Navio está hundido lo quitamos del selector de navios ya que
            // el Jugador no podrá utilizar dicho Navio para atacar.
            if(estaHundido(posiciones)) selectorNavios.removeItem(navioJugador.getNombre());
            // Por último, recorremos las posiciones del Navio ya que en caso de
            // que sea el Submarino debemos sacarlo a flote cambiando tanto el
            // aspecto estético como interno.
            for(String posicion : posiciones.keySet()) {
               if(casillasJugador.get(posicion).getIcon().toString().equals(iconoSubmarinoSumergido.toString())
                       && !navioJugador.getPosiciones().containsValue(true)) {
                    casillasJugador.get(posicion).setName("OCUPADO");
                    casillasJugador.get(posicion).setIcon(imagenSubmarino);
                } 
            }
        }
    }
    
    /**
     * Método encargado de actualizar tanto internamente como estéticamente
     * el estado de los navios de la IA en la Partida.
     * 
     * @param ventanaAtacar Ventana donde se recorren tanto el ataque realizado
     *                      por el Jugador como las coordenadas del ataque.
     */
    public void actualizarEstadoNaviosIA(VentanaAtacar ventanaAtacar) {
        // Establecemos los iconos necesarios.
        Icon iconoTocado = new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroTocado.png"));
        Icon iconoSubmarinoSumergido = new ImageIcon(getClass().getResource("/interfaz/imagenes/CasillaTableroSubmarinoSumergido.png"));
        // Recorremos los navios de la IA.
        for (Navio navioIA : naviosIA) {
            // Copiamos las posiciones del Navio de la IA para no modifificar
            // las originales.
            LinkedHashMap<String, Boolean> posiciones = navioIA.getPosiciones();
            for(String coordenadaDeAtaque : ventanaAtacar.getCoordenadasAtaque()) {
                // En caso de haber sido tocado actualizamos las coordenadas
                // del Navio poniendo a true el booleano que indica que ha sido
                // tocado.
                if(casillasIA.get(coordenadaDeAtaque).getIcon().toString().equals(iconoTocado.toString())) {
                    posiciones.replace(coordenadaDeAtaque, true);
                    navioIA.setPosiciones(posiciones);
                }
                
            }
            // Por último, recorremos las posiciones del Navio ya que en caso de
            // que sea el Submarino debemos sacarlo a flote cambiando tanto el
            // aspecto estético como interno.
            for(String posicion : posiciones.keySet()) {
               if(navioIA.getNombre().equals("Submarino:IA")) {
                    casillasIA.get(posicion).setName("OCUPADO");
                } 
            }
        }
    }
    
    /**
     * Método encargado de comprobar si la Partida ha finalizado o no.
     * 
     * @return Booleano que indica si la Partida ha finalizado o no.
     */
    public boolean comprobarFinDePartida() {

        boolean partidaTerminada = false;

        // En caso de todos los navios de la IA estén hundidos la Partida habrá
        // finalizado dando la victoria al Jugador.
        if(obtenerTotalNaviosHundidosPorJugador() == 5 && obtenerTotalNaviosHundidosPorIA() != 5) {
            JOptionPane.showMessageDialog(this, "Victoria del jugador", "Informacion" , JOptionPane.INFORMATION_MESSAGE);
            // Actualizamos las estadísticas del Jugador, hacemos visible la
            // ventana de selección, borramos la insancia del Tablero y damos
            // la Partida por finalizada cerrando la ventana del Tablero.
            int numeroVictorias = jugador.getEstadisticas().getNumeroDeVictorias();
            jugador.getEstadisticas().setNumeroDeVictorias(numeroVictorias++);
            ventanaSeleccion.setVisible(true);
            this.setInstanciaTablero();
            partidaTerminada = true;
            this.dispose(); 

        } // En caso contrario se da la victoria a la IA.
        else if(obtenerTotalNaviosHundidosPorIA() == 5 && obtenerTotalNaviosHundidosPorJugador() != 5){
            JOptionPane.showMessageDialog(this, "Victoria de IA", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            // Hacemos visible la ventana de selección y damos la Partida por 
            // finalizada cerrando la ventana del Tablero.
            ventanaSeleccion.setVisible(true);
            partidaTerminada = true;
            this.dispose();
        }

        return partidaTerminada;
    }
    
    /**
     * Método encargado de calcular el número de navios hundidos por la IA en
     * la Partida.
     * 
     * @return Número de navios hundidos por la IA.
     */
    public int obtenerTotalNaviosHundidosPorIA() {
        // Inicializamos el contador.
        int contadorNaviosHundidosPorIA = 0;
        // Creamos un iterador para recorrer los navios del Jugador.
        Iterator<Navio> iteradorNavios = naviosJugador.iterator();
        // Recorremos los navios
        while(iteradorNavios.hasNext()) {
            Navio navio = iteradorNavios.next();
            if(estaHundido(navio.getPosiciones()))
                contadorNaviosHundidosPorIA++;    
        }
        return contadorNaviosHundidosPorIA;
    }
    
    /**
     * Método encargado de calcular el número de navios hundidos por el Jugador
     * en la Partida.
     * 
     * @return Número de navios hundidos por el Jugador.
     */
    public int obtenerTotalNaviosHundidosPorJugador() {
        // Inicializamos el contador.
        int contadorNaviosHundidosPorJugador = 0;
        // Creamos un iterador para recorrer los navios del Jugador.
        Iterator<Navio> iteradorNavios = naviosIA.iterator();
        // Recorremos los navios
        while(iteradorNavios.hasNext()) {
            Navio navio = iteradorNavios.next();
            if(estaHundido(navio.getPosiciones()))
                contadorNaviosHundidosPorJugador++;
        }     
        return contadorNaviosHundidosPorJugador;
    }
    
    /**
     * Método encargado de detectar el Enter en el chat para enviar los mensajes
     * a la IA.
     * 
     * @param evt Evento de pulsar la tecla Enter.
     */
    @Override
    public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) { //Detectamos el Enter.
            try {
                // Obtenemos el mensaje escrito en el chat y lo enviamos a la IA.
                jugador.enviar(ia.getUsuario(), chatEscrito.getText());
                // Establecemos los colores del chat al escribir lo del Jugador.
                StyleConstants.setForeground(style, Color.red);
                doc.insertString(doc.getLength(), "\n" + jugador.getUsuario() + ": " + chatEscrito.getText() , style);
                // La IA contesta al Jugador.
                ia.enviar(jugador.getUsuario(), chatEscrito.getText());
                // Establecemos los colores del chat al escribir lo de la IA.
                StyleConstants.setForeground(style, Color.blue);
                doc.insertString(doc.getLength(), "\n" + ia.getUsuario() + ": " + ia.getMensajeAEnviar(), style);
                // Vaciamos el chat donde hemos escrito.
                chatEscrito.setText("");
  
            } catch (BadLocationException ex) {
                Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void decorarTablero(LinkedHashMap<String, JLabel> casillasJugador, 
                               LinkedHashMap<String, JLabel> casillasIA, JFrame ventana) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("BATTLEVA: TABLERO");
        setMinimumSize(new java.awt.Dimension(850, 750));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Método encargado de guardar la Partida antes de cerrar la Ventana del
     * Tablero una vez pulsado la cruz de cerrar.
     * 
     * @param evt Evento de cerrar la Ventana Tablero en la cruz.
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // Creamos una Partida donde guardaremos todo lo necesario para poder
        // reanudarla posteriormente.
        JOptionPane.showMessageDialog(this, "Antes de salir, se guardará la partida...", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        Partida partida = new Partida(jugador, naviosJugador, naviosIA, selectorNavios,
        casillasJugador, casillasIA, posiciones, contadorTurnos, contadorTurnosAux, 
        haUtilizadoHabilidadEspecial, esVisibleColocar, esVisibleAtacar, naviosHundidos);
        // Creamos el Recuerdo con la Partida.
        proxy.getConserje().addRecuerdo(new Recuerdo(partida));
        // Realizamos la copia del Servidor para guardar los cambios.
        proxy.backupServidor();
        // Cerramos el Tablero e inicalizamos la Ventana de Selección.
        this.dispose();
        ventanaSeleccion.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}