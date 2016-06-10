import java.util.Comparator;
import java.io.Serializable;

public class ComparatorTriploStringIntInt implements Comparator<TriploStringIntInt>,Serializable
{
    public int compare(TriploStringIntInt i1, TriploStringIntInt i2) {
        if(i1.getSegundo()>i2.getSegundo()) return -1;
        if(i1.getSegundo()<i2.getSegundo()) return 1;
        else return i1.getPrimeiro().compareTo(i2.getPrimeiro());
    }
}