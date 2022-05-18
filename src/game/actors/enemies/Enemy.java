package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.DrinkWaterBehaviour;
import game.reset.Resettable;

import java.util.Map;
import java.util.TreeMap;

import static game.Status.*;
//final
/**
 * An abstract Class representing Enemy of the player.
 */
public abstract class Enemy extends Actor implements Resettable {

    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();
    protected int intrinsicWeaponDamage;

    /**
     * Constructor
     *
     * @param name        Enemy name
     * @param displayChar Enemy char
     * @param hitPoints   Enemy HP
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(2, new DrinkWaterBehaviour());
        this.registerInstance();
    }

    /**
     * Adds the Attack action to the actionlist of player
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(HOSTILE_TO_ENEMY) && this.getDisplayChar()!='D') {
            actions.add(new AttackAction(this,direction));
        }
        else if (this.getDisplayChar()=='D' && otherActor.hasCapability(DESTRUCTIVE)){
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Make the Enemy object resettable
     */
    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
    }

    public int getIntrinsicWeaponDamage() {
        return intrinsicWeaponDamage;
    }

    public void setIntrinsicWeaponDamage(int intrinsicWeaponDamage) {
        this.intrinsicWeaponDamage = intrinsicWeaponDamage;
    }
}
