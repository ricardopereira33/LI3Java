import java.io.Serializable;

public class PrecoQuantidade implements Serializable
{
   private double totalprice;             
   private int totalVendas;
   private int totalQuant;
   
   public PrecoQuantidade (){
       this.totalprice = 0;
       this.totalVendas = 0;
       this.totalQuant = 0;
   }
   
   public PrecoQuantidade (PrecoQuantidade pq){
       this.totalprice = pq.getTotalPrice();
       this.totalVendas = pq.getTotalVendas();
       this.totalQuant = pq.getTotalQuant();
    }
   
    /** Insere uma Venda.
      * @return
      */
   public void inserePrecoQuant(Venda v){
       this.totalprice += v.getPreco()*v.getQuantidade();
       this.totalVendas += v.getQuantidade();
       this.totalQuant ++;
    } 
    
   /** Retorna o total facturado.
     * @return
     */
   public double getTotalPrice(){
       return this.totalprice;
    }
   
    /** Retorna a quantidade total comprada.
      * @return
      */
   public int getTotalVendas(){
       return this.totalVendas;
    } 
   
    /** Retorna o número de vendas.
      * @return
      */
   public int getTotalQuant(){
       return this.totalQuant;
    }
    
   public PrecoQuantidade clone(){
       return new PrecoQuantidade(this);
    } 
}
