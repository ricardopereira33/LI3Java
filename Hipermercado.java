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
    
   private static final int num_filiais = 3;
   private CatClientes catalogoClientes;
   private CatProdutos catalogoProdutos;
   private Facturacao facturacao;
   private Filial filiais[];
   
   /**
    * Construtor vazio.
    */
   public Hipermercado(){
       int i;
       this.catalogoClientes = new CatClientes();
       this.catalogoProdutos = new CatProdutos();
       this.facturacao = new Facturacao();
       this.filiais = new Filial [num_filiais];
       for(i=0;i<num_filiais;i++)
            filiais[i]= new Filial();
   }
   
   /**
    * Construtor por cópia.
    * @param h
    */
   public Hipermercado(Hipermercado h){
       this.catalogoClientes = h.getCatClientes();
       this.catalogoProdutos = h.getCatProdutos();
       this.facturacao = h.getFacturacao();
   }
   
   /**
    * Função que retorna o Catálogo de Clientes.
    * @return
    */
   public CatClientes getCatClientes(){
       return this.catalogoClientes.clone();
   }
   
   /**
    * Função que retorna o Catálogo de Produtos.
    * @return
    */
   public CatProdutos getCatProdutos(){
       return this.catalogoProdutos.clone();
   }
   
   /**
    * Função que retorna a facturação.
    * @return
    */
   public Facturacao getFacturacao(){
       return this.facturacao.clone(); 
   }
   
   /**
    * Função que retorna as filiais.
    */
   public List<Filial> getFilial(){
       List<Filial> n_filiais = new ArrayList<Filial>(num_filiais);
       for(int i=0;i<num_filiais;i++) n_filiais.add(filiais[i].clone());
       return n_filiais;
   }
   
   /**
    * Função que testa se o Hipermercado se encontra vazio.
    * @return
    */
   public boolean isEmpty(){
       boolean result = true;
       if(catalogoClientes.totalClientes()>0 && catalogoProdutos.totalProdutos()>0 && facturacao.nProdDif() >0)
            result = false;
       return result;
   }
   
   /**
    * Função que faz a leitura dos ficheiros, carregando a informação para o Hipermercado.
    * @param ficheiro_clientes
    * @param ficheiro_produtos
    * @param ficheiro_vendas
    */
   public ParIntInt carregaDados(String ficheiro_clientes, String ficheiro_produtos, String ficheiro_vendas){
       Leitura.leituraClientes(ficheiro_clientes,catalogoClientes);
       Leitura.leituraProdutos(ficheiro_produtos,catalogoProdutos);
       ParIntInt n = Leitura.leituraVendas(ficheiro_vendas,catalogoProdutos,catalogoClientes,facturacao,filiais);
       return n;
   }
   
   /**
    * Função para limpar o Hipermercado.
    */
   public void limpar (){
       catalogoClientes.limpar();
       catalogoProdutos.limpar();
       facturacao.limpar();
       for(Filial f:filiais){
           f.limpar();
        }
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
   /**
    * Função que retorna uma lista ordenada alfabeticamente com os códigos dos produtos
    * nunca comprados e o seu respectivo total.
    * @return
    */
   public Set<String> getProdsNaoComp(){
       Set<String> prods = new TreeSet<String>();
       for(String prod : this.catalogoProdutos.getProdutos()){
           if(!this.facturacao.existeProd(prod)) prods.add(prod);
       }
       return prods;
   }
   
   //QUERIE2
   /**
    * Função que dado um mês válido, determina o número total de vendas realizadas
    * e o número total de clientes distintos que as fizeram.
    * @param mes
    * @return
    */
   public ParIntInt getNumVendNumCliMes(int mes) throws MesInvalidoException{
       int numVendas=0;
       int numClientes=0;
       if(mes < 0 || mes > 12 ) throw new MesInvalidoException("Més inválido! Por favor introduzia um mês válido (1-12).");
       
       Set<String> clientes = new TreeSet<String>();
       
       for(int i=0;i<num_filiais;i++){
           numVendas += filiais[i].getNumVendMes(clientes,mes);
       }
       numClientes = clientes.size();
       return new ParIntInt(numVendas,numClientes);
   }
   
   //QUERIE3
   /**
    * Função que dado um código de cliente determina, para cada mês,
    * quantas compras este fez, quantos produtos distintos comprou e quando gastou no total.
    * @param cli
    * @return
    */
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
           Set<String> prods = new TreeSet<String>();
           for(int j=0;j<num_filiais;j++){
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
   /**
    * Função que dado o código de um produto existente determina, mês a mês
    * quantas vezes foi comprado, por quantos clientes diferentes e o total facturado.
    * @param prod
    * @return
    */   
   public List<TriploIntIntDouble> getNumCompNumCliTot(String prod) throws ProdutoInexistenteException{
       
       if(!catalogoProdutos.existeProduto(prod)) throw new ProdutoInexistenteException("O produto " + prod + " não se encontra no Catálogo!");
       
       List<TriploIntIntDouble> lista = new ArrayList<>(12);
      
       List<ParIntDouble> listaF = new ArrayList<>(12);
       facturacao.getNumVendasFactTotal(listaF,prod);
       
       List<Set<String>> listaC = new ArrayList<>(12);
        for(int i=0;i<12;i++)
           listaC.add(i,null); 

            for(int j=0;j<12;j++){     
             for(int i=0;i<num_filiais;i++)
                filiais[i].getNumCompTotMesProd(listaC,prod,j);
            } 
        
       
       for(int i=0;i<12;i++){
           Set<String> aux =listaC.get(i);
           if(aux!=null)
            lista.add(new TriploIntIntDouble(listaF.get(i).getPrimeiro(),aux.size(),listaF.get(i).getSegundo()));
           else lista.add(new TriploIntIntDouble(listaF.get(i).getPrimeiro(),0,listaF.get(i).getSegundo()));
        }
       return lista;
   }
   
   //QUERIE5
   /**
    * Função que dado o código de um cliente determina a lista de códigos de produtos
    * que mais comprou, ordenada por ordem decrescente de quantidade e, para quantidades iguais
    * por ordem alfabética.
    * @param cli
    * @return
    */
   public TreeSet<ParStringInt> getProdsMaisComprados(String cli) throws ClienteInexistenteException{
       
       if(!catalogoClientes.existeCliente(cli)) throw new ClienteInexistenteException("O cliente " + cli + " não se encontra no Catálogo!");
       
       Map<String,Object> prods = new HashMap<String,Object>();
       for(int j=0;j<num_filiais;j++){
               filiais[j].getProdsMaisComp(cli,prods);
       }
       TreeSet<ParStringInt> inf = new TreeSet<ParStringInt>(new ComparatorParStringInt());
       prods.forEach((k,v)->{inf.add(new ParStringInt(k,(int)v));});
       return inf;
   }
   
   //QUERIE6
   /**
    * Determina o conjunto dos N produtos mais vendido em todo o ano, indicando o número total
    * de clientes distintos que o compraram.
    * @param x
    * @return
    */
   public TreeSet<TriploStringIntInt> getProdsMaisVend(int x){
       int j;
       TreeSet<TriploStringIntInt> prods = new TreeSet<TriploStringIntInt>(new ComparatorTriploStringIntInt());
       Map<String,ParIntSet> prodscli = new HashMap<String,ParIntSet>();
       for(j=0;j<num_filiais;j++){
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
   /**
    * Determina para cada filial, a lista dos três maiores compradores em termos de dinheiro facturado.
    * @return
    */
   public List<List<ParStringDouble>> getMaioresComp(){
       List<List<ParStringDouble>> list = new ArrayList<>(num_filiais);
       for(int j=0;j<num_filiais;j++){
               List<ParStringDouble> fil = filiais[j].getCompTot();
               Collections.sort(fil,new ComparatorParStringDouble());
               list.add(j,fil.subList(0,num_filiais));
       }
       return list;
   }
   
   //QUERIE8
   /**
    * Determina os códigos dos N clientes que compraram mais produtos diferentes.
    * @param x
    * @return
    */
   public TreeSet<ParStringInt> getCliMaisCompDif(int x){
       int j;
       TreeSet<ParStringInt> cli = new TreeSet<ParStringInt>(new ComparatorParStringInt());
       Map<String,Set<String>> cliprods = new HashMap<String,Set<String>>();
       for(j=0;j<num_filiais;j++){
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
   /**
    * Dado o código de um produto, determinar o conjunto dos N clientes que mais o compraram, e para cada um
    * indicar o valor gasto.
    */
   public TreeSet<ParStringDouble> getCliMaisCompProd(String prod, int x) throws ProdutoInexistenteException{
       
       if(!catalogoProdutos.existeProduto(prod)) throw new ProdutoInexistenteException("O produto " + prod + " não se encontra no Catálogo!");
       
       int j;
       TreeSet<ParStringDouble> cli = new TreeSet<ParStringDouble>(new ComparatorParStringDouble());
       Map<String,ParIntDouble> cliquant = new HashMap<String,ParIntDouble>();
       for(j=0;j<num_filiais;j++){
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
   
   /**
    * Função que faz um clone.
    * @return
    */
   public Hipermercado clone(){
       return new Hipermercado(this);
   }
   
   /*CONSULTAS ESTATISTICAS*/
   /** Calcula o número total de Produtos diferentes comprados.
     * @return
     */
   public int numeroProdutosDif(){
       return facturacao.nProdDif();       
    }
   
   /** Calcula a facturação total.
     * @return
     */
   public double facturacaoTotal(){
       return facturacao.total();
    } 
   
   /** Calcula o número de vendas com valor igual a 0.
     * @return
     */
   public int zeros(){
       return facturacao.zeros();
   }
   
   /** Calcula o número total de Produtos diferentes.
     * @return int
     */
   public int getTotProdsDif(){
       return catalogoProdutos.totalProdutos();
   }
   
   /** Calcula o número total de Produtos diferentes comprados.
     * @return int
     */
   public int getTotProdsComp(){
       return facturacao.totalProdutos();
   }
   
   /** Calcula o número total de Produtos não comprados.
     * @return int
     */
   public int getTotProdsNaoComp(){
       return getProdsNaoComp().size();
   }
   
   /** Calcula o número total de Clientes diferentes.
     * @return int
     */
   public int getTotCliDif(){
       return catalogoClientes.totalClientes();
   }
   
   /** Calcula o número total de Clientes que realizaram compras.
     * @return int
     */
   public int getTotCliComp(){
       Set<String> clientes = new HashSet<>();
       for(int i =0;i<3;i++){
           this.filiais[i].total(clientes);
       }
       return clientes.size();
   }
   
    /** Calcula o total de compras igual a 0.
     * @return int
     */
   public int getCompZero(){
        return facturacao.zeros();
   }
   
   /** Calcula a facturação total.
     * @return double
     */
   public double getFactTot(){
       return facturacao.total();
    }
    
   /** Calcula o total de vendas num determinado mês.
     * @return int
     */
   public int getTotVendasMes(int mes){
       return this.facturacao.totalVendas(mes);
   }
   
   /** Guarda numa estrutura a factoração de cada mês numa determinada filial.
     * @return void
     */
   public void getFactTotalMes(double lista[],int filial){
       filiais[filial].totalMes(lista);
   }
   
   /** Guarda numa estrutura os clientes que compraram num determinado mês, numa determinada filial.
     * @return void
     */
   public void getCliMes(Set<String> cli,int mes,int filial){
       filiais[filial].getCliMes(cli,mes);
   }
}
