package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class JumpAction extends Action {
    private JumpableGround jumpableGround;
    private Location jumpableLocation;
    private String direction;

    public JumpAction(JumpableGround jumpableGround,Location jumpableLocation,String direction){
        this.jumpableGround = jumpableGround;
        this.jumpableLocation = jumpableLocation;
        this.direction = direction;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return jumpableGround.jumped(actor,jumpableLocation,map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps the " + jumpableGround + " to the " + direction;
    }
}
