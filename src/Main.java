public class Main {
  //Netzwerk
  static MyClient client;
  static MyServer server;
  boolean attServer; //Server/client?
  String attIP;
  int attPort;

  //Gloop-Koordinaten
  double puckX;
  double puckZ;
  double opponentX;
  double selfX;

  //Game
  static Game game;

  //TODO:GUI @Benedikt
  Gui gui;

  public Main() {
    //game = new Game(this);
    gui = new Gui(this);
    gui.setVisible(true);
  }

  //Server erzeugen
  void createServer(int port){
    server = new MyServer(port, this);
    this.attServer = true;
    this.attPort = port;
  }

  //Client erzeugen
  void createClient(String ip, int port){
    client = new MyClient(ip, port, this);
    this.attServer = false;
    this.attPort = port;
    this.attIP = ip;
  }

  //Get-Set Gloop
  void setPuck(double x, double y){
    //TODO: Überprüfung XY
  }

  void setOpponent(double x){
    if (x > -100 && x < 100) this.opponentX = x;
  }

  void setSelf(double x){
    if(x > -100 && x < 100) this.selfX = x;
  }
}
