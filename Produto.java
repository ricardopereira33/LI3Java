import java.util.Arrays;
public class Produto
{
    private String prod;
    
    public Produto(String prod){
        this.prod = prod;
    }
    
    public Produto(Produto p){
        this.prod = p.getNomeProduto();
    }
    
    public String getNomeProduto(){
        return this.prod;
    }
    
    public Produto clone() {
        return new Produto(this);
    }
    
    public int hashCode(){
        return Arrays.hashCode( new Object[] { prod } );
    }
    
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Produto a = (Produto) obj;
        return a.getNomeProduto().equals(this.prod); 
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Produto: ").append(prod).append("\n");
        return sb.toString();
    
    }
    
}
