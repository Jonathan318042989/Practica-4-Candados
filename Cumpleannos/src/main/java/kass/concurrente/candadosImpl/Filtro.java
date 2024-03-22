package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Semaphore;

import java.util.Arrays;

/**
 * Clase que modela el Algoritmo del Filtro Modificado
 * Este algoritmo es similar al del filtro, lo diferente es que
 * permite una cantidad m de hilos SIMULTANEOS en la seccion critica
 * Todo es casi igual, solo realiza la modificacion pertinente para esto
 * 
 * @version 1.0
 * @author Kassandra Mirael
 */
public class Filtro implements Semaphore {

    private volatile int[] victima;
    private volatile int[] nivel;
    private Integer numHilos;
    private Integer maxHilos;

    /**
     * Constructor del Filtro
     * 
     * @param hilos                El numero de Hilos Permitidos
     * @param maxHilosConcurrentes EL numero de hilos concurrentes simultaneos
     */
    public Filtro(int hilos, int maxHilosConcurrentes) {
        this.numHilos = hilos;
        this.maxHilos = maxHilosConcurrentes;
        this.victima = new int[numHilos];
        this.nivel = new int[numHilos];
    }

    @Override
    public int getPermitsOnCriticalSection() {
        return maxHilos;
    }

    @Override
    public void acquire() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        for (int i = 1; i < this.numHilos; i++) {
            this.nivel[id] = i;
            this.victima[i] = id;
            boolean conflicto = true;
            while (conflicto) {
                conflicto = false;
                for (int k = 1; k < this.numHilos; k++) {
                    if (k != id && this.nivel[k] >= i && this.victima[i] == id) {
                        conflicto = true;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void release() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        this.nivel[id] = 0;
    }

}
