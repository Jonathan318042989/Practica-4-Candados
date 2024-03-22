package kass.concurrente.invitados;

import kass.concurrente.candados.Semaphore;
import kass.concurrente.candadosImpl.Filtro;

/**
 * Clase que modela al inversionista, pero esta vez
 * usando el filtro.
 * No se sobreescribe el run, si hicieron bien las cosas
 * Entonces se pasara sin problemas para aca
 * Good Luck!
 * @version 1.1
 * @author Kassandra Mirael
 */
public class InversionistaFiltro extends Inversionista {

    private Semaphore semaforo = new Filtro(5,5);

    @Override
    public void entraALaMesa() {
        semaforo.acquire();
        tomaTenedores();
        come();
        sueltaTenedores();
        semaforo.release();
    }

    @Override
    public void tomaTenedores() {
        tenedorIzq.tomar();
        tenedorDer.tomar();
    }

    @Override
    public void sueltaTenedores() {
        tenedorIzq.soltar();
        tenedorDer.soltar();
    }
}
