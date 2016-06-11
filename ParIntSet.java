import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;

public class ParIntSet implements Serializable
{
   private int primeiro;
   private Set<String> segundo;
   
   /**
    * Construtor por parâmetro.
    * @param primeiro
    */
   public ParIntSet(int primeiro){
       this.primeiro = primeiro;
       this.segundo = new TreeSet<String>();
   }
   
   /**
    * Construtor por parâmetros.
    * @param p
    */
   public ParIntSet(ParIntSet p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
   }
   
   /**
    * Função que aumenta o primeiro elemento.
    * @param x
    */
   public void addPrimeiro(int x){
       this.primeiro += x;
   }
   
   /**
    * Função que adiciona ao segundo.
    * @param str
    */
   public void addSegundo(String str){
       this.segundo.add(str);
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
   public Set<String> getSegundo(){
        return this.segundo;
   }
   
   /**
    * Função que retorna o tamanho do segundo(lista).
    * @return
    */
   public int getTamanhoSegundo(){
       return this.segundo.size();
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
        ParIntSet hi = (ParIntSet) obj;
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
    */
   public ParIntSet clone(){
       return new ParIntSet(this);
   }
}