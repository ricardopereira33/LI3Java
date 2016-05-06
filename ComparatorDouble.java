import java.util.Comparator;

public class ComparatorDouble implements Comparator<ParStringDouble>{
    
   public int compare(ParStringDouble a, ParStringDouble b){
       return a.getNumero().compareTo(b.getNumero());
   }
}