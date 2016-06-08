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
    
    
   public static void leituraVendas(CatProdutos prod,CatClientes cli,Facturacao f,Filial fil[]){
       // BufferedReader

       System.out.println("--------------------------------------------------------");
       System.out.println("Leitura do ficheiro: Vendas_1M.txt");
       Crono.start();
       int n=readLinesWithBuffVendas("../Vendas_1M.txt",cli,prod,f,fil);
       Crono.stop();
       System.out.println("Linhas lidas: "  + n );
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("--------------------------------------------------------");
   
   } 
   
   public static void leituraClientes(CatClientes cli){
       System.out.println("--------------------------------------------------------");
       System.out.println("Leitura do ficheiro: Clientes.txt");
       Crono.start();
       readLinesWithBuffCli("../Clientes.txt",cli);
       Crono.stop();
       System.out.println("Linhas lidas: " + cli.totalClientes());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("--------------------------------------------------------");
   }
   
   public static void leituraProdutos(CatProdutos prod){
       System.out.println("--------------------------------------------------------");
       System.out.println("Leitura do ficheiro: Produtos.txt");
       Crono.start();
       readLinesWithBuffProd("../Produtos.txt",prod);
       Crono.stop();
       System.out.println("Linhas lidas: " + prod.totalProdutos());
       System.out.println("Tempo: " + Crono.print() + "segundos.");
       System.out.println("--------------------------------------------------------");
   } 
   
   
  /* public static ArrayList<Cliente> readClientesWithBuff(String fich){
       ArrayList<Cliente> c = readLinesWithBuffCli(fich);
       
       ArrayList<Cliente> c = parseAllLinhasCli(listaBufferedReader);
       
       listaBufferedReader.clear();
       return c;
    }
    
   public static ArrayList<Produto> readProdutosWithBuff(String fich){
       ArrayList<Produto> p = readLinesWithBuffProd(fich);
       
       ArrayList<Produto> p = parseAllLinhasProd(listaBufferedReader);
       
       listaBufferedReader.clear();
       return p;
    }*/
    
  /*public static ArrayList<Venda> readVendasWithBuff(String fich,CatClientes cli,CatProdutos prod) {
       ArrayList<Venda> v = readLinesWithBuffVendas(fich,cli,prod);
       
       ArrayList<Venda> v = parseAllLinhas(listaBufferedReader,cli,prod);
       
       listaBufferedReader.clear();
       return v;       
   }*/
    
   /*public static ArrayList<String> readLinesArrayWithScanner(String ficheiro) {
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
   }*/

    public static void readLinesWithBuffCli(String fich,CatClientes cli) {
      BufferedReader inStream = null; 
      String linha = null;
      Cliente c;
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ){
                              cli.insereCliente(linha);
                            }
      }
      catch(IOException e) 
          { System.out.println(e.getMessage()); };

   }      
   
    public static void readLinesWithBuffProd(String fich,CatProdutos prod) {
      BufferedReader inStream = null; 
      String linha = null;
      Produto p;
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ){
                              prod.insereProduto(linha);
                            }
      }
      catch(IOException e) 
          { System.out.println(e.getMessage());  };
        
   }
   
    public static int readLinesWithBuffVendas(String fich,CatClientes cli,CatProdutos prod,Facturacao f,Filial fil[]) {
      BufferedReader inStream = null; 
      String linha = null;
      Venda v;
      int total=0;
      try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ){
                v = parseLinhaVenda(linha,cli,prod);
                if(v!=null){
                    f.insereVenda(v);
                    fil[v.getFilial()-1].insereVenda(v);
                    total++;
                }
            }
      }
      catch(IOException e) 
          { System.out.println(e.getMessage());  };
      return total;
   }
   
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
   
   /* public static ArrayList<Produto> parseAllLinhasProd(ArrayList<String> linhas){
      
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
   
   public static ArrayList<Venda> parseAllLinhas(ArrayList<String> linhas,CatClientes cli,CatProdutos prod){
      
      ArrayList<Venda> listV = new ArrayList<Venda>();
     
      for(String linha: linhas){
       
          Venda v = parseLinhaVenda(linha,cli,prod);
          if(v!=null)
            listV.add(listV.size(),v);
      }
      
      return listV;
   }*/
   
   /*public static HashSet<Venda> parseAllLinhasToSet(ArrayList<String> linhas){
      
      HashSet<Venda> listV = new HashSet<Venda>();
      
      for(String linha: linhas){
          listV.add(parseLinhaVenda(linha));
      }
      
      return listV;
   }
   */
   /*Consultas*/
   /*
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
   */
}
