package stealmysheep.common.services;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Projectile;
import stealmysheep.common.game.Point;
import stealmysheep.common.game.World;

public interface IWeaponsService {
    /**
     * Fires a ranged weapon. The wielder must have a RangedWeapon.
     * Does not deal with any reload or cooldown logic.
     *
     * @param alignment if the projectile belongs to the player or the enemies
     */
    void fireWeapon(World world, Entity wielder, Projectile.Alignment alignment, Point target);
    void swingWeapon(World world, Entity wielder, Point target);
}
