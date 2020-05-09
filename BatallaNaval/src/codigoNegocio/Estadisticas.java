package codigoNegocio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Estadisticas: Representa un objeto de tipo Estadisticas encargado de
 *               almacenar las estadísticas de juego de cada Jugador.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class Estadisticas implements Serializable{
    
    // Número total de navios hundidos por el Jugador.
    private int totalBarcosHundidos;
    // Colección con el número total de navios hundidos por categoría por el Jugador.
    private ArrayList<Integer> totalBarcosHundidosPorCategorias;
    // Número de victorias conseguidas por el Jugador.
    private int numeroDeVictorias;
    // Número de derrotas conseguidas por el Jugador.
    private int numeroDeDerrotas;

    /**
     * Constructor.
     * 
     */
    public Estadisticas() {
        this.totalBarcosHundidos = 0;
        this.totalBarcosHundidosPorCategorias = new ArrayList<>();
        for(int i = 0; i < 5; i++) 
            totalBarcosHundidosPorCategorias.add(0);       
        this.numeroDeVictorias = 0;
        this.numeroDeDerrotas = 0;
    }

    /**
     * Método encargado de devolver el atributo totalBarcosHundidos.
     * 
     * @return Número total de navios hundidos por el Jugador.
     */
    public int getTotalBarcosHundidos() {
        return totalBarcosHundidos;
    }

    /**
     * Método encargado de devolver el atributo TotalBarcosHundidosPorCategorias.
     * 
     * @return Colección con el número total de navios hundidos por categoría por el Jugador.
     */
    public ArrayList<Integer> getTotalBarcosHundidosPorCategorias() {
        return totalBarcosHundidosPorCategorias;
    }

    /**
     * Método encargado de devolver el atributo numeroDeVictorias.
     * 
     * @return Número de victorias conseguidas por el Jugador.
     */
    public int getNumeroDeVictorias() {
        return numeroDeVictorias;
    }

    /**
     * Método encargado de devolver el atributo numeroDeDerrotas.
     * 
     * @return Número de derrotas conseguidas por el Jugador.
     */
    public int getNumeroDeDerrotas() {
        return numeroDeDerrotas;
    }
    
    /**
     * Método encargado de establecer el valor del atributo totalBarcosHundidos.
     * 
     * @param totalBarcosHundidos Número total de navios hundidos por el Jugador.
     */
    public void setTotalBarcosHundidos(int totalBarcosHundidos) {
        this.totalBarcosHundidos = totalBarcosHundidos;
    }

    /**
     * Método encargado de establecer el valor del atributo TotalBarcosHundidosPorCategorias.
     * 
     * @param index Número del navio, el cual, se quiere actualizar sus estadisticas.
     */
    public void setTotalBarcosHundidosPorCategorias(int index) {
        int valor = this.totalBarcosHundidosPorCategorias.get(index) + 1;
        this.totalBarcosHundidosPorCategorias.set(index, valor);
    }

    /**
     * Método encargado de establecer el valor del atributo numeroDeVictorias.
     * 
     * @param numeroDeVictorias Número de victorias conseguidas por el Jugador.
     */
    public void setNumeroDeVictorias(int numeroDeVictorias) {
        this.numeroDeVictorias = numeroDeVictorias;
    }

    /**
     * Método encargado de establecer el valor del atributo numeroDeDerrotas.
     * 
     * @param numeroDeDerrotas Número de derrotas conseguidas por el Jugador.
     */
    public void setNumeroDeDerrotas(int numeroDeDerrotas) {
        this.numeroDeDerrotas = numeroDeDerrotas;
    }
}