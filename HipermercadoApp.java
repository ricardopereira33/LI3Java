import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collection;
import java.util.List;

public class HipermercadoApp{
    
   private static Hipermercado hipermercado = null;
   private static Menu menu_principal, menu_queries, menu_leitura;
   private static MenuPaginas menu_paginas;
   
   private HipermercadoApp() {}
   
   public static void main(String[] args){
       carregarMenus();
       apresentarMenu();
       
       /*System.out.println("---------------------------");
       System.out.println("Nª total de produtos diferentes comprados:" + hipermercado.numeroProdutosDif());
       System.out.println("Nª total 0 :" + hipermercado.zeros());
       System.out.println("Nª total :" + hipermercado.facturacaoTotal());
       System.out.println("");
       System.out.println("----------------------------");*/
       
       if(hipermercado != null) hipermercado.limpar();
   } 
   
   private static void carregarMenus(){
       String[] menu0 = {"Carregar dados",
                         "Menu",
                         "Gravar dados"};
       String[] menu1 = {"Produtos nunca comprados",
                         "Vendas num determinado mês",
                         "Informações sobre as compras de um determinado cliente",
                         "Informações sobre as vendas de um determinado produto",
                         "Produtos mais comprados por um determinado cliente",
                         "Produtos mais vendidos",
                         "Produtos em que cada uma das filiais facturou mais",
                         "N clientes que compraram mais produtos diferentes",
                         "Clientes que compraram mais vezes um determinado produto"
                         };
       String[] menu2 = {"Ler ficheiros default",
                         "Escolher ficheiros a ler",
                         "Carregar dados já existentes"}; 
       
       menu_principal = new Menu(menu0,0);
       menu_queries = new Menu(menu1,1);
       menu_leitura = new Menu(menu2,2);
   }
   
   private static void apresentarMenu(){
       do{
           menu_principal.executa();
           switch(menu_principal.getOpcao()){
               case 1: carregarInformacao();
                       break;
               case 2: abrirMenu();
                       break;
               case 3: gravarDados();
                       break;
           }
       }while(menu_principal.getOpcao()!=0);
   }
   
   private static void abrirMenu(){
       
       if(hipermercado == null ||hipermercado.isEmpty()){
            System.out.print("Não existem dados, por favor use a opção \"Carregar dados\"!");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
       }
       else{
           do{
               menu_queries.executa();
               switch(menu_queries.getOpcao()){
                   case 1: produtosNaoComprados();
                           break;
                   case 2: vendasMes(); 
                           break;
                   case 3: comprasCliente();
                           break;
                   case 4: vendasProduto();
                           break;
                   case 5: produtosMaisCompradosCliente();
                           break;
                   case 6: produtosMaisVendidos();
                           break;
                   case 7: produtosFilial();
                           break;
                   case 8: clientesComMaisProdutosDiferentes();
                           break;
                   case 9: clientesCompraramProduto();
                           break;
               }
           }while(menu_queries.getOpcao()!=0);
       }
   }
   
   private static void carregarInformacao(){
       int ready = 0;
       do{
           menu_leitura.executa();
           switch(menu_leitura.getOpcao()){
               case 1: leituraNormal();
                       break;
               case 2: leituraOpcional();
                       break;
               case 3: carregarDados();
           }
           if(hipermercado.getCatClientes()!=null && !hipermercado.isEmpty()) ready=1;
       }while(menu_leitura.getOpcao()!=0 && ready==0);
   }
   
   private static void criaNovoHiper(){
       if(hipermercado!=null)hipermercado.limpar();
       hipermercado = new Hipermercado();
    }
   
   private static void leituraNormal(){
       criaNovoHiper();
       hipermercado.carregaDados("../Clientes.txt","../Produtos.txt","../Vendas_1M.txt");
       Scanner is = new Scanner(System.in);
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
   }
   
   private static void leituraOpcional(){
       Scanner is = new Scanner(System.in);
       criaNovoHiper();
       System.out.print("Ficheiro de Clientes: ");
       String ficheiro_clientes = is.nextLine();
       System.out.print("Ficheiro de Produtos: ");
       String ficheiro_produtos = is.nextLine();
       System.out.print("Ficheiro de Vendas: ");
       String ficheiro_vendas = is.nextLine();
      
       hipermercado.carregaDados(ficheiro_clientes,ficheiro_produtos,ficheiro_vendas);
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
   }
   
   /* querie 1 */
   private static void produtosNaoComprados(){
       Collection<String> produtosNaoComprados = hipermercado.getProdsNaoComp();
       List<String> lista = new ArrayList<String>();
       
       for(String elemento: produtosNaoComprados){
            String linha = "                                     " + elemento;
            lista.add(linha);
       }
       ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
       menu_paginas = new MenuPaginas(conjunto,0,null);
       menu_paginas.executa();
      
   }
   
