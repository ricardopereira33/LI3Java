import java.util.ArrayList;
import java.util.TreeSet;

public class HipermercadoApp{
    
   private static Hipermercado hipermercado;
   
   public static void main(String[] args){
       // BufferedReader
       Hipermercado hipermercado = new Hipermercado();
       
       System.out.println("--------------------------------------------------------");
       System.out.println("Leitura do ficheiro: Vendas_3M.txt");
       Crono.start();
       ArrayList<Venda> vendas = Leitura.readVendasWithBuff("../Vendas_3M.txt");
       Crono.stop();
       System.out.println("Linhas lidas: " + vendas.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("--------------------------------------------------------");
       System.out.println("Leitura do ficheiro: Clientes.txt");
       Crono.start();
       ArrayList<Cliente> clientes = Leitura.readClientesWithBuff("../Clientes.txt");
       Crono.stop();
       System.out.println("Linhas lidas: " + clientes.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("--------------------------------------------------------");
       System.out.println("Leitura do ficheiro: Produtos.txt");
       Crono.start();
       ArrayList<Produto> produtos = Leitura.readProdutosWithBuff("../Produtos.txt");
       Crono.stop();
       System.out.println("Linhas lidas: " + produtos.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("--------------------------------------------------------");
       Crono.start();
       hipermercado.carregarCatalogoProdutos(produtos);
       hipermercado.carregarCatalogoClientes(clientes);
       Crono.stop();
       System.out.println("Clientes: " + hipermercado.getCatClientes().sizeCatalogoClientes());
       System.out.println("Produtos: " + hipermercado.getCatProdutos().sizeCatalogoProdutos());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("--------------------------------------------------------");
   } 
}
