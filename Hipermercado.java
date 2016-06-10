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
import java.util.Iterator;

public class Hipermercado implements Serializable{
    
   private CatClientes catalogoClientes;
   private CatProdutos catalogoProdutos;
   private Facturacao facturacao;
   private Filial filiais[];
   
   public Hipermercado(){
       int i;
       this.catalogoClientes = new CatClientes();
       this.catalogoProdutos = new CatProdutos();
       this.facturacao = new Facturacao();
       this.filiais = new Filial [3];
       for(i=0;i<3;i++)
            filiais[i]= new Filial();
   }
   
   public CatClientes getCatClientes(){
       return this.catalogoClientes.clone();
   }
   
   public CatProdutos getCatProdutos(){
       return this.catalogoProdutos.clone();
   }
   
   public Facturacao getFacturacao(){
       return this.facturacao; // falta clone
   }
   
   public boolean isEmpty(){
       boolean result = true;
       if(catalogoClientes.totalClientes()>0 && catalogoProdutos.totalProdutos()>0 && facturacao.nProdDif() >0)
            result = false;
       return result;
   }
   
   public void carregaDados(String ficheiro_clientes, String ficheiro_produtos, String ficheiro_vendas){
       Leitura.leituraClientes(ficheiro_clientes,catalogoClientes);
       Leitura.leituraProdutos(ficheiro_produtos,catalogoProdutos);
       /*this.carregarcatalogoProdutos(produtos);
       this.carregarcatalogoClientes(clientes);
       clientes.clear();
       produtos.clear();*/
       Leitura.leituraVendas(ficheiro_vendas,catalogoProdutos,catalogoClientes,facturacao,filiais);
       /*this.carregarFacturacao(vendas);
       vendas.clear();*/
   }
   
   public void carregarcatalogoProdutos(ArrayList<String> produtos){
       for(String p: produtos){
           catalogoProdutos.insereProduto(p);
       }
   }
   
    public void carregarcatalogoClientes(ArrayList<String> clientes){
       for(String c: clientes){
           catalogoClientes.insereCliente(c);
       }
   }
   
   public void carregarFacturacao(ArrayList<Venda> vendas){
       for(Venda v : vendas){
           facturacao.insereVenda(v);
        }
   }
   
   public void limpar (){
       catalogoClientes.limpar();
       catalogoProdutos.limpar();
       facturacao.limpar();
       for(Filial f:filiais){
           f.limpar();
        }
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
       for(String prod : this.catalogoProdutos.getProdutos()){
           if(!this.facturacao.existeProd(prod)) prods.add(prod);
       }
       return prods;
   }
   
   //QUERIE2
   /*Dado um mês válido, determinar o número total global de vendas realizadas e o
   número total de clientes distintos que as fizeram;*/
   
   public ParIntInt getNumVendNumCliMes(int mes) throws MesInvalidoException{
       int numVendas=0;
       int numClientes=0;
       if(mes < 0 || mes > 12 ) throw new MesInvalidoException("Més inválido! Por favor introduzia um mês válido (1-12).");
       
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
   public List<TriploIntIntDouble> getNumCompNumProdTot(String cli) throws ClienteInexistenteException {
       
       if(!catalogoClientes.existeCliente(cli)) throw new ClienteInexistenteException("O cliente " + cli + " não se encontra no Catálogo!");
       
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
   public List<TriploIntIntDouble> getNumCompNumCliTot(String prod) throws ProdutoInexistenteException{
       
       if(!catalogoProdutos.existeProduto(prod)) throw new ProdutoInexistenteException("O produto " + prod + " não se encontra no Catálogo!");
       
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
   public TreeSet<ParStringInt> getProdsMaisComprados(String cli) throws ClienteInexistenteException{
       
       if(!catalogoClientes.existeCliente(cli)) throw new ClienteInexistenteException("O cliente " + cli + " não se encontra no Catálogo!");
       
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
   public TreeSet<TriploStringIntInt> getProdsMaisVend(int x){
       int j;
       TreeSet<TriploStringIntInt> prods = new TreeSet<TriploStringIntInt>(new ComparatorTriploStringIntInt());
       Map<String,ParIntSet> prodscli = new HashMap<String,ParIntSet>();
       for(j=0;j<3;j++){
               filiais[j].getProdsMaisVend(prodscli);
       }
       prodscli.forEach((k,v)->{prods.add(new TriploStringIntInt(k,v.getPrimeiro(),v.getTamanhoSegundo()));});
       TreeSet<TriploStringIntInt> prodss = new TreeSet<TriploStringIntInt>(new ComparatorTriploStringIntInt());
       Iterator<TriploStringIntInt> it = prods.iterator();
       for(j=0;j<x && it.hasNext();j++){
           prodss.add(it.next());
       }
       return prodss;
   }
   
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
   
   //QUERIE8
   /*Determinar os códigos dos X clientes (sendo X dado pelo utilizador) que compraram
    * mais produtos diferentes (não interessa a quantidade nem o valor), indicando 
    * quantos, sendo o critério de ordenação a ordem decrescente do número de
    * produtos; */
   public TreeSet<ParStringInt> getCliMaisCompDif(int x){
       int j;
       TreeSet<ParStringInt> cli = new TreeSet<ParStringInt>(new ComparatorParStringInt());
       Map<String,Set<String>> cliprods = new HashMap<String,Set<String>>();
       for(j=0;j<3;j++){
           filiais[j].getCliMaisCompDif(cliprods);
       }
       cliprods.forEach((k,v)->{cli.add(new ParStringInt(k,v.size()));});
       TreeSet<ParStringInt> clii = new TreeSet<ParStringInt>(new ComparatorParStringInt());
       Iterator<ParStringInt> it = cli.iterator();
       for(j=0;j<x && it.hasNext();j++){
           clii.add(it.next());
       }
       return clii;
   }
   
   //QUERIE9
   /*Dado o código de um produto que deve existir, determinar o conjunto dos X clientes
    * que mais o compraram e, para cada um, qual o valor gasto (ordenação cf. 5);*/
   public TreeSet<ParStringDouble> getCliMaisCompProd(String prod, int x) throws ProdutoInexistenteException{
       
       if(!catalogoProdutos.existeProduto(prod)) throw new ProdutoInexistenteException("O produto " + prod + " não se encontra no Catálogo!");
       
       int j;
       TreeSet<ParStringDouble> cli = new TreeSet<ParStringDouble>(new ComparatorParStringDouble());
       Map<String,ParIntDouble> cliquant = new HashMap<String,ParIntDouble>();
       for(j=0;j<3;j++){
           filiais[j].getCliMaisCompProd(cliquant,prod);
       }
       cliquant.forEach((k,v)->{cli.add(new ParStringDouble(k,v.getSegundo()));});
       TreeSet<ParStringDouble> clii = new TreeSet<ParStringDouble>(new ComparatorParStringDouble());
       Iterator<ParStringDouble> it = cli.iterator();
       for(j=0;j<x && it.hasNext();j++){
           clii.add(it.next());
       }
       return clii;
   }
}
