package org.maps;
import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;

import org.game.GameScreen;

/**
 * The MapManager class is responsible for managing maps in a game environment. 
 * <p>
 * It extends the base Map class and is associated with a specific GameScreen instance.
 * The class provides methods for loading map images, reading map data from files,
 * and drawing the map on a myGraphics2D context.
 *
 * @author Usman Aziz
 * @author Nicole Malku
 */
public class MapManager extends Map{
    GameScreen screen;
    public Map [] map;
    public int[][] mapNum;
    public String[]maptxt = new String[10];
    public String[]mapIMG = new String[10];
    
    /**
     * Constructs a MapManager object associated with a specific GameScreen instance.
     * <p>
     * This constructor initializes the MapManager with the provided GameScreen and sets up the required data structures.
     * It creates an array of Map objects, a two-dimensional array to store map numbers, and loads map images.
     * Additionally, it loads a specific map based on the provided mapToLoad parameter.
     *
     *
     * @author Usman Aziz
     * @author Nicole Malku
     * @param screen The GameScreen instance to associate with this MapManager.
     */
    public MapManager(GameScreen screen){
        this.screen = screen;
        map = new Map[10];
        mapNum = new int[screen.maxWorldCol][screen.maxWorldRow];
        getMapImg();
        maptxt[1] = "/maps/map1.txt";
        maptxt[2] = "/maps/map2.txt";
        maptxt[3] = "/maps/map3.txt";
        maptxt[4] = "/maps/map4.txt";
        maptxt[5] = "/maps/map5.txt";
        maptxt[6] = "/maps/map6.txt";
        mapIMG[1] = "/maps/map1.png";
        mapIMG[2] = "/maps/map2.png";
        mapIMG[3] = "/maps/map3.png";
        mapIMG[4] = "/maps/map4.png";
        mapIMG[5] = "/maps/map5.png";
        mapIMG[6] = "/maps/map6.png";
        loadMap(maptxt[1]);
        screen.currentMapIMGpath = mapIMG[1];
    }

    /**
     * Retrieves and initializes map images for a specific game scenario.
     * <p>
     * This method populates an array of Map objects, each containing an image and collision information.
     * The images are loaded using the ImageIO class from the specified resource paths.
     * In case of an IOException during image loading, a stack trace is printed.
     *
     * @author Usman Aziz
     * @author Nicole Malku
     */
    public void getMapImg(){
        try {
            map[0] = new Map();
            map[0].img = ImageIO.read(getClass().getResourceAsStream("/maps/walk.png"));
            map[0].collision=true;

            map[1] = new Map();
            map[1].img = ImageIO.read(getClass().getResourceAsStream("/maps/barrier.png"));
            map[1].collision = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a map from a specified file and populates the mapNum array.
     *
     * @author Usman Aziz
     * @param S The name of the file to be loaded.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void loadMap(String S){
        try{
            InputStream is = getClass().getResourceAsStream(S);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < screen.maxScreenCol && row < screen.maxScreenRow){
                String line = br.readLine();

                while(col<screen.maxScreenCol){
                    String numArray[] = line.split(" ");

                    int num = Integer.parseInt((numArray[col]));
                    mapNum[col][row] = num;
                    col++;
                }
                if(col == screen.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception ignored){}
    }

    /**
     * Draws the map on the specified myGraphics2D context.
     * <p>
     * This method loads a map image from the "/maps/" directory and draws it onto
     * the provided myGraphics2D context at the coordinates (0, 0).
     * <p>
     * In case of an IOException during image loading, the exception details are printed to
     * the standard error stream.
     * 
     * @author Nicole Malku
     * @param myGraphics The myGraphics2D context on which to draw the map.
     */
    public void draw(Graphics2D myGraphics, String mapIMGpath){
        try {
            mapy = ImageIO.read(getClass().getResourceAsStream(screen.currentMapIMGpath));

        } catch (IOException e) {
            e.printStackTrace();
        }
        myGraphics.drawImage(mapy, 0,0 , null);
    }
}
