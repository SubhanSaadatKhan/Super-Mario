package game.grounds.harmless.jumpablegrounds.teleportablegrounds;

import edu.monash.fit2099.engine.positions.Location;
/**
 * Special Class that manages Teleportation action
 */
public class TeleportManager {

    private static TeleportManager instance;
    private Location destination;

    /**
     * Constructor
     */
    private TeleportManager(){
        this.destination = null;
    }

    /**
     * Instance provider
     * @return
     */
    public static TeleportManager getInstance() {
        if (instance == null) {
            instance = new TeleportManager();
        }
        return instance;
    }

    /**
     * retrives location of player when he teleported from GameMap
     * @return
     */
    public Location getDestination() {
        return destination;
    }

    /**
     * stores location of player in GameMap
     * @param destination
     */
    public void setDestination(Location destination) {
        this.destination = destination;
    }
}
