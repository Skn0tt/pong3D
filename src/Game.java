import GLOOP.*;

public class Game{
    Main main;

    //Welt
    GLKamera kamera;
    GLLicht licht;
    GLHimmel himmel;
    GLBoden boden;

    //Spielobjekte
    GLQuader server;
    GLQuader client;
    GLZylinder puck;

    public Game(Main main){
        this.main = main;

        kamera = new GLSchwenkkamera(1200,1200);
        kamera.setzePosition(-1000, 450, 1000);
        kamera.setzeBlickpunkt(0,0,0);

        licht = new GLLicht(-5000, 1000, -5000);
        himmel = new GLHimmel("Bilder/Himmel.jpg");
        boden = new GLBoden("Bilder/Strasse.jpg");

        initGL();
    }

    public void initGL() {
        this.server = new GLQuader(0, 0, -100, 20, 10, 20);
        this.client = new GLQuader(0, 0, 100, 20, 10, 20);
        this.puck = new GLZylinder(0, 0, 0, 10, 20);
    }

    public void refreshPos(){
        server.setzePosition(main.clientX, server.gibY(), server.gibZ());
        client.setzePosition(main.serverX, client.gibY(), client.gibZ());
        puck.setzePosition(main.puckX, puck.gibY(), main.puckZ);
    }
}
