package Entity.Weapon;

import Entity.Weapon.Bullets.Bullet;
import iface.*;

public class Gun implements iWeapon, iItem {
    private Magazine magazine;
    private String name;
    private double size;
    public Gun(String name , double size){
        this.name = name;
        this.size = size;
        this.magazine = null;
    }

    /**
     * 如果子弹打空返回true
     * 其次返回false
     */
    @Override
    public Bullet fire(){//开火
        if(!magazine.isEmpty()){
            System.out.println("Bang!");
        }
        Bullet bullet = magazine.popBullet();
        return bullet;
    }

    /**
     * 换上新弹夹并且返回上一个弹夹
     */
    @Override
    public Magazine reload(Magazine m){
        Magazine currentMagazine;
        currentMagazine = this.magazine;
        this.magazine = m;
        return currentMagazine;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public Magazine getMagazine() {
        return magazine;
    }
}
