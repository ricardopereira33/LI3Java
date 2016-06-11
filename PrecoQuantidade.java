import java.io.Serializable;

public class PrecoQuantidade implements Serializable
{
   private double totalprice;             
   private int totalVendas;
   private int totalQuant;
   
   /**
    * Construtor vazio.
    */
   public PrecoQuantidade (){
       this.totalprice = 0;
       this.totalVendas = 0;
       this.totalQuant = 0;
   }
   
   /**
    * Construtor por cópia.
    * @param pq
    */
   public PrecoQuantidade (PrecoQuantidade pq){
       this.totalprice = pq.getTotalPrice();
       this.totalVendas = pq.getTotalVendas();
       this.totalQuant = pq.getTotalQuant();
    }
   
   /** 
     * Insere uma Venda.
     * @return
     */
   public void inserePrecoQuant(Venda v){
       this.totalprice += v.getPreco()*v.getQuantidade();
       this.totalVendas += v.getQuantidade();
       this.totalQuant ++;
    } 
    
   /** 
    * Retorna o total facturado.
    * @return
    */
   public double getTotalPrice(){
       return this.totalprice;
    }
   
    /** 
     * Retorna a quantidade total comprada.
     * @return
     */
   public int getTotalVendas(){
       return this.totalVendas;
    } 
   
    /** 
     * Retorna o número de vendas.
     * @return
     */
   public int getTotalQuant(){
       return this.totalQuant;
    }
    
   /**
    * Função que faz o clone.
    * @return
    */
   public PrecoQuantidade clone(){
       return new PrecoQuantidade(this);
    } 
    
   /**
     * Função que testa a igualdade.
     */
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj == null || this.getClass()!=obj.getClass()) return false;
        PrecoQuantidade i = (PrecoQuantidade) obj;
        return this.totalprice == i.getTotalPrice() && this.totalVendas == i.getTotalVendas() &&
               this.totalQuant == i.getTotalQuant();
    }  
}
