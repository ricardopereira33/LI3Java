
public class InfoClienteProduto
{
    private int quantity[];            
    private double price[]; 
    
    public InfoClienteProduto(){
        int i;
        this.quantity= new int [2];
        this.price= new double [2];
        for(i=0;i<2;i++){
            this.quantity[i]=0;
            this.price[i]=0;
        }
    }
    
    public InfoClienteProduto(InfoClienteProduto icp){
        int i;
        for(i=0;i<2;i++){
            this.quantity[i]=icp.getQuantity(i);
            this.price[i]=icp.getPrice(i);
        }
    }
    
    public int getQuantity(int indice){
        return this.quantity[indice];
    }
    
    public double getPrice(int indice){
        return this.price[indice];
    }
    
    public void insereInfoCP(Venda v){
        if(v.getInfoPromo() == 'P'){
            this.quantity[1]+=v.getQuantidade();
            this.price[1]+=v.getPreco();
        }
          else {
            this.quantity[0]+=v.getQuantidade();
            this.price[0]+=v.getPreco();
            }
      } 
    
    public InfoClienteProduto clone(){
        return new InfoClienteProduto(this);
    }
}
