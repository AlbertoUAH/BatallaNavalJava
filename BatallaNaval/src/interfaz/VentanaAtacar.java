package interfaz;

import codigoNegocio.Navio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * VentanaAtacar: Ventana encargada del ataque de los navios.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class VentanaAtacar extends javax.swing.JFrame implements ActionListener{

    // Tipo de Navio que va a ser colocado.
    private Navio tipoNavio;
    // Colección de las casillas del contrincante junto con el JLabel correspondiente
    // de cada una del Tablero en la Partida.
    private LinkedHashMap<String, JLabel> casillasContrincante;
    // Colección de las casillas del Jugador junto con el JLabel correspondiente
    // de cada una del Tablero en la Partida.
    private LinkedHashMap<String, JLabel> casillasJugador;
    // Booleano que indica si se ha utilizado o no el ataque especial de un Navio.
    private boolean haUtilizadoHabilidadEspecial;
    // Colección con las posiciones donde se va a colocar el Navio.
    private ArrayList<String> posiciones;
    // Colección de coordenadas de ataque en las que va a atacar el Navio.
    private ArrayList<String> coordenadasAtaque;
    // Colección de JComboBox en los cuales se selecciona las coordenadas a
    // atacar.
    private ArrayList<JComboBox> jcombobox;
    // Colección de JTextFields en los cuales se escribe cada una de las
    // coordenadas seleccionadas en los JComboBox.
    private ArrayList<JTextField> jtextfields;
    // JComboBox que permite seleccionar el ataque correspondiente dependiendo
    // del Navio.
    private JComboBox tipoAtaque;
    // Titulo principal de la ventana.
    private JLabel tituloAtaques;
    // Fondo principal de la ventana.
    private JLabel fondo;
    // Botón de aceptar para confirmar las coordenaadas seleccionadas.
    private JButton aceptarButton;
    // Anchura del componente.
    private int anchuraComponente = 150;
    // Altura del componente.
    private int alturaComponente = 25;
    // Posición inicial x del primer componente.
    private int posX = 10;
    // Posición inicial y del primer componente.
    private int posY = 10;
    // Posición inicial x del primer componente (JTextField).
    private int posXTF = posX + anchuraComponente + 5;
    
    /**
     * Constructor.
     * 
     * @param tipoNavio Tipo de Navio que va a ser colocado.
     * @param casillasContrincante Colección de las casillas del contrincante 
     *                             junto con el JLabel correspondiente de cada 
     *                             una del Tablero en la Partida.
     * @param casillasJugador Colección de las casillas del Jugador junto con el
     *                        JLabel correspondiente de cada una del Tablero en 
     *                        la Partida.
     * @param haUtilizadoHabilidadEspecial Booleano que indica si se ha utilizado
     *                                     o no el ataque especial de un Navio.
     */
    public VentanaAtacar(Navio tipoNavio, LinkedHashMap<String, JLabel> casillasContrincante,
                         LinkedHashMap<String, JLabel> casillasJugador, boolean haUtilizadoHabilidadEspecial) {
        this.tipoNavio = tipoNavio;
        this.casillasContrincante = casillasContrincante;
        this.casillasJugador = casillasJugador;
        this.haUtilizadoHabilidadEspecial = haUtilizadoHabilidadEspecial;
        this.jtextfields = new ArrayList<>();
        this.jcombobox = new ArrayList<>();
        this.coordenadasAtaque = new ArrayList<>();
        this.tipoAtaque = new JComboBox();
        this.tituloAtaques = new JLabel("Seleccione el tipo de ataque", SwingConstants.CENTER);
        
        DefaultComboBoxModel modelNavios = new DefaultComboBoxModel(new String[] {"Ataque normal"});
        tipoAtaque.setModel(modelNavios);
        
        Font f = tituloAtaques.getFont();
        tituloAtaques.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        tituloAtaques.setForeground(Color.WHITE);
        
        this.fondo = new JLabel();
        
        // Dependiendo del Navio con el que atacar el JComboBox tipoAtaque tendrá,
        // además del ataque normal, el ataque especial correspondiente.
        switch(tipoNavio.getNombre()) {
            case "Corbeta":
                tipoAtaque.addItem("Lanzamiento de dron");
                break;
            case "Destructor":
                tipoAtaque.addItem("Triple torpedo");
                break;
            case "Acorazado":
                tipoAtaque.addItem("Lanzamiento de misil Tomahawk");
                break;
            case "Portaaviones":
                tipoAtaque.addItem("Ataque aereo");
                break;
            case "Submarino":
                tipoAtaque.addItem("Inmersion");
                break;
        }
        
        tituloAtaques.setBounds(anchuraComponente / 2, posY, anchuraComponente + 35, alturaComponente);
        tipoAtaque.setBounds(anchuraComponente / 2, posY + alturaComponente + 5, anchuraComponente + 35,
                             alturaComponente);
        tipoAtaque.addActionListener(this);
        tipoAtaque.setActionCommand("TipoAtaque");
        this.add(tituloAtaques);
        this.add(tipoAtaque);
        
        // Construimos la ventana con los componentes necesarios.
        construirVentana(1);
        // Incluimos un fondo a la ventana.
        addFondo();
        // Inicializamos y centramos la ventana.
        initComponents();
        centrarVentana(this);
    }

    /**
     * Método encargado de construir e inicializar todos los componentes
     * necesarios para atacar con el Navio correspondinte.
     * 
     * @param numeroComponentes Número de componentes que conforman la ventana.
     */
    public void construirVentana(int numeroComponentes) {
        // Establecemos la nueva posición y después de incluir el título y el
        // JComboBox (tipoAtaque) que permite seleccionar el tipo de ataque.
        posY = posY * 2 + alturaComponente + 25;
        // Copiamos las casillas del Jugador, para no modificar las originales.
        ArrayList<String> casillas = new ArrayList<>();
        casillas.addAll(casillasContrincante.keySet());
        // Generamos los componentes necesarios para la ventana.
        for(int i = 0; i < numeroComponentes; i++) {
            // Generamos el JComboBox.
            JComboBox jComboBox = new JComboBox();
            jComboBox.setBounds(posX, posY, anchuraComponente, alturaComponente);
            jComboBox.setModel(new DefaultComboBoxModel<>(casillas.toArray(new String[0])));
            jComboBox.setName(Integer.toString(i));
            jComboBox.addActionListener(this);
            jcombobox.add(jComboBox);
            // Generamos el JTextField asociado.
            JTextField jTextField = new JTextField();
            jTextField.setBounds(posXTF, posY, anchuraComponente, alturaComponente);
            jTextField.setEditable(false);
            jTextField.setName(Integer.toString(i));
            jtextfields.add(jTextField);
            // Los incluimos en la ventana y actualizamos la posición y para el
            // siguiente componente.
            this.add(jComboBox);
            this.add(jTextField);
            posY = posY + alturaComponente + 5;
        }
        // Generamos el botón Aceptar y lo incluimos en la ventana.
        aceptarButton = new JButton();
        aceptarButton.setBounds(posXTF, posY, anchuraComponente, alturaComponente);
        aceptarButton.setText("Aceptar");
        aceptarButton.addActionListener(this);
        aceptarButton.setActionCommand("Aceptar");
        this.add(aceptarButton);
        // Establecemos las dimensiones de la ventana en función de los componentes
        // generados.
        this.setMinimumSize(new Dimension((anchuraComponente * 2) + 25, posY + alturaComponente + 30));      
    }
    
    /**
     * Método encargado de establecer el fondo de la ventana.
     * 
     */
    public void addFondo() {
        // Si tenía un fondo establecido, lo quitamos.
        if(fondo.getIcon() != null) {
            fondo.setIcon(null);
        } 
        // Establecemos el fondo a la ventana.
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/Radar.jpg")));
        // Lo incluimos en la ventana y le ponemos sus dimensiones.
        this.getContentPane().add(fondo);
        fondo.setBounds(0, 0, this.getSize().width,  this.getSize().height);
    }
    
    /**
     * Método encargado de centrar la ventana en función de la resolución de
     * pantalla actual.
     * 
     * @param frame Ventana actual.
     */
    public void centrarVentana(Window frame) {
        // Obtenemos la dimensión de la pantalla.
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        // Obtenemos las dimensiones para centrar la ventana.
        int anchura = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int altura = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        // Establecemos las dimensiones.
        frame.setLocation(anchura, altura);
    }
    
    /**
     * Método encargado de eliminar los componentes de la ventana cuando el
     * Jugador decide cambiar de tipo de ataque (del normal al especial o
     * vicerversa) mediante el JComboBox de tipoAtaque. Esto se debe a que cada
     * ataque tiene un número determinado de casiillas.
     * 
     */
    public void eliminarComponentes() {
        // Establecemos de nuevo la posición inicial.
        posY = 10;
        // Eliminamos los JTextField y los JComboBox.
        jtextfields.forEach((jtextfield) -> {
            jtextfield.setVisible(false);
            this.remove(jtextfield);
        });
        jtextfields.clear();
        jcombobox.forEach((combobox) -> {
            combobox.setVisible(false);
            this.remove(combobox);
        });
        jcombobox.clear();
        // Eliminamos el botón de aceptar.
        aceptarButton.setVisible(false);
        this.remove(aceptarButton);
    }
    
    /**
     * Método encargado de realizar el ataque especial del Portaaviones.
     * 
     */
    public void realizarAtaqueAereo() {
        Random coordenadaAleatoria = new Random();
        // Establecemos el ASCII del intervalo de letras posibles para las
        // coordenadas de ataque.
        int low = 65;
        int high = 85;
        // Numero y letra de cada coordenada de ataque.
        int coordenadaNumero;
        String coordenadaLetra;
        // Colección donde guardamos las coordenadas de ataque.
        ArrayList<String> coordenadasDeAtaque = new ArrayList<>();
        // Generamos las 5 coordenads de ataque de manera aleatoria.
        for(int i = 0 ; i < 5 ; i++) {
            coordenadaNumero = coordenadaAleatoria.nextInt(19) + 1;
            coordenadaLetra = Character.toString((char) (coordenadaAleatoria.nextInt(high-low) + low));
            coordenadasDeAtaque.add(coordenadaLetra + coordenadaNumero);
        }
        // Confirmamos al Jugador las coordenadas en las cuales se va a realizar
        // el ataque.
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Coordenadas seleccionadas: \n" + 
                                       coordenadasDeAtaque + "\n ¿Desea continuar?",
                                       "Warning",dialogButton);

        if(dialogResult == 0) { // Si acepta.
            atacar(coordenadasDeAtaque); // Atacamos
            // Establecemos que ha utilizado la habilidad especial.
            setHaUtilizadoHabilidadEspecial(true);
        }
        this.dispose(); // Cerramos la ventana de ataque.
        remove(dialogButton);
    }
    
    /**
     * Método encargado de realizar el ataque especial del Submarino.
     * 
     */
    public void realizarInmersion() {
        // Confirmamos al Jugador si desea realizar la inmersión del Submarino.
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "¿Desea realizar la inmersion?","Warning",dialogButton);
        if(dialogResult == 0) { // Si acepta.
            String imagenSumergido = "/interfaz/imagenes/CasillaTableroSubmarinoSumergido.png";
            // Establecemos el Submarino como sumergido llamando a su método atacar.
            if(tipoNavio.atacar(posiciones)) {
                // Cambiamos el icono del Submarino en el Tablero e impedimos que sea
                // atacado poniendo el setName a LIBRE.
                tipoNavio.getPosiciones().keySet().forEach((posicion) -> {
                    casillasJugador.get(posicion).setIcon(new ImageIcon(getClass().getResource(imagenSumergido)));
                    casillasJugador.get(posicion).setName("LIBRE");
                });
                // Establecemos que ha utilizado la habilidad especial.
                setHaUtilizadoHabilidadEspecial(true);
                remove(dialogButton);
                this.dispose(); // Cerramos la ventana de ataque.
            }
            else {
                JOptionPane.showMessageDialog(this, "El submarino no puede sumergirse", "Warning" , JOptionPane.WARNING_MESSAGE); 
                tipoAtaque.setSelectedItem("Ataque normal");
            }
        }
    }
    
    /**
     * Método encargado de realizar el ataque de cualquier tipo de Navio con
     * excepción del Submarino.
     * 
     * @param coordenadasAtaque Colección de coordenadas de ataque en las que va
     *                          a atacar el Navio.
     */
    public void atacar(ArrayList<String> coordenadasAtaque) {
        // Establecemos las rutas de las imágenes correspondientes.
        String imagenTocado = "/interfaz/imagenes/CasillaTableroTocado.png";
        String imagenNoTocado = "/interfaz/imagenes/CasillaTableroNoTocado.png";
        String imagenDron = "/interfaz/imagenes/CasillaTableroInterrogante.png";
        // Si el ataque es especial establecemos que se ha utilizado la
        // habilidad especial.
        if(!tipoAtaque.getSelectedItem().toString().equals("Ataque normal"))
            setHaUtilizadoHabilidadEspecial(true);
        // Recorremos las coordenadas de ataque.
        coordenadasAtaque.forEach((String coordenadaAtaque) -> {
            // Detectamos que hemos dado a un Navio.
            if(casillasContrincante.get(coordenadaAtaque).getName().equals("OCUPADO")) {
                // En caso de haberlo hecho con la Corbeta y haber utilizado el
                // ataque especial.
                if(tipoNavio.getNombre().equals("Corbeta") && 
                    tipoAtaque.getSelectedItem().toString().equals("Lanzamiento de dron"))
                    // Cambiamos el icono a Avistado.
                    casillasContrincante.get(coordenadaAtaque)
                            .setIcon(new ImageIcon(getClass().getResource(imagenDron)));
                else // En caso de haber sido cualquier otro Navio.
                    // Cambiamos el icono a Tocado.
                    casillasContrincante.get(coordenadaAtaque)
                            .setIcon(new ImageIcon(getClass().getResource(imagenTocado)));
            } 
            else // En este caso no damos a un Navio.
                // Si no es la Corbeta.
                if(!tipoAtaque.getSelectedItem().toString().equals("Lanzamiento de dron"))
                    // Cambiamos el icono a No Tocado.
                    casillasContrincante.get(coordenadaAtaque)
                            .setIcon(new ImageIcon(getClass().getResource(imagenNoTocado)));
        });
        this.dispose();
    }
    
    /**
     * Método encargado de detectar las acciones de la ventana de tal forma
     * que si pulsamos el botón Atacar llamamos a los métodos correspondientes
     * para el ataque mientras que si seleccionamos el JComboBox del tipoAtaque
     * nos construye una nueva ventana (quitando la actual) con los nuevos
     * componentes necesarios.
     * También detecta cuando seleccionamos un JComboBox de coordenadas,
     * estableciendo esas mismas coordenadas en el JTextField correspondiente.
     * 
     * @param evt Evento del botón Aceptar o del JComboBox tipoAtaque. Además 
     *            del evente del JComboBox de cada una de las coordenadas.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Obtenemos el nombre del comando para comprobar que componente se ha
        // activado.
        String comando = evt.getActionCommand();
        if(comando.equals("Aceptar")){ // Pulsamos el botón Aceptar.
            try {
                // Recorremos las coordenadas seleccionadas.
                jtextfields.forEach((jtextfield) -> {
                    coordenadasAtaque.add(jtextfield.getText());
                });
                // Dependiendo del tipo de ataque seleccionado.
                if(tipoAtaque.getSelectedItem().toString().equals("Ataque normal")) {
                    atacar(coordenadasAtaque); // Ataque normal.
                } else {
                    // Si las coordenadas son correctas.
                    if(tipoNavio.atacar(coordenadasAtaque))
                        atacar(coordenadasAtaque); // Ataque especial.
                    else // Si no son correctas.
                        JOptionPane.showMessageDialog(this, "Casillas no validas para el tipo de barco: "
                                + tipoNavio.getNombre(), "Error" , JOptionPane.ERROR_MESSAGE);
                }
                coordenadasAtaque.clear();
            } catch(NullPointerException excepcion) {
                        JOptionPane.showMessageDialog(this, "Casillas no validas para el tipo de barco: "
                                + tipoNavio.getNombre(), "Error" , JOptionPane.ERROR_MESSAGE);
            }
            
        }
        else if(comando.equals("TipoAtaque")){ // Cambiamos de tipo de ataque.
            // Si seleccionamos ataque normal.
            if(tipoAtaque.getSelectedItem().toString().equals("Ataque normal")) {
                // Construimos una nueva ventana que se ajusta a ese ataque normal.
                eliminarComponentes();
                construirVentana(1);
            } else { // Ataque especial.
                // Verificamos que puede hacer el ataque especial.
                if(getHaUtilizadoHabilidadEspecial() == true) { // No puede realizarlo.
                    JOptionPane.showMessageDialog(this, "No puedes usar mas habilidades especiales"
                            + " hasta haber transcurrido cinco turnos", "Error" , JOptionPane.ERROR_MESSAGE);
                    tipoAtaque.setSelectedItem("Ataque normal");
                    addFondo();
                }
                else { // Puede realizar el ataque especial.
                    switch(tipoAtaque.getSelectedItem().toString()) {
                        case "Lanzamiento de dron":
                            eliminarComponentes();
                            construirVentana(2);
                            addFondo();
                            break;
                        case "Triple torpedo":
                            eliminarComponentes();
                            construirVentana(3);
                            addFondo();
                            break;
                        case "Lanzamiento de misil Tomahawk":
                            eliminarComponentes();
                            construirVentana(4);
                            addFondo();
                            break;
                        case "Ataque aereo":
                            realizarAtaqueAereo();
                            addFondo();
                            break;
                        case "Inmersion":
                            realizarInmersion();
                            addFondo();
                            break;
                    }
                }
            }
                
        } else{ // Seleccionamos alguno de los JComboBox.
            JComboBox src = (JComboBox) evt.getSource();
            // Establecemos en el JTextField correspondiente el valor seleccionado
            // en el JComboBox.
            jtextfields.get(Integer.parseInt(src.getName())).setText(src.getSelectedItem().toString());
        }
    }
    
    /**
     * Método encargado de devolver el atributo coordenadasAtaque.
     * 
     * @return Colección de coordenadas de ataque en las que va a atacar el Navio.
     */
    public ArrayList<String> getCoordenadasAtaque() {
        return this.coordenadasAtaque;
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
     *                                     o no el ataque especial de un Navio. 
     */
    public void setHaUtilizadoHabilidadEspecial(boolean haUtilizadoHabilidadEspecial) {
        this.haUtilizadoHabilidadEspecial = haUtilizadoHabilidadEspecial;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}