package display;

import Entity.Human;
import Entity.Player;
import Entity.SCAV;
import Entity.Weapon.Bullets.FlyBullets;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GameScreen extends JPanel {
    private Game game;
    public GameScreen(Game g){
        this.game = g;
    }
    @Override
    protected void paintComponent(Graphics g){
        if(game != null){
            Graphics2D g2d = (Graphics2D) g;
            clearScreen(g2d);
            drawUI(g2d);
            drawPlayer(g2d , game.getPlayer());
            drawBots(g2d);
            drawBullets(g2d , game.getBulletList());
        }
    }
    private void drawBullets(Graphics2D g , LinkedList<FlyBullets> bulletList){
        for(FlyBullets b : bulletList){
            if(b.isAlive()){
                g.setColor(Color.RED);
                g.drawLine(b.getX(),b.getY(),(int) (b.getFinalPos()[0]) , (int) (b.getFinalPos()[1]));
            }
        }
    }
    private void drawPlayer(Graphics2D g , Player p){
        int x = p.getX();
        int y = p.getY();
        g.setColor(Color.BLACK);
        g.fillOval(x-(Human.HUMAN_SIZE/2),y-(Human.HUMAN_SIZE/2),Human.HUMAN_SIZE,Human.HUMAN_SIZE);
        if(p.isEquipped()){
            //绘制武器
            int x2 = p.getMuzzleX();
            int y2 = p.getMuzzleY();
            g.drawLine(x,y,x2,y2);
        }
        //骨骼标记点
        g.drawRect(p.getHitBox().x,p.getHitBox().y,p.getHitBox().width,p.getHitBox().height);
        g.setColor(Color.RED);
        g.fillOval(x,y,2,2);
    }
    public void clearScreen(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
    }
    public void drawBots(Graphics2D g){
        LinkedList<SCAV> botList = game.getBotList();
        g.setColor(Color.BLUE);
        for(SCAV s : botList){
            g.fillOval(s.getX()-(Human.HUMAN_SIZE/2) , s.getY()-(Human.HUMAN_SIZE/2) , Human.HUMAN_SIZE , Human.HUMAN_SIZE);
            g.drawRect(s.getHitBox().x,s.getHitBox().y,s.getHitBox().width,s.getHitBox().height);
        }
    }
    public void drawUI(Graphics2D g){
        g.setColor(Color.BLACK);
        g.drawString(game.getPlayer().getWeaponInHand().getName() , Game.SCREEN_HEIGHT-100 , Game.SCREEN_HEIGHT-120);
        g.drawString(game.getPlayer().getWeaponInHand().getMagazine().ammoLeft() + "/" + game.getPlayer().getWeaponInHand().getMagazine().getCapacity(),
                Game.SCREEN_WIDTH-100,Game.SCREEN_HEIGHT-100);
    }
}
