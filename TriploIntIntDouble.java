import java.io.Serializable;

public class TriploIntIntDouble implements Serializable
{
    private int primeiro;
    private int segundo;
    private double terceiro;
    
    public TriploIntIntDouble(int primeiro, int segundo, double terceiro){
       this.primeiro = primeiro;
       this.segundo = segundo;
       this.terceiro = terceiro;
    }
   
   public TriploIntIntDouble(TriploIntIntDouble p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
       this.terceiro = p.getTerceiro();
    }
   
   public int getPrimeiro(){
       return this.primeiro;
   }
   
   public int getSegundo(){
       return this.segundo;
   }
    
   public double getTerceiro(){
       return this.terceiro;
    }
    
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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("("+this.primeiro+","+this.segundo+","+this.terceiro+")\n");
        return sb.toString();
    }

}
