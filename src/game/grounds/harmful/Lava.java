package game.grounds.harmful;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
/**
 * Class representing Lava Ground
 */
public class Lava extends Ground {

    /**
     * Constructor
     */
    public Lava(){
        super('L');
    }

    /**
     * Method to check if a particular actor is allowed to enter a ground
     *
     * @param actor the Actor to check
     * @return true (if actor not player)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        char actChar = actor.getDisplayChar();
        if (actChar == 'g' || actChar == 'K') { //Enemy cannot enter lava
            return false;
        }
        return true;
    }

    /**
     * Gives a damage of 15 every turn to the actor standing on it
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            Actor player = location.getActor();
            player.hurt(15); //for every turn; player get 15 damage when it steps on lava
            new Display().println(player + " standing on Lava Ground got 15 damage");
        }
    }
}
