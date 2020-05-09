package codigoNegocio;

import java.util.Collection;

/**
 * Servidor: Interface encargada de los servicios del Servidor.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public interface Servidor{
    
    /**
     * Método encargado de incluir un nuevo Jugador en el sistema.
     * 
     * @param jugador Jugador a incluir.
     * @return Booleano que indica si se incluido el Jugador correctamente o no
     *         en el sistema.
     */
    public boolean addJugador(Jugador jugador);
    
    /**
     * Método encargado de devolver un Jugador del sistema.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @return Jugador obtenido del sistema.
     */
    public Jugador getJugador(String usuario);
    
    /**
     * Método encargado de devolver los jugadores del sistema.
     * 
     * @return Colección con todos los jugadores del sistema.
     */
    public Collection<Jugador> getJugadores();
    
    /**
     * Método encargado de devolver el Conserje asociado al Servidor.
     * 
     * @return Conserje asociado al Servidor.
     */
    public Conserje getConserje();
    
    /**
     * Método encargado de verificar si existe o no un Jugador en el sistema.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @return Booleano si existe o no el Jugador en el sistema.
     */
    public boolean existeJugador(String usuario);
    
    /**
     * Método encargado de realizar una copia del Servidor.
     * 
     */
    public void backupServidor();
    
    /**
     * Método encargado de verificar que el usuario y contraseña introducidos
     * al iniciar sesión son correctos.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @param password Contraseña asociada a dicho usuario.
     * @return Booleano si es correcto o no el usuario y la contraseña introducidos.
     */
    public boolean verificarJugador(String usuario, String password);
}