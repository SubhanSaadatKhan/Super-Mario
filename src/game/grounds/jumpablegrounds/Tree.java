package game.grounds.jumpablegrounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;

import static game.Status.RESETTABLE;

/**
 * An Abstract Class representing the Tree.
 */
public abstract class Tree extends Ground implements Resettable {
    /**
     * Constructor
     * @param treeStage
     */
    public Tree(char treeStage) {
        super(treeStage);
        this.registerInstance();
    }

    /**
     * Different stages of Tree can experience change with the passage of time.
     * @param location The location of the Ground
     */
    @Override
    public abstract void tick(Location location);

    /**
     * Method to check if a particular actor is allowed to enter a ground
     * @param actor the Actor to check
     * @return true (if actor not player)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.getDisplayChar()=='m'){ //player cannot enter tree without jumping
            return false;
        }
        return true;
    }

    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
    }
}
