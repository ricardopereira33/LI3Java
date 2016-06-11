import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Pagina implements Serializable{

    private List<String> elementos;
    
    /**
     * Construtor de uma página.
     * @param elementos
     */
    public Pagina(List<String> elementos){
        this.elementos = elementos; 
    }
    
    /**
     * Função que retorna os elementos da página.
     * @return
     */
    public List<String> getElementos(){
        return elementos;
    }
    
    /**
     * Função que define os elementos da página.
     * @param elementos
     */
    public void setElementos(List<String> elementos){
        this.elementos = elementos;
    }
    
    /**
     * Função que adiciona um elemento a uma página.
     * @param elemento
     */
    public void addElemento(String elemento){
        if(elementos == null){
            elementos = new ArrayList<String>();
        } 
        elementos.add(elemento);
    }
}