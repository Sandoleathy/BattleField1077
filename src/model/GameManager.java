package model;

import javax.swing.*;
import java.awt.*;

public class GameManager {
    private JFrame mainDisplay;
    private JPanel mainPanel;
    private JPanel menuScreen;
    private JPanel gameScreen;
    private Game game;
    private CardLayout cardLayout;
    public GameManager(Game game , JFrame display , JPanel menuScr , JPanel gameScr){
        this.game = game;
        mainDisplay = display;
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        gameScreen = gameScr;
        menuScreen = menuScr;
        mainDisplay.getContentPane().add(mainPanel);
        mainPanel.add(menuScreen , "Menu Screen");
        mainPanel.add(gameScreen , "Game Screen");
    }
    public void run(){
        //计算固定帧数下每一帧所需的时间
        long fpsTime = (long) ((Double.valueOf(1000) / Double.valueOf(30)) * 1000000);
        long now = 0;//绘制图像前的时间戳
        long total = 0;//每次绘制所用时间
        cardLayout.show(mainPanel, "Game Screen");
        while(!game.isGameOver()){
            now = System.nanoTime();
            game.updateGame();
            gameScreen.paintImmediately(game.getScreenBounds());
            try{
                total = System.nanoTime() - now;
                if(total > fpsTime){//如果绘制时间超过fpsTime则说明已经超时，立刻绘制下一帧
                    continue;
                }
                Thread.sleep((fpsTime - (System.nanoTime() - now)) / 1000000);//没有超时则休眠到预定时间绘制下一帧
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
