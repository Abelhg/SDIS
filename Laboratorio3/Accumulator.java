package server.box;

public class Accumulator implements java.io.Serializable {
    private int acumulado;

    public Accumulator(int valorInicial){
        this.acumulado = valorInicial;    
    }
    
    public int value(){
        return acumulado;
    }
    
    public int increment(){
        return this.acumulado++;
    }
}
