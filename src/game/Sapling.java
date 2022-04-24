package game;

import edu.monash.fit2099.engine.positions.Location;

public class Sapling extends Tree{
    private int value;

    public Sapling(){
        super('t');
        value = 0;
    }

    @Override
    public void tick(Location location) {
        value += 1;
        if(value % 10 == 0){
            location.setGround(new Mature());
        }
        else{
            if(Math.random() <= 0.1){
                location.addItem(new Coin(20));
            }
        }
    }


}
