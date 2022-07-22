import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameData implements Serializable{
 
    //default serialVersion id
    private static final long serialVersionUID = 1L;
    //Variables to Store
    private States State_current;
    public Player[] Players; //Holds the struct of the Players, this should not be changed
    public int[] Order; //This holds the player order, the int are the index for the Players array
    public Grid[] PlayerGrids; //Holds the different grids
    public ArrayList<Integer> DrawPile;
    public PickDominoData[] PickPile;
    public int turn = 0;


    public GameData()
    {

    }

    public void StoreDate(GameSystem Game)
    {
        State_current = Game.State_current;
        Players = Game.Players;
        Order = Game.Order;
        PlayerGrids = Game.PlayerGrids;
        DrawPile = Game.DrawPile;
        PickPile[0] = new PickDominoData(Game.PickPile[0]);
        PickPile[1] = new PickDominoData(Game.PickPile[1]);
        PickPile[2] = new PickDominoData(Game.PickPile[2]);
        PickPile[3] = new PickDominoData(Game.PickPile[3]);
        turn = Game.turn;
    }

    public void RetreiveData()
    {

    }
}
