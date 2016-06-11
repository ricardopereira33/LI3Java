import java.io.Serializable;

public class ParIntDouble implements Serializable
{   
   private int primeiro;
   private double segundo;
   
   /**
    * Construtor por parâmetros.
    * @param primeiro
    * @param segundo
    */
   public ParIntDouble(int primeiro, double segundo){
       this.primeiro = primeiro;
       this.segundo = segundo;
   }
   
   /**
    * Construtor por cópia.
    * @param p
    */
   public ParIntDouble(ParIntDouble p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
    }
   
    /**
     * Função que retorna o primeiro.
     * @return
     */
   public int getPrimeiro(){
       return this.primeiro;
   }
   
   /**
    * Função que retorna o segundo.
    * @return
    */
   public double getSegundo(){
       return this.segundo;
   }
   
   /**
    * Função que adiciona ao primeiro.
    * @param x
    */
   public void addPrimeiro(int x){
       this.primeiro += x;
    }
   
   /**
    * Função que adiciona ao segundo.
    * @param x
    */
   public void addSegundo(double x){
       this.segundo += x;
    }
   
   /**
    * Função que testa a igualdade.
    * @param obj
    * @return
    */
   public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ParIntDouble hi = (ParIntDouble) obj;
        return this.primeiro == hi.getPrimeiro() && this.segundo == hi.getSegundo();
    }
    
   /**
    * Função para imprimir.
    * @return
    */
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("("+this.primeiro+","+this.segundo+")\n");
        return sb.toString();
   }
   
   /**
    * Função que faz um clone.
    * @return
    */
   public ParIntDouble clone(){
       return new ParIntDouble(this);
   }
}
