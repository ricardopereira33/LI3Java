import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collection;
import java.util.List;

public class HipermercadoApp{
    
   private static Hipermercado hipermercado;
   private static Menu menu_principal, menu_queries, menu_leitura;
   private static MenuPaginas menu_paginas;
   
   public static void main(String[] args){
       hipermercado = new Hipermercado();
       carregarMenus();
       apresentarMenu();
       
       /*System.out.println("---------------------------");
       System.out.println("Nª total de produtos diferentes comprados:" + hipermercado.numeroProdutosDif());
       System.out.println("Nª total 0 :" + hipermercado.zeros());
       System.out.println("Nª total :" + hipermercado.facturacaoTotal());
       System.out.println("");
       System.out.println("----------------------------");*/
       
       hipermercado.limpar();
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
   
   private static void carregarInformacao(){
       do{
           menu_leitura.executa();
           switch(menu_leitura.getOpcao()){
               case 1: leituraNormal();
                       break;
               case 2: leituraOpcional();
                       break;
               case 3: carregarDados();
           }
       }while(menu_leitura.getOpcao()!=0);
   }
   
   private static void leituraNormal(){
       // Se já tiver merdas temos de limpar
       hipermercado.carregaDados("../Clientes.txt","../Produtos.txt","../Vendas_1M.txt");
       Scanner is = new Scanner(System.in);
       System.out.print("Pressione ENTER para continuar!");
       is.nextLine();
   }
   
   private static void leituraOpcional(){
       Scanner is = new Scanner(System.in);
       // Se já tiver merdas temos de limpar
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
       Collection<String> lista = hipermercado.getProdsNaoComp();
       ConjuntoPaginas conjunto = new ConjuntoPaginas(lista,20);
       
       menu_paginas = new MenuPaginas(conjunto,0);
       menu_paginas.executa();
      
   }
   
   /* querie 2 */
   private static void vendasMes(){
       int mes = inputMes();
       if(mes == 0); // sair
       else{
           ParIntInt info = hipermercado.getNumVendNumCliMes(mes);
           System.out.println(info);
       }
   }
   
   /* querie 3 */
   private static void comprasCliente(){
       String cliente;
       //Scanner is = new Scanner(System.in);
       /*try{
           cliente = inputCliente();
       }
       catch(Exception e){
           System.out.println(e.getMessage());
           is.nextLine();
       }*/
       cliente = inputCliente();
       List<TriploIntIntDouble> lista = hipermercado.getNumCompNumProdTot(cliente);
       for(TriploIntIntDouble elemento: lista)
            System.out.println(elemento);
   }
   
   private static void produtosFilial(){
       // fazer 
   }
   
   private static void clientesCompraramProduto(){
       String produto;
       Scanner is = new Scanner(System.in);
       try{
           produto = inputProduto();
       }
       catch(Exception e){
           System.out.println(e.getMessage());
           is.nextLine();
       }
   }
  
   private static void clientesComMaisProdutosDiferentes(){
       int numero = inputNumero();
   }
   
   private static void produtosMaisVendidos(){
       // fazer 
   }
   
   private static void produtosMaisCompradosCliente(){
       String cliente;
       Scanner is = new Scanner(System.in);
       try{
           cliente = inputCliente();
       }
       catch(Exception e){
           System.out.println(e.getMessage());
           is.nextLine();
       }
   }
   
   private static void vendasProduto(){
       String produto;
       Scanner is = new Scanner(System.in);
       try{
           produto = inputProduto();
       }
       catch(Exception e){
           System.out.println(e.getMessage());
           is.nextLine();
       }
   }
   
   private static void carregarDados(){
       String fich = "hipermercado.dat";
       
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
   }
   
   private static void gravarDados(){
       String fich = "hipermercado.dat";
       
       try {
            hipermercado.gravaObj(fich);
       }
        catch (IOException e) {
            System.out.println("Não consegui gravar os dados!");
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
   
   private static String inputCliente() /*throws ClienteInexistenteException*/{
       String cliente;
       System.out.print("Cliente: ");
       Scanner is = new Scanner(System.in);
       cliente = is.nextLine();
       if(hipermercado.getCatClientes().existeCliente(cliente))
            return cliente;
       else{
           return "A1234";
           //throw new ClienteInexistenteException("Este cliente não se encontra no Catálogo!");
       }
   }
   
   private static String inputProduto() throws ProdutoInexistenteException{
       String produto;
       System.out.print("Produto: ");
       Scanner is = new Scanner(System.in);
       produto = is.nextLine();
       throw new ProdutoInexistenteException("Este produto não se encontra no Catálogo!\n");
   }
}
