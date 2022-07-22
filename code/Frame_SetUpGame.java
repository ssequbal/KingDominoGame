import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame_SetUpGame extends MainFrame implements MouseListener
{

    class ColorButton extends JButton
    {
        public Color color;
        public String name;
        public ColorButton(Color color, String name, Frame_SetUpGame outer)
        {
            this.color = color;
            this.name = name;
            this.setBackground(this.color);
            ActionListener _color = new ActionListener(){public void actionPerformed(ActionEvent e){ outer.color = getColor();}};
            this.addActionListener(_color);
            this.setPreferredSize(new java.awt.Dimension(50, 50));
        }
        public String getColor() {return this.name;}
    }

    class PlayerPanel extends JPanel implements ActionListener
    {
        private JTextField EnterName;
        private JLabel PlayerName, PlayerNum, SelectColor;
        private ColorButton[] Colors;
        private Integer num;

        public PlayerPanel(Integer num, Frame_SetUpGame outer)
        {
            EnterName = new JTextField("Enter Name");
            PlayerName = new JLabel("Player Name:");
            PlayerNum = new JLabel("Player "+num.toString());
            SelectColor = new JLabel("Select a Color");
            Colors = new ColorButton[4];
            Colors[0] = new ColorButton(Color.green, "green",outer);
            Colors[1] = new ColorButton(Color.red, "red",outer);        
            Colors[2] = new ColorButton(Color.yellow, "yellow",outer);        
            Colors[3] = new ColorButton(Color.blue, "blue",outer);        
            this.num = num;
            this.setPreferredSize(new java.awt.Dimension(470, 250));

            //#region Panel Layout
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PlayerNum)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(PlayerName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(EnterName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Colors[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Colors[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Colors[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Colors[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(SelectColor))
                    .addContainerGap(67, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PlayerNum)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PlayerName)
                        .addComponent(EnterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(SelectColor)
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Colors[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Colors[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Colors[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Colors[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(53, Short.MAX_VALUE))
            );
            //#endregion
        
            EnterName.addActionListener(this);
            Colors[0].addActionListener(this);
            Colors[1].addActionListener(this);
            Colors[2].addActionListener(this);
            Colors[3].addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if(evt.getSource() == EnterName){
                PlayerNames[num-1] = evt.getActionCommand();
            }else{
                ColorButton but = (ColorButton)evt.getSource();
                PlayerColors[num-1] = but.color;
            }
        }
    }

    class StartCommand implements Command    { public void execute()     { 
        UI.CreateGameSystem();
        UI.Game.SetPlayerNames(PlayerNames,PlayerColors);
        UI.ChangeFrame(new Frame_Game(UI)); //Changed
        System.out.println("Game Made");
    }}
    class ExitCommand implements Command    { public void execute()     { System.exit(0);}}

    private PlayerPanel PlayerPanel1, PlayerPanel2, PlayerPanel3, PlayerPanel4;
    private Button Exit, Start;
    private String[] PlayerNames;
    private Color[] PlayerColors;
    private String color;
    private JSeparator Separator1;


    public Frame_SetUpGame(MainUI UI)
    {
        //JPanel p = new JPanel();
        //p.setBounds(x, y, width, height);
        
        super("SetUpGame", UI);

        //JPanel p = new JPanel();
        PlayerNames = new String[] {"Player 1", "Player 2","Player 3","Player 4"};
        PlayerColors = new Color[] {Color.RED, Color.BLUE, Color.YELLOW, Color.green};

        Separator1 = new JSeparator();
        PlayerPanel1 = new PlayerPanel(1,this);
        PlayerPanel2 = new PlayerPanel(2,this);
        PlayerPanel3 = new PlayerPanel(3,this);
        PlayerPanel4 = new PlayerPanel(4,this);

        //Button Stuff
        Exit = new Button("Exit",new ExitCommand()); 
        Start = new Button("Start",new StartCommand());
        Start.setPreferredSize(new java.awt.Dimension(100, 40));
        Exit.setPreferredSize(new java.awt.Dimension(100, 40));

        Start.addActionListener(this);
        Exit.addActionListener(this);

        //#region Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PlayerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PlayerPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PlayerPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PlayerPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    )
                    
                        
                .addContainerGap())
            .addComponent(Separator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(Separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PlayerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PlayerPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PlayerPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PlayerPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                
                .addContainerGap(348, Short.MAX_VALUE))

        );
        //#endregion

        this.pack();
        this.setVisible(true);//making the frame visible  
        this.setSize(new java.awt.Dimension(1024, 768));
    }
    // Mouse Listener events
    public void mouseClicked(MouseEvent mevt)
    {
        // get the object that was selected in the gui
        //Object selected = mevt.getSource();
    }
    
  
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    
}
