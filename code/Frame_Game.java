import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame_Game extends MainFrame
{
    
    class ExitGameCommand implements Command        { public void execute()     { System.exit(0);}}
    class PlayerSettingCommand implements Command   { public void execute()     { 
        String[] names = new String[] {"","","",""};
        String[] scolors = new String[] {"RED","BLUE","YELLOW","GREEN"};
        Color[] _colors = new Color[] {Color.RED,Color.blue};
        names[0] = JOptionPane.showInputDialog(null, "Enter Player's new name");
        names[1] = JOptionPane.showInputDialog(null, "Enter Player's new name");
        names[2] = JOptionPane.showInputDialog(null, "Enter Player's new name");
        names[3] = JOptionPane.showInputDialog(null, "Enter Player's new name");
        scolors[0] =  JOptionPane.showInputDialog(null, "Enter Player's new Color (DEF: RED,BLUE,YELLOW,GREEN)");
        scolors[1] =  JOptionPane.showInputDialog(null, "Enter Player's new Color (RED,DEF: BLUE,YELLOW,GREEN)");
        scolors[2] =  JOptionPane.showInputDialog(null, "Enter Player's new Color (RED,BLUE,DEF: YELLOW,GREEN)");
        scolors[3] =  JOptionPane.showInputDialog(null, "Enter Player's new Color (RED,BLUE,YELLOW,DEF: GREEN)");
        for (var i = 0; i < 4; ++i) {
            switch (scolors[i]) {
                case "RED": _colors[i] = Color.red;
                    break;
                case "BLUE": _colors[i] = Color.blue;
                    break;
                case "YELLOW": _colors[i] = Color.yellow;
                    break;
                case "GREEN": _colors[i] = Color.green;
                    break;
            }
        }
        UI.Game.SetPlayerNames(names,_colors);
        Name.setText(UI.Game.Players[0].GetName()+" Kingdom");
        Name2.setText(UI.Game.Players[1].GetName()+" Kingdom");
        Name3.setText(UI.Game.Players[2].GetName()+" Kingdom");
        Name4.setText(UI.Game.Players[3].GetName()+" Kingdom");
    }}
    class NewGameCommand implements Command         { public void execute()     {}}
    class SaveGameCommand implements Command        { public void execute()     { UI.Game.State_current = States.End; UI.Game.Update();}}
    class DiscardCommand implements Command         { public void execute()     { UI.Game.DiscardDomino();}}

    private Button Discard;
    private JLabel Name,Name2,Name3,Name4;
    private MenuJ Exit, NewGame, Save, PlayerSettings;
    private JMenuBar MenuBar;
    private JLabel[] Names;
    public Grid[] gridPanels;
    private PickPanel PickPanel;

    public class PickPanel extends JPanel{

        //private PickDomino[] PickPile;

        public PickPanel(GameSystem Game)
        {
            //PickPile = new PickDomino[8];
            //for (int a = 0; a < 8; a++) {
                //PickPile[a] = new PickDomino();
            //}

            this.setBackground(new java.awt.Color(204, 204, 204));

            //Panel Stuff
            GridLayout experimentLayout = new GridLayout(4,2,7,7);
            setLayout(experimentLayout);

            //Because GridLayout goes 1,2,3 left to right
            add(Game.GetPickDomino(0));
            add(Game.GetPickDomino(4));
            add(Game.GetPickDomino(1));
            add(Game.GetPickDomino(5));
            add(Game.GetPickDomino(2));
            add(Game.GetPickDomino(6));
            add(Game.GetPickDomino(3));
            add(Game.GetPickDomino(7));
        }
    }

    public Frame_Game(MainUI UI)
    {
        super("Game",UI); 

        JPanel Background = new JPanel();
        Background.setLayout(null);
        this.add(Background);

        //#region MenuBar
        MenuBar = new JMenuBar();

        Save = new MenuJ("Save", new SaveGameCommand());
        NewGame = new MenuJ("New Game",new NewGameCommand());
        Exit = new MenuJ("Exit", new ExitGameCommand());
        PlayerSettings = new MenuJ("Player Settings",new PlayerSettingCommand());

        Save.addMouseListener(this);
        NewGame.addMouseListener(this);
        Exit.addMouseListener(this);
        PlayerSettings.addMouseListener(this);

        MenuBar.add(Save);
        MenuBar.add(NewGame);
        MenuBar.add(PlayerSettings);
        MenuBar.add(Exit);

        setJMenuBar(MenuBar);
        //#endregion

        //#region Grid Panels
        gridPanels = new Grid[4];
        gridPanels[0] = UI.Game.GetGrid(0);
        gridPanels[1] = UI.Game.GetGrid(1);
        gridPanels[2] = UI.Game.GetGrid(2);
        gridPanels[3] = UI.Game.GetGrid(3);

        gridPanels[0].setBounds(25, 40, 240, 240);
        gridPanels[1].setBounds(740, 40, 240, 240);
        gridPanels[2].setBounds(25,400, 240, 240);
        gridPanels[3].setBounds(740, 400, 240, 240);

        Background.add(gridPanels[0]);
        Background.add(gridPanels[1]);
        Background.add(gridPanels[2]);
        Background.add(gridPanels[3]);
        //#endregion

        //#region JLabels
        Names = new JLabel[4];
        for(var i=0; i<4; i++){
            Names[i] = new JLabel();
            Names[i].setText(UI.Game.Players[i].GetName()+" Kingdom");
        }
        Dimension size = Names[0].getPreferredSize();
        Names[0].setBounds(25, 15, 150, 30);
        Names[1].setBounds(740, 15, 150, 30);
        Names[2].setBounds(25,375,150,30);
        Names[3].setBounds(740,375,150,30);

        var _displayText = UI.Game.GetDisplayText();
        _displayText.SetText("Display Text");
        _displayText.SetStuff(430, 30, 265, 100);

        Background.add(Names[0]);
        Background.add(Names[1]);
        Background.add(Names[2]);
        Background.add(Names[3]);
        Background.add(_displayText);
        //#endregion
             
        PickPanel = new PickPanel(UI.Game);
        PickPanel.setBounds(350, 300, 250, 250);
        Background.add(PickPanel);

        Discard = new Button("Discard", new DiscardCommand());
        Discard.setBounds(350, 600, 120, 30);
        Discard.addActionListener(this);
        Background.add(Discard);

        this.add(Background,null);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(false);
        this.setResizable(false);
        //GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //GraphicsDevice device = graphics.getDefaultScreenDevice();
        //device.setFullScreenWindow(this);
        //this.setUndecorated(true);
        this.setVisible(true);
        this.setSize(new java.awt.Dimension(1024, 768));
    }
}