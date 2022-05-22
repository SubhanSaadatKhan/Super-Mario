package game.grounds.harmless;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
//final

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
    public Floor() {
        super('_');
    }

    /**
     * Method to check if a particular actor is allowed to enter a ground
     *
     * @param actor the Actor to check
     * @return true (if actor not enemy)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        char actChar = actor.getDisplayChar();
        //Enemy cannot enter floor
        return actChar != 'g' && actChar != 'K' && actChar != 'F';
    }
}
