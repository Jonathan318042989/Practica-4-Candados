package kass.concurrente.tenedor;

/**
 * Clase que implementa el tenedor
 * Tenemos una variable entera que cuenta el numero de veces que fue tomado
 * Tiene una variable que simboliza su id
 * 
 * @version 1.1
 * @author <Su equipo>
 */
public class TenedorImpl implements Tenedor {

    // True en caso de que este siendo utilizado el tenedor
    Boolean esUtilizado = false;

    // Id del tenedor
    Integer id;

    // Las veces que se ha tomado el tenedor
    volatile Integer vecesTomado = 0;

    public TenedorImpl(int id) {
        this.id = id;
    }

    @Override
    public void tomar() {
        this.esUtilizado = true;
    }

    @Override
    public void soltar() {
        this.esUtilizado = false;
        vecesTomado = vecesTomado.intValue()+1;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getVecesTomado() {
        return this.vecesTomado;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setVecesTomado(int vecesTomado) {
        this.vecesTomado = vecesTomado;
    }

    /**
     * Nos indica si esta siendo utilizado
     * @return True en caso de que el tenedor este siendo utilizado, false en caso contrario
     */
    public boolean getEsUtilizado(){
        return esUtilizado;
    }
}
