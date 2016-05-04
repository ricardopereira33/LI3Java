public class Venda{
    
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
        this("n/a","n/a",0.0,0,'-',0,0);
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
}
