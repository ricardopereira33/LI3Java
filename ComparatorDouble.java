import java.util.Comparator;
import java.io.Serializable;

public class ComparatorDouble implements Comparator<ParStringDouble>, Serializable{
    
   public int compare(ParStringDouble a, ParStringDouble b){
       return a.getNumero().compareTo(b.getNumero());
   }
}