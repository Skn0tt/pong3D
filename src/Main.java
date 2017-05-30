public class Main {
  //Netzwerk
  static MyClient client;
  static MyServer server;
  boolean attServer; //Server/client?
  String attIP;
  int port;

  //Gloop-Koordinaten
  double puckX;
  double puckY;
  double opponentX;
  double selfX;

  //TODO:GUI @Benedikt

  public static void main(String[] args) {

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
    if (x > -100 && x < 100){
      this.x = x;
    }
  }

  void setSelf(double x){

  }
}
