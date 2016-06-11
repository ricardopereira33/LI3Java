import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Facturacao implements Serializable{
    private List<Map<String,InfoProduto>> produtos;

    /**
     * Construtor vazio.
     */
    public Facturacao(){
        this.produtos = new ArrayList<>(26);
        Map<String,InfoProduto> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new HashMap<String,InfoProduto>();
            this.produtos.add(i,arvore);
        }
    }
    
    /** Insere uma venda na Facturação.
      * @param v
      */
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
    
    /** Verifica se um Produto existe na Facturação.
      * @param prod
      * @return 
      */
    public boolean existeProd(String prod){
        int indice = calculaIndice(prod.charAt(0));
        return this.produtos.get(indice).containsKey(prod);
    }
    
    /** Apaga a lista dos produtos da Facturação.
      */
    public void limpar (){
       this.produtos.clear();
    }
    
    /** Retorna um clone da Facturação.
      * @return
      */
    public List<Map<String,InfoProduto>> getFactProdutos(){
      ArrayList<Map<String,InfoProduto>> lista = new ArrayList<>(26);
      int i;
      
      for(i=0; i<26; i++){
         Map<String,InfoProduto> arvore = new HashMap<String,InfoProduto>();
         this.produtos.get(i).forEach( (k,v) -> {arvore.put(k,v.clone());});
         lista.add(i,arvore);         
      }
        
      return lista;
    }
    
    /** Calcula o índice da lista correspondente a uma determinada letra. 
      * @param letra
      * @return
      */
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    /** Calcula o número de produtos diferentes comprados. 
      * @return
      */
    public int nProdDif(){
        int total=0;
        for( Map<String,InfoProduto> lista :produtos){
            total += lista.size();
        }
        return total;
    }
    
    /** Calcula o total Facturado.
      * @return
      */
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
    
    /** Calcula o total de compras de valor igual a 0.
      * @return
      */
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
    
    /** Calcula para cada mês, o número de vendas e o total facturado com um dado produto.
      * @param lista
      * @param prod
      * @return
      */
     public List<ParIntDouble> getNumVendasFactTotal(List<ParIntDouble> lista,String prod){
        int indice = calculaIndice(prod.charAt(0));
        
        InfoProduto ip = this.produtos.get(indice).get(prod);
        
        ip.NumVendasFactTotal(lista);
        
        return lista;
    }
    
    /** Calcula para cada Produto, o número de unidades compradas.
      * @param prods
      */
    public void getProdMaisVendidos(Set<ParStringInt> prods){
        for(Map<String,InfoProduto> produto: this.produtos){
            for(String produtoS : produto.keySet()){
                InfoProduto ip = produto.get(produtoS);
                prods.add(new ParStringInt(produtoS,ip.totalQuant()));
            }
        }
    }
}
