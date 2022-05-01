package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.players.Player;
import game.item.Coin;
import game.item.consumables.PowerStar;

public class TradingAction extends Action {
    protected Item target;
    protected Coin price;

    public TradingAction(Item target, Coin price) {
        this.target = target;
        this.price = price;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getWallet().decrease(price)) {
            if (target instanceof PowerStar) {
                actor.addItemToInventory(new PowerStar(11));
            }
            else {
                actor.addItemToInventory(target);
            }
            return actor + " buys " + target;
        }
        return actor + " does not have enough money to buy " + target;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys a " + target + " (" + price + ")";
    }
}
