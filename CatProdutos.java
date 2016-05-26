import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

public class CatProdutos implements Serializable{
    
    private List<Set<Produto>> catalogo;
   
    public CatProdutos(){
        catalogo = new ArrayList<>(26);
        Set<Produto> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeSet<>(new ComparatorProduto());
            this.catalogo.add(i,arvore);
        }
    }
    
    public CatProdutos(Collection <Produto> collection){
        int indice;
        for(Produto produto: collection){
            indice = calculaIndice(produto.getNomeProduto().charAt(0));
            this.catalogo.get(indice).add(produto.clone());
        }
    }
    
    public CatProdutos(CatProdutos catalogoProdutos){
        int indice;
        
        for(indice=0; indice < catalogoProdutos.catalogo.size(); indice++){
            for(Produto produto: catalogoProdutos.catalogo.get(indice)){
                this.catalogo.get(indice).add(produto.clone());
            }
        }
    }
    
    //Limpar
    public void limpar (){
       this.catalogo.clear();
    }
   
    // Gets
    
    public List<Produto> getProdutos(){
        List<Produto> lista = new ArrayList<Produto>();
        for(Set<Produto> arvore: this.catalogo){
            for(Produto produto: arvore)
                lista.add(produto.clone());
        }
        return lista;
    }
    
    // Métodos
    
    public List<Produto> getProdutosLetra(char letra){
        int indice = calculaIndice(letra);
        List<Produto> lista = new ArrayList<Produto>();
        for(Produto produto: this.catalogo.get(indice))
            lista.add(produto.clone());
        return lista;
    }
   
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    public int totalProdutos(){
        int total=0;
        for(Set<Produto> arvore: this.catalogo)
            total+=arvore.size();
        return total;
    }
    
    public int totalProdutosLetra(char letra){
        return this.catalogo.get(calculaIndice(letra)).size();
    }
    
    public void insereProduto(Produto produto){
        int indice = calculaIndice(produto.getNomeProduto().charAt(0));
        this.catalogo.get(indice).add(produto.clone());
    }
    
    public void removeProduto(Produto produto){
        int indice = calculaIndice(produto.getNomeProduto().charAt(0));
        this.catalogo.get(indice).remove(produto);
    }
    
    public boolean existeProduto(Produto produto){
        int indice = calculaIndice(produto.getNomeProduto().charAt(0));
        return this.catalogo.get(indice).contains(produto);
    }
    
    // clone
    public CatProdutos clone(){
        return new CatProdutos(this);
    }
    
}
