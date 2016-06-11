public class ProdutoInexistenteException extends Exception {
  /**
   * Exceção para um produto que não está presente no Catálogo de produtos.
   */
  public ProdutoInexistenteException(String msg){
    super(msg);
  }
  
}