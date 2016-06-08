import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class ParStringDouble{
    
    private String string;
    private double numero;
    
    public static void main(String[] args){
        ArrayList<ParStringDouble> paresSD = new ArrayList<ParStringDouble> (Arrays.asList(  
                                                            new ParStringDouble("X500", 20.75),
                                                            new ParStringDouble("Z398", 11.45),
                                                            new ParStringDouble("A11", 2.5),
                                                            new ParStringDouble("W455", 12.5)));
       
       TreeSet<ParStringDouble> tree = new TreeSet<ParStringDouble>(new ComparatorDouble());                                                                                
       Crono.start();
       for(ParStringDouble p: paresSD){
           tree.add(p);
       }
       Crono.stop();
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println(tree.size());
    }
    
    public ParStringDouble(String string, Double numero){
        this.string = string;
        this.numero = numero;
    }
    
    public ParStringDouble(ParStringDouble a){
        this(a.getString(),a.getNumero());
    }
    
    public String getString(){
        return this.string;
    }
    
    public Double getNumero(){
        return this.numero;
    }    
    
    public ParStringDouble clone() {
        return new ParStringDouble(this);
    }
    
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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(string.toString());
        sb.append("\n");
        sb.append(numero);
        sb.append("\n");
        return sb.toString();
    }
}
