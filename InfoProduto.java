import java.io.Serializable;
import java.util.List;

public class InfoProduto implements Serializable
{
   private PrecoQuantidade N[][];
   private PrecoQuantidade P[][];
   
   /**
    * Construtor vazio.
    */
   public InfoProduto (){
       this.N = new PrecoQuantidade [12][3];
       this.P = new PrecoQuantidade [12][3];
    }
   
   /**
    * Construtor por cópia.
    * @param ip
    */
   public InfoProduto(InfoProduto ip){
       int i,j;
       if(ip!=null){
            for(i=0;i<12;i++){
                for(j=0;j<3;j++){
                    this.N[i][j]=ip.getPromoN(i,j);
                    this.P[i][j]=ip.getPromoP(i,j);
                }
            }
       } 
   } 
   
   /** 
    * Insere uma determinada Venda.
    * @param v
    */
   public void insereInfoP(Venda v){
       if(v.getInfoPromo() == 'P'){
          if(this.P[v.getMes()-1][v.getFilial()-1]!=null)
            this.P[v.getMes()-1][v.getFilial()-1].inserePrecoQuant(v);
          else {
            PrecoQuantidade pq = new PrecoQuantidade();
            pq.inserePrecoQuant(v);
            this.P[v.getMes()-1][v.getFilial()-1]=pq;
            }
        }
      else {
          if(this.N[v.getMes()-1][v.getFilial()-1]!=null)
            this.N[v.getMes()-1][v.getFilial()-1].inserePrecoQuant(v);
          else {
            PrecoQuantidade pq = new PrecoQuantidade();
            pq.inserePrecoQuant(v);
            this.N[v.getMes()-1][v.getFilial()-1]=pq;
            }
      }
   }
   
   /**
    * Retorna a matriz dos Sem-Promoção (N).
    * @return PrecoQuantidade[][]
    */
   public PrecoQuantidade[][] getN(){
       return N;
   }
   
   /**
    * Retorna a matriz dos Com-Promoção (P).
    * @return PrecoQuantidade[][]
    */
   public PrecoQuantidade[][] getP(){
       return P;
   }
   
   /** Retorna a estrutura PrecoQuantidade referente a um determinado mês, de uma determinada filial,no modo Normal.
     * @param mes
     * @param filial
     * @return PrecoQuantidade
     */
   public PrecoQuantidade getPromoN(int mes, int filial){
       return N[mes][filial].clone();
   }
  
   /** Retorna a estrutura PrecoQuantidade referente a um determinado mês, de uma determinada filial,no modo Promoção.
     * @param mes
     * @param filial
     * @return PrecoQuantidade
     */
    public PrecoQuantidade getPromoP(int mes, int filial){
       return P[mes][filial].clone();
   }
   
   /**
    * Função responsável por fazer clone.
    * @return InfoProduto
    */
   public InfoProduto clone(){
       return new InfoProduto(this);
   } 
   
   /** Calcula o total Facturado.
      * @return double
      */
   public double total(){
       int i,j;
       double total=0;
       for(i=0;i<12;i++){
        for(j=0;j<3;j++){
            if(this.P[i][j]!=null)total+=this.P[i][j].getTotalPrice();
            if(this.N[i][j]!=null)total+=this.N[i][j].getTotalPrice();
        }
       }
       return total;
    
    }
    
   /** Calcula o total de compras de valor igual a 0.
     * @return int
     */
   public int zero(){
       int i,j;
       int total=0;
       for(i=0;i<12;i++){
        for(j=0;j<3;j++){
            if(this.P[i][j]!=null)
                if(this.P[i][j].getTotalPrice()==0) 
                    total++;
            if(this.N[i][j]!=null)
                if(this.N[i][j].getTotalPrice()==0) 
                    total++;
        }
       }
       return total;
   } 
   
   /** Calcula para cada mês, o número de unidades compradas e a facturação total(de um dado produto).
     * @param lista
     * @return List<ParIntDouble>
     */
    public List<ParIntDouble> NumVendasFactTotal(List<ParIntDouble> lista){
       int i,j;
       double total=0;
       int numVendas= 0;
       for(i=0;i<12;i++){
        total=0;
        numVendas=0;
        for(j=0;j<3;j++){
            if(this.P[i][j]!=null){total+=this.P[i][j].getTotalPrice();numVendas+=this.P[i][j].getTotalQuant();} 
            if(this.N[i][j]!=null){total+=this.N[i][j].getTotalPrice();numVendas+=this.N[i][j].getTotalQuant();}
        }
        ParIntDouble aux = new ParIntDouble(numVendas,total);
        lista.add(i,aux);
       }

       return lista;
    }
    
    /** Calcula a quantidade total vendida(de um determinado produto).
      * @return int
      */
    public int totalQuant(){
       int i,j;
       int total=0;
       for(i=0;i<12;i++){
        for(j=0;j<3;j++){
            if(this.P[i][j]!=null)total+=this.P[i][j].getTotalVendas();
            if(this.N[i][j]!=null)total+=this.N[i][j].getTotalVendas();
        }
       }
       return total;
    }
    
    /** Calcula o número de vendas num determinado mês.
      * @param mes
      * @return int
      */
     public int numVendasTotal(int mes){
       int i,j;
       int total=0;
       
        for(j=0;j<3;j++){
            if(this.P[mes][j]!=null){total+=this.P[mes][j].getTotalQuant();} 
            if(this.N[mes][j]!=null){total+=this.N[mes][j].getTotalQuant();}
        }
       

       return total;
    }
    /**
     * Função que testa a igualdade.
     * @param obj
     * @return boolean
     */
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj == null || this.getClass()!=obj.getClass()) return false;
        InfoProduto i = (InfoProduto) obj;
        return this.N.equals(i.getN()) && this.P.equals(i.getP());
    } 
    
}

