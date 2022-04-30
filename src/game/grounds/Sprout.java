package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actors.Goomba;
import game.grounds.JumpableGround;
import game.grounds.Sapling;
import game.grounds.Tree;
import game.item.Coin;

import static game.Status.*;

/**
 * Class representing Sprout the first stage of a Tree.
 */
public class Sprout extends Tree implements JumpableGround {
    private int value;

    /**
     * Constructor
     */
    public Sprout(){
        super('+');
        value = 0;
    }

    /**
     * Sprout spawns Goomba and grow into Sapling with the passage of time
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (this.hasCapability(RESETTABLE)) {
            if (Math.random() <= 0.5) {
                location.setGround(new Dirt());
            }
            this.removeCapability(RESETTABLE);
            return;
        }
        //Grows into sapling after 10 turns
        value += 1;
        if(value % 10 == 0){
            location.setGround(new Sapling());
        }
        //spawns goomba
        else{
            if(Math.random() <= 0.1 && !location.containsAnActor()){
                location.addActor(new Goomba());
            }
        }
    }

    /**
     * Implement the criteria and consequences of jumping a Sprout
     *
     * @param act indicate who jumps the jumpable ground
     * @param at to indicate where in the game map the jumpable ground is jumped
     * @param map represents the map where jump is taking place
     *
     * @return string indicating if player succeeds or fails while jumping
     */
    @Override
    public String jumped(Actor act, Location at, GameMap map) {
        Actor actor = act;
        if (actor.hasCapability(INVINCIBLE)) {
            map.moveActor(act,at); //moves actor on a successful jump
            at.setGround(new Dirt());
            at.addItem(new Coin(5));
            return actor + " had moved to (" + at.x() + "," + at.y() + ")!";
        }
        if (actor.hasCapability(TALL)) {
            map.moveActor(act,at); //moves actor on a successful jump
            return actor + " had a successfully jump at Sprout(" + at.x() + "," + at.y() + ")!";
        }
        if(Math.random() <= 0.9) {
            map.moveActor(act,at); //moves actor on a successful jump
            return actor + " had a successfully jump at Sprout(" + at.x() + "," + at.y() + ")!";
        }
        else {
            actor.hurt(10); //damages actor on an unsuccessful jump
            return actor + " fails to jump the Sprout, faced a 10 fall damage!";
        }
    }

    /**
     * Method to check if a particular actor is allowed to enter a ground
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {

        return super.canActorEnter(actor);
    }

    /**
     * Adds the jump action to the action list of player
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     *
     * @return actionList of player
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        return new ActionList(new JumpAction(this, location, direction));
    }

    /**
     * To display the name of class
     * @return String representing the class name
     */
    @Override
    public String toString() {
        return "Sprout";
    }

}
