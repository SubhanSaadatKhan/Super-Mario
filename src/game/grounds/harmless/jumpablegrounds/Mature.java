package game.grounds.harmless.jumpablegrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.koopas.BaseKoopa;
import game.actors.enemies.koopas.FlyingKoopa;
import game.actors.enemies.koopas.NormalKoopa;
import game.grounds.harmless.Dirt;
import game.items.portable.Coin;

import java.util.ArrayList;
import java.util.Random;

import static game.Status.*;
//final

/**
 * Class representing Mature the third stage of a Tree.
 */
public class Mature extends Tree {
    private int value;
    private boolean fertile_soil_around = true;
    private final Random random;

    /**
     * Constructor
     */
    public Mature() {
        super('T');
        value = 0;
        random = new Random();
    }

    /**
     * Sapling produces Sprout, spawns Koopa and turn into Dirt with the passage of time
     *
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
        //turns into dust
        value += 1;
        if (Math.random() <= 0.2) {
            location.setGround(new Dirt());
        } else {
            //Grows new sprouts on fertile ground
            if (value % 5 == 0 && fertile_soil_around) {
                ArrayList<Location> fertileLocations = new ArrayList<>();

                for (Exit item : new ArrayList<>(location.getExits())) { // Copy the list in case the item wants to leave
                    Location by = item.getDestination();
                    Ground gr = by.getGround();
                    char ch = gr.getDisplayChar();
                    if (ch == '.') {
                        fertileLocations.add(by);
                    }
                }
                if (!fertileLocations.isEmpty()) {
                    fertileLocations.get(random.nextInt(fertileLocations.size())).setGround(new Sprout());
                } else {
                    fertile_soil_around = false;
                }
            }
            //spawns koopa
            double valu = Math.random();
            if (valu <= 0.50 && !location.containsAnActor()) {

                if (valu <= 0.25) {
                    BaseKoopa normalKoopa = new NormalKoopa();
                    location.addActor(normalKoopa);
                } else {
                    BaseKoopa flyingKoopa = new FlyingKoopa();
                    location.addActor(flyingKoopa);
                }

            }
        }
    }

    /**
     * Method to check if a particular actor is allowed to enter a ground
     *
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {

        return super.canActorEnter(actor);
    }

    /**
     * Implement the criteria and consequences of jumping a Mature
     *
     * @param act indicate who jumps the jumpable ground
     * @param at  to indicate where in the game map the jumpable ground is jumped
     * @param map represents the map where jump is taking place
     * @return string indicating if player succeeds or fails while jumping
     */
    @Override
    public String jumped(Actor act, Location at, GameMap map) {
        if (act.hasCapability(INVINCIBLE)) {
            map.moveActor(act, at); //moves actor on a successful jump
            at.setGround(new Dirt());
            at.addItem(new Coin(5));
            return act + " had moved to (" + at.x() + "," + at.y() + ")!";
        }
        if (act.hasCapability(TALL)) {
            map.moveActor(act, at); //moves actor on a successful jump
            return act + " had a successfully jump at Sprout(" + at.x() + "," + at.y() + ")!";
        }
        if (Math.random() <= 0.7) {
            map.moveActor(act, at); //moves actor on a successful jump
            return act + " had a successfully jump at Mature(" + at.x() + "," + at.y() + ")!";
        } else {
            act.hurt(30); //damages actor on an unsuccessful jump
            return act + " fails to jump the Mature,faced a 30 fall damage!";
        }
    }

    /**
     * Adds the jump action to the action list of player
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actionList of player
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {

        return super.allowableActions(actor, location, direction);
    }

    /**
     * To display the name of class
     *
     * @return String representing the class name
     */
    @Override
    public String toString() {
        return "Mature";
    }
}
