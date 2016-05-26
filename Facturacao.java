import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

public class Facturacao implements Serializable{
    private List<Map<Produto,InfoProduto>> produtos;
    
    /*Construtor*/
    public Facturacao(){
        this.produtos = new ArrayList<>(26);
        Map<Produto,InfoProduto> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeMap<Produto,InfoProduto>(new ComparatorProduto());
            this.produtos.add(i,arvore);
        }
    }
    
    public void insereVenda(Venda v){ 
       Produto p = v.getProduto();
       int indice = calculaIndice(p.getNomeProduto().charAt(0));
       if(produtos.get(indice).containsKey(p))
            produtos.get(indice).get(p).insereInfoP(v);
       else{ 
           InfoProduto ip = new InfoProduto();
           ip.insereInfoP(v);
           produtos.get(indice).put(p,ip);
       }
    }
    
    //Limpar
    public void limpar (){
       this.produtos.clear();
    }
    
    /*Get's*/
    public List<Map<Produto,InfoProduto>> getFactProdutos(){
      ArrayList<Map<Produto,InfoProduto>> lista = new ArrayList<>(26);
      Map<Produto,InfoProduto> arvore;
      int i;  
      
      for(i=0; i<26; i++){
         arvore = new TreeMap<Produto,InfoProduto>();
         /*this.produtos.get(i).forEach( (k,v) -> arvore.put(k.clone(),((InfoProduto) v)));*/
         for(Produto p: this.produtos.get(i).keySet()){
            arvore.put(p.clone(),this.produtos.get(i).get(p).clone());
         }
         lista.add(i,arvore);         
      }
        
      return this.produtos;
    }
    
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    public int nProdDif(){
        int total=0;
        for( Map<Produto,InfoProduto> lista :produtos){
            total += lista.size();
        }
        return total;
    }
    
    public double total(){
        double total=0;
        int i,o=0;
      
            for(Map<Produto,InfoProduto> lista : produtos){
                for(InfoProduto lista2 : lista.values()){
                    total += lista2.total();
                }
            }
        
        return total;
    }
    
    public int zeros(){
      int total=0;
      int i,o=0;
      
      for(Map<Produto,InfoProduto> lista : produtos){
        for(InfoProduto lista2 : lista.values()){
            total += lista2.zero();
           }
      }
        
      return total;
    }
}
