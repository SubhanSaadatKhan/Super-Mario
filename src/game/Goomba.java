package game;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {
//	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
	private Map<Integer, Behaviour> behaviours = new TreeMap<>();

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 50);
		this.behaviours.put(0,new AttackBehaviour());
		this.behaviours.put(2, new WanderBehaviour());

	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {

		return new IntrinsicWeapon(10, "kick");
	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		this.behaviours.put(1, new FollowBehaviour(otherActor));
		return super.allowableActions(otherActor,direction,map);
	}

	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if(Math.random() <= 0.1){
			map.removeActor(this);
			return new DoNothingAction();
		}
		for(Behaviour Behaviour : behaviours.values()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

}
