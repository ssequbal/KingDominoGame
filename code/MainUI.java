import java.awt.Color;

public class MainUI{
    public MainFrame f;
    public GameSystem Game;

    Color Color;
    public MainUI()
    {
        Color= Color.WHITE;
        f = new Frame_MainMenu(this);       
    }    
   
    public void CreateGameSystem()
    {
        //Create Game System
        Game = new GameSystem(this);
    }

    public void ChangeFrame(MainFrame ToFrame)
    {
        f.dispose();;
        f = ToFrame;
        f.getContentPane().setBackground(Color);
        f.setVisible(true);
    }
    public Color returnColor(){
        return Color;
    }
    public void ChangeColor(Color color){
        Color = color;
        f.getContentPane().setBackground(color);

    }
}
