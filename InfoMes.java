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
    
    public InfoMes (){
        int i;
        this.produtos = new HashMap<>();
        this.produtos=null;
        this.quantidade=0;
        this.numVendas = 0;
        this.totGasto = 0;
    }
    
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
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public int getNumVendas(){
        return this.numVendas;
    }
    
    public double getTotGasto(){
        return this.totGasto;
    }
    
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
    
    public void insereProdutoQuantAux(Venda v,String p){

        if(produtos.containsKey(p))
                produtos.get(p).insereInfoCP(v);
            else{ 
                    InfoClienteProduto icp = new InfoClienteProduto();
                    icp.insereInfoCP(v);
                    produtos.put(p,icp);
                }
    }
    
    public InfoClienteProduto getProdutoInfo(String prod){
        int indice = calculaIndice(prod.charAt(0));
        if(produtos==null) return null;
        if(!(produtos.containsKey(prod))) return null;
        else return produtos.get(prod).clone();
    }
    
    public Map<String,InfoClienteProduto> getProdutos(){
        return this.produtos;
    }
    
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    public InfoMes clone(){
        return new InfoMes(this);
    }
}
