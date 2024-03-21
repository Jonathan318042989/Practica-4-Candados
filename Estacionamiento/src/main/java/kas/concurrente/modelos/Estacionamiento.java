package kas.concurrente.modelos;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * En esta clase se simula el estacionamiento en si
 * Posee un conjunto de arreglos de tipo Lugar (o arreglo bidimensional?)
 * Posee un entero de lugaresDisponibles (Se podra hacer por pisos?) (Habra otra
 * manera de hacerlo mejor?)
 * 
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Estacionamiento {
    private Random random = new Random();
    private Semaphore semaforo;
    private volatile Integer lugaresDisponibles;
    private Integer capacidad;
    private Lugar[] lugares;
    private static final Logger LOG = Logger.getLogger("paquete.NombreClase");

    /**
     * Metodo constructor
     * Modifica el constructor o crea otro segun consideres necesario
     * 
     * @param capacidad La capacidad del estacionamiento
     */
    public Estacionamiento(int capacidad) {
        this.setLugaresDisponibles(capacidad);
        this.setCapacidad(capacidad);
        this.inicializaLugares(capacidad);
        this.semaforo = new Semaphore(capacidad);
    }

    public int getLugaresDisponibles() {
        return this.lugaresDisponibles;
    }

    public void setLugaresDisponibles(int lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Lugar[] getLugares() {
        return lugares;
    }

    public void setLugares(Lugar[] lugares) {
        this.lugares = lugares;
    }

    /**
     * Metodo que nos indica si esta lleno el estacionamiento
     * 
     * @return true si esta lleno, false en otro caso
     */
    public boolean estaLleno() {
        boolean ocupado = this.lugares[0].getDisponible();
        for (int i = 1; i < this.getCapacidad(); i++) {
            ocupado = ocupado || this.lugares[i].getDisponible();
        }
        return !ocupado;
    }

    /**
     * Metodo que inicaliza los lugares del arreglo
     * Este es un método optativo
     */
    public void inicializaLugares(int capacidad) {
        this.lugares = new Lugar[capacidad];
        for (int i = 0; i < capacidad; i++) {
            this.lugares[i] = new Lugar(i);
        }
    }

    /**
     * Metodo en el que se simula la entrada de un carro
     * Imprime un texto que dice que el carro a entrado de color AZUL
     * 
     * @param nombre El nombre del carro
     * @throws InterruptedException Si llega a fallar
     */
    public void entraCarro(int nombre) throws InterruptedException {
        String carroEntra = "\u001B[34m Carro " + nombre + " entrando";
        // LOG.info(carroEntra);
        Integer lugar = this.obtenLugar();
        this.semaforo.acquire();
        this.setLugaresDisponibles(this.getLugaresDisponibles() - 1);
        asignaLugar(lugar);
        this.setLugaresDisponibles(this.getLugaresDisponibles() + 1);
        this.semaforo.release();
        String carroSale = "\u001B[31m Carro " + nombre + " saliendo";
        // LOG.info(carroSale);
    }

    /**
     * Metodo que asigna el lugar, una vez asignado ESTACIONA su nave
     * 
     * @param lugar El lugar que correspone
     * @throws InterruptedException
     */
    public void asignaLugar(int lugar) throws InterruptedException {
        this.lugares[lugar].estaciona();
    }

    /**
     * Se obtiene un lugar de forma pseudoAleatoria
     * Aqui necesito que revisen el repaso de estadistica que mande en
     * repaso, quiero que expliquen porque lo pedimos en forma pseudoAleatoria
     * 
     * @return Retorna el indice del lugar
     */
    public int obtenLugar() {
        Integer indice = random.nextInt(capacidad);
        while (Boolean.FALSE.equals(this.lugares[indice].getDisponible())) {
            indice = random.nextInt(capacidad);
        }
        return indice;
    }
}