import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.io.Serializable;

public class ConjuntoPaginas implements Serializable{
    
    private List<Pagina> paginas;
    private int tamanho;
    private int totalPaginas = 0;
    
    public ConjuntoPaginas(Collection<String> lista, int tamanho){
        int indice = 0;
        paginas = new ArrayList<Pagina>();
        Pagina pagina = new Pagina(null);
        
        paginas.add(pagina);
        for(String elemento: lista){
            if(paginas.get(indice).getElementos() == null || paginas.get(indice).getElementos().size()<tamanho){
                paginas.get(indice).addElemento(elemento);
            }
            else{
                indice++;
                pagina = new Pagina(null);
                paginas.add(pagina);
                paginas.get(indice).addElemento(elemento);
            }   
        }
        
        this.totalPaginas = indice+1;
    }
    
    public Pagina getPagina(int pagina){
        if(pagina <= totalPaginas && pagina>0)
            return paginas.get(pagina-1);
        else return null;
    }
    
    public int getTotalPaginas(){
        return totalPaginas;
    }

}
