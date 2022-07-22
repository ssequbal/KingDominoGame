import javax.swing.*;
import org.w3c.dom.events.Event;
import java.awt.*;
import java.awt.event.*;

public class Frame_MainMenu extends MainFrame
{
    class NewGameCommand implements Command     { public void execute()     { UI.ChangeFrame(new Frame_SetUpGame(UI));}}
    class ExitGameCommand implements Command    { public void execute()     { System.exit(0);}}
    class LoadGameCommand implements Command    { public void execute()     { UI.ChangeFrame(new Frame_EndingGame(UI, "Poopoo"));}}
    class SettingsCommand implements Command    { public void execute()     { UI.ChangeFrame(new Frame_Settings(UI));}}

    //private JButton NewGame;
    private Button NewGame;
    private Button LoadGame;
    private Button ExitGame;
    private Button Settings;

    public Frame_MainMenu(MainUI UI)
    {
        super("Main Menu",UI);

        //Buttons
        NewGame = new Button("New Game",new NewGameCommand());//new JButton("New Game");  
        NewGame.setPreferredSize(new java.awt.Dimension(100, 40));

        LoadGame = new Button("Load Game",new LoadGameCommand());
        LoadGame.setPreferredSize(new java.awt.Dimension(100, 40));

        ExitGame = new Button("Exit Game",new LoadGameCommand()); 
        ExitGame.setPreferredSize(new java.awt.Dimension(100, 40));

        Settings = new Button("Settings",new SettingsCommand()); 
        Settings.setPreferredSize(new java.awt.Dimension(100, 40));

        NewGame.addActionListener(this);
        LoadGame.addActionListener(this);
        ExitGame.addActionListener(this);
        Settings.addActionListener(this);

        //#region Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Settings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(273, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(NewGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Settings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExitGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        //#endregion
        this.changeColor(Color.RED);     
        this.pack();
        this.setVisible(true);
        //this.setSize(new java.awt.Dimension(640, 680));
        this.setSize(new java.awt.Dimension(1024, 768));
    }
}