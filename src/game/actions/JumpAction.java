package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.jumpablegrounds.JumpableGround;

import static game.Status.INVINCIBLE;
//final
/**
 * Special Action for jumping high grounds.
 */
public class JumpAction extends Action {
    private JumpableGround jumpableGround;
    private Location jumpableLocation;
    private String direction;

    /**
     * Constructor
     *
     * @param jumpableGround   a jumpableGround object.
     * @param jumpableLocation location of the jumpable ground.
     * @param direction        which direction to jump.
     */
    public JumpAction(JumpableGround jumpableGround, Location jumpableLocation, String direction) {
        this.jumpableGround = jumpableGround;
        this.jumpableLocation = jumpableLocation;
        this.direction = direction;

    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return string indicating if player succeeds or fails while jumping
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return jumpableGround.jumped(actor, jumpableLocation, map);
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        if (actor.hasCapability(INVINCIBLE)) {
            return actor + " moves " + direction;
        }
        return actor + " jumps the " + jumpableGround + " to the " + direction;
    }
}
