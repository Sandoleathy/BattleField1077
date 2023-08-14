package model;

import Entity.*;
import Entity.Weapon.Bullets.Bullet;
import Entity.Weapon.Bullets.FlyBullets;
import Entity.Weapon.GoldenAK;
import model.listener.PlayerListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 此类存储所有的关卡信息，battlefield列表存储所有的关卡
 * selectedMap表示当前所选的关卡，开始游戏时加载所选关卡的所有文件
 */
public class Game {
    //尽管监听器是由Player对象操作，但是Game类中也得保留这个监听器以便传输
    private PlayerListener playerListener;
    private Player player;
    LinkedList<SCAV> botList;//所有的人机
    LinkedList<FlyBullets> bulletList;//飞行子弹的列表
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 768;
    private static final Rectangle SCREEN_BOUNDS = new Rectangle(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
    private boolean isPause = false;
    private ArrayList<BattleField> mapList;//所有的地图
    private BattleField selectedMap;//当前选择的地图
    private boolean isGameOver;
    public Game(PlayerListener listener){
        this.playerListener = listener;
        testGame();
        //startNewGame();
    }
    public BattleField selectMap(BattleField b){
        selectedMap = b;
        return b;
    }
    public void testGame(){
        botList = new LinkedList<>();
        bulletList = new LinkedList<>();
        player = new Player("player" , 2 , 100 , 50 , playerListener);
        player.setPosition(50,50);
        player.equipMainWeapon(new GoldenAK());
        player.switchWeapon();
        SCAV bot1 = new SCAV("SCAV1" , 3 , 150);
        bot1.setPosition(100,150);
        botList.add(bot1);
    }
    public void startNewGame(){
        isGameOver = false;
        botList = new LinkedList<>();
        bulletList = new LinkedList<>();
        player = new Player("player" , 2 , 100 , 50 , playerListener);
    }
    public void checkFire(){
        if(playerListener.isClick()){
            System.out.println("(" + playerListener.getX() + " , " + playerListener.getY());
            Bullet b = player.useWeapon();
            if (b != null) {
                bulletList.add(
                        new FlyBullets(player.getMuzzleX() , player.getMuzzleY() , b.getDamage() ,
                                50 , b.getRange() , player.getAngle()));
            }
        }
    }
    public void updateGame(){
        player.move();
        checkFire();
        for(FlyBullets b : bulletList){
            b.move();
            /*
             *子弹飞的太快可能会直接穿过物体不造成伤害，这点需要注意，未来需要修复
             */
            if(player.isHit(b) && b.isAlive()){
                System.out.println(b.getName() + " hit " + player.getName());
                player.takeDamage(b.getDamage());
                b.destroy();
            }
            for(SCAV s : botList){
                if(s.isHit(b) && b.isAlive()){
                    System.out.println(b.getName() + " hit " + s.getName());
                    s.takeDamage(b.getDamage());
                    b.destroy();
                }
            }
        }
    }
    public Rectangle getScreenBounds(){
        return SCREEN_BOUNDS;
    }
    public LinkedList<SCAV> getBotList(){
        return botList;
    }
    public LinkedList<FlyBullets> getBulletList(){
        return bulletList;
    }
    public Player getPlayer(){
        return player;
    }
    public ArrayList<BattleField> getAllMap(){
        return mapList;
    }
    public BattleField getSelectedMap(){
        return selectedMap;
    }
    public PlayerListener getPlayerListener(){
        return playerListener;
    }
    public boolean isGameOver(){
        return isGameOver;
    }
}
