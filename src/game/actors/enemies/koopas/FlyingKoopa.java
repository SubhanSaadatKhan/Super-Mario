package game.actors.enemies.koopas;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actors.enemies.koopas.BaseKoopa;
import game.behaviours.Behaviour;

import static game.Status.DORMANT;
import static game.Status.RESETTABLE;

/**
 * A class representing Flying Koopa
 */
public class FlyingKoopa extends BaseKoopa {

    /**
     * Constructor.
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        setIntrinsicWeaponDamage(30);
    }

    /**
     * Creates a new IntrinsicWeapon
     *
     * @return
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getIntrinsicWeaponDamage(), "punch");
    }


}
