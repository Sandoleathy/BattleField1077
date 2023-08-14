package Entity;

import model.listener.PlayerListener;

public class Player extends Human{
    private PlayerListener listener;
    private double angle;
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
        this.angle = countAngle();
        muzzleX = x + (int) (getWeaponInHand().getSize() * Math.cos(angle));
        muzzleY = y + (int) (getWeaponInHand().getSize() * Math.sin(angle));
        super.setHitBox();
    }

    /**
     * 首先获取鼠标指向和人物位置的相对位置
     * 利用向量长度公式计算出人物位置到鼠标的距离
     * 距离除以相对位置坐标即是方向向量
     * @return directionVector
     */
    private double countAngle(){//计算瞄准鼠标方向所需要旋转的角度
        int dx = (listener.getX()-7) - this.x;
        int dy = (listener.getY()-29) - this.y;
        double angle = Math.atan2(dy,dx);
        return angle;
    }
    @Override
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getMuzzleX(){
        return muzzleX;
    }
    public int getMuzzleY(){
        return muzzleY;
    }
    public PlayerListener getListener(){
        return listener;
    }
    public double getAngle(){
        return angle;
    }
}
