import GLOOP.*;
public class Game{
    Main main;

    GLQuader self;
    GLQuader opponent;
    GLQuader puck;

    public Game(Main main){
        this.main = main;
    }

    public void refreshPos(){
        self.setzePosition(main.selfX, self.gibY(), self.gibZ());
        opponent.setzePosition(main.opponentX, opponent.gibY(), opponent.gibZ());
        puck.setzePosition(main.puckX, puck.gibY(), main.puckZ);
    }
}
