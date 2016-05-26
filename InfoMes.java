import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InfoMes
{
    private List<Map<Produto,InfoClienteProduto>> produtos;
    private int quantidade;
    
    public InfoMes (){
        int i;
        this.produtos = new ArrayList<>(26);
        for(i=0;i<26;i++)
            this.produtos.add(i,null);
        this.quantidade=0;
    }
    
    public InfoMes (InfoMes im){
        int indice;
        
        for(indice=0; indice < 26; indice++){
            for(Produto produto: im.getProdutos().get(indice).keySet()){
                this.produtos.get(indice).put(produto.clone(),im.getProdutos().get(indice).get(produto).clone());
            }
        }
    
    }
    
    public void insereProdutoQuant(Venda v){
        this.quantidade+= v.getQuantidade();
        
        Produto p = v.getProduto();
        int indice = calculaIndice(p.getNomeProduto().charAt(0));
        
        if(produtos.get(indice)!=null){
            insereProdutoQuantAux(v,indice,p);
        }
        else {
            Map<Produto,InfoClienteProduto> arvore;
            arvore = new TreeMap<Produto,InfoClienteProduto>(new ComparatorProduto());
            this.produtos.add(indice,arvore);
            insereProdutoQuantAux(v,indice,p);
        }
    }
    
    public void insereProdutoQuantAux(Venda v,int indice,Produto p){

        if(produtos.get(indice).containsKey(p))
                produtos.get(indice).get(p).insereInfoCP(v);
            else{ 
                    InfoClienteProduto icp = new InfoClienteProduto();
                    icp.insereInfoCP(v);
                    produtos.get(indice).put(p,icp);
                }
    }
        
    public List<Map<Produto,InfoClienteProduto>> getProdutos(){
        return this.produtos;
    }
    
    private static int calculaIndice(char letra){
        return letra - 'A';
    }
    
}
