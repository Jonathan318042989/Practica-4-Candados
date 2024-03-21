package kas.concurrente.modelos;

import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * Clase que modela un Lugar
 * El lugar consta de un id
 * un booleano que nos dice si esta dispoible
 * y un objeto del tipo Semaphore (El semaforo)
 * 
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Lugar {
    private Random random = new Random();
    private Integer id;
    private Boolean disponible;
    private Semaphore semaforo;
    private volatile int vecesEstacionado;

    /**
     * Metodo constructor
     * El lugar por defecto esta disponible
     * Pueden llegar un numero n de carros en el peor de los casos
     * veces estacionado sera el numero de veces que se han estacianado en el lugar
     * Si llegan 2 carros y ambos se estacionan, entonces, su valor sera de 2
     * 
     * @param id El id del Lugar
     */
    public Lugar(int id) {
        this.setId(id);
        this.setDisponible(true);
        this.setSemaforo(1);
        this.setVecesEstacionado(0);
    }

    /**
     * En este metodo se simula que se estaciona
     * PELIGRO: ESTAS ENTRANDO A LA 2da SECCION CRITICA
     * Cambia el valor de disponible a false
     * Y se simula que vas pastel de cumple :D
     * Al final, imprime un texto color ROJO diciendo que va salir (Esperen
     * instrucciones para esto)
     * 
     * @throws InterruptedException Si algo falla
     */
    public void estaciona() throws InterruptedException {
        this.semaforo.acquire();
        this.setDisponible(false);
        this.vePorPastel();
        this.semaforo.release();
    }

    /**
     * En este metodo se genera la sumulación de espera
     * Se genera un tiempo entre 1 y 5 segundos
     * Es pseudo aleatorio
     * 
     * @throws InterruptedException En caso de que falle
     */
    public void vePorPastel() throws InterruptedException {
        Integer tiempo = random.nextInt(1000, 5000);
        Thread.sleep(tiempo);
        this.incrementaVecesEstacionado();
        this.setDisponible(true);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Semaphore getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(int lugaresDisponibles) {
        this.semaforo = new Semaphore(lugaresDisponibles);
    }

    public Integer getVecesEstacionado() {
        return vecesEstacionado;
    }

    public void setVecesEstacionado(Integer vecesEstacionado) {
        this.vecesEstacionado = vecesEstacionado;
    }

    private void incrementaVecesEstacionado() {
        this.setVecesEstacionado(this.getVecesEstacionado() + 1);
    }

}
