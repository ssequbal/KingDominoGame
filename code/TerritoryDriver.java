import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.ArrayList;


public class TerritoryDriver
 {  

    private JFrame j;

    public static void main(String[] args)

    {   

        JFrame j = new JFrame();
        j.setBounds(50,50,500,500);
        Square d = new Square(Terrains.Desert,1,5);
        Square o = new Square(Terrains.Desert,1,3);
        Square f = new Square(Terrains.Farm,1,3);
        Square k = new Square(Terrains.Forest,1,2);
        Square w = new Square(Terrains.Water,1,1);
        Square m = new Square(Terrains.Mine,1,0);
        Square g = new Square(Terrains.Grass,1,2);
        Square s = new Square(Terrains.Start,1,0);

        Grid _grids = new Grid(0);
        _grids.setSquareTile(1,1,g);
        _grids.setSquareTile(1,2,d);
        _grids.setSquareTile(1,3,o);
        _grids.setSquareTile(1,4,m);
        _grids.setSquareTile(1,5,m);
        _grids.setSquareTile(2,1,d);
        _grids.setSquareTile(2,2,d);
        _grids.setSquareTile(2,3,d);
        _grids.setSquareTile(2,4,s);
        _grids.setSquareTile(2,5,d);
        _grids.setSquareTile(3,1,w);
        _grids.setSquareTile(3,2,w);
        _grids.setSquareTile(3,3,w);
        _grids.setSquareTile(3,4,w);
        _grids.setSquareTile(3,5,k);
        _grids.setSquareTile(4,1,w);
        _grids.setSquareTile(4,2,d);
        _grids.setSquareTile(4,3,f);
        _grids.setSquareTile(4,4,f);
        _grids.setSquareTile(4,5,f);
        _grids.setSquareTile(5,1,d);
        _grids.setSquareTile(5,2,f);
        _grids.setSquareTile(5,3,f);
        _grids.setSquareTile(5,4,f);
        _grids.setSquareTile(5,5,d);



        _grids.setSquareColor(1,1);
        _grids.setSquareColor(1,2);
        _grids.setSquareColor(1,3);
        _grids.setSquareColor(1,4);
        _grids.setSquareColor(1,5);
        _grids.setSquareColor(2,1);
        _grids.setSquareColor(2,2);
        _grids.setSquareColor(2,3);
        _grids.setSquareColor(2,4);
        _grids.setSquareColor(2,5);
        _grids.setSquareColor(3,1);
        _grids.setSquareColor(3,2);
        _grids.setSquareColor(3,3);
        _grids.setSquareColor(3,4);
        _grids.setSquareColor(3,5);
        _grids.setSquareColor(4,1);
        _grids.setSquareColor(4,2);
        _grids.setSquareColor(4,3);
        _grids.setSquareColor(4,4);
        _grids.setSquareColor(4,5);
        _grids.setSquareColor(5,1);
        _grids.setSquareColor(5,2);
        _grids.setSquareColor(5,3);
        _grids.setSquareColor(5,4);
        _grids.setSquareColor(5,5);

       
        j.add(_grids);
        j.setVisible(true);
        j.pack();
        System.out.println(Territories_identify.territory_check(_grids));
        ArrayList list1 = (ArrayList) Territories_identify.territory_check(_grids).get(1);
        Tile tileee = (Tile) list1.get(0);
        Square _square = (Square) tileee;
        System.out.println(_square.GetCrown());
        System.out.println(CrownTotal.total(_grids));
        

    
        
    }
  
    }
