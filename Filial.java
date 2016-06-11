import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.HashSet;

public class Filial implements Serializable{
    private List<Map<String,InfoCliente>> clientes;
    
    /**
     * Construtor vazio.
     */
    public Filial(){
        this.clientes = new ArrayList<>(26);
        Map<String,InfoCliente> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new HashMap<String,InfoCliente>();
            this.clientes.add(i,arvore);
        }
    }
    
    /**
     * Construtor por cópia.
     * @param f
     */
    public Filial(Filial f){
        this.clientes = f.getFilialClientes();
    }
    
    /** 
     * Calcula o número de vendas num determinado mês, e guarda os clientes que as fizeram.
     * @param clientes
     * @param mes
     * @return
      */
    public int getNumVendMes(Set<String> clientes,int mes){
        int i;
        int numVend=0;
        for(i=0; i<26; i++){
            for(Map.Entry<String,InfoCliente> entry : this.clientes.get(i).entrySet()){
                InfoMes m = entry.getValue().getMesIndex(mes);
                if(m!=null){numVend += m.getNumVendas();clientes.add(entry.getKey());}; 
            }
        }
        return numVend;
    }
    
    /** 
     * Calcula o número de compras e o total gasto por um determinado cliente, num determinado mês
     * e guarda os respectivos produtos comprados.
     * @param prods
     * @param mes
     * @param clie
     */
    public ParIntDouble getNumCompTotMes(Set<String> prods,int mes,String cli){
        int indice = calculaIndice(cli.charAt(0));
        int numCompras = 0;
        double totGasto = 0;
        if(!(this.clientes.get(indice).containsKey(cli))) return new ParIntDouble(numCompras,totGasto);
        InfoCliente i = this.clientes.get(indice).get(cli);
        InfoMes m = i.getMesIndex(mes);
        if(m!=null){numCompras += m.getNumVendas();totGasto += m.getTotGasto();prods.addAll(m.getProdutos().keySet());}
        return new ParIntDouble(numCompras,totGasto);
    }
    
    //QUERIE4
    /** 
     * Guarda numa estrutura, para cada mês, os clientes que compraram determinado produto.
     * @param cli
     * @param prod
     * @param mes
     */
    public void getNumCompTotMesProd(List<Set<String>> cli, String prod,int mes){
        int i;
        int numVend = 0;
        int totFact = 0;
        for(i=0; i<26; i++){
            Map<String,InfoCliente> clientes = this.clientes.get(i);
            for(String cliente : clientes.keySet()){
                  
                    InfoMes m = clientes.get(cliente).getMesIndex(mes);
                    if(m!=null){InfoClienteProduto h = m.getProdutoInfo(prod);
                            if(h!=null){
                                if(cli.get(mes)!=null)
                                        cli.get(mes).add(cliente);
                                else {
                                        Set<String> list = new HashSet<>();
                                        list.add(cliente);
                                        cli.add(mes,list);
                                }
                            }
                        }
                    
            }
        }
        //return new ParIntDouble(numVend,totFact);
    }
    
    /** 
     * Guarda numa estrutura os produtos e as repectivas quantidades que um determinado cliente comprou.
     * @param cli
     * @param prods
     */
    public void getProdsMaisComp(String cli,Map<String,Object> prods){
        int i,j;
        int indice = calculaIndice(cli.charAt(0));
        if(clientes.get(indice).containsKey(cli)){
            for(i=0;i<12;i++){
                InfoMes m = this.clientes.get(indice).get(cli).getMesIndex(i);
                if(m!=null){
                            m.getProdutos().forEach((k,v)-> {if(prods.containsKey(k)){
                                                                Object actual = prods.get(k);
                                                                int actual2 = (int)actual + (v.getQuantity(0)+v.getQuantity(1));
                                                                prods.remove(k);
                                                                prods.put(k,actual2);
                                                             }
                                                             else{prods.put(k,(v.getQuantity(0)+v.getQuantity(1)));}});
                }
            }
        }
    }
    
    /** 
     * Guarda numa estrutura os produtos e as respectivas quantidades compradas, assim como os clientes que os compraram.
     * @param prodscli
     */
    public void getProdsMaisVend(Map<String,ParIntSet> prodscli){
        int i,j;
        for(i=0; i<26; i++){
            for(Map.Entry<String,InfoCliente> clientes : this.clientes.get(i).entrySet()){
                for(j=0;j<12;j++){
                    InfoMes m = clientes.getValue().getMesIndex(j);
                    if(m!=null){
                                for(Map.Entry<String,InfoClienteProduto> entry : m.getProdutos().entrySet()){
                                    String prod = entry.getKey();
                                    InfoClienteProduto info = entry.getValue(); 
                                    if(prodscli.containsKey(prod)){
                                        prodscli.get(prod).addPrimeiro(info.getQuantity(0)+info.getQuantity(1));
                                        prodscli.get(prod).addSegundo(clientes.getKey());
                                    }
                                    else{
                                        ParIntSet par = new ParIntSet(info.getQuantity(0)+info.getQuantity(1));
                                        par.addSegundo(clientes.getKey());
                                        prodscli.put(prod,par);
                                    }
                                }
                    }
                }
            }
        }
    }
    
    /** 
     * Guarda num estrutura cada cliente com a respectiva facturação total.
     * @return
     */
    public List<ParStringDouble> getCompTot(){
        List<ParStringDouble> list = new ArrayList<>();
        int i;
        for(i=0; i<26; i++){
            this.clientes.get(i).forEach((k,v)->{list.add(new ParStringDouble(k,v.getTotFact()));});
        }
        return list;
    }
    
    /** 
     * Guarda numa estrutura cada cliente com os respectivos produtos comprados.
     * @param cliprods
     */
    public void getCliMaisCompDif(Map<String,Set<String>> cliprods){
        int i,j;
         for(i=0; i<26; i++){
            for(String cli:this.clientes.get(i).keySet()){
                for(j=0;j<12;j++){
                    InfoMes m = this.clientes.get(i).get(cli).getMesIndex(j);
                    if(m!=null){
                                Set<String> prods = m.getProdutos().keySet();
                                if(cliprods.containsKey(cli)){cliprods.get(cli).addAll(prods);}
                                else {
                                      Set<String> prd = new TreeSet<String>();
                                      prd.addAll(prods);
                                      cliprods.put(cli,prd);
                                    }
                    }
                }
            }
        }
    }
    
    /** 
     * Guarda numa estrutura cada cliente com a respectiva quantidade e facturação de um determinado produto.
     * @param cliquant
     * @param prod
     */
    public void getCliMaisCompProd(Map<String,ParIntDouble> cliquant, String prod){
        int i,j;
         for(i=0; i<26; i++){
            for(String cli:this.clientes.get(i).keySet()){
                for(j=0;j<12;j++){
                    InfoMes m = this.clientes.get(i).get(cli).getMesIndex(j);
                    if(m!=null){
                                Set<String> prods = m.getProdutos().keySet();
                                if(prods.contains(prod)){
                                    InfoClienteProduto info = m.getProdutos().get(prod);
                                    if(cliquant.containsKey(cli)){
                                        cliquant.get(cli).addPrimeiro(info.getQuantity(0)+info.getQuantity(1));
                                        cliquant.get(cli).addSegundo(info.getTotGasto(0)+info.getTotGasto(1));
                                    }
                                    else{
                                        ParIntDouble par = new ParIntDouble(info.getQuantity(0)+info.getQuantity(1),info.getTotGasto(0)+info.getTotGasto(1));
                                        cliquant.put(cli,par);
                                    }
                                }
                    }
                }
            }
        }
    }
    
    /** 
     * Insere uma determinada venda.
     * @param v
     */
    public void insereVenda(Venda v){ 
       String c = v.getCliente();
       int indice = calculaIndice(c.charAt(0));
       if(clientes.get(indice).containsKey(c))
           clientes.get(indice).get(c).insereInfoC(v);
       else{ 
           InfoCliente ic = new InfoCliente();
           ic.insereInfoC(v);
           clientes.get(indice).put(c,ic);
       }
    }
    
    /** 
     * Apaga a estrutura da Filial.
     */
    public void limpar (){
       this.clientes.clear();
    }
    
    /** 
     * Retorna a estrutura da Filial.
     * @return
     */
    public List<Map<String,InfoCliente>> getFilialClientes(){
      ArrayList<Map<String,InfoCliente>> lista = new ArrayList<>(26);
      int i;  
      
      for(i=0; i<26; i++){
         Map<String,InfoCliente> arvore = new HashMap<String,InfoCliente>();
         this.clientes.get(i).forEach( (k,v) -> arvore.put(k,v.clone()));
         //for(String c: this.clientes.get(i).keySet()){
           // arvore.put(c,this.clientes.get(i).get(c));
         //}
         lista.add(i,arvore);         
      }
        
      return lista;
    }
    
    /** Guarda numa estrutura todos os clientes que fizeram compras na Filial.
     * @return void
     */
    public void total(Set<String> lista){
        for(int i=0; i<26; i++){
            for( String cliente : this.clientes.get(i).keySet()){
                lista.add(cliente);
            }
        }
    }
    
    /** Guarda numa estrutura a factoração de cada mês.
     * @return void
     */
    public void totalMes(double lista[]){
        int j;
        for(int i=0; i<26; i++){
            Map<String,InfoCliente> clientes = this.clientes.get(i);
            for(String cli : clientes.keySet()){
                for(j=0;j<12;j++){
                    InfoCliente ic = clientes.get(cli);
                    if(ic!=null){
                        InfoMes m = ic.getMesIndex(j);
                        if(m!=null){
                            lista[j]+=m.getTotGasto();
                        }
                    }
                }
                }
        }
    }
    
    /** Guarda numa estrutura os clientes que compraram num determinado mês.
     * @return void
     */
    public void getCliMes(Set<String> clie, int mes){
        int j;
        for(int i=0; i<26; i++){
            Map<String,InfoCliente> clientes = this.clientes.get(i);
            for(String cli : this.clientes.get(i).keySet()){
                 InfoCliente ic = clientes.get(cli);
                 if(ic!=null){
                     InfoMes m = ic.getMesIndex(mes);
                     if(m!=null){
                         clie.add(cli);
                     }
                 }
            }
        }
    }
    
    /** 
     * Calcula o índice correspondente a uma determinada letra.
     * @param letra
     * @return
     */
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    /**
     * Função para fazer clone.
     */
    public Filial clone(){
        return new Filial(this);
    }
    
    /**
     * Função que testa a igualdade.
     */
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj == null || this.getClass()!=obj.getClass()) return false;
        Filial f = (Filial) obj;
        return this.clientes.equals(f.getFilialClientes());
    }
}
