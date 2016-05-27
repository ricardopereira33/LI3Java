import java.util.Arrays;
import java.io.Serializable;

public class Venda implements Serializable{
    
    private String produto;
    private String cliente;
    private double preco;
    private int quantidade;
    private char infoPromo;
    private int mes;
    private int filial;

    public Venda(String produto, String cliente, double preco,int quantidade, char infoPromo, int mes, int filial){
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.infoPromo = infoPromo;
        this.mes = mes;
        this.filial = filial;
    }
    
    public Venda(){
        this(null,null,0.0,0,'-',0,0);
    }
    
    public Venda(Venda a){
        this.produto = a.getProduto();
        this.cliente = a.getCliente();
        this.preco = a.getPreco();
        this.quantidade = a.getQuantidade();
        this.infoPromo = a.getInfoPromo();
        this.mes = a.getMes();
        this.filial = a.getFilial();
    }
    
    public String getProduto(){
        return this.produto;
    }
    
    public String getCliente(){
        return this.cliente;
    }
    
    public double getPreco(){
        return this.preco;
    }
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public char getInfoPromo(){
        return this.infoPromo;
    }
    
    public int getMes(){
        return this.mes;
    }
    
    public int getFilial(){
        return this.filial;
    }
    
    public Venda clone() {
        return new Venda(this);
    }
    
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Venda a = (Venda) obj;
        return a.getProduto().equals(this.produto) && 
               a.getCliente().equals(this.cliente) && 
               a.getPreco()==this.preco &&
               a.getQuantidade() == this.quantidade && 
               a.getInfoPromo() == this.infoPromo &&
               a.getMes() == this.mes &&
               a.getFilial() == this.filial;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Produto: ");
        sb.append(produto.toString());
        sb.append("\n");
        sb.append("Cliente: ");
        sb.append(cliente.toString());
        sb.append("\n");
        sb.append("Quantidade: ");
        sb.append(quantidade);
        sb.append("\n");
        sb.append("Preço: ");
        sb.append(preco);
        sb.append("\n");
        sb.append("Info Promoção: ");
        sb.append(infoPromo);
        sb.append("\n");
        sb.append("Mês: ");
        sb.append(mes);
        sb.append("\n");
        return sb.toString();
    }
    
    public int hashCode(){
        //return produto.hashCode()+cliente.hashCode()+quantidade+(int)preco+(String.valueOf(infoPromo)).hashCode()+mes;
        return Arrays.hashCode(new Object[]{produto,cliente,preco,quantidade,infoPromo,mes,filial});
    }
}
