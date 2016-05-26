import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

public class CatClientes implements Serializable{
    
    private List<Set<Cliente>> catalogo;
   
    public CatClientes(){
        catalogo = new ArrayList<>(26);
        Set<Cliente> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeSet<>(new ComparatorCliente());
            this.catalogo.add(i,arvore);
        }
    }
    
    public CatClientes(Collection <Cliente> collection){
        int indice;
        for(Cliente cliente: collection){
            indice = calculaIndice(cliente.getNomeCliente().charAt(0));
            this.catalogo.get(indice).add(cliente.clone());
        }
    }
    
    public CatClientes(CatClientes catalogoClientes){
        int indice;
        
        for(indice=0; indice < catalogoClientes.catalogo.size(); indice++){
            for(Cliente cliente: catalogoClientes.catalogo.get(indice)){
                this.catalogo.get(indice).add(cliente.clone());
            }
        }
    }
    
    //Limpar
    public void limpar (){
       this.catalogo.clear();
    }
   
    // Gets
    
    public List<Cliente> getClientes(){
        List<Cliente> lista = new ArrayList<Cliente>();
        for(Set<Cliente> arvore: this.catalogo){
            for(Cliente cliente: arvore)
                lista.add(cliente.clone());
        }
        return lista;
    }
    
    // Métodos
    
    public List<Cliente> getClientesLetra(char letra){
        int indice = calculaIndice(letra);
        List<Cliente> lista = new ArrayList<Cliente>();
        for(Cliente cliente: this.catalogo.get(indice))
            lista.add(cliente.clone());
        return lista;
    }
   
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    public int totalClientes(){
        int total=0;
        for(Set<Cliente> arvore: this.catalogo)
            total+=arvore.size();
        return total;
    }
    
    public int totalClientesLetra(char letra){
        return this.catalogo.get(calculaIndice(letra)).size();
    }
    
    public void insereCliente(Cliente cliente){
        int indice = calculaIndice(cliente.getNomeCliente().charAt(0));
        this.catalogo.get(indice).add(cliente.clone());
    }
    
    public void removeCliente(Cliente cliente){
        int indice = calculaIndice(cliente.getNomeCliente().charAt(0));
        this.catalogo.get(indice).remove(cliente);
    }
    
    public boolean existeCliente(Cliente cliente){
        int indice = calculaIndice(cliente.getNomeCliente().charAt(0));
        return this.catalogo.get(indice).contains(cliente);
    }
    
    // clone
    public CatClientes clone(){
        return new CatClientes(this);
    }
    
}
