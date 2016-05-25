import java.util.Comparator;
import java.io.Serializable;

public class ComparatorCliente implements Comparator<Cliente>, Serializable
{
    public int compare(Cliente a, Cliente b){
       return a.getNomeCliente().compareTo(b.getNomeCliente());
   }
}
