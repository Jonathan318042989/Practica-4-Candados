package kas.concurrente.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LugarTest {
    Semaphore semaforo;
    Lugar lugar;
    List<Thread> hilos;
    static final int numHilos = 15;

    @BeforeEach
    void setUp() throws InterruptedException {
        lugar = new Lugar(1);
        semaforo = new Semaphore(1);
        initHilos();
    }

    @Test
    void constructorTest() {
        assertTrue(lugar.getId() == 1 && lugar.getDisponible() == true && lugar.getSemaforo() != null);
    }

    @Test
    void estacionaTest() throws InterruptedException {
        lugar.estaciona();
        assertTrue(lugar.getDisponible());
    }

    @Test
    void contadorLugarTest() throws InterruptedException {
        Integer vecesEstacionadoActual = lugar.getVecesEstacionado();
        lugar.estaciona();
        assertEquals(vecesEstacionadoActual + 1, lugar.getVecesEstacionado());
    }

    void initHilos() {
        hilos = new ArrayList<>();

        for (int i = 0; i < numHilos * 2; ++i) {
            Thread t = new Thread(this::simulaCS, "" + i);
            hilos.add(t);
        }
    }

    void simulaCS() {
        try {
            lugar.estaciona();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
