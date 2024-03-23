package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Carte {

    private static final Point SIZEMAP = new Point(28, 17);
    private Inventory inventaire;
    private Tile[] tiles;
    private int numMap[][];
    private int avancement_x=0;
    private int avancement_y=0;
    private ArrayList<String> mapList = new ArrayList<String>();


    public int getAvancement_x(){return avancement_x;}
    public int getAvancement_y(){return avancement_y;}
    public Point getSizeMap(){return SIZEMAP;}
    public int[][] getNumMap(){return numMap;}
    public Tile[] getTiles(){return tiles;}

    public void loadMap(String map){
        InputStream is = getClass().getResourceAsStream(map);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int [][] numMap = new int[SIZEMAP.y][SIZEMAP.x];
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
        this.numMap = numMap;
    }

    public void loadTile(){
        int x; int y=0; int index=0;
        Tile[] tiles = new Tile[numMap.length*numMap[0].length];
        for (int i = 0; i < numMap.length; i++) {
            x=0;
            for (int j = 0; j < numMap[i].length; j++) {
                tiles[index]= new Tile(x, y, numMap[i][j]);
                index++;
                x+=40;
            }
            y+=40;
        }
        this.tiles = tiles;
    }

    public Carte(){
        mapList.add("/img/carte_data.txt");
        mapList.add("/img/carte_data2.txt");
        loadMap(mapList.get(0));
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

    public void changeMap(){
        loadMap(mapList.get(1));
        loadTile();
    }
}
