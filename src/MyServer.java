public class MyServer extends Server{
  Main main;

  public MyServer(int port, Main main){
    super(port);
    this.main = main;
  }

  @Override
  public void processNewConnection(String clientIP, int clientPort){
    //TODO: New Connection Handler
  }

  @Override
  public void processMessage(String clientIP, int clientPort, String msg){
    //TODO: MSGHandler
  }

  @Override
  public void processClosingConnection(String clientIP, int clientPort){
    //TODO: Close Connection Handler
  }

}
