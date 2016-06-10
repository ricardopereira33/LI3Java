import java.io.Serializable;

public class ParIntInt implements Serializable
{
   private int primeiro,segundo;
   
   public ParIntInt(int primeiro, int segundo){
       this.primeiro = primeiro;
       this.segundo = segundo;
    }
   
   public ParIntInt(ParIntInt p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
    }
   
   public int getPrimeiro(){
       return this.primeiro;
   }
   
   public int getSegundo(){
       return this.segundo;
   }
    
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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("("+this.primeiro+","+this.segundo+")\n");
        return sb.toString();
    }
}
