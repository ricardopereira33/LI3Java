import java.util.Comparator;

public class ComparatorProduto implements Comparator<Produto>
{
    public int compare(Produto a, Produto b){
       return a.getNomeProduto().compareTo(b.getNomeProduto());
   }
}
