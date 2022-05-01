package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.players.Player;
import game.items.Coin;

public class PickUpCoinAction extends PickUpItemAction{

    private final Coin coin;

    /**
     * Constructor.
     *
     * @param coin the coin to pick up
     */
    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        Player player = (Player) actor;
        player.getWallet().increase(coin);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + coin;
    }
}
