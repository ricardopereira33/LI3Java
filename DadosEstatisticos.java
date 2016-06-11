import java.io.Serializable;

public class DadosEstatisticos implements Serializable
{
   private String ficheiro;
   private int vendasValidas;
   private int vendasErradas;
   private int produtos;
   private int produtosDif;
   private int produtosNaoComprados;
   private int clientes;
   private int clientesCompraram;
   private int clientesNaoCompraram;
   private int totalZeros;
   private double totalFacturado;
   
   /**
    * Construtor vazio.
    */
   public DadosEstatisticos (){
    this.ficheiro="n/a";
    this.vendasValidas=0;
    this.vendasErradas=0;
    this.produtos=0;
    this.produtosDif=0;
    this.produtosNaoComprados=0;
    this.clientes=0;
    this.clientesCompraram=0;
    this.clientesNaoCompraram=0;
    this.totalZeros=0;
    this.totalFacturado=0;
    }
    
   /**
    * Construtor por parâmetro.
    * @param ficheiro
    * @param vendasValidas
    * @param vendasErradas
    * @param produtos
    * @param produtosDiferentes
    * @param produtosNaoComprados
    * @param clientes 
    * @param clientesCompraram 
    * @param clientesNaoCompraram 
    * @param totalZeros
    * @param totalFacturado
    */
   public DadosEstatisticos(String ficheiro,int vendasValidas,int vendasErradas,int produtos,int produtosDif, int produtosNaoComprados,int clientes, int clientesCompraram, int clientesNaoCompraram, int totalZeros, double totalFacturado){
    this.ficheiro=ficheiro;
    this.vendasValidas=vendasValidas;
    this.vendasErradas=vendasErradas;
    this.produtos=produtos;
    this.produtosDif=produtosDif;
    this.produtosNaoComprados=produtosNaoComprados;
    this.clientes=clientes;
    this.clientesCompraram=clientesCompraram;
    this.clientesNaoCompraram=clientesNaoCompraram;
    this.totalZeros=totalZeros;
    this.totalFacturado=totalFacturado;
    }
   
   /**
    * Construtor por cópia.
    * @param d
    */ 
   public DadosEstatisticos(DadosEstatisticos d){
    this.ficheiro=d.getFicheiro();
    this.vendasValidas=d.getVendasValidas();
    this.vendasErradas=d.getVendasErradas();
    this.produtos=d.getProdutos();
    this.produtosDif=d.getProdutosDif();
    this.produtosNaoComprados=d.getProdutosNaoComprados();
    this.clientes=d.getClientes();
    this.clientesCompraram=d.getClientesCompraram();
    this.clientesNaoCompraram=d.getClientesNaoCompraram();
    this.totalZeros=d.getTotalZeros();
    this.totalFacturado=d.getTotalFacturado();
    }
   
   /**
     * Função que retorna o Nome do ficheiro.
     * @return
     */
   public String getFicheiro(){
    return this.ficheiro;
   }
   
   /**
     * Função que retorna o numero de Vendas validas.
     * @return
   */
   public int getVendasValidas(){
      return this.vendasValidas;
   }
    
   /**
     * Função que retorna o numero de Vendas invalidas.
     * @return
   */
   public int getVendasErradas(){
      return this.vendasErradas;
   }
   
   /**
     * Função que retorna o numero de Produtos diferentes.
     * @return
     */
    public int getProdutos(){
      return this.produtos;
   }
   
   /**
     * Função que retorna o numero de Produtos diferentes que foram comprados.
     * @return
     */
   public int getProdutosDif(){
      return this.produtosDif;
   }
   
   /**
     * Função que retorna numero de Produtos diferentes que nao foram comprados.
     * @return
     */
   public int getProdutosNaoComprados(){
      return this.produtosNaoComprados;
   }
   
   /**
     * Função que retorna o numero de Clientes diferentes.
     * @return
     */
   public int getClientes(){
      return this.clientes;
   }
   
   /**
     * Função que retorna o numero de Clientes diferentes que compraram.
     * @return
     */
   public int getClientesCompraram(){
      return  this.clientesCompraram;
   }
   
   /**
     * Função que retorna o numero de Clientes diferentes nao que compraram.
     * @return
     */
   public int getClientesNaoCompraram(){
      return this.clientesNaoCompraram;
   }
   
   /**
     * Função que retorna o numero de Vendas com o preco a 0.
     * @return
     */
   public int getTotalZeros(){
      return  this.totalZeros;
   }
   
   /**
     * Função que retorna o total facturado.
     * @return
     */
   public double getTotalFacturado(){
      return this.totalFacturado;
   }
   
   /**
     * Função responsável por retornar um clone.
     * @return
     */
   public DadosEstatisticos clone (){
       return new DadosEstatisticos(this);
   }
   
   /**
     * Função que testa a igualdade.
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DadosEstatisticos d = (DadosEstatisticos) obj;

        return this.ficheiro.equals(d.getFicheiro()) && this.vendasValidas==d.getVendasValidas() && 
               this.vendasErradas==d.getVendasErradas() && this.produtos==d.getProdutos() &&
               this.produtosDif==d.getProdutosDif() && this.produtosNaoComprados==d.getProdutosNaoComprados() &&
               this.clientes==d.getClientes() && this.clientesCompraram==d.getClientesCompraram() &&
               this.clientesNaoCompraram==d.getClientesNaoCompraram() && this.totalZeros==d.getTotalZeros() &&
               this.totalFacturado==d.getTotalFacturado();
    }
   
   /**
     * Função responsável por imprimir.
     * @return
     */
   public String toString(){
       StringBuilder str = new StringBuilder();
       str.append("\n");
       str.append("***** Informações sobre a leitura do ficheiro: "+this.ficheiro +" *****\n");
       str.append("\n");
       str.append("    Registos de vendas corretos: " + this.vendasValidas+"\n");
       str.append("    Registos de vendas errados: "  + this.vendasErradas+"\n");
       str.append("_____________________________________________________________\n");
       str.append("    Número de Produtos: " + this.produtos+"\n");
       str.append("    Número de Produtos comprados: " + this.produtosDif+"\n");
       str.append("    Número de Produtos não comprados: " +this.produtosNaoComprados +"\n");
       str.append("_____________________________________________________________\n");
       str.append("    Número de Clientes: " +this.clientes +"\n");
       str.append("    Número de Clientes que realizaram compras: " + this.clientesCompraram+"\n");
       str.append("    Número de Clientes que não realizaram compras: " + this.clientesNaoCompraram+"\n");
       str.append("_____________________________________________________________\n");
       str.append("    Total de compras de valor igual a 0: " + this.totalZeros+"\n");
       str.append("    Facturação total: " + this.totalFacturado+"\n");
       str.append("_____________________________________________________________\n");
       return str.toString();
    }
}