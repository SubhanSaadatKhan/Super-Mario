package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.Map;
import java.util.TreeMap;

public class Koopa extends Enemy{

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(0,new AttackBehaviour());
        this.behaviours.put(2, new WanderBehaviour());
    }

    public void changeDisplayChar(char displayChar){
        super.setDisplayChar(displayChar);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "punch");
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
//        if(this.getDisplayChar()=='D'){
//            return
//        }
//        else{
//            this.behaviours.put(1, new FollowBehaviour(otherActor));
//            return super.allowableActions(otherActor,direction,map);
//        }
        this.behaviours.put(1, new FollowBehaviour(otherActor));
        return super.allowableActions(otherActor,direction,map);

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            if(this.getDisplayChar()=='K'){
                this.setDisplayChar('D');
            }
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
