import GLOOP.GLTastatur;
import com.sun.istack.internal.NotNull;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by skn0tt on 31.05.17.
 */

public class InputThread extends Thread{
    double sensitivity = 300;
    int tickrate = 128;

    Main main;

    public InputThread(@NotNull Main main){
        this.main = main;
    }

    @Override
    public void run(){
        main.game.tastatur = new GLTastatur();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (main.game.tastatur.links()) main.move(sensitivity/tickrate);
                if (main.game.tastatur.rechts()) main.move(sensitivity/tickrate * -1);
            }
        },1000/tickrate, 1000/tickrate);
    }
}
