package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;
//final
/**
 * Action for resetting the game
 */
public class ResetAction extends Action {

    private static boolean used = false; // If ResetAction has been used

    /**
     * Constructor of ResetAction
     */
    public ResetAction() {
    }

    /**
     * Return if ResetAction is available
     *
     * @return true if ResetAction is available, false if ResetAction is not available
     */
    public static boolean available() {
        return !used;
    }

    /**
     * Execute this ResetAction
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The description of the result after execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        used = true;
        return "The game is reset";
    }

    /**
     * Return the menu description of this action
     *
     * @param actor The actor performing the action.
     * @return The description of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }

    /**
     * Returns the key used in the menu to trigger ResetAction.
     *
     * @return The key we use for ResetAction in the menu
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
