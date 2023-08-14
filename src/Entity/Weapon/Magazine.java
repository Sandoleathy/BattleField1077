package Entity.Weapon;

import java.util.Stack;
import Entity.Weapon.Bullets.*;
import iface.*;

public class Magazine implements iItem {
    private String name;
    private int capacity;
    private Stack<Bullet> magazine = new Stack<>();
    private boolean isFull;
    private double size;
    public Magazine(String name , int capacity , double size){
        this.name = name;
        this.capacity = capacity;
        this.isFull = false;
        this.size = size;
    }
    public int pushBullet(Bullet bullet){//向弹夹内压子弹
        if(magazine.size() >= this.capacity){
            System.out.println("magazine is full");
            isFull = true;
            return magazine.size();
        }
        magazine.push(bullet);
        return magazine.size();
    }
    public Bullet popBullet(){//弹出弹夹最上面的子弹
        if(magazine.isEmpty()){
            System.out.println("magazine is empty!");
            return null;
        }
        Bullet bullet = magazine.pop();
        if(isFull()){
            isFull = false;
        }
        return bullet;
    }
    public int ammoLeft(){
        return magazine.size();
    }
    public int getCapacity(){
        return capacity;
    }
    @Override
    public String getName(){
        return name;
    }

    @Override
    public double getSize() {
        return size;
    }

    public boolean isFull(){
        return isFull;
    }
    public boolean isEmpty(){
        return magazine.isEmpty();
    }
}
