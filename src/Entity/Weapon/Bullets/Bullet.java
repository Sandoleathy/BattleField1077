package Entity.Weapon.Bullets;

import iface.iItem;
public class Bullet implements iItem {
    private String name;
    private double size;
    private int damage;//伤害
    private int range;//射程
    public Bullet(String name , double size , int damage , int range){
        this.name = name;
        this.size = size;
        this.damage = damage;
        this.range = range;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSize() {
        return size;
    }
    public int getDamage(){
        return damage;
    }
    public int getRange(){
        return range;
    }
}
