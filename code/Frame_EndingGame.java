import javax.swing.*;
import org.w3c.dom.events.Event;
import java.awt.*;
import java.awt.event.*;

public class Frame_EndingGame extends MainFrame implements ActionListener
{
    class MainMenu implements Command     { public void execute()     { UI.ChangeFrame(new Frame_MainMenu(UI));}}
    class ExitGameCommand implements Command    { public void execute()     { System.exit(0);}}

    //private JButton NewGame;
    private Button MainMenu;
   // private Button LoadGame;
    private Button ExitGame;
    //private Button Settings;

    public Frame_EndingGame(MainUI UI, String Winner)
    {
        super("Ending Game",UI);

        //Buttons
        MainMenu = new Button("New Game",new MainMenu());//new JButton("New Game");
        ExitGame = new Button("Exit Game",new ExitGameCommand()); 


        MainMenu.addActionListener(this);
        ExitGame.addActionListener(this);



        JPanel Background = new JPanel();
        JLabel winner = new JLabel(Winner);
        Background.setLayout(null);
        this.add(Background);
        Background.setBounds(0, 0, 1024, 768);
        Background.add(MainMenu); 
        Background.add(ExitGame); 
        Background.add(winner); 

        winner.setBounds(30,350,100,100);
        MainMenu.setBounds(30,30,100,100);
        ExitGame.setBounds(350,30,100,100);



        this.changeColor(Color.RED);
        this.pack();
        this.setVisible(true);
        //this.setSize(new java.awt.Dimension(640, 680));
        this.setSize(new java.awt.Dimension(1024, 768));
    }
}
