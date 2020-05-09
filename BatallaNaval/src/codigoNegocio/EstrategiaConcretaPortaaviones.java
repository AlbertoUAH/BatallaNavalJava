package codigoNegocio;

import interfaz.Tablero;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * EstrategiaConcretaPortaaviones: Representa un objeto de tipo EstrategiaAtaqueIA,
 *                                 en este caso, EstrategiaConcretaPortaaviones
 *                                 encargado de los ataques del Navio Portaaviones
 *                                 de la IA.
 * 
 * @author Alberto Fernández Hernández
 * @author Francisco Cano Díaz
 */
public class EstrategiaConcretaPortaaviones extends EstrategiaAtaqueIA {
    
    /**
     * Constructor.
     * 
     * @param tableroJuego Tablero donde se desarrolla toda la acción del juego.
     */
    public EstrategiaConcretaPortaaviones(Tablero tableroJuego) {
        super(tableroJuego);
    }

    /**
     * Método encargado de realizar el ataque del Portaaviones seleccionado
     * por la IA. De esta forma, obtiene las casillas que va a atacar y recorre
     * las casillas seleccionadas verificando si hay un Navio o no, estableciendo
     * el icono de tocado en el primer caso y el icono de no tocado en el segundo.
     * 
     */
    @Override
    public void atacar() {
        // Obtenemos las casillas a atacar y las guardamos.
        ArrayList<String> coordenadasDeAtaque = obtenerCasillasDeAtaque();
        setCasillasDeAtaque(coordenadasDeAtaque);
        //Recorremos las casillas de ataque obtenidas.
        for(String coordenadaDeAtaque : coordenadasDeAtaque) {
            // Obtenemos el JLabel de la casilla correspondiente.
            JLabel posicionDeAtaque = getTableroJuego().getCasillasJugador().get(coordenadaDeAtaque);
            // Verificamos si hay Navio o no.
            if(posicionDeAtaque.getName().equals("OCUPADO")) { // Hay Navio.
                posicionDeAtaque.setIcon(new ImageIcon(getClass().getResource(getImagenTocado())));
            }
            else // No hay Navio.
                posicionDeAtaque.setIcon(new ImageIcon(getClass().getResource(getImagenNoTocado())));
         } 
    }
    
    /**
     * Método encargado de obtener las casillas a atacar por parte de la IA. En
     * este caso, las casillas obtenidas van relacionadas con el tipo de ataque
     * que realiza la Portaaviones.
     * 
     * @return Colección con las casillas que van a ser atacadas.
     */
    @Override
    public ArrayList<String> obtenerCasillasDeAtaque() {
        
        Random rand = new Random();
        // Colección con las casillas que van a ser atacadas.
        ArrayList<String> coordenadasAtaque = new ArrayList<>();
        
        // Estableceremos la probabilidad de que se realice un ataque normal en
        // un 60% por lo que la probabilidad de que se realice un ataque especial
        // será de un 40%.
        double probabilidadAtaqueNormal = 0.6;
        
        // Obtenemos las filas y columnas del Tablero.
        int numeroFilas = getTableroJuego().getNumeroFilas();
        int numeroColumnas = getTableroJuego().getNumeroColumnas();
        
        // Probabilidad del tipo de ataque. Si es menor que el 60% realizará un
        // ataque normal, en caso contrario, un ataque especial.
        double tipoDeAtaque;
        
        // Numero y letra que conformaran cada casilla de ataque.
        int numeroCoordenada = 0;
        String letraCoordenada = null;
        
        // Coordenada en la que empieza el ataque especial.
        int coordenadaInicio = 0;
        
        tipoDeAtaque = (Math.round(rand.nextDouble()*100)/100D);

        if( tipoDeAtaque <= probabilidadAtaqueNormal) { // Ataque normal.
            
            // En caso de que la IA tenga casillas guardadas donde sepa
            // que hay un Navio, atacará a dichas casillas. En caso contrario,
            // lo hará de forma aleatoria.
            if(!getCasillasDeAtaque().isEmpty()) {
                coordenadasAtaque.add(getCasillasDeAtaque().get(0));
                eliminarCasillaDeAtaque(0);
            }
            else {
                
                // Realizaremos tantas iteraciones como casillas incorrectas
                // para el ataque se generen. Esto se debe a que la IA solo
                // ataca a posiciones que no ha atacado anteriormente.
                // Esto solo ocurre para el ataque normal ya que el ataque 
                // especial al atacar 5 casillas no verifica que ya hayan sido 
                // atacadas para evitar que el Portaaviones sea un Navio muy 
                // superior al resto.
                do{
                    coordenadasAtaque.clear(); // Vaciamos las coordenadas erróneas.
                    numeroCoordenada = rand.nextInt((numeroFilas + 1) - 1) + 1;
                    letraCoordenada = Character.toString((char) (rand.nextInt((numeroColumnas + 65) - 65) + 65));
                    coordenadasAtaque.add(letraCoordenada + numeroCoordenada);
                }while(comprobarCasillasDeAtaque(coordenadasAtaque, getTableroJuego().getCasillasJugador()));
            }
        } else { // Ataque especial.
            
            // Generamos 5 casillas de ataque de forma aleatoria.
            for(int i = 0 ; i < 5 ; i++) {
                coordenadaInicio = rand.nextInt(((numeroColumnas - 2) + 65) - 65) + 65;
                numeroCoordenada =  rand.nextInt((numeroFilas + 1) - 2) + 1;
                coordenadasAtaque.add(Character.toString((char) coordenadaInicio) + numeroCoordenada);
            }               
        }
       
        return coordenadasAtaque;
    }
}