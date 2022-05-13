package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;

import java.util.Map;
import java.util.TreeMap;

import static game.Status.HOSTILE_TO_ENEMY;

public class Bowser extends Actor {
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    public Bowser(){
        super("Bowser", 'B', 500);
        this.behaviours.put(0, new AttackBehaviour());
    }
    /**
     * Creates a new IntrinsicWeapon
     *
     * @return
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {

        return new IntrinsicWeapon(80, "punch");
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.behaviours.put(1, new FollowBehaviour(otherActor)); //attack action takes place thus follow behaviour implemented
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }

        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
