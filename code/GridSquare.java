/*
    Description:
       This is what a grid is filled with, they hold the tile object 
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;




public class GridSquare extends JPanel
{
    //variables
    private int xcoord, ycoord; //Location of Square in Grid
    private Tile tile; //Tile dictates color and stuff
    private boolean blink = false;
    private Grid parentGrid;

    //init
    public GridSquare( int xcoord,int ycoord, Tile tile, Grid parentGrid)
    {
        this.xcoord = xcoord;
		this.ycoord = ycoord;
        this.tile = tile;
        this.parentGrid = parentGrid;
        //setPreferredSize(new java.awt.Dimension(25, 25));
        setColor();
    }

    public void Blink(boolean _blink) //TO use when moving a selected domino around the grid
    {
        blink = _blink;
        if(blink){ this.setBackground(Color.white);} else {setColor();}
    }

    @Override
    public String toString() { 
        return "GridSquare at ("+xcoord+","+ycoord+"), Tile: "+tile;
    }

    //setters and getters
    public void setColor(){
        Color colour = Color.white;
        if(this.tile == null){
            colour = (int) ((xcoord+ycoord)/2.0) == ((xcoord+ycoord)/2.0) ? new Color(57, 181, 74) : new Color(32, 102, 41);
        }else{ 
            colour = Terrains.GetColor(this.tile.GetTerrain());
        }
        this.setBackground( colour);
    }
    /*public BufferedImage setIcon(int num, int postion){
        String os = System.getProperty("os.name");
        var slash = "/";
        if(os.substring(0,7).equals("Windows")){
            slash = "\\";
        }
        BufferedImage icon1=null;
        try {
            icon1 = ImageIO.read(new File ("Images"+slash+"Domino "+num+"_row-1-column-1.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedImage icon2=null;
        try {
            icon2 = ImageIO.read(new File ("Images"+slash+"Domino "+num+"_row-1-column-2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        if (postion ==1){
            return icon1;
        }
        else{
            return icon2;
        } 
    } */
    public void setXcoord(int value)    { xcoord = value; }
    public void setYcoord(int value)    { ycoord = value; }
    public void setTile(Tile newtile)   { tile = newtile; }
    public int getXcoord()              { return xcoord;  }
    public int getYcoord()              { return ycoord;  }
    public Tile getTile()               { return tile;    }
    public Grid getParentGrid()         { return parentGrid;}
}
