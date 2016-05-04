import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Leitura{
   
   public static ArrayList<Venda> main(String[] args){
       // Scanner
       /*Crono.start();
       ArrayList<String> listaScanner = readLinesArrayWithScanner("Vendas_3M.txt");
       Crono.stop();
       System.out.println(Crono.print());*/
       
       // BufferedReader
       Crono.start();
       ArrayList<String> listaBufferedReader = readLinesWithBuff("Vendas_3M.txt");
       Crono.stop();
       System.out.println(Crono.print());
       
       ArrayList<Venda> v = parseAllLinhas(listaBufferedReader);
       return v;
   } 
    
   public static ArrayList<String>
            readLinesArrayWithScanner(String ficheiro) {
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
            case 1: preco=Double.parseDouble(pars[1]);
                    break;
            case 2: quantidade=Integer.parseInt(pars[2]);
                    break;
            case 3: infoPromo=pars[3].charAt(0);
                    break;
            case 4: cliente=pars[4];
                    break;
            case 5: mes=Integer.parseInt(pars[5]);
                    break;
            case 6: filial=Integer.parseInt(pars[6]);
                    break;
        }
    }
    
    Venda v = new Venda(produto,cliente,preco,quantidade,infoPromo,mes,filial);
    
    return v;
   }
   
   public static ArrayList<Venda> parseAllLinhas(ArrayList<String> linhas){
      ArrayList<Venda> listV = new ArrayList<Venda>();
      
      for(String linha: linhas){
          listV.add( listV.size(),parseLinhaVenda(linha) );
      }
      
      return listV;
   }
   
}
