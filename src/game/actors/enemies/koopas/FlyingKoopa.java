package game.actors.enemies.koopas;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


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
