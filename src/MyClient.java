public class MyClient extends Client{
  Main main;

  public MyClient(String serverIp, int serverPort, Main main){
    super(serverIp, serverPort);
    this.main = main;
  }

  @Override
  public void processMessage(String msg){
    String[] s = msg.split(";");

    switch(s[0]) {
      case "0":
        main.setPuck(Double.parseDouble(s[1]), Double.parseDouble(s[2]));
        main.setServer(Double.parseDouble(s[3]));
        main.setClient(Double.parseDouble(s[4]));
        break;
      case "1":
        main.startGame();
        break;
      case "2":
        main.gui.setVerbunden(true, s[1]);
        break;
    }
  }

  void sendPos(){
    String s = "0;" + main.puckX + ";" + main.puckZ + ";" + main.serverX + ";" + main.clientX;
    send(s);
  }
}
