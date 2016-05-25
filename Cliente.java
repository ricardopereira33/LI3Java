import java.util.Arrays;
public class Cliente
{
   private String cli;
    
    public Cliente(String cli){
        this.cli = cli;
    }
    
    public Cliente(Cliente c){
        this.cli = c.getNomeCliente();
    }
    
    public String getNomeCliente(){
        return this.cli;
    }
    
    public Cliente clone() {
        return new Cliente(this);
    }
    
     public int hashCode(){
        return Arrays.hashCode( new Object[] { cli } );
    }
    
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Cliente a = (Cliente) obj;
        return a.getNomeCliente().equals(this.cli); 
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cli).append("\n");
        return sb.toString();
    
    }
}
