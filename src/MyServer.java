public class MyServer extends Server{
  double x;
  double z;

  public MyServer(int port){
    super(port);
  }

  @Override
  public void processNewConnection(String clientIP, int clientPort){
    System.out.println("Funkt");
  }

  @Override
  public void processMessage(String clientIP, int clientPort, String msg){
    System.out.println(msg);

    String[] values = msg.split(";");

    x = Double.parseDouble(values[0]);
    z = Double.parseDouble(values[1]);
  }

  @Override
  public void processClosingConnection(String clientIP, int clientPort){}

}
