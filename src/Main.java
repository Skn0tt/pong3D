public class Main {
  //Netzwerk
  MyClient client;
  MyServer server;
  boolean attServer; //Server/client?
  String attIP;
  int attPort;

  //Gloop-Koordinaten
  double puckX;
  double puckZ;
  double serverX;
  double clientX;

  //Game
  static Game game;

  //TODO:GUI @Benedikt
  GUI gui;

  public Main() {
    gui = new GUI(this);
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
  void setPuck(double x, double z){
    //TODO: Überprüfung XY
    this.puckX = x;
    this.puckZ = z;
    game.refreshPos();
  }

  void setServer(double x){
    if (x > -100 && x < 100) this.serverX = x;
    game.refreshPos();
  }

  void setClient(double x){
    if(x > -100 && x < 100) this.clientX = x;
    game.refreshPos();
  }

  void publishPositions(){
    if (attServer){
      server.sendPos();
    }
    else{
      client.sendPos();
    }
  }

  void startGame(){
    game = new Game(this);
  }
}
