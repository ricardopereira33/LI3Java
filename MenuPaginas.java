import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

// IMPORTANTE: ESTÁ A USAR SCANNER!! 

public class MenuPaginas{
   
    private ConjuntoPaginas conjuntoPaginas;
    private int pagina;
    private int menu_type;
    
    public MenuPaginas(ConjuntoPaginas conjuntoPaginas, int type){
        this.conjuntoPaginas = conjuntoPaginas;
        this.pagina = 1;
        this.menu_type = type;
    }

    public void executa(){
        do {
            showPagina();
            this.pagina = lerPagina();
        }
        while(this.pagina != 0);
    }
    
    private void showPagina() {
        clearConsole();
        
        if(menu_type == 0)
            System.out.println("\n****************************** Página " + pagina + " de " + conjuntoPaginas.getTotalPaginas() + " ******************************");
            
        for(String elemento: conjuntoPaginas.getPagina(pagina).getElementos())
            System.out.println("                    " + elemento);
        
        if(menu_type == 0)
            System.out.println("                                                          0 - Sair");
        System.out.println("****************************************************************************");
    }
    
    private void clearConsole(){
        for(int i=0;i<50;i++) System.out.println();
    }
    
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
    
    public int getPagina() {
        return this.pagina;
    }
}
