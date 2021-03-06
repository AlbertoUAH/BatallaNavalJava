package interfaz;

import codigoNegocio.Mediador;
import codigoNegocio.MediadorConcreto;
import codigoNegocio.Servidor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * IniciarSesion: Ventana encargada del Inicio de Sesión.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class IniciarSesion extends javax.swing.JFrame {
    
    // Mediador encargado de la comunicación entre jugadores.
    private Mediador mediador;
    // Proxy encargado de la comunicación entre el cliente y el Servidor.
    private Servidor proxy;
    // Ventana Principal anterior a esta ventana.
    private JFrame ventanaPrincipal;
    
    /**
     * Constructor.
     * 
     * @param ventanaPrincipal Ventana Principal anterior a esta ventana.
     * @param proxy Proxy encargado de la comunicación entre el cliente y el Servidor.
     */
    public IniciarSesion(JFrame ventanaPrincipal, Servidor proxy) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.proxy = proxy;
        this.mediador = new MediadorConcreto();
        // Iniciamos la ventana, establecemos sus dimensiones y la centramos.
        initComponents();
        this.setSize(470, 330);
        centrarVentana(this);
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        usuarioTF = new javax.swing.JTextField();
        passwordTF = new javax.swing.JPasswordField();
        aceptarButton = new javax.swing.JButton();
        volverButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BATTLEVA: INICIO SESIÓN");
        setMaximumSize(new java.awt.Dimension(470, 330));
        setMinimumSize(new java.awt.Dimension(470, 330));
        setPreferredSize(new java.awt.Dimension(470, 330));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Copperplate", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BIENVENIDO A BATTLEVA");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(110, 50, 250, 24);

        jLabel3.setFont(new java.awt.Font("Copperplate", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("INICIAR SESION");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(175, 80, 120, 19);

        jLabel4.setFont(new java.awt.Font("Copperplate", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("USUARIO");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 118, 70, 30);

        jLabel5.setFont(new java.awt.Font("Copperplate", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CONTRASEÑA");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 162, 100, 20);

        usuarioTF.setMaximumSize(new java.awt.Dimension(190, 25));
        usuarioTF.setMinimumSize(new java.awt.Dimension(190, 25));
        usuarioTF.setPreferredSize(new java.awt.Dimension(190, 25));
        getContentPane().add(usuarioTF);
        usuarioTF.setBounds(170, 120, 190, 25);

        passwordTF.setMaximumSize(new java.awt.Dimension(190, 25));
        passwordTF.setMinimumSize(new java.awt.Dimension(190, 25));
        passwordTF.setPreferredSize(new java.awt.Dimension(190, 25));
        getContentPane().add(passwordTF);
        passwordTF.setBounds(170, 160, 190, 25);

        aceptarButton.setText("ACEPTAR");
        aceptarButton.setFocusable(false);
        aceptarButton.setMaximumSize(new java.awt.Dimension(95, 23));
        aceptarButton.setMinimumSize(new java.awt.Dimension(95, 23));
        aceptarButton.setPreferredSize(new java.awt.Dimension(95, 23));
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(aceptarButton);
        aceptarButton.setBounds(265, 220, 95, 23);

        volverButton.setText("VOLVER");
        volverButton.setFocusable(false);
        volverButton.setMaximumSize(new java.awt.Dimension(85, 23));
        volverButton.setMinimumSize(new java.awt.Dimension(85, 23));
        volverButton.setPreferredSize(new java.awt.Dimension(85, 23));
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });
        getContentPane().add(volverButton);
        volverButton.setBounds(170, 220, 85, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/portada.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(470, 330));
        jLabel1.setMinimumSize(new java.awt.Dimension(470, 330));
        jLabel1.setPreferredSize(new java.awt.Dimension(470, 330));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 470, 330);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método encargado de cerrar la ventana actual e iniciar la Ventana
     * Principal al pulsar el botón Volver.
     * 
     * @param evt Evento del botón Volver.
     */
    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverButtonActionPerformed
        this.dispose();
        ventanaPrincipal.setVisible(true);
    }//GEN-LAST:event_volverButtonActionPerformed
    
    /**
     * Método encargado de verificar el inicio de sesión e iniciar la Ventana
     * de Selección al pulsar el botón Aceptar llamando a los métodos correspondientes.
     * 
     * @param evt Evento del botón Aceptar.
     */
    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        // Verificamos que el usuario y contraseña introducidos son correctos.
        if(proxy.verificarJugador(usuarioTF.getText(), passwordTF.getText())) {
            JOptionPane.showMessageDialog(this, "Bienvenido de nuevo", "Informacion",
                                          JOptionPane.INFORMATION_MESSAGE);
            // Creamos la Ventana de Selección.
            VentanaSeleccion ventanaSeleccion = new VentanaSeleccion(proxy, 
                                                                     proxy.getJugador(usuarioTF.getText()));
            // Cerramos la ventana actual...
            this.dispose();
            // E iniciamos la Ventana de Selección.
            ventanaSeleccion.setVisible(true);
        } else { // En caso de no ser correctos.
            JOptionPane.showMessageDialog(this, "Credenciales erróneos", "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
        // Vaciamos los campos.
        usuarioTF.setText("");
        passwordTF.setText("");
    }//GEN-LAST:event_aceptarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField passwordTF;
    private javax.swing.JTextField usuarioTF;
    private javax.swing.JButton volverButton;
    // End of variables declaration//GEN-END:variables
}