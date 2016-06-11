import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.List;
import java.io.Serializable;


public class Leitura implements Serializable{
    
   /**
    * Função que faz a leitura e cronometragem do ficheiro das Vendas.
    * @param ficheiro
    * @param prod
    * @param cli
    * @param f
    * @param fil
    */
   public static DadosEstatisticos leituraVendas(String ficheiro, CatProdutos prod,CatClientes cli,Facturacao f,Filial fil[]){
       System.out.println("_____________________________________________________________");
       System.out.println("     Leitura do ficheiro: " + ficheiro);
       Crono.start();
       DadosEstatisticos n = readLinesWithBuffVendas(ficheiro,cli,prod,f,fil);
       Crono.stop();
       System.out.println("     Linhas lidas: "  +  (n.getVendasValidas()+n.getVendasErradas()) );
       System.out.println("     Tempo: " + Crono.print() + " segundos.");
       System.out.println("_____________________________________________________________");
       return n;
   } 
   
   /**
    * Função que faz a leitura e cronometragem do ficheiro dos Clientes.
    * @param ficheiro
    * @param cli
    */
   public static void leituraClientes(String ficheiro, CatClientes cli){
       System.out.println("_____________________________________________________________");
       System.out.println("     Leitura do ficheiro: " + ficheiro);
       Crono.start();
       readLinesWithBuffCli(ficheiro,cli);
       Crono.stop();
       System.out.println("     Linhas lidas: " + cli.totalClientes());
       System.out.println("     Tempo: " + Crono.print() + " segundos.");
   }
   
   /**
    * Função que faz a leitura e cronometragem do ficheiros dos Produtos.
    * @param ficheiro
    * @param prod
    */
   public static void leituraProdutos(String ficheiro, CatProdutos prod){
       System.out.println("_____________________________________________________________");
       System.out.println("     Leitura do ficheiro: " + ficheiro);
       Crono.start();
       readLinesWithBuffProd(ficheiro,prod);
       Crono.stop();
       System.out.println("     Linhas lidas: " + prod.totalProdutos());
       System.out.println("     Tempo: " + Crono.print() + " segundos.");
   } 

   /**
    * Função que faz a leitura do ficheiro dos Clientes.
    * @param fich
    * @param cli
    */
    public static void readLinesWithBuffCli(String fich,CatClientes cli) {
      BufferedReader inStream = null; 
      String linha = null;
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ){
                              cli.insereCliente(linha);
                            }
      }
      catch(IOException e) 
          { System.out.println(e.getMessage()); };

   }      
   
   /**
    * Função que faz a leitura do ficheiro dos Produtos.
    * @param fich
    * @param prod
    */
    public static void readLinesWithBuffProd(String fich,CatProdutos prod) {
      BufferedReader inStream = null; 
      String linha = null;
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ){
                              prod.insereProduto(linha);
                            }
      }
      catch(IOException e) 
          { System.out.println(e.getMessage());  };
        
   }
   
   /**
    * Função que faz a leitura do ficheiro das Vendas.
    * @param fich
    * @param cli
    * @param prod
    * @param f
    * @param fil
    * @return DadosEstatisticos
    */
    public static DadosEstatisticos readLinesWithBuffVendas(String fich,CatClientes cli,CatProdutos prod,Facturacao f,Filial fil[]) {
      BufferedReader inStream = null; 
      String linha = null;
      Venda v;
      int vendasErradas=0;
      int vendasValidas=0;
      Set<String> produtosDif=new HashSet<String>();
      Set<String> clientesCompraram=new HashSet<String>();
      int totalZeros=0;
      double totalFacturado=0;
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ){
                v = parseLinhaVenda(linha,cli,prod);
                if(v!=null){
                    if(v.getPreco()==0) totalZeros++;
                    produtosDif.add(v.getProduto());
                    clientesCompraram.add(v.getCliente());
                    totalFacturado+=(v.getPreco()*v.getQuantidade());
                    vendasValidas++;
                    f.insereVenda(v);
                    fil[v.getFilial()-1].insereVenda(v);
                   
                }
                else{
                    vendasErradas++;
                }
            }
      }
      catch(IOException e) 
          { System.out.println(e.getMessage());  };
      return new DadosEstatisticos(fich,vendasValidas,vendasErradas,prod.totalProdutos(),produtosDif.size(),(prod.totalProdutos()-produtosDif.size()),cli.totalClientes(),clientesCompraram.size(),(cli.totalClientes()-clientesCompraram.size()),totalZeros,totalFacturado);
   }
   
   /**
    * Função que faz parsing das linhas do ficheiro Vendas.
    * @param linha
    * @param cli
    * @param prod
    * @return Venda
    */
   public static Venda parseLinhaVenda(String linha,CatClientes cli,CatProdutos prod){
       
       int i,quantidade=0,mes=0,filial=0;
       double preco=0;
       String produto=null; 
       String cliente=null;
       char infoPromo='-';

       String[] pars = linha.split(" ");
    
       for(i=0;i<7;i++){
           switch(i){
               case 0: produto=pars[0].trim();
                       break;
               case 1: try{preco=Double.parseDouble(pars[1].trim());}
                       catch(NullPointerException | NumberFormatException e ){
                           return null;
                       }
                       break;
               case 2: try{quantidade=Integer.parseInt(pars[2].trim());}
                       catch(NullPointerException | NumberFormatException e ){
                           return null;
                       }
                       break;
               case 3: infoPromo=pars[3].trim().charAt(0);
                       break;
               case 4: cliente= pars[4].trim();
                       break;
               case 5: try{mes=Integer.parseInt(pars[5].trim());}
                       catch(NullPointerException | NumberFormatException e ){
                          return null;
                        }
                       break;
               case 6: try{filial=Integer.parseInt(pars[6].trim());}
                       catch(NullPointerException | NumberFormatException e){
                         return null;
                        }
                       break;
        }
       }
       
       if(cli.existeCliente(cliente) && prod.existeProduto(produto)){
            Venda v = new Venda(produto,cliente,preco,quantidade,infoPromo,mes,filial);
            return v;
       }
       else return null;
   }
   
}