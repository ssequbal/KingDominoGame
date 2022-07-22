//This will be where the players pick their dominoes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;

public class PickDomino extends JButton implements CommandHandler
{
    public int Num = -1; //Domino Num to track
    public boolean expose = false;
    public int selectedBY = -1; //Player Index who selected it
    Terrains[] terrain = {null,null};
    public boolean canBeSelected; //To distinguish the PickDominoes you can select and cannot

    Command myCommand;

    public PickDomino(boolean canBeSelected)
    {

        //setText("PickPile");
        setBackground(Color.white);
        setPreferredSize(new Dimension(80, 35));
        this.canBeSelected = canBeSelected;
    }

    public void RefreshDomino()
    {
        if(Num==-1) return;
	
        //Get Domino
        var _domino = GameSystem.Dominoes.get(Num);
        var square1 = _domino.GetSquare(0);
        var square2 = _domino.GetSquare(1);
            
        terrain[0] = square1.GetTerrain();
        terrain[1] = square2.GetTerrain();

        if(expose){ IconSet();} else{ setIcon(null);}
    }

    public void setBackground(Color color){

    }
    public void IconSet(){
        

        //Icon icon = new ImageIcon(currentpath+"\\Images"+"\\Domino ("+Num+")"+".png");
        String os = System.getProperty("os.name");
        var slash = "/";
        if(os.substring(0,7).equals("Windows")){
            slash = "\\";
        }
        Icon icon = new ImageIcon("Images"+slash+"Domino "+(Num+1)+".png");
        setIcon(icon);
    }
    public void Clean()
    {
        expose = false;	
        selectedBY = -1;
        terrain[0] = null;
        terrain[1] = null;
    }

    @Override
    public String toString() { 
        return "Domino:"+Num+" Exposded:"+expose+" SelectedBy:"+selectedBY;
    }

    //Setters and Getters
    public void SetNum(int i){ Num=i;}
    public int GetNum(){ return Num;}
    public void SetSelected(int i){ selectedBY=i;}
    public int GetSelected(){ return selectedBY;}
    public void Expose(){ this.expose = true; IconSet();}

    //Command Handler
    public void setCommand( Command command){ this.myCommand = command; }
    public Command getCommand(){ return this.myCommand;}

    //Data Handlings
}
