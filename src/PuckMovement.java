import GLOOP.GLVektor;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by skn0tt on 03/06/17.
 */
public class PuckMovement extends Thread {
    Main main;

    int tickrate = 128;

    public PuckMovement(Main main){
        this.main = main;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                main.movePuck();
            }
        },1000/tickrate, 1000/tickrate);
    }
}
