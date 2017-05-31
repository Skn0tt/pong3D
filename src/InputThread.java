import GLOOP.GLTastatur;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by skn0tt on 31.05.17.
 */

public class InputThread extends Thread{
    Main main;

    public InputThread(Main main){
        this.main = main;
    }

    @Override
    public void run(){
        main.game.tastatur = new GLTastatur();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (main.game.tastatur.oben()) main.move(10);
                if (main.game.tastatur.unten()) main.move(-10);
            }
        },5, 5);
    }
}
