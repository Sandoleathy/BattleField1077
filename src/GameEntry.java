import display.GameScreen;
import display.MenuScreen;
import model.Game;
import model.GameManager;
import model.listener.PlayerListener;

import javax.swing.*;
import java.awt.*;

public class GameEntry {
    public static void  main(String[] arg){
        JFrame mainDisplay = new JFrame();
        mainDisplay.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        mainDisplay.setResizable(false);
        mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainDisplay.setTitle("BattleField 1077");
        mainDisplay.setLocationRelativeTo(null);

        PlayerListener playerListener = new PlayerListener();
        mainDisplay.addKeyListener(playerListener);
        mainDisplay.addMouseMotionListener(playerListener);
        mainDisplay.addMouseListener(playerListener);
        Game battlefield1077 = new Game(playerListener);
        GameScreen gameScreen = new GameScreen(battlefield1077);
        MenuScreen menuScreen = new MenuScreen();
        GameManager manager = new GameManager(battlefield1077 , mainDisplay , menuScreen , gameScreen);
        mainDisplay.setVisible(true);
        manager.run();
    }
}
