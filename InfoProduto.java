import java.io.Serializable;
import java.util.List;

public class InfoProduto implements Serializable
{
   private PrecoQuantidade N[][];
   private PrecoQuantidade P[][];
   
   public InfoProduto (){
       this.N = new PrecoQuantidade [12][3];
       this.P = new PrecoQuantidade [12][3];
    }
    
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
   
   public PrecoQuantidade getPromoN(int mes, int filial){
       return N[mes][filial].clone();
   }
  
    public PrecoQuantidade getPromoP(int mes, int filial){
       return P[mes][filial].clone();
   }
   
   public InfoProduto clone(){
       return new InfoProduto(this);
   } 
   
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
    
}

