package codigoNegocio;

import java.io.Serializable;

/**
 * Recuerdo: Representa un objeto de tipo Recuerdo encargado de almacenar una
 *           Partida.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Recuerdo implements Serializable{
    
    // Partida que almacena el Recuerdo.
    private Partida partida;
    
    /**
     * Constructor.
     * 
     * @param partida Partida que almacena el Recuerdo.
     */
    public Recuerdo(Partida partida) {
        this.partida = partida;
    }
    
    /**
     * Método encargado de obtener la Partida asociada al Recuerdo.
     * 
     * @return Partida que almacena el Recuerdo.
     */
    public Partida getPartida() {
        return this.partida;
    }
    
    /**
     * Método encargado de asociar una Partida al Recuerdo.
     * 
     * @param partida Partida a incluir en el Recuerdo.
     */
    public void setPartida(Partida partida) {
        this.partida = partida;
    }  
}