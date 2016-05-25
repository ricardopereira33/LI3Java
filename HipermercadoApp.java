import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class HipermercadoApp{
    
   private static Hipermercado hipermercado;
   
   public static void main(String[] args){
       // BufferedReader
       Hipermercado hipermercado = new Hipermercado();
       
       ArrayList<Cliente> clientes = Leitura.leituraClientes();
       ArrayList<Produto> produtos = Leitura.leituraProdutos();
       
       hipermercado.carregarCatalogoProdutos(produtos);
       hipermercado.carregarCatalogoClientes(clientes);
       /*tentar converter estas 4 funcoes em apenas duas, passando logo o que obtemos da leitura para 
       instancias principais do hipermercado*/
       
       Set <Produto> prod =  hipermercado.getCatProdutos().getCatalogoProdutos();
       Set <Cliente> cli = hipermercado.getCatClientes().getCatalogoClientes();
      
       ArrayList<Venda> vendas = Leitura.leituraVendas(prod,cli);
   
       System.out.println("Clientes: " + hipermercado.getCatClientes().sizeCatalogoClientes());
       System.out.println("Produtos: " + hipermercado.getCatProdutos().sizeCatalogoProdutos());
       
       System.out.println("--------------------------------------------------------");
   } 
}
