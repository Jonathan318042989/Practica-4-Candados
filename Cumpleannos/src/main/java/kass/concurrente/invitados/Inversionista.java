package kass.concurrente.invitados;

import java.util.logging.Logger;

import kass.concurrente.tenedor.*;
import kass.concurrente.candadosImpl.PetersonLock;
import kass.concurrente.candados.Lock;

/**
 * Clase abstracta que modela al inversionista.
 * El inversionista tiene 2 tenedores a sus lados.
 * El inversionista posee un ID para que se pueda identificar.
 * El inversionista tiene una variable que indica el numero de veces que ha
 * comido.
 * 
 * @version 1.0
 * @author Kassandra Mirael
 */
public abstract class Inversionista implements Runnable {

    private static final Logger LOG = Logger.getLogger("paquete.NombreClase");

    private Lock lock = new PetersonLock();

    TenedorImpl tenedorDer;

    TenedorImpl tenedorIzq;

    /* El id con el que se identifica */
    private Integer id;

    /* Las veces que a comido el inversionista */
    private Integer vecesComido = 0;

    @Override
    public void run() {
        for(int i = 0; i < 500; i++){
            try {
                piensa();
                entraALaMesa();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        /**
         * El inversionista debe pensar y entrar a la mesa un periodo de veces
         * puesto en el test, agrega el valor aqui.
         */
    }

    /**
     * Metodo que nos permite entrar a la mesa.
     * El inversionista por fin dejo de pensar y de escribir en su
     * servilleta y se digna en entrar.
     * PRIMERO toma los tenedores.
     * DESPUES come.
     * FINALMENTE los suelta para que los demas los puedan usar.
     * 
     * @throws InterruptedException Cuando el hilo que se utiliza en el método come
     *                              se detiene
     */
    public void entraALaMesa() throws InterruptedException {
        tomaTenedores();
        come();
        sueltaTenedores();
    }

    /**
     * Una vez que termino de pensar sobre finanzas el inversionista
     * se prepara para comer.
     * El inversionista le toma un par de milisegundos comer.
     * ESTA ES LA SECCION CRITICA, SIGNIFICA PELIGRO
     * Incrementa el numero de veces que ha comido.
     * 
     * @throws InterruptedException Cuando el hilo que se utiliza para dormir se
     *                              detenga
     */
    public void come() throws InterruptedException {
        lock.lock();
        while (tenedorDer.getEsUtilizado() || tenedorIzq.getEsUtilizado()) {
            tomaTenedores();
        }
        Thread.sleep(generaTiempoDeEspera());
        sueltaTenedores();
        vecesComido++;
        lock.unlock();
    }

    /**
     * Metodo que hace que el inversionista piense.
     * El inversionista pensara por una par de milisegundos.
     * Esto pasa antes de que tome sus tenedores.
     * 
     * @throws InterruptedException En caso de que el hilo que se utiliza para
     *                              dormir se detenga
     */
    public void piensa() throws InterruptedException {
        Thread.sleep(generaTiempoDeEspera());
    }

    /**
     * Metodo que nos permite tomar los tenedores.
     * Los toma uno por uno.
     */
    public abstract void tomaTenedores();

    /**
     * Metodo que hace que el inversionista suelte ambos tenedores una vez
     * que terminara de comer.
     * De esta manera otro los puede usar.
     * Suelta los tenedores uno por uno.
     */
    public abstract void sueltaTenedores();

    /**
     * Metodo que genera un numero pseudoaleatorio entre 1 y 10
     * 
     * @return El tiempo de espera
     */
    private long generaTiempoDeEspera() {
        double i = Math.random() * 10.0;
        return (long) i;
    }

    /*
     * Rellena Getter and Setters primero
     * Documenta los metodos.
     * Cuando acabes borra estew comentario
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tenedor getTenedorIzq() {
        return tenedorIzq;
    }

    public void setTenedorIzq(Tenedor t) {
        if (t instanceof TenedorImpl) {
            TenedorImpl tenedor = (TenedorImpl) t;
            this.tenedorIzq = tenedor;
        }
    }

    public Tenedor getTenedorDer() {
        return tenedorDer;
    }

    public void setTenedorDer(Tenedor t) {
        if (t instanceof TenedorImpl) {
            TenedorImpl tenedor = (TenedorImpl) t;
            this.tenedorDer = tenedor;
        }
    }

    public int getVecesComido() {
        return vecesComido;
    }

    public void setVecesComido(int vecesComido) {
        this.vecesComido = vecesComido;
    }
}
