package codigoNegocio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Conserje: Representa un objeto de tipo Conserje encargado de guardar los
 *           recuerdos.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Conserje implements Serializable{
    
    //Nombre del Conserje.
    private String nombre;
    // Colección de los recuerdos almacenados.
    private ArrayList<Recuerdo> recuerdos; 
    
    /**
     * Constructor.
     * 
     * @param nombre Nombre del Conserje.
     */
    public Conserje(String nombre) {
        this.nombre = nombre;
        this.recuerdos = new ArrayList<>();
    }
    
    /**
     * Método encrgado de devolver el atributo recuerdos.
     * 
     * @return Colección de los recuerdos almacenados.
     */
    public ArrayList<Recuerdo> getRecuerdos() {
        return this.recuerdos;
    }
    
    /**
     * Método encrgado de devolver un Recuerdo concreto de la colección.
     * 
     * @param indice Número de Recuerdo a recuperar.
     * @return Recuerdo concreto.
     */
    public Recuerdo getRecuerdo(int indice) {
        return this.recuerdos.get(indice);
    }
    
    /**
     * Método encargado de incluir un Recuerdo a la colección.
     * 
     * @param recuerdo Recuerdo a incluir en la colección.
     */
    public void addRecuerdo(Recuerdo recuerdo) {
        if(recuerdos.contains(recuerdo)) {
            int index = recuerdos.indexOf(recuerdo);
            recuerdos.set(index, recuerdo);
        }
        else
            recuerdos.add(recuerdo);    
    }
}