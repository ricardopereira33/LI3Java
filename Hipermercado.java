import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.TreeSet;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileWriter;


public class Hipermercado implements Serializable{
    
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
   
   public void carregaDados(){
       ArrayList<Cliente> clientes = Leitura.leituraClientes();
       ArrayList<Produto> produtos = Leitura.leituraProdutos();
       this.carregarCatalogoProdutos(produtos);
       this.carregarCatalogoClientes(clientes);
       clientes.clear();
       produtos.clear();
       ArrayList<Venda> vendas = Leitura.leituraVendas(CatalogoProdutos,CatalogoClientes);
   }
   
   public void carregarCatalogoProdutos(ArrayList<Produto> produtos){
       for(Produto p: produtos){
           CatalogoProdutos.insereProduto(p);
       }
   }
   
    public void carregarCatalogoClientes(ArrayList<Cliente> clientes){
       for(Cliente c: clientes){
           CatalogoClientes.insereCliente(c);
       }
   }
   
   /**
     * Gravar o estado da aplicação num determinado ficheiro.
     * @param fich
     */
    public void gravaObj(String fich) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    /**
     * Iniciar a aplicação com o estado guardado num determinado ficheiro.
     * @param fich
     * @return
     */
    public static Hipermercado leObj(String fich) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
        Hipermercado te = (Hipermercado) ois.readObject();
        ois.close();
        return te;
    }
   
}
