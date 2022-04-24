package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

public class Mature extends Tree{
    private int value;
    private boolean fertile_soil_around = true;

    public Mature(){
        super('T');
        value = 0;
    }

    @Override
    public void tick(Location location) {
        value += 1;
        if(Math.random() <= 0.2) {
            location.setGround(new Dirt());
        }
        else{
            if (value % 5 == 0 && fertile_soil_around) {
                boolean isFertile = false;
                for (Exit item : new ArrayList<Exit>(location.getExits())) { // Copy the list in case the item wants to leave
                    Location by = item.getDestination();
                    Ground gr = by.getGround();
                    char ch = gr.getDisplayChar();
                    if(ch=='.'){
                        by.setGround(new Sprout());
                        isFertile = true;
                        break;
                    }
                }
                if(isFertile == false){
                    fertile_soil_around = false;
                }
            }
        }
    }
}
