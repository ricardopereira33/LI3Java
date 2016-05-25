import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;



public class Leitura{
    
    
   public static void main(String[] args){
       // BufferedReader
       System.out.println("----------------------------");
       System.out.println("Leitura do ficheiro: Vendas_3M.txt");
       Crono.start();
       ArrayList<Venda> vendas = readVendasWithBuff("../Vendas_3M.txt");
       Crono.stop();
       System.out.println("Linhas lidas: " + vendas.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       
       System.out.println("----------------------------");
       System.out.println("Leitura do ficheiro: Produtos.txt");
       Crono.start();
       ArrayList<Produto> produtos = readProdutosWithBuff("../Produtos.txt");
       Crono.stop();
       System.out.println("Linhas lidas: " + vendas.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       
       System.out.println("----------------------------");
       System.out.println("Leitura do ficheiro: Clientes.txt");
       Crono.start();
       ArrayList<Cliente> clientes = readClientesWithBuff("../Clientes.txt");
       Crono.stop();
       System.out.println("Linhas lidas: " + vendas.size());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       
       /*Consulta 1
       Scanner s = new Scanner(System.in);
       int filial = s.nextInt();
       Crono.start();
       System.out.println("Número total de compras realizadas na filial"+filial+" :" + comprasFilial(vendas,filial));  
       Crono.stop();
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       s.close();
       
       /*Consulta 2*/
       //System.out.println("Número total de compras a custo zero:" + custoZero(vendas));  

       /*Consulta 3*/
       
       /*Consulta 4*/
       //char letra=s.next().charAt(0);
       //System.out.println("Número de produtos começados pela letra"+letra+" :" + produtosLetra(vendas,letra));
       
       /* Clientes - HashSet */
       /*System.out.printf("Número da filial: ");
       Scanner s = new Scanner(System.in);
       int filial = s.nextInt();
       Crono.start();
       HashSet<String> lista = clientesFilial(vendas,filial);
       Crono.stop();
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("TOTAL: " + lista.size());
       s.close();
       */ 
   } 
   
   public static ArrayList<Cliente> readClientesWithBuff(String fich){
       ArrayList<String> listaBufferedReader = readLinesWithBuff(fich);
       
       ArrayList<Cliente> c = parseAllLinhasCli(listaBufferedReader);
       
       return c;
    }
    
   public static ArrayList<Produto> readProdutosWithBuff(String fich){
       ArrayList<String> listaBufferedReader = readLinesWithBuff(fich);
       
       ArrayList<Produto> p = parseAllLinhasProd(listaBufferedReader);
       
       return p;
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
   
    public static ArrayList<Produto> parseAllLinhasProd(ArrayList<String> linhas){
      
      ArrayList<Produto> listV = new ArrayList<Produto>();
      
      for(String linha: linhas){
          Produto p = new Produto(linha);
          listV.add(listV.size(),p);
      }
      
      return listV;
   }
   
   public static ArrayList<Cliente> parseAllLinhasCli(ArrayList<String> linhas){
      
      ArrayList<Cliente> listV = new ArrayList<Cliente>();
      
      for(String linha: linhas){
          Cliente c = new Cliente(linha);
          listV.add(listV.size(),c);
      }
      
      return listV;
   }
   
   public static ArrayList<Venda> parseAllLinhas(ArrayList<String> linhas){
      
      ArrayList<Venda> listV = new ArrayList<Venda>();
      
      for(String linha: linhas){
          listV.add(listV.size(),parseLinhaVenda(linha));
      }
      
      return listV;
   }
   
   public static HashSet<Venda> parseAllLinhasToSet(ArrayList<String> linhas){
      
      HashSet<Venda> listV = new HashSet<Venda>();
      
      for(String linha: linhas){
          listV.add(parseLinhaVenda(linha));
      }
      
      return listV;
   }
   
   /*Consultas*/
   
   private static int comprasFilialStream (ArrayList<Venda> lista, int filial){  
     return (int) lista.stream().filter(v-> v.getFilial()==filial).count();
    }
    
   private static int comprasFilial(ArrayList<Venda> lista, int filial){
       int contador=0;
       for(Venda v: lista)
            if(v.getFilial() == filial) contador++;
       return contador;
    }
    
   private static int custoZero (ArrayList<Venda> lista){
     return (int) lista.stream().filter(v-> v.getPreco()==0).count();
    }
    
   private static int vendasDuplicadas(ArrayList<Venda> lista){
       HashSet<Venda> diff = new HashSet<>();
       HashSet<Venda> dupp = new HashSet<>();
       for(Venda v : lista){
           if(!diff.add(v)){
               dupp.add(v);
            }
        }
       return dupp.size();
    } 
    
   private static int produtosLetra (ArrayList<Venda> lista,char letra){
     return (int) lista.stream().filter(v-> v.getProduto().charAt(0)==letra).count();
    }
    
   private static HashSet<String> clientesFilialHash(ArrayList<Venda> lista, int filial){
       
       HashSet<String> hash = new HashSet<String>();
       
       for(Venda v: lista)
           if(v.getFilial() == filial) hash.add(v.getCliente());
           
       return hash;
   }
   
   private static TreeSet<String> clientesFilialTree(ArrayList<Venda> lista, int filial){
       TreeSet<String> tree = new TreeSet<>(new ComparatorByString());

       for(Venda v: lista)
            if(v.getFilial() == filial) tree.add(v.getCliente());
            
       return tree;
   }
}
