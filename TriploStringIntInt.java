public class TriploStringIntInt
{
    private String primeiro;
    private int segundo;
    private int terceiro;
    
    public TriploStringIntInt(String primeiro, int segundo, int terceiro){
       this.primeiro = primeiro;
       this.segundo = segundo;
       this.terceiro = terceiro;
    }
   
   public TriploStringIntInt(TriploStringIntInt p){
       this.primeiro = p.getPrimeiro();
       this.segundo = p.getSegundo();
       this.terceiro = p.getTerceiro();
    }
   
   public String getPrimeiro(){
       return this.primeiro;
   }
   
   public int getSegundo(){
       return this.segundo;
   }
    
   public int getTerceiro(){
       return this.terceiro;
   }
    
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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("("+this.primeiro+","+this.segundo+","+this.terceiro+")\n");
        return sb.toString();
    }
}