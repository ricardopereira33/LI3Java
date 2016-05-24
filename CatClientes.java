import java.util.TreeSet;

public class CatClientes{
    private TreeSet<String> CatalogoClientes;
   
    public CatClientes(){
        this.CatalogoClientes = new TreeSet<String>();
    }
    
    public CatClientes(TreeSet<String> CatalogoClientes){
        this.CatalogoClientes = CatalogoClientes;
    }
    
    public CatClientes(CatClientes a){
        this.CatalogoClientes = a.getCatalogoClientes();
    }
    
    public int sizeCatalogoClientes(){
        return CatalogoClientes.size();
    }
    
    public TreeSet<String> getCatalogoClientes(){
        return this.CatalogoClientes;
    }
    
    public void setCatalogoClientes(TreeSet<String> CatalogoClientes){
        this.CatalogoClientes=CatalogoClientes;
    }
    
    public void addCliente(String cliente){
        CatalogoClientes.add(cliente);
    }
    
    public CatClientes clone(){
        return new CatClientes(this);
    }
}
