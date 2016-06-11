import java.io.Serializable;

public class TriploStringIntInt implements Serializable
{
    private String primeiro;
    private int segundo;
    private int terceiro;
   
   /**
    * Construtor por parâmetros.
    * @param primeiro
    * @param segundo
    * @param terceiro
    */
   public TriploStringIntInt(String primeiro, int segundo, int terceiro){
       this.primeiro = primeiro;
       this.segundo = segundo;
       this.terceiro = terceiro;
   }
   
   /**
    * Construtor por cópia.
    * @param p
    */
   public TriploStringIntInt(TriploStringIntInt p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
       this.terceiro = p.getTerceiro();
    }
   
   /**
    * Função que retorna o primeiro elemento.
    * @return String
    */
   public String getPrimeiro(){
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
    * @return int
    */
   public int getTerceiro(){
       return this.terceiro;
   }
   
   /** 
    * Função testa a igualdade.
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
        TriploStringIntInt hi = (TriploStringIntInt) obj;
        return this.primeiro.equals(hi.getPrimeiro()) && this.segundo == hi.getSegundo() && this.terceiro == hi.getTerceiro();
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
    * @return TriploStringIntInt
    */
   public TriploStringIntInt clone(){
       return new TriploStringIntInt(this);
   }
}