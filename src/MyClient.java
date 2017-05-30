public class MyClient extends Client{
  Starter main;

  public MyClient(String serverIp, int serverPort, Starter main){
    super(serverIp, serverPort);
    this.main = main;
  }

  @Override
  public void processMessage(String s){
    //TODO: MSG Handler
  }
}
