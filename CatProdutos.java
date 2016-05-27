import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

public class CatProdutos implements Serializable{
    
    private List<Set<String>> catalogo;
   
    public CatProdutos(){
        catalogo = new ArrayList<>(26);
        Set<String> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeSet<>();
            this.catalogo.add(i,arvore);
        }
    }
    
    public CatProdutos(Collection <String> collection){
        int indice;
        for(String produto: collection){
            indice = calculaIndice(produto.charAt(0));
            this.catalogo.get(indice).add(produto);
        }
    }
    
    public CatProdutos(CatProdutos catalogoProdutos){
        int indice;
        
        for(indice=0; indice < catalogoProdutos.catalogo.size(); indice++){
            for(String produto: catalogoProdutos.catalogo.get(indice)){
                this.catalogo.get(indice).add(produto);
            }
        }
    }
    
    //Limpar
    public void limpar (){
       this.catalogo.clear();
    }
   
    // Gets
    
    public List<String> getProdutos(){
        List<String> lista = new ArrayList<String>();
        for(Set<String> arvore: this.catalogo){
            for(String produto: arvore)
                lista.add(produto);
        }
        return lista;
    }
    
    // Métodos
    
    public List<String> getProdutosLetra(char letra){
        int indice = calculaIndice(letra);
        List<String> lista = new ArrayList<String>();
        for(String produto: this.catalogo.get(indice))
            lista.add(produto);
        return lista;
    }
   
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    public int totalProdutos(){
        int total=0;
        for(Set<String> arvore: this.catalogo)
            total+=arvore.size();
        return total;
    }
    
    public int totalProdutosLetra(char letra){
        return this.catalogo.get(calculaIndice(letra)).size();
    }
    
    public void insereProduto(String produto){
        int indice = calculaIndice(produto.charAt(0));
        this.catalogo.get(indice).add(produto);
    }
    
    public void removeProduto(String produto){
        int indice = calculaIndice(produto.charAt(0));
        this.catalogo.get(indice).remove(produto);
    }
    
    public boolean existeProduto(String produto){
        int indice = calculaIndice(produto.charAt(0));
        return this.catalogo.get(indice).contains(produto);
    }
    
    // clone
    public CatProdutos clone(){
        return new CatProdutos(this);
    }
    
}
