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
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Hipermercado implements Serializable{
    
   private CatClientes CatalogoClientes;
   private CatProdutos CatalogoProdutos;
   private Facturacao facturacao;
   private Filial filiais[];
   
   public Hipermercado(){
       int i;
       this.CatalogoClientes = new CatClientes();
       this.CatalogoProdutos = new CatProdutos();
       this.facturacao = new Facturacao();
       this.filiais = new Filial [3];
       for(i=0;i<3;i++)
            filiais[i]= new Filial();
   }
   
   public CatClientes getCatClientes(){
       return this.CatalogoClientes.clone();
   }
   
   public CatProdutos getCatProdutos(){
       return this.CatalogoProdutos.clone();
   }
   
   public void carregaDados(){
       Leitura.leituraClientes(CatalogoClientes);
       Leitura.leituraProdutos(CatalogoProdutos);
       /*this.carregarCatalogoProdutos(produtos);
       this.carregarCatalogoClientes(clientes);
       clientes.clear();
       produtos.clear();*/
       Leitura.leituraVendas(CatalogoProdutos,CatalogoClientes,facturacao,filiais);
       /*this.carregarFacturacao(vendas);
       vendas.clear();*/
   }
   
   public void carregarCatalogoProdutos(ArrayList<String> produtos){
       for(String p: produtos){
           CatalogoProdutos.insereProduto(p);
       }
   }
   
    public void carregarCatalogoClientes(ArrayList<String> clientes){
       for(String c: clientes){
           CatalogoClientes.insereCliente(c);
       }
   }
   
   public void carregarFacturacao(ArrayList<Venda> vendas){
       for(Venda v : vendas){
           facturacao.insereVenda(v);
        }
   }
   
   public void limpar (){
       CatalogoClientes.limpar();
       CatalogoProdutos.limpar();
       facturacao.limpar();
       /*for(Filial f:filiais){
           f.limpar();
        }*/
   }
   
   /*CONSULTAS ESTATISTICAS*/
   public int numeroProdutosDif(){
       return facturacao.nProdDif();       
    }
    
   public double facturacaoTotal(){
       return facturacao.total();
    } 
   
   public int zeros(){
       return facturacao.zeros();
   }
   
   // GRAVAR && CARREGAR
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
   
   //QUERIE1
   /*Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o
   seu respectivo total;*/
   
   public Set<String> getProdsNaoComp(){
       Set<String> prods = new TreeSet<String>(new ComparatorByString());
       for(String prod : this.CatalogoProdutos.getProdutos()){
           if(this.facturacao.existeProd(prod)) prods.add(prod);
       }
       return prods;
   }
   
   //QUERIE2
   /*Dado um mês válido, determinar o número total global de vendas realizadas e o
   número total de clientes distintos que as fizeram;*/
   
   public ParIntInt getNumVendNumCliMes(int mes){
       int numVendas=0;
       int numClientes=0;
       Set<String> clientes = new TreeSet<String>(new ComparatorByString());
       
       for(int i=0;i<3;i++){
           numVendas += filiais[i].getNumVendMes(clientes,mes);
       }
       numClientes = clientes.size();
       return new ParIntInt(numVendas,numClientes);
   }
   
   //QUERIE3
   /*Dado um código de cliente, determinar, para cada mês, quantas compras fez,
    * quantos produtos distintos comprou e quanto gastou no total. ; */
   public List<TriploIntIntDouble> getNumCompNumProdTot(String cli){
       List<TriploIntIntDouble> lista = new ArrayList<>(12);
       int numCompras = 0;
       int numProds = 0;
       double totGasto = 0;
       
       for(int i=0;i<12;i++){
           numCompras = 0;
           numProds = 0;
           totGasto = 0;
           Set<String> prods = new TreeSet<String>(new ComparatorByString());
           for(int j=0;j<3;j++){
               ParIntDouble p = filiais[j].getNumCompTotMes(prods,i,cli);
               numCompras += p.getPrimeiro();
               totGasto += p.getSegundo();
           }
           numProds = prods.size();
           lista.add(i,new TriploIntIntDouble(numCompras,numProds,totGasto));
       }
       return lista;
   }
   
   //QUERIE4
   /*Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
    * comprado, por quantos clientes diferentes e o total facturado;*/
   public List<TriploIntIntDouble> getNumCompNumCliTot(String prod){
       List<TriploIntIntDouble> lista = new ArrayList<>(12);
       int numCompras = 0;
       int numCli = 0;
       double totFact = 0;
       for(int i=0;i<12;i++){
           numCompras = 0;
           numCli = 0;
           totFact = 0;
           Set<String> cli = new TreeSet<String>(new ComparatorByString());
           for(int j=0;j<3;j++){
               ParIntDouble p = filiais[j].getNumCompTotMesProd(cli,i,prod);
               numCompras += p.getPrimeiro();
               totFact += p.getSegundo();
           }
           numCli = cli.size();
           lista.add(i,new TriploIntIntDouble(numCompras,numCli,totFact));
       }
       return lista;
   }
   
   //QUERIE5
   /*Dado o código de um cliente determinar a lista de códigos de produtos que mais
    * comprou (e quantos), ordenada por ordem decrescente de quantidade e, para
    * quantidades iguais, por ordem alfabética dos códigos;*/
   public TreeSet<ParStringInt> getProdsMaisComprados(String cli){
       Map<String,Object> prods = new HashMap<String,Object>();
       for(int j=0;j<3;j++){
               filiais[j].getProdsMaisComp(cli,prods);
       }
       TreeSet<ParStringInt> inf = new TreeSet<ParStringInt>(new ComparatorParStringInt());
       prods.forEach((k,v)->{inf.add(new ParStringInt(k,(int)v));});
       return inf;
   }
   //QUERIE6
   /*Determinar o conjunto dos X produtos mais vendidos em todo o ano (em número de
    * unidades vendidas) indicando o número total de distintos clientes que o
    * compraram (X é um inteiro dado pelo utilizador);*/
   //public TreeSet<ParStringInt> getProdsMaisVend(int x){
       
   //}
   
   //QUERIE7
   /*Determinar, para cada filial, a lista dos três maiores compradores em termos de
    * dinheiro facturado;*/ 
   public List<List<ParStringDouble>> getMaioresComp(){
       List<List<ParStringDouble>> list = new ArrayList<>(3);
       for(int j=0;j<3;j++){
               List<ParStringDouble> fil = filiais[j].getCompTot();
               Collections.sort(fil,new ComparatorParStringDouble());
               list.add(j,fil.subList(0,3));
       }
       return list;
   }
}
