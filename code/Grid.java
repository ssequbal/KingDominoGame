import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Grid extends JPanel
{
    //Variables
    private GridSquare[][] playerGrid;
    //private int[][] Mosts; //Right,Up,Left,Down //Dont remember what this does
    private int playernum;
    public int[][] Mosts; //To check limit constraints, //Right,Up,Left,Down

    public Grid(int playernum)
    {
        //Grid Stuff
        this.playernum = playernum;
        playerGrid = new GridSquare[11][11]; //Game System Creates the Grids and Frame_Game holds ID

        //Panel Stuff
        GridLayout experimentLayout = new GridLayout(11,11,2,2);
        setLayout(experimentLayout);

        //Init Grid
        for (int a = 0; a < 11; a++) {
            for (int b = 0; b < 11; b++){
                if(a==5 && b==5){
                    playerGrid[a][b] = new GridSquare(a, b, new StartingTile(Terrains.Start),this); 
                }else{ playerGrid[a][b] = new GridSquare(a, b, null,this); }
                add(playerGrid[a][b]);
            }
        }

        //Contraints Stuff
        Mosts = new int[4][2];
        for (var i = 0; i < 4; ++i) {
            Mosts[i][0] = 5;
            Mosts[i][1] = 5; 
        }

        //pack();
        setBackground(new java.awt.Color(255, 255, 255));
        //setPreferredSize(new java.awt.Dimension(297, 297));
        //this.setBounds(50, 50, 275, 275);
    }

    @Override
    public String toString() { 
        return "Grid For Player #"+playernum;
    }

    public Point GetMousePos(){
        var point = this.getMousePosition();
        var _x = Math.floorDiv((int) point.getX(), 27);
        var _y = Math.floorDiv((int) point.getY(), 27);
        return new Point(_x,_y);
    }

    public void GridConstraints(Point[] points)
    {
        int mx = (int) points[0].getX();
        int my = (int) points[0].getY();
        int rx = (int) points[1].getX();
        int ry = (int) points[1].getY();
        for (var i = 0; i < 4; ++i) {
            switch(i)
            {
                case 0:
                    if(mx>Mosts[i][0]){ Mosts[i][0] = mx; Mosts[i][1] = my;}
                    if(rx>Mosts[i][0]){ Mosts[i][0] = rx; Mosts[i][1] = ry;}
                break;
                case 1:
                    if(my<Mosts[i][1]){ Mosts[i][0] = mx; Mosts[i][1] = my;}
                    if(rx<Mosts[i][1]){ Mosts[i][0] = rx; Mosts[i][1] = ry;}
                break;
                case 2:	
                    if(mx<Mosts[i][0]){ Mosts[i][0] = mx; Mosts[i][1] = my;}
                    if(rx<Mosts[i][0]){ Mosts[i][0] = rx; Mosts[i][1] = ry;}
                break;
                case 3:
                    if(my>Mosts[i][1]){ Mosts[i][0] = mx; Mosts[i][1] = my;}
                    if(rx>Mosts[i][1]){ Mosts[i][0] = rx; Mosts[i][1] = ry;}
                break;
            }
            //System.out.println(Arrays.toString(Mosts[i]));
        }
        
    }

    //Setters and Getters
    public GridSquare getGridSquare(int x, int y)       { return playerGrid[x][y]; } //Get the Grid Square at pos in Grid
    public GridSquare getGridSquare(Point point)        { return playerGrid[(int) point.getX()][(int) point.getY()]; }
    public Tile getSquareTile(int x, int y)             { return playerGrid[x][y].getTile(); } //Get the Tile that the GridSquare has
    public void setSquareTile(int x, int y, Tile tile)  { playerGrid[x][y].setTile(tile);} //Change the Tile that the GridSquare has
    public void setSquareTile(Point point,Tile tile)    { playerGrid[(int) point.getX()][(int) point.getY()].setTile(tile);}
    public int getNum()                                 { return playernum;}
    public void setSquareColor(int x,int y)             { playerGrid[x][y].setColor();}
    public void setSquareColor(Point point)             { playerGrid[(int) point.getX()][(int) point.getY()].setColor();}
}
