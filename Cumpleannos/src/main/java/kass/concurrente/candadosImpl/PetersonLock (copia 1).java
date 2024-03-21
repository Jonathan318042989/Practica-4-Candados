package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Lock;

/**
 * Clase que implementa el candado usando el Legendario
 * algoritmo de PeterGod.
 * No hay mucho que decir, ya saben que hacer
 * @version 1.0
 * @author Kassandra Mirael
 */
public class PetersonLock implements Lock {

    private Boolean[] flag = new Boolean[2];

    private Integer victima;

    @Override
    public void lock() {
        int id = ThreadID.get();
        flag[id] = true;
        victima = id;
        while (flag[1-id].booleanValue() && victim == id){
        }
    }

    @Override
    public void unlock() {
        int i = ThreadID.get();
        flag[i] = false;
    }    
}
