public class ProdutoInexistenteException extends Exception {
  /**
   * Exceção para um produto que não está presente no Catálogo de produtos.
   * @param msg
   */
  public ProdutoInexistenteException(String msg){
    super(msg);
  }
  
}