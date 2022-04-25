package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Wall extends Ground implements JumpableGround {

	public Wall() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}



	@Override
	public String jumped(Actor act, Location at, GameMap map) {
		Actor actor = act;
		if(Math.random() <= 0.8) {
			map.moveActor(act,at);
			return actor + " had a successfully jump at Wall(" + at.x() + "," + at.y() + ")!";
		}
		else {
			actor.hurt(20);
			return actor + " fails to jump the Wall, faced a 20 fall damage!";
		}
	}

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction){
		return new ActionList(new JumpAction(this, location, direction));
	}

	@Override
	public String toString() {
		return "Wall";
	}

}
