package org.game;

import org.object.Heart;
import org.object.RegularReward;
import org.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

public class UI {
    GameScreen screen;
    //Font arial_25, arial_70BI,arial_45;
    Font pixelmix;
    public BufferedImage regRewardImg, full, half, empty, titleImg;
    public boolean textFlag = false;
    public String text = "";
    int textCounter = 0;
    public boolean endGame = false;
    double onTime;
    DecimalFormat deci = new DecimalFormat("0.0");
    Graphics2D myGraphics;
    public int commandNum = 0;
    int subState = 0;



    public UI (GameScreen screen) {
        this.screen = screen;
        try {
            InputStream stream = getClass().getResourceAsStream("/font/pixelmix.ttf");
            pixelmix = Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        RegularReward planet = new RegularReward();
        regRewardImg = planet.image;

        SuperObject heart = new Heart(screen);
        full = heart.image;
        half = heart.image2;
        empty = heart.image3;
    }
    public void showMessage (String msg) {
        text = msg;
        textFlag = true;
    }

    public void draw (Graphics2D graphics){
        this.myGraphics = graphics;
        myGraphics.setFont(pixelmix);
        myGraphics.setColor(Color.YELLOW);

        if(screen.gameState == screen.titleState){
            drawTitleScreen();
        }
        if(screen.playState1<=screen.gameState && screen.gameState <= screen.playState6){
            displayTime();
            displayPlanetReward();
            drawLife();
            displayScore();
        }
        if(screen.gameState == screen.pauseState){
            drawLife();
            displayPause();       
        }
        if(screen.gameState == screen.winState){
            displayWinState();
        }
        if(screen.gameState == screen.lossState){
            drawGameOverScreen();
        }
    }

    

    public void drawLife(){
        int x = screen.tileSize*18;
        int y = screen.tileSize*11+12;
        int i = 0;
        //draws empty health
        while(i<screen.player.maxLife/2){
            myGraphics.drawImage(empty, x, y, null);
            i++;
            x+= screen.tileSize;
        }
        //draw current player health
        x = screen.tileSize *18;
        y = screen.tileSize *11+12;
        i=0;
        while(i<screen.player.life){
            myGraphics.drawImage(half, x,y,null);
            i++;
            if(i<screen.player.life){
                myGraphics.drawImage(full, x,y,null);
            }
            i++;
            x+= screen.tileSize;
        }
    }

    public void displayTime(){
            //Displaying Playing Time
            onTime+=(double)1/60;
            myGraphics.setColor(Color.WHITE); //Planet RGB 55,99,116
            myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,25F));
            myGraphics.drawString(deci.format(onTime),screen.tileSize*9, screen.tileSize*12-3);

