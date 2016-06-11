import java.io.Serializable;

public class TriploIntIntDouble implements Serializable{
   private int primeiro;
   private int segundo;
   private double terceiro;
   
   /**
    * Construtor por parâmetros.
    * @param primeiro
    * @param segundo
    * @param terceiro
    */
   public TriploIntIntDouble(int primeiro, int segundo, double terceiro){
       this.primeiro = primeiro;
       this.segundo = segundo;
       this.terceiro = terceiro;
   }
   
   /**
    * Construtor por cópia.
    * @param p
    */
   public TriploIntIntDouble(TriploIntIntDouble p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
       this.terceiro = p.getTerceiro();
   }
   
   /**
    * Função que retorna o primeiro elemento.
    * @return int
    */
   public int getPrimeiro(){
       return this.primeiro;
   }
   
   /**
    * Função que retorna o segundo elemento.
    * @return int
    */
   public int getSegundo(){
       return this.segundo;
   }
   
   /**
    * Função que retorna o terceiro elemento.
    * @return double
    */
   public double getTerceiro(){
       return this.terceiro;
    }
   
   /**
    * Função que testa a igualdade.
    * @param obj
    * @return boolean
    */
   public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        TriploIntIntDouble hi = (TriploIntIntDouble) obj;
        return this.primeiro == hi.getPrimeiro() && this.segundo == hi.getSegundo() && this.terceiro == hi.getTerceiro();
    }
    
   /**
    * Função para imprimir.
    * @return String
    */
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("("+this.primeiro+","+this.segundo+","+this.terceiro+")\n");
        return sb.toString();
    }
    
   /**
    * Função que faz clone.
    * @return TriploIntIntDouble
    */
   public TriploIntIntDouble clone(){
       return new TriploIntIntDouble(this);
   }

}
