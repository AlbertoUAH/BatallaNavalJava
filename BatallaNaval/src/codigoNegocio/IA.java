package codigoNegocio;

import java.util.ArrayList;
import java.util.Random;

/**
 * IA: Representa un objeto de tipo Jugador, en este caso, IA.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class IA extends Jugador{
    
    // Colección de saludos que pudee realizar la IA.
    private ArrayList<String> saludos = new ArrayList<>();
    // Colección de despedidas que pudee realizar la IA.
    private ArrayList<String> despedidas = new ArrayList<>();
    // Colección de comentarios que pudee realizar la IA.
    private ArrayList<String> comentarios = new ArrayList<>();
    // Mensaje que envía la IA al Jugador.
    private String mensajeAEnviar;

    /**
     * Constructor.
     * 
     * @param nombre Nombre de la IA.
     * @param apellidos Apellidos de la IA.
     * @param edad Edad de la IA.
     * @param usuario Usuario de la IA.
     * @param password Contraseña de la IA.
     * @param mediador Intermediario entre el Jugador y la IA para la comunicación.
     * @param estadisticas Estadisticas de la IA.
     */
    public IA(String nombre, String apellidos, int edad, String usuario, String password,
              Mediador mediador, Estadisticas estadisticas) {
        super(nombre, apellidos, edad, usuario, password, mediador, estadisticas);
        
        // Saludos disponibles de la IA.
        saludos.add("Saludos, pequeña e insignificante mota en el espacio a la que pienso aplastar :D");
        saludos.add("Hola, tampoco me cojas mucho cariño porque pienso hundirte igual que a toda tu flota");
        saludos.add("Buenas pequeño grumete, date por derrotado!");
        
        // Despedidas disponibles de la IA.
        despedidas.add("Adios, joven incauto :O");
        despedidas.add("Pobrecito, se despide ya y no ha empezado aun lo peor muahahaha");
        despedidas.add("Bueno, pos molt be, pos adeu");
        
        // Comentarios disponibles de la IA.
        comentarios.add("Estas mas perdido que en tu primer dia en la uni");
        comentarios.add("Estas mas muerto que FORTRAN");
        comentarios.add("Uhhhhhh, estas mas trastornao que uno de teleco");
    }

    /**
     * Método encargado de enviar un mensaje al Jugador.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador al que enviar el mensaje.
     * @param mensaje Mensaje que se envía al Jugador.
     */
    @Override
    public void enviar(String usuario, String mensaje) {
        recibir(mensaje);
        this.getMediador().enviar(usuario, this.getMensajeAEnviar());
    }

    /**
     * Método encargado de recibir los mensajes enviados por el Jugador.
     * 
     * @param mensaje Mensaje enviado por el Jugador.
     */
    @Override
    public void recibir(String mensaje) {

        // Dependiendo de lo que diga el Jugador...
        switch(mensaje.toLowerCase()){
            case "hola":
                setMensajeAEnviar(saludos.get(new Random().nextInt(saludos.size())));
            break;
            case "adios":
                setMensajeAEnviar(despedidas.get(new Random().nextInt(despedidas.size())));
            break;
            case "habla":
                setMensajeAEnviar(comentarios.get(new Random().nextInt(comentarios.size())));
            break;
            
            default:
                setMensajeAEnviar("Disculpa, no te he entendido :/");
        }
    }
    
    /**
     * Método encargado de devolver el atributo mensajeAEnviar.
     * 
     * @return Mensaje que envía la IA al Jugador.
     */
    public String getMensajeAEnviar(){
        return this.mensajeAEnviar;
    }
    
    /**
     * Método encargado de establecer el valor del atributo mensajeAEnviar.
     * 
     * @param mensajeAEnviar Mensaje que envía la IA al Jugador.
     */
    public void setMensajeAEnviar(String mensajeAEnviar){
        this.mensajeAEnviar = mensajeAEnviar;
    }
}