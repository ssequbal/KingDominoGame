/*
    Description:
        This is the Domino Object
*/

public class Domino {
    //variables
    private int Num;
    public Square[] Squares;

    //init
    public Domino(Terrains terrain1, int crown1, Terrains terrain2, int crown2, int num)
    {
        Squares = new Square[2];
        Num = num;
        Squares[0] = new Square(terrain1, Num, crown1);
        Squares[1] = new Square(terrain2, Num, crown2);
    }

    //setters and getters
    public int GetNum(){ return Num; }
    public Square GetSquare(int i){ return Squares[i]; }
    public Terrains GetTerrain(int i){ return Squares[i].GetTerrain(); }

    @Override
    public String toString() { 
        return "Domino:" +Num+ "\n"+Squares[0].toString() + "\n" + Squares[1].toString();
    }

}
