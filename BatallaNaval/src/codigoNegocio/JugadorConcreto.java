package codigoNegocio;

/**
 * JugadorConcreto: Representa un objeto de tipo Jugador, en este caso, JugadorConcreto.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class JugadorConcreto extends Jugador{

    /**
     * Constructor.
     * 
     * @param nombre Nombre del JugadorConcreto.
     * @param apellidos Apellidos del JugadorConcreto.
     * @param edad Edad del JugadorConcreto.
     * @param usuario Usuario del JugadorConcreto.
     * @param password Contraseña del JugadorConcreto.
     * @param mediador Intermediario entre el JugadorConcreto y la IA para la comunicación.
     * @param estadisticas Estadisticas del JugadorConcreto.
     */
    public JugadorConcreto(String nombre, String apellidos, int edad, String usuario,
                           String password, Mediador mediador, Estadisticas estadisticas) {
        super(nombre, apellidos, edad, usuario, password, mediador, estadisticas);
    }
    
    /**
     * Método encargado de enviar un mensaje al Jugador.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador al que enviar el mensaje.
     * @param mensaje Mensaje que se envía al Jugador.
     */
    @Override
    public void enviar(String usuario, String mensaje){
        this.getMediador().enviar(usuario, mensaje);
    }
    
    @Override
    public void recibir(String mensaje){}       
}