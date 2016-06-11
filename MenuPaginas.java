import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Serializable;
// IMPORTANTE: ESTÁ A USAR SCANNER!! 

public class MenuPaginas implements Serializable{
   
    private ConjuntoPaginas conjuntoPaginas;
    private String cabecalho = null;
    private int pagina;
    private int menu_type;
    
    /**
     * Construtor para objetos da classe Menu Páginas
     * @param conjuntoPaginas
     * @param type
     * @param cabecalho
     */
    public MenuPaginas(ConjuntoPaginas conjuntoPaginas, int type, String cabecalho){
        this.conjuntoPaginas = conjuntoPaginas;
        this.pagina = 1;
        this.menu_type = type;
        this.cabecalho = cabecalho;
    }

    /**
     * Função para executar o menu das páginas.
     */
    public void executa(){
        do {
            showPagina();
            this.pagina = lerPagina();
        }
        while(this.pagina != 0);
    }
    
    /**
     * Função para mostrar uma página.
     */
    private void showPagina() {
        clearConsole();
        
        if(menu_type == 0)
            System.out.println("\n****************************** Página " + pagina + " de " + conjuntoPaginas.getTotalPaginas() + " ******************************");
        if(cabecalho != null){
            System.out.println(cabecalho);
        }
        if(conjuntoPaginas.getPagina(pagina).getElementos()!=null)
            for(String elemento: conjuntoPaginas.getPagina(pagina).getElementos())
                System.out.println(elemento);
        
        if(menu_type == 0)
            System.out.println("                                                          0 - Sair");
        System.out.println("****************************************************************************");
    }
    
    /**
     * Função para limpar a consola.
     */
    private void clearConsole(){
        for(int i=0;i<50;i++) System.out.println();
    }
    
    /**
     * Função ler uma opção do menu.
     * @return int
     */
    private int lerPagina() {
        int p; 
        Scanner is = new Scanner(System.in);
        
        System.out.print("Página: ");
        try {
            pagina = is.nextInt();
        }
        catch (InputMismatchException e) { 
            pagina = -1;
        }
        
        if (pagina < 0 || pagina > conjuntoPaginas.getTotalPaginas()) {
            System.out.print("\nPágina Inválida!");
            is.nextLine();
            is.nextLine();
            pagina = lerPagina();
        }
        return pagina;
    }
    
    /**
     * Obter opção selecionada.
     * @return int
     */
    public int getPagina() {
        return this.pagina;
    }
}
