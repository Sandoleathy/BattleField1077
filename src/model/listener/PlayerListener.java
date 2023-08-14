package model.listener;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class PlayerListener extends MouseAdapter implements KeyListener{
    /**
     * wasd控制移动
     * 左键射击
     * R重新装填
     * E交互
     * 1切换武器
     */
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private boolean mouseClick;
    private boolean ESC;
    private boolean R;
    private boolean E;
    private boolean switchWeapon;
    private int x,y;
    public boolean isUp(){
        return up;
    }
    public boolean isDown(){
        return down;
    }
    public boolean isRight(){
        return right;
    }
    public boolean isLeft(){
        return left;
    }
    public boolean isClick(){
        return mouseClick;
    }
    public boolean isR(){
        return R;
    }
    public boolean isE(){
        return E;
    }
    public boolean isESC(){
        return ESC;
    }
    public boolean isSwitchWeapon(){
        return switchWeapon;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    private Set<Integer> pressed = new HashSet<Integer>();

    /**
     * 使用集合的Contains方法检测按键
     * 问题是每次都必须遍历整个集合 因此每个按键的检测复杂度都是O(n)
     * 按键过多可能会导致卡顿
     */
    private void setKeyValue(){
        if(pressed.contains(KeyEvent.VK_W)){
            up = true;
        }else{
            up = false;
        }
        if(pressed.contains(KeyEvent.VK_S)){
            down = true;
        }else{
            down = false;
        }
        if(pressed.contains(KeyEvent.VK_D)){
            right = true;
        }else{
            right = false;
        }
        if(pressed.contains(KeyEvent.VK_A)){
            left = true;
        }else{
            left = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 使用同步锁检测多个按键
     * 不知道能不能成功
     * 同步锁可能会导致线程卡死
     * @param e the event to be processed
     */
    @Override
    public synchronized void keyPressed(KeyEvent e) { //使用同步锁一次性捕捉多个按键
        pressed.add(e.getKeyCode());
        setKeyValue();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
        setKeyValue();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        this.x = e.getX();
        this.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }
    @Override
    public void mousePressed(MouseEvent e){
        mouseClick = true;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        mouseClick = false;
    }
}
