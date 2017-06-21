import javafx.util.Pair;

import java.util.ArrayList;

public class MyServer extends Server{
  Main main;

  ArrayList<Pair<String, Integer>> clientList = new ArrayList<Pair<String, Integer>>();

  public MyServer(int port, Main main){
    super(port);
    this.main = main;
  }

  @Override
  public void processNewConnection(String clientIP, int clientPort){
    main.gui.setConnection(true);
    //TODO: Auf Controller ändern (auch bei processCLosingConnection (Methode))

    clientList.add(new Pair<String, Integer>(clientIP, clientPort));
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

    System.out.println(msg);
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

  @Override
  public void sendToAll(String pMessage) {
    for (Pair<String, Integer> p : clientList){
      send(p.getKey(), p.getValue(), pMessage);
    }
  }
}
