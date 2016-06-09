
public class InfoClienteProduto
{
    private int quantity[];            
    private double totGasto[];
    private int numVendas;
    
    public InfoClienteProduto(){
        int i;
        this.quantity= new int [2];
        this.totGasto= new double [2];
        for(i=0;i<2;i++){
            this.quantity[i]=0;
            this.totGasto[i]=0;
        }
        this.numVendas = 0;
    }
    
    public InfoClienteProduto(InfoClienteProduto icp){
        int i;
        this.quantity= new int [2];
        this.totGasto= new double [2];
        for(i=0;i<2;i++){
            this.quantity[i]=icp.getQuantity(i);
            this.totGasto[i]=icp.getTotGasto(i);
        }
        this.numVendas = icp.getNumVendas();
    }
    
    public int getQuantity(int indice){
        return this.quantity[indice];
    }
    
    public double getTotGasto(int indice){
        return this.totGasto[indice];
    }
    
    public int getNumVendas(){
        return this.numVendas;
    }
    
    public void insereInfoCP(Venda v){
        this.numVendas++;
        if(v.getInfoPromo() == 'P'){
            this.quantity[1]+=v.getQuantidade();
            this.totGasto[1]+=(v.getPreco())*(v.getQuantidade());
        }
          else {
            this.quantity[0]+=v.getQuantidade();
            this.totGasto[0]+=(v.getPreco())*(v.getQuantidade());
            }
    } 
    
    public InfoClienteProduto clone(){
        return new InfoClienteProduto(this);
    }
}
