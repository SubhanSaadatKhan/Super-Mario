package game.actors.players;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.ResetAction;
import game.effects.InvincibleEffect;
import game.items.consumables.magicalwaters.Bottle;
import game.items.portable.Coin;
import game.reset.Resettable;

import static game.Status.*;
//final
/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

    private final Menu menu = new Menu();
    private final Coin wallet; // Coins that the player has
    private int intrinsicWeaponDamage;
    private Bottle bottle;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        wallet = new Coin(0, false);
        intrinsicWeaponDamage = 5;
        this.addItemToInventory(wallet);
        bottle = null;
        this.registerInstance();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(RESETTABLE)) { // Reset the player
            if (this.hasCapability(TALL)) {
                this.removeCapability(TALL);
            }
            if (this.hasCapability(INVINCIBLE)) {
                this.removeItemFromInventory(new InvincibleEffect("INVINCIBLE", ' ', true));
            }
            this.heal(this.getMaxHp());
            this.removeCapability(RESETTABLE);
//			return menu.showMenu(this, actions, display);
        }
        if (ResetAction.available()) { // Add ResetAction to players action list if ResetAction has not been used
            actions.add(new ResetAction());
        }
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        if (this.hasCapability(INVINCIBLE)) { // Show the INVINCIBLE message if the player is INVINCIBLE
            display.println(this + " is INVINCIBLE");
        }
        Location location = map.locationOf(this); // Print the player's information
        display.println(this.getInfo() + " at (" + location.x() + ", " + location.y() + ")");
        display.println("wallet: $" + this.getWallet().getWorth());

        // return/print the console menu
        return menu.showMenu(this, actions, display);
    }

    /**
     * Accessor for the player's wallet
     *
     * @return the player's wallet
     */
    public Coin getWallet() {
        return wallet;
    }

    @Override
    public char getDisplayChar() {
        return this.hasCapability(TALL) ? Character.toUpperCase(super.getDisplayChar()) : super.getDisplayChar();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicWeaponDamage, "punches");
    }

    public int getIntrinsicWeaponDamage() {
        return intrinsicWeaponDamage;
    }

    public void setIntrinsicWeaponDamage(int intrinsicWeaponDamage) {
        this.intrinsicWeaponDamage = intrinsicWeaponDamage;
    }

    /**
     * Make the player resettable
     */
    @Override
    public void resetInstance() {
        this.addCapability(RESETTABLE);
    }

    /**
     * @return the basic information of the player
     */
    public String getInfo() {
        return this + this.printHp();
    }

    public boolean hasBottle() {
        return bottle != null;
    }

    public void getABottle(Bottle bottle) {
        this.bottle = bottle;
    }

    public Bottle getBottle() {
        return bottle;
    }
}
