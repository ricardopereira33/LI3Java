import java.util.Comparator;
import java.io.Serializable;

public class ComparatorByString implements Comparator<String>, Serializable{
   public int compare(String a, String b){
       return a.compareTo(b);
   }
}
