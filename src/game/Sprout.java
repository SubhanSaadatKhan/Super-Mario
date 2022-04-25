package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class Sprout extends Tree implements JumpableGround {
    private int value;

    public Sprout(){
        super('+');
        value = 0;
    }

    @Override
    public void tick(Location location) {
        value += 1;
        if(value % 10 == 0){
            location.setGround(new Sapling());
        }
        else{
            if(Math.random() <= 0.1 && !location.containsAnActor()){
                location.addActor(new Goomba());
            }
        }
    }

    @Override
    public String jumped(Actor act, Location at, GameMap map) {
        Actor actor = act;
        if(Math.random() <= 0.9) {
            map.moveActor(act,at);
            return actor + " had a successfully jump at Sprout(" + at.x() + "," + at.y() + ")!";
        }
        else {
            actor.hurt(10);
            return actor + " fails to jump the Sprout, faced a 10 fall damage!";
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        return new ActionList(new JumpAction(this, location, direction));
    }

    @Override
    public String toString() {
        return "Sprout";
    }
}
