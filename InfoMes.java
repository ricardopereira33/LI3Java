import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.io.Serializable;

public class InfoMes implements Serializable
{
    private Map<String,InfoClienteProduto> produtos;
    private int quantidade;
    private int numVendas;
    private double totGasto;
    
    /**
     * Construtor vazio.
     */
    public InfoMes (){
        int i;
        this.produtos = new HashMap<>();
        this.produtos=null;
        this.quantidade=0;
        this.numVendas = 0;
        this.totGasto = 0;
    }
    
    /**
     * Construtor por cópia.
     * @param im
     */
    public InfoMes (InfoMes im){
        int indice,i;
        this.produtos = new HashMap<>();

        if(im.getProdutos() != null){
                for(String produto: im.getProdutos().keySet()){
                    this.produtos.put(produto,im.getProdutos().get(produto).clone());
                }
        }
        this.quantidade = im.getQuantidade();
        this.numVendas = im.getNumVendas();
        this.totGasto = im.getTotGasto();
    }
    
    /** Retorna a quantidade total.
      * @return
      */
    public int getQuantidade(){
        return this.quantidade;
    }
    
    /** Retorna o numero de vendas.
      * @return
      */
    public int getNumVendas(){
        return this.numVendas;
    }
    
    /** Retorna o total gasto.
      * @return
      */
    public double getTotGasto(){
        return this.totGasto;
    }
    
    /** Insere uma determinada venda.
      * @param v
      */
    public void insereProdutoQuant(Venda v){
        this.quantidade+= v.getQuantidade();
        this.numVendas++;
        this.totGasto += (v.getPreco())*(v.getQuantidade());
        
        String p = v.getProduto();
        int indice = calculaIndice(p.charAt(0));
        
        if(produtos!=null){
            insereProdutoQuantAux(v,p);
        }
        else {
            this.produtos= new HashMap<>();
            insereProdutoQuantAux(v,p);
        }
    }
    
    /**
     * Função para inserir um InfoClienteProduto.
     * @param v
     * @param p
     */
    public void insereProdutoQuantAux(Venda v,String p){

        if(produtos.containsKey(p))
                produtos.get(p).insereInfoCP(v);
            else{ 
                    InfoClienteProduto icp = new InfoClienteProduto();
                    icp.insereInfoCP(v);
                    produtos.put(p,icp);
                }
    }
    
    /** Retorna a informação relativa a um determinado produto.
      * @param prod
      * @return
      */
    public InfoClienteProduto getProdutoInfo(String prod){
        int indice = calculaIndice(prod.charAt(0));
        if(produtos==null) return null;
        if(!(produtos.containsKey(prod))) return null;
        else return produtos.get(prod).clone();
    }
    
    /** Retorna a estrutura relativa aos produtos.
      * @return
      */
    public Map<String,InfoClienteProduto> getProdutos(){
        return this.produtos;
    }
    
    /** Calcula o indice relativo a uma determinada letra.
     * @param letra
     * @return
     */
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    /**
     * Função que faz clone.
     * @return
     */
    public InfoMes clone(){
        return new InfoMes(this);
    }
}
