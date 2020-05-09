package codigoNegocio;

import java.io.Serializable;

/**
 * Jugador: Representa un objeto de tipo Jugador.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public abstract class Jugador implements Serializable {
    
    // Nombre del Jugador.
    private String nombre;
    // Apellidos del Jugador.
    private String apellidos;
    // Edad del Jugador.
    private int edad;
    // Usuario del Jugador.
    private String usuario;
    // Contraseña del Jugador.
    private String password;
    // Intermediario entre el Jugador y la IA para la comunicación.
    private Mediador mediador;
    // Estadísticas del Jugador.
    private Estadisticas estadisticas;
    
    /**
     * Constructor.
     * 
     * @param nombre Nombre del Jugador.
     * @param apellidos Apellidos del Jugador.
     * @param edad Edad del Jugador.
     * @param usuario Usuario del Jugador.
     * @param password Contraseña del Jugador.
     * @param mediador Intermediario entre el Jugador y la IA para la comunicación.
     * @param estadisticas Estadísticas del Jugador.
     */
    public Jugador(String nombre, String apellidos, int edad, String usuario, String password, Mediador mediador, Estadisticas estadisticas)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.usuario = usuario;
        this.password = password;
        this.estadisticas = estadisticas;
        this.mediador = mediador;
        // Asignamos el Jugador al Mediador.
        mediador.setJugador(this);        
    }

    /**
     * Método encargado de devolver el atributo nombre.
     * 
     * @return Nombre del Jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método encargado de devolver el atributo apellidos.
     * 
     * @return Apellidos del Jugador.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Método encargado de devolver el atributo edad.
     * 
     * @return Edad del Jugador.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método encargado de devolver el atributo usuario.
     * 
     * @return Usuario del Jugador.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Método encargado de devolver el atributo password.
     * 
     * @return Contraseña del Jugador.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Método encargado de devolver el atributo medidador.
     * 
     * @return Intermediario entre el Jugador y la IA para la comunicación.
     */
    public Mediador getMediador(){
        return this.mediador;
    }
    
    /**
     * Método encargado de devolver el atributo estadisticas.
     * 
     * @return Estadísticas del Jugador.
     */
    public Estadisticas getEstadisticas() {
        return this.estadisticas;
    }

    /**
     * Método encargado de establecer el valor del atributo nombre.
     * 
     * @param nombre Nombre del Jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método encargado de establecer el valor del atributo apellidos.
     * 
     * @param apellidos Apellidos del Jugador.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Método encargado de establecer el valor del atributo edad.
     * 
     * @param edad Edad del Jugador.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Método encargado de establecer el valor del atributo usuario.
     * 
     * @param usuario Usuario del Jugador.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Método encargado de establecer el valor del atributo password.
     * 
     * @param password Contraseña del Jugador.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método encargado de establecer el valor del atributo mediador.
     * 
     * @param mediador Intermediario entre el Jugador y la IA para la comunicación.
     */
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }
    
    /**
     * Método encargado de establecer el valor del atributo estadisticas.
     * 
     * @param estadisticas Estadísticas del Jugador.
     */
    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }
    
    /**
     * Método encargado de enviar un mensaje al Jugador.
     * 
     * @param usuario Nombre de usuario asociado a un Jugador al que enviar el mensaje.
     * @param mensaje Mensaje que se envía al Jugador.
     */
    public abstract void enviar(String usuario, String mensaje);
    
    /**
     * Método encargado de recibir los mensajes enviados por el Jugador.
     * 
     * @param mensaje Mensaje enviado por el Jugador.
     */
    public abstract void recibir(String mensaje);
}