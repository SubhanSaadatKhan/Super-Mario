package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE, // the status of the player who consumed Power Star
    RESETTABLE, // the status of actors or items to be reset
    DORMANT, // the status of Koopa who is defeated
    DESTRUCTIVE // the status that can destroy Koopa's shell
}
