
import java.util.Comparator;
import java.io.Serializable;

public class ComparatorParStringInt implements Comparator<ParStringInt>, Serializable
{
    public int compare(ParStringInt i1, ParStringInt i2) {
        if(i1.getNumero()>i2.getNumero()) return -1;
        if(i1.getNumero()<i2.getNumero()) return 1;
        else {return i1.getString().compareTo(i2.getString());}
    }
}
