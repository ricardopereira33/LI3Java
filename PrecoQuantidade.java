import java.io.Serializable;

public class PrecoQuantidade implements Serializable
{
   private double totalprice;             
   private int totalVendas; 
   
   public PrecoQuantidade (){
       this.totalprice = 0;
       this.totalVendas = 0;
   }
   
   public PrecoQuantidade (PrecoQuantidade pq){
       this.totalprice = pq.getTotalPrice();
       this.totalVendas = pq.getTotalVendas();
    }
   
   public void inserePrecoQuant(Venda v){
       this.totalprice += v.getPreco()*v.getQuantidade();
       this.totalVendas += v.getQuantidade();
    } 
    
   public double getTotalPrice(){
       return this.totalprice;
    }
    
   public int getTotalVendas(){
       return this.totalVendas;
    } 
    
   public PrecoQuantidade clone(){
       return new PrecoQuantidade(this);
    } 
}
