package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Carte {

    private static final Point SIZEMAP = new Point(28, 17);
    private Tile[] tiles;
    private int numMap[][];
    private int avancement_x=0;
    private int avancement_y=0;


    public int getAvancement_x(){return avancement_x;}
    public int getAvancement_y(){return avancement_y;}
    public Point getSizeMap(){return SIZEMAP;}
    public int[][] getNumMap(){return numMap;}
    public Tile[] getTiles(){return tiles;}

    public void loadMap(){
        InputStream is = getClass().getResourceAsStream("/img/carte_data.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        for (int i = 0; i < SIZEMAP.y; i++) {
            String line;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String num[] = line.split(" ");
            for(int j=0; j<num.length; j++){
                int typ = Integer.parseInt(num[j]);
                numMap[i][j] = typ;
            }
        }
    }

    public void loadTile(){
        int x; int y=0; int index=0;
        tiles = new Tile[numMap.length*numMap[0].length];
        for (int i = 0; i < numMap.length; i++) {
            x=0;
            for (int j = 0; j < numMap[i].length; j++) {
                tiles[index]= new Tile(x, y, numMap[i][j]);
                index++;
                x+=40;
            }
            y+=40;
        }
    }

    public Carte(){
        numMap = new int[SIZEMAP.y][SIZEMAP.x];
        loadMap();
        loadTile();
//        tiles = new Tile[10];
//        tiles[0] = new Tile(0, 0, 1);
//        tiles[1] = new Tile(40, 0, 0);

    }

    public void recolte(int i, int j){
        int index = SIZEMAP.x*(i) + j;
        tiles[index] = new Tile(tiles[index].pos_x, tiles[index].pos_y, 3);
        ThreadRepousse threadRepousse = new ThreadRepousse(this, index);
        threadRepousse.start();
    }
}
