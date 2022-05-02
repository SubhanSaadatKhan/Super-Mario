package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

import static game.Status.TALL;

/**
 * A class that carries an attack action when the target actor is in his surroundings.
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Checks all possible exits if target actor found in any of those exits an attack action will take place.
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return action to be carried
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location enemyLoc = map.locationOf(actor);
        for (Exit exit : enemyLoc.getExits()) {
            Location destination = exit.getDestination();
            String direction = exit.getName();
            Actor actorPlayer = destination.getActor();
            if (actorPlayer != null) {
                char ch = actorPlayer.getDisplayChar();
                if (ch == 'm') {
                    return new AttackAction(actorPlayer, direction);
                } else if (ch == 'M') {
                    actorPlayer.removeCapability(TALL);
                    return new AttackAction(actorPlayer, direction);
                }
            }


        }
        return null;
    }
}
