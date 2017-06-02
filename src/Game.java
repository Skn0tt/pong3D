import GLOOP.*;
import com.sun.istack.internal.Nullable;

public class Game{
    //Camera Perspektiven
    public static final double[] PSPCTV_SERVER_GAME = new double[]{
            0,  //Position X
            500,    //Position Y
            -1000,      //Postion Z
            0,      //Blickpunkt X
            0,      //Blickpunkt Y
            0,      //Blickpunkt Z
    };

    public static final double[] PSPCTV_CLIENT_GAME = new double[]{
            1000,  //Position X
            500,    //Position Y
            0,      //Postion Z
            0,      //Blickpunkt X
            0,      //Blickpunkt Y
            0,      //Blickpunkt Z
    };

    //Objekte Werte
    public static final double[] PADDLE_A = new double[]{
        50, //Laenge X
        10, //Laenge Y
        20 //Laenge Z
    };

    Main main;

    //Input
    GLTastatur tastatur;
    Thread inputThread;

    //Welt
    GLKamera kamera;
    GLLicht licht;
    GLHimmel himmel;
    GLBoden boden;

    //Spielobjekte
    GLQuader paddleServer;
    GLQuader paddleClient;
    GLZylinder puck;

    //Einstellungen
    double distance = 250;

    //Grundconstructor
    public Game(Main main){
        this.main = main;

        kamera = new GLKamera(1200,1200);
        kamera.setzePosition(-1000, 450, 1000);
        kamera.setzeBlickpunkt(0,0,0);

        licht = new GLLicht(-5000, 1000, -5000);
        himmel = new GLHimmel("Bilder/Himmel.jpg");
        boden = new GLBoden("Bilder/Strasse.jpg");

        initGL();

        inputThread = new InputThread(main);
        inputThread.start();
    }

    //Constructor mit Perspektive
    public Game(Main main, double[] pspctv){
        this(main);
        setCamera(pspctv);
    }

    public void initGL() {
        this.paddleServer = new GLQuader(0, 0, distance * -1, 20, 10, 20);
        this.paddleClient = new GLQuader(0,0,distance,20,10,20);
        this.puck = new GLZylinder(0, 0, 0, 10, 20);
    }

    public void refreshPos(){
        paddleServer.setzePosition(main.serverX, paddleServer.gibY(), paddleServer.gibZ());
        paddleClient.setzePosition(main.clientX, paddleClient.gibY(), paddleClient.gibZ());
        puck.setzePosition(main.puckX, puck.gibY(), main.puckZ);
    }

    public void setCamera(double[] coord){
        if (coord.length != 6) return;   //Abbruch wegen unzureichenden Koordinaten

        kamera.setzePosition(coord[0], coord[1], coord[2]);
        kamera.setzeBlickpunkt(coord[3], coord[4], coord[5]);
    }


    public void setPaddle(double[] paddleData){
        if (paddleData.length != 3) return;

        if (paddleServer != null) paddleServer.loesche();
        if (paddleClient != null) paddleClient.loesche();

        paddleServer = new GLQuader(0,0,distance * -1, paddleData[0], paddleData[1], paddleData[2]);
        paddleClient = new GLQuader(0,0,distance, paddleData[0], paddleData[1], paddleData[2]);
    }

    public void setDistance(double z){
        this.distance = z;

        paddleClient.setzePosition(paddleClient.gibX(), paddleClient.gibY(), z / 2);
        paddleServer.setzePosition(paddleServer.gibX(), paddleServer.gibY(), z / 2 * -1);
    }
}
