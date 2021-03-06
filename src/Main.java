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

  public static final double MAX_ANGLE = 75;

  //Game
  Game game;

  //GUI
  GUI gui;

  public Main() {
    ///*
    gui = new GUI(this);
    gui.setVisible(true);
    //*/
    /*
    Controller controller = new Controller();
    controller.show();
    */
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
    //checkCollision();
  }

  void setServer(double x){
    if (x > (Game.BREITE * -1) + (game.paddleWidth / 2) && x < (Game.BREITE) - (game.paddleWidth / 2)) {
      this.serverX = x;
      game.refreshPos();
      //checkCollision();
    }
  }

  void setClient(double x){
    if (x > (Game.BREITE * -1) + (game.paddleWidth / 2) && x < (Game.BREITE) - (game.paddleWidth / 2)){
      this.clientX = x;
      game.refreshPos();
      //checkCollision();
    }
  }

  void move(double x){
    if (attServer){
      setServer(serverX + x);
    }
    else{
      setClient(clientX + x * -1);
    }

    checkCollision();
    publishPositions();
  }

  void movePuck(){
    setPuck(puckX + puckRichtung.x * speed, puckZ + puckRichtung.z * speed);
    //setClient(puckX);
    checkCollision();
    publishPositions();
  }

  void checkCollision() {
    /**
     * Kollision Jan
     */
    ///*
    //Paddle Collision
    if (Math.abs(puckZ) >= game.distance - (game.paddleDepth / 2) - Game.PUCK_RADIUS) {  //Prüft ob auf höhe der TorLinie
      if (puckZ > 0) { //Client Seite
        if (puckX > (clientX - (game.paddleWidth / 2)) && puckX < (clientX + (game.paddleWidth / 2))) { //Prüft ob zwischen Enden des Paddles
          double yIntersect = puckX - clientX;
          double normalized = (yIntersect/(game.paddleWidth/2));
          richtungFlipHorizontal(normalized); //Paddle Hit
          //richtungFlipHorizontal();
        }
        else punktServer();
      } else {  //Server Seite
        if (puckX > (serverX - (game.paddleWidth / 2)) && puckX < (serverX + (game.paddleWidth / 2))) { //Prüft ob zwischen Enden des Paddles
          double yIntersect = puckX - serverX;
          double normalized = (yIntersect/(game.paddleWidth/2));
          richtungFlipHorizontal(normalized); //Paddle Hit
          //richtungFlipHorizontal();
        }
        else punktClient();
      }
    }
    //Wall Collision
    else if (Math.abs(puckX) > (Game.BREITE - Game.WAND_BREITE / 2) - Game.PUCK_RADIUS){
      richtungFlipVertikal();
    }

    if (Math.abs(puckZ) > game.distance) setPuck(0,0);
    //*/

    /**
     * Kollision Simon
     */
    /*
    if (Math.abs(puckZ) > game.distance - (game.paddleDepth / 2) - (Game.PUCK_RADIUS / 2)) {  //Prüft ob auf höhe der linie
      if (puckZ > 0){ //Client Seite
        if (puckX > (clientX - (game.paddleWidth/2)) && puckX < (clientX + (game.paddleWidth/2))) richtungFlipHorizontal(puckX-clientX); //Paddle Hit
        else punktServer();
      } else {  //Server Seite
        if (puckX > (serverX - (game.paddleWidth/2)) && puckX < (serverX + (game.paddleWidth/2))) richtungFlipHorizontal(puckX-clientX); //Paddle Hit
        else punktClient();
      }
    }
    else if (Math.abs(puckX) > Game.BREITE - Game.WAND_BREITE / 2 - Game.PUCK_RADIUS) richtungFlipVertikal(); //Wall Hit
    */
  }

  void richtungFlipHorizontal(double x) {
    double bounceAngle = x * MAX_ANGLE;
    puckRichtung.z = Math.cos(bounceAngle);
    puckRichtung.x = Math.sin(bounceAngle) * -1;
    //System.out.println(x);
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
    publishPositions();
    checkCollision();
    richtungFlipHorizontal(0);
  }

  void punktClient(){
    setPuck(0,0);
    publishPositions();
    checkCollision();
    richtungFlipHorizontal(0);
  }

  void startGame() {
    startGame(Game.PADDLE_A);
  }
}
