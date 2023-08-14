package iface;

import Entity.Weapon.Bullets.Bullet;
import Entity.Weapon.Magazine;

public interface iWeapon extends iItem{
    public Bullet fire();
    public Magazine reload(Magazine m);
    public String getName();
    public Magazine getMagazine();
}
