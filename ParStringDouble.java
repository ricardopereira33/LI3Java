import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.io.Serializable;

public class ParStringDouble implements Serializable{
    
    private String string;
    private double numero;
    
    /**
     * Construtor por parâmetros.
     * @param string
     * @param numero
     */
    public ParStringDouble(String string, Double numero){
        this.string = string;
        this.numero = numero;
    }
    
    /**
     * Construtor por cópia.
     * @param a
     */
    public ParStringDouble(ParStringDouble a){
        this(a.getString(),a.getNumero());
    }
    
    /**
     * Função que retorna a String.
     * @return
     */
    public String getString(){
        return this.string;
    }
    
    /**
     * Função que retorna o número.
     * @return
     */
    public Double getNumero(){
        return this.numero;
    }    
    
    /**
     * Função que faz um clone.
     * @return
     */
    public ParStringDouble clone() {
        return new ParStringDouble(this);
    }
    
    /** 
     * Função que testa a igualdade.
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ParStringDouble hi = (ParStringDouble) obj;
        return hi.getString().equals(string) && hi.getNumero() == numero;
    }
    
    /**
     * Função para imprimir.
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(string.toString());
        sb.append("\n");
        sb.append(numero);
        sb.append("\n");
        return sb.toString();
    }
}
