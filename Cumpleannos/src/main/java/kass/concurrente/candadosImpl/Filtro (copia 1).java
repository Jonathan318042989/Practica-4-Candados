package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Semaphore;

/**
 * Clase que modela el Algoritmo del Filtro Modificado
 * Este algoritmo es similar al del filtro, lo diferente es que
 * permite una cantidad m de hilos SIMULTANEOS en la seccion critica
 * Todo es casi igual, solo realiza la modificacion pertinente para esto
 * @version 1.0
 * @author Kassandra Mirael
 */
public class Filtro implements Semaphore {

    Boolean[] flag;
    Integer[] label;
    Integer numHilos;
    Integer maxHilos;

    /**
     * Constructor del Filtro
     * @param hilos El numero de Hilos Permitidos
     * @param maxHilosConcurrentes EL numero de hilos concurrentes simultaneos
     */
    public Filtro(int hilos, int maxHilosConcurrentes) {
        this.numHilos = hilos;
        this.maxHilos = maxHilosConcurrentes;
    }

    @Override
    public int getPermitsOnCriticalSection() {
        return maxHilos;
    }

    @Override
    public void acquire() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acquire'");
    }

    @Override
    public void release() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'release'");
    }
    
}
