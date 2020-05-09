package codigoNegocio;

/**
 * Mediador: Interface encargada de la comunicación entre jugadores.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public interface Mediador {
    
    /**
     * Método encargado de obtener el Jugador asociado al Mediador.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @return Jugador asociado al Mediador.
     */
    public Jugador getJugador(String usuario);
    
    /**
     * Método encargado de asociar un Jugador al Mediador.
     * 
     * @param jugador Jugador a asociar al Mediador.
     */
    public void setJugador(Jugador jugador);
    
    /**
     * Método encargado de enviar un mensaje al Jugador.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador al que enviar el mensaje.
     * @param mensaje Mensaje que se envía al Jugador.
     */
    public void enviar(String usuario, String mensaje);   
}