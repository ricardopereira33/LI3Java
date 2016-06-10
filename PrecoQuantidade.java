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
   
   public void inserePrecoQuant(Venda v){
       this.totalprice += v.getPreco()*v.getQuantidade();
       this.totalVendas += v.getQuantidade();
       this.totalQuant ++;
    } 
    
   public double getTotalPrice(){
       return this.totalprice;
    }
    
   public int getTotalVendas(){
       return this.totalVendas;
    } 
    
   public int getTotalQuant(){
       return this.totalQuant;
    }
    
   public PrecoQuantidade clone(){
       return new PrecoQuantidade(this);
    } 
}
