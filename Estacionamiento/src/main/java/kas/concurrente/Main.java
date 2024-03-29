package kas.concurrente;

import java.util.ArrayList;
import java.util.logging.Logger;

import kas.concurrente.constante.Contante;
import kas.concurrente.modelos.Estacionamiento;

/**
 * Clase principal, la usaran para SUS pruebas
 * Pueden modigicar los valores estaticos para ver como funciona
 * NO USEN VALORES EXTREMEDAMENTE ALTOS, puede alentar mucho su compu
 * AQUI EJECUTAN LA SIMULACION
 * 
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Main implements Runnable {

    private static final Logger LOG = Logger.getLogger("paquete.NombreClase");
    Estacionamiento estacionamiento;

    /**
     * Metodo constructor
     * Se inicializa el Semaforo Modificado con _______
     * Se inicaliza el Estacionamiento con _______
     */
    public Main() {
        this.estacionamiento = new Estacionamiento(Contante.NUM_CARROS_PERMITIDOS);
    }

    /**
     * Una documentacion del main xD, esta bien
     * Paso 0: Lee estas instrucciones
     * Paso 1: Crea el Objeto de tipo main
     * Paso 2: Crea Una estructura de datos que contenga a nuestros hilos
     * Paso 3: Genera con un ciclo, el cual inialice un numero igual de NUM_CARROS
     * Paso 4: No olvides agregarlos a la estructura e inicializarlos
     * Paso 5: Finalmente has un Join a tus hilos
     * 
     * @param args Los Argumentos
     * @throws InterruptedException Por si explota su compu al ponerle medio millon
     *                              de hilos xD
     */
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        ArrayList<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < Contante.NUM_CARROS; i++) {
            Thread t = new Thread(main, "" + i);
            t.start();
            hilos.add(t);
        }
        for (Thread hilo : hilos) {
            hilo.join();
        }
        int res = 0;
        for (int i = 0; i < main.estacionamiento.getLugares().length; ++i) {
            res += main.estacionamiento.getLugares()[i].getVecesEstacionado();
        }
        String pasaron = "Pasaron " + res + " carros";
        LOG.info(pasaron);
    }

    /**
     * Aqui esta su primer seccion crítica
     * Paso 1: Keep calm and ...
     * Paso 2: Beware with the concurrent code
     * Paso 3: Try to remember some basics of Java and POO
     * Paso 4: Obten el ID de tu hilo
     * Paso 5: TU CARRO (HILO) ENTRARA AL ESTACIONAMIENTO (Los Hilos simulan ser
     * carros,
     * no es necesario que generes clase Carro (puedes hacerlo si quieres))
     */
    @Override
    public void run() {
        try {
            int id = Integer.parseInt(Thread.currentThread().getName());
            this.estacionamiento.entraCarro(id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
