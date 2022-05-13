package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Lava extends Ground {

    public Lava(){
        super('L');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        char actChar = actor.getDisplayChar();
        if (actChar == 'g' || actChar == 'K') { //Enemy cannot enter lava
            return false;
        }
        return true;
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            Actor player = location.getActor();
            player.hurt(15); //for every turn; player get 15 damage when it steps on lava
            new Display().println(player + " standing on Lava Ground got 15 damage");
        }
    }
}
