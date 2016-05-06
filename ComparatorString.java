import java.util.Comparator;

public class ComparatorString implements Comparator<ParStringDouble>{
   public int compare(ParStringDouble a, ParStringDouble b){
       return a.getString().compareTo(b.getString());
   }
}
