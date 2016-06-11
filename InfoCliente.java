import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InfoCliente implements Serializable
{
   private Map<Integer,InfoMes> meses;
   private double totFact;
   
   /**
    * Construtor vazio.
    */
   public InfoCliente(){
       this.meses = new HashMap<Integer,InfoMes>();
       this.totFact = 0;
    }
   
   /**
    * Construtor por cópia.
    * @param ic
    */
   public InfoCliente(InfoCliente ic){
       int i;
       for(i=0;i<12;i++){
           this.meses.put(i,ic.getMesIndex(i));
        }
       this.totFact = ic.getTotFact();
   }
   
   /** 
    * Insere uma determinada venda.
    * @param v
    */
   public void insereInfoC (Venda v){
       this.totFact += v.getPreco() * v.getQuantidade();
       if(this.meses.get(v.getMes()-1)!=null) 
           this.meses.get(v.getMes()-1).insereProdutoQuant(v);
       else{
        InfoMes im = new InfoMes();
        im.insereProdutoQuant(v);
        this.meses.put(v.getMes()-1,im);
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
    public Map<Integer,InfoMes> getMeses(){
        Map<Integer,InfoMes> n = new HashMap<Integer,InfoMes>();
        meses.forEach((k,v) -> n.put(k,v.clone()));
        return n;
    }
   
   /** 
    * Retorna a informação relativa a um determinado mês.
    * @param indice
    * @return
    */
   public InfoMes getMesIndex(int indice){
       if(this.meses.get(indice)==null) return null;
       else return this.meses.get(indice).clone();
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
