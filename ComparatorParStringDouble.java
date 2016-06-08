import java.util.Comparator;
import java.io.Serializable;

public class ComparatorParStringDouble implements Comparator<ParStringDouble>, Serializable
{
   public int compare(ParStringDouble i1, ParStringDouble i2) {
        if(i1.getNumero()>i2.getNumero()) return -1;
        if(i1.getNumero()<i2.getNumero()) return 1;
        else return 0;
    }
}
