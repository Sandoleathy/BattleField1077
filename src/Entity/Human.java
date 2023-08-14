package Entity;

import Entity.Weapon.Bullets.Bullet;
import Entity.Weapon.Bullets.FlyBullets;
import Entity.Weapon.Magazine;
import iface.*;

import java.awt.*;
import java.util.LinkedList;

public class Human implements iBarrier{
    public static int HUMAN_SIZE = 10;
    private String name;//小人的名称
    private int speed;//移速
    private int HP;//生命值
    protected int x;
    protected int y;
    protected Rectangle hitBox;
    private boolean isAlive;
    private LinkedList<iItem> items;//存储物品的物品栏
    private LinkedList<Bullet> bullets;//存储子弹的物品栏
    private LinkedList<Magazine> magazines;//存储弹夹的物品栏
    private double inventorySize;//物品栏的存储上限
    private double currentSize;//当前物品栏大小
    private iWeapon mainWeapon;//主副武器
    private iWeapon subWeapon;
    private iWeapon weaponInHand;
    public Human(String name , int speed , int HP , double inventorySize){
        this.name = name;
        this.speed = speed;
        this.HP = HP;
        this.isAlive = true;
        this.inventorySize = inventorySize;
        this.currentSize = 0;
        this.hitBox = new Rectangle();
        items = new LinkedList<>();
        bullets = new LinkedList<>();
        magazines = new LinkedList<>();
        mainWeapon = null;
        subWeapon = null;
    }
    public void takeDamage(int damage){
        System.out.println(name + " is taking damage");
        HP = HP - damage;
        if(HP <= 0){
            System.out.println(name + " is dead");
            isAlive = false;
        }
    }
    /**
     * 装备武器
     */
    public void equipMainWeapon(iWeapon w){
        this.mainWeapon = w;
    }
    public void equipSubWeapon(iWeapon w){
        this.subWeapon = w;
    }
    /**
     * 切换武器
     */
    public void switchWeapon(){
        if(weaponInHand == mainWeapon){
            weaponInHand = subWeapon;
        }else{
            weaponInHand = mainWeapon;
        }
    }
    public Bullet useWeapon(){
        if(weaponInHand != null){
            Bullet bullet = weaponInHand.fire();
            return bullet;
        }else{
            return null;
        }
    }
    /**
     * 存取物品的方法
     * 存取成功返回true
     * 如果物品栏空间不够则返回false
     */
    public boolean storeMagazine(Magazine m){
        if(currentSize + m.getSize() > inventorySize){
            System.out.println("Inventory's space is not enough!");
            return false;
        }
        magazines.add(m);
        currentSize = currentSize + m.getSize();
        return true;
    }
    public boolean storeBullet(Bullet b){
        if(currentSize + b.getSize() > inventorySize){
            System.out.println("Inventory's space is not enough!");
            return false;
        }
        bullets.add(b);
        currentSize = currentSize + b.getSize();
        return true;
    }
    public boolean storeItem(iItem i){
        if(currentSize + i.getSize() > inventorySize){
            System.out.println("Inventory's space is not enough!");
            return false;
        }
        items.add(i);
        currentSize = currentSize + i.getSize();
        return true;
    }
    private Magazine removeMagazine(Magazine m){
        boolean result = magazines.remove(m);
        if(!result){
            System.out.println("remove error");
        }else{
            currentSize = currentSize - m.getSize();
        }
        return m;
    }
    private Bullet removeBullet(Bullet b){
        boolean result = bullets.remove(b);
        if(!result){
            System.out.println("remove error");
        }else{
            currentSize = currentSize - b.getSize();
        }
        return b;
    }
    private iItem removeItem(iItem i){
        boolean result = items.remove(i);
        if(!result){
            System.out.println("remove error");
        }else{
            currentSize = currentSize - i.getSize();
        }
        return i;
    }
    public Magazine drop(Magazine m){
        removeMagazine(m);
        return m;
    }
    public Bullet drop(Bullet b){
        removeBullet(b);
        return b;
    }
    public iItem drop(iItem i){
        removeItem(i);
        return i;
    }
    public void setPosition(int x , int y){
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x-(HUMAN_SIZE/2) , y-(HUMAN_SIZE/2) , HUMAN_SIZE ,HUMAN_SIZE);
    }
    protected void setHitBox(){
        hitBox = new Rectangle(x-(HUMAN_SIZE/2) , y-(HUMAN_SIZE/2) , HUMAN_SIZE ,HUMAN_SIZE);
    }
    public boolean isHit(FlyBullets b){
        if(b.getHitBox().intersects(this.hitBox)){
            return true;
        }
        return false;
    }

    /**
     * get方法合集
     */
    public int getHP(){
        return this.HP;
    }
    public String getName(){
        return this.name;
    }
    public int getSpeed(){
        return this.speed;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public Rectangle getHitBox(){
        return hitBox;
    }
    public double getInventorySize(){
        return inventorySize;
    }
    public double getCurrentSize(){
        return currentSize;
    }
    public boolean isEquipped(){//是否装备武器
        if(weaponInHand != null){
            return true;
        } else{
            return false;
        }
    }
    public iWeapon getWeaponInHand(){
        return weaponInHand;
    }
}
