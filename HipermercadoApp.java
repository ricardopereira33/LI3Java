import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.io.IOException;

public class HipermercadoApp{
    
   private static Hipermercado hipermercado;
   private static Menu menu_principal;
   
   public static void main(String[] args){
       hipermercado = new Hipermercado();
       carregarMenus();
       apresentarMenu();
       /*hipermercado.limpar();*/
       System.out.println("---------------------------");
       System.out.println("Nª total de produtos diferentes comprados:" + hipermercado.numeroProdutosDif());
       System.out.println("Nª total 0 :" + hipermercado.zeros());
       System.out.println("Nª total :" + hipermercado.facturacaoTotal());
       System.out.println("");
       System.out.println("----------------------------");
   } 
   
   private static void carregarMenus(){
       String[] menu0 = {"Ler ficheiros",
                         "Carregar dados",
                         "Gravar dados"};
       menu_principal = new Menu(menu0);
   }
   
   private static void apresentarMenu(){
       do{
           menu_principal.executa();
           switch(menu_principal.getOpcao()){
               case 1: lerFicheiros();
                       break;
               case 2: carregarDados();
                       break;
               case 3: gravarDados();
                       break;
           }
       }while(menu_principal.getOpcao()!=0);
   }
   
   private static void lerFicheiros(){
       hipermercado.carregaDados();
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
}
