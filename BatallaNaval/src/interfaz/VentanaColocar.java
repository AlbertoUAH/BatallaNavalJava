package interfaz;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * VentanaColocar: Ventana encargada de la colocación de los navios.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class VentanaColocar extends javax.swing.JFrame implements ActionListener{

    // Tipo de Navio que va a ser colocado.
    private String tipoNavio;
    // Colección de las casillas del Jugador junto con el JLabel correspondiente
    // de cada una del Tablero en la Partida.
    private LinkedHashMap<String, JLabel> casillasJugador;
    // Colección con las posiciones donde se va a colocar el Navio.
    private ArrayList<String> posiciones;
    // Al ser la ventana dinámica, dependiendo del Navio que va a ser colocado,
    // se generarán un número determinado de componentes.
    private int numeroComponentes;
    // Anchura del componente.
    private int anchuraComponente = 75;
    // Altura del componente.
    private int alturaComponente = 25;
    // Dimensión del botón.
    private int tamBoton = 25;
    // Posición inicial x del primer componente (JComboBox).
    private int posX = 10;
    // Posición inicial y del primer componente (JComboBox).
    private int posY = 10;
    // Posición inicial x del primer componente (JTextFields).
    private int posXTF = posX + anchuraComponente + 5;
    // Colección de JTextFields en los cuales se escribe cada una de las
    // coordenadas seleccionadas en los JComboBox.
    private ArrayList<JTextField> jtextfields;
    
    /**
     * Constructor.
     * 
     * @param tipoNavio Tipo de Navio que va a ser colocado.
     * @param casillasJugador Colección de las casillas del Jugador junto con el
     *                        JLabel correspondiente de cada una del Tablero en 
     *                        la Partida.
     * @param posiciones Colección con las posiciones donde se va a colocar el Navio.
     */
    public VentanaColocar(String tipoNavio, LinkedHashMap<String, JLabel> casillasJugador,
                          ArrayList<String> posiciones) {
        this.tipoNavio = tipoNavio;
        this.casillasJugador = casillasJugador;
        this.jtextfields = new ArrayList<>();
        this.posiciones = posiciones;
        
        // Establecemos el número de componentes dependiendo del tipo de Navio.
        switch(tipoNavio) {
            case "Corbeta":
                numeroComponentes = 2;
                break;
            case "Destructor":
                numeroComponentes = 3;
                break;
            case "Acorazado":
                numeroComponentes = 4;
                break;
            case "Portaaviones":
                numeroComponentes = 5;
                break;
            case "Submarino":
                numeroComponentes = 3;
                break;
        }
        // Construimos la ventana con los componentes necesarios.
        construirVentana();
        // Incluimos un fondo a la ventana.
        addFondo();
        // Inicializamos y centramos la ventana.
        initComponents();
        centrarVentana(this);
    }
    
    /**
     * Método encargado de construir e inicializar todos los componentes
     * necesarios para colocar el Navio correspondinte.
     * 
     */
    public void construirVentana() {
        // Copiamos las casillas del Jugador, para no modificar las originales.
        ArrayList<String> casillas = new ArrayList<>();
        casillas.addAll(casillasJugador.keySet());
        // Generamos los componentes necesarios para la ventana.
        for(int i = 0; i < numeroComponentes; i++) {
            // Generamos el JComboBox.
            JComboBox jComboBox = new JComboBox();
            jComboBox.setBounds(posX, posY, anchuraComponente, alturaComponente);
            jComboBox.setModel(new DefaultComboBoxModel<>(casillas.toArray(new String[0])));
            jComboBox.setName(Integer.toString(i));
            jComboBox.addActionListener(this);
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
        // Generamos el botón Colocar y lo incluimos en la ventana.
        JButton aceptarButton = new JButton();
        aceptarButton.setBounds(posXTF, posY, anchuraComponente, alturaComponente);
        aceptarButton.setText("OK");
        aceptarButton.addActionListener(this);
        aceptarButton.setActionCommand("Colocar");
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
        // Creamos el JLabel y le incluimos el icono.
        JLabel fondo = new JLabel();
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
     * Método encargado de verificar que las casillas introducidas por el
     * Jugador son correctas llamando a los distintos métodos de verificación.
     * 
     * @return Booleano que indica si las casillas introducidas son correctas.
     */
    public boolean validarCasillas() {
        // Obtenemos los JTextFields las casillas introducidas.
        ArrayList<String> coordenadasSeleccionadas = new ArrayList<>();
        jtextfields.forEach((jtextfield) -> {
            coordenadasSeleccionadas.add(jtextfield.getText());
        });
        
        // Verificamos las casillas a través de los diferentes métodos.
        return verificarRepetidos(coordenadasSeleccionadas) &&
               verificarPosicion(coordenadasSeleccionadas) &&
               verificarOcupados(coordenadasSeleccionadas);
    }
    
    /**
     * Método encargado de verificar que ninguna de las casillas introducidas
     * está repetida.
     * 
     * @param coordenadasSeleccionadas Casillas introducidas por el Jugador.
     * @return Booleano que indica si las casillas introducidas están repetidas.
     */
    public boolean verificarRepetidos(ArrayList<String> coordenadasSeleccionadas) {
        return coordenadasSeleccionadas.stream().distinct().count() == numeroComponentes;
    }
    
    /**
     * Método encargado de verificar que ninguna de las casillas introducidas
     * ya están siendo ocupadas por otro Navio situado previamente.
     * 
     * @param coordenadasSeleccionadas Casillas introducidas por el Jugador.
     * @return Booleano que indica si las casillas introducidas ya están ocupadas
     *         por otros navios.
     */
    public boolean verificarOcupados(ArrayList<String> coordenadasSeleccionadas) {
        // Flags para ir verificando las casillas introducidas.
        boolean hayCasillasLibres = true;
        int contador = 0;
        // Recorremos las casillas introducidas.
        while(contador < coordenadasSeleccionadas.size() && hayCasillasLibres) {
            // Verificamos si la casilla ya está ocupada o no.
            if((casillasJugador.get(coordenadasSeleccionadas.get(contador))).getName().equals("OCUPADO")) {
                hayCasillasLibres = false;
            }
            contador++;
        }
        return hayCasillasLibres;
    }
    
    /**
     * Método encargado de verificar que ninguna de las casillas introducidas
     * no están situadas de forma contigua.
     * 
     * @param coordenadasSeleccionadas Casillas introducidas por el Jugador.
     * @return Booleano que indica si las casillas introducidas no están de
     *         forma contigua.
     */
    public boolean verificarPosicion(ArrayList<String> coordenadasSeleccionadas) {
        // Ordenamos las casillas introducidas ya que el Jugador puede introducir
        // las casillas de forma desordenada.
        // Creamos dos colecciones independientes:
        //  1. coordenadasLetra: con las letras de cada coordenada
        //  2. coordenadasNumero: con los numeros de cada coordenada
        ArrayList<String> coordenadasLetra = new ArrayList<>();
        ArrayList<Integer> coordenadasNumero = new ArrayList<>();
        
        // Recorremos las coordenadas, separando la letra del numero
        for(String coordenadaSeleccionada : coordenadasSeleccionadas) {
            coordenadasLetra.add(Character.toString(coordenadaSeleccionada.charAt(0)));
            coordenadasNumero.add(Integer.parseInt(coordenadaSeleccionada.substring(1)));
        }
        coordenadasSeleccionadas.clear();
        
        // Ordenamos las coleeciones
        Collections.sort(coordenadasLetra);
        Collections.sort(coordenadasNumero);
        
        // Finalmente, juntamos las letras y los numeros de las colecciones anteriores
        for(int i = 0 ; i < coordenadasLetra.size() ; i++) {
            coordenadasSeleccionadas.add(coordenadasLetra.get(i) + Integer.toString(coordenadasNumero.get(i)));
        }
        
        // Flag que utilizaremos para verificar cada par de casillas.
        int contadorResta = 0;
        // Recorremos las casillas introducidas por el Jugador.
        for(int i = 0; i<coordenadasSeleccionadas.size()-1; i++) {
            // Obtenemos las coordenadas a pares.
            String primeraCoordenadaAux = coordenadasSeleccionadas.get(i);
            String segundaCoordenadaAux = coordenadasSeleccionadas.get(i+1);
            contadorResta += Math.abs((Character.getNumericValue(primeraCoordenadaAux.charAt(0))
                             - Character.getNumericValue(segundaCoordenadaAux.charAt(0)))) 
                             + Math.abs(Integer.parseInt(primeraCoordenadaAux.substring(1))
                             - Integer.parseInt(segundaCoordenadaAux.substring(1)));
        }
        // En caso de que la resta entre todas las casillas sea igual al número
        // de componentes menos 1 podremos asegurar que estás casillas están 
        // contiguas.
        return contadorResta == (numeroComponentes - 1);
    }
    
    /**
     * Método encargado de obtener las casillas introducidas por el Jugador,
     * verificarlas y colocarlas en el Tablero al pulsar el botón Colocar
     * llamando a los métodos correspondientes.
     * También detecta cuando seleccionamos un JComboBox de coordenadas,
     * estableciendo esas mismas coordenadas en el JTextField correspondiente.
     * 
     * @param evt Evente del botón Colocar o el JComboBox de cada una de las
     *            coordenadas.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Obtenemos el nombre del comando para comprobar que componente se ha
        // activado.
        String comando = evt.getActionCommand();
        if(comando.equals("Colocar")){ // Pulsamos el botón colocar.
            // Validamos las casillas.
            if(!validarCasillas()){
                JOptionPane.showMessageDialog(this, "Casillas no validas", "Error",
                                              JOptionPane.ERROR_MESSAGE);
            }
            else { // En caso de ser correctas, se actualiza el Tablero con el nuevo Navio.
                String rutaImagen = "/interfaz/imagenes/CasillaTablero"+tipoNavio+".png";
                ImageIcon imagen = new ImageIcon(getClass().getResource(rutaImagen));
                jtextfields.forEach((jtextfield) -> {  
                    casillasJugador.get(jtextfield.getText()).setIcon(imagen);
                    casillasJugador.get(jtextfield.getText()).setName("OCUPADO");
                    posiciones.add(jtextfield.getText());
                });
                // Una vez colocado el Navio se cierra la Ventana Colocar.
                this.dispose();
            }
        }
        else{ // Seleccionamos alguno de los JComboBox.
            JComboBox src = (JComboBox) evt.getSource();
            // Establecemos en el JTextField correspondiente el valor seleccionado
            // en el JComboBox.
            jtextfields.get(Integer.parseInt(src.getName())).setText(src.getSelectedItem().toString());
        }             
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