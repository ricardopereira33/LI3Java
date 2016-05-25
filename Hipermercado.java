import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.TreeSet;

public class Hipermercado{
    
   private CatClientes CatalogoClientes;
   private CatProdutos CatalogoProdutos;
   private Facturacao facturacao;
   private ArrayList<Filial> filiais;
   
   public Hipermercado(){
       this.CatalogoClientes = new CatClientes();
       this.CatalogoProdutos = new CatProdutos();
       this.facturacao = null;
       this.filiais = null;
   }
   
   public CatClientes getCatClientes(){
       return this.CatalogoClientes.clone();
   }
   
   public CatProdutos getCatProdutos(){
       return this.CatalogoProdutos.clone();
   }
    
   public void carregarCatalogoProdutos(ArrayList<Produto> produtos){
       for(Produto p: produtos){
           CatalogoProdutos.addProduto(p.clone());
       }
   }
   
    public void carregarCatalogoClientes(ArrayList<Cliente> clientes){
       for(Cliente c: clientes){
           CatalogoClientes.addCliente(c.clone());
       }
   }
}
