import java.util.Comparator;
import java.io.Serializable;

public class ComparatorTriploStringIntDouble implements Comparator<TriploStringIntDouble>,Serializable
{
    public int compare(TriploStringIntDouble i1, TriploStringIntDouble i2) {
        if(i1.getSegundo()>i2.getSegundo()) return -1;
        if(i1.getSegundo()<i2.getSegundo()) return 1;
        else return i1.getPrimeiro().compareTo(i2.getPrimeiro());
    }
}