//Command Classes
//Work In Progress
import javax.swing.*;

interface Command 
{
    public abstract void execute ( );
}

interface CommandHandler
{
    public void setCommand( Command command);
    public Command getCommand();
}

class Button extends JButton implements CommandHandler
{
    Command myCommand;

    public Button(String text, Command command) 
    { 
        this.setText(text);
        this.setCommand(command);
    }

    public void setCommand( Command command){ this.myCommand = command; }

    public Command getCommand(){ return this.myCommand;}
}

class MenuJ extends JMenu implements CommandHandler
{
    Command myCommand;

    public MenuJ(String text, Command command)
    {
        this.setText(text);
        this.setCommand(command);
    }

    public void setCommand( Command command){ this.myCommand = command;}
    public Command getCommand(){ return this.myCommand;}
}
