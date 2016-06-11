import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Serializable;

public class Menu implements Serializable{
   
    private List<String> opcoes;
    private int op;
    private int menu_type;

    /**
     * Construtor para objetos da classe Menu
     */
    public Menu(String[] opcoes, int type){
        this.opcoes = new ArrayList<String>();
        for(String op: opcoes)
            this.opcoes.add(op);
        this.op = 0;
        this.menu_type = type;
    }

    /**
     * Função para executar o menu.
     */
    public void executa(){
        do {
            showMenu();
            this.op = lerOpcao();
        }
        while(this.op == -1);
    }
    
    /**
     * Função para mostrar o menu.
     */
    private void showMenu() {
        clearConsole();
        if(menu_type == 0)
            System.out.println("\n****************************** Menu Principal ******************************");
        else if(menu_type == 1)
            System.out.println("\n********************************* Queries *********************************");
        else if(menu_type == 2)
            System.out.println("\n******************************* Menu Leitura *******************************");
            
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print("   "+(i+1));
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        if(menu_type == 0)
            System.out.println("                                                          0 - Sair");
        else if(menu_type == 1 || menu_type == 2)
            System.out.println("                                                      0 - Anterior");
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
     * @return
     */
    private int lerOpcao() {
        int op; 
        Scanner is = new Scanner(System.in);
        
        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { 
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.print("\nOpção Inválida!");
            is.nextLine();
            is.nextLine();
            op = -1;
        }
        return op;
    }
    
    /**
     * Obter opção selecionada.
     * @return 
     */
    public int getOpcao() {
        return this.op;
    }
}
