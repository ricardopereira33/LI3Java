
public class InfoCliente
{
   private InfoMes meses [];
   
   public InfoCliente(){
       this.meses = new InfoMes [12];
    }
   
   public InfoCliente(InfoCliente ic){
       int i;
       for(i=0;i<12;i++){
            this.meses[i] = ic.getMesIndex(i);
        }
    } 
    
   public void insereInfoC (Venda v){
       if(this.meses[v.getMes()-1]!=null) 
           this.meses[v.getMes()-1].insereProdutoQuant(v);
       else{
        InfoMes im = new InfoMes();
        im.insereProdutoQuant(v);
        this.meses[v.getMes()-1]=im;
        }
    }
    
   public InfoMes getMesIndex(int indice){
       return this.meses[indice];
    }
    
   public InfoCliente clone(){
       return new InfoCliente(this);
    }
    
    private static int calculaIndice(char letra){
        return letra - 'A';
    } 
}
