package game.grounds.jumpablegrounds.teleportablegrounds;

import edu.monash.fit2099.engine.positions.Location;

import java.time.LocalDate;

public class TeleportManager {

    private static TeleportManager instance;
    private Location destination;

    private TeleportManager(){
        this.destination = null;
    }

    public static TeleportManager getInstance() {
        if (instance == null) {
            instance = new TeleportManager();
        }
        return instance;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }
}
