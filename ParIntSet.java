import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;

public class ParIntSet implements Serializable
{
   private int primeiro;
   private Set<String> segundo;
   
   /**
    * Construtor por parâmetros.
    * @param primeiro
    */
   public ParIntSet(int primeiro){
       this.primeiro = primeiro;
       this.segundo = new TreeSet<String>();
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
    * Função que retorna o tamanho do segundo(lista).
    * @return
    */
   public int getTamanhoSegundo(){
       return this.segundo.size();
    }
}