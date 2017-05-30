import GLOOP.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener{
    Main main;

    //Welt
    GLKamera kamera;
    GLLicht licht;
    GLHimmel himmel;
    GLBoden boden;

    //Spielobjekte
    GLQuader self;
    GLQuader opponent;
    GLZylinder puck;

    GLTastatur tastatur;

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
        this.self = new GLQuader(0, 0, -100, 20, 10, 20);
        this.opponent = new GLQuader(0, 0, 100, 20, 10, 20);
        this.puck = new GLZylinder(0, 0, 0, 10, 20);
    }

    public void refreshPos(){
        self.setzePosition(main.clientX, self.gibY(), self.gibZ());
        opponent.setzePosition(main.serverX, opponent.gibY(), opponent.gibZ());
        puck.setzePosition(main.puckX, puck.gibY(), main.puckZ);
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                main.move(-1);
                break;
            case KeyEvent.VK_RIGHT:
                main.move(1);
                break;
            default:
                main.move(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
