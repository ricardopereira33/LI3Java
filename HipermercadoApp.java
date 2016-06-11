import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collection;
import java.util.List;

public class HipermercadoApp{
    
   private static Hipermercado hipermercado = null;
   private static Menu menu_principal, menu_queries, menu_leitura, menu_consultas;
   private static MenuPaginas menu_paginas;
   
   private HipermercadoApp() {}
   
   /**
    * Função principal de toda a aplicação.
    * @param args
    */
   
   public static void startApp(){
       carregarMenus();
       apresentarMenu();
       if(hipermercado != null)
           hipermercado.limpar();
   }
   
   /**
    * Função responsável por carregar os Menus.
    */
   private static void carregarMenus(){
       String[] menu0 = {"Carregar dados",
                         "Consultas Estatísticas",
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
       String[] menu3 = {"Número total de compras por mês",
                         "Facturação por mês",
                         "Número de clientes que compraram em cada mês"};
       
       menu_principal = new Menu(menu0,0);
       menu_queries = new Menu(menu1,1);
       menu_leitura = new Menu(menu2,2);
       menu_consultas = new Menu(menu3,3);
   }
   
   /**
    * Função responsável por aprensentar o Menu Principal.
    */
   private static void apresentarMenu(){
       do{
           menu_principal.executa();
           switch(menu_principal.getOpcao()){
               case 1: carregarInformacao();
                       break;
               case 2: consultasEstatisticas();
                       break;
               case 3: abrirMenu();
                       break;
               case 4: gravarDados();
                       break;
           }
       }while(menu_principal.getOpcao()!=0);
   }
   
   /**
    * Função responsável por aprensentar as consultas estatísticas.
    */
   private static void consultasEstatisticas(){
       if(hipermercado == null ||hipermercado.isEmpty()){
            System.out.print("Não existem dados, por favor use a opção \"Carregar dados\"!");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
       }
       else{
           do{
               menu_consultas.executa();
               switch(menu_consultas.getOpcao()){
                   case 1: imprimeEstatisticasComprasMes();
                           break;
                   case 2: imprimeEstatisticasFacturacao();
                           break;
                   case 3: imprimeEstatisticasClientesCompras();
                           break;
               }
           }while(menu_consultas.getOpcao()!=0);
       }
   }
   
   /**
    * Função responsável por aprensentar, se possível, o Menu das Queries.
    */
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
   
   /**
    * Função responsável por apresentar o Menu de carregamento de dados.
    */
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
           if(menu_leitura.getOpcao()!=0 && hipermercado.getCatClientes()!=null && !hipermercado.isEmpty()) ready=1;
       }while(menu_leitura.getOpcao()!=0 && ready==0);
   }
   
   /**
    * Função que cria uma nova classe Hipermercado.
    */
   private static void criaNovoHiper(){
       if(hipermercado!=null)hipermercado.limpar();
       hipermercado = new Hipermercado();
    }
   
   /**
     * Fucção que imprime as consultas estatisticas após leitura de ficheiro.
     * @param n
     * @param ficheiro
     */
   private static void imprimeEstatisticasLeitura(DadosEstatisticos n, String ficheiro){
       System.out.println(n.toString());
   }
   
   /**
    * Função que imprimie as consultas estatísticas - Compras por mês.
    */
   private static void imprimeEstatisticasComprasMes(){
       for(int i=0;i<50;i++) System.out.println(); //limpar
       System.out.println("|   Número total de compras por mês    |");
       for(int i=0; i<12;i++){
           try{
               System.out.printf("|     Mês %2d |       %6d            | \n",i+1,hipermercado.getNumVendNumCliMes(i).getPrimeiro());
            }
            catch(Exception e){e.getMessage();}
       }
       
       Scanner is = new Scanner(System.in);
       System.out.println();
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
   }
   
   /**
    * Função que imprime as consultas estatísticas - Facturação total por mês.
    */
   private static void imprimeEstatisticasFacturacao(){
       System.out.println("|   Facturação total por mês   |");
       double lista1[]= new double[12];
       double lista2[]= new double[12];
       double lista3[]= new double[12];
       for(int i=0;i<12;i++){
           lista1[i]=0;
           lista2[i]=0;
           lista3[i]=0;
       }
       
       for(int i=0; i<3;i++){
           switch(i){ 
               case 0:  hipermercado.getFactTotalMes(lista1,i);break;
               case 1:  hipermercado.getFactTotalMes(lista2,i);break;
               case 2:  hipermercado.getFactTotalMes(lista3,i);break;
           }
       }
       
       System.out.printf("|  Mês |     Filial 1      |     Filial 2      |     Filial 3      |     Total      |\n");
       for(int i=0;i<12;i++){
           System.out.printf("|  %2d  | %f | %f | %f | %f |\n",i+1,lista1[i],lista2[i],lista3[i],lista1[i]+lista2[i]+lista3[i]);
       }
       
       Scanner is = new Scanner(System.in);
       System.out.println();
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
   }
   
   /** 
     * Função que imprime as consultas estatisticas dos dados actuais do Hipermercado.
     *  
     */
   private static void imprimeEstatisticasClientesCompras(){
       for(int i=0;i<50;i++) System.out.println(); //limpar
       System.out.println("|   Número de clientes que compraram em cada mês   |");
       for(int i=0; i<12;i++){
           Set<String> cli = new HashSet<>();
           for(int j=0; j<3;j++){
               hipermercado.getCliMes(cli,i,j);
           }
           System.out.printf("|     Mês %2d |                %7d              |\n",i+1,cli.size());
       }
       
       Scanner is = new Scanner(System.in);
       System.out.println();
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
   }
   
   /**
    * Responsável pela leitura dos ficheiros default.
    */
   private static void leituraNormal(){
       for(int i=0;i<50;i++) System.out.println(); //limpar
       criaNovoHiper();
       DadosEstatisticos n = hipermercado.carregaDados("../Clientes.txt","../Produtos.txt","../Vendas_1M.txt");
       imprimeEstatisticasLeitura(n,"Vendas_1M.txt");
       Scanner is = new Scanner(System.in);
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
   }
   
   /**
    * Responsável pela leitura de ficheiros opcionais.
    */
   private static void leituraOpcional(){
       for(int i=0;i<50;i++) System.out.println(); //limpar
       Scanner is = new Scanner(System.in);
       criaNovoHiper();
       System.out.print("Ficheiro de Clientes: ");
       String ficheiro_clientes = is.nextLine();
       System.out.print("Ficheiro de Produtos: ");
       String ficheiro_produtos = is.nextLine();
       System.out.print("Ficheiro de Vendas: ");
       String ficheiro_vendas = is.nextLine();
      
       DadosEstatisticos n = hipermercado.carregaDados(ficheiro_clientes,ficheiro_produtos,ficheiro_vendas);
       imprimeEstatisticasLeitura(n,ficheiro_vendas);
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
   }
   
   // querie 1 
   /**
    * Responsável por apresentar uma lista ordenada alfabeticamente dos produtos não comprados.
    */
   private static void produtosNaoComprados(){
       Scanner is = new Scanner(System.in);
       Crono.start();
       Collection<String> produtosNaoComprados = hipermercado.getProdsNaoComp();
       Crono.stop();
       
       List<String> lista = new ArrayList<String>();
       
       for(String elemento: produtosNaoComprados){
            String linha = "                                     " + elemento;
            lista.add(linha);
       }
       ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
       menu_paginas = new MenuPaginas(conjunto,0,null);
       
       System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
       menu_paginas.executa();
      
   }
   
   // querie 2
   /**
    * Responsável por apresentar informações relativas às vendas de um determinado mês.
    */
   private static void vendasMes(){
       int mes = inputMes();
       if(mes == 0); // sair
       else{
           Scanner is = new Scanner(System.in);
           try{
               Crono.start();
               ParIntInt info = hipermercado.getNumVendNumCliMes(mes-1);
               Crono.stop();
               for(int i=0;i<50;i++) System.out.println(); //limpar
               System.out.println("\n****************** Informações sobre as vendas no mês " + mes + " ********************\n");
               System.out.println("         Número de vendas realizadas: " + info.getPrimeiro());
               System.out.println("         Número de clientes distintos que as fizeram: " + info.getSegundo());
               System.out.println("\n****************************************************************************");
               System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
               System.out.print("Pressione ENTER para continuar!");
               is.nextLine();
               is.close();
           }
           catch(MesInvalidoException e){
               System.out.println(e.getMessage());
           }
       }
   }
   
   // querie 3
   /**
    * Responsável por apresentar informações relativas a cada mês sobre as compras de um determinado cliente.
    */
   private static void comprasCliente(){
       String cliente = inputCliente();
       if(!cliente.equals("0")){
           Scanner is =  new Scanner(System.in);
           int mes = 1;
           try{
               Crono.start();
               List<TriploIntIntDouble> meses = hipermercado.getNumCompNumProdTot(cliente);
               Crono.stop();
               for(int i=0;i<50;i++) System.out.println(); //limpar
               System.out.println("\n****************** Informações sobre as compras do cliente " + cliente + " ********************\n");
               System.out.println("|  Mês  |      #Compras       |      #Produtos      |      Total gasto     |\n|_______|_____________________|_____________________|______________________|");       
           
               for(TriploIntIntDouble i: meses){  
                   System.out.printf("    %2d          %5d                 %5d                 %f            \n",mes,i.getPrimeiro(),i.getSegundo(),i.getTerceiro());
                   mes++;
                }
               System.out.println("\n****************************************************************************");
               System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
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
   
   // querie 4
   /**
    * Responsável por apresentar as informações sobre as compras de um determinado produto durante o ano.
    */
   private static void vendasProduto(){
       String produto = inputProduto();
       if(!produto.equals("0")){
           Scanner is = new Scanner(System.in);
           int mes = 1;
           try{
               Crono.start();
               List<TriploIntIntDouble> meses = hipermercado.getNumCompNumCliTot(produto);
               Crono.stop();
               for(int i=0;i<50;i++) System.out.println(); //limpar
               System.out.println("\n****************** Informações sobre as vendas do produto " + produto + " ********************\n");
               System.out.println("|  Mês  |      #Vendas       |      #Clientes      |      Total facturado     |\n|_______|____________________|_____________________|__________________________|");       
           
               for(TriploIntIntDouble i: meses){  
                   System.out.printf("    %2d          %5d                 %5d                 %f            \n",mes,i.getPrimeiro(),i.getSegundo(),i.getTerceiro());
                   mes++;
                }
               System.out.println("\n****************************************************************************");
               System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
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
   
   // querie 5
   /**
    * Responsável por apresentar os produtos mais comprados por um determinado cliente.
    */
   private static void produtosMaisCompradosCliente(){
       String cliente = inputCliente();
       if(!cliente.equals("0")){
           try{
               Scanner is = new Scanner(System.in);
               Crono.start();
               Collection<ParStringInt> maisComprados = hipermercado.getProdsMaisComprados(cliente);
               Crono.stop();
               List<String> lista = new ArrayList<String>();
                      String cabecalho = "|             Produto             |             Quantidade             |\n|_________________________________|____________________________________|";
               String linha;
               for(ParStringInt par: maisComprados){
                    linha = "              " + par.getString() + "                                " + par.getNumero();
                    lista.add(linha);
               }
               ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
               menu_paginas = new MenuPaginas(conjunto,0,cabecalho);
               System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
               System.out.print("Pressione ENTER para continuar!");
               is.nextLine();
               menu_paginas.executa();
           }
           catch(ClienteInexistenteException e){
                System.out.println(e.getMessage());
                comprasCliente();
           }    
       }
   }
   
   // querie 6
   /**
    * Responsável por apresentar os N produtos mais vendidos.
    */
   private static void produtosMaisVendidos(){
       int numero = inputNumero();
       Scanner is = new Scanner(System.in);
       Crono.start();
       Collection<TriploStringIntInt> produtos = hipermercado.getProdsMaisVend(numero);
       Crono.stop();
       List<String> lista = new ArrayList<String>();
       String linha;
       String cabecalho = "|          Produto          |      Quantidade      |        #Clientes        |\n|___________________________|______________________|_______________________|";
       for(TriploStringIntInt elemento: produtos){
           linha = "            " + elemento.getPrimeiro() + "                   " + elemento.getSegundo() + "                    " + elemento.getTerceiro();
           lista.add(linha);
       }
       ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
       menu_paginas = new MenuPaginas(conjunto,0,cabecalho);
       System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
       menu_paginas.executa();
   }
   
   // querie 7
   /**
    * Responsável por determinar para cada filial os três maiores compradores.
    */
   private static void produtosFilial(){
       Scanner is = new Scanner(System.in);
       Crono.start();
       List<List<ParStringDouble>> lista = hipermercado.getMaioresComp();
       Crono.stop();
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
       System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
       is.close();
   }
   
   // querie 8
   /**
    * Responsável por apresentar os N clientes que compraram mais produtos diferentes.
    */
   private static void clientesComMaisProdutosDiferentes(){
       int numero = inputNumero(); Scanner is = new Scanner(System.in);
       Crono.start();
       Collection<ParStringInt> clientes = hipermercado.getCliMaisCompDif(numero);
       Crono.stop();
       List<String> lista = new ArrayList<String>();
       String linha;
       String cabecalho = "|             Cliente             |             Quantidade             |\n|_________________________________|____________________________________|";
       for(ParStringInt elemento: clientes){
           linha = "              " + elemento.getString() + "                                 " + elemento.getNumero() + "             ";
           lista.add(linha);
       }
       ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
       menu_paginas = new MenuPaginas(conjunto,0,cabecalho);
       System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
       menu_paginas.executa();
   }
   
   // querie 9
   /**
    * Responsável por apresentar os N clientes que mais compraram um determinado produto.
    */
   private static void clientesCompraramProduto(){
       String produto = inputProduto();
       if(!produto.equals("0")){
           int numero = inputNumero();
           try{
               Scanner is = new Scanner(System.in);
               Crono.start();
               Collection<ParStringDouble> clientes = hipermercado.getCliMaisCompProd(produto,numero);
               Crono.stop();
               List<String> lista = new ArrayList<String>();
               String cabecalho = "|             Cliente             |             Valor gasto             |\n|_________________________________|_____________________________________|";
               String linha;
               for(ParStringDouble par: clientes){
                    linha = "              " + par.getString() + "                              " + par.getNumero() + "             ";
                    lista.add(linha);
               }
               ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
               menu_paginas = new MenuPaginas(conjunto,0,cabecalho);
               System.out.println("Tempo demorado: " + Crono.print() + " segundos.");
               System.out.print("Pressione ENTER para continuar!");
               is.nextLine();
               menu_paginas.executa();
           }
           catch(ProdutoInexistenteException e){
                System.out.println(e.getMessage());
                clientesCompraramProduto();
           }    
       }
       
   }
   
   /**
    * Função responsável por fazer o carregamento de dados através de um dado ficheiro.
    */
   private static void carregarDados(){       
       criaNovoHiper();
       String fich;
       Scanner is = new Scanner(System.in);
       System.out.print("Nome do ficheiro: ");
       fich = is.nextLine();
       try {
            System.out.println("A carregar informação... Este processo pode ser demorado! ");
            hipermercado = Hipermercado.leObj(fich);
            System.out.println("Concluído!");
        }
        catch (IOException e) {
            hipermercado = new Hipermercado();
            System.out.print("Não consegui ler os dados!\nErro de leitura.");
        }
        catch (ClassNotFoundException e) {
            hipermercado = new Hipermercado();
            System.out.print("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
            hipermercado = new Hipermercado();
            System.out.print("Não consegui ler os dados!\nErro de formato.");
        }
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
       is.close();
   }
   
   /**
    * Função responsável por guardar o estado do Hipermercado num ficheiro binário.
    */
   private static void gravarDados(){
        if(hipermercado == null || hipermercado.isEmpty()){
            System.out.print("Não existem dados, por favor use a opção \"Carregar dados\"!");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
       }
       else{
           Scanner is = new Scanner(System.in);
           System.out.print("Nome do ficheiro: ");
           String fich = is.nextLine();
           try {
               System.out.println("A gravar... Este processo pode ser demorado! ");
               hipermercado.gravaObj(fich);
               System.out.println("Concluído!");
           }
           catch (IOException e) {
               System.out.print("Não consegui gravar os dados!");
           }
           System.out.print("Pressione ENTER para continuar!");
           is.nextLine();
           is.close();
       }
    }
    
   /**
    * Função responsável pelo input do mês.
    * @return int
    */
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
   
   /**
    * Função responsável pelo input de um número.
    * @return int
    */
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
   
   /**
    * Função responsável pelo input de um Cliente.
    * @return String
    */
   private static String inputCliente(){
       String cliente;
       System.out.print("Cliente: ");
       Scanner is = new Scanner(System.in);
       cliente = is.nextLine();
       is.close();
       return cliente;
   }
   
   /**
    * Função responsável pelo input de um Produto.
    * @return String
    */
   private static String inputProduto(){
       String produto;
       System.out.print("Produto: ");
       Scanner is = new Scanner(System.in);
       produto = is.nextLine();
       is.close();
       return produto;
   }
}
