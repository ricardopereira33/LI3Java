import java.io.Serializable;

public class InfoCliente implements Serializable
{
   private InfoMes meses [];
   private double totFact;
   
   /**
    * Construtor vazio.
    */
   public InfoCliente(){
       this.meses = new InfoMes [12];
       this.totFact = 0;
    }
   
   /**
    * Construtor por cópia.
    * @param ic
    */
   public InfoCliente(InfoCliente ic){
       int i;
       for(i=0;i<12;i++){
            this.meses[i] = ic.getMesIndex(i);
        }
       this.totFact = ic.getTotFact();
   }
   
   /** 
    * Insere uma determinada venda.
    * @param v
    */
   public void insereInfoC (Venda v){
       this.totFact += v.getPreco() * v.getQuantidade();
       if(this.meses[v.getMes()-1]!=null) 
           this.meses[v.getMes()-1].insereProdutoQuant(v);
       else{
        InfoMes im = new InfoMes();
        im.insereProdutoQuant(v);
        this.meses[v.getMes()-1]=im;
        }
    }
   
    /** 
     * Retorna o total facturado.
     * @return
     */
   public double getTotFact(){
       return this.totFact;
    }
    
    /**
     * Retorna os meses.
     * @return
     */
    public InfoMes[] getMeses(){
        return meses;
    }
   
   /** 
    * Retorna a informação relativa a um determinado mês.
    * @param indice
    * @return
    */
   public InfoMes getMesIndex(int indice){
       if(this.meses[indice]==null) return null;
       else return this.meses[indice].clone();
    }
    
   /**
    * Função que faz clone.
    * @return
    */
   public InfoCliente clone(){
       return new InfoCliente(this);
    }
    
   /**
     * Função que testa a igualdade.
     */
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj == null || this.getClass()!=obj.getClass()) return false;
        InfoCliente i = (InfoCliente) obj;
        return this.totFact == i.getTotFact() && this.meses.equals(i.getMeses());
    } 
    
    /** 
     * Calcula o indice relativo a uma determinada letra.
     * @param letra 
     * @return
     */
    private static int calculaIndice(char letra){
        return letra - 'A';
    } 
}
