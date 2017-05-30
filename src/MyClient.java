public class MyClient extends Client{
  double posPaddleServer;
  double posPaddleClient;
  double posBallX;
  double posBallZ;

  public MyClient(String serverIp, int serverPort){
    super(serverIp, serverPort);
  }

  @Override
  public void processMessage(String s){
    msgToAtt(s);
  }

  public void msgToAtt(String s){
    String[] values = s.split(";");
    posPaddleServer = Double.parseDouble(values[0]);
    posPaddleClient = Double.parseDouble(values[1]);
    posBallX = Double.parseDouble(values[2]);
    posBallZ = Double.parseDouble(values[3]);
  }
}
