package codigoNegocio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Collection;

/**
 * ServidorCentral: Representa un objeto de tipo Servidor, en este caso,
 *                  ServidorCentral encargado de implementar los servicios
 *                  del Servidor.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class ServidorCentral implements Servidor, Serializable{
    
    // Nombre del ServidorCentral.
    private String nombre;
    // Colección almacenada en el ServidorCentral de los jugadores junto con
    // su nombre de usuario.
    private HashMap<String, Jugador> jugadoresRegistrados;
    // Conserje asociado al ServidorCetral.
    private Conserje conserje;
    
    /**
     * Constructor.
     * 
     * @param nombre Nombre del ServidorCentral.
     */
    public ServidorCentral(String nombre){
        this.nombre = nombre;
        this.conserje = new Conserje("Conserje");
        this.jugadoresRegistrados = new HashMap<>();
    }
    
    /**
     * Método encargado de realizar una copia del ServidorCentral (Serialización).
     * 
     */
    @Override
    public void backupServidor() {
        try {
            // Creamos el fichero.
            FileOutputStream f = new FileOutputStream(new File("serverData.dat"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Escribir el objeto ServidorCentral en el fichero creado anteriormente.
            o.writeObject(this);
            o.close();
            f.close();
        } catch (IOException ex) {
            ex.toString();
        } 
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
        boolean added = false;
        // Comprobamos si ya está en el sistema o no.
        if(!jugadoresRegistrados.containsKey(jugador.getUsuario())) {
            jugadoresRegistrados.put(jugador.getUsuario(), jugador);
            added = true;
        }
        return added;
    }
    
    /**
     * Método encargado de devolver un Jugador del sistema.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @return Jugador obtenido del sistema.
     */
    @Override
    public Jugador getJugador(String usuario){
        return jugadoresRegistrados.get(usuario);
    }
    
    /**
     * Método encargado de devolver los jugadores del sistema.
     * 
     * @return Colección con todos los jugadores del sistema.
     */
    @Override
    public Collection<Jugador> getJugadores(){
        return jugadoresRegistrados.values();
    }
    
    /**
     * Método encargado de devolver el Conserje asociado al ServidorCentral.
     * 
     * @return Conserje asociado al Servidor.
     */
    @Override
    public Conserje getConserje(){
        return this.conserje;
    }
    
    /**
     * Método encargado de verificar si existe o no un Jugador en el sistema.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @return Booleano si existe o no el Jugador en el sistema.
     */
    @Override
    public boolean existeJugador(String usuario){
        return jugadoresRegistrados.containsKey(usuario);
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
        boolean verificado = false;
        // Comprobamos si ya está en el sistema o no.
        if(jugadoresRegistrados.containsKey(usuario)) {
            // Comprobamos que la contraseña introducida es correcta con respecto
            // al usuario introducido.
            if(jugadoresRegistrados.get(usuario).getPassword().equals(password)) {
                verificado = true;
            }
        }
        return verificado;
    }
}