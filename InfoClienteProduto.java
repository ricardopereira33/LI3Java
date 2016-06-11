import java.io.Serializable;

public class InfoClienteProduto implements Serializable
{
    private int quantity[];            
    private double totGasto[];
    private int numVendas;
    
    /**
     * Construtor vazio.
     */
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
    
    /**
     * Construtor por cópia.
     * @param icp
     */
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
    
    /** Retorna a quantidade presente num determinado indice da estrututa.
      * @param indice
      * @return
      */
    public int getQuantity(int indice){
        return this.quantity[indice];
    }
    
    /** Retorna o total gasto presente num determinado indice da estrututa.
      * @param indice
      * @return
      */
    public double getTotGasto(int indice){
        return this.totGasto[indice];
    }
    
    /** Retorna o número de vendas.
      * @return
      */
    public int getNumVendas(){
        return this.numVendas;
    }
    
    /** Insere uma determinada venda.
     * @param v 
     */
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
    
    /**
     * Função responsável por fazer clone.
     * @return
     */
    public InfoClienteProduto clone(){
        return new InfoClienteProduto(this);
    }
}
