package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.FireAttackEffect;
import game.items.Fire;

public class FireAttackAction extends AttackAction{
    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction the attack direction
     */
    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(target).addItem(new Fire());
        actor.removeItemFromInventory(new FireAttackEffect());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire!";
    }
}
