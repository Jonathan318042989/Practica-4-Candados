package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Lock;

/**
 * Clase que implementa el candado usando el Legendario
 * algoritmo de PeterGod.
 * No hay mucho que decir, ya saben que hacer
 * 
 * @version 1.0
 * @author Kassandra Mirael
 */
public class PetersonLock implements Lock {

    private Boolean[] flag = new Boolean[2];

    private volatile int victima;

    @Override
    public void lock() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        flag[id] = true;
        this.victima = id;
        while (Boolean.TRUE.equals(flag[1 - id]) && this.victima == id) {
        }
    }

    @Override
    public void unlock() {
        int i = Integer.parseInt(Thread.currentThread().getName());
        flag[i] = false;
    }

}
