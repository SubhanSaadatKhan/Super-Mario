package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public abstract class Tree extends Ground {

    /**
     * Constructor.
     *
     */
    public Tree(char treeStage) {
        super(treeStage);
    }

    @Override
    public void tick(Location location) {
    }


}
