import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

public class InfoMes implements Serializable
{
    private List<Map<String,InfoClienteProduto>> produtos;
    private int quantidade;
    private int numVendas;
    private double totGasto;
    
    public InfoMes (){
        int i;
        this.produtos = new ArrayList<>(26);
        for(i=0;i<26;i++)
            this.produtos.add(i,null);
        this.quantidade=0;
        this.numVendas = 0;
        this.totGasto = 0;
    }
    
    public InfoMes (InfoMes im){
        int indice,i;
        this.produtos = new ArrayList<Map<String,InfoClienteProduto>>();
        for(i=0;i<26;i++){
            this.produtos.add(i,null);
        }
        for(indice=0; indice < 26; indice++){
            if(im.getProdutos().get(indice) != null){
                Map<String,InfoClienteProduto> arvore;
                arvore = new TreeMap<String,InfoClienteProduto>();
                this.produtos.add(indice,arvore);
                for(String produto: im.getProdutos().get(indice).keySet()){
                    this.produtos.get(indice).put(produto,im.getProdutos().get(indice).get(produto).clone());
                }
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
        
        if(produtos.get(indice)!=null){
            insereProdutoQuantAux(v,indice,p);
        }
        else {
            Map<String,InfoClienteProduto> arvore;
            arvore = new TreeMap<String,InfoClienteProduto>();
            this.produtos.add(indice,arvore);
            insereProdutoQuantAux(v,indice,p);
        }
    }
    
    public void insereProdutoQuantAux(Venda v,int indice,String p){

        if(produtos.get(indice).containsKey(p))
                produtos.get(indice).get(p).insereInfoCP(v);
            else{ 
                    InfoClienteProduto icp = new InfoClienteProduto();
                    icp.insereInfoCP(v);
                    produtos.get(indice).put(p,icp);
                }
    }
    
    public InfoClienteProduto getProdutoInfo(String prod){
        int indice = calculaIndice(prod.charAt(0));
        if(produtos.get(indice)==null) return null;
        if(!(produtos.get(indice).containsKey(prod))) return null;
        else return produtos.get(indice).get(prod).clone();
    }
    
    public List<Map<String,InfoClienteProduto>> getProdutos(){
        return this.produtos;
    }
    
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    public InfoMes clone(){
        return new InfoMes(this);
    }
}
