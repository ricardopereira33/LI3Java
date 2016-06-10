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

public class TestesPerformance implements Serializable
{  
   public static void main(String [ ] args){
       System.out.println("Leitura com Scanner(sem parsing):");
       Crono.start();
       List<String> listaScanner= readLinesWithScanner("../Vendas_5M.txt");
       Crono.stop();
       System.out.println("Linhas lidas: "+listaScanner.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.\n");
       listaScanner.clear();
       System.out.println("Leitura com Buffer(sem parsing):");
       Crono.start();
       List<String> listaBuffer= readLinesWithBuff("../Vendas_5M.txt");
       Crono.stop();
       System.out.println("Linhas lidas: "+listaBuffer.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("-------------------------------------------------");
       listaBuffer.clear();
       System.out.println("Leitura com Scanner(com parsing):");
       Crono.start();
       List<Venda> listaScanner2= readLinesWithScannerVendas("../Vendas_5M.txt");
       Crono.stop();
       System.out.println("Vendas criadas: "+listaScanner2.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.\n");
       listaScanner2.clear();
       System.out.println("Leitura com Buffer(com parsing):");
       Crono.start();
       List<Venda> listaBuffer2= readLinesWithBuffVendas("../Vendas_5M.txt");
       Crono.stop();
       System.out.println("Vendas criadas: "+listaBuffer2.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       listaBuffer2.clear();
   }
   
   public static List<String> readLinesWithScanner(String fich) {
        List<String> linhas = new ArrayList<>();
        Scanner scanFile = null;
        try {
            scanFile = new Scanner(new FileReader(fich));
            scanFile.useDelimiter("\n\r");
            while(scanFile.hasNext())
                linhas.add(scanFile.nextLine());
        }
        catch(IOException ioExc)
             { System.out.println(ioExc.getMessage()); return null; }
        finally { if(scanFile != null) scanFile.close(); }
        return linhas;
   }
   
   public static List<Venda> readLinesWithScannerVendas(String fich) {
        List<Venda> vendas = new ArrayList<>();
        Scanner scanFile = null;
        Venda v;
        try {
            scanFile = new Scanner(new FileReader(fich));
            scanFile.useDelimiter("\n\r");
            while(scanFile.hasNext()){
                v = parseLinhaVenda(scanFile.nextLine());
                if(v!=null){
                    vendas.add(v);
                }
            }
        }
        catch(IOException ioExc)
             { System.out.println(ioExc.getMessage()); return null; }
        finally { if(scanFile != null) scanFile.close(); }
        return vendas;
   }
   
   public static List<String> readLinesWithBuff(String fich) {
      BufferedReader inStream = null; 
      String linha = null;
      List<String> vendas = new ArrayList<String>();
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ){
                if(linha!=null){
                    vendas.add(linha);
                }
            }
      }
      catch(IOException e) 
          { System.out.println(e.getMessage());  };
      return vendas;
   }
   
   public static List<Venda> readLinesWithBuffVendas(String fich) {
      BufferedReader inStream = null; 
      String linha = null;
      Venda v;
      List<Venda> vendas = new ArrayList<Venda>();
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ){
                v = parseLinhaVenda(linha);
                if(v!=null){
                    vendas.add(v);
                }
            }
      }
      catch(IOException e) 
          { System.out.println(e.getMessage());  };
      return vendas;
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
       Venda v = new Venda(produto,cliente,preco,quantidade,infoPromo,mes,filial);
       return v;
   }
}

