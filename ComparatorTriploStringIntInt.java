import java.util.Comparator;
public class ComparatorTriploStringIntInt implements Comparator<TriploStringIntInt>
{
    public int compare(TriploStringIntInt i1, TriploStringIntInt i2) {
        if(i1.getSegundo()>i2.getSegundo()) return -1;
        if(i1.getSegundo()<i2.getSegundo()) return 1;
        else return i1.getPrimeiro().compareTo(i2.getPrimeiro());
    }
}