import java.util.TreeSet;

public class CatProdutos{
    private TreeSet<String> CatalogoProdutos;
    
    public CatProdutos(){
        this.CatalogoProdutos = new TreeSet<String>();
    }
    
    public CatProdutos(TreeSet<String> CatalogoProdutos){
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
    
    public TreeSet<String> getCatalogoProdutos(){
        return this.CatalogoProdutos;
    }
    
    public void setCatalogoClientes(TreeSet<String> CatalogoProdutos){
        this.CatalogoProdutos = CatalogoProdutos;
    }
    
    public void addProduto(String produto){
        CatalogoProdutos.add(produto);
    }
}