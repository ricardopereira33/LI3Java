
public class ParStringInt
{
   private String str;
   private int num;
   
   public ParStringInt(String str, int num){
        this.str = str;
        this.num = num;
    }
    
    public ParStringInt(ParStringInt a){
        this(a.getString(),a.getNumero());
    }
    
    public String getString(){
        return this.str;
    }
    
    public int getNumero(){
        return this.num;
    }    
    
    public ParStringInt clone() {
        return new ParStringInt(this);
    }
    
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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\n");
        sb.append(num);
        sb.append("\n");
        return sb.toString();
    }
}
