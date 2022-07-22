/*
    Description:
        This is the class for the Player object
*/

import java.awt.*;

public class Player {
    //variables
    private int selectedDomino;
    private String name;
    private Color color;

    //init
    public Player()
    {
        selectedDomino = -1;
    }

    //setters and getters
    public void SetDomino(int dom)         { selectedDomino = dom; }
    public void SetName(String name)       { this.name = name;}
    public void SetColor(Color color)     { this.color = color;}
    public int GetDomino()                 { return selectedDomino;}
    public String GetName()                { return name;}
    public Color GetColor()               { return color;}
}
