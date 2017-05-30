public class MyClient extends Client{
  Main main;

  public MyClient(String serverIp, int serverPort, Main main){
    super(serverIp, serverPort);
    this.main = main;
  }

  @Override
  public void processMessage(String msg){
    String[] s = msg.split(";");
    main.setPuck(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
    main.setServer(Double.parseDouble(s[2]));
    main.setClient(Double.parseDouble(s[3]));
  }

  void sendPos(){
    String s = main.puckX + ";" + main.puckZ + ";" + main.serverX + ";" + main.clientX;
    send(s);
  }
}
