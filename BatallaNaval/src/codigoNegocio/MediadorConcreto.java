package codigoNegocio;

import java.io.Serializable;
import java.util.HashMap;

/**
 * MediadorConcreto: Representa un objeto de tipo Mediador, en este caso,
 *                   MediadorConcreto encargado de la comunicación entre
 *                   jugadores.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class MediadorConcreto implements Mediador, Serializable{

    // Colección de los jugadores asociados al MediadorConcreto junto con el
    // usuario de cada uno de ellos.
    private HashMap<String,Jugador> jugadores = new HashMap<>();

    /**
     * Método encargado de obtener el Jugador asociado al MediadorConcreto.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador.
     * @return Jugador asociado al MediadorConcreto.
     */
    @Override
    public Jugador getJugador(String usuario) {
        return jugadores.get(usuario);
    }

    /**
     * Método encargado de asociar un Jugador al MediadorConcreto.
     * 
     * @param jugador Jugador a asociar al MediadorConcreto.
     */
    @Override
    public void setJugador(Jugador jugador) {
        jugadores.put(jugador.getUsuario(), jugador);
    }

    /**
     * Método encargado de enviar un mensaje al Jugador.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador al que enviar el mensaje.
     * @param mensaje Mensaje que se envía al Jugador.
     */
    @Override
    public void enviar(String usuario, String mensaje) {
        getJugador(usuario).recibir(mensaje);
    }
}