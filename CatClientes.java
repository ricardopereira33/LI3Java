import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

public class CatClientes implements Serializable{
    
    private List<Set<String>> catalogo;
   
    public CatClientes(){
        catalogo = new ArrayList<>(26);
        Set<String> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeSet<>();
            this.catalogo.add(i,arvore);
        }
    }
    
    public CatClientes(Collection <String> collection){
        int indice;
        for(String cliente: collection){
            indice = calculaIndice(cliente.charAt(0));
            this.catalogo.get(indice).add(cliente);
        }
    }
    
    public CatClientes(CatClientes catalogoClientes){
        int indice;
        
        catalogo = new ArrayList<>(26);
        Set<String> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeSet<>();
            this.catalogo.add(i,arvore);
        }
        
        for(indice=0; indice < catalogoClientes.getCatalogo().size(); indice++){
            for(String cliente: catalogoClientes.getCatalogo().get(indice)){
                this.catalogo.get(indice).add(cliente);
            }
        } 
    }
    
    public List<Set<String>> getCatalogo(){//retornar um clone??
        return this.catalogo;
    }
    //Limpar
    public void limpar (){
       this.catalogo.clear();
    }
   
    // Gets
    
    public List<String> getClientes(){
        List<String> lista = new ArrayList<String>();
        for(Set<String> arvore: this.catalogo){
            for(String cliente: arvore)
                lista.add(cliente);
        }
        return lista;
    }
    
    // Métodos
    
    public List<String> getClientesLetra(char letra){
        int indice = calculaIndice(letra);
        List<String> lista = new ArrayList<String>();
        for(String cliente: this.catalogo.get(indice))
            lista.add(cliente);
        return lista;
    }
   
    private static int calculaIndice(char letra){
        letra = Character.toUpperCase(letra);
        return letra - 'A';
    }
    
    public int totalClientes(){
        int total=0;
        for(Set<String> arvore: this.catalogo)
            total+=arvore.size();
        return total;
    }
    
    public int totalClientesLetra(char letra){
        return this.catalogo.get(calculaIndice(letra)).size();
    }
    
    public void insereCliente(String cliente){
        int indice = calculaIndice(cliente.charAt(0));
        this.catalogo.get(indice).add(cliente);
    }
    
    public void removeCliente(String cliente){
        int indice = calculaIndice(cliente.charAt(0));
        this.catalogo.get(indice).remove(cliente);
    }
    
    public boolean existeCliente(String cliente){
        int indice = calculaIndice(cliente.charAt(0));
        return this.catalogo.get(indice).contains(cliente);
    }
    
    // clone
    public CatClientes clone(){
        return new CatClientes(this);
    }
    
}
