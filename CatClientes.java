import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

public class CatClientes implements Serializable{
    
    private List<Set<String>> catalogo;
   
    /**
     * Construtor vazio do Católogo de Clientes.
     */
    public CatClientes(){
        catalogo = new ArrayList<>(26);
        Set<String> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new HashSet<>();
            this.catalogo.add(i,arvore);
        }
    }
    
    /**
     * Construtor por parâmetros do Catálogo de Clientes.
     * @param collection
     */
    public CatClientes(Collection <String> collection){
        int indice;
        for(String cliente: collection){
            indice = calculaIndice(cliente.charAt(0));
            this.catalogo.get(indice).add(cliente);
        }
    }
    
    /**
     * Construtor por cópia do Catálogo de Clientes.
     * @param catalogoProdutos
     */
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
    
    /**
     * Função que retorna o catálogo.
     * @return
     */
    public List<Set<String>> getCatalogo(){//retornar um clone??
        return this.catalogo;
    }
   
    /**
     * Função que retorna a lista de Clientes.
     * @return
     */
    public List<String> getClientes(){
        List<String> lista = new ArrayList<String>();
        for(Set<String> arvore: this.catalogo){
            for(String cliente: arvore)
                lista.add(cliente);
        }
        return lista;
    }
    
    // Métodos
    
    /**
     * Função que retorna uma lista de Clientes começados por uma determinada letra.
     * @param letra
     * @return
     */
    public List<String> getClientesLetra(char letra){
        int indice = calculaIndice(letra);
        List<String> lista = new ArrayList<String>();
        for(String cliente: this.catalogo.get(indice))
            lista.add(cliente);
        return lista;
    }
   
    /**
     * Função que calcula o índice dado a primeira letra de um cliente.
     * @param letra
     * @return
     */
    private static int calculaIndice(char letra){
        letra = Character.toUpperCase(letra);
        return letra - 'A';
    }
    
    /**
     * Função que retorna o total de clientes presentes no Catálogo.
     * @return
     */
    public int totalClientes(){
        int total=0;
        for(Set<String> arvore: this.catalogo)
            total+=arvore.size();
        return total;
    }
    
    /**
     * Função que retorna o total de clientes de uma determinada letra presentes no Catálogo.
     * @param letra
     * @return 
     */
    public int totalClientesLetra(char letra){
        return this.catalogo.get(calculaIndice(letra)).size();
    }
    
    /**
     * Função que insere um cliente ao catálogo.
     * @param cliente
     */
    public void insereCliente(String cliente){
        int indice = calculaIndice(cliente.charAt(0));
        this.catalogo.get(indice).add(cliente);
    }
    
    /**
     * Função que remove um cliente do catálogo.
     * @param cliente
     */
    public void removeCliente(String cliente){
        int indice = calculaIndice(cliente.charAt(0));
        this.catalogo.get(indice).remove(cliente);
    }
    
    /**
     * Função que determina a existência ou não de um cliente no catálogo.
     * @param cliente
     * @return
     */
    public boolean existeCliente(String cliente){
        int indice = calculaIndice(cliente.charAt(0));
        return this.catalogo.get(indice).contains(cliente);
    }
    
    /**
    * Função que faz clone do Catálogo de Clientes.
    */
    public CatClientes clone(){
        return new CatClientes(this);
    }
    
    /**
     * Função que limpa o catálogo.
     */
    public void limpar (){
       this.catalogo.clear();
    }
    
}
