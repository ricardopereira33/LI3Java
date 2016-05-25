import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;

public class CatProdutos{
    private Set<Produto> CatalogoProdutos;
    
    public CatProdutos(){
        this.CatalogoProdutos = new HashSet<>();
    }
    
    public CatProdutos(Set<Produto> CatalogoProdutos){
        this.CatalogoProdutos = CatalogoProdutos;
    }
    
    public CatProdutos(CatProdutos a){
        this.CatalogoProdutos = a.getCatalogoProdutos();
    }
    
    public CatProdutos clone(){
        return new CatProdutos(this);
    }
    
    public int sizeCatalogoProdutos(){
        return CatalogoProdutos.size();
    }
    
    public Set<Produto> getCatalogoProdutos(){
        return this.CatalogoProdutos;
    }
    
    public void setCatalogoClientes(Set<Produto> CatalogoProdutos){
        this.CatalogoProdutos = CatalogoProdutos;
    }
    
    public void addProduto(Produto p){
        CatalogoProdutos.add(p);
    }
}