public class MyClient extends Client{
  Main main;

  public MyClient(String serverIp, int serverPort, Main main){
    super(serverIp, serverPort);
    this.main = main;
  }

  @Override
  public void processMessage(String s){
    //TODO: MSG Handler
  }
}
