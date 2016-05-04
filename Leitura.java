import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Leitura{
   
   public static void main(String[] args){
       // Scanner
       System.out.println("Olá mundo!");
       Crono.start();
       ArrayList<String> listaScanner = readLinesArrayWithScanner("Vendas_3M.txt");
       Crono.stop();
       System.out.println(Crono.print());
       
       // BufferedReader
       //System.out.println("Olá mundo!");
       //Crono.start();
       //ArrayList<String> listaBufferedReader = readLinesWithBuff("Vendas_3M.txt");
       //Crono.stop();
       //System.out.println(Crono.print());
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

}
