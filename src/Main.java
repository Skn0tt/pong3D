import GLOOP.GLVektor;
import com.sun.istack.internal.Nullable;

public class Main {
  //Netzwerk
  MyClient client;
  MyServer server;
  boolean attServer; //Server/paddleClient?
  String attIP;
  int attPort;
  final static int STANDARD_PORT = 27014;

  //Gloop-Koordinaten
  double puckX;
  double puckZ;
  double serverX;
  double clientX;

  GLVektor puckRichtung = new GLVektor(0,0,1);
  double speed = 1;

  //Game
  Game game;

  //GUI
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
    if (x > Game.BREITE / 2 * -1 && x < Game.BREITE / 2) this.serverX = x;
    game.refreshPos();
  }

  void setClient(double x){
    if(x > Game.BREITE / 2 * -1 && x < Game.BREITE / 2) this.clientX = x;
    game.refreshPos();
  }

  void move(double x){
    if (attServer){
      setServer(serverX + x);
    }
    else{
      setClient(clientX + x);
    }

    publishPositions();
  }

  void movePuck(){
    puckX += puckRichtung.x * speed;
    puckZ += puckRichtung.z * speed;

    game.refreshPos();
    publishPositions();
  }

  void publishPositions(){
    if (attServer){
      server.sendPos();
    }
    else{
      client.sendPos();
    }
  }

  void startGame(double[] paddleForm){
    game = new Game(this);
    game.setPaddle(paddleForm);
    game.start();
    if (attServer) {
      game.setCamera(game.PSPCTV_SERVER_GAME);
      server.sendStart();
    }
    else{
      game.setCamera(game.PSPCTV_CLIENT_GAME);
    }
  }

  void startGame() {
    startGame(Game.PADDLE_A);
  }
}
