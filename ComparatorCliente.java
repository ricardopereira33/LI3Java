import java.util.Comparator;

public class ComparatorCliente implements Comparator<Cliente>
{
    public int compare(Cliente a, Cliente b){
       return a.getNomeCliente().compareTo(b.getNomeCliente());
   }
}
