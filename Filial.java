import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

public class Filial implements Serializable{
    private List<Map<String,InfoCliente>> clientes;
    
     public Filial(){
        this.clientes = new ArrayList<>(26);
        Map<String,InfoCliente> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new TreeMap<String,InfoCliente>();
            this.clientes.add(i,arvore);
        }
    }
    //QUERIE2
    public int getNumVendMes(Set<String> clientes,int mes){
        int i;
        int numVend=0;
        for(i=0; i<26; i++){
            for(Map.Entry<String,InfoCliente> entry : this.clientes.get(i).entrySet()){
                InfoMes m = entry.getValue().getMesIndex(mes);
                if(m!=null){numVend += m.getNumVendas();clientes.add(entry.getKey());}; 
            }
            //this.clientes.get(i).forEach((k,v)->{InfoMes m = v.getMesIndex(mes); if(m!=null){numVend += m.getNumVendas();clientes.add(k);};});
        }
        return numVend;
    }
    //QUERIE3
    public ParIntDouble getNumCompTotMes(Set<String> prods,int mes,String cli){
        int indice = calculaIndice(cli.charAt(0));
        int numCompras = 0;
        int totGasto = 0;
        if(!(this.clientes.get(indice).containsKey(cli))) return new ParIntDouble(numCompras,totGasto);
        InfoCliente i= this.clientes.get(indice).get(cli);
        InfoMes m = i.getMesIndex(mes);
        if(m!=null){numCompras += m.getNumVendas();totGasto += m.getTotGasto();m.getProdutos().forEach(x -> {prods.addAll(x.keySet());});};
        return new ParIntDouble(numCompras,totGasto);
    }
    //QUERIE4
    public ParIntDouble getNumCompTotMesProd(Set<String> cli, int mes,String prod){
        int i;
        int numVend = 0;
        int totFact = 0;
        for(i=0; i<26; i++){
            for(Map.Entry<String,InfoCliente> entry : this.clientes.get(i).entrySet()){
                InfoMes m = entry.getValue().getMesIndex(mes);
                if(m!=null){InfoClienteProduto h = m.getProdutoInfo(prod);
                            if(h!=null){cli.add(entry.getKey());
                                        numVend += h.getNumVendas();
                                        totFact += (h.getTotGasto(0)+h.getTotGasto(1));
                            }
                } 
            }
        }
        return new ParIntDouble(numVend,totFact);
    }
    //QUERIE5
    public void getProdsMaisComp(String cli,Map<String,Object> prods){
        int i,j;
        int indice = calculaIndice(cli.charAt(0));
        if(clientes.get(indice).containsKey(cli)){
            for(i=0;i<12;i++){
                InfoMes m = this.clientes.get(indice).get(cli).getMesIndex(i);
                if(m!=null){
                    for(Map<String,InfoClienteProduto> p : m.getProdutos()){
                        for(Map.Entry<String,InfoClienteProduto> entry : p.entrySet()){
                            if(prods.containsKey(entry.getKey())){
                                String prod = entry.getKey();
                                Object actual = prods.get(prod);
                                double actual2 = (double)actual + (entry.getValue().getQuantity(0)+entry.getValue().getQuantity(1));
                                prods.remove(prod);
                                prods.put(prod,actual2);
                            }
                            else{prods.put(entry.getKey(),(entry.getValue().getQuantity(0)+entry.getValue().getQuantity(1)));}
                        }
                    }
                    
                }
            }
        }
    }
    
    //QUERIE7
    public List<ParStringDouble> getCompTot(){
        List<ParStringDouble> list = new ArrayList<>();
        int i;
        for(i=0; i<26; i++){
            this.clientes.get(i).forEach((k,v)->{list.add(new ParStringDouble(k,v.getTotFact()));});
        }
        return list;
    }
    
    public void insereVenda(Venda v){ 
       String c = v.getCliente();
       int indice = calculaIndice(c.charAt(0));
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
    public List<Map<String,InfoCliente>> getFilialClientes(){
      ArrayList<Map<String,InfoCliente>> lista = new ArrayList<>(26);
      int i;  
      
      for(i=0; i<26; i++){
         Map<String,InfoCliente> arvore = new TreeMap<String,InfoCliente>();
         this.clientes.get(i).forEach( (k,v) -> arvore.put(k,v.clone()));
         //for(String c: this.clientes.get(i).keySet()){
           // arvore.put(c,this.clientes.get(i).get(c));
         //}
         lista.add(i,arvore);         
      }
        
      return lista;
    }
    
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
}
