import java.util.Comparator;
import java.io.Serializable;

public class ComparatorProduto implements Comparator<Produto>,Serializable
{
    public int compare(Produto a, Produto b){
       return a.getNomeProduto().compareTo(b.getNomeProduto());
   }
}
