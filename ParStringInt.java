import java.io.Serializable;

public class ParStringInt implements Serializable
{
   private String str;
   private int num;
   
   /**
    * Construtor por parâmetros.
    * @param str
    * @param num
    */
   public ParStringInt(String str, int num){
        this.str = str;
        this.num = num;
    }
   
   /**
    * Constutor por cópia.
    * @param a
    */
   public ParStringInt(ParStringInt a){
        this(a.getString(),a.getNumero());
   }
   
   /**
    * Função que retorna a string.
    * @return String
    */
   public String getString(){
        return this.str;
   }
   
   /**
    * Função que retorna o número.
    * @return int
    */
   public int getNumero(){
        return this.num;
   }    
   
   /**
    * Função que retorna um clone.
    * @return ParStringInt
    */
   public ParStringInt clone() {
        return new ParStringInt(this);
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
        ParStringInt hi = (ParStringInt) obj;
        return hi.getString().equals(this.str) && hi.getNumero() == this.num;
   }
   
   /**
    * Função para imprimir.
    * @return String
    */
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\n");
        sb.append(num);
        sb.append("\n");
        return sb.toString();
   }
}
