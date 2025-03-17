package org.game;

import org.ai.Pathfinder;
import org.alien.*;

import javax.swing.JPanel;

import org.entity.Player;
import org.maps.MapManager;
import org.object.SuperObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Represents the main game screen, managing game state, player input, objects, and rendering.
 * <p>
 * The {@code GameScreen} class extends JPanel and implements the Runnable interface, serving as the
 * primary container for the game. It handles game state transitions, player input, updates, and
 * rendering of game elements.
 * <p>
 * The class defines constants for screen dimensions, world dimensions, and game states. It includes
 * a game loop in the {@code run} method, which periodically updates and repaints the screen.
 * <p>
 * The class manages various game elements, including the player, aliens, objects, maps, and user interface.
 * 
 * @author Nicole Malku
 * @author Usman Aziz
 * @author Ryan Chong
 * @author Curtis Pu
 * @version 1.0
 */
public class GameScreen extends JPanel implements Runnable{
    
    //SETTINGS
    final int orgTileSize = 22; //22*22 pixel

    final int scale = 2;

    public final int tileSize = orgTileSize * scale; //44*44 pixel tiles
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize* maxScreenCol; // 924 pixels
    public final int screenHeight = tileSize *maxScreenRow; //528 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 6;
    public int currentMap = 0;
    public String currentMapIMGpath;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldCol;


    // initializing objects and threads
    KeyboardInput keyInput = new KeyboardInput(this);
    Sound music = new Sound();
    Sound sfx = new Sound();
    public collisionChecker cChecker = new collisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public PortalHandler portalHandler = new PortalHandler(this);
    public Pathfinder pFinder = new Pathfinder(this);
    Thread gameThread;
    public Player player = new Player(this, keyInput);
    public MapManager mapM = new MapManager(this);
    public SuperObject obj[] = new SuperObject[20];
    public Alien alien[] = new Alien[20];
    public Portal portal[] = new Portal[20];
    
    //initializing variables
    int fps = 60;
    public int gameState;
    public final int titleState = 0;
    public final int playState1 = 1;
    public final int playState2 = 2;
    public final int playState3 = 3;
    public final int playState4 = 4;
    public final int playState5 = 5;
    public final int playState6 = 6;
    public final int pauseState = 7;
    public final int winState = 8;
    public final int lossState = 9;
    public int latestPlayState;
    public UI ui = new UI(this);
    


    /**
     * Creates a new instance of the {@code GameScreen} class.
     * <p>
     * Initializes the panel with the specified dimensions, background color, and sets it as focusable
     * to receive keyboard input.
     * 
     * @author Nicole Malku
     */
    public GameScreen(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PINK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObjectMap(1);
        startMusic(4);
        //gameState = playState;
        gameState = titleState;
    }

    public void restart(){
        stopMusic();
        startSFX(6);
        try {
            Thread.sleep(700);              
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        startMusic(4);
        player.setDefaultPositions();
        player.restoreLife();
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The game loop responsible for updating and rendering the game.
     * <p>
     * This method calculates the time intervals for updates and repaints to achieve a specified
     * frames-per-second (fps) rate. It continuously updates game elements and repaints the screen
     * until the game thread is interrupted.
     * 
     * @author Nicole Malku
     */
    @Override
    public void run() { // create game loop
        double drawInterval = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            update(); // 1. update info like character position
            repaint(); // 2. draw the screen with the updated info
            try{
                double remainingTime = nextDrawTime- System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime<0){
                    remainingTime =0;
                }

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e){
                e.printStackTrace();;
            }
        }

    }

    public void update(){
        if(this.playState1<=this.gameState && this.gameState <= this.playState6) {
            player.update();
            aSetter.update(gameState);
        for(int i = 0; i < alien.length; i++){
            if(alien[i] != null){
                //System.out.println(i);
                alien[i].update();
            }
        }
        
        }
    }

    public void paintComponent(Graphics myGraphics){
        super.paintComponent(myGraphics);
        Graphics2D myGraphics2 = (Graphics2D)myGraphics;

        //title screen
        if(gameState == titleState){
            ui.draw(myGraphics2);
        }
        else{

            mapM.draw(myGraphics2,currentMapIMGpath);
            
            //object
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    //System.out.println(i);
                    obj[i].draw(myGraphics2, this);
                }
            }
        }
        //aliens
        for(int i = 0; i < alien.length; i++){
            if(alien[i] != null){
                //System.out.println(i);
                alien[i].draw(myGraphics2, this);
            }
            }

        for(int i = 0; i < portal.length; i++){
            if(portal[i] != null){
                //System.out.println(i);
                portal[i].draw(myGraphics2, this);
            }
        }

        player.draw(myGraphics2);
        ui.draw(myGraphics2);
        myGraphics2.dispose();
    }

    // SOUND
    public void startMusic(int i){
        music.setFile(i);
        music.start();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void startSFX(int i){
        sfx.setFile(i);
        sfx.start();
    }
}
