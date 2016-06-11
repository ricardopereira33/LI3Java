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
    
    /**
     * Retorna o array com as quantidades.
     * @return int[]
     */
    public int[] getQuant(){
        return quantity;
    }
    
    /** 
     * Retorna a quantidade presente num determinado indice da estrututa.
     * @param indice
     * @return int
     */
    public int getQuantity(int indice){
        return this.quantity[indice];
    }
    
    /**
     * Retorna o array com o total gasto.
     * @return double[]
     */
    public double[] getGasto(){
        return totGasto;
    }
    
    /** 
     * Retorna o total gasto presente num determinado indice da estrututa.
     * @param indice
     * @return double[]
     */
    public double getTotGasto(int indice){
        return this.totGasto[indice];
    }
    
    /** 
     * Retorna o número de vendas.
     * @return int
     */
    public int getNumVendas(){
        return this.numVendas;
    }
    
    /** 
     * Insere uma determinada venda.
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
     * @return InfoClienteProduto
     */
    public InfoClienteProduto clone(){
        return new InfoClienteProduto(this);
    }
    
    /**
     * Função que testa a igualdade.
     * @param obj
     * @return boolean
     */
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj == null || this.getClass()!=obj.getClass()) return false;
        InfoClienteProduto i = (InfoClienteProduto) obj;
        return this.numVendas == i.getNumVendas() 
                && this.totGasto.equals(i.getGasto())
                && this.quantity.equals(i.getQuant());
    } 
}
