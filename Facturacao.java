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
    
    /**
     * Construtor por cópia
     * @param f
     */
    public Facturacao(Facturacao f){
        this.produtos = f.getFactProdutos();
    }
    
    /** 
     * Insere uma venda na Facturação.
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
    
    /** 
     * Verifica se um Produto existe na Facturação.
     * @param prod
     * @return boolean
     */
    public boolean existeProd(String prod){
        int indice = calculaIndice(prod.charAt(0));
        return this.produtos.get(indice).containsKey(prod);
    }
    
    /** 
     * Apaga a lista dos produtos da Facturação.
     */
    public void limpar (){
       this.produtos.clear();
    }
    
    /** 
     * Retorna um clone da Facturação.
     * @return List<Map<String,InfoProduto>>
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
    
    /** 
     * Calcula o índice da lista correspondente a uma determinada letra. 
     * @param letra
     * @return int
     */
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
    /** 
     * Calcula o número de produtos diferentes comprados. 
     * @return int
     */
    public int nProdDif(){
        int total=0;
        for( Map<String,InfoProduto> lista :produtos){
            total += lista.size();
        }
        return total;
    }
    
    /** 
     * Calcula o total Facturado.
     * @return double
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
    
    /** 
     * Calcula o total de compras de valor igual a 0.
     * @return int
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
    
    /** 
     * Calcula para cada mês, o número de vendas e o total facturado com um dado produto.
     * @param lista
     * @param prod
     * @return List<ParIntDouble>
      */
     public List<ParIntDouble> getNumVendasFactTotal(List<ParIntDouble> lista,String prod){
        int indice = calculaIndice(prod.charAt(0));
        
        InfoProduto ip = this.produtos.get(indice).get(prod);
        
        ip.NumVendasFactTotal(lista);
        
        return lista;
    }
    
    /** 
     * Calcula para cada Produto, o número de unidades compradas.
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
    
    /** Calcula o numero de produtos diferentes. 
      * @return int
      */
    public int totalProdutos(){
      int total=0;
      
      for(Map<String,InfoProduto> lista : produtos){
            total += lista.size();
      }
        
      return total;
    }
    
    /** Calcula o numero de vendas num determinado mes.
      * @param mes 
      * @return int
      */
      public int totalVendas(int mes){
      int total=0;
      
      for(Map<String,InfoProduto> lista : produtos){
          for(String produto : lista.keySet()){
                InfoProduto ip = lista.get(produto);
                total+=ip.numVendasTotal(mes);
            }     
      }
        
      return total;
    }
    
    /**
     * Função que faz clone.
     * @return Facturacao
     */
    public Facturacao clone(){
        return new Facturacao(this);
    }
    
    /**
     * Função que testa a igualdade.
     * @return boolean
     */
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj == null || this.getClass()!=obj.getClass()) return false;
        Facturacao f = (Facturacao) obj;
        return this.produtos.equals(f.getFactProdutos());
    }
}
