/*
    Description:
        This is the Manager for the game.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameSystem implements ActionListener, MouseListener
{ 
    class PickPileCommand implements Command    { public void execute()     { }}
    

    class DisplayText extends JTextArea{
        public DisplayText(int x,int y){
            super(x,y);
            this.setFont(new Font("Serif", Font.BOLD, 20));
            this.setEditable(false);
        }
        public void SetStuff(int x, int y, int width, int height){
            this.setBounds(x, y, width, height);
        }
        public void SetText(String text){
            this.setText(text);
        }
    }
    //variables
    public States State_current;
    private boolean State_is_new; //To check if we have a function to pass at the start of the state but should not repeat
    private int State_timer; 
    private MainUI UI;

    public Player[] Players; //Holds the struct of the Players, this should not be changed
    public int[] Order; //This holds the player order, the int are the index for the Players array
    public Grid[] PlayerGrids; //Holds the different grids

    public ArrayList<Integer> DrawPile;
    public PickDomino[] PickPile;

    static List<Domino> Dominoes;

    private int[] rotate = {0,0,0,0};
    public int turn = 0; //the index for Order array, finds whose turn it is in the round
    private Point[] selected = {new Point(-1,-1),new Point(-1,-1)};  
    public DisplayText displayText;  
    

    //WIP

    /*
        Dominoes stores all the dominoes in the game, it should not be updated but only referenced.
    */

    public GameSystem(MainUI UI)
    {
        State_current = States.Start;
        State_is_new = true;
        State_timer = 0;
        Order = new int[] {0,1,2,3};
        Players = CreatePlayers(4);
        PlayerGrids = CreateGrids(4);
        Dominoes = LoadDominoesCSV.readDominoesFromCSV("Dominoes.csv"); //Loading Dominoes from CSV File
        DrawPile = CreateDrawPile(); //Holds the Numbers corresponding to Dominoes
        PickPile = CreatePickPile(); //Needs more work
        displayText =  new DisplayText(0, 0);
        this.UI = UI;
        

        Update();
    }

    //Update per frame (Main)
    public void Update()
    {
        //This will be main method that runs every frame
        //Track Mouse pos

        var _state_current	= State_current; 

        //State control
        switch (State_current)
        {
            case Start:
                State_Start();
                break;
            case Play:
                State_Play();
                break;
            case End:
                State_End();
                break;
        }

        //This is to check if its the first time entering a state
        State_is_new		= _state_current != State_current;
        State_timer		= State_is_new ? 0 : State_timer + 1;
    }

    //#region State Methods
    public void State_Start()
    {
        if(State_is_new){
            System.out.println("Start State");
            DrawDominoes();
            ExposeDominoes(0,4);
            System.out.println("Dominoes Drawn & Exposed");    
        }
        State_current = States.SelectDomino;
        showInfo("Player "+(turn+1)+" Select a Domino");
    }

    public void State_Play()
    {
        System.out.println("Play State");
    }

    public void State_SelectDomino(){}
    
    public void State_buildingKingdom(){}

    public void State_End()
    {
        //EndGame, results will be written on debug for now.
        System.out.println("EndGame");
        var _winner = 0;
        for(var i=1; i<4; i++){
            if(CrownTotal.total(PlayerGrids[_winner])<CrownTotal.total(PlayerGrids[i])){ _winner = i;}
        }
        String winner = "Player "+_winner;
        UI.ChangeFrame(new Frame_EndingGame(UI, winner));
    }
    //#endregion

    //#region Methods to run when System Begins
    public Player[] CreatePlayers(int size)
    {
        var _players = new Player[4];
        
        for (var i = 0; i < size; ++i) {
            var _player = new Player();
            _players[i] = _player;
        }
        return _players;
    }

    public Grid[] CreateGrids(int size)
    {
        //Game System Creates the Grids and Frame_Game holds ID
        var _grids = new Grid[4];
        for (var i = 0; i < size; ++i) {
            //_grid.setSquareTile(5, 5, new StartingTile(Terrains.Start));// Loop Grid to create tiles then do this
            _grids[i] = new Grid(i);
            //_grids[i].addMouseListener(this);
            for (int a = 0; a < 11; a++) {
                for (int b = 0; b < 11; b++){
                    _grids[i].getGridSquare(a, b).addMouseListener(this);
                    //_grids[i].addMouseWheelListener(this);
                }
            }
        }
        return(_grids);
    }
    
    public ArrayList<Integer> CreateDrawPile() //Creates The Draw Pile
    {
        var _draw = new ArrayList<Integer>();
        var _list = new ArrayList<Domino>(Dominoes);

        Collections.shuffle(_list); //Shuffle Draw Pile

        for (Domino d : _list)
        {
            _draw.add(d.GetNum()); //Add Domino number to draw pile
        }

        return(_draw);
    }
    
    public PickDomino[] CreatePickPile() //Creates The Pick Pile
    {
        PickDomino[] _pile = new PickDomino[8];
        for (int a = 0; a < 8; a++) {
            var can = (a<4);
            _pile[a] = new PickDomino(can);
            _pile[a].addActionListener(this);
            _pile[a].setCommand(new PickPileCommand());
        }
        return _pile;
    }
    //#endregion

    //Setters
    public void SetPlayerNames(String[] Names, java.awt.Color[] Colors) //And Color
    {
        for (var i = 0; i < 4; ++i) { //2 for now
            if( Names != null){ Players[i].SetName(Names[i]);}
            if( Colors != null){Players[i].SetColor(Colors[i]);}
        }
    }
    public DisplayText CreateDisplayText(){
        displayText =  new DisplayText(0, 0);
        return displayText;
    }

    //Getters
    public Grid GetGrid(int i)
    {
        return(PlayerGrids[i]);
    }
    public PickDomino GetPickDomino(int i)
    {
        return(PickPile[i]);
    }
    public Player GetCurrentPlayer()
    {
        var _player = Players[turn];
        return _player;
    }
    public DisplayText GetDisplayText(){
        return displayText;
    }

    //#region System Methods
    public void DrawDominoes()
    {
        //If draw pile empty End game
        if(DrawPile.size() == 0){ 
            State_current = States.End;
            Update();
            return;
        }
        //Draw 4 Dominoes from the DrawPile
        var _draw = new ArrayList<Integer>();
        _draw.add(DrawPile.remove(0));
        _draw.add(DrawPile.remove(0));
        _draw.add(DrawPile.remove(0));
        _draw.add(DrawPile.remove(0));
        Collections.sort(_draw);  // Sort cars
        for (var i = 0; i < 4; ++i) {
            PickPile[i+4].SetNum(PickPile[i].GetNum());
            PickPile[i+4].SetSelected(PickPile[i].GetSelected());
            PickPile[i+4].RefreshDomino();
            PickPile[i].SetNum(_draw.get(i));
            PickPile[i].Clean();
            PickPile[i].RefreshDomino();
        }
    }
 
    public void DiscardDomino()
    {
        if(State_current == States.KingBuilding){
            var current = GetCurrentPlayer();
            current.SetDomino(0);
            showInfo("Player "+turn+" has Discarded Turn");
            NextTurn();
            if(turn == 0){ State_current = States.SelectDomino; showInfo("Player "+(turn+1)+" Select a Domino"); }
            //DisplayPlacingDomino(grd.getGridSquare(selected[0]),false);
            selected[0] = new Point(-1,-1);
            selected[1] = new Point(-1,-1);
        }
    }

    public void KingdomBuilding(GridSquare sqr)
    {
        Grid grd = sqr.getParentGrid();
        if(turn != grd.getNum()) { return;}
        var current = GetCurrentPlayer();
        var num = current.GetDomino();
        if(num==-1){ //Check Player has selected a domino
            return;
        }
        //WIP: CanConnect()
        if(selected[0].getX() == -1){
            var _pnt = new Point(sqr.getXcoord(),sqr.getYcoord());
            if(CheckConnection(grd, Dominoes.get(num), _pnt, 0)){ 
                selected[0] = _pnt; 
                DisplayPlacingDomino(sqr, true);
                showInfo("First Square Placed\nPlace 2nd Square\nOr Discard Domino and Turn");
            }else { showInfo("Un-able to place square at "+_pnt+"\nTry again or Discard Domino and Turn");}
        }else{
            var _pnt = new Point(sqr.getXcoord(),sqr.getYcoord());
            if(Check2ndSquare(grd, Dominoes.get(num), selected[0], _pnt)){
                selected[1] = _pnt;
                var _domino = Dominoes.get(num);
                GridConstraints(selected,grd);
                PlaceDomino(grd,_domino, selected);
                showInfo("Player "+(turn+1)+" has placed domino");
                NextTurn();
                if(turn == 0){
                    State_current = States.SelectDomino; showInfo("Player "+(turn+1)+" Select a Domino");
                }else{ showInfo("Player "+(turn+1)+" Place the first square");}
            }else { showInfo("Un-able to place square at"+_pnt+"\nTry again or Discard Domino and Turn");}
            DisplayPlacingDomino(grd.getGridSquare(selected[0]),false);
            selected[0] = new Point(-1,-1);
            selected[1] = new Point(-1,-1);
        }  
    }
    
    public void PlaceDomino(Grid grd,Domino _domino, Point[] points){
        var tile = _domino.GetSquare(0);
        var tile2 = _domino.GetSquare(1);//new Tile(_domino.GetTerrain(1));
        grd.setSquareTile(points[0], tile);
        grd.setSquareColor(points[0]);
        grd.setSquareTile(points[1], tile2);
        grd.setSquareColor(points[1]);
        var current = GetCurrentPlayer();
        current.SetDomino(0);
    }

    public void ExposeDominoes(int x1, int x2)
    {
        for(int i = x1; i < x2; i++){
            PickPile[i].Expose();
            PickPile[i].RefreshDomino();
        }
    }
    
    public void DominoSelection(PickDomino pick)
    {
        System.out.println(pick.toString());
        if(pick.GetNum()==-1 || pick.GetSelected() != -1){ return; } //Check if claimed or doesnt num a domino

        //Claim Domino
        var current = GetCurrentPlayer(); 
        pick.SetSelected(turn);
        current.SetDomino(pick.GetNum());

        //System.out.println("Domino Selected");
        
        var dom = Dominoes.get(pick.GetNum());
        System.out.println(dom);
        
        NextTurn();
        if(turn == 0){ 
            DrawDominoes();
            ExposeDominoes(4,8);
            State_current = States.KingBuilding; showInfo("Player "+(turn+1)+" Place the 1st square");
        }else{
            showInfo("Player "+(turn+1)+" Select a Domino");
        }
        
        Update();
    }

    public void NextTurn()
    {
        turn += 1;
        System.out.println("Next Turn");
        if(turn<0) turn=3;
        if(turn>3) turn=0;
    }
    
    public boolean CheckConnection(Grid grid,Domino domino, Point point, int n)
    {
        Terrains _terrain = domino.GetTerrain(n); //Terrain of selected domino
        var _x = (int) point.getX();
        var _y = (int) point.getY();

        //Checking Square ontop
        var _Onsquare = grid.getSquareTile(_x, _y);
        if(_Onsquare != null) {return false;}
        
        if(n==1){ return true;}
        //Checking Near Squares
        var x2 = 0;
        var y2 = 0;
        //System.out.println("Point: "+_x+","+_y);
        for (var i=0; i<4; ++i){
            if(i==0){x2=1; y2=0;}
            if(i==1){y2=1; x2=0;}
            if(i==2){x2=-1; y2=0;}
            if(i==3){y2=-1; x2=0;}    

            x2 = x2 + _x;
            y2 = y2 + _y;
            //System.out.println("Point "+i+": "+x2+","+y2);
            if(x2<0 || y2<0 || x2>10 || y2>10){ continue;}
        
            if(n==0){
                _Onsquare = grid.getSquareTile(x2, y2);
                if(_Onsquare != null)
                {
                    if(_Onsquare.GetTerrain() == Terrains.Start)        { return CheckLimit(point,grid);}
                    if(_terrain == _Onsquare.GetTerrain())              { return CheckLimit(point,grid);}
                }
            }  
        }
        return false;
    }

    public boolean Check2ndSquare(Grid grid,Domino domino, Point _square1Pnt, Point _square2Pnt){
        //Check near first
        var _x = _square2Pnt.getX() - _square1Pnt.getX();
        var _y = _square2Pnt.getY() - _square1Pnt.getY();
        if((_x<=1 && _x>=-1 && _y==0) || (_y<=1 && _y>=-1 && _x==0)){
            if(CheckConnection(grid, domino, _square2Pnt, 1)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean CheckLimit(Point point, Grid grd)
    {
        //return checking(grd, firstUsedTile(grd), point);
        return true;
    }
    
    public void GridConstraints(Point[] points, Grid grd)
    {
        //grd.GridConstraints(points);
    }

    //#endregion

    //#region Display Functions
    public void showInfo(String text){
        //Display Text to DisplayText
        displayText.SetText(text);
    }
    //Prototype to show Dominoes when placing
    public void DisplayPlacingDomino(GridSquare centre, boolean _blink)
    {
        //Centre is where the left square of the domino is, also where the mouse is
        //Right now lets just assume the rotation is to the right
        Grid grd = centre.getParentGrid();
        if(turn != grd.getNum()) { return;}
        var _x = centre.getXcoord();
        var _y = centre.getYcoord()+1;
        //if(_x>=0 && _x<11 && _y>=0 && _y<11){
        //    GridSquare sqr2 = grd.getGridSquare(_x, _y);
        //    sqr2.Blink(_blink);
        //}
        centre.Blink(_blink);
    }
    
    public void ImageProcess(GridSquare sqr,GridSquare sqr1, Integer num)
    {
        String os = System.getProperty("os.name");
        var slash = "/";
        if(os.substring(0,7).equals("Windows")){
            slash = "\\";
        }
        num = num + 1;
        ImageIcon icon1 =  new ImageIcon("Images"+slash+"Domino "+num+"_row-1-column-1.png");
        ImageIcon icon2 =  new ImageIcon("Images"+slash+"Domino "+num+"_row-1-column-2.png");
        JLabel Part1 = new JLabel(icon1);
        JLabel Part2 = new JLabel(icon2);
        sqr.add(Part1);
        sqr1.add(Part2);
    }
    //#endregion

    //Action Listener
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if (obj instanceof PickDomino) {
            if(State_current==States.SelectDomino){ DominoSelection( (PickDomino)obj ); }
        }  
    }

    //Mouse Listener
    public void mouseClicked(MouseEvent mevt)
    {
        // get the object that was selected in the gui
		Object selected = mevt.getSource();

        if (selected instanceof GridSquare){
            GridSquare sqr = (GridSquare)selected;
            if(State_current==States.KingBuilding){ KingdomBuilding(sqr);}
        }
    }
    
    
  
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent mevt)
    {
        Object selected = mevt.getSource();
        if (selected instanceof GridSquare){
            GridSquare sqr = (GridSquare)selected;
            //DisplayPlacingDomino(sqr,true);
        }
    }
    public void mouseExited(MouseEvent mevt) {
        Object selected = mevt.getSource();
        if (selected instanceof GridSquare){
            GridSquare sqr = (GridSquare)selected;
            //DisplayPlacingDomino(sqr,false);
        }
    }
    public void mousePressed(MouseEvent mevt) {}
    public void mouseReleased(MouseEvent mevt) {}
    public void mouseWheelMoved(MouseWheelEvent e){}
    
    
    public static Point firstUsedTile(Grid _grids) {
        String found= "n";
        int vertical = 0;
        int horizontal = 0;
        for (int a = 0;found== "n"&& a<11; a++) {
            for (int b = 0;found== "n"&& b<11; b++){
                if(_grids.getSquareTile(a,b) != null){
                    found = "y";
                        vertical = b;
                        }
                    }}found = "n";
        for (int b = 0;found== "n"&& b<11; b++){
        for (int a = 0;found== "n"&& a<11; a++) {
        if(_grids.getSquareTile(a,b) != null){
        found = "y";
        horizontal = b;}}}return new Point(horizontal,vertical);
        
    }
    public static boolean checking(Grid _grids,Point p,Point e){
        int a = (int) p.getX();
        int b= (int)p.getY();
        int c = (int)e.getX();
        int d= (int)e.getY();
        if (c<=a+4 && d<=b+4 && c>=a && d>= b){return true;}
        return false;
    }

    //#region FileHandling
    public void SaveGame()
    {
        FileHandling objectIO = new FileHandling();
 
        GameData data = new GameData();
        data.StoreDate(this);
        objectIO.WriteObjectToFile(data);
    }
  

}




/*
    Whats Next?
    Know when all players have reached the limit
    Know when Draw Pile is empty
    Count Territories
    End Game
*/
