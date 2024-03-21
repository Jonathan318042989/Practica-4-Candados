package kass.concurrente.tenedor;

/**
 * Clase que implementa el tenedor
 * Tenemos una variable entera que cuenta el numero de veces que fue tomado
 * Tiene una variable que simboliza su id
 * @version 1.1
 * @author <Su equipo>
 */
public class TenedorImpl implements Tenedor {

    //True en caso de que este siendo utilizado el tenedor
    Boolean esUtilizado = false;

    //Id del tenedor
    Integer id;

    //Las veces que se ha tomado el tenedor
    Integer vecesTomado = 0;

    public TenedorImpl(int id){
        this.id = id;
    }

    @Override
    public void tomar() {
        contador++;
        esUtilizado = true;
    }

    @Override
    public void soltar() {
        esUtilizado = false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getVecesTomado() {
        return contador;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setVecesTomado(int vecesTomado) {
        this.vecesTomado = vecesTomado;
    }
    
}
