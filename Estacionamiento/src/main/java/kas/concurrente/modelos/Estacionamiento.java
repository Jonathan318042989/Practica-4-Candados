package kas.concurrente.modelos;

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

    private Integer lugaresDisponibles;
    private Integer capacidad;
    private Lugar[] lugares;

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
        /**
         * Aqui va tu codigo
         */
    }

    /**
     * Metodo que asigna el lugar, una vez asignado ESTACIONA su nave
     * 
     * @param lugar El lugar que correspone
     * @throws InterruptedException
     */
    public void asignaLugar(int lugar) throws InterruptedException {
        /**
         * Aqui va tucodigo
         */
    }

    /**
     * Se obtiene un lugar de forma pseudoAleatoria
     * Aqui necesito que revisen el repaso de estadistica que mande en
     * repaso, quiero que expliquen porque lo pedimos en forma pseudoAleatoria
     * 
     * @return Retorna el indice del lugar
     */
    public int obtenLugar() {
        /**
         * Aqui va tu codigo
         */
        return -1;
    }
}