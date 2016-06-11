public class ClienteInexistenteException extends Exception {
  /**
   * Exceção para um cliente que não está presente no Catálogo de clientes.  
   */
  public ClienteInexistenteException(String msg){
    super(msg);
  }
  
}