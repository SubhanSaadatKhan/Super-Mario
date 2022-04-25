package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public abstract class Enemy extends Actor {

    public Enemy(String name, char displayChar, int hitPoints){
        super(name,displayChar,hitPoints);
    }

    @Override
    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);
}
