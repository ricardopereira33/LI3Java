import java.util.Comparator;
import java.io.Serializable;

public class ComparatorString implements Comparator<ParStringDouble>, Serializable{
   public int compare(ParStringDouble a, ParStringDouble b){
       return a.getString().compareTo(b.getString());
   }
}
