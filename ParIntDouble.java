
public class ParIntDouble
{   
    private int primeiro;
    private double segundo;
    
    public ParIntDouble(int primeiro, double segundo){
       this.primeiro = primeiro;
       this.segundo = segundo;
    }
   
   public ParIntDouble(ParIntDouble p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
    }
   
   public int getPrimeiro(){
       return this.primeiro;
   }
   
   public double getSegundo(){
       return this.segundo;
   }
   
   public void addPrimeiro(int x){
       this.primeiro += x;
    }
   
   public void addSegundo(double x){
       this.segundo += x;
    }
    
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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("("+this.primeiro+","+this.segundo+")\n");
        return sb.toString();
    }


}