            //Displaying Message for Collecting Regular Reward
            if(textFlag) {
                myGraphics.setColor(new Color(55,99,116)); //Planet RGB
                //myGraphics.setFont(myGraphics.getFont().deriveFont(32F));
                myGraphics.drawString(text, screen.tileSize *6, 30);
                textCounter++;
                if (textCounter > 60) {
                    textCounter = 0;
                    textFlag = !textFlag;
                }
            }
    }

    public void displayPlanetReward(){
        myGraphics.setFont(pixelmix);
        myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,25F));
        myGraphics.setColor(new Color(55,99,116)); //Planet RGB
        myGraphics.drawImage(regRewardImg, screen.tileSize*1,0,screen.tileSize,screen.tileSize,null);
        myGraphics.drawString(" x" + screen.player.hasPlanet,screen.tileSize*2, screen.tileSize-11);
    }

    public void displayScore(){
        myGraphics.setFont(pixelmix);
        myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,25F));
        myGraphics.setColor(Color.WHITE); //Planet RGB
        myGraphics.drawString(" "+screen.player.score,screen.tileSize*14+16, screen.tileSize*12-2);
    }

    public void displayWinState(){
            String msg;
            int msgLen;
            int x,y;

            //Displaying Game Won on Screen
            myGraphics.setFont(pixelmix);
            myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,100F));
            //graphics.setColor(new Color(109,95,153)); //Purple RGB
            myGraphics.setColor(Color.yellow);
            msg = "Game Won!";
            x = screenCenterX(msg);
            y = screen.screenHeight/2 - (screen.tileSize*3);
            myGraphics.drawString(msg, x, y);

            myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,25F));
            //Displaying Planets Collected, or can be any win statement
            msg = "Final Score: " + screen.player.score;
            msgLen = (int)myGraphics.getFontMetrics().getStringBounds(msg,myGraphics).getWidth();
            x = screen.screenWidth/2 - msgLen/2;
            y = screen.screenHeight/2 - (screen.tileSize*2);
            myGraphics.drawString(msg, x, y);

            //Displaying Time
            msg = "You Played: " + deci.format(onTime)+" seconds.";
            msgLen = (int)myGraphics.getFontMetrics().getStringBounds(msg,myGraphics).getWidth();
            x = screen.screenWidth/2 - msgLen/2;
            y = screen.screenHeight/2 - (screen.tileSize);
            myGraphics.drawString(msg, x, y);

            myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,100F));
            //Displaying Congratulations
            myGraphics.setFont(pixelmix);
            myGraphics.setColor(Color.orange);
            msg = "Congratulations!!";
            msgLen = (int)myGraphics.getFontMetrics().getStringBounds(msg,myGraphics).getWidth();
            x = screen.screenWidth/2 - msgLen/2;
            y = screen.screenHeight/2 + (screen.tileSize*2);
            myGraphics.drawString(msg, x, y);

            //To Stop the Game
            screen.gameThread = null;
    }
    
    /**
     * Draws the Game Over screen on the graphics context
     * The method sets the background color with partial transparency, fill the screen,
     * and displays the "Game Over" text along with the options to retry or quit.
     * <p>
     * The "Game Over" text displayed with font size of 110 and retry and quit options
     * are displayed with font size of 50.
     * The selected option is indicated with a ">" symbol
     * 
     *
     */
    public void drawGameOverScreen(){
        myGraphics.setColor(new Color(153, 51, 153, 150));
        myGraphics.fillRect(0, 0, screen.screenWidth, screen.screenHeight);

        int x;
        int y;
        String text;
        myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,110F));

        text = "Game Over!";
        myGraphics.setColor(Color.black);
        x = screenCenterX(text);
        y = screen.tileSize * 4;
        myGraphics.drawString(text, x, y);
        myGraphics.setColor(Color.white);
        myGraphics.drawString(text, x - 4, y - 4);

        myGraphics.setFont(myGraphics.getFont().deriveFont(25f));
        myGraphics.setColor(Color.black);
        myGraphics.drawString("Final Score: " + screen.player.score, screen.tileSize*2 ,screen.tileSize*6);
        myGraphics.drawString("Time: " + deci.format(onTime) + " seconds", screen.tileSize*11 ,screen.tileSize*6);
        myGraphics.drawString("Retry", screen.tileSize*6 ,screen.tileSize*8);
        myGraphics.drawString("Quit", screen.tileSize*6, screen.tileSize*10);
    
        myGraphics.setColor(Color.white);
        myGraphics.drawString("Final Score: " + screen.player.score, screen.tileSize*2-4 ,screen.tileSize*6-4);
        myGraphics.drawString("Time: " + deci.format(onTime)+ " seconds", screen.tileSize*11-4 ,screen.tileSize*6-4);
        myGraphics.drawString("Retry", screen.tileSize*6-4 ,screen.tileSize*8-4);
        myGraphics.drawString("Quit", screen.tileSize*6-4, screen.tileSize*10-4);

        if(commandNum == 0){
            myGraphics.setColor(Color.BLACK);
            myGraphics.drawString(">", screen.tileSize*6-40, screen.tileSize*8);
            myGraphics.setColor(Color.WHITE);
            myGraphics.drawString(">", screen.tileSize*6-44, screen.tileSize*8-4);
            
        }

        if(commandNum == 1){
            myGraphics.setColor(Color.BLACK);
            myGraphics.drawString(">",screen.tileSize*6-40, screen.tileSize*10);
            myGraphics.setColor(Color.WHITE);
            myGraphics.drawString(">",screen.tileSize*6-44, screen.tileSize*10-4);
        }

    }

    public void displayPause(){
        //myGraphics.setFont(myGraphics.getFont().deriveFont(Font.PLAIN,80F));
        /*String msg = "Game Paused.";
        int x, y;
        x = screenCenterX(msg);
        y = screen.screenHeight/2;
        myGraphics.drawString(msg,x,y);
        */
        int windowX= screen.tileSize*5;
        int windowY = screen.tileSize;
        int windowW = screen.tileSize*11;
        int windowH = screen.tileSize*10;
        drawWindow(windowX, windowY, windowW, windowH);
        switch(subState){
        case 0: 
            menu_title();
            break;
        case 1: 
            how_to_play();
            break;
        case 2: 
            end_game();
            break;
        }
        screen.keyInput.enterPressed = false;
    }

    public void menu_title(){
        String txt1 = "~Game Paused~";
        String txt2 = "Options:";
        myGraphics.setFont(pixelmix);
        myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,25F));
        myGraphics.setColor(Color.WHITE); //Planet RGB
        myGraphics.drawString(txt1, screen.tileSize *8 +6, screen.tileSize*2-6);
        myGraphics.drawString(txt2, screen.tileSize *6-9, screen.tileSize*3-6);

        // change music vol
        myGraphics.drawString("Music Volume", screen.tileSize *6 +6, screen.tileSize*4-6);
        if(commandNum ==0){
            myGraphics.setColor(Color.PINK);
            myGraphics.drawString(">", screen.tileSize *6-10, screen.tileSize*4-6);
            myGraphics.setColor(Color.WHITE);
        }
        // sfx vol
        myGraphics.drawString("SFX Volume", screen.tileSize *6 +6, screen.tileSize*5-6);
        if(commandNum ==1){
            myGraphics.setColor(Color.PINK);
            myGraphics.drawString(">", screen.tileSize *6-10, screen.tileSize*5-6);
            myGraphics.setColor(Color.WHITE);
        }
       /*  myGraphics.drawString("Change Controls", screen.tileSize *6 +6, screen.tileSize*6-6);
        if(commandNum ==2){
            myGraphics.drawString(">", screen.tileSize *6-10, screen.tileSize*6-6);
        } */
        // how to play
        myGraphics.drawString("How to Play", screen.tileSize *6 +6, screen.tileSize*6-6);
        if(commandNum ==2){
            myGraphics.setColor(Color.PINK);
            myGraphics.drawString(">", screen.tileSize *6-10, screen.tileSize*6-6);
            myGraphics.setColor(Color.WHITE);
            if(screen.keyInput.enterPressed == true){
                subState =1;
                commandNum =0;
            }
        }
        //exit
        myGraphics.drawString("Exit Game", screen.tileSize *6 +6, screen.tileSize*9-6);
        if(commandNum ==3){
            myGraphics.setColor(Color.PINK);
            myGraphics.drawString(">", screen.tileSize *6-10, screen.tileSize*9-6);
            if(screen.keyInput.enterPressed == true){
                subState = 2;
                commandNum =0;
            }
            myGraphics.setColor(Color.WHITE);
        }

        // unpause

        myGraphics.drawString("Back", screen.tileSize *6 +6, screen.tileSize*10-6);
        if(commandNum ==4){
            myGraphics.setColor(Color.PINK);
            myGraphics.drawString(">", screen.tileSize *6-10, screen.tileSize*10-6);
            myGraphics.setColor(Color.WHITE);
            if(screen.keyInput.enterPressed == true){
                screen.gameState = screen.latestPlayState;
                commandNum =0;
            }
        }
        
        // music slider
        myGraphics.setStroke(new BasicStroke(3));
        myGraphics.drawRect(screen.tileSize*12, screen.tileSize*3+20, 120, 24); 
        int volW = 24* screen.music.volSlider;
        myGraphics.fillRect(screen.tileSize*12, screen.tileSize*3+20,volW, 24);

        // SFX slider
        myGraphics.drawRect(screen.tileSize*12, screen.tileSize*4+20, 120, 24);
        int volW2 = 24* screen.sfx.volSlider;
        myGraphics.fillRect(screen.tileSize*12, screen.tileSize*4+20,volW2, 24);
    }

    public void how_to_play(){
        myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,15F));
        myGraphics.setColor(Color.green);
        myGraphics.drawString("Welcome to Alien Escape!",screen.tileSize *7 -7, screen.tileSize*2-6);
        myGraphics.setColor(Color.WHITE);
        myGraphics.drawString("Aliens have stolen the planets. Grab", screen.tileSize *5 +12, screen.tileSize*3-6);
        myGraphics.drawString("each planet, and escape to the rocket!", screen.tileSize *5 +12, screen.tileSize*4-6);
        myGraphics.drawString("Avoid all Aliens and Poison!", screen.tileSize *5 +12, screen.tileSize*5-6);
        myGraphics.drawString("Grab special stars to double your score.", screen.tileSize *5 +12, screen.tileSize*6-6);
        myGraphics.setColor(Color.ORANGE);
        myGraphics.drawString("W,A,S,D to move", screen.tileSize *5 +12, screen.tileSize*7-6);
        myGraphics.drawString("Enter key for selections", screen.tileSize *5 +12, screen.tileSize*8-6);
        myGraphics.drawString("Up & down arrows for volume adustments", screen.tileSize *5 +12, screen.tileSize*9-6);
        myGraphics.drawString("P or ESC key to toggle pause/options", screen.tileSize *5 +12, screen.tileSize*10-6);
        
    
        myGraphics.drawString("Back",screen.tileSize *14 +12, screen.tileSize*11-19);
        if(commandNum == 0){
            myGraphics.setColor(Color.PINK);
            myGraphics.drawString(">", screen.tileSize *14-4, screen.tileSize*11-19);
            myGraphics.setColor(Color.WHITE);
            if(screen.keyInput.enterPressed == true){
                subState =0;
                commandNum =2;
                screen.gameState = screen.latestPlayState;
            }
        }
    
    }

    public void end_game(){
        myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,20F));
        myGraphics.setColor(Color.cyan);
        myGraphics.drawString("Are you sure you'd like to quit?",screen.tileSize *5 + 22, screen.tileSize*4-6);
        myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,25F));
        myGraphics.setColor(Color.WHITE);
        myGraphics.drawString("Yes.",screen.tileSize *7 +7, screen.tileSize*7-6);
        myGraphics.drawString("No!",screen.tileSize *11 -7, screen.tileSize*7-6);

        if(commandNum ==0){
            myGraphics.setColor(Color.cyan);
            myGraphics.drawString(">", screen.tileSize *7-10, screen.tileSize*7-6);
            if(screen.keyInput.enterPressed == true){
                screen.stopMusic();
                screen.startSFX(7);
                try {
                    Thread.sleep(700);              
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                screen.startMusic(4);
                subState =0;
                screen.gameState = screen.titleState;

            }
        }
        if(commandNum ==1){
            myGraphics.setColor(Color.cyan);
            myGraphics.drawString(">", screen.tileSize *11-24, screen.tileSize*7-6);
            if(screen.keyInput.enterPressed == true){
                subState=0;
                commandNum = 3;
            }

        }
    }



    public void drawWindow(int x, int y, int w, int h){
        myGraphics.setColor(new Color(0,0,0, 200));
        myGraphics.fillRoundRect(x, y, w, h, 35, 35);
        myGraphics.setColor(Color.PINK);
        myGraphics.setStroke(new BasicStroke(5));
        myGraphics.drawRoundRect(x+5, y+5, w-10, h-10, 25, 25);
    }
    public int screenCenterX(String msg){
        int len = (int)myGraphics.getFontMetrics().getStringBounds(msg,myGraphics).getWidth();
        int x = screen.screenWidth/2 - len/2;
        return x;
    }

    /**
     * Draws the game title screen with a predesigned image, and implements menu option switching.
     * 
     * This method loads the title screen image from the "/maps/" directory. 
     * It then draws the background image and menu options, including highlighting the selected option
     * with a ">" indicator.
     * 
     * In case of an IOException during image loading, the exception details are printed
     * to the standard error stream.
     * 
     * 
     * 
    */
    public void drawTitleScreen(){
        onTime= 0;
        int x,y;
        //String msg;
        myGraphics.setFont(myGraphics.getFont().deriveFont(Font.BOLD,25F));
        try {
            titleImg = ImageIO.read(getClass().getResourceAsStream("/maps/titlescreen.png"));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        myGraphics.drawImage(titleImg, 0,0, null);
    

        //msg = "NEW GAME";
        x = screen.tileSize*6 -26;
        y = screen.tileSize*10 +5;

        if(commandNum == 0){
            myGraphics.drawString(">", x, y);
        }

        //msg = "QUIT";
        x += screen.tileSize*6;
        //myGraphics.drawString(msg, x, y);
        if(commandNum == 1){
            myGraphics.drawString(">", x+6, y);
        }
       
    }
}
