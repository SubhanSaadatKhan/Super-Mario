package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.players.Player;
import game.grounds.harmless.fountains.Fountains;

public class RefillBottomAction extends Action {

    Fountains target;

    public RefillBottomAction(Fountains target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.getBottle().refill(target);
        return actor + " refills " + target.getWaterType();
    }

    @Override
    public String menuDescription(Actor actor) {
            return actor + " refills bottle from " + target;
    }
}
