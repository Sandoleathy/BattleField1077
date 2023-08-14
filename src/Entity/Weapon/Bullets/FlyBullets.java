package Entity.Weapon.Bullets;

import java.awt.*;

public class FlyBullets extends Bullet{//飞行中的子弹，主要负责将计算移动及碰撞
    private boolean isAlive;
    private int x,y;
    private int speed;
    private double[] directionVector;
    private double[] finalPos = new double[2];
    protected Rectangle hitBox;
    public static int BULLET_SIZE = 2;
    public FlyBullets(int x , int y , int damage , int speed , int range , double[] directionVector) {
        super("flying bullet", 0 , damage , range);
        this.isAlive = true;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.directionVector = directionVector;
        finalPos[0] = x;
        finalPos[1] = y;
        hitBox = new Rectangle(x , y ,BULLET_SIZE , BULLET_SIZE);
    }

    /**
     * finalPos指的是子弹射出去弹头的位置，而子弹尾部则是子弹的x，y坐标
     */
    public void move(){
        //更新子弹的位置
        x = (int)finalPos[0];
        y = (int)finalPos[1];
        finalPos[0] = x + (directionVector[0] * ((double) this.speed /10));
        finalPos[1] = y + (directionVector[1] * ((double) this.speed /10));
        hitBox = new Rectangle(x,y,BULLET_SIZE,BULLET_SIZE);
    }
    public void destroy(){
        isAlive = false;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getSpeed(){
        return speed;
    }
    public boolean isAlive(){
        return isAlive;
    }
    public double[] getFinalPos(){
        return finalPos;
    }
    public Rectangle getHitBox(){
        return hitBox;
    }
}
