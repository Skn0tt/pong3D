public class MyServer extends Server{
  Main main;

  public MyServer(int port, Main main){
    super(port);
    this.main = main;
  }

  @Override
  public void processNewConnection(String clientIP, int clientPort){
    main.gui.setConnection(true);
    //TODO: Auf Controller ändern (auch bei processCLosingConnection (Methode))

    String s = "2;" + main.gui.getIp();
    send(clientIP, clientPort, s);
  }

  @Override
  public void processMessage(String clientIP, int clientPort, String msg){
    String[] s = msg.split(";");

    switch (s[0]) {
      case "0":
        main.setPuck(Double.parseDouble(s[1]), Double.parseDouble(s[2]));
        main.setServer(Double.parseDouble(s[3]));
        main.setClient(Double.parseDouble(s[4]));
        break;
    }
  }

  @Override
  public void processClosingConnection(String clientIP, int clientPort){
    main.gui.setConnection(false);
  }

  void sendPos(){
    String s = "0;" + main.puckX + ";" + main.puckZ + ";" + main.serverX + ";" + main.clientX;
    sendToAll(s);
  }

  void sendStart(){
    sendToAll("1;");
  }

}
