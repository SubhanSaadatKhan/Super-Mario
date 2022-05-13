package game.actors.friendlyactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradingAction;
import game.items.Coin;
import game.items.consumables.PowerStar;
import game.items.consumables.SuperMushroom;
import game.items.weapons.Wrench;

import static game.Status.INTERACTWITHPRINCESS;
import static game.Status.RESETTABLE;

public class PrincessPeach extends Actor {

    public PrincessPeach(){
        super("Princess Peach",'P',1);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(INTERACTWITHPRINCESS)){
            //TODO
//            actions.add(new SpeakingAction()));
        }

        return actions;
    }




    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
