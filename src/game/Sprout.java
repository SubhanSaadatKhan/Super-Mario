package game;

import edu.monash.fit2099.engine.positions.Location;

public class Sprout extends Tree {
    private int value;

    public Sprout(){
        super('+');
        value = 0;
    }

    @Override
    public void tick(Location location) {
        value += 1;
        if(value % 10 == 0){
            location.setGround(new Sapling());
        }
        else{
            if(Math.random() <= 0.1 && !location.containsAnActor()){
                location.addActor(new Goomba());
            }
        }
    }


}
