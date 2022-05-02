package game.grounds.jumpablegrounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for Grounds that require a Jump used by JumpAction to jump over high grounds.
 */
public interface JumpableGround {

    /**
     * Overwrite this method to implement the jumping criteria for each high ground
     *
     * @param by indicate who jumps the jumpable ground
     * @param at to indicate where in the game map the jumpable ground is jumped
     * @param in represents the map where jump is taking place
     * @return string indicating if player succeeds or fails while jumping
     */
    String jumped(Actor by, Location at, GameMap in);

}
