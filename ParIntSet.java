import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;

public class ParIntSet implements Serializable
{
   private int primeiro;
   private Set<String> segundo;
   
   public ParIntSet(int primeiro){
       this.primeiro = primeiro;
       this.segundo = new TreeSet<String>();
   }
   
   public void addPrimeiro(int x){
       this.primeiro += x;
   }
   
   public void addSegundo(String str){
       this.segundo.add(str);
   }
   
   public int getPrimeiro(){
       return this.primeiro;
    }
    
   public int getTamanhoSegundo(){
       return this.segundo.size();
    }
}