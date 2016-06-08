import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

public class Facturacao{
    private List<Map<String,InfoProduto>> produtos;
    
    /*Construtor*/
    public Facturacao(){
        this.produtos = new ArrayList<>(26);
        Map<String,InfoProduto> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeMap<String,InfoProduto>();
            this.produtos.add(i,arvore);
        }
    }
    
    public void insereVenda(Venda v){ 
       String p = v.getProduto();
       int indice = calculaIndice(p.charAt(0));
       if(produtos.get(indice).containsKey(p))
            produtos.get(indice).get(p).insereInfoP(v);
       else{ 
           InfoProduto ip = new InfoProduto();
           ip.insereInfoP(v);
           produtos.get(indice).put(p,ip);
       }
    }
    
    public boolean existeProd(String prod){
        int indice = calculaIndice(prod.charAt(0));
        return this.produtos.get(indice).containsKey(prod);
    }
    //Limpar
    public void limpar (){
       this.produtos.clear();
    }
    
    /*Get's*/
    public List<Map<String,InfoProduto>> getFactProdutos(){
      ArrayList<Map<String,InfoProduto>> lista = new ArrayList<>(26);
      int i;
      
      for(i=0; i<26; i++){
         Map<String,InfoProduto> arvore = new TreeMap<String,InfoProduto>();
         this.produtos.get(i).forEach( (k,v) -> {arvore.put(k,v.clone());});
         //for(String p: this.produtos.get(i).keySet()){
           // arvore.put(p,this.produtos.get(i).get(p).clone());
         //}
         lista.add(i,arvore);         
      }
        
      return lista;
    }
    
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    public int nProdDif(){
        int total=0;
        for( Map<String,InfoProduto> lista :produtos){
            total += lista.size();
        }
        return total;
    }
    
    public double total(){
        double total=0;
        int i,o=0;
      
            for(Map<String,InfoProduto> lista : produtos){
                for(InfoProduto lista2 : lista.values()){
                    total += lista2.total();
                }
            }
        
        return total;
    }
    
    public int zeros(){
      int total=0;
      int i,o=0;
      
      for(Map<String,InfoProduto> lista : produtos){
        for(InfoProduto lista2 : lista.values()){
            total += lista2.zero();
           }
      }
        
      return total;
    }
}
