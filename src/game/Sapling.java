package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class Sapling extends Tree implements JumpableGround{
    private int value;

    public Sapling(){
        super('t');
        value = 0;
    }

    @Override
    public void tick(Location location) {
        value += 1;
        if(value % 10 == 0){
            location.setGround(new Mature());
        }
        else{
            if(Math.random() <= 0.1){
                location.addItem(new Coin(20));
            }
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public String jumped(Actor act, Location at, GameMap map) {
        Actor actor = act;
        if(Math.random() <= 0.8) {
            map.moveActor(act,at);
            return actor + " had a successfully jump at Sapling(" + at.x() + "," + at.y() + ")!";

        }
        else {
            actor.hurt(20);
            return actor + " fails to jump the Sapling, faced a 20 fall damage!";
        }
    }
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        return new ActionList(new JumpAction(this, location, direction));
    }

    @Override
    public String toString() {
        return "Sapling";
    }
}
