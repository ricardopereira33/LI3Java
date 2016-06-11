import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

public class CatProdutos implements Serializable{
    
    private List<Set<String>> catalogo;
    
    /**
     * Construtor vazio do Catálogo de Produtos.
     */
    public CatProdutos(){
        catalogo = new ArrayList<>(26);
        Set<String> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new HashSet<>();
            this.catalogo.add(i,arvore);
        }
    }
    
    /**
     * Construtor por parâmetros do Catálogo de Produtos.
     * @param collection
     */
    public CatProdutos(Collection <String> collection){
        int indice;
        for(String produto: collection){
            indice = calculaIndice(produto.charAt(0));
            this.catalogo.get(indice).add(produto);
        }
    }
    
    /** 
     * Construtor por cópia do Catálogo de Produtos.
     * @param catalogoProdutos
     */
    public CatProdutos(CatProdutos catalogoProdutos){
        int indice;
        
        catalogo = new ArrayList<>(26);
        Set<String> arvore;
        
        for(int i=0; i<26; i++){
            arvore = new HashSet<>();
            this.catalogo.add(i,arvore);
        }
        
        for(indice=0; indice < catalogoProdutos.getCatalogo().size(); indice++){
            for(String produto: catalogoProdutos.getCatalogo().get(indice)){
                this.catalogo.get(indice).add(produto);
            }
        }
    }
    
    /**
     * Função que retorna o catálogo.
     * @return
     */
    public List<Set<String>> getCatalogo(){
        return this.catalogo;
    }
   
    /**
     * Função que retorna a lista de Produtos.
     * @return
     */
    public List<String> getProdutos(){
        List<String> lista = new ArrayList<String>();
        for(Set<String> arvore: this.catalogo){
            for(String produto: arvore)
                lista.add(produto);
        }
        return lista;
    }
    
    // Métodos
    
    /**
     * Função que retorna uma lista de Produtos começados por uma determinada letra.
     * @param letra
     * @return
     */
    public List<String> getProdutosLetra(char letra){
        int indice = calculaIndice(letra);
        List<String> lista = new ArrayList<String>();
        for(String produto: this.catalogo.get(indice))
            lista.add(produto);
        return lista;
    }
   
    /**
     * Função que calcula o índice dado a primeira letra de um produto.
     * @param letra
     * @return
     */
    private static int calculaIndice(char letra){
        letra = Character.toUpperCase(letra);
        return letra - 'A';
    }
    
    /**
     * Função que retorna o total de produtos presentes no Catálogo.
     * @return
     */
    public int totalProdutos(){
        int total=0;
        for(Set<String> arvore: this.catalogo)
            total+=arvore.size();
        return total;
    }
    
    /**
     * Função que retorna o total de produtos de uma determinada letra presentes no Catálogo.
     * @param letra
     * @return 
     */
    public int totalProdutosLetra(char letra){
        return this.catalogo.get(calculaIndice(letra)).size();
    }
    
    /**
     * Função que insere um produto ao catálogo.
     * @param produto
     */
    public void insereProduto(String produto){
        int indice = calculaIndice(produto.charAt(0));
        this.catalogo.get(indice).add(produto);
    }
    
    /**
     * Função que remove um produto do catálogo.
     * @param produto
     */
    public void removeProduto(String produto){
        int indice = calculaIndice(produto.charAt(0));
        this.catalogo.get(indice).remove(produto);
    }
    
    /**
     * Função que determina a existência ou não de um produto no catálogo.
     * @param produto
     * @return
     */
    public boolean existeProduto(String produto){
        int indice = calculaIndice(produto.charAt(0));
        return this.catalogo.get(indice).contains(produto);
    }
    
    /**
     * Função que faz clone do Catálogo de Produtos.
     */
    public CatProdutos clone(){
        return new CatProdutos(this);
    }
    
    /**
     * Função que testa a igualdade.
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CatProdutos i = (CatProdutos) obj;

        return this.catalogo.equals(i.getCatalogo());
    }
    
    /**
     * Função que limpa o catálogo.
     */
    public void limpar (){
       this.catalogo.clear();
    }
}
