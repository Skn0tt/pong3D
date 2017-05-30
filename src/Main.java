public class Main {
  //Netzwerk
  static MyClient client;
  static MyServer server;

  //TODO:GUI @Benedikt

  public static void main(String[] args) {

  }

  //Server erzeugen
  void createServer(int port){
    server = new MyServer(port, this);
  }

  //Client erzeugen
  void createClient(String ip, int port){
    client = new MyClient(ip, port, this);
  }
}
