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
    
    /**
     * Construtor por parâmetro.
     * @param produto
     * @param cliente
     * @param preco
     * @param quantidade
     * @param infoPromo
     * @param mes
     * @param filial
     */
    public Venda(String produto, String cliente, double preco,int quantidade, char infoPromo, int mes, int filial){
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.infoPromo = infoPromo;
        this.mes = mes;
        this.filial = filial;
    }
    
    /**
     * Constutor vazio.
     */
    public Venda(){
        this(null,null,0.0,0,'-',0,0);
    }
    
    /**
     * Construtor por cópia.
     * @param a
     */
    public Venda(Venda a){
        this.produto = a.getProduto();
        this.cliente = a.getCliente();
        this.preco = a.getPreco();
        this.quantidade = a.getQuantidade();
        this.infoPromo = a.getInfoPromo();
        this.mes = a.getMes();
        this.filial = a.getFilial();
    }
    
    /**
     * Função que retorna o Produto.
     * @return String
     */
    public String getProduto(){
        return this.produto;
    }
    
    /**
     * Função que retorna o Cliente.
     * @return String
     */
    public String getCliente(){
        return this.cliente;
    }
    
    /**
     * Função que retorna o preço.
     * @return double
     */
    public double getPreco(){
        return this.preco;
    }
    
    /**
     * Função que retorna a quantidade.
     * @return int
     */
    public int getQuantidade(){
        return this.quantidade;
    }
    
    /**
     * Função que retorna a informação (Promoção ou não).
     * @return char 
     */
    public char getInfoPromo(){
        return this.infoPromo;
    }
    
    /**
     * Função que retorna o mês.
     * @return int
     */
    public int getMes(){
        return this.mes;
    }
    
    /**
     * Função que retorna a filial.
     * @return int
     */
    public int getFilial(){
        return this.filial;
    }
    
    /**
     * Função responsável por retornar um clone.
     * @return Venda
     */
    public Venda clone() {
        return new Venda(this);
    }
    
    /**
     * Função responsável por testar a igualdade.
     * @param obj
     * @return boolean
     */
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
    
    /**
     * Função responsável por imprimir.
     * @return String
     */
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
    
    /**
     * Função responsável por calcular o hashCode.
     * @return int
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{produto,cliente,preco,quantidade,infoPromo,mes,filial});
    }
}
