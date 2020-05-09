package codigoNegocio;

import java.util.Collection;

/**
 * Proxy: Representa un objeto de tipo Servidor, en este caso, Proxy encargado
 *        de hacer de puente entre el cliente y el Servidor. De esta forma, se
 *        controla el acceso al Servidor original.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Proxy implements Servidor{
    
    // Servidor original, al cual el Proxy, controla el acceso a sus servicios.
    private Servidor servidor;
    
    /**
     * Constructor.
     * 
     * @param servidor Servidor original, al cual el Proxy, controla el acceso a
     *                 sus servicios.
     */
    public Proxy(Servidor servidor){
        this.servidor = servidor;
    }

    /**
     * Método encargado de incluir un nuevo Jugador en el sistema.
     * 
     * @param jugador Jugador a incluir.
     * @return Booleano que indica si se incluido el Jugador correctamente o no
     *         en el sistema.
     */
    @Override
    public boolean addJugador(Jugador jugador) {
        return servidor.addJugador(jugador);
    }

    /**
     * Método encargado de devolver un Jugador del sistema.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @return Jugador obtenido del sistema.
     */
    @Override
    public Jugador getJugador(String usuario) {
        return servidor.getJugador(usuario);
    }

    /**
     * Método encargado de devolver los jugadores del sistema.
     * 
     * @return Colección con todos los jugadores del sistema.
     */
    @Override
    public Collection<Jugador> getJugadores() {
        return servidor.getJugadores();
    }
    
    /**
     * Método encargado de devolver el Conserje asociado al Servidor.
     * 
     * @return Conserje asociado al Servidor.
     */
    @Override
    public Conserje getConserje() {
        return servidor.getConserje();
    }
    
    /**
     * Método encargado de verificar si existe o no un Jugador en el sistema.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @return Booleano si existe o no el Jugador en el sistema.
     */
    @Override
    public boolean existeJugador(String usuario){
        return servidor.existeJugador(usuario);
    }

    /**
     * Método encargado de realizar una copia del Servidor.
     * 
     */
    @Override
    public void backupServidor() {
        servidor.backupServidor();
    }

    /**
     * Método encargado de verificar que el usuario y contraseña introducidos
     * al iniciar sesión son correctos.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @param password Contraseña asociada a dicho usuario.
     * @return Booleano si es correcto o no el usuario y la contraseña introducidos.
     */
    @Override
    public boolean verificarJugador(String usuario, String password) {
        return servidor.verificarJugador(usuario, password);
    }
}