package codigoNegocio;

import interfaz.VentanaPrincipal;

/**
 * BatallaNaval: Inico de la Aplicación Battleva.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class BatallaNaval {
    /**
     * Método encargado de iniciar la Ventana Principal para comenzar la
     * ejecución de la aplicación.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setVisible(true);
    }
}