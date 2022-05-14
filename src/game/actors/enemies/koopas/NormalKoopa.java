package game.actors.enemies.koopas;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
//final
/**
 * Class representing Koopa a little turtle.
 */
public class NormalKoopa extends BaseKoopa {


    /**
     * Constructor.
     */
    public NormalKoopa() {
        super("Koopa", 'K', 100);
    }

    /**
     * Creates a new IntrinsicWeapon
     *
     * @return
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }
}
