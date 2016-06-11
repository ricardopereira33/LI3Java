

public class DadosEstatisticos
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
   
  
   public String getFicheiro(){
    return this.ficheiro;
    }
    
   public int getVendasValidas(){
      return this.vendasValidas;
    }
    
   public int getVendasErradas(){
      return this.vendasErradas;
   }
    public int getProdutos(){
      return this.produtos;
   }
    public int getProdutosDif(){
      return this.produtosDif;
   }
    public int getProdutosNaoComprados(){
      return this.produtosNaoComprados;
   }
    public int getClientes(){
      return this.clientes;
   }
    public int getClientesCompraram(){
      return  this.clientesCompraram;
   }
     public int getClientesNaoCompraram(){
      return this.clientesNaoCompraram;
   }
     public int getTotalZeros(){
      return  this.totalZeros;
   }
     public double getTotalFacturado(){
      return this.totalFacturado;
   }
   
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
    
   public String toString(){
       StringBuilder str = new StringBuilder();
       str.append("\n");
       str.append("***** Informações sobre a leitura do ficheiro: "+this.ficheiro +" *****\n");
       str.append("\n");
       str.append("    Registos de vendas corretas: " + this.vendasValidas+"\n");
       str.append("    Registos de vendas errados: "  + this.vendasErradas+"\n");
       str.append("    Número de produtos: " + this.produtos+"\n");
       str.append("    Número de diferentes produtos comprados: " + this.produtosDif+"\n");
       str.append("    Número de diferentes produtos não comprados: " +this.produtosNaoComprados +"\n");
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