   /* querie 2 */
   private static void vendasMes(){
       int mes = inputMes();
       if(mes == 0); // sair
       else{
           Scanner is = new Scanner(System.in);
           ParIntInt info = hipermercado.getNumVendNumCliMes(mes-1);
           for(int i=0;i<50;i++) System.out.println(); //limpar
           System.out.println("\n****************** Informações sobre as vendas no mês " + mes + " ********************\n");
           System.out.println("         Número de vendas realizadas: " + info.getPrimeiro());
           System.out.println("         Número de clientes distintos que as fizeram: " + info.getSegundo());
           System.out.println("\n****************************************************************************");
           System.out.print("Pressione ENTER para continuar!");
           is.nextLine();
           is.close();
       }
   }
   
   /* querie 3 */
   private static void comprasCliente(){
       String cliente = inputCliente();
       if(!cliente.equals("0")){
           Scanner is =  new Scanner(System.in);
           int mes = 1;
           try{
               List<TriploIntIntDouble> meses = hipermercado.getNumCompNumProdTot(cliente);
               for(int i=0;i<50;i++) System.out.println(); //limpar
               System.out.println("\n****************** Informações sobre as compras do cliente " + cliente + " ********************\n");
               System.out.println("|  Mês  |      #Compras       |      #Produtos      |      Total gasto     |\n|_______|_____________________|_____________________|______________________|");       
           
               for(TriploIntIntDouble i: meses){  
                   System.out.printf("    %2d          %5d                 %5d                 %f            \n",mes,i.getPrimeiro(),i.getSegundo(),i.getTerceiro());
                   mes++;
                }
               System.out.println("\n****************************************************************************");
               System.out.print("Pressione ENTER para continuar!");
               is.nextLine();
               is.close();
           }
           catch(ClienteInexistenteException e){
                System.out.println(e.getMessage());
                comprasCliente();
           }    
       }
   }
   
   /* querie 4 */
   private static void vendasProduto(){
       String produto = inputProduto();
       if(!produto.equals("0")){
           Scanner is = new Scanner(System.in);
           int mes = 1;
           try{
               List<TriploIntIntDouble> meses = hipermercado.getNumCompNumCliTot(produto);
               for(int i=0;i<50;i++) System.out.println(); //limpar
               System.out.println("\n****************** Informações sobre as vendas do produto " + produto + " ********************\n");
               System.out.println("|  Mês  |      #Vendas       |      #Clientes      |      Total facturado     |\n|_______|____________________|_____________________|__________________________|");       
           
               for(TriploIntIntDouble i: meses){  
                   System.out.printf("    %2d          %5d                 %5d                 %f            \n",mes,i.getPrimeiro(),i.getSegundo(),i.getTerceiro());
                   mes++;
                }
               System.out.println("\n****************************************************************************");
               System.out.print("Pressione ENTER para continuar!");
               is.nextLine();
               is.close();
           }
           catch(ProdutoInexistenteException e){
                System.out.println(e.getMessage());
                vendasProduto();
           } 
       }
   }
   
   /* querie 5 */
   private static void produtosMaisCompradosCliente(){
       String cliente = inputCliente();
       if(!cliente.equals("0")){
           try{
               Collection<ParStringInt> maisComprados = hipermercado.getProdsMaisComprados(cliente);
               List<String> lista = new ArrayList<String>();
                      String cabecalho = "|             Produto             |             Quantidade             |\n|_________________________________|____________________________________|";
               String linha;
               for(ParStringInt par: maisComprados){
                    linha = "              " + par.getString() + "                                " + par.getNumero();
                    lista.add(linha);
               }
               ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
               menu_paginas = new MenuPaginas(conjunto,0,cabecalho);
               menu_paginas.executa();
           }
           catch(ClienteInexistenteException e){
                System.out.println(e.getMessage());
                comprasCliente();
           }    
       }
   }
   
   /* querie 6 */ 
   private static void produtosMaisVendidos(){
       int numero = inputNumero();
       Collection<TriploStringIntInt> produtos = hipermercado.getProdsMaisVend(numero);
       List<String> lista = new ArrayList<String>();
       String linha;
       String cabecalho = "|          Produto          |      Quantidade      |        #Clientes        |\n|___________________________|______________________|_______________________|";
       for(TriploStringIntInt elemento: produtos){
           linha = "            " + elemento.getPrimeiro() + "                   " + elemento.getSegundo() + "                    " + elemento.getTerceiro();
           lista.add(linha);
       }
       ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
       menu_paginas = new MenuPaginas(conjunto,0,cabecalho);
       menu_paginas.executa();
   }
   
