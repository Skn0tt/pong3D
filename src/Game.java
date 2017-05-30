import GLOOP.*;
public class Game{
    Main main;

<<<<<<< HEAD
    //Welt
    GLKamera kamera;
    GLLicht licht;
    GLHimmel himmel;
    GLBoden boden;

    //Spielobjekte
    GLQuader self;
    GLQuader opponent;
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

    public void initGL(){
        this.self = new GLQuader(0, 0, -100, 20, 10, 20);
        this.opponent = new GLQuader(0, 0, 100, 20, 10, 20);
        this.puck = new GLZylinder(0,0,0,10,20);
=======
    GLQuader self;
    GLQuader opponent;
    GLQuader puck;

    public Game(Main main){
        this.main = main;
>>>>>>> master
    }

    public void refreshPos(){
        self.setzePosition(main.selfX, self.gibY(), self.gibZ());
        opponent.setzePosition(main.opponentX, opponent.gibY(), opponent.gibZ());
        puck.setzePosition(main.puckX, puck.gibY(), main.puckZ);
    }
}
