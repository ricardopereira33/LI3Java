import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Pagina implements Serializable{

    private List<String> elementos;
    
    public Pagina(List<String> elementos){
        this.elementos = elementos; 
    }
    
    public List<String> getElementos(){
        return elementos;
    }
    
    public void setElementos(List<String> elementos){
        this.elementos = elementos;
    }
    
    public void addElemento(String elemento){
        if(elementos == null){
            elementos = new ArrayList<String>();
        } 
        elementos.add(elemento);
    }
}