   /* querie 7 */
   private static void produtosFilial(){
       Scanner is = new Scanner(System.in);
       List<List<ParStringDouble>> lista = hipermercado.getMaioresComp();
       int filial = 0;
       
       for(int i=0;i<50;i++) System.out.println(); //limpar
       System.out.println("\n************************** Maiores compradores em cada filial ****************************\n");
       System.out.println("|  Filial  |                                  Compradores                                |");
       System.out.println("|__________|_____________________________________________________________________________|");
           
       for(List<ParStringDouble> filiais: lista){
           filial++;      
           System.out.print("|    " + filial + "     ");
           for(ParStringDouble elemento: filiais){
               System.out.printf("| %5s -> %f ",elemento.getString(),elemento.getNumero());
           }
           System.out.println("|");
       }
       
       System.out.println("\n********************************************************************************************");
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
       is.close();
   }
   
   /* querie 8 */
   private static void clientesComMaisProdutosDiferentes(){
       int numero = inputNumero();
       Collection<ParStringInt> clientes = hipermercado.getCliMaisCompDif(numero);
       List<String> lista = new ArrayList<String>();
       String linha;
       String cabecalho = "|             Cliente             |             Quantidade             |\n|_________________________________|____________________________________|";
       for(ParStringInt elemento: clientes){
           linha = "              " + elemento.getString() + "                                 " + elemento.getNumero() + "             ";
           lista.add(linha);
       }
       ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
       menu_paginas = new MenuPaginas(conjunto,0,cabecalho);
       menu_paginas.executa();
   }
   
   /* querie 9 */
   private static void clientesCompraramProduto(){
       String produto = inputProduto();
       if(!produto.equals("0")){
           int numero = inputNumero();
           try{
               Collection<ParStringDouble> clientes = hipermercado.getCliMaisCompProd(produto,numero);
               List<String> lista = new ArrayList<String>();
               String cabecalho = "|             Cliente             |             Valor gasto             |\n|_________________________________|_____________________________________|";
               String linha;
               for(ParStringDouble par: clientes){
                    linha = "              " + par.getString() + "                              " + par.getNumero() + "             ";
                    lista.add(linha);
               }
               ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
               menu_paginas = new MenuPaginas(conjunto,0,cabecalho);
               menu_paginas.executa();
           }
           catch(ProdutoInexistenteException e){
                System.out.println(e.getMessage());
                clientesCompraramProduto();
           }    
       }
       
   }
   
   private static void carregarDados(){       
       criaNovoHiper();
       String fich = "hipermercado.dat";
       Scanner is = new Scanner(System.in);
       try {
            hipermercado = Hipermercado.leObj(fich);
        }
        catch (IOException e) {
            hipermercado = new Hipermercado();
            System.out.println("Não consegui ler os dados!\nErro de leitura.");
        }
        catch (ClassNotFoundException e) {
            hipermercado = new Hipermercado();
            System.out.println("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
            hipermercado = new Hipermercado();
            System.out.println("Não consegui ler os dados!\nErro de formato.");
        }
       is.nextLine();
       is.close();
   }
   
   private static void gravarDados(){
        if(hipermercado == null || hipermercado.isEmpty()){
            System.out.print("Não existem dados, por favor use a opção \"Carregar dados\"!");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
       }
       else{
           String fich = "hipermercado.dat";
           Scanner is = new Scanner(System.in);
           try {
               hipermercado.gravaObj(fich);
           }
           catch (IOException e) {
               System.out.println("Não consegui gravar os dados!");
            }
           is.nextLine();
           is.close();
       }
    }
    
   // INPUTS
   private static int inputMes(){
        int mes;
        System.out.print("Mês: ");
        Scanner is = new Scanner(System.in);
        try{
            mes = is.nextInt();
            if(!(mes>=0 && mes<=12)) throw new InputMismatchException();
        }
        catch(InputMismatchException e){
            System.out.println("Mês inválido! Por favor introduza um mês válido (1-12).");
            mes = inputMes();
        }
        is.close();
        return mes;
   }
   
   private static int inputNumero(){
        int numero;
        System.out.print("Número: ");
        Scanner is = new Scanner(System.in);
        try{
            numero = is.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Número inválido! Por favor introduza um número válido!");
            numero = inputNumero();
        }
        is.close();
        return numero;
   }
   
   private static String inputCliente(){
       String cliente;
       System.out.print("Cliente: ");
       Scanner is = new Scanner(System.in);
       cliente = is.nextLine();
       is.close();
       return cliente;
   }
   
   private static String inputProduto(){
       String produto;
       System.out.print("Produto: ");
       Scanner is = new Scanner(System.in);
       produto = is.nextLine();
       is.close();
       return produto;
   }
}
