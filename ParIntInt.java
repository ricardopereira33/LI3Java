import java.io.Serializable;

public class ParIntInt implements Serializable
{
   private int primeiro,segundo;
   
   /**
    * Construtor por parâmetros.
    * @param primeiro
    * @param segundo
    */
   public ParIntInt(int primeiro, int segundo){
       this.primeiro = primeiro;
       this.segundo = segundo;
   }
   
   /**
    * Construtor por cópia.
    * @param p
    */
   public ParIntInt(ParIntInt p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
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
        ParIntInt hi = (ParIntInt) obj;
        return this.primeiro == hi.getPrimeiro() && this.segundo == hi.getSegundo();
    }
   
   /**
    * Função para imprimir.
    * @return String
    */
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("("+this.primeiro+","+this.segundo+")\n");
        return sb.toString();
   }
   
   /**
    * Função que faz um clone.
    * @return ParIntInt
    */
   public ParIntInt clone(){
       return new ParIntInt(this);
   }
}
