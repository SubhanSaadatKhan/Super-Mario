package game.items.consumables;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;
import game.effects.FireAttackEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * FireFlower, can be consumed by player. Player will get FireAttackEffect after consuming
 */
public class FireFlower extends Item implements Consumable {
    /***
     * Constructor of FireFlower.
     */
    public FireFlower() {
        super("Fire Flower", 'f', false);
    }

    /**
     * This method will make the player be able to interact with FireFlower
     *
     * @return A ConsumeItemAction that allows the player to consume the FireFlower
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeItemAction(this));
        return actions;
    }

    /**
     * This method will let the FireFlower be consumed,
     * the consumer will be able to perform FireAttack once
     *
     * @param actor           The actor that will do a ConsumeItemAction
     * @param currentLocation The location that ConsumeItemAction happens
     */
    @Override
    public void consumed(Actor actor, Location currentLocation) {
        actor.addItemToInventory(new FireAttackEffect());
        currentLocation.removeItem(this);
    }
}
