package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.friends.Toad;
import game.actors.players.Player;
import game.items.consumables.magicalwaters.Bottle;

public class GetBottleAction extends Action {

    protected Toad toad;
    protected Bottle bottle;

    public GetBottleAction(Toad toad) {
        this.toad = toad;
        this.bottle = new Bottle();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.addItemToInventory(bottle);
        player.getABottle(bottle);
        toad.sendsBottle();
        return actor + "gets a magical bottle from " + toad;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets a magical bottle from Toad ";
    }
}
