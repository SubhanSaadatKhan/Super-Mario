package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.players.Player;
import game.grounds.harmless.fountains.Fountain;

/**
 * Action for refilling the magical bottle
 */
public class RefillBottomAction extends Action {

    Fountain target;  // The target Fountain

    /**
     * Constructor of RefillBottomAction
     *
     * @param target The target Fountain
     */
    public RefillBottomAction(Fountain target) {
        this.target = target;
    }

    /**
     * Execute this RefillBottomAction
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The description of the result after execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.getBottle().refill(target);
        return actor + " refills " + target.getWaterType();
    }

    /**
     * Return the menu description of this action
     *
     * @param actor The actor performing the action.
     * @return The description of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + target;
    }
}
