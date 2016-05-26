import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

public class Filial implements Serializable{
    private List<Map<Cliente,InfoCliente>> clientes;
    
     public Filial(){
        this.clientes = new ArrayList<>(26);
        Map<Cliente,InfoCliente> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeMap<Cliente,InfoCliente>(new ComparatorCliente());
            this.clientes.add(i,arvore);
        }
    }
    
    public void insereVenda(Venda v){ 
       Cliente c = v.getCliente();
       int indice = calculaIndice(c.getNomeCliente().charAt(0));
       if(clientes.get(indice).containsKey(c))
           clientes.get(indice).get(c).insereInfoC(v);
       else{ 
           InfoCliente ic = new InfoCliente();
           ic.insereInfoC(v);
           clientes.get(indice).put(c,ic);
       }
    }
    
    //Limpar
    public void limpar (){
       this.clientes.clear();
    }
    
    /*Get's*/
    public List<Map<Cliente,InfoCliente>> getFilialClientes(){
      ArrayList<Map<Cliente,InfoCliente>> lista = new ArrayList<>(26);
      Map<Cliente,InfoCliente> arvore;
      int i;  
      
      for(i=0; i<26; i++){
         arvore = new TreeMap<Cliente,InfoCliente>();
         /*this.produtos.get(i).forEach( (k,v) -> arvore.put(k.clone(),((InfoProduto) v)));*/
         for(Cliente c: this.clientes.get(i).keySet()){
            arvore.put(c,this.clientes.get(i).get(c));
         }
         lista.add(i,arvore);         
      }
        
      return lista;
    }
    
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
}
