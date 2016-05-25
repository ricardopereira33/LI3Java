import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;

public class CatClientes{
    private Set<Cliente> CatalogoClientes;
   
    public CatClientes(){
        this.CatalogoClientes = new HashSet<>();
    }
    
    public CatClientes(TreeSet<Cliente> CatalogoClientes){
        this.CatalogoClientes = CatalogoClientes;
    }
    
    public CatClientes(CatClientes a){
        this.CatalogoClientes = a.getCatalogoClientes();
    }
    
    public int sizeCatalogoClientes(){
        return CatalogoClientes.size();
    }
    
    public Set<Cliente> getCatalogoClientes(){
        return this.CatalogoClientes;
    }
    
    public void setCatalogoClientes(TreeSet<Cliente> CatalogoClientes){
        this.CatalogoClientes=CatalogoClientes;
    }
    
    public void addCliente(Cliente c){
        CatalogoClientes.add(c);
    }
    
    public CatClientes clone(){
        return new CatClientes(this);
    }
}
