package Entity;

import model.listener.PlayerListener;

import java.awt.*;

public class Player extends Human{
    private int[] direction = new int[2];//鼠标指向(x,y)
    private PlayerListener listener;
    private double[] directionVector;
    private int muzzleX;//枪口的坐标
    private int muzzleY;
    public Player(String name , int speed , int HP , int inventorySize , PlayerListener listener){
        super(name,speed,HP,inventorySize);
        muzzleX = super.x + HUMAN_SIZE;
        muzzleY = super.y + HUMAN_SIZE;
        this.listener = listener;
    }

    /**
     * 这个方法应该是控制一帧当中玩家所有动作的方法
     */
    public void move(){
        if(listener.isUp()){
            y = y - super.getSpeed();
        }
        if(listener.isDown()){
            y = y + super.getSpeed();
        }
        if(listener.isLeft()){
            x = x - super.getSpeed();
        }
        if(listener.isRight()){
            x = x + super.getSpeed();
        }
        this.directionVector = countDirectionVector();
        muzzleX = x + (int) (directionVector[0] * getWeaponInHand().getSize());
        muzzleY = y + (int) (directionVector[1] * getWeaponInHand().getSize());
        super.setHitBox();
    }

    /**
     * 首先获取鼠标指向和人物位置的相对位置
     * 利用向量长度公式计算出人物位置到鼠标的距离
     * 距离除以相对位置坐标即是方向向量
     * @return directionVector
     */
    private double[] countDirectionVector(){//获取方向向量
        direction[0] = listener.getX();
        direction[1] = listener.getY();
        int x = direction[0] - this.x;
        int y = direction[1] - this.y;
        if(x==0 || y==0){
            x = 1;
            y = 1;
        }
        double length = Math.sqrt((x*x) + (y*y));//开根号
        //double length = Math.pow((x*x)+(y*y) , 0.5);//取0.5次方
        double[] directionVector = new double[2];
        directionVector[0] = x/length;
        directionVector[1] = y/length;
        return directionVector;
    }
    @Override
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public double[] getDirectionVector() {
        return directionVector;
    }
    public int getMuzzleX(){
        return muzzleX;
    }
    public int getMuzzleY(){
        return muzzleY;
    }
}
