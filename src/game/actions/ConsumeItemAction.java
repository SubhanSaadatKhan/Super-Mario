package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumables.Consumable;

public class ConsumeItemAction extends Action {
    protected Consumable target;

    public ConsumeItemAction(Consumable target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        target.Consumed(actor, map.locationOf(actor));
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + target;
    }
}
