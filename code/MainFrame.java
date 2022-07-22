import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener, MouseListener
{

    protected MainUI UI;
    

    public MainFrame(String name,MainUI UI)
    {   
        super(name);
        this.UI = UI;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(new java.awt.Dimension(1024, 768));
    }

    public void changeColor(Color color){
        this.setBackground(color);
    }

    public void actionPerformed(ActionEvent event)
    {
        ((CommandHandler) event.getSource()).getCommand().execute();;
    }

    //Mouse Listener
    public void mouseClicked(MouseEvent mevt)
    {
        ((CommandHandler) mevt.getSource()).getCommand().execute();;
    }

    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent mevt){}
    public void mouseExited(MouseEvent mevt) {}
    public void mousePressed(MouseEvent mevt) {}
    public void mouseReleased(MouseEvent mevt) {}
    public void mouseWheelMoved(MouseWheelEvent e){}
}


