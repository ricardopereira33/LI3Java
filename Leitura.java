import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Leitura{
   
   public static void main(String[] args){
       // Scanner
       /*Crono.start();
       ArrayList<String> listaScanner = readLinesArrayWithScanner("Vendas_3M.txt");
       Crono.stop();
       System.out.println(Crono.print());*/
       
       // BufferedReader
       System.out.println("----------------------------");
       System.out.println("Leitura do ficheiro: Vendas_3M.txt");
       Crono.start();
       ArrayList<Venda> vendas = readVendasWithBuff("Vendas_3M.txt");
       Crono.stop();
       System.out.println("Linhas lidas: " + vendas.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       
       /*Consulta 1**/
       Scanner s = new Scanner(System.in);
       int filial = s.nextInt();
       System.out.println("Número total de compras realizadas na filial"+filial+" :" + comprasFilial(vendas,filial));  
      
       
       /*Consulta 2*/
       System.out.println("Número total de compras a custo zero:" + custoZero(vendas));  

       /*Consulta 3*/
       
       /*Consulta 4*/
       char letra=s.next().charAt(0);
       System.out.println("Número de produtos começados pela letra"+letra+" :" + produtosLetra(vendas,letra));
       
       
       s.close();
   } 
   
   public static ArrayList<Venda> readVendasWithBuff(String fich) {
       ArrayList<String> listaBufferedReader = readLinesWithBuff(fich);
       
       ArrayList<Venda> v = parseAllLinhas(listaBufferedReader);
       
       return v;
   }    
    
   public static ArrayList<String> readLinesArrayWithScanner(String ficheiro) {
        ArrayList<String> linhas = new ArrayList<>();
        Scanner scanFile = null;
        try {
            scanFile = new Scanner(new FileReader(ficheiro));
            scanFile.useDelimiter("\n\r");
            while(scanFile.hasNext()) 
                linhas.add(scanFile.nextLine());
        }
        catch(IOException ioExc)
             { System.out.println(ioExc.getMessage()); return null; }
        finally { if(scanFile != null) scanFile.close(); }
        return linhas;
   }

    public static ArrayList<String> readLinesWithBuff(String fich) {
      ArrayList<String> linhas = new ArrayList<>();
      BufferedReader inStream = null; 
      String linha = null;
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null )
                              linhas.add(linha);
      }
      catch(IOException e) 
          { System.out.println(e.getMessage()); return null; };
      return linhas;  
   }
   
   public static Venda parseLinhaVenda(String linha){
       
       int i,quantidade=0,mes=0,filial=0;
       double preco=0;
       String produto=null; 
       String cliente=null;
       char infoPromo='-';
    
       String[] pars = linha.split(" ");
    
       for(i=0;i<7;i++){
           switch(i){
               case 0: produto=pars[0];
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
               case 4: cliente=pars[4].trim();
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
    
        Venda v = new Venda(produto,cliente,preco,quantidade,infoPromo,mes,filial);
        return v;
   }
   
   public static ArrayList<Venda> parseAllLinhas(ArrayList<String> linhas){
      
      ArrayList<Venda> listV = new ArrayList<Venda>();
      
      for(String linha: linhas){
          listV.add(listV.size(),parseLinhaVenda(linha));
      }
      
      return listV;
   }
   
   
   
   /*Consultas*/
   
   private static int comprasFilial (ArrayList<Venda> lista, int filial){  
     return (int) lista.stream().filter(v-> v.getFilial()==filial).count();
    }
    
   private static int custoZero (ArrayList<Venda> lista){
     return (int) lista.stream().filter(v-> v.getPreco()==0).count();
    }
    
   private static int produtosLetra (ArrayList<Venda> lista,char letra){
     return (int) lista.stream().filter(v-> v.getProduto().charAt(0)==letra).count();
    }
    
    
}
