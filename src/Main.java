import GLOOP.GLVektor;

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

  GLVektor puckRichtung = new GLVektor(Math.random() * 0.6,0,- 1);
  double speed = 4;

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
    if (x > (Game.BREITE * -1) + (game.paddleWidth / 2) && x < (Game.BREITE) - (game.paddleWidth / 2)) this.serverX = x;
    game.refreshPos();
  }

  void setClient(double x){
    if (x > (Game.BREITE * -1) + (game.paddleWidth / 2) && x < (Game.BREITE) - (game.paddleWidth / 2)) this.clientX = x;
    game.refreshPos();
  }

  void move(double x){
    if (attServer){
      setServer(serverX + x);
    }
    else{
      setClient(clientX + x * -1);
    }

    publishPositions();
  }

  void movePuck(){
    puckX += puckRichtung.x * speed;
    puckZ += puckRichtung.z * speed;

    checkCollision();

    game.refreshPos();
    publishPositions();
  }

  void checkCollision(){
    if (Math.abs(puckZ) > game.distance) {
      if (!(puckX > serverX - (game.paddleWidth / 2) && puckZ < (serverX + (game.paddleWidth / 2)))) punktClient();
      else if (!(puckX > clientX - (game.paddleWidth / 2) && puckZ < (clientX + (game.paddleWidth / 2)))) punktServer();
      else richtungFlipHorizontal();  //Paddle Hit
    }
    else if (Math.abs(puckX) > Game.BREITE - Game.WAND_BREITE / 2 - Game.PUCK_RADIUS) richtungFlipVertikal(); //Wall Hit
  }

  void richtungFlipHorizontal(){
    puckRichtung.z = puckRichtung.gibZ() * -1;
  }

  void richtungFlipVertikal(){
    puckRichtung.x = puckRichtung.gibX() * -1;

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
      game.setCamera(Game.PSPCTV_SERVER_GAME);
      server.sendStart();
    }
    else{
      game.setCamera(Game.PSPCTV_CLIENT_GAME);
    }
  }

  void punktServer(){
    setPuck(0,0);
    richtungFlipHorizontal();
  }

  void punktClient(){
    setPuck(0,0);
    richtungFlipHorizontal();
  }

  void startGame() {
    startGame(Game.PADDLE_A);
  }
